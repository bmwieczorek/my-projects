package com.bawi;

import java.io.IOException;

import org.junit.Test;

public class WarTest {

    // run via: export M2_REPO=~/dev/environment/apache-maven-2.0.9/localRepo;
    // java -classpath
    // $M2_REPO/com/bawi/multi-module-maven-war/0.0.1-SNAPSHOT/multi-module-maven-war-0.0.1-SNAPSHOT.jar
    // :$M2_REPO/com/bawi/multi-module-maven-core/0.0.1-SNAPSHOT/multi-module-maven-core-0.0.1-SNAPSHOT.jar
    // com.bawi.maven.ServiceRunner

    @Test
    public void shouldPrint() throws IOException {
        main(null);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("*** WAR TEST ***");
        System.out.println(CoreMainPropertiesFileReader.readPropertiesFromResource("my-core-test.properties"));

        // need to copy properties via maven dependency:copy
        System.out.println(CoreMainPropertiesFileReader
                .readPropertiesFromFile("target/dependency/my-multi-module-maven-core/"
                        + "com/bawi/my-com-bawi-core-test.properties"));

        CoreTestPropertiesFileReader.readAndPrintProperties();
        WarMainPropertiesFileReader.readAndPrintProperties();
        WarTestPropertiesFileReader.readAndPrintProperties();
    }
}
