package com.bawi.encryption;

import static com.bawi.encryption.Algorithm.DES;

import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class CipherFactory {

    private final SecretKey key;

    public CipherFactory(SecretKey key) {
        this(key, DES);
    }

    private final Algorithm algorithm;

    public CipherFactory(SecretKey key, Algorithm algorithm) {
        this.key = key;
        this.algorithm = algorithm;
    }

    public Cipher create(int mode) {
        try {
            Cipher encriptCipher = Cipher.getInstance(algorithm.toString());
            encriptCipher.init(mode, key);
            return encriptCipher;
        } catch (GeneralSecurityException e) {
            throw new RuntimeException("Could not create cipher: " + e.getMessage());
        }
    }
}
