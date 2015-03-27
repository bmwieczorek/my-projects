#!/bin/bash
echo "M2_HOME=$M2_HOME"

for t in `ls -d my-* | grep -v java-8 | grep -v eclipse`; 
do 
    cd $t; 
    echo "Building $t ..."
    "$M2_HOME"/bin/mvn clean install #eclipse:eclipse -DdownloadSources=true -DdownloadJavadocs=true -DskipTests;    
    if [ $? -gt 0 ]; 
    then 
        exit 1; 
    fi; 
    cd -; 
done

