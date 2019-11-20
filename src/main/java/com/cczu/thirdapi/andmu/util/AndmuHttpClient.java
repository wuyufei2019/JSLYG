package com.cczu.thirdapi.andmu.util;

import com.alibaba.fastjson.JSON;
import com.cczu.sys.comm.utils.Global;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.thirdapi.andmu.AndmuToken;
import com.google.common.collect.Maps;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.IOUtils;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;

public class AndmuHttpClient {
    private static Logger logger = LoggerFactory.getLogger(AndmuHttpClient.class);
    private final static String appid = Global.getConfig("andmu-appId");
    private final static String andmuRSA = Global.getConfig("andmu-RSA");
    private final static String version = Global.getConfig("andmu-interface-version");

    public static String doPost(String httpUrl, String body, String token) {

        HttpURLConnection connection = null;
        InputStream is = null;
        OutputStream os = null;
        BufferedReader br = null;
        String result = null;
        Map map = Maps.newHashMap();
        try {
            URL url = new URL(httpUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(15000);
            // 设置读取主机服务器返回数据超时时间：60000毫秒
            connection.setReadTimeout(60000);

            // 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
            connection.setDoOutput(true);
            connection.setRequestProperty("content-type", "application/json");
            connection.setRequestProperty("appid", appid);
            map.put("appid", appid);
            String md5 = SecretUtil.md5(body);
            connection.setRequestProperty("md5", md5);
            map.put("md5", md5);
            String time = new Date().getTime() + "";
            connection.setRequestProperty("timestamp", time);
            map.put("timestamp", time);
            connection.setRequestProperty("version", version);
            map.put("version", version);
            if (StringUtils.isNotBlank(token)) {
                connection.setRequestProperty("token", token);
                map.put("token", token);
            }
            String signature = SecretUtil.rsaSign(JSON.toJSONString(map), andmuRSA);

            connection.setRequestProperty("signature", signature);
            // 通过连接对象获取一个输出流
            os = connection.getOutputStream();
            // 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的
            os.write(body.getBytes());
            // 通过连接对象获取一个输入流，向远程读取

            is = connection.getInputStream();
            // 对输入流对象进行包装:charset根据工作项目组的要求来设置
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            StringBuffer sbf = new StringBuffer();
            String temp = null;
            // 循环遍历一行一行读取数据
            while ((temp = br.readLine()) != null) {
                sbf.append(temp);
            }
            result = sbf.toString();
            logger.info(result);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(br);
            IOUtils.closeQuietly(os);
            IOUtils.closeQuietly(is);
            IOUtils.close(connection);
        }
        return result;

    }

    public static void main(String[] args) {
        String token = AndmuToken.getInstance().getToken();
        System.out.println(token);

        String andmuAppId = Global.getConfig("andmu-appId");
        String andmuRSA = Global.getConfig("andmu-RSA");
        String andmuAppkey = Global.getConfig("andmu-appkey");
        String andmuSecret = Global.getConfig("andmu-secret");
        String andmuSig = null;
        try {
            andmuSig = Hex.encodeHexString(MessageDigest.getInstance("MD5").
                    digest((andmuAppkey + andmuSecret).getBytes("utf-8")));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Map map = Maps.newHashMap();
        map.put("appKey", andmuAppkey);
        map.put("sig", andmuSig);
        System.out.println(JSON.toJSONString(map));
        String md5 = null;
        try {
            md5 =
                    Hex.encodeHexString(MessageDigest.getInstance("MD5").digest(JSON.toJSONString(map)
                            .getBytes("utf-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map map2 = Maps.newHashMap();
        map2.put("appid", andmuAppId);
        map2.put("md5", md5);
        String time = new Date().getTime() + "";
        map2.put("timestamp", "1558575845839");
        String version = "1.0.0";
        map2.put("version", version);
        String json = JSON.toJSONString(map2);
        System.out.println(json);
        String rsa = SecretUtil.rsaSign(json, andmuRSA);
        System.out.println(rsa);

    }
}