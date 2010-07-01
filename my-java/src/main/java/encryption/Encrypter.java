package encryption;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

import encryption.encoding.Base64Encoder;
import encryption.encoding.BytesToStringEncoder;

public class Encrypter {

    private final static String DEFAULT_ALGORITHM = "DES";

    private final Cipher encriptCipher;

    private BytesToStringEncoder converter = new Base64Encoder();

    public Encrypter(SecretKey key) {
        this(key, DEFAULT_ALGORITHM);
    }

    public Encrypter(SecretKey key, String algorithm) {
        encriptCipher = new CipherFactory(key, algorithm).create(Cipher.ENCRYPT_MODE);
    }

    public Encrypter(String keyFilePath) {
        this(keyFilePath, DEFAULT_ALGORITHM);
    }

    public Encrypter(String keyFilePath, String algorithm) {
        SecretKey key = new SecretKeyProvider(algorithm).readKeyFile(keyFilePath);
        encriptCipher = new CipherFactory(key, algorithm).create(Cipher.ENCRYPT_MODE);
    }

    public String encryptToString(String text) {
        return converter.encodeToString(encrypt(text));
    }

    public byte[] encrypt(String dataToEncrypt) {
        try {
            return encriptCipher.doFinal(dataToEncrypt.getBytes("UTF-8"));
        } catch (Exception e) {
            throw new RuntimeException("Encription failed: " + e.getMessage());
        }
    }

    public void setBytesStringConverter(BytesToStringEncoder converter) {
        this.converter = converter;
    }

}
