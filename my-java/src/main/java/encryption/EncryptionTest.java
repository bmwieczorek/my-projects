package encryption;

import static junit.framework.Assert.assertEquals;

import javax.crypto.SecretKey;

import org.junit.Test;

public class EncryptionTest {

    SecretKeyProvider keyProvider = new SecretKeyProvider();

    @Test
    public void shouldEncryptAndDecryptWithDefaulDESAlgorithm() {
        SecretKey key = createKey();
        String originalText = "Hello World!";

        // when
        String encryptedText = new Encrypter(key).encryptToBase64Encoded(originalText);
        String decryptedText = new Decrypter(key).decryptBase64Encoded(encryptedText);

        // then
        System.out.println(encryptedText);
        assertEquals(originalText, decryptedText);
    }

    @Test
    public void shouldEncryptAndDecryptQWithDeafultDESedeAlgorithm() {
        // given
        Algorithm algorithm = Algorithm.DESede;
        SecretKey key = new SecretKeyProvider(algorithm).createRandomKey();
        String originalText = "Hello World!";

        // when
        String encryptedText = new Encrypter(key, algorithm).encryptToBase64Encoded(originalText);
        String decryptedText = new Decrypter(key, algorithm).decryptBase64Encoded(encryptedText);

        // then
        System.out.println(encryptedText);
        assertEquals(originalText, decryptedText);
    }

    @Test
    public void shouldEncryptAndDecryptWithStoringBase64EncodedKeyToFile() {
        // given
        String originalText = "Hello World!";
        String keyFilePath = "target/key.txt";
        SecretKey originalKey = createKey();

        // when
        String encryptedText = new Encrypter(originalKey).encryptToBase64Encoded(originalText);
        keyProvider.writeBase64EncodedKeyToFile(originalKey, keyFilePath);

        // when
        SecretKey readKey = keyProvider.readBase64EncodedKeyFromFile(keyFilePath);
        String decryptedText = new Decrypter(readKey).decryptBase64Encoded(encryptedText);

        // then
        assertEquals(originalText, decryptedText);
        assertEquals(originalKey, readKey);
    }

    @Test
    public void shouldEncryptAndDecryptWithStoringRawKeyToFile() {
        // given
        String originalText = "Hello World!";
        String keyFilePath = "target/key.txt";
        SecretKey originalKey = createKey();

        // when
        byte[] encryptedData = new Encrypter(originalKey).encrypt(originalText);
        keyProvider.writeRawKeyToFile(originalKey, keyFilePath);

        // when
        SecretKey readKey = keyProvider.readRawKeyFromFile(keyFilePath);
        String decryptedText = new Decrypter(readKey).decrypt(encryptedData);

        // then
        assertEquals(originalText, decryptedText);
        assertEquals(originalKey, readKey);
    }

    private SecretKey createKey() {
        return keyProvider.createRandomKey();
    }

}
