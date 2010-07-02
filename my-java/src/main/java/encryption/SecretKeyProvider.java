package encryption;

import static encryption.Algorithm.DES;
import static encryption.Base64EncodingUtils.decodeBase64;
import static encryption.Base64EncodingUtils.encodeBase64;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.io.FileUtils;

public class SecretKeyProvider {

    private final Algorithm algorithm;

    public SecretKeyProvider() {
        this(DES);
    }

    public SecretKeyProvider(Algorithm algorithm) {
        this.algorithm = algorithm;
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

    public void writeRawKeyToFile(SecretKey key, String keyFilePath) {
        try {
            FileUtils.writeByteArrayToFile(new File(keyFilePath), key.getEncoded());
        } catch (IOException e) {
            throw new RuntimeException("Failed to write key in raw format to file " + keyFilePath + ".");
        }
    }

    public void writeBase64EncodedKeyToFile(SecretKey key, String keyFilePath) {
        try {
            FileUtils.writeStringToFile(new File(keyFilePath), encodeBase64(key.getEncoded()));
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
}
