package de.dfki.smartfactoryKL;

import de.dfki.smartfactoryKL.events.consumers.ZeroMQBrainEventConsumer;
import de.dfki.smartfactoryKL.ontology.WeighingModule;
import de.dfki.smartfactoryKL.ontology.WeighingModuleOntologyFactory;
import de.dfki.smartfactoryKL.simulator.SimulationManager;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

import java.io.File;

public class Main {

    public static void printStep(WeighingModuleOntologyFactory wmof, int step) {
        System.out.println("Step " + step);
        for (WeighingModule wm : wmof.getAllWeighingModuleInstances()) {
            System.out.println(wm);
        }
    }

    public static void main(String[] args) {
        File file = new File(
                "C:\\Users\\Utkarsh\\Desktop\\FinalOntology22503 - Copy.owl");
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLDataFactory factory = OWLManager.getOWLDataFactory();

        ZeroMQBrainEventConsumer brainEventConsumer = new ZeroMQBrainEventConsumer("tcp://localhost:5555", true);

        try {
            OWLOntology ontology = manager.loadOntologyFromOntologyDocument(file);
            WeighingModuleOntologyFactory wmof = new WeighingModuleOntologyFactory(ontology);
            // IBrainEventConsumer brainEventConsumer = new ConsoleBrainEventConsumer();

            SimulationManager sm = new SimulationManager(wmof, 42, brainEventConsumer);

            for (int ii = 0; ii < 5; ii++) {
                // printStep(wmof, ii);
                sm.runStep();
            }
        } catch (OWLOntologyCreationException e) {
            System.err.println("Unable to load wmof.");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.err.println("Interrupted by the user.");
        } finally {
            brainEventConsumer.close();
        }
    }

}
