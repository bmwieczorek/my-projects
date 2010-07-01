package encryption;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.io.FileUtils;

import encryption.encoding.Base64Decoder;
import encryption.encoding.Base64Encoder;
import encryption.encoding.BytesToStringEncoder;
import encryption.encoding.StringToBytesDecoder;

public class SecretKeyProvider {

    private final static String DEFAULT_ALGORITHM = "DES";

    private StringToBytesDecoder decoder = new Base64Decoder();
    private BytesToStringEncoder encoder = new Base64Encoder();

    private final String algorithm;

    public SecretKeyProvider() {
        this(DEFAULT_ALGORITHM);
    }

    public SecretKeyProvider(String algorithm) {
        this.algorithm = algorithm;
    }

    public SecretKey readKeyFile(String keyFilePath) {
        return new SecretKeySpec(readFile(keyFilePath), algorithm);
    }

    public void writeKeyToFile(SecretKey key, String keyFilePath) {
        byte[] keyBytes = key.getEncoded();
        try {
            FileUtils.writeStringToFile(new File(keyFilePath), encoder.encodeToString(keyBytes));
        } catch (IOException e) {
            throw new RuntimeException("Problem writing key to file " + keyFilePath + ".");
        }
    }

    public SecretKey createRandomKey() {
        try {
            return KeyGenerator.getInstance(algorithm).generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Could not create key for algorithm " + algorithm + ": "
                    + e.getMessage());
        }
    }

    private byte[] readFile(String keyFilePath) {
        File keyFile = new File(keyFilePath);
        if (!keyFile.exists()) {
            throw new RuntimeException("File " + keyFilePath + "  doesn't exist.");
        }
        try {
            return decoder.decodeToBytes(FileUtils.readFileToString(keyFile));
        } catch (IOException e) {
            throw new RuntimeException("Problem reading file " + keyFilePath + ".");
        }
    }

    public void setBytesToStringEncoder(BytesToStringEncoder encoder) {
        this.encoder = encoder;
    }

    public void setStringToBytesDecoder(StringToBytesDecoder decoder) {
        this.decoder = decoder;
    }

}
