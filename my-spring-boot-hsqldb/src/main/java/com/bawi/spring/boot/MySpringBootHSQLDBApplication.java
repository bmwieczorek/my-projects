package com.bawi.spring.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class MySpringBootHSQLDBApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySpringBootHSQLDBApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MySpringBootHSQLDBApplication.class, args);
    }

    @Autowired
    PersonRepository personRepository;

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            Stream.of(new PersonEntity("John", "Smith", 21, "US"), new PersonEntity("Marry", "Jones", 18, "UK"))
                .forEach(personEntity -> {
                    personRepository.save(personEntity);
                    LOGGER.info("Person with ID '{}' saved successfully", personEntity.getId());
                });

            personRepository.findAll()
                    .forEach(personEntity -> LOGGER.info("Retrieved: {}", personEntity));
        };
    }
}
