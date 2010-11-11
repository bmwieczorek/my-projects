package com.bawi.myhibernate.dao.hibernate;

import static java.util.Arrays.asList;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bawi.myhibernate.dao.CompanyDao;
import com.bawi.myhibernate.dao.EmployeeDao;
import com.bawi.myhibernate.domain.Company;
import com.bawi.myhibernate.domain.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-resources.xml", "classpath:infrastructure-context.xml",
        "classpath:datasources.xml", "classpath:dao-context.xml", "classpath:transaction-context.xml",
        "classpath:hibernate-annotated-classes-context.xml" })
public class HibernatePersonDaoTest {

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Test
    public void testSaveIntoDB() {
        Employee a = new Employee("aaa");
        Employee b = new Employee("bbb");
        Employee c = new Employee("ccc");
        companyDao.saveOrUpdate(new Company("K", asList(a, b, c)));

        Employee x = new Employee("xxx");
        Employee y = new Employee("yyy");
        Employee z = new Employee("zzz");
        companyDao.saveOrUpdate(new Company("L", asList(x, y, z)));

        // System.err.println("++++++++++++");
        // for (Employee employee : employeeDao.getEmployees()) {
        // System.err.println(employee.getName());
        // }

        System.err.println("---------------");
        List<Company> companies = companyDao.getCompanies();
        for (Company company : companies) {
            System.err.println(company.getName());
            System.out.println(company.getEmployees());
        }

    }
}
