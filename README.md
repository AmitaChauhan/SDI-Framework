# SDI-Framework

This repository contains ontologies for the manuscript "A Framework for Semantic Description and Interoperability across Cyber-Physical Systems". 

The ontologies correspond to the central system, mid-level, the assistance system, and two CPS modules, viz. a weighing modules and an eye-tracking module.

Following are the sample queries, each for the two ways of sharing ontologies, for the example discussed in the manuscript.

### Decentralized Organization of Ontologies
```sql
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX owl: <http://www.w3.org/2002/07/owl#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
PREFIX WM: <http://www.semanticweb.org/amita/WeighingModule#>      
-- namespace WM for importing weighing module ontology

SELECT ?Container ?X ?Y ?Z
	WHERE { ?Container WM:hasX ?X .
	        ?Container WM:hasY ?Y .
	        ?Container WM:hasZ ?Z } 
```
          

### Centralized Organization of Ontologies
```sql
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX owl: <http://www.w3.org/2002/07/owl#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
PREFIX ML: <http://www.semanticweb.org/amita/MidLevelOntology#>     
-- namespace ML for importing the mid-level ontology 

SELECT ?Container ?X ?Y ?Z
	WHERE { ?Container ML:hasX ?X .
	        ?Container ML:hasY ?Y .
	        ?Container ML:hasZ ?Z }
```
