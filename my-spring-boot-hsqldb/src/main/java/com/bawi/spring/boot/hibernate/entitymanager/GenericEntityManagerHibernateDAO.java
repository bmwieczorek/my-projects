package com.bawi.spring.boot.hibernate.entitymanager;

import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

public abstract class GenericEntityManagerHibernateDAO<PK extends Serializable, T> extends GenericHibernateDAO<PK, T> {

    @PersistenceContext
    protected EntityManager em;

    @Override
    protected Session getCurrentSession() {
        em = em.getEntityManagerFactory().createEntityManager();
        return em.unwrap(Session.class);
    }

    protected EntityManager getEntityManager() {
        return em;
    }
}
