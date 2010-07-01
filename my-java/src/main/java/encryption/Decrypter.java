package encryption;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

import encryption.encoding.Base64Decoder;
import encryption.encoding.StringToBytesDecoder;

public class Decrypter {

    private final static String DEFAULT_ALGORITHM = "DES";

    private StringToBytesDecoder decoder = new Base64Decoder();

    private final Cipher decriptCipher;

    public Decrypter(SecretKey key) {
        this(key, DEFAULT_ALGORITHM);
    }

    public Decrypter(SecretKey key, String algorithm) {
        decriptCipher = new CipherFactory(key, algorithm).create(Cipher.DECRYPT_MODE);
    }

    public String decryptFromString(String text) {
        return decrypt(decoder.decodeToBytes(text));
    }

    public String decrypt(byte[] bytes) {
        try {
            byte[] decodedBytes = decriptCipher.doFinal(bytes);
            return new String(decodedBytes, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("Decryption failed: " + e.getMessage());
        }
    }

    public void setStringToBytesDecoder(StringToBytesDecoder decoder) {
        this.decoder = decoder;
    }
}
