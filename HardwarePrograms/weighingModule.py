import serial
import time
import zmq
import os
import pprint
import sys

deviceDict = {}

LOW_INVENTORY_THRESHOLD = 2

# define the initialiyer tag ID's here
leftID = '1266991503068545'
middleID = '1246100782140801'
rightID = '1245001270513025'

nfcId1Received = False
nfcId2Received = False
nfcId3Received = False

def partChange(oldTotalWeight, newTotalWeight, weightPerPiece):
    partDiff = round ((newTotalWeight - oldTotalWeight) / (0.001 * float (weightPerPiece)))
    return partDiff

def getNumParts(newTotalWeight, weightPerPiece):
    numParts = round (newTotalWeight / (0.001 * float (weightPerPiece)))
    return numParts

# with faulty hardware message might not be clean
# cleans messages from the weighing module

def cleanWeighingSensorMsg(bytes_raw):
    start, end = 0xf2, 0xf3
    res = b''
    started = False

    for b in bytes_raw:
        if ord(b) == start and not started:
            started = True
    
        if started:
            res += b

        if started and ord(b) == end:
            break
    
    if len(res) == 0:
        return None
    else:
        return res

context = zmq.Context()
socket = context.socket(zmq.REP)
socket.bind("tcp://*:5555")
socket.RCVTIMEO = 2000

while True:
   # zmQ Communication
    # nfcid=1326748060108928;name=iuzt;weight=987;connstring=acr122_usb:001:037,
    # nfcid=0000000000;name=None;weight=9999;connstring=acr122_usb:001:036,

    try:
        msgZMQ = socket.recv()
        socket.send_string("ACK")
        print ("Received: ", msgZMQ)

        if len(msgZMQ) > 0:
            if msgZMQ[-1:] == ",":
                msgZMQ = msgZMQ[:-1]

        for boxIndex, individual_msg in enumerate(msgZMQ.split(',')):
                if len(individual_msg) == 0:
                    #print('Empty individual_msg')
                    continue

                # print boxIndex, individual_msg

                msg_dict = {}
                for key_value in individual_msg.split(';'):
                    key,value = key_value.split('=')
                    key = key.strip()
                    #print key, value
                    msg_dict[key] = value


                nfcId = msg_dict['nfcid']
                if nfcId != "0000000000":
                    productName = msg_dict['name']
                    connString = msg_dict['connstring']
                    productWeight = msg_dict['weight']
                    #print "Now start looking for the message.."

                    if nfcId == leftID:
                        deviceDict[connString] = "left"
                    elif nfcId == rightID:
                        deviceDict[connString] = "right"
                    elif nfcId == middleID:
                        deviceDict[connString] = "middle"
                    elif connString in deviceDict:
                        if deviceDict[connString] == "left":
                            nfcId1Received = True
                            nfcId1 = nfcId
                            connStr1 = connString
                            productName1 = productName
                            productWeight1 = productWeight
                            oldTotalWeight1 = 0
                            boxPosition1 = 'left'

                        if deviceDict[connString] == "middle":
                            nfcId2Received = True
                            nfcId2 = nfcId
                            connStr2 = connString
                            productName2 = productName
                            productWeight2 = productWeight
                            oldTotalWeight2 = 0
                            boxPosition2 = 'middle'

                        if deviceDict[connString] == "right":
                            nfcId3Received = True
                            nfcId3 = nfcId
                            connStr3 = connString
                            productName3 = productName
                            productWeight3 = productWeight
                            oldTotalWeight3 = 0
                            boxPosition3 = 'right'

    except zmq.ZMQError as e:
        pass
        #print "passed"


    msgReceived = False
    # Sending message to weighing scale
    msg = bytearray()

    msg.append(0xf2)
    length_of_message = 0x07

    msg.append(length_of_message)
    msg.append(ord('T'))
    zero = 0x30
    one = 0x31
    msg.append (zero)
    msg.append (zero)
    msg.append (zero)
    msg.append (one)

    checksum = 0
    for x in msg[1:]:
       checksum ^= x

    msg.append(checksum)

    msg.append(0xf3)

    # Receiving message from weighing scale
    ser = serial.Serial(
                   port='/dev/ttyAMA0',
                   baudrate = 9600,
                   parity=serial.PARITY_NONE,
                   stopbits=serial.STOPBITS_ONE,
                   bytesize=serial.EIGHTBITS,

               )

    ser.flushInput()
    ser.flushOutput()

    ser.write(msg)

    ser.flushOutput()
    ser.flushInput()

    time.sleep(2)

    bytesToRead = ser.inWaiting()
    r = ser.read(bytesToRead)
    ser.close()

    #for y in r:
     #   z = ord(y)
      #  print "%02x" %z

    cleanMsg = cleanWeighingSensorMsg(r)
    cleanMsgReceived = False

    if cleanMsg is not None:
        try:
            totalWeightofBox1 = float(cleanMsg[9:13])
            totalWeightofBox2 = float(cleanMsg[19:23])
            totalWeightofBox3 = float(cleanMsg[29:33])
            print totalWeightofBox1
            print totalWeightofBox2
            print totalWeightofBox3
            cleanMsgReceived = True
        except Exception:
            # could not parse the cleanMsg
            pass

    if nfcId1Received and cleanMsgReceived:
        partDiff = partChange(oldTotalWeight1, totalWeightofBox1, productWeight1)
        oldTotalWeight1 = totalWeightofBox1
        print "<%s,%s,%s>" % (productName1, partDiff, boxPosition1)
        # Call msg-builder to report partDiff
        command = "mono messageWeighingModule.exe part %s %s %s" % (productName1, partDiff, boxPosition1)
        os.system(command)
        inventoryCheck = getNumParts(totalWeightofBox1, productWeight1)
        if inventoryCheck <= LOW_INVENTORY_THRESHOLD:
            print "<%s,%s>" % (productName1, boxPosition1)
            # Call msg-builder to report low filling
            command = "mono messageWeighingModule.exe inventory %s %s" % (productName1, boxPosition1)
            os.system(command)

    if nfcId2Received and cleanMsgReceived:
        partDiff = partChange(oldTotalWeight2, totalWeightofBox2, productWeight2)
        oldTotalWeight2 = totalWeightofBox2
        print "<%s,%s,%s>" % (productName2, partDiff,boxPosition2 )
        # Call msg-builder to report partDiff
        command = "mono messageWeighingModule.exe part %s %s %s" % (productName2, partDiff, boxPosition2)
        os.system(command)
        if getNumParts(totalWeightofBox2, productWeight2) <= LOW_INVENTORY_THRESHOLD:
            print "Inventory low for %s, %s" % (productName2, boxPosition2)
            # Call msg-builder to report low filling
            command = "mono messageWeighingModule.exe inventory %s %s" % (productName2, boxPosition2)
            os.system(command)

    if nfcId3Received and cleanMsgReceived:
        partDiff = partChange(oldTotalWeight3, totalWeightofBox3, productWeight3)
        oldTotalWeight3 = totalWeightofBox3
        print "<%s,%s,%s>" % (productName3, partDiff,boxPosition3)
        # Call msg-builder to report partDiff
        command = "mono messageWeighingModule.exe part %s %s %s" % (productName3, partDiff, boxPosition3)
        os.system(command)
        if getNumParts(totalWeightofBox3, productWeight3) <= LOW_INVENTORY_THRESHOLD:
            print "Inventory low for %s, %s" % (productName3, boxPosition3)
            # Call msg-builder to report low filling
            command = "mono messageWeighingModule.exe inventory %s %s" % (productName3, boxPosition3)
            os.system(command)
