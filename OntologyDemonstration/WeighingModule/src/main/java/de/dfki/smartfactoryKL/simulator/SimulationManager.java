package de.dfki.smartfactoryKL.simulator;

import de.dfki.smartfactoryKL.events.BrainWeighingModuleEvent;
import de.dfki.smartfactoryKL.events.OntologyUpdater;
import de.dfki.smartfactoryKL.events.SimulatedWeighingSensorEvent;
import de.dfki.smartfactoryKL.events.consumers.IBrainEventConsumer;
import de.dfki.smartfactoryKL.ontology.Container;
import de.dfki.smartfactoryKL.ontology.RFID;
import de.dfki.smartfactoryKL.ontology.WeighingModule;
import de.dfki.smartfactoryKL.ontology.WeighingModuleOntologyFactory;
import de.dfki.smartfactoryKL.utils.OntologyTranslator;
import uk.ac.manchester.cs.owl.owlapi.OWLLiteralImplDouble;

import java.util.Random;

/**
 * Created by Amita on 3/25/2017.
 */
public class SimulationManager {
    private final WeighingModuleOntologyFactory wmof;
    private final HumanSimulator humanSimulator;
    private final Random rg;
    private final OntologyUpdater ontologyUpdater;
    private final IBrainEventConsumer brainEventConsumer;

    public SimulationManager(WeighingModuleOntologyFactory wmof, int seed, IBrainEventConsumer brainEventConsumer) {
        this.wmof = wmof;
        this.humanSimulator = new HumanSimulator(wmof, seed, 10);
        this.rg = new Random(seed + 1001);
        this.ontologyUpdater = new OntologyUpdater(wmof);
        this.brainEventConsumer = brainEventConsumer;
    }

    private SimulatedWeighingSensorEvent generateSensorEvent(WorkerAction action) {
        Container container = action.getContainer();

        WeighingModule wm = OntologyTranslator.getWeighingModuleFrom(container.getIsKeptOn(), wmof);
        RFID rfid = OntologyTranslator.getRFIDFrom(wm.getGetsDataFrom(), wmof);

        double totalWeight = wm.getTotal_weight().toArray(new OWLLiteralImplDouble[0])[0].parseDouble();
        double partWeight = rfid.getWeight_per_piece().toArray(new OWLLiteralImplDouble[0])[0].parseDouble();
        double tolerance = rfid.getTolerance_of_weight().toArray(new OWLLiteralImplDouble[0])[0].parseDouble();
        double precision = wm.getPrecision_of_weighing_sensor().toArray(new OWLLiteralImplDouble[0])[0].parseDouble();
        double noise = this.rg.nextGaussian() * tolerance;
        double newExactWeight = totalWeight - partWeight * action.getPartsTaken() + noise;
        double diff = newExactWeight % precision;
        double newWeight = (diff > precision / 2) ? newExactWeight + (precision - diff) : newExactWeight - diff;

        return new SimulatedWeighingSensorEvent(wm, newWeight, action, noise, newExactWeight);
    }

    public void runStep() throws InterruptedException {
        WorkerAction action = this.humanSimulator.generateAction();
        WeighingModule wm = OntologyTranslator.getWeighingModuleFrom(action.getContainer().getIsKeptOn(), this.wmof);
        int oldNumParts = wm.getTotal_number_of_parts().toArray(new Integer[0])[0];

        Thread.sleep((long) (1000 * action.getDelay()));
        SimulatedWeighingSensorEvent wse = generateSensorEvent(action);

        // Update the Ontology.
        BrainWeighingModuleEvent brainWeighingModuleEvent = ontologyUpdater.applyEvent(wse);

        int newNumParts = wm.getTotal_number_of_parts().toArray(new Integer[0])[0];
        System.out.println("Actual number of parts = " + (oldNumParts - action.getPartsTaken()) +
                "; Calculated = " + newNumParts);

        this.brainEventConsumer.consumeEvent(brainWeighingModuleEvent);
    }
}
