package com.sabre.java8.newfeatures;

import static java.util.Arrays.asList;

import java.util.List;
import java.util.stream.Stream;

public class MyMapVsFlatMap {

    public static void main(String[] args) {
        // map
        Stream<Integer> mappedStream = createStreamOfLists()
            .map(list -> {
                System.out.println("in map: " + list);
                return list.size();
            });
            mappedStream
                .forEach(System.out::println);

        Stream<String> flatMappedStream = createStreamOfLists()
            .flatMap(list -> {
                System.out.println("in flatMap: " + list);
                return list.stream();
            });
            flatMappedStream
                .forEach(System.err::println);

        List<String> letters = asList("a", "b");
        Stream<String> letterStream = letters.stream();
        letterStream.forEach(t -> System.out.println("s1: " + t));
        // letterStream.forEach(t -> System.out.println("s2: " + t)); // cannot reuse stream as java.lang.IllegalStateException: stream has already been operated upon or closed

    }

    private static Stream<List<String>> createStreamOfLists() {
        List<List<String>> listOfLists = asList(asList("a", "b"), asList("A", "B"), asList("1", "2", "3"));
        return listOfLists.stream();
    }
}
