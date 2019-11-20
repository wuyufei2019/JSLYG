package com.cczu.thirdapi.hikvision.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cczu.thirdapi.hikvision.HikvisionToken;
import com.cczu.util.common.StringUtils;

public class HIkvisionJSONUtil {
    public static JSONObject getMainDataObject(JSONObject jsonObject) {
        if (StringUtils.equals(jsonObject.getString("code"), "200")) {
            return jsonObject.getJSONObject("data");
        } else if (StringUtils.equals(jsonObject.getString("code"), "10002")) {
            HikvisionToken.reloadToken();
            throw new HikvisionException("请重新请求token！");
        } else {
            throw new RuntimeException("请求结果错误！");
        }
    }

    public static JSONObject getMainDataObject(String result) {
        JSONObject jsonObject = JSON.parseObject(result);
        return getMainDataObject(jsonObject);
    }

    public static JSONArray getMainDataArray(JSONObject jsonObject) {
        if (StringUtils.equals(jsonObject.getString("code"), "200")) {
            return jsonObject.getJSONArray("data");
        } else if (StringUtils.equals(jsonObject.getString("code"), "10002")) {
            HikvisionToken.reloadToken();
            throw new HikvisionException("请重新请求token！");
        } else {
            throw new RuntimeException("请求结果错误！");
        }
    }

    public static JSONArray getMainDataArray(String result) {
        JSONObject jsonObject = JSON.parseObject(result);
        return getMainDataArray(jsonObject);
    }
    
    public static String getMainDataString(JSONObject jsonObject) {
        if (StringUtils.equals(jsonObject.getString("code"), "200")) {
            return jsonObject.getString("data");
        } else if (StringUtils.equals(jsonObject.getString("code"), "10002")) {
            HikvisionToken.reloadToken();
            throw new HikvisionException("请重新请求token！");
        } else {
            throw new RuntimeException("请求结果错误！");
        }
    }

    public static String getMainDataString(String result) {
        JSONObject jsonObject = JSON.parseObject(result);
        return getMainDataString(jsonObject);
    }
}
