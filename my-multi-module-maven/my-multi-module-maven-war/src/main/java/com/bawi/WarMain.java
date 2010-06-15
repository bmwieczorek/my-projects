package com.bawi;

import java.io.IOException;

public class WarMain {

    // run via: export M2_REPO=~/dev/environment/apache-maven-2.0.9/localRepo; java -classpath
    // $M2_REPO/com/bawi/multi-module-maven-war/0.0.1-SNAPSHOT/multi-module-maven-war-0.0.1-SNAPSHOT.jar:$M2_REPO/com/bawi/multi-module-maven-core/0.0.1-SNAPSHOT/multi-module-maven-core-0.0.1-SNAPSHOT.jar
    // com.bawi.maven.ServiceRunner

    public static void main(String[] args) throws IOException {
        System.out.println("*** WAR MAIN ***");

        new CoreMainPropertiesFileReader();

        // cannot use classes from test in main
        // new CoreTestPropertiesFileReader();

        new WarMainPropertiesFileReader();

        // cannot use classes from test in main
        // new WarTestPropertiesFileReader();
    }
}
