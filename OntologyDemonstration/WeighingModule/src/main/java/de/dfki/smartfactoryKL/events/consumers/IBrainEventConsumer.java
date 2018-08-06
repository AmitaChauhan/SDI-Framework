package de.dfki.smartfactoryKL.events.consumers;

import de.dfki.smartfactoryKL.events.IBrainEvent;

/**
 * Created by Amita on 3/26/2017.
 */
public interface IBrainEventConsumer {
    void consumeEvent(IBrainEvent brainWeighingModuleEvent);
}