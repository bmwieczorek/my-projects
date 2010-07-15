package com.bawi.encryption;

import static com.bawi.encryption.Algorithm.DES;
import static org.apache.commons.codec.binary.Base64.decodeBase64;
import static org.apache.commons.codec.binary.Base64.encodeBase64String;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class SecretKeyProvider {

    private final Algorithm algorithm;

    public SecretKeyProvider() {
        this(DES);
    }

    public SecretKeyProvider(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public SecretKey createFromBase64EncodedString(String base64EncodedKey) {
        return new SecretKeySpec(decodeBase64(base64EncodedKey), algorithm.toString());
    }

    public SecretKey createRandomKey() {
        try {
            return KeyGenerator.getInstance(algorithm.toString()).generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to create key for algorithm " + algorithm + ": "
                    + e.getMessage());
        }
    }

    public SecretKey readRawKeyFromFile(String keyFilePath) {
        validateFileExists(keyFilePath);
        try {
            byte[] rawKey = FileUtils.readFileToByteArray(new File(keyFilePath));
            return new SecretKeySpec(rawKey, algorithm.toString());
        } catch (IOException e) {
            throw new RuntimeException("Failed to read key in raw format from file " + keyFilePath + ": "
                    + e.getMessage());
        }
    }

    public SecretKey readBase64EncodedKeyFromFile(String keyFilePath) {
        validateFileExists(keyFilePath);
        try {
            String base64EncodedKey = FileUtils.readFileToString(new File(keyFilePath));
            return new SecretKeySpec(decodeBase64(base64EncodedKey), algorithm.toString());
        } catch (IOException e) {
            throw new RuntimeException("Failed to read key in base64Encoded format from file " + keyFilePath
                    + ":" + e.getMessage());
        }
    }

    public SecretKey readRawKeyFromResource(String keyResource) {
        try {
            InputStream rawKey = SecretKeyProvider.class.getClassLoader().getResourceAsStream(keyResource);
            validateResourceNotNull(keyResource, rawKey);
            return new SecretKeySpec(IOUtils.toByteArray(rawKey), algorithm.toString());
        } catch (IOException e) {
            throw new RuntimeException("Failed to read key in raw format from file " + keyResource + ": "
                    + e.getMessage());
        }
    }

    public SecretKey readBase64EncodedKeyFromResource(String keyResource) {
        try {
            InputStream rawKey = SecretKeyProvider.class.getClassLoader().getResourceAsStream(keyResource);
            validateResourceNotNull(keyResource, rawKey);
            String base64EncodedKey = IOUtils.toString(rawKey);
            return new SecretKeySpec(decodeBase64(base64EncodedKey), algorithm.toString());
        } catch (IOException e) {
            throw new RuntimeException("Failed to read key in base64Encoded format from file " + keyResource
                    + ":" + e.getMessage());
        }
    }

    public void writeRawKeyToFile(SecretKey key, String keyFilePath) {
        try {
            FileUtils.writeByteArrayToFile(new File(keyFilePath), key.getEncoded());
        } catch (IOException e) {
            throw new RuntimeException("Failed to write key in raw format to file " + keyFilePath + ".");
        }
    }

    public void writeBase64EncodedKeyToFile(SecretKey key, String keyFilePath) {
        try {
            FileUtils.writeStringToFile(new File(keyFilePath), encodeBase64String(key.getEncoded()));
        } catch (IOException e) {
            throw new RuntimeException("Failed to write key in base64Encoded format to file " + keyFilePath
                    + ".");
        }
    }

    private static void validateFileExists(String keyFilePath) {
        File keyFile = new File(keyFilePath);
        if (!keyFile.exists()) {
            throw new RuntimeException("File " + keyFilePath + "  doesn't exist.");
        }
    }

    private static void validateResourceNotNull(String keyResource, InputStream rawKey) {
        if (rawKey == null) {
            throw new RuntimeException("Resouse " + keyResource + "  doesn't exist.");
        }
    }

}
