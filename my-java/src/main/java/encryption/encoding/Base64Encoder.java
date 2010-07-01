package encryption.encoding;

import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class Base64Encoder implements BytesToStringEncoder {

    @Override
    public String encodeToString(byte[] encodedBytes) {
        return new BASE64Encoder().encode(encodedBytes);
    }

}
