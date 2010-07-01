package encryption.encoding;


public class Utf8Decoder implements StringToBytesDecoder {

    @Override
    public byte[] decodeToBytes(String text) {
        return text.getBytes();
    }

}
