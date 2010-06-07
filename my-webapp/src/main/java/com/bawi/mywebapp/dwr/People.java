package com.bawi.mywebapp.dwr;

import java.util.HashSet;
import java.util.Set;

import com.bawi.mywebapp.domain.Person;

public class People {

    private Set<Person> people;

    public Set<Person> getPeople() {
        people = new HashSet<Person>();
        Person p;
        p = new Person();
        p.setAge(10);
        p.setName("Ania");
        people.add(p);
        p = new Person();
        p.setAge(11);
        p.setName("Kasia");
        people.add(p);
        return people;
    }

    public void setPeople(Set<Person> people) {
        this.people = people;
    }
}
