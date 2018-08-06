package de.dfki.smartfactoryKL.events;

import de.dfki.smartfactoryKL.ontology.WeighingModuleOntologyFactory;

/**
 * Created by Amita on 3/26/2017.
 */
public class WeighingModuleInitEvent implements IBrainEvent {

    private final WeighingModuleOntologyFactory wmof;

    public WeighingModuleInitEvent(WeighingModuleOntologyFactory wmof) {
        this.wmof = wmof;
    }

    public String toString() {
        return "Init: " + wmof.getOwlOntology().getOWLOntologyManager().getOntologyDocumentIRI(wmof.getOwlOntology());
    }
}
