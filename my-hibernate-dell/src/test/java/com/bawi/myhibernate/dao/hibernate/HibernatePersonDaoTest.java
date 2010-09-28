package com.bawi.myhibernate.dao.hibernate;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.bawi.myhibernate.dao.PersonDao;
import com.bawi.myhibernate.domain.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-resources.xml",
		"classpath:infrastructure-context.xml", "classpath:datasources.xml",
		"classpath:dao-context.xml", "classpath:transaction-context.xml",
		"classpath:hibernate-annotated-classes-context.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class HibernatePersonDaoTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	private PersonDao personDao;

	@Test
	public void testSaveIntoDB() {
		Person p = new Person();
		p.setName("bawi");
		Person p2 = personDao.saveOrUpdate(p);
		assertEquals(p, p2);
	}

}
