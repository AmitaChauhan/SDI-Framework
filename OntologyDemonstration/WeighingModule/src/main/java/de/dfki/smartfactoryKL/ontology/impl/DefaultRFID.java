package de.dfki.smartfactoryKL.ontology.impl;

import de.dfki.smartfactoryKL.ontology.RFID;
import de.dfki.smartfactoryKL.ontology.Vocabulary;
import org.protege.owl.codegeneration.WrappedIndividual;
import org.protege.owl.codegeneration.impl.WrappedIndividualImpl;
import org.protege.owl.codegeneration.inference.CodeGenerationInference;
import org.semanticweb.owlapi.model.IRI;

import java.util.Collection;


/**
 * Generated by Protege (http://protege.stanford.edu).<br>
 * Source Class: DefaultRFID <br>
 * @version generated on Sat Mar 25 23:36:08 CET 2017 by Utkarsh
 */
public class DefaultRFID extends WrappedIndividualImpl implements RFID {

    public DefaultRFID(CodeGenerationInference inference, IRI iri) {
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
     * Data Property http://www.semanticweb.org/ontologies/2017/1/WeighingModuleOntology1#Inventory
     */

    public Collection<? extends Integer> getInventory() {
        return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_INVENTORY, Integer.class);
    }

    public boolean hasInventory() {
        return !getInventory().isEmpty();
    }

    public void addInventory(Integer newInventory) {
        getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_INVENTORY, newInventory);
    }

    public void removeInventory(Integer oldInventory) {
        getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_INVENTORY, oldInventory);
    }


    /* ***************************************************
     * Data Property http://www.semanticweb.org/ontologies/2017/1/WeighingModuleOntology1#Part_description
     */

    public Collection<? extends String> getPart_description() {
        return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_PART_DESCRIPTION, String.class);
    }

    public boolean hasPart_description() {
        return !getPart_description().isEmpty();
    }

    public void addPart_description(String newPart_description) {
        getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_PART_DESCRIPTION, newPart_description);
    }

    public void removePart_description(String oldPart_description) {
        getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_PART_DESCRIPTION, oldPart_description);
    }


    /* ***************************************************
     * Data Property http://www.semanticweb.org/ontologies/2017/1/WeighingModuleOntology1#Tolerance_of_weight
     */

    public Collection<? extends Object> getTolerance_of_weight() {
        return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_TOLERANCE_OF_WEIGHT, Object.class);
    }

    public boolean hasTolerance_of_weight() {
        return !getTolerance_of_weight().isEmpty();
    }

    public void addTolerance_of_weight(Object newTolerance_of_weight) {
        getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_TOLERANCE_OF_WEIGHT, newTolerance_of_weight);
    }

    public void removeTolerance_of_weight(Object oldTolerance_of_weight) {
        getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_TOLERANCE_OF_WEIGHT, oldTolerance_of_weight);
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
     * Data Property http://www.semanticweb.org/ontologies/2017/1/WeighingModuleOntology1#Weight_per_piece
     */

    public Collection<? extends Object> getWeight_per_piece() {
        return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_WEIGHT_PER_PIECE, Object.class);
    }

    public boolean hasWeight_per_piece() {
        return !getWeight_per_piece().isEmpty();
    }

    public void addWeight_per_piece(Object newWeight_per_piece) {
        getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_WEIGHT_PER_PIECE, newWeight_per_piece);
    }

    public void removeWeight_per_piece(Object oldWeight_per_piece) {
        getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_WEIGHT_PER_PIECE, oldWeight_per_piece);
    }


}
