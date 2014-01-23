#!/bin/bash

if cat bin\/classes\/MainClass.class>/dev/null
then 
	java -cp bin classes/MainClass
else
	java -jar motus.jar
fi
