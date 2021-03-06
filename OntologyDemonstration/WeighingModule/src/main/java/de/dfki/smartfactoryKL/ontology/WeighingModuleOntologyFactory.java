package de.dfki.smartfactoryKL.ontology;

import de.dfki.smartfactoryKL.ontology.impl.DefaultContainer;
import de.dfki.smartfactoryKL.ontology.impl.DefaultRFID;
import de.dfki.smartfactoryKL.ontology.impl.DefaultWeighingModule;
import org.protege.owl.codegeneration.CodeGenerationFactory;
import org.protege.owl.codegeneration.WrappedIndividual;
import org.protege.owl.codegeneration.impl.FactoryHelper;
import org.protege.owl.codegeneration.impl.ProtegeJavaMapping;
import org.protege.owl.codegeneration.inference.CodeGenerationInference;
import org.protege.owl.codegeneration.inference.SimpleInference;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

import java.util.Collection;

/**
 * A class that serves as the entry point to the generated code providing access
 * to existing individuals in the ontology and the ability to create new individuals in the ontology.<p>
 * 
 * Generated by Protege (http://protege.stanford.edu).<br>
 * Source Class: WeighingModuleOntologyFactory<br>
 * @version generated on Sat Mar 25 23:36:08 CET 2017 by Utkarsh
 */
public class WeighingModuleOntologyFactory implements CodeGenerationFactory {
    private OWLOntology ontology;
    private ProtegeJavaMapping javaMapping = new ProtegeJavaMapping();
    private FactoryHelper delegate;
    private CodeGenerationInference inference;

    {
        javaMapping.add("http://www.semanticweb.org/ontologies/2017/1/WeighingModuleOntology1#Container", Container.class, DefaultContainer.class);
    }

    {
        javaMapping.add("http://www.semanticweb.org/ontologies/2017/1/WeighingModuleOntology1#RFID", RFID.class, DefaultRFID.class);
    }

    {
        javaMapping.add("http://www.semanticweb.org/ontologies/2017/1/WeighingModuleOntology1#WeighingModule", WeighingModule.class, DefaultWeighingModule.class);
    }

    public WeighingModuleOntologyFactory(OWLOntology ontology) {
        this(ontology, new SimpleInference(ontology));
    }

    public WeighingModuleOntologyFactory(OWLOntology ontology, CodeGenerationInference inference) {
        this.ontology = ontology;
        this.inference = inference;
        javaMapping.initialize(ontology, inference);
        delegate = new FactoryHelper(ontology, inference);
    }

    public OWLOntology getOwlOntology() {
        return ontology;
    }

    public void saveOwlOntology() throws OWLOntologyStorageException {
        ontology.getOWLOntologyManager().saveOntology(ontology);
    }

    public void flushOwlReasoner() {
        delegate.flushOwlReasoner();
    }

    public boolean canAs(WrappedIndividual resource, Class<? extends WrappedIndividual> javaInterface) {
        return javaMapping.canAs(resource, javaInterface);
    }

    public <X extends WrappedIndividual> X as(WrappedIndividual resource, Class<? extends X> javaInterface) {
        return javaMapping.as(resource, javaInterface);
    }

    /* ***************************************************
     * Class http://www.semanticweb.org/ontologies/2017/1/WeighingModuleOntology1#Container
     */

    public Class<?> getJavaInterfaceFromOwlClass(OWLClass cls) {
        return javaMapping.getJavaInterfaceFromOwlClass(cls);
    }

    public OWLClass getOwlClassFromJavaInterface(Class<?> javaInterface) {
        return javaMapping.getOwlClassFromJavaInterface(javaInterface);
    }

    public CodeGenerationInference getInference() {
        return inference;
    }

    /**
     * Creates an instance of type Container.  Modifies the underlying ontology.
     */
    public Container createContainer(String name) {
        return delegate.createWrappedIndividual(name, Vocabulary.CLASS_CONTAINER, DefaultContainer.class);
    }


    /* ***************************************************
     * Class http://www.semanticweb.org/ontologies/2017/1/WeighingModuleOntology1#RFID
     */

    /**
     * Gets an instance of type Container with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public Container getContainer(String name) {
        return delegate.getWrappedIndividual(name, Vocabulary.CLASS_CONTAINER, DefaultContainer.class);
    }

    /**
     * Gets all instances of Container from the ontology.
     */
    public Collection<? extends Container> getAllContainerInstances() {
        return delegate.getWrappedIndividuals(Vocabulary.CLASS_CONTAINER, DefaultContainer.class);
    }

    /**
     * Creates an instance of type RFID.  Modifies the underlying ontology.
     */
    public RFID createRFID(String name) {
        return delegate.createWrappedIndividual(name, Vocabulary.CLASS_RFID, DefaultRFID.class);
    }

    /**
     * Gets an instance of type RFID with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public RFID getRFID(String name) {
        return delegate.getWrappedIndividual(name, Vocabulary.CLASS_RFID, DefaultRFID.class);
    }


    /* ***************************************************
     * Class http://www.semanticweb.org/ontologies/2017/1/WeighingModuleOntology1#WeighingModule
     */

    /**
     * Gets all instances of RFID from the ontology.
     */
    public Collection<? extends RFID> getAllRFIDInstances() {
        return delegate.getWrappedIndividuals(Vocabulary.CLASS_RFID, DefaultRFID.class);
    }

    /**
     * Creates an instance of type WeighingModule.  Modifies the underlying ontology.
     */
    public WeighingModule createWeighingModule(String name) {
        return delegate.createWrappedIndividual(name, Vocabulary.CLASS_WEIGHINGMODULE, DefaultWeighingModule.class);
    }

    /**
     * Gets an instance of type WeighingModule with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public WeighingModule getWeighingModule(String name) {
        return delegate.getWrappedIndividual(name, Vocabulary.CLASS_WEIGHINGMODULE, DefaultWeighingModule.class);
    }

    /**
     * Gets all instances of WeighingModule from the ontology.
     */
    public Collection<? extends WeighingModule> getAllWeighingModuleInstances() {
        return delegate.getWrappedIndividuals(Vocabulary.CLASS_WEIGHINGMODULE, DefaultWeighingModule.class);
    }


}
