package com.cczu.model.controller;

import com.cczu.model.entity.YHPC_CheckTeamSetting;
import com.cczu.model.service.YhpcTeamSettingService;
import com.cczu.sys.comm.controller.BaseController;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
 * 班组设置
 * @author YZH
 */
@Controller
@RequestMapping("yhpc/teamsetting")
public class PageYhpcTeamSettingController extends BaseController {

    @Autowired
    private YhpcTeamSettingService settingService;

    /**
     * 列表显示页面
     *
     * @param model
     */
    @RequestMapping(value = "index")
    public String index(Model model) {
        List<YHPC_CheckTeamSetting> list = settingService.listAll();
        if (list.size() == 0) {
            model.addAttribute("action", "create");
            return "/yhpc/teamsetting/form";
        } else {
            return "redirect: /yhpc/teamsetting/view";
        }
    }



    /**
     * 添加
     *
     * @param request
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(HttpServletRequest request) {
        String[] depts = request.getParameterValues("dept");
        String[] dates = request.getParameterValues("date");
        String[] worktimes = request.getParameterValues("worktime");
        settingService.saveInfo(depts, dates, worktimes);
        return "success";
    }
    /**
     * 重新添加
     * @param
     */
    @RequestMapping(value = "recreate", method = RequestMethod.GET)
    public String reCreate() {
        settingService.deleteAll();
        return "redirect: /yhpc/teamsetting/index";
    }


    /**
     * 查看页面跳转
     *
     * @param model
     */
    @RequestMapping(value = "view", method = RequestMethod.GET)
    public String view(Model model) {
        List<Map<String, Object>> list = settingService.listAllMap();
        List<String> deptList = Lists.newArrayList();
        for (Map<String, Object> setting : list) {
            if(!deptList.contains(setting.get("m1"))){
                deptList.add(setting.get("m1")+"");
            }
        }
        model.addAttribute("depts", deptList);
        model.addAttribute("settings", list);
        return "/yhpc/teamsetting/view";
    }

}
