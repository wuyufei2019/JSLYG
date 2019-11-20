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
import com.google.common.collect.Maps;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 云视讯-设备设置controller
 *
 * @author jason
 * @date 2017年6月15日
 */
@Controller
@RequestMapping("zxjkyj/hkvision/device-qy")
public class PageHkVideoMapController extends BaseController {

    @Autowired
    private MonitorHkVideoMapsService mapsService;

    @Autowired
    private MonitorHkVideoCameraService cameraService;


    /**
     * 字典显示
     *
     * @param {json}
     */
    @RequestMapping(value = "/jsonlist")
    @ResponseBody
    public String jsonlist() {
        List<Map> all = mapsService.listMap();
        return JSON.toJSONString(all);
    }


    /**
     * 列表显示页面
     *
     * @param
     */
    @RequestMapping(value = "index")
    public String index() {
        return "zxjkyj/hksetting/index";
    }


    @RequestMapping(value = "list")
    @ResponseBody
    public Map<String, Object> ajgetData(HttpServletRequest request) {

        Page<ThirdHikvisionDevice> page = getPage(request);
        List<PropertyFilter> list = PropertyFilter.buildFromHttpRequest(request);
        mapsService.search(page, list);
        return getEasyUIData(page);

    }


    /**
     * 添加页面跳转
     *
     * @param model
     */
    @RequiresPermissions("api:hksetting:add")
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("action", "create");
        model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
        return "zxjkyj/hksetting/form";
    }

    /**
     * 添加页面跳转
     *
     * @param model
     */
    @RequiresPermissions("api:hksetting:update")
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable Long id, Model model) {
        ThirdHikvisionDevice device = mapsService.get(id);
        model.addAttribute("action", "update");
        model.addAttribute("sb", device);
        return "zxjkyj/hksetting/form";
    }

    /**
     * 添加
     *
     * @param
     */
    @RequiresPermissions("api:hksetting:add")
    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public String create(@Valid ThirdHikvisionDevice entity) {
        String result = "success";
        try {
            //接口添加
            mapsService.save(entity);
        } catch (Exception e) {
            result = e.getMessage();
            e.printStackTrace();
        }
        // 返回结果
        return result;

    }


    /**
     * 修改
     *
     * @param
     */
    @RequiresPermissions("api:hksetting:update")
    @RequestMapping(value = "updateName")
    @ResponseBody
    public String updateName(@RequestBody Map<String, Object> map) {
        String datasuccess = "success";
        map.put("accessToken", HikvisionToken.getInstance().getToken());
        try {
            String result = HikvisionHttpClient.doPost("https://open.ys7.com/api/lapp/device/name/update", map);
            if (JSON.parseObject(result).getInteger("code") == 200) {
                mapsService.updateDeviceName(map.get("deviceSerial").toString(), map.get("deviceName").toString());
            }
        } catch (Exception e) {
            datasuccess = "error";
            e.printStackTrace();
        }
        // 返回结果
        return datasuccess;
    }

    /**
     * 修改
     *
     * @param
     */
    @RequiresPermissions("api:hksetting:update")
    @RequestMapping(value = "update")
    @ResponseBody
    public String update(ThirdHikvisionDevice device) {
        String datasuccess = "success";
        mapsService.save(device);
        return datasuccess;
    }

    /**
     * 删除云视讯信息
     *
     * @param
     * @param
     * @throws ParseException
     */
    @RequiresPermissions("api:hksetting:delete")
    @RequestMapping(value = "delete/{ids}")
    @ResponseBody
    public String delete(@PathVariable("ids") String ids) {
        String datasuccess = "删除成功!";
        // 可以批量删除
        String[] arrids = ids.split(",");
        for (int i = 0; i < arrids.length; i++) {
            mapsService.delete(Long.parseLong(arrids[i]));
        }
        return datasuccess;
    }

    /**
     * 查看页面跳转
     *
     * @param id,model
     */
    @RequiresPermissions("api:hksetting:view")
    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") Integer id, Model model) {
        long id1 = id;
        ThirdHikvisionDevice ee = mapsService.get(id1);
        model.addAttribute("entity", ee);
        return "zxjkyj/hksetting/view";
    }

    /**
     * 查看页面跳转
     */
    @RequiresPermissions("api:hksetting:add")
    @RequestMapping(value = "init", method = RequestMethod.GET)
    @ResponseBody
    public String init() {
        Map<String, Object> paramsMap = getTokenMap();
        paramsMap.put("pageSize", 50);
        String result = HikvisionHttpClient.doPost("https://open.ys7.com/api/lapp/device/list", paramsMap);
        log.info(result);
        JSONArray array = HIkvisionJSONUtil.getMainDataArray(result);
        List<ThirdHikvisionDevice> all = mapsService.getAll();
        out:
        for (Object o : array) {
            JSONObject jobject = (JSONObject) o;
            for (ThirdHikvisionDevice device : all) {
                if (device.getDeviceSerial().equals(jobject.getString("deviceSerial"))) {
                    continue out;
                }
            }
            ThirdHikvisionDevice device = new ThirdHikvisionDevice();
            device.setDeviceName(jobject.getString("deviceName"));
            device.setDeviceSerial(jobject.getString("deviceSerial"));
            device.setStatus(jobject.getInteger("status"));
            device.setBind(1);
            mapsService.save(device);
        }

        return "success";
    }


    /**
     * 绑定企业地图页面跳转
     */
    @RequestMapping(value = "bind/{deviceSerial}", method = RequestMethod.GET)
    public String BindIndex(@PathVariable("deviceSerial") String deviceSerial, Model model) {
        model.addAttribute("deviceSerial", deviceSerial);
        return "zxjkyj/hksetting/camerachoose";
    }

    /**
     */
    @RequestMapping(value = "bind/{deviceSerials}", method = RequestMethod.POST)
    public String BindIndexs(@PathVariable("deviceSerials") String deviceSerials,
                             @RequestParam String nvrDeviceSerial, Model model) {
        model.addAttribute("deviceSerial", deviceSerials);
        return "zxjkyj/hksetting/camerachoose";
    }

    /**
     * nvr绑定摄像头列表
     */
    @RequestMapping(value = "camera/index/{deviceSerial}")
    public String cameraList(@PathVariable("deviceSerial") String deviceSerial, Model model) {
        model.addAttribute("deviceSerial", deviceSerial);
        return "zxjkyj/hksetting/cameraIndex";
    }
    /**
     * nvr直播列表
     */
    @RequestMapping(value = "live/index/{deviceSerial}")
    public String liveList(@PathVariable("deviceSerial") String deviceSerial, Model model) {
        model.addAttribute("deviceSerial", deviceSerial);
        return "zxjkyj/hksetting/liveIndex";
    }

    /**
     * nvr绑定设备list
     */
    @RequestMapping(value = "camera/list")
    @ResponseBody
    public Map<String, Object> cameraList(HttpServletRequest request) {
        String deviceSerial = request.getParameter("deviceSerial");
        Map<String, Object> paramsMap = getTokenMap();
        paramsMap.put("deviceSerial", deviceSerial);
        String result = HikvisionHttpClient.doPost("https://open.ys7.com/api/lapp/device/camera/list", paramsMap);
        JSONArray array = HIkvisionJSONUtil.getMainDataArray(result);
        List<ThirdHikvisionCamera> all = cameraService.getAll();
        for (Object o : array) {
            JSONObject object = (JSONObject) o;
            for (ThirdHikvisionCamera camera : all) {
                if (camera.getDeviceSerial().equals(object.getString("deviceSerial")) &&
                        camera.getChannelNo() == object.getInteger("channelNo")) {
                    object.put("open", true);
                    break;
                }
            }
        }

        Map<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("total", array.size());
        resultMap.put("rows", array);
        return resultMap;
    }
    /**
     * nvr绑定设备list
     */
    @RequestMapping(value = "live/list")
    @ResponseBody
    public Map<String, Object> liveList(HttpServletRequest request) {
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromHttpRequest(request);
        Page<ThirdHikvisionCamera> page = getPage(request);
        cameraService.search(page,propertyFilters);
        return getEasyUIData(page);
    }

    /**
     * 开通直播
     */
    @RequestMapping(value = "open")
    @ResponseBody
    public String openCamera(@RequestParam("deviceSerial") String deviceSerial,
                             @RequestParam("channelNo") String channelNo,
                             @RequestParam(value = "channelName",required=false) String channelName) {

        Map<String, Object> paramsMap = getTokenMap();
        paramsMap.put("source", deviceSerial + ":" + channelNo + ",");
        String result = HikvisionHttpClient.doPost("https://open.ys7.com/api/lapp/live/video/open", paramsMap);
        JSONArray array = HIkvisionJSONUtil.getMainDataArray(result);
        JSONObject o = (JSONObject) array.get(0);
        if (o.getString("ret").equals("200")) {
            //获取直播地址
            String live = HikvisionHttpClient.doPost("https://open.ys7.com/api/lapp/live/address/get", paramsMap);
            JSONObject liveo = (JSONObject) HIkvisionJSONUtil.getMainDataArray(live).get(0);
            if(liveo.getString("ret").equals("200")){
                ThirdHikvisionCamera camera = new ThirdHikvisionCamera(o.getString("deviceSerial"),
                        o.getInteger("channelNo"));
                camera.setDeviceName(liveo.getString("deviceName"));
                camera.setChannelName(channelName);
                camera.setHls(liveo.getString("hls"));
                camera.setHlsHd(liveo.getString("hlsHd"));
                camera.setRtmp(liveo.getString("rtmp"));
                camera.setRtmpHd(liveo.getString("rtmpHd"));
                cameraService.save(camera);
            }
        }
        return o.getString("desc");
    }

    /**
     * 关闭直播
     */
    @RequestMapping(value = "close")
    @ResponseBody
    public String closeCamera(@RequestParam("deviceSerial") String deviceSerial,
                              @RequestParam("channelNo") Integer channelNo) {
        Map<String, Object> paramsMap = getTokenMap();
        paramsMap.put("source", deviceSerial + ":" + channelNo + ",");
        String result = HikvisionHttpClient.doPost("https://open.ys7.com/api/lapp/live/video/close", paramsMap);
        JSONArray array = HIkvisionJSONUtil.getMainDataArray(result);
        JSONObject o = (JSONObject) array.get(0);
        if (o.getString("ret").equals("200")) {
            //关闭成功后删除本地数据
            cameraService.deleteByDeviceSerialChannelNo(deviceSerial, channelNo);
        }
        return o.getString("desc");
    }

    private Map<String, Object> getTokenMap() {
        Map map = new HashMap();
        map.put("accessToken", HikvisionToken.getInstance().getToken());
        return map;
    }


}
