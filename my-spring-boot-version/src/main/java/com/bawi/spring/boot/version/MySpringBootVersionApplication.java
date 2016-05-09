package com.bawi.spring.boot.version;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MySpringBootVersionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySpringBootVersionApplication.class, args);

        // version
   		// http://localhost:8080/build/git.properties (only git-commit-id-plugin)
        // or alternatively:
        // http://localhost:8080/build/version (buildnumber-maven-plugin and ApplicationVersionController)

        // changelog
		// http://localhost:8080/build/changelog.txt
		// http://localhost:8080/build/changelog.html
		// http://localhost:8080/build/changelog.json


	}
}
