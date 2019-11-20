package com.cczu.thirdapi.hikvision.util;

import com.cczu.sys.comm.utils.Global;
import com.cczu.thirdapi.hikvision.HikvisionToken;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class HikvisionHttpClient {
    private static Logger logger = LoggerFactory.getLogger(HikvisionHttpClient.class);

    public static String doPost(String httpUrl, Map<String, Object> params) {

        HttpURLConnection connection = null;
        InputStream is = null;
        DataOutputStream dps = null;
        BufferedReader br = null;
        String result = null;
        String strParam = "";

        try {
            URL url = new URL(httpUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(15000);
            // 设置读取主机服务器返回数据超时时间：60000毫秒
            connection.setReadTimeout(60000);

            // 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
            connection.setDoOutput(true);
            connection.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                //connection.setRequestProperty(entry.getKey(), entry.getValue().toString());
                strParam += "&" + entry.getKey() + "=" + URLEncoder.encode(
                        entry.getValue().toString(), "utf-8");
            }
            // 通过连接对象获取一个输出流
            dps = new DataOutputStream(connection.getOutputStream());

            // 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的
            dps.writeBytes(strParam.substring(1));
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
        Map map = new HashMap();
        map.put("accessToken",HikvisionToken.getInstance().getToken());
        map.put("source","C61283614:4");
        //String result = doPost("https://open.ys7.com/api/lapp/live/video/open",map);
        String result = doPost("https://open.ys7.com/api/lapp/live/address/get",map);
        System.out.println(result);
    }
}