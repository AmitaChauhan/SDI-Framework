import time
import sys
import pprint
from pymongo import MongoClient

# setup mongo connection. Expecting a mongo server on localhost:27017 (default options)
print "connecting to mongo"
client = MongoClient()
db = client.storageBins
collection = db.rfid

# read command-line argument
nfcidstring= sys.argv[1]
nfcid = int(nfcidstring.replace(" ", ""), 16)
name = sys.argv[2]
weight = sys.argv[3]

# Insert in database
storageBin = collection.find({"nfcid": nfcid})
print storageBin
post = {"nfcid": nfcid, "productName": name, "productWeight": weight}
if storageBin.count() == 1:
    print "This ID tag is already linked to the following product:"
    print storageBin
    pprint.pprint(storageBin)
    overWrite = raw_input("do you want to overwrite it? [y/n]")
    if overWrite == "y" or overWrite == "Y":
    	print "Replacing existing entry with the following: nfcid="+str(nfcid)+", productName="+str(name)+", productWeight="+str(weight)
    	collection.update({"nfcid": nfcid}, post, upsert=False, multi=False)     
    
else:
    print "The following database entry will be added: nfcid="+str(nfcid)+", productName="+str(name)+", productWeight="+str(weight) 
    collection.insert(post)
    print "Done!"  
