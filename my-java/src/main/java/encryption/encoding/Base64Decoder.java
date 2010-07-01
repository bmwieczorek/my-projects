package encryption.encoding;

import java.io.IOException;


import sun.misc.BASE64Decoder;

@SuppressWarnings("restriction")
public class Base64Decoder implements StringToBytesDecoder {

    @Override
    public byte[] decodeToBytes(String text) {
        try {
            return new BASE64Decoder().decodeBuffer(text);
        } catch (IOException e) {
            throw new RuntimeException("Failed to decode from format Base64: " + e.getMessage());
        }
    }

}
