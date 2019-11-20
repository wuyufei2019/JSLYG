package com.cczu.thirdapi.andmu.util;

import org.apache.commons.codec.binary.Hex;
import org.springframework.util.Base64Utils;

import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;

public class SecretUtil {

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

    public static String rsaSign(String resource, String privateKey) {
        String result = "";
        byte[] data = resource.getBytes();
        byte[] keyBytes = Base64Utils.decode(privateKey.getBytes());
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        Signature signature = null;
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
            //签名算法
            signature = Signature.getInstance("SHA1WithRSA");
            signature.initSign(privateK);
            signature.update(data);
            result = Base64Utils.encodeToString(signature.sign());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}