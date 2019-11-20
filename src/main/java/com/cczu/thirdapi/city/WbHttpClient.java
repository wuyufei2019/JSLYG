package com.cczu.thirdapi.city;

import com.cczu.sys.comm.utils.Global;
import org.apache.commons.io.IOUtils;
import org.eclipse.jetty.util.ajax.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WbHttpClient {
    private static Logger logger = LoggerFactory.getLogger(WbHttpClient.class);

    public static String doPost(String method, String json) {

        HttpURLConnection connection = null;
        InputStream is = null;
        DataOutputStream dps = null;
        BufferedReader br = null;
        String result = null;
        String strParam = "";

        try {
            URL url = new URL(Global.getConfig("city_wb_url") + method);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(15000);
            // 设置读取主机服务器返回数据超时时间：60000毫秒
            connection.setReadTimeout(60000);

            // 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
            connection.setDoOutput(true);
            connection.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            strParam = "json=" + json;
            // 通过连接对象获取一个输出流
            dps = new DataOutputStream(connection.getOutputStream());

            // 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的
            dps.write(strParam.getBytes("UTF-8"));
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
            IOUtils.closeQuietly(dps);
            IOUtils.closeQuietly(is);
            IOUtils.close(connection);
        }
        return result;

    }

    public static void main(String[] args) {
        //String result = doPost("https://open.ys7.com/api/lapp/live/video/open",map);
        String json = "[{\"Password\":\"57-38-4D-D3-83-1A-5B-C6-E4-D9-15-9B-5D-C1-4D-7A\",\"UserID\":\"lyqyjj\",\"data\":[{\"CurrentValue\":\"4\",\"EquipmentID\":\"32070391320700555879135A001\",\"EquipmentLowerThreshold\":0.00,\"EquipmentUpperThreshold\":1.65,\"RecordingTime\":\"2019-10-21 15:09:04\",\"WarningType\":\"超上限报警\"}]}]";
        String re = doPost("/WarningData", json);
        logger.info(json);
        String result = re.substring(re.indexOf("[") + 1, re.indexOf("]"));
        Object parse = JSON.parse(result);
        System.out.println(result);
    }
}