package com.bawi.myhibernate.dao.hibernate;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.bawi.myhibernate.dao.CompanyDao;
import com.bawi.myhibernate.domain.Company;
import com.bawi.myhibernate.domain.Employee;

public class HibernateCompanyDao extends HibernateDaoSupport implements CompanyDao {

    // needed for transaction template
    PlatformTransactionManager transactionManager;

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public Company saveOrUpdateTransaction(final Company company) {
        TransactionTemplate template = new TransactionTemplate(transactionManager);
        template.execute(new TransactionCallback() {
            public Object doInTransaction(TransactionStatus status) {
                getHibernateTemplate().saveOrUpdate(company);
                sou();
                return null;
            }
        });
        return null;
    }

    @Transactional()
    public Company saveOrUpdate(final Company company) {
        // getHibernateTemplate().getSessionFactory().getCurrentSession();
        getHibernateTemplate().saveOrUpdate(company);
        // throw new RuntimeException("On purpose");
        return null;
    }

    public Company saveOrUpdateSession(final Company company) {

        Session session = getSession();
        // Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(company);
        // sou();
        // transaction.commit();
        session.close();
        return null;
    }

    private void sou() {
        Employee ee1 = new Employee("xxx");
        Employee ee2 = new Employee("yyy");
        Employee ee3 = new Employee("zzz");
        Company company2 = new Company("myCompany2", Arrays.asList(ee1, ee2, ee3));

        getHibernateTemplate().saveOrUpdate(company2);
        // Session session = getSession(false);
        // session.saveOrUpdate(company2);
    }

    public void delete(Company company) {
        getSession().delete(company);
    }

    public List<Company> getCompanies() {
        // Query query = getSession().createQuery("from Company com");
        Query query = getSession().createQuery("from Company com join fetch com.employees emp");
        return query.list();
    }

}
