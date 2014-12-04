package com.bawi.java8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmdStatsParallelProvider {
    
    private static Logger LOGGER = LoggerFactory.getLogger(EmdStatsParallelProvider.class);

    public static void main(String[] args) {
        LOGGER.info("Available cores: {}", Runtime.getRuntime().availableProcessors());
        LOGGER.info("Started {}", new Date());
        long start = System.currentTimeMillis();

        File[] files = new File("target").listFiles();
        LOGGER.info("Files {}", Arrays.asList(files));

        List<File> filteredSortedFiles = Stream.of(files).
            filter(file -> file.getName().contains("sucessful.csv")).
            sorted(Comparator.comparing(file -> Integer.parseInt(file.getName().replaceAll("sucessful.csv\\.?","0")))).
            collect(Collectors.toList());

        LOGGER.info("Files filtered and sorted: {}", filteredSortedFiles);

        List<BufferedReader> csvReaders = Stream.of(files).
            filter(file -> file.getName().contains("csv")).
            map(csvFile -> createReader(csvFile)).
            collect(Collectors.toList());

        EmdStatsResult emdStatsResult = csvReaders.
            parallelStream().
            flatMap(csvReader -> csvReader.lines()).
            map(line -> new CSVLine(extractEmdNumber(line))).
            collect(ResultCollector.collector());

        LOGGER.info("Finished {}", new Date());
        long stop = System.currentTimeMillis();
        LOGGER.info("Finished: {} in {} ms", emdStatsResult, stop - start);
        //LOGGER.info("Finished: in {}", stop - start);
    }

    static long extractEmdNumber(String line) {
        String emdNumber = line.split(",")[3];
        //LOGGER.debug("{}: {}", Thread.currentThread(), emdNumber);
        return Long.parseLong(emdNumber);
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
