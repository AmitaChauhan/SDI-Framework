package de.dfki.smartfactoryKL.simulator;

import de.dfki.smartfactoryKL.ontology.Container;
import de.dfki.smartfactoryKL.ontology.WeighingModuleOntologyFactory;

import java.util.Random;

/**
 * Created by Amita on 3/23/2017.
 */
public class HumanSimulator {
    WeighingModuleOntologyFactory wmof;
    Random rg;
    double timeScale;

    public HumanSimulator(WeighingModuleOntologyFactory wmof, int seed, double timeScale) {
        this.wmof = wmof;
        this.rg = new Random(seed);
        this.timeScale = timeScale;
    }

    Container selectRandomBox() {
        Container[] containerArray = wmof.getAllContainerInstances().toArray(new Container[0]);
        int selection = Math.abs(this.rg.nextInt()) % containerArray.length;
        return containerArray[selection];
    }

    int selectNumParts() {
        return 1 + (Math.abs(this.rg.nextInt()) % 5);
    }

    public WorkerAction generateAction() {
        double delay = this.rg.nextDouble() * 10;
        Container randContainer = selectRandomBox();

        return new WorkerAction(delay, randContainer, selectNumParts());
    }
}
