package de.dfki.smartfactoryKL.events;

import de.dfki.smartfactoryKL.ontology.WeighingModule;

/**
 * Created by Utkarsh on 3/25/2017.
 */
public class WeighingSensorEvent {

    private final double weight;
    private final WeighingModule weighingModule;

    public WeighingSensorEvent(WeighingModule wm, double weight) {
        this.weighingModule = wm;
        this.weight = weight;
    }

    public WeighingModule getWeighingModule() {
        return weighingModule;
    }

    public double getWeight() {
        return weight;
    }

    public String toString() {
        return "WeighingSensorEvent for " + this.weighingModule.toString() + " : " + this.weight;
    }
}
