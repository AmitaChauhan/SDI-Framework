import time
import zmq
import sys
import os
import pprint
from pymongo import MongoClient

# setup mongo connection. Expecting a mongo server on localhost:27017 (default options)
print "connecting to mongo"
client = MongoClient()
db = client.storageBins
collection = db.rfid

# setup zero MQ
print "setting up mongodb"
context = zmq.Context()
socket = context.socket(zmq.REQ)
socket.connect("tcp://127.0.0.1:5555")  # connect to localhost
#socket.connect("tcp://192.168.11.224:5555")  # Amita's server

# read command-line argument
pprint.pprint(sys.argv)
inputstrings = sys.argv[1].replace(" ", "")
inputstrings = inputstrings.split(",")
msg = ""

# loop through the inputstrings
for index in range(len(inputstrings)-1):
    print inputstrings[index] 
    nfcid = None
    connstring = None
    parameters = inputstrings[index].split(";")
    pprint.pprint(parameters)
    for i in range(len(parameters)):
	part = parameters[i].split("=")
	if part[0] == "nfcid":
	    nfcid = part[1]
	    if nfcid != "0000000000":
                nfcid = int(nfcid, 16)
	if part[0] == "connstring":
	    connstring = part[1];
    if nfcid is None:
        print "Could not find NFCID parameter"
	exit()
    if connstring is None:
	print "Warning: could not find connstring parameter"
	connstring = ""

    if nfcid == "0000000000":
	msgPart = "nfcid=%s;name=None;weight=9999;connstring=%s," % (nfcid, connstring)
	msg += msgPart
    else:
        # find the related mongo collection
        print "Searching in database"
        print "nfcid is: " + str(nfcid)
        storageBinCursor = collection.find({"nfcid": nfcid})
        print "Found %d database entries." % storageBinCursor.count()
        if storageBinCursor.count() != 0:
            doc = storageBinCursor[0]
            print "The database entry looks like: "
            pprint.pprint(doc)
            msgPart = "nfcid=%s;name=%s;weight=%s;connstring=%s," % (nfcid, doc['productName'], doc['productWeight'], connstring)
            msg += msgPart
        else:
	    print "ID not recognized."



# send message over zmq
print msg
socket.send_string(msg)
print "The message was sent. Waiting for confirmation"
reply = socket.recv()
print "Confirmed" if reply=="ACK" else "Something went wrong"

# Send message to JAVA server
#    command = "mono message.exe %s %s %s" % (doc['nfcid'], doc['productName'], doc['productWeight'])
#    print "executing the following command: "
#    print command
#    os.system(command)
   

