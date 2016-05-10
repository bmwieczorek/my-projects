package com.bawi.spring.boot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "person")
public class Person extends BaseEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    private int age;

    private String place;

    protected Person() {
        // default constructor needed for ORM
    }

    public Person(String firstName, String lastName, int age, String place) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.place = place;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", place='" + place + '\'' +
                '}';
    }
}
