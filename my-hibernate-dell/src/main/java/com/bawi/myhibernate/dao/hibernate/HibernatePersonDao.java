package com.bawi.myhibernate.dao.hibernate;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bawi.myhibernate.dao.PersonDao;
import com.bawi.myhibernate.domain.Person;

public class HibernatePersonDao extends HibernateDaoSupport implements PersonDao {

    @Override
    public Person saveOrUpdate(Person person) {
        getSession().saveOrUpdate(person);
        return person;
    }

    @Override
    public void delete(Person person) {
        getSession().delete(person);
    }

}
