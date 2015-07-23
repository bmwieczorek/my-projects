package com.bawi.devoxx.demo.a.comma.separated.filenames;

import java.io.File;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ImprerativeCommaSeparatedFileNames {
    public static void main(String[] args) {
        File parent = new File(".");
        System.out.println(
                Stream.of(parent.listFiles())
                    .map(file -> file.getName())
                    .collect(Collectors.joining(", "))
                );
    }
}
