package com.bawi.spring.boot;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "person")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    private int age;

    private String place;

    protected PersonEntity() {
        // entity default constructor needed for ORM
    }

    PersonEntity(String firstName, String lastName, int age, String place) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.place = place;
    }

    long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "PersonEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", place='" + place + '\'' +
                '}';
    }
}
