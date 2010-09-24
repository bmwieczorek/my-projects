package gzipcompression;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

import org.apache.commons.io.IOUtils;

public class Reader {
    public static void main(String[] args) throws IOException {
        File file = new File("K0-transactions-trunk-compressed.log.2010-09-13-15-42.gz");
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        GZIPInputStream gzipInputStream = new GZIPInputStream(bufferedInputStream);
        String string = IOUtils.toString(gzipInputStream);
        System.out.println(string);
        System.out.println(file.exists());
    }
}
