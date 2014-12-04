package com.bawi.java8.parallel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmdStatsAndTpsParallelProvider {
    
    private static Logger LOGGER = LoggerFactory.getLogger(EmdStatsAndTpsParallelProvider.class);

    public static void main(String[] args) {
        LOGGER.info("Available cores: {}", Runtime.getRuntime().availableProcessors());
        long start = System.currentTimeMillis();

        File[] files = new File("target").listFiles();
        LOGGER.info("Files {}", Arrays.asList(files));

        List<File> filteredSortedFiles = Stream.of(files).
            filter(file -> file.getName().contains("sucessful.csv")).
            sorted(Comparator.comparing(file -> Integer.parseInt(file.getName().replaceAll("sucessful.csv\\.?","0")))).
            collect(Collectors.toList());

        LOGGER.info("Files filtered and sorted: {}", filteredSortedFiles);

        Result result = getCsvReaders(files).
            parallelStream().
            flatMap(csvReader -> csvReader.lines()).
            map(line -> new CSVLine(extractEmdNumber(line), extractDate(line))).
            collect(new ResultCollector(new PeriodFactory(getStartDateForLogs(files)), new ResultFactory()));

        long stop = System.currentTimeMillis();
        LOGGER.info("Finished: {} in {} ms", result, stop - start);
    }

    private static List<BufferedReader> getCsvReaders(File[] files) {
        return Stream.of(files).
            filter(file -> file.getName().contains("csv")).
            map(csvFile -> createReader(csvFile)).
            collect(Collectors.toList());
    }

    private static Date getStartDateForLogs(File[] files) {
        List<File> filteredSortedFiles = Stream.of(files).
            filter(file -> file.getName().contains("sucessful.csv")).
            sorted(Comparator.comparing(file -> Integer.parseInt(file.getName().replaceAll("sucessful.csv\\.?","0")))).
            collect(Collectors.toList());

        String firstLine = createReader(filteredSortedFiles.get(filteredSortedFiles.size() - 1)).
             lines().
             findFirst().
             get();

        return extractDate(firstLine);
    }

    static long extractEmdNumber(String line) {
        String emdNumber = line.split(",")[3];
        //LOGGER.debug("{}: {}", Thread.currentThread(), emdNumber);
        return Long.parseLong(emdNumber);
    }

    static Date extractDate(String line) {
        String date = line.split(",")[0];
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    static BufferedReader createReader(File file) {
        try {
            LOGGER.debug("{}: {}", Thread.currentThread(), file);
            return new BufferedReader(new FileReader(file));
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

}
