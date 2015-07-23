package com.bawi.devoxx.demo.a.comma.separated.filenames;

import java.io.File;

public class DeclarativeCommaSeparatedFileNames {
    public static void main(String[] args) {
        File parent = new File(".");
        File[] children = parent.listFiles();
        for (int i = 0; i < children.length; i++) {
            System.out.print(children[i].getName());
            if (i < children.length - 1) {
                System.out.print(",");
            }
        }
    }
}
