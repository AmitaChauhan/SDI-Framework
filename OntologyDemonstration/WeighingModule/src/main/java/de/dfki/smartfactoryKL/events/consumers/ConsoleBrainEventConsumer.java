package de.dfki.smartfactoryKL.events.consumers;

import de.dfki.smartfactoryKL.events.IBrainEvent;

/**
 * Created by Amita on 3/26/2017.
 */
public class ConsoleBrainEventConsumer implements IBrainEventConsumer {

    @Override
    public void consumeEvent(IBrainEvent brainWeighingModuleEvent) {
        System.out.println(brainWeighingModuleEvent);
    }
}
