package com.bawi.jdk8;

import java.util.List;
import java.util.stream.Collectors;

public class AdultFinder {

    public List<Person> findAdults(List<Person> persons) {
        return persons
            .stream()
            .filter(p -> p.getAge() > 18)
            .collect(Collectors.toList());
    }
}
