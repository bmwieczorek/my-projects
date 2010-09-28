package com.bawi.myhibernate.dao;

import com.bawi.myhibernate.domain.Person;

public interface PersonDao {

	Person saveOrUpdate(Person person);
	
	void delete(Person person);
	
}
