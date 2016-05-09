package com.bawi.spring.boot;

import com.bawi.spring.boot.entity.Person;
import com.bawi.spring.boot.hibernate.entitymanager.PersonEntityManagerHibernateDAO;
import com.bawi.spring.boot.jpa.PersonJpaDao;
import com.bawi.spring.boot.springdata.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class MySpringBootHSQLDBSpringDataAndJPAAndHibernateSessionViaEntityManagerApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySpringBootHSQLDBSpringDataAndJPAAndHibernateSessionViaEntityManagerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MySpringBootHSQLDBSpringDataAndJPAAndHibernateSessionViaEntityManagerApplication.class, args);
    }

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonJpaDao personJpaDao;

    @Autowired
    PersonEntityManagerHibernateDAO personEntityManagerHibernateDAO;

//    @Autowired
//    PersonSessionFactoryHibernateDao personSessionFactoryHibernateDao;
//
//    @Bean // needed only by PersonSessionFactoryHibernateDao
//    public SessionFactory sessionFactory(HibernateEntityManagerFactory hibernateEntityManagerFactory){
//        return hibernateEntityManagerFactory.getSessionFactory();
//    }


    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {

            // SpringData
            Stream.of(new Person("John", "Smith", 21, "US"), new Person("Marry", "Jones", 18, "UK"))
                    .forEach(person -> {
                        person = personRepository.save(person);
                        LOGGER.info("SpringData {} with ID '{}' saved successfully", person, person.getId());
                    });

            personRepository.findAll()
                    .forEach(person -> LOGGER.info("SpringData retrieved: {}", person));

            personRepository.findByFirstName("John").stream()
                    .forEach(person -> LOGGER.info("SpringData retrieved firstName John: {} with id: {}", person, person.getId()));


            // JPA

            Stream.of(new Person("Jan", "Kowalski", 25, "PL"), new Person("Sergei", "Ivanov", 27, "RU"))
                    .forEach(person -> {
                        personJpaDao.save(person);
                        LOGGER.info("JPA  {} saved successfully", person);
                    });

            personJpaDao.findAll().stream()
                    .forEach(person -> LOGGER.info("JPA retrieved: {} with id: {}", person, person.getId()));

            personJpaDao.findAll2().stream()
                    .forEach(person -> LOGGER.info("JPA retrieved: {} with id: {}", person, person.getId()));

            // Hibernate session from entity manager

            Stream.of(new Person("Jan", "Kowalski", 25, "PL"), new Person("Sergei", "Ivanov", 27, "RU"))
                    .forEach(person -> {
                        personEntityManagerHibernateDAO.save(person);
                        LOGGER.info("Hibernate (EM) {} saved successfully", person);
                    });

            personEntityManagerHibernateDAO.findAllByCriteria().stream()
                    .forEach(person -> LOGGER.info("Hibernate (EM) criteria retrieved: {} with id: {}", person, person.getId()));

            personEntityManagerHibernateDAO.findAll2().stream()
                    .forEach(person -> LOGGER.info("Hibernate (EM) query retrieved: {} with id: {}", person, person.getId()));

            personEntityManagerHibernateDAO.findByFirstName("John").stream()
                    .forEach(person -> LOGGER.info("Hibernate (EM) query retrieved firstName John: {} with id: {}", person, person.getId()));


            // Hibernate session from session factory

//            Stream.of(new Person("Jan", "Kowalski", 25, "PL"), new Person("Sergei", "Ivanov", 27, "RU"))
//                    .forEach(person -> {
//                        personSessionFactoryHibernateDao.save(person);
//                        LOGGER.info("JPA  {} saved successfully", person);
//                    });
//
//            personSessionFactoryHibernateDao.findAllByCriteria().stream()
//                    .forEach(person -> LOGGER.info("Hibernate criteria retrieved: {} with id: {}", person, person.getId()));
//
//            personSessionFactoryHibernateDao.findAll2().stream()
//                    .forEach(person -> LOGGER.info("Hibernate query retrieved: {} with id: {}", person, person.getId()));
//
//            personSessionFactoryHibernateDao.findByFirstName("John").stream()
//                    .forEach(person -> LOGGER.info("Hibernate query retrieved firstName John: {} with id: {}", person, person.getId()));

        };
    }
}
