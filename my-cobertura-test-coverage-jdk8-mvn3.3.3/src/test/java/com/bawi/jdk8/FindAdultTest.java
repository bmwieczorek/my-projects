package com.bawi.jdk8;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class FindAdultTest {

    @Test
    public void shouldFindAdult() {
        // given
        List<Person> persons = new ArrayList<>();
        persons.add(new Person(10));
        persons.add(new Person(15));
        persons.add(new Person(20));
        persons.add(new Person(25));
        persons.add(new Person(30));
        AdultFinder adultFinder = new AdultFinder();
        

        // when
        List<Person> findAdults = adultFinder.findAdults(persons);

        // then
        Assert.assertEquals(3, findAdults.size());
    }
}
