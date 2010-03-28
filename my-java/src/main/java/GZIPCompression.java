import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.junit.Test;

/**
 * @author Bartosz Wieczorek
 * @Dec 1, 2008
 * 
 */
public class GZIPCompression {

    static ByteArrayOutputStream compress(String data) throws IOException {

        ByteArrayInputStream in =
                new ByteArrayInputStream(data.getBytes("UTF-8"));

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        GZIPOutputStream gzout = new GZIPOutputStream(os);

        // Transfer bytes from the input String to the GZIP output stream
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            gzout.write(buf, 0, len);
        }
        in.close();
        gzout.close();
        return os;
    }

    static String uncompress(InputStream compressedStream) throws IOException {

        GZIPInputStream gzin = new GZIPInputStream(compressedStream);

        ByteArrayOutputStream os = new ByteArrayOutputStream();

        // Transfer bytes from the GZIP input stream to the output String
        byte[] gzbuf = new byte[1024];
        int len;
        while ((len = gzin.read(gzbuf)) > 0) {
            os.write(gzbuf, 0, len);
        }

        return os.toString("UTF-8");

    }

    @Test
    public void testCompression() throws IOException {

        // String data = "Hello world Bartek";
        String data =
                "fdsaaaaaaaaaaaaaaaaaafjkasdl;fjaopfho[ahfoahfa;hfil;euahrfeuioahfuioahf;dhafuidshfpiaushfkl;ahfui;aehsfiupa";
        ByteArrayOutputStream os = compress(data);
        String result = uncompress(new ByteArrayInputStream(os.toByteArray()));
        assertEquals(data, result);
    }

}
