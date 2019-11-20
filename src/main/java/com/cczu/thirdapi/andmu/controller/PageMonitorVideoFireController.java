package com.cczu.thirdapi.andmu.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.thirdapi.andmu.AndmuToken;
import com.cczu.thirdapi.andmu.entity.ThirdAndmuDevice;
import com.cczu.thirdapi.andmu.service.MonitorVideoMapsService;
import com.cczu.thirdapi.andmu.util.AndmuHttpClient;
import com.cczu.thirdapi.andmu.util.AndmuJSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 在线监控预警---烟花爆竹
 *
 * @author jason
 * @date 2017年9月6日
 */
@Controller
@RequestMapping("zxjkyj/fire")
public class PageMonitorVideoFireController extends BaseController {
    @Autowired
    private MonitorVideoMapsService videoMapsService;

    /**
     * 默认页面
     */
    @RequestMapping(value = "index")
    public String index(Model model) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("page", 1);
        jsonObject.put("pageSize", 50);
        String token = AndmuToken.getInstance().getToken();

        String xzqy = UserUtil.getCurrentShiroUser().getXzqy();
        //根据行政区域获取数据
        List<ThirdAndmuDevice> list = videoMapsService.listAllByXzqy(xzqy);
        for (ThirdAndmuDevice deviceIdMapQy : list) {

            JSONObject tmp = new JSONObject();
            tmp.put("deviceId", deviceIdMapQy.getDeviceId());
            String urlResult = AndmuHttpClient.doPost("https://open.andmu.cn/v1/openapi/app/realtime/thumbnail",
                    JSON.toJSONString(tmp), token);
            String url = null;
            try {
                url = AndmuJSONUtil.getMainDataString(urlResult);
            } catch (Exception e) {
                e.printStackTrace();
            }
            deviceIdMapQy.setUrl(url);
        }
        model.addAttribute("list", list);
        return "zxjkyj/ssjc/fire/index";
    }

    /**
     * 查看企业视频页面跳转
     *
     * @param deviceId
     * @param model
     * @return
     */
    @RequestMapping(value = "show/{deviceId}")
    public String showQYSP(@PathVariable String deviceId, Model model) {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("deviceId", deviceId);
        String result = AndmuHttpClient.doPost("https://open.andmu.cn/v1/openapi/app/getLiveUrl",
                JSON.toJSONString(jsonObject), AndmuToken.getInstance().getToken());
        try {
            JSONObject object = AndmuJSONUtil.getMainDataObject(result);
            model.addAttribute("url", object.get("url"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "zxjkyj/ssjc/fire/show";
    }


}

