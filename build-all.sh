#!/bin/bash

for t in `ls -d my-*`; 
do 
    cd $t; 
    mvn clean install eclipse:eclipse -DdownloadSources=true; 
    if [ $? -gt 0 ]; 
    then 
        exit 1; 
    fi; 
    cd -; 
done
