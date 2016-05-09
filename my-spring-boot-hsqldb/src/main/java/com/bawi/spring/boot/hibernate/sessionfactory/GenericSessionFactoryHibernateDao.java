package com.bawi.spring.boot.hibernate.sessionfactory;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class GenericSessionFactoryHibernateDao<PK extends Serializable, T> {

    private final Class<T> persistentClass;

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public GenericSessionFactoryHibernateDao() {
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
        this.persistentClass = (Class<T>) actualTypeArguments[1];
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }


    @SuppressWarnings("unchecked")
    public T getByKey(PK key) {
        return (T) getCurrentSession().get(persistentClass, key);
    }

    @Transactional
    public void persist(T entity) {
        getCurrentSession().persist(entity);
    }

    @Transactional
    public void save(T entity) {
        getCurrentSession().save(entity);
    }

    protected Criteria createEntityCriteria() {
        return getCurrentSession().createCriteria(persistentClass);
    }

    @Transactional
    public List<T> findAllByCriteria() {
        Criteria criteria = createEntityCriteria();
        @SuppressWarnings("unchecked")
        List<T> elements = (List<T>) criteria.list();
        return elements;
    }

    public List<T> findAll2() {
        Session currentSession = getCurrentSession();
        Query query = currentSession.createQuery("from " + persistentClass.getName());
        @SuppressWarnings("unchecked")
        List<T> elements = (List<T>) query.list();
        return elements;
    }

}