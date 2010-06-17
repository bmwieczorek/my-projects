#!/bin/bash
echo "M2_HOME=$M2_HOME"

cd my-super-pom && "$M2_HOME"/bin/mvn clean install && cd -

for t in `ls -d my-*`; 
do 
    cd $t; 
    echo "Building $t ..."
    "$M2_HOME"/bin/mvn clean install eclipse:eclipse -DdownloadSources=true; 
    if [ $? -gt 0 ]; 
    then 
        exit 1; 
    fi; 
    cd -; 
done
