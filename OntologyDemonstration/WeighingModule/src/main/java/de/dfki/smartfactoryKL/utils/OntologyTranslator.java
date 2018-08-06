package de.dfki.smartfactoryKL.utils;

import de.dfki.smartfactoryKL.ontology.Container;
import de.dfki.smartfactoryKL.ontology.RFID;
import de.dfki.smartfactoryKL.ontology.WeighingModule;
import de.dfki.smartfactoryKL.ontology.WeighingModuleOntologyFactory;
import org.protege.owl.codegeneration.WrappedIndividual;

import java.util.Collection;

/**
 * Created by Amita on 3/25/2017.
 */
public class OntologyTranslator {
    public static WeighingModule getWeighingModuleFrom(Collection<? extends WrappedIndividual> collection, WeighingModuleOntologyFactory wmof) {
        return wmof.getWeighingModule(
                collection
                        .toArray(new WrappedIndividual[0])[0]
                        .getOwlIndividual()
                        .getIRI().toString()
        );
    }

    public static RFID getRFIDFrom(Collection<? extends WrappedIndividual> collection, WeighingModuleOntologyFactory wmof) {
        return wmof.getRFID(
                collection
                        .toArray(new WrappedIndividual[0])[0]
                        .getOwlIndividual()
                        .getIRI().toString()
        );
    }

    public static Container getContainerFrom(Collection<? extends WrappedIndividual> collection, WeighingModuleOntologyFactory wmof) {
        return wmof.getContainer(
                collection
                        .toArray(new WrappedIndividual[0])[0]
                        .getOwlIndividual()
                        .getIRI().toString()
        );
    }
}
