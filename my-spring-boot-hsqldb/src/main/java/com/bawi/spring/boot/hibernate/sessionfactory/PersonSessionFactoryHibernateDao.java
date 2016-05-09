package com.bawi.spring.boot.hibernate.sessionfactory;


import com.bawi.spring.boot.entity.Person;
import org.springframework.stereotype.Repository;

@Repository
public class PersonSessionFactoryHibernateDao extends GenericSessionFactoryHibernateDao<Long, Person> {
}
