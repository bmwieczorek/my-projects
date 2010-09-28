package com.bawi.myhibernate.dao.hibernate;

import static java.util.Arrays.asList;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bawi.myhibernate.dao.CompanyDao;
import com.bawi.myhibernate.dao.EmployeeDao;
import com.bawi.myhibernate.domain.Company;
import com.bawi.myhibernate.domain.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-resources.xml", "classpath:infrastructure-context.xml",
        "classpath:datasources.xml", "classpath:dao-context.xml", "classpath:transaction-context.xml",
        "classpath:hibernate-annotated-classes-context.xml" })
public class HibernatePersonDaoTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Test
    public void testSaveIntoDB() {
        Employee e1 = new Employee("bartek");
        Employee e2 = new Employee("kasia");
        Employee e3 = new Employee("ania");
        companyDao.saveOrUpdate(new Company("myCompany", asList(e1, e2, e3)));

        Employee ee1 = new Employee("ebartek");
        Employee ee2 = new Employee("ekasia");
        Employee ee3 = new Employee("eania");
        companyDao.saveOrUpdate(new Company("myCompany2", asList(ee1, ee2, ee3)));

        // System.err.println("++++++++++++");
        // for (Employee employee : employeeDao.getEmployees()) {
        // System.err.println(employee.getName());
        // }

        System.err.println("---------------");
        List<Company> companies = companyDao.getCompanies();
        for (Company company : companies) {
            System.err.println(company.getName());
            // System.out.println(company.getEmployees());
        }

    }
}
