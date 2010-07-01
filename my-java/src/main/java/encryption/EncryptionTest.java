package encryption;

import static junit.framework.Assert.assertEquals;

import javax.crypto.SecretKey;

import org.junit.Test;

import encryption.encoding.Utf8Decoder;
import encryption.encoding.Utf8Encoder;

public class EncryptionTest {

    SecretKeyProvider keyProvider = new SecretKeyProvider();

    @Test
    public void shouldEncryptAndDecryptWithDefaulDESAlgorithm() {
        SecretKey key = createKey();
        String originalText = "Hello World!";

        // when
        String encryptedText = new Encrypter(key).encryptToString(originalText);
        String decryptedText = new Decrypter(key).decryptFromString(encryptedText);

        // then
        System.out.println(encryptedText);
        assertEquals(originalText, decryptedText);
    }

    @Test
    public void shouldEncryptAndDecryptQWithDeafultDESedeAlgorithm() {
        // given
        String algorithm = "DESede";
        SecretKey key = new SecretKeyProvider(algorithm).createRandomKey();
        String originalText = "Hello World!";

        // when
        String encryptedText = new Encrypter(key, algorithm).encryptToString(originalText);
        String decryptedText = new Decrypter(key, algorithm).decryptFromString(encryptedText);

        // then
        System.out.println(encryptedText);
        assertEquals(originalText, decryptedText);
    }

    @Test
    public void shouldEncryptAndDecryptWithStoringKeyToFile() {
        // given
        String originalText = "Hello World!";
        String keyFilePath = "target/key.txt";
        SecretKey originalKey = createKey();

        // when
        String encryptedText = new Encrypter(originalKey).encryptToString(originalText);
        keyProvider.writeKeyToFile(originalKey, keyFilePath);

        // when
        SecretKey readKey = keyProvider.readKeyFile(keyFilePath);
        String decryptedText = new Decrypter(readKey).decryptFromString(encryptedText);

        // then
        assertEquals(originalText, decryptedText);
        assertEquals(originalKey, readKey);
    }

    @Test
    public void shouldEncryptAndDecryptWithStoringUtf8EncodedKeyToFile() {
        // given
        String originalText = "Hello World!";
        String keyFilePath = "target/key.txt";
        SecretKey originalKey = createKey();
        keyProvider.setBytesToStringEncoder(new Utf8Encoder());
        keyProvider.setStringToBytesDecoder(new Utf8Decoder());

        // when
        String encryptedText = new Encrypter(originalKey).encryptToString(originalText);
        keyProvider.writeKeyToFile(originalKey, keyFilePath);

        // when
        SecretKey readKey = keyProvider.readKeyFile(keyFilePath);
        String decryptedText = new Decrypter(readKey).decryptFromString(encryptedText);

        // then
        assertEquals(originalText, decryptedText);
        assertEquals(originalKey, readKey);
    }

    private SecretKey createKey() {
        return keyProvider.createRandomKey();
    }

}
