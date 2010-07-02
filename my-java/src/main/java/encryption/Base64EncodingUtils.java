package encryption;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class Base64EncodingUtils {

    public static String encodeBase64(byte[] encodedBytes) {
        return new BASE64Encoder().encode(encodedBytes);
    }

    public static byte[] decodeBase64(String text) {
        try {
            return new BASE64Decoder().decodeBuffer(text);
        } catch (IOException e) {
            throw new RuntimeException("Failed to decode from format Base64: " + e.getMessage());
        }
    }
}
