package de.dfki.smartfactoryKL.ontology.impl;

import de.dfki.smartfactoryKL.ontology.Container;
import de.dfki.smartfactoryKL.ontology.Vocabulary;
import org.protege.owl.codegeneration.WrappedIndividual;
import org.protege.owl.codegeneration.impl.WrappedIndividualImpl;
import org.protege.owl.codegeneration.inference.CodeGenerationInference;
import org.semanticweb.owlapi.model.IRI;

import java.util.Collection;


/**
 * Generated by Protege (http://protege.stanford.edu).<br>
 * Source Class: DefaultContainer <br>
 * @version generated on Sat Mar 25 23:36:08 CET 2017 by Utkarsh
 */
public class DefaultContainer extends WrappedIndividualImpl implements Container {

    public DefaultContainer(CodeGenerationInference inference, IRI iri) {
        super(inference, iri);
    }





    /* ***************************************************
     * Object Property http://www.semanticweb.org/ontologies/2017/1/WeighingModuleOntology1#getsDataFrom
     */

    public Collection<? extends WrappedIndividual> getGetsDataFrom() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                Vocabulary.OBJECT_PROPERTY_GETSDATAFROM,
                WrappedIndividualImpl.class);
    }

    public boolean hasGetsDataFrom() {
        return !getGetsDataFrom().isEmpty();
    }

    public void addGetsDataFrom(WrappedIndividual newGetsDataFrom) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                Vocabulary.OBJECT_PROPERTY_GETSDATAFROM,
                newGetsDataFrom);
    }

    public void removeGetsDataFrom(WrappedIndividual oldGetsDataFrom) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                Vocabulary.OBJECT_PROPERTY_GETSDATAFROM,
                oldGetsDataFrom);
    }


    /* ***************************************************
     * Object Property http://www.semanticweb.org/ontologies/2017/1/WeighingModuleOntology1#hasContainer
     */

    public Collection<? extends WrappedIndividual> getHasContainer() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                Vocabulary.OBJECT_PROPERTY_HASCONTAINER,
                WrappedIndividualImpl.class);
    }

    public boolean hasHasContainer() {
        return !getHasContainer().isEmpty();
    }

    public void addHasContainer(WrappedIndividual newHasContainer) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                Vocabulary.OBJECT_PROPERTY_HASCONTAINER,
                newHasContainer);
    }

    public void removeHasContainer(WrappedIndividual oldHasContainer) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                Vocabulary.OBJECT_PROPERTY_HASCONTAINER,
                oldHasContainer);
    }


    /* ***************************************************
     * Object Property http://www.semanticweb.org/ontologies/2017/1/WeighingModuleOntology1#isKeptOn
     */

    public Collection<? extends WrappedIndividual> getIsKeptOn() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                Vocabulary.OBJECT_PROPERTY_ISKEPTON,
                WrappedIndividualImpl.class);
    }

    public boolean hasIsKeptOn() {
        return !getIsKeptOn().isEmpty();
    }

    public void addIsKeptOn(WrappedIndividual newIsKeptOn) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                Vocabulary.OBJECT_PROPERTY_ISKEPTON,
                newIsKeptOn);
    }

    public void removeIsKeptOn(WrappedIndividual oldIsKeptOn) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                Vocabulary.OBJECT_PROPERTY_ISKEPTON,
                oldIsKeptOn);
    }


    /* ***************************************************
     * Object Property http://www.semanticweb.org/ontologies/2017/1/WeighingModuleOntology1#providesDataTo
     */

    public Collection<? extends WrappedIndividual> getProvidesDataTo() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                Vocabulary.OBJECT_PROPERTY_PROVIDESDATATO,
                WrappedIndividualImpl.class);
    }

    public boolean hasProvidesDataTo() {
        return !getProvidesDataTo().isEmpty();
    }

    public void addProvidesDataTo(WrappedIndividual newProvidesDataTo) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                Vocabulary.OBJECT_PROPERTY_PROVIDESDATATO,
                newProvidesDataTo);
    }

    public void removeProvidesDataTo(WrappedIndividual oldProvidesDataTo) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                Vocabulary.OBJECT_PROPERTY_PROVIDESDATATO,
                oldProvidesDataTo);
    }


    /* ***************************************************
     * Data Property http://www.semanticweb.org/ontologies/2017/1/WeighingModuleOntology1#Breadth
     */

    public Collection<? extends Object> getBreadth() {
        return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_BREADTH, Object.class);
    }

    public boolean hasBreadth() {
        return !getBreadth().isEmpty();
    }

    public void addBreadth(Object newBreadth) {
        getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_BREADTH, newBreadth);
    }

    public void removeBreadth(Object oldBreadth) {
        getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_BREADTH, oldBreadth);
    }


    /* ***************************************************
     * Data Property http://www.semanticweb.org/ontologies/2017/1/WeighingModuleOntology1#Height
     */

    public Collection<? extends Object> getHeight() {
        return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HEIGHT, Object.class);
    }

    public boolean hasHeight() {
        return !getHeight().isEmpty();
    }

    public void addHeight(Object newHeight) {
        getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HEIGHT, newHeight);
    }

    public void removeHeight(Object oldHeight) {
        getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_HEIGHT, oldHeight);
    }


    /* ***************************************************
     * Data Property http://www.semanticweb.org/ontologies/2017/1/WeighingModuleOntology1#Length
     */

    public Collection<? extends Object> getLength() {
        return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_LENGTH, Object.class);
    }

    public boolean hasLength() {
        return !getLength().isEmpty();
    }

    public void addLength(Object newLength) {
        getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_LENGTH, newLength);
    }

    public void removeLength(Object oldLength) {
        getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_LENGTH, oldLength);
    }


    /* ***************************************************
     * Data Property http://www.semanticweb.org/ontologies/2017/1/WeighingModuleOntology1#Total_number_of_parts
     */

    public Collection<? extends Object> getTotal_number_of_parts() {
        return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_TOTAL_NUMBER_OF_PARTS, Object.class);
    }

    public boolean hasTotal_number_of_parts() {
        return !getTotal_number_of_parts().isEmpty();
    }

    public void addTotal_number_of_parts(Object newTotal_number_of_parts) {
        getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_TOTAL_NUMBER_OF_PARTS, newTotal_number_of_parts);
    }

    public void removeTotal_number_of_parts(Object oldTotal_number_of_parts) {
        getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_TOTAL_NUMBER_OF_PARTS, oldTotal_number_of_parts);
    }


    /* ***************************************************
     * Data Property http://www.semanticweb.org/ontologies/2017/1/WeighingModuleOntology1#Weight_of_box
     */

    public Collection<? extends Object> getWeight_of_box() {
        return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_WEIGHT_OF_BOX, Object.class);
    }

    public boolean hasWeight_of_box() {
        return !getWeight_of_box().isEmpty();
    }

    public void addWeight_of_box(Object newWeight_of_box) {
        getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_WEIGHT_OF_BOX, newWeight_of_box);
    }

    public void removeWeight_of_box(Object oldWeight_of_box) {
        getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_WEIGHT_OF_BOX, oldWeight_of_box);
    }


    /* ***************************************************
     * Data Property http://www.semanticweb.org/ontologies/2017/1/WeighingModuleOntology1#x
     */

    public Collection<? extends Object> getX() {
        return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_X, Object.class);
    }

    public boolean hasX() {
        return !getX().isEmpty();
    }

    public void addX(Object newX) {
        getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_X, newX);
    }

    public void removeX(Object oldX) {
        getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_X, oldX);
    }


    /* ***************************************************
     * Data Property http://www.semanticweb.org/ontologies/2017/1/WeighingModuleOntology1#y
     */

    public Collection<? extends Object> getY() {
        return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_Y, Object.class);
    }

    public boolean hasY() {
        return !getY().isEmpty();
    }

    public void addY(Object newY) {
        getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_Y, newY);
    }

    public void removeY(Object oldY) {
        getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_Y, oldY);
    }


    /* ***************************************************
     * Data Property http://www.semanticweb.org/ontologies/2017/1/WeighingModuleOntology1#z
     */

    public Collection<? extends Object> getZ() {
        return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_Z, Object.class);
    }

    public boolean hasZ() {
        return !getZ().isEmpty();
    }

    public void addZ(Object newZ) {
        getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_Z, newZ);
    }

    public void removeZ(Object oldZ) {
        getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_Z, oldZ);
    }


}
