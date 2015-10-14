package com.bawi.java8.groupBy;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MyJavaGroupBy {
    static class Person {
        private final String name;
        private final int age;
        
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
        List<Person> persons = Arrays.asList(
                new Person("John", 20), 
                new Person("Rob", 20), 
                new Person("Bartek", 22), 
                new Person("Ania", 21));
        
        // Group by person age, default collect to map: key=age, value = list of people (aggregate function is returning as list of persons)
        Map<Integer, List<Person>> peopleListByAge = persons.stream().collect(Collectors.groupingBy(Person::getAge));
        System.out.println(peopleListByAge);
        // {20=[Person [name=John, age=20], Person [name=Rob, age=20]], 21=[Person [name=Ania, age=21]], 22=[Person [name=Bartek, age=22]]}

        // Group by person age (key), map person to person name and collect the names to set (value), (aggregate function is returning as set of names)  
        Map<Integer, Set<String>> peopleNameSetByAge = persons.stream().collect(Collectors.groupingBy(Person::getAge, Collectors.mapping(Person::getName, Collectors.toSet())));
        System.out.println(peopleNameSetByAge);
        // {20=[Rob, John], 21=[Ania], 22=[Bartek]}

        
        // Group by person age (key) and count of person for a given age (value), apply counting aggregate function  
        Map<Integer, Long> peopleByAgeWithCount = persons.stream().collect(Collectors.groupingBy(Person::getAge, Collectors.counting()));
        System.out.println(peopleByAgeWithCount);
        // {20=2, 21=1, 22=1}



    }
}
