package de.dfki.smartfactoryKL.events;

import de.dfki.smartfactoryKL.ontology.Container;
import de.dfki.smartfactoryKL.ontology.RFID;
import de.dfki.smartfactoryKL.ontology.WeighingModule;
import de.dfki.smartfactoryKL.ontology.WeighingModuleOntologyFactory;
import de.dfki.smartfactoryKL.utils.OntologyTranslator;
import uk.ac.manchester.cs.owl.owlapi.OWLDataFactoryImpl;
import uk.ac.manchester.cs.owl.owlapi.OWLLiteralImplDouble;
import uk.ac.manchester.cs.owl.owlapi.OWLLiteralImplInteger;

/**
 * Created by Amita on 3/25/2017.
 */
public class OntologyUpdater {
    private final WeighingModuleOntologyFactory wmof;

    public OntologyUpdater(WeighingModuleOntologyFactory wmof) {
        this.wmof = wmof;
    }

    public BrainWeighingModuleEvent applyEvent(WeighingSensorEvent wse) {

        // Update weight in the Ontology.
        WeighingModule wm = wse.getWeighingModule();
        wm.removeTotal_weight(wm.getTotal_weight().toArray()[0]);
        wm.addTotal_weight(new OWLLiteralImplDouble(wse.getWeight(), new OWLDataFactoryImpl().getDoubleOWLDatatype()));

        // Get new total number of parts.
        RFID rf = OntologyTranslator.getRFIDFrom(wm.getGetsDataFrom(), wmof);
        double weightPerPiece = rf.getWeight_per_piece().toArray(new OWLLiteralImplDouble[0])[0].parseDouble();
        double totalWeightWithBox = wm.getTotal_weight().toArray(new OWLLiteralImplDouble[0])[0].parseDouble();
        Container con = OntologyTranslator.getContainerFrom(wm.getHasContainer(), wmof);
        double boxWeight = con.getWeight_of_box().toArray(new OWLLiteralImplDouble[0])[0].parseDouble();
        double totalWeight = totalWeightWithBox - boxWeight;
        int totalParts = (int) Math.round(totalWeight / weightPerPiece);

        // Update total number of parts.
        // Note: this can only handle xsd:integer and not xsd:int
        // Be sure to use xsd:integer while creating the ontology.
        wm.removeTotal_number_of_parts(wm.getTotal_number_of_parts().toArray()[0]);
        wm.addTotal_number_of_parts(new OWLLiteralImplInteger(totalParts, new OWLDataFactoryImpl().getIntegerOWLDatatype()));

        return new BrainWeighingModuleEvent(wm);
    }
}

