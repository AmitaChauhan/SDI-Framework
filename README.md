# SDI-Framework

This repository contains implementaion code for demonstration of the concept presented in the manuscript "Towards Modular and Adaptive Assistance Systems for Manual Assembly: A Semantic Description and Interoperability Framework". 

The implementation has a central system (written in Python) and a CPS module, i.e. an inventory module, written in Java.

Following comands can be used to run the implementation:

### Central System
- Open command window.
- Navigate to the folder.
- Install `zeromq`, if required, as ```pip install zmq```.
- Run the Python file ```python CentralSystem.py ```

### CPS Module (Inventory Module -- OntologyDemonstration)
- Open the Java project using Eclipse, IntelliJ or a similar IDE.
- Change the path of the Ontology in `Main.java` to point to `SimplifiedOntology.owl` in the root folder.
- Run the project using `Main.main` as the entry point.

Note: Central system is the class ```Brain``` and CPS module is the class ```WeighingModule``` in the code. HardwarePrograms folder contains code for collecting data from sensors and RFID readers.
