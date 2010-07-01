package encryption.encoding;

public class Utf8Encoder implements BytesToStringEncoder {

    @Override
    public String encodeToString(byte[] bytes) {
        return new String(bytes);
    }
}
