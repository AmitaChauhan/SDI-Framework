package de.dfki.smartfactoryKL.simulator;

import de.dfki.smartfactoryKL.ontology.Container;

/**
 * This class represents an action taken by a worker.
 * Created by Amita on 3/23/2017.
 */
public class WorkerAction {

    private final int partsTaken;
    private final Container container;
    private final double delay;

    public WorkerAction(double delay, Container container, int partsTaken) {
        this.delay = delay;
        this.container = container;
        this.partsTaken = partsTaken;
    }

    public int getPartsTaken() {
        return partsTaken;
    }

    public Container getContainer() {
        return container;
    }

    public double getDelay() {
        return delay;
    }

    public String toString() {
        return "WorkerAction(" +
                "delay: " + delay + ", " +
                "container: " + container + ", " +
                "partsTaken: " + partsTaken + ")";
    }
}
