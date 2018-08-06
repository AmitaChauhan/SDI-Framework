package de.dfki.smartfactoryKL.events;

import de.dfki.smartfactoryKL.ontology.WeighingModule;
import de.dfki.smartfactoryKL.simulator.WorkerAction;

/**
 * Created by Amita on 3/25/2017.
 */
public class SimulatedWeighingSensorEvent extends WeighingSensorEvent {
    private final WorkerAction workerAction;
    private final double exactWeight;
    private final double noise;

    public SimulatedWeighingSensorEvent(WeighingModule wm, double newWeight, WorkerAction workerAction, double noise, double exactWeight) {
        super(wm, newWeight);
        this.workerAction = workerAction;
        this.noise = noise;
        this.exactWeight = exactWeight;
    }

    public WorkerAction getWorkerAction() {
        return workerAction;
    }

    public double getExactWeight() {
        return exactWeight;
    }

    public double getNoise() {
        return noise;
    }

    public String toString() {
        return "SimulatedWeighingSensorEvent(" +
                "wm:" + getWeighingModule().toString() + ", " +
                "newWeight: " + getWeight() + ", " +
                "workerAction: " + workerAction.toString() + ", " +
                "noise: " + noise + ", " +
                "exactWeight: " + exactWeight + ")";
    }
}
