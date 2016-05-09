package com.bawi.spring.boot;

import com.bawi.spring.boot.entity.Person;
import com.bawi.spring.boot.hibernate.sessionfactory.PersonSessionFactoryHibernateDao;
import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication(scanBasePackages = "com.bawi.spring.boot.hibernate.sessionfactory")
public class MySpringBootHSQLDBSessionFactoryPropertyApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySpringBootHSQLDBSessionFactoryPropertyApplication.class);

    public static void main(String[] args) {
        // needed only by PersonSessionFactoryHibernateDao
        System.setProperty("spring.jpa.properties.hibernate.current_session_context_class", "org.springframework.orm.hibernate4.SpringSessionContext");
        SpringApplication.run(MySpringBootHSQLDBSessionFactoryPropertyApplication.class, args);
    }

    @Autowired
    PersonSessionFactoryHibernateDao personSessionFactoryHibernateDao;

    @Bean // needed only by PersonSessionFactoryHibernateDao
    public SessionFactory sessionFactory(HibernateEntityManagerFactory hibernateEntityManagerFactory) {
        return hibernateEntityManagerFactory.getSessionFactory();
    }


    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            // Hibernate session from session factory

            Stream.of(new Person("Jan", "Kowalski", 25, "PL"), new Person("Sergei", "Ivanov", 27, "RU"))
                    .forEach(person -> {
                        personSessionFactoryHibernateDao.save(person);
                        LOGGER.info("Hibernate {} saved successfully", person);
                    });

            personSessionFactoryHibernateDao.findAllByCriteria().stream()
                    .forEach(person -> LOGGER.info("Hibernate criteria retrieved: {} with id: {}", person, person.getId()));

        };
    }
}
