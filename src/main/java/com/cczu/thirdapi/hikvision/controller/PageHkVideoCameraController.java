package com.cczu.thirdapi.hikvision.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.thirdapi.hikvision.HikvisionToken;
import com.cczu.thirdapi.hikvision.entity.ThirdHikvisionCamera;
import com.cczu.thirdapi.hikvision.entity.ThirdHikvisionDevice;
import com.cczu.thirdapi.hikvision.service.MonitorHkVideoCameraService;
import com.cczu.thirdapi.hikvision.service.MonitorHkVideoMapsService;
import com.cczu.thirdapi.hikvision.util.HIkvisionJSONUtil;
import com.cczu.thirdapi.hikvision.util.HikvisionHttpClient;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 海康-设备设置controller
 *
 * @author jason
 * @date 2017年6月15日
 */
@Controller
@RequestMapping("zxjkyj/hkvision/camera")
public class PageHkVideoCameraController extends BaseController {

    @Autowired
    private MonitorHkVideoCameraService cameraService;

    /**
     * 初始化
     */
    @RequiresPermissions("api:hksetting:add")
    @RequestMapping(value = "init", method = RequestMethod.GET)
    @ResponseBody
    public String init() {
        String dataResult = "success";
        Map<String, Object> paramsMap = getTokenMap();
        paramsMap.put("pageSize", 50);
        List<ThirdHikvisionCamera> all = cameraService.getAll();
        for (int pageStart = 0; ; pageStart++) {
            paramsMap.put("pageStart", pageStart);
            String result = HikvisionHttpClient.doPost("https://open.ys7.com/api/lapp/camera/list", paramsMap);
            log.info(result);
            JSONArray array = HIkvisionJSONUtil.getMainDataArray(result);
            if (array.size() == 0) {
                break;
            } else {
                out:
                for (Object o : array) {
                    JSONObject jobject = (JSONObject) o;
                    for (ThirdHikvisionCamera device : all) {
                        if (device.getDeviceSerial().equals(jobject.getString("deviceSerial"))) {
                            continue out;
                        }
                    }
                    ThirdHikvisionCamera device = new ThirdHikvisionCamera();
                    cameraService.save(device);
                }
            }
        }
        return dataResult;
    }


    @RequestMapping(value = "list")
    @ResponseBody
    public List<ThirdHikvisionCamera> list(HttpServletRequest request) {
        List<PropertyFilter> list = PropertyFilter.buildFromHttpRequest(request);
        List<ThirdHikvisionCamera> all = cameraService.listByAttribute(list);
        return all;

    }


    private Map<String, Object> getTokenMap() {
        Map map = new HashMap();
        map.put("accessToken", HikvisionToken.getInstance().getToken());
        return map;
    }


}
