package com.bawi.threads.cpu;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class WritingFile {
    private static final String LINE = "abcdefghijklmnopqrstuwvxyzABCDEFGHIJKLMNOPQRSUWVXYZ\r\n";

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("target/data.txt");
        Files.deleteIfExists(path);
        Files.createFile(path);
        BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND);

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        for (int i = 0; i < 29999999; i++) { // generates 1.7GB file 
            bufferedWriter.write(i + LINE); // without this line it takes 3ms
        }

        stopWatch.stop();
        System.out.println(stopWatch);
    }

}
/*
User (app code only) time:                   5023 ms (23.71%)
System (os code on behalf app e.g.I/O) time: 1950 ms (9.21%)
CPU (total CPU spent for app) time:          6973 ms (32.92%)
Elapsed time:                                21181 ms (100.00%)


"main" #1 prio=5 os_prio=0 tid=0x000000000035e000 nid=0x2158 runnable [0x000000000259e000]
   java.lang.Thread.State: RUNNABLE
    at sun.nio.ch.FileDispatcherImpl.write0(Native Method)
    at sun.nio.ch.FileDispatcherImpl.write(FileDispatcherImpl.java:75)
    at sun.nio.ch.IOUtil.writeFromNativeBuffer(IOUtil.java:93)
    at sun.nio.ch.IOUtil.write(IOUtil.java:65)
    at sun.nio.ch.FileChannelImpl.write(FileChannelImpl.java:211)
    - locked <0x0000000081011208> (a java.lang.Object)
    at java.nio.channels.Channels.writeFullyImpl(Channels.java:78)
    at java.nio.channels.Channels.writeFully(Channels.java:101)
    at java.nio.channels.Channels.access$000(Channels.java:61)
    at java.nio.channels.Channels$1.write(Channels.java:174)
    - locked <0x00000000810110e8> (a java.nio.channels.Channels$1)
    at sun.nio.cs.StreamEncoder.writeBytes(StreamEncoder.java:221)
    at sun.nio.cs.StreamEncoder.implWrite(StreamEncoder.java:282)
    at sun.nio.cs.StreamEncoder.write(StreamEncoder.java:125)
    - locked <0x00000000810110d0> (a java.io.OutputStreamWriter)
    at java.io.OutputStreamWriter.write(OutputStreamWriter.java:207)
    at java.io.BufferedWriter.flushBuffer(BufferedWriter.java:129)
    - locked <0x00000000810110d0> (a java.io.OutputStreamWriter)
    at java.io.BufferedWriter.write(BufferedWriter.java:230)
    - locked <0x00000000810110d0> (a java.io.OutputStreamWriter)
    at java.io.Writer.write(Writer.java:157)
    at com.bawi.threads.cpu.WritingFile.main(WritingFile.java:23)

 */ 
