package com.cczu.thirdapi.yunshixun.util;

import com.cczu.sys.comm.utils.Global;
import com.google.common.collect.Maps;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Map;

public class YsxHttpClient {
    private static Logger logger = LoggerFactory.getLogger(YsxHttpClient.class);
    private final static String identity = Global.getConfig("ysx-identity");
    private final static String key = Global.getConfig("ysx-key");
    private final static String userId = Global.getConfig("ysx-userId");
    private final static String passwd = Global.getConfig("ysx-passwd");
    private final static String address = Global.getConfig("ysx-address");

    public static String doPost(String body, String method) {

        HttpURLConnection connection = null;
        InputStream is = null;
        OutputStream os = null;
        BufferedReader br = null;
        String result = null;
        try {
            URL url = new URL(address + method);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(15000);
            // 设置读取主机服务器返回数据超时时间：60000毫秒
            connection.setReadTimeout(120000);

            // 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
            connection.setDoOutput(true);
            connection.setRequestProperty("content-type", "application/json");
            connection.setRequestProperty("identity", identity);
            connection.setRequestProperty("key", key);
            connection.setRequestProperty("userId", userId);
            connection.setRequestProperty("passwd", passwd);
            String timestamp = new Date().getTime() + "";
            connection.setRequestProperty("timestamp", timestamp);
            String appointSecret = "meet";//约定的秘钥
            String token = YsxSecretUtil.md5(method + identity + key + userId + passwd + timestamp + appointSecret);
            logger.info(token);
            connection.setRequestProperty("token", token);

            // 通过连接对象获取一个输出流
            os = connection.getOutputStream();
            // 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的
            os.write(body.getBytes("UTF-8"));
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
        String str = "CreateConference5b357915281c0fef984cd974bcd07f17e10adc3949ba59abbe56e057f20f883e132292119121234561505451419meet";
        System.out.println(DigestUtils.md5Hex(str));

        String body = "{\"operateID\":\"1\",\"subAddr\":\" \",\"strUserId\":\"sip:+8651880328541@ims.ge.chinamobile" +
                ".com\",\"strConvener\":\"1052594544\",\"strSubject\":\"1052594544\",\"iMaxMember\":5,\"sqMemberList\":[{\"strUserID\":\"sip:+8651880328541@ims.ge.chinamobile.com\",\"callID\":\"8601052594544\",\"strName\":\"8601052594544\",\"role\":1,\"isDefaultMute\":false,\"state\":\"6\"}],\"stPassword\":{},\"sqRecordList\":[],\"strBeginTime\":\"2019-06-12 11:14:00\",\"strEndTime\":\"2019-06-12 12:14:00\",\"ConfCtrlFlag\":\"00000101\",\"strAgenda\":\"\",\"stConferenceType\":{\"bAudio\":true,\"bVideo\":false,\"bHDVideo\":true,\"bData\":false},\"iCycleNo\":\"0\",\"confState\":\"0\"}";

        System.out.println(body);
        YsxHttpClient.doPost(body,
                YsxMethodEnum.CreateConference.name());
    }
}