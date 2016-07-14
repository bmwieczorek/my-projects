package com.bawi.spring.boot.web;


import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.spec.KeySpec;

public final class PasswordUtils {

    private static final String UTF_8 = "UTF-8";
    private static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    private static final String ENCRYPTION_KEY = "TRAVEL_EXPERIENCE_SABRE_CUSTOM_MESSAGING"; // at least 24 chars

    private PasswordUtils() {
        // utility
    }

    public static String encrypt(String unencryptedString) throws Exception {
        if (unencryptedString == null || unencryptedString.isEmpty()) {
            return unencryptedString;
        }
        Cipher cipher = Cipher.getInstance(DESEDE_ENCRYPTION_SCHEME);
        SecretKey key = getKey();
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] plainText = unencryptedString.getBytes(UTF_8);
        byte[] encryptedText = cipher.doFinal(plainText);
        return new String(Base64.encodeBase64(encryptedText));
    }

    public static String decrypt(String encryptedString) throws Exception {
        if (encryptedString == null || encryptedString.isEmpty()) {
            return encryptedString;
        }
        Cipher cipher = Cipher.getInstance(DESEDE_ENCRYPTION_SCHEME);
        SecretKey key = getKey();
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] encryptedText = Base64.decodeBase64(encryptedString);
        byte[] plainText = cipher.doFinal(encryptedText);
        return new String(plainText);
    }

    private static SecretKey getKey() throws Exception {
        byte[] arrayBytes = ENCRYPTION_KEY.getBytes(UTF_8);
        KeySpec ks = new DESedeKeySpec(arrayBytes);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(DESEDE_ENCRYPTION_SCHEME);
        return skf.generateSecret(ks);
    }
}
