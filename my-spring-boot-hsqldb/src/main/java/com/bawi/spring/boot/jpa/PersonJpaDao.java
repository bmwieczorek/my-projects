package com.bawi.spring.boot.jpa;


import com.bawi.spring.boot.entity.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class PersonJpaDao extends GenericJpaDao<Long, Person> {

    public List<Person> findAll2() {
        Query query = em.createQuery("from Person");
        @SuppressWarnings("unchecked")
        List<Person> resultList = query.getResultList();
        return resultList;
    }


}
