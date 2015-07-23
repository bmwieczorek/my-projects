package com.bawi.devoxx.demo.b.sorting.byAge.and.byName;

import static java.util.Arrays.asList;

import java.util.Collections;
import java.util.List;

public class ImperativeSorting {

    public static class Person implements Comparable<Person> {

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

        @Override
        public int compareTo(Person o) {
            return ((Integer) age).compareTo(o.age);
        }

    }

    public static void main(String[] args) {
        List<Person> people = asList(new Person("Julie", 12), new Person("Bob", 12), new Person("Tony", 2),
                new Person("Adam", 6));
        Collections.sort(people);
        System.out.println(people);
    }
}
