package com.bawi.threads.cpu;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadingFileThread {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("target/data.txt");
        String line = null;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        try (BufferedReader br = Files.newBufferedReader(path)) {
            for (String l = null; (l = br.readLine()) != null;) {
                line = l;
            }
        }

//        try (Scanner sc = new Scanner(path, "UTF-8")) {
//            while (sc.hasNextLine()) {
//                line = sc.nextLine();
//            }
//        }

        stopWatch.stop();
        System.out.println(line);
        System.out.println(stopWatch);

    }

}
