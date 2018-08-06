#!/bin/sh
continue=true
currrentidstring=""
echo "tap NFC card to begin or ctrl-C to exit"
while $continue
do
	idstring=$(nfc-list | grep "UID (NFCID1):")
	if [ "$idstring" != "" ] 
	 then
	  if [ "$idstring" != "$currentidstring" ]
           then 
	    currentidstring=$idstring
	    id=$(echo $idstring| cut -d':' -f2)
	    echo "tag found with the following ID: $id"
	    echo "What is the product name for this tag?"
	    read productname
	    echo "What is the weight per piece in grams for this tag?"
	    read productweight
	    echo "Trying to link tag $id to the name $productname with a weight per piece of $productweight grams..."
	    python addNewTag.py "$id" "$productname" "$productweight"
	    echo "Do you want to add another? [y/n]"
	    read continueAnswer 
	    if [ "$continueAnswer" = "n" ]
		then
		  continue=false
	    fi
	  fi
	fi
sleep 0.5
done
