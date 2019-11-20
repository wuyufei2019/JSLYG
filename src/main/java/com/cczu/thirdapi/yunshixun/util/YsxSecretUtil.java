package com.cczu.thirdapi.yunshixun.util;

import org.apache.commons.codec.binary.Hex;
import org.springframework.util.Base64Utils;

import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;

public class YsxSecretUtil {

    public static String md5(String resource) {
        String result = "";
        try {
            result = Hex.encodeHexString(MessageDigest.getInstance("MD5").
                    digest(resource.getBytes("utf-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}