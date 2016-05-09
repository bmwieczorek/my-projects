package com.bawi.spring.boot;

import com.bawi.spring.boot.entity.Person;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;

@SpringBootApplication
public class MySpringBootHSQLDBWithAnnotatedSessionFactoryApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySpringBootHSQLDBWithAnnotatedSessionFactoryApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MySpringBootHSQLDBWithAnnotatedSessionFactoryApplication.class, args);
    }

    @Autowired
    PersonDao personDao;

    @Repository
    static class PersonDao {

        @Autowired
        SessionFactory sessionFactory;

        @Transactional
        public void save(Person person) {
            sessionFactory.getCurrentSession().save(person);
        }

        @Transactional
        public List<Person> findAll() {
            Session currentSession = sessionFactory.getCurrentSession();
            Criteria criteria = currentSession.createCriteria(Person.class);
            @SuppressWarnings("unchecked")
            List<Person> list = criteria.list();
            return list;
        }
    }

    @Bean
    PersonDao personDao() {
        return new PersonDao();
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            Stream.of(new Person("John", "Smith", 21, "US"), new Person("Marry", "Jones", 18, "UK"))
                    .forEach(person -> {
                        personDao.save(person);
                        LOGGER.info("SpringData {}  saved successfully", person);
                    });

            personDao
                    .findAll()
                    .stream()
                    .forEach(person -> LOGGER.info("Hibernate (SF) query retrieved: {} with id: {}", person, person.getId()));

        };
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.bawi.spring.boot.entity");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
        dataSource.setUrl("jdbc:hsqldb:mem:.");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.format_sql", true);
        return properties;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(s);
        return txManager;
    }

}
