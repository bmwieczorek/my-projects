package com.bawi.devoxx.demo.b.sorting.byAge.and.byName;

import static java.util.Arrays.asList;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DeclarativeSorting {

    public static class Person {

        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "Person [name=" + name + ", age=" + age + "]";
        }

    }

    public static void main(String[] args) {
        List<Person> people = asList(new Person("Julie", 12), new Person("Bob", 12), new Person("Tony", 2),
                new Person("Adam", 6));

        System.out.println(people.stream()
                // .sorted(Comparator.comparing(person -> person.getAge()))
                // .sorted(Comparator.comparing(Person::getName))
                // .sorted(Comparator.comparing(Person::getName))
                .sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getName))
                .collect(Collectors.toList()));
    }
}
