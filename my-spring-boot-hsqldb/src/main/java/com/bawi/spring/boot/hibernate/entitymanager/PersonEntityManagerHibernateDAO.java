package com.bawi.spring.boot.hibernate.entitymanager;

import com.bawi.spring.boot.entity.Person;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonEntityManagerHibernateDAO extends GenericEntityManagerHibernateDAO<Long, Person> {

    public List<Person> findByFirstName(String firstName) {
        Session currentSession = getCurrentSession();
        Criteria criteria = currentSession.createCriteria(Person.class);
        criteria.add(Restrictions.eq("firstName", firstName));
        @SuppressWarnings("unchecked")
        List<Person> persons = criteria.list();
        return persons;
    }

}
