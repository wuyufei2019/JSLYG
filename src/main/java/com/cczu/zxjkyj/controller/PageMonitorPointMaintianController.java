package com.cczu.zxjkyj.controller;

import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.zxjkyj.entity.Monitor_PointMaintainEntity;
import com.cczu.zxjkyj.service.MonitorPointMaintainService;
import com.google.common.collect.Maps;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 在线监控预警设置 监测点数据维护
 *
 * @author jason
 */
@Controller
@RequestMapping("zxjkyj/points/maintian")
public class PageMonitorPointMaintianController extends BaseController {
    @Autowired
    private MonitorPointMaintainService maintainService;

    /**
     * 物料实时数据页面跳转
     */
    @RequiresPermissions("zxjkyj:pointsetting:view")
    @RequestMapping(value = "index")
    public String index() {
        return "zxjkyj/points/maintain/index";
    }


    /**
     * 多企业datalist
     *
     * @param request
     */
    @RequiresPermissions("zxjkyj:pointsetting:view")
    @RequestMapping(value = "list")
    @ResponseBody
    public Map<String, Object> ajgetData(HttpServletRequest request) {

        Page<Monitor_PointMaintainEntity> page = getPage(request);
        List<PropertyFilter> list = PropertyFilter.buildFromHttpRequest(request);
        maintainService.search(page, list);
        return getEasyUIData(page);

    }


    /**
     * 添加页面跳转
     *
     * @param model
     */
    @RequiresPermissions("zxjkyj:pointsetting:add")
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("action", "create");
        model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
        return "zxjkyj/points/maintain/form";
    }

    /**
     * 添加云视讯信息
     *
     * @param
     */
    @RequiresPermissions("zxjkyj:pointsetting:add")
    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public String create(@Valid Monitor_PointMaintainEntity bw) {
        String datasuccess = "success";
        maintainService.save(bw);
        // 返回结果
        return datasuccess;

    }


    /**
     * 修改页面跳转
     *
     * @param id,model
     */
    @RequiresPermissions("zxjkyj:pointsetting:update")
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") Long id, Model model) {
        //查询选择的车间信息
        Monitor_PointMaintainEntity ee = maintainService.get(id);
        model.addAttribute("entity", ee);
        model.addAttribute("action", "update");
        return "zxjkyj/points/maintain/form";
    }

    /**
     * 修改云视讯信息
     *
     * @param
     */
    @RequiresPermissions("zxjkyj:pointsetting:update")
    @RequestMapping(value = "update")
    @ResponseBody
    public String update(Monitor_PointMaintainEntity bw, Model model) {
        String datasuccess = "success";
        maintainService.save(bw);
        // 返回结果
        return datasuccess;
    }

    /**
     * 删除云视讯信息
     *
     * @param
     * @param
     * @throws
     */
    @RequiresPermissions("zxjkyj:pointsetting:delete")
    @RequestMapping(value = "delete/{ids}")
    @ResponseBody
    public String delete(@PathVariable("ids") String ids) {
        String datasuccess = "删除成功";
        // 可以批量删除
        String[] arrids = ids.split(",");
        for (int i = 0; i < arrids.length; i++) {
            maintainService.delete(Long.parseLong(arrids[i]));
        }
        return datasuccess;
    }

    /**
     * 查看页面跳转
     *
     * @param id,model
     */
    @RequiresPermissions("zxjkyj:pointsetting:view")
    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") Integer id, Model model) {
        long id1 = id;
        Monitor_PointMaintainEntity ee = maintainService.get(id1);
        model.addAttribute("entity", ee);
        return "zxjkyj/setting/bismodbus/view";
    }

    @RequestMapping(value = "dealReport", method = RequestMethod.POST)
    @ResponseBody
    public String dealReport(@RequestParam("report") String report,
                             @RequestParam("id") Long id, Model model) {
        Monitor_PointMaintainEntity ee = maintainService.get(id);
        ee.setNoreport(StringUtils.equals(report, "1") ? 1 : null);
        maintainService.save(ee);
        return "success";
    }


    /**
     * 查看页面跳转
     * zxjkyj:pointsetting:add
     *
     * @param id,model
     */

    @RequiresPermissions("zxjkyj:pointsetting:view")
    @RequestMapping(value = "json")
    public String view(Model model) {
        List<Monitor_PointMaintainEntity> list = maintainService.getAll();
        List<Map> result = new ArrayList<>();
        for (Monitor_PointMaintainEntity modbus : list) {
            HashMap<Object, Object> hashMap = Maps.newHashMap();
            //hashMap.put("text", modbus.getQyname() + modbus.getIp());
            hashMap.put("value", modbus.getID());
        }
        return "zxjkyj/points/maintain/view";
    }

    @RequestMapping(value = "qyjson")
    @ResponseBody
    public List<Map> getQyJson() {
        Map<String, Object> authorityMap = getAuthorityMap();
        List<Map> list = maintainService.listQy(authorityMap);
        return list;
    }


}
