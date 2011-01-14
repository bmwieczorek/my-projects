package com.bawi.myhibernate.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bawi.myhibernate.dao.EmployeeDao;
import com.bawi.myhibernate.domain.Employee;

public class HibernateEmployeeDao extends HibernateDaoSupport implements EmployeeDao {

    @Override
    @SuppressWarnings("unchecked")
    public List<Employee> getEmployees() {
        // Query query =
        // getSession().createQuery("from Employee emp join fetch emp.company com");
        Query query = getSession().createQuery("from Employee emp");
        return query.list();
    }

}
