package encryption;

import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class CipherFactory {

    private final static String DEFAULT_ALGORITHM = "DES";

    private final SecretKey key;

    public CipherFactory(SecretKey key) {
        this(key, DEFAULT_ALGORITHM);
    }

    private final String algorithm;

    public CipherFactory(SecretKey key, String algorithm) {
        this.key = key;
        this.algorithm = algorithm;
    }

    public Cipher create(int mode) {
        try {
            Cipher encriptCipher = Cipher.getInstance(algorithm);
            encriptCipher.init(mode, key);
            return encriptCipher;
        } catch (GeneralSecurityException e) {
            throw new RuntimeException("Could not create cipher: " + e.getMessage());
        }
    }
}
