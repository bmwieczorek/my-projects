package com.bawi.encryption;

import static com.bawi.encryption.Algorithm.DES;
import static javax.crypto.Cipher.DECRYPT_MODE;
import static org.apache.commons.codec.binary.Base64.decodeBase64;

import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class Decrypter {

    private final Cipher decriptCipher;

    public Decrypter(SecretKey key) {
        this(key, DES);
    }

    public Decrypter(SecretKey key, Algorithm algorithm) {
        decriptCipher = new CipherFactory(key, algorithm).create(DECRYPT_MODE);
    }

    public String decryptBase64Encoded(String base64EndodedData) {
        return decrypt(decodeBase64(base64EndodedData));
    }

    public String decrypt(byte[] data) {
        try {
            return new String(decriptCipher.doFinal(data));
        } catch (GeneralSecurityException e) {
            throw new RuntimeException("Decryption failed: " + e.getMessage());
        }
    }
}
