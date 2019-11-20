package com.cczu.zxjkyj.controller;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.zxjkyj.entity.Main_MonitoringAlarmDataEntity;
import com.cczu.zxjkyj.entity.Monitor_PointMaintainEntity;
import com.cczu.zxjkyj.service.MonitorAlarmDataService;
import com.cczu.zxjkyj.service.MonitorPointMaintainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
 * 监控报警controller
 *
 * @author jason
 */
@Controller
@RequestMapping("zxjkyj/alarm")
public class PageMonitorAlarmController extends BaseController {

    @Autowired
    private MonitorAlarmDataService service;
    @Autowired
    private IBisQyjbxxService qyjbxxService;
    @Autowired
    private MonitorPointMaintainService maintainService;

    /**
     * 储罐报警默认页面
     */
    @RequestMapping(value = "index")
    public String cgindex(Model model) {
        model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
        return "zxjkyj/points/alarm/index";
    }

    /**
     * 储罐报警list页面(未处理)
     */
    @RequestMapping(value = "list")
    @ResponseBody
    public Map<String, Object> getData(HttpServletRequest request) {
        Page<Map> page = getPage(request);
        String qyid = request.getParameter("qyid");
        String status = request.getParameter("status");
        Map<String, Object> authorityMap = getAuthorityMap();
        if (StringUtils.isNotBlank(qyid)) {
            authorityMap.put("qyid", qyid);
        }
        service.getPage(page, authorityMap, status);
        return getEasyUIData(page);
    }


    /**
     * 修改页面跳转
     *
     * @param id,model
     */
    @RequestMapping(value = "deal/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") Long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("action", "deal");
        return "zxjkyj/alarm/form";
    }

    /**
     * 修改页面跳转
     *
     * @param id,model
     */
    @RequestMapping(value = "deal2/{id}", method = RequestMethod.GET)
    public String viewAndUpd(@PathVariable("id") Long id, Model model) {
        Main_MonitoringAlarmDataEntity alarmEntity = service.get(id);
        model.addAttribute("bj", alarmEntity);
        BIS_EnterpriseEntity infoById = qyjbxxService.findInfoById(alarmEntity.getID1());
        Monitor_PointMaintainEntity maintain = maintainService.get(alarmEntity.getPointid());
        model.addAttribute("action", "deal");
        model.addAttribute("target", maintain.getTargetName());
        model.addAttribute("qyname", infoById.getM1());
        return "zxjkyj/points/alarm/view";
    }

    /**
     * @param id,model
     */
    @RequestMapping(value = "deal", method = RequestMethod.POST)
    @ResponseBody
    public String deal(@RequestParam("id") Long id, @RequestParam("reason") String reason, Model model) {
        String result = "success";
        try {
            service.updateReason(id, reason);
        } catch (Exception e) {
            result = "error";
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 获取一分钟内报警信息
     */
    @RequestMapping(value = "json")
    @ResponseBody
    public List<Map> json() {
        List<Map> alarms = service.listAlarmTwoMin();
        return alarms;
    }

}
