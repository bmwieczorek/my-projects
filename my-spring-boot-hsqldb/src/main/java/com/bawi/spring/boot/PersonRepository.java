package com.bawi.spring.boot;

import org.springframework.data.repository.CrudRepository;


public interface PersonRepository extends CrudRepository<PersonEntity, Long> {
}
