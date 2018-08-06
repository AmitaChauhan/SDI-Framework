package de.dfki.smartfactoryKL.events.consumers;

import de.dfki.smartfactoryKL.events.IBrainEvent;
import org.zeromq.ZMQ;

/**
 * Created by Amita on 3/26/2017.
 */
public class ZeroMQBrainEventConsumer implements IBrainEventConsumer {
    private final ZMQ.Context context;
    private final ZMQ.Socket socket;
    private final boolean verbose;

    private boolean closed;

    public ZeroMQBrainEventConsumer(String addr) {
        this(addr, false);
    }

    public ZeroMQBrainEventConsumer(String addr, boolean verbose) {
        this.context = ZMQ.context(1);
        this.socket = context.socket(ZMQ.REQ);
        this.socket.connect(addr);
        this.verbose = verbose;
        closed = false;
    }

    public void close() {
        this.socket.close();
        context.term();
        closed = true;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (!closed) {
            System.err.println("ZeroMQBrainEventConsumer not closed properly.");
        }
    }

    @Override
    public void consumeEvent(IBrainEvent brainEvent) {
        // Should replace this REP-REQ model with a PUB-SUB or something else. SEND-RECV?
        this.socket.send(brainEvent.toString());
        String resp = this.socket.recvStr();
        if (verbose) {
            System.err.println("Received Reply: " + resp);
        }
    }
}
