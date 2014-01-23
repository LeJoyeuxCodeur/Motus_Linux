#!/bin/bash

mkdir bin 2>/dev/null
mkdir bin/classes 2>/dev/null
javac -d bin src/classes/*.java
