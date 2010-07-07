package com.bawi.encryption;

import static com.bawi.encryption.Algorithm.DES;
import static com.bawi.encryption.Base64EncodingUtils.encodeBase64;
import static javax.crypto.Cipher.ENCRYPT_MODE;

import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class Encrypter {

    private final Cipher encriptCipher;

    public Encrypter(SecretKey key) {
        this(key, DES);
    }

    public Encrypter(SecretKey key, Algorithm algorithm) {
        encriptCipher = new CipherFactory(key, algorithm).create(ENCRYPT_MODE);
    }

    public Encrypter(String keyFilePath) {
        this(keyFilePath, DES);
    }

    public Encrypter(String keyFilePath, Algorithm algorithm) {
        SecretKey key = new SecretKeyProvider(algorithm).readRawKeyFromFile(keyFilePath);
        encriptCipher = new CipherFactory(key, algorithm).create(ENCRYPT_MODE);
    }

    public String encryptToBase64Encoded(String text) {
        return encodeBase64(encrypt(text));
    }

    public byte[] encrypt(String dataToEncrypt) {
        try {
            return encriptCipher.doFinal(dataToEncrypt.getBytes());
        } catch (GeneralSecurityException e) {
            throw new RuntimeException("Encription failed: " + e.getMessage());
        }
    }

}
