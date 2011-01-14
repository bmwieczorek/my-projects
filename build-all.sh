#!/bin/bash
echo "M2_HOME=$M2_HOME"

for t in `ls -d my-*`; 
do 
    cd $t; 
    echo "Building $t ..."
    "$M2_HOME"/bin/mvn eclipse:eclipse -DdownloadSources=true -DdownloadJavadocs=true; 
    if [ $? -gt 0 ]; 
    then 
        exit 1; 
    fi; 
    cd -; 
done
