package com.cczu.thirdapi.hikvision.controller;


import com.cczu.model.service.TsVideoService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;
import com.cczu.sys.system.service.ShiroRealm;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.thirdapi.hikvision.HikvisionToken;
import com.cczu.thirdapi.hikvision.entity.ThirdHikvisionCamera;
import com.cczu.thirdapi.hikvision.entity.ThirdHikvisionDevice;
import com.cczu.thirdapi.hikvision.service.MonitorHkVideoCameraService;
import com.cczu.thirdapi.hikvision.service.MonitorHkVideoMapsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 在线监控预警---企业视屏
 *
 * @author jason
 * @date 2017年9月6日
 */
@Controller
@RequestMapping("zxjkyj/hikvision")
public class PageMonitorHkVideoController extends BaseController {
    @Autowired
    private MonitorHkVideoCameraService cameraService;
    @Autowired
    private MonitorHkVideoMapsService mapsService;

    @Autowired
    TsVideoService tsVideoService;

    /**
     * 默认页面
     */
    @RequestMapping(value = "index")
    public String index(HttpServletRequest request, Model model) {

        ShiroRealm.ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
        Map<String, Object> mapData = new HashMap<String, Object>();
        //根据企业名称查询
        String qyname = request.getParameter("qyname");
        if (qyname != null) {
            mapData.put("qyname", qyname);
            model.addAttribute("qyname", qyname);
        }

        //企业查看自己的视频监控信息
        if (!"1".equals(sessionuser.getUsertype())) {
            if (!"9".equals(sessionuser.getUsertype())) {
                mapData.put("xzqy", sessionuser.getXzqy());
            }
            List<Map<String, Object>> list = tsVideoService.findQyList(mapData);
            model.addAttribute("qylist", list);
            List<ThirdHikvisionDevice> devices = mapsService.listByQyname(getAuthorityMap());
            model.addAttribute("nvrList", devices);
        }
        return "zxjkyj/hkmonitor/index";

    }

    /**
     * 查看企业视频页面跳转
     *
     * @param deviceId
     * @param model
     * @return
     */
    @RequestMapping(value = "show")
    public String showQYSP(HttpServletRequest request, Model model) {
        Page<ThirdHikvisionCamera> page = getPage(request);
        List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
        cameraService.search(page, filters);
        model.addAttribute("page", page);
        model.addAttribute("deviceSerial", request.getParameter("filter_EQS_deviceSerial"));
        return "zxjkyj/hkmonitor/show";
    }

    private Map<String, Object> getTokenMap() {
        Map map = new HashMap();
        map.put("accessToken", HikvisionToken.getInstance().getToken());
        return map;
    }


}

