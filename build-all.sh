#!/bin/bash
find -maxdepth 1 -type d -name my-\* -exec bash -c "(cd {} &&  mvn clean install eclipse:clean eclipse:eclipse -DdownloadSources=true)"  \;

