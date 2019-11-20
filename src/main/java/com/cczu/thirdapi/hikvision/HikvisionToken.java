package com.cczu.thirdapi.hikvision;


import com.alibaba.fastjson.JSONObject;
import com.cczu.sys.comm.utils.Global;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.thirdapi.hikvision.util.HIkvisionJSONUtil;
import com.cczu.thirdapi.hikvision.util.HikvisionHttpClient;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

public class HikvisionToken {
    @Getter
    @Setter
    private Date expireTime;//过期时间

    @Getter
    @Setter
    private String token;


    private static HikvisionToken api;

    private HikvisionToken() {
    }

    public static HikvisionToken getInstance() {
        if (api == null || StringUtils.isBlank(api.getToken())) {
            synchronized (HikvisionToken.class) {
                if (api == null || StringUtils.isBlank(api.getToken())) {
                    api = new HikvisionToken();
                    setHkTokenInfo();
                } else {
                    //token有效期 七天 过期重新请求
                    if (new Date().after(api.getExpireTime())) {
                        setHkTokenInfo();
                    }
                }
            }
        }
        return api;
    }

    private static void setHkTokenInfo() {
        String hkAppkey = Global.getConfig("hikvision-AppKey");
        String hkSecret = Global.getConfig("hikvision-Secret");
        Map map = Maps.newHashMap();
        map.put("appKey", hkAppkey);
        map.put("appSecret", hkSecret);
        String result = HikvisionHttpClient.doPost("https://open.ys7.com/api/lapp/token/get", map);
        try {
            JSONObject mainData = HIkvisionJSONUtil.getMainDataObject(result);
            api.setToken(mainData.getString("accessToken"));
            api.setExpireTime(mainData.getTimestamp("expireTime"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void reloadToken() {
        synchronized (HikvisionToken.class) {
            setHkTokenInfo();
        }
    }

}
