package com.cczu.zxjkyj.controller;

import com.alibaba.fastjson.JSON;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.zxjkyj.entity.Main_MonitoringHistoryDataEntity;
import com.cczu.zxjkyj.entity.Monitor_PointMaintainEntity;
import com.cczu.zxjkyj.service.MonitorHistoryDataService;
import com.cczu.zxjkyj.service.MonitorPointMaintainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 在线监控预警设置 企业匹配modbus ip
 *
 * @author jason
 */
@Controller
@RequestMapping("zxjkyj/points/value")
public class PageMonitorPointValueController extends BaseController {
    @Autowired
    private MonitorPointMaintainService maintainService;

    @Autowired
    private MonitorHistoryDataService historyDataService;


    /**
     * 物料实时数据页面跳转
     */
    @RequestMapping(value = "index")
    public String index() {
        return "zxjkyj/points/value/index";
    }

    @RequestMapping(value = "index2")
    public String index2() {
        return "zxjkyj/points/value/index2";
    }

    @RequestMapping(value = "index3")
    public String index3() {
        return "zxjkyj/points/value/index3";
    }


    /**
     * 多企业datalist
     *
     * @param request
     */
    @RequestMapping(value = "list")
    @ResponseBody
    public Map<String, Object> ajgetData(HttpServletRequest request) {
        Page<Monitor_PointMaintainEntity> page = getPage(request);
        String qyid = request.getParameter("qyid");
        Map<String, Object> authorityMap = getAuthorityMap();
        if (StringUtils.isNotBlank(qyid)) {
            authorityMap.put("qyid", qyid);
        }
        maintainService.page(page, authorityMap);
        return getEasyUIData(page);
    }

    /**
     * 多企业datalist
     *
     * @param request
     */
    @RequestMapping(value = "hislist")
    @ResponseBody
    public Map<String, Object> historyList(HttpServletRequest request) {
        Page<Map> page = getPage(request);
        Map<String, Object> authorityMap = getAuthorityMap();
        String qyid = request.getParameter("qyid");
        if (StringUtils.isNotBlank(qyid)) {
            authorityMap.put("qyid", qyid);
        }
        String starttime = request.getParameter("starttime");
        String endtime = request.getParameter("endtime");
        String time = DateUtils.getDate();
        if (StringUtils.isBlank(starttime))
            starttime = time + " 00:00:00";
        if (StringUtils.isBlank(endtime))
            endtime = time + " 23:59:59";
        historyDataService.getPageStatistics(page, authorityMap, starttime, endtime);
        return getEasyUIData(page);
    }

    @RequestMapping(value = "statistics")
    public String statistics(HttpServletRequest request, Model model) {
        List<PropertyFilter> list = new ArrayList<>();

        String pointid = request.getParameter("pointId");
        String startTime = request.getParameter("starttime");
        String endTime = request.getParameter("endtime");
        String time = DateUtils.getDate();
        if (StringUtils.isBlank(startTime))
            startTime = time + " 00:00:00";
        if (StringUtils.isBlank(endTime))
            endTime = time + " 23:59:59";
        list.add(new PropertyFilter("EQL_pointid", pointid));
        list.add(new PropertyFilter("GED_updatetime", startTime));
        list.add(new PropertyFilter("LED_updatetime", endTime));
        List<Main_MonitoringHistoryDataEntity> results = historyDataService.search(list);
        model.addAttribute("result", JSON.toJSONString(results));
        return "zxjkyj/points/value/statistics";
    }

}
