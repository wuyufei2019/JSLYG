package com.cczu.thirdapi.yunshixun.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cczu.thirdapi.andmu.AndmuToken;
import com.cczu.util.common.StringUtils;

public class YsxJSONUtil {
    public static JSONObject getMainDataObject(JSONObject jsonObject) {
        if (StringUtils.equals(jsonObject.getString("iResult"), "0")) {
            return jsonObject.getJSONObject("data");
        } else if (StringUtils.equals(jsonObject.getString("resultCode"), "11504")) {
            AndmuToken.reloadToken();
            throw new YsxException("请重新请求token！");
        } else {
            throw new RuntimeException("请求结果错误！");
        }
    }

    public static JSONObject getMainDataObject(String result) {
        JSONObject jsonObject = JSON.parseObject(result);
        return getMainDataObject(jsonObject);
    }


}
