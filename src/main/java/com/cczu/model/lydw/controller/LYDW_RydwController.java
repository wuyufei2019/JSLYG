package com.cczu.model.lydw.controller;

import com.cczu.model.lydw.service.LYDW_RydwService;
import com.cczu.model.mbgl.entity.Target_SafetyPromiseAgreement;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.ShiroRealm;
import com.cczu.sys.system.utils.UserUtil;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * 蓝牙定位----人员定位
 */
@Controller
@RequestMapping("lydw/rydw")
public class LYDW_RydwController extends BaseController {

	@Autowired
	private LYDW_RydwService lydw_rydwService;

	/**
     * 首页
     * @param model
     */
    @RequestMapping(value="index")
    public String index(Model model) {
        return "lydw/rydw/index";
    }

    /**
     * 人员位置listjson
     * @param request
     */
    @RequestMapping(value="listjson")
    @ResponseBody
    public String getRywzList(HttpServletRequest request) {
    	Map<String, Object> map = new HashMap<>();
    	map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
        return lydw_rydwService.rydwData(map);
    }

    /**
     * 根据部门统计在线人数
     * @param request
     */
    @RequestMapping(value="bmtotaljson")
    @ResponseBody
    public String getTotalByDepartment(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
        return lydw_rydwService.totalOnlinePoeple(map);
    }

    /**
     * 轨迹回放首页
     * @param model
     * @return
     */
    @RequestMapping(value="gjcx/index")
    public String gjindex(Model model) {
        model.addAttribute("qyid", UserUtil.getCurrentShiroUser().getQyid());
        return "lydw/gjcx/index";
    }

    /**
     * 查询历史轨迹
     * @param request
     */
    @RequestMapping(value="hisgjlist")
    @ResponseBody
    public String getHisGjList(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("starttime",request.getParameter("starttime"));
        map.put("finishtime",request.getParameter("finishtime"));
        map.put("ygid",request.getParameter("ygid"));
        map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
        return lydw_rydwService.getHisGjList(map);
    }

    /**
     * 人员追踪
     * @param model
     * @return
     */
    @RequestMapping(value="ryzz")
    public String track(Model model) {

        return "lydw/ryzz/index";
    }
    /**
     * 人员追踪位置刷新
     * @param request
     */
    @RequestMapping(value="realtimezb")
    @ResponseBody
    public String getRealTimeList(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        String ygid=request.getParameter("ygid");
        if(ygid==null||ygid.equals(""))
            return "";
        else{
            map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
            map.put("ygid", ygid);
        }
        return lydw_rydwService.rydwData(map);
    }

    /**
     * 查询绑定工卡的人员
     * @param request
     */
    @RequestMapping(value="yglist")
    @ResponseBody
    public String getYgList(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
        return lydw_rydwService.getYGList(map);
    }
}
