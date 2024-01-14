#!/bin/bash
java -cp .:java-cup-11b-runtime.jar parser
clang -c -o output.o output.ll
clang -o output output.o
#print compile done, running executable now
echo "Compile done, running executable now"
./output