package de.dfki.smartfactoryKL.events;

        import de.dfki.smartfactoryKL.ontology.WeighingModule;

/**
 * Created by Amita on 3/26/2017.
 */
public class BrainWeighingModuleEvent implements IBrainEvent {
    private final WeighingModule weighingModule;

    public BrainWeighingModuleEvent(WeighingModule weighingModule) {
        this.weighingModule = weighingModule;
    }

    public WeighingModule getWeighingModule() {
        return weighingModule;
    }

    public String toString() {
        return "BrainWeighingModuleEvent: " +
                getWeighingModule().getOwlIndividual().getIRI() + ": " +
                getWeighingModule().getTotal_number_of_parts();
    }
}
