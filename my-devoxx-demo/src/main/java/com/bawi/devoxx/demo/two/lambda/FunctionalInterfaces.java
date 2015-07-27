package com.bawi.devoxx.demo.two.lambda;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class FunctionalInterfaces {

    public interface Callback {
        void doSth();
    }

    public static class Person {
        private String name;
        private int age;
        public Person() {
        }
        public Person(String name, int age) {
            this.age = age;
            this.name = name;
        }
        public String getName() {
            return name;
        }
        public int getAge() {
            return age;
        }
        public void setName(String name) {
            this.name = name;
        }
        public void setAge(int age) {
            this.age = age;
        }
    }
    
    static class PersonComparer {
        public int compareByAge(Person p1, Person p2) {
            int age1 = p1.getAge();
            int age2 = p2.getAge();
            return age1 == age2 ? 0 : age1 > age2 ? 1 : -1; 
        }

        public static int staticCompareByAge(Person p1, Person p2) {
            int age1 = p1.getAge();
            int age2 = p2.getAge();
            return age1 == age2 ? 0 : age1 > age2 ? 1 : -1; 
        }

    }

    public static void main(String[] args) {
        Callback callback1 = new Callback() {

            @Override
            public void doSth() {
                System.out.println("Hello");
            }
        };

        Callback callback2 = () -> System.out.println("Hello");

        Callback callback3 = () -> {
            System.out.println("Hello");
        };


        int age = 10;
        Supplier<Person> supplier = new Supplier<Person>() {
            @Override
            public Person get() { 
                return new Person("Bob", age); 
            }
        };

        Supplier<Person> supplier2 = () -> new Person("Bob", age);

        Supplier<Person> supplier3 = () -> {
            return new Person("Bob", age);
        };

        Supplier<Person> supplier4 = () -> {
            return new Person();
        };

        Supplier<Person> supplier5 = () ->  new Person();

        Supplier<Person> supplier6 = Person::new; // Constructor reference (only for parameterless constructor)


        Consumer<String> consumer1 = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };

        Consumer<String> consumer = s -> {
            System.out.println(s);
        };

        Consumer<String> consumer2 = s -> System.out.println(s);

        Consumer<String> consumer3 = (String s) -> System.out.println(s);

        Consumer<?> consumer4 = o -> System.out.println(o);

        Consumer<?> consumer5 = System.out::println;


        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return true;
            }
        };

        Predicate<String> predicate2 = (String s) -> true;

        Predicate<String> predicate3 = s -> true;

        Predicate<String> predicate4 = s -> {
            return true;
        };

        Predicate<?> predicate5 = s -> true;

        Predicate<?> predicate6 = s -> Boolean.TRUE;
        
        Predicate<Person> isMature = new Predicate<Person>() {
            @Override
            public boolean test(Person person) {
                return person.getAge() > 18;
            }
        };
        Predicate<Person> isMature2 = person -> person.getAge() > 18;

        Predicate<Person> isMature3 = person -> {
            return person.getAge() > 18;
        };


        Function<String, Integer> function = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.parseInt(s);
            }
        };

        Function<String, Integer> function2 = (String s) -> Integer.parseInt(s);

        Function<String, Integer> function3 = s -> Integer.parseInt(s);

        Function<String, Integer> function4 = s -> {
            return Integer.parseInt(s);
        };

        Function<String, Integer> function5 = Integer::parseInt; // static method reference

        Function<Person, Integer> function6 = new Function<Person, Integer>() {
            @Override
            public Integer apply(Person t) {
                return t.getAge();
            }
        };

        Function<Person, Integer> function7 = t -> t.getAge();

        Function<Person, Integer> function8 = Person::getAge; // instance method reference

        PersonComparer personComparer = new PersonComparer();
        BiFunction<Person, Person, Integer> biFunction = new BiFunction<Person, Person, Integer>() {
            @Override
            public Integer apply(Person p1, Person p2) {
                return personComparer.compareByAge(p1, p2);
            }
        }; 

        BiFunction<Person, Person, Integer> biFunction2 = (p1, p2) -> personComparer.compareByAge(p1, p2);

        BiFunction<Person, Person, Integer> biFunction3 = personComparer::compareByAge; // instance method reference

        BiFunction<Person, Person, Integer> biFunction4 = new BiFunction<Person, Person, Integer>() {
            @Override
            public Integer apply(Person p1, Person p2) {
                return PersonComparer.staticCompareByAge(p1, p2);
            }
        };

        BiFunction<Person, Person, Integer> biFunction5 = (p1, p2) -> PersonComparer.staticCompareByAge(p1, p2);

        BiFunction<Person, Person, Integer> biFunction6 = PersonComparer::staticCompareByAge; // static method reference
    }

}
