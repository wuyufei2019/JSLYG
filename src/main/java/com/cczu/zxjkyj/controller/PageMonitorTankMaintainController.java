package com.cczu.zxjkyj.controller;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 储罐维护信息设置
 */
@Controller
@RequestMapping("zxjkyj/setting/tank")
public class PageMonitorTankMaintainController {


    /**
     * 默认页面
     */
    @RequestMapping(value = "index")
    public String index(Model model) {

        return "zxjkyj/setting/tank/index";
    }

    /**
     * 添加页面跳转
     *
     * @param
     */

    @RequiresPermissions("zxjkyj:setting:tank:add")
    @RequestMapping(value = "create/{tankId}/{qyid}")
    public String create(@PathVariable("tankId") Long tankId,@PathVariable("qyid") Long qyid,Model model) {
        model.addAttribute("action","create");
        model.addAttribute("tankId",tankId);
        model.addAttribute("qyid",qyid);
        return "zxjkyj/setting/tank/form";
    }



}
