package com.cczu.thirdapi.andmu;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cczu.sys.comm.utils.Global;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.thirdapi.andmu.util.AndmuHttpClient;
import com.cczu.thirdapi.andmu.util.AndmuJSONUtil;
import com.cczu.thirdapi.andmu.util.SecretUtil;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

public class AndmuToken {
    @Getter
    @Setter
    private Date createTime;

    @Getter
    @Setter
    private String token;


    private static AndmuToken api;

    private AndmuToken() {
    }

    public static AndmuToken getInstance() {
        if (api == null || StringUtils.isBlank(api.getToken())) {
            synchronized (AndmuToken.class) {
                if (api == null || StringUtils.isBlank(api.getToken())) {
                    api = new AndmuToken();
                    api.setCreateTime(new Date());
                    api.setToken(getAndmuToken());
                } else {
                    Date old = api.getCreateTime();
                    Date now = new Date();
                    //token有效期 七天 过期重新请求
                    if ((now.getTime() - old.getTime()) / 1000 * 60 * 60 * 24 > 7) {
                        api.setCreateTime(now);
                        api.setToken(getAndmuToken());
                    }
                }
            }
        }
        return api;
    }

    private static String getAndmuToken() {
        String andmuAppkey = Global.getConfig("andmu-appkey");
        String andmuSecret = Global.getConfig("andmu-secret");
        String andmuSig = SecretUtil.md5(andmuAppkey + andmuSecret);
        Map map = Maps.newHashMap();
        map.put("appKey", andmuAppkey);
        map.put("sig", andmuSig);
        String token = "";
        String result = AndmuHttpClient.doPost("https://open.andmu.cn/v1/openapi/auth/getToken",
                JSON.toJSONString(map), null);
        try {
            JSONObject mainData = AndmuJSONUtil.getMainDataObject(result);
            token = mainData.getString("token");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    public static void reloadToken() {
        synchronized (AndmuToken.class) {
            api.setToken(getAndmuToken());
        }
    }

}
