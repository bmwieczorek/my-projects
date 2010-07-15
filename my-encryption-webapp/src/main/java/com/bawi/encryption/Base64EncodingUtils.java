package com.bawi.encryption;

import org.apache.commons.codec.binary.Base64;

public class Base64EncodingUtils {

    public static byte[] decodeBase64(String base64String) {
        return Base64.decodeBase64(base64String);
    }
}
