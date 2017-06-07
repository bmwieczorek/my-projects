package com.bawi.spring.boot.entity;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass // annotation needed for base class
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    long getId() {
        return id;
    }

    @Override
    public String toString() {
        return  "id=" + id;
    }
}
