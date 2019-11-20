package com.cczu.thirdapi.yunshixun.controller;

import com.alibaba.fastjson.JSON;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.thirdapi.yunshixun.entity.ThirdYxsEquipment;
import com.cczu.thirdapi.yunshixun.service.YsxSbszService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 云视讯-设备设置controller
 *
 * @author jason
 * @date 2017年6月15日
 */
@Controller
@RequestMapping("ysx/sbsz")
public class PageYsxSbszController extends BaseController {

    @Autowired
    private YsxSbszService ysxSbszService;


    /**
     * 字典显示
     *
     * @param {json}
     */
    @RequestMapping(value = "/jsonlist")
    @ResponseBody
    public String jsonlist() {
        List<Map> all = ysxSbszService.listMapQyname();
        return JSON.toJSONString(all);
    }


    /**
     * 列表显示页面
     *
     * @param
     */
    @RequestMapping(value = "index")
    public String index() {
        return "ysx/sbsz/ajindex";
    }

    /**
     * 多企业datalist
     *
     * @param request
     */
    @RequestMapping(value = "ajlist")
    @ResponseBody
    public Map<String, Object> ajgetData(HttpServletRequest request) {

        Page<ThirdYxsEquipment> page = getPage(request);
        List<PropertyFilter> list = PropertyFilter.buildFromHttpRequest(request);
        ysxSbszService.search(page, list);
        return getEasyUIData(page);

    }


    /**
     * 添加页面跳转
     *
     * @param model
     */
    @RequiresPermissions("ysx:sbsz:add")
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("action", "create");
        model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
        return "ysx/sbsz/form";
    }

    /**
     * 添加云视讯信息
     *
     * @param
     */
    @RequiresPermissions("ysx:sbsz:add")
    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public String create(@Valid ThirdYxsEquipment bw) {
        String datasuccess = "success";
        ysxSbszService.save(bw);
        // 返回结果
        return datasuccess;

    }


    /**
     * 修改页面跳转
     *
     * @param id,model
     */
    @RequiresPermissions("ysx:sbsz:update")
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") Integer id, Model model) {
        //查询选择的车间信息

        long id1 = id;
        ThirdYxsEquipment ee = ysxSbszService.get(id1);
        model.addAttribute("sb", ee);
        model.addAttribute("action", "update");
        return "ysx/sbsz/form";
    }

    /**
     * 修改云视讯信息
     *
     * @param
     */
    @RequiresPermissions("ysx:sbsz:update")
    @RequestMapping(value = "update")
    @ResponseBody
    public String update(ThirdYxsEquipment bw, Model model) {
        String datasuccess = "success";
        ysxSbszService.save(bw);
        // 返回结果
        return datasuccess;
    }

    /**
     * 删除云视讯信息
     *
     * @param
     * @param
     * @throws ParseException
     */
    @RequiresPermissions("ysx:sbsz:delete")
    @RequestMapping(value = "delete/{ids}")
    @ResponseBody
    public String delete(@PathVariable("ids") String ids) {
        String datasuccess = "删除成功";
        // 可以批量删除
        String[] arrids = ids.split(",");
        for (int i = 0; i < arrids.length; i++) {
            ysxSbszService.delete(Long.parseLong(arrids[i]));
        }
        return datasuccess;
    }

    /**
     * 查看页面跳转
     *
     * @param id,model
     */
    @RequiresPermissions("ysx:sbsz:view")
    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") Integer id, Model model) {
        long id1 = id;
        ThirdYxsEquipment ee = ysxSbszService.get(id1);
        model.addAttribute("entity", ee);
        return "ysx/sbsz/view";
    }

}
