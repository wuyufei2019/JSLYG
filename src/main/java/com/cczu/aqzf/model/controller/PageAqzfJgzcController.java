package com.cczu.aqzf.model.controller;

import com.cczu.aqzf.model.entity.AQZF_SafetyCheckRecordEntity;
import com.cczu.aqzf.model.entity.AQZF_SuperviseRectifyEntity;
import com.cczu.aqzf.model.service.AqzfJcjlService;
import com.cczu.aqzf.model.service.AqzfSuperviseRectifyService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ServletUtils;
import com.cczu.sys.system.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * 监管整饬Controller
 *
 * @author YZH
 */
@Controller
@RequestMapping("aqzf/jgzc")
public class PageAqzfJgzcController extends BaseController {

    @Autowired
    private AqzfSuperviseRectifyService rectifyService;
    @Autowired
    private AqzfJcjlService jcjlService;


    /**
     * 列表显示页面
     *
     * @param model
     */
    @RequestMapping(value = "index")
    public String index(Model model) {
        model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
        return "aqzf/jgzc/index";
    }

    /**
     * list页面
     *
     * @param request
     */
    @RequestMapping(value = "list")
    @ResponseBody
    public Map<String, Object> getData(HttpServletRequest request) {
        Page<Map> page = getPage(request);
        Map<String, Object> params = ServletUtils.getParametersStartingWith(request, "filter_");
        params.putAll(getAuthorityMap());
        rectifyService.pageMap(page, params);
        return getEasyUIData(page);

    }


    /**
     * 添加页面跳转
     *
     * @param model id1 检查记录id
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(@RequestParam("id1") Long id1, Model model) {
        model.addAttribute("id1", id1);
        model.addAttribute("action", "create");
        return "aqzf/jgzc/form";
    }


    /**
     * 添加责令整改信息
     *
     * @param
     */
    @RequestMapping(value = "create")
    @ResponseBody
    public String create(AQZF_SuperviseRectifyEntity entity) {
        setBaseInfo(entity);
        rectifyService.save(entity);
        AQZF_SafetyCheckRecordEntity rec = jcjlService.findjl(entity.getID1());
        rec.setM16("1");
        jcjlService.updateInfo(rec);
        //修改 隐患记录 隐患整治填写记录
        return "success";

    }

    /**
     * 修改页面跳转
     *
     * @param id,model
     */
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") Long id, Model model) {
        AQZF_SuperviseRectifyEntity apply = rectifyService.get(id);
        //返回页面
        model.addAttribute("action", "update");
        model.addAttribute("entity", apply);
        return "aqzf/jgzc/form";
    }

    /**
     * 修改责令整改信息
     *
     * @param entity
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public String update(AQZF_SuperviseRectifyEntity entity) {
        String datasuccess = "success";
        entity.setS2(DateUtils.getSysTimestamp());
        rectifyService.save(entity);
        //返回结果
        return datasuccess;
    }


    /**
     * 删除责令整改信息
     */
    @RequestMapping(value = "delete/{ids}")
    @ResponseBody
    public String delete(@PathVariable("ids") String ids) {
        String datasuccess = "删除成功";
        //可以批量删除
        String[] arrids = ids.split(",");
        for (int i = 0; i < arrids.length; i++) {
            rectifyService.delete(Long.valueOf(arrids[i]));
        }
        return datasuccess;
    }


    /**
     * 修改页面跳转
     *
     * @param id,model
     */
    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") Long id, Model model) {
        AQZF_SuperviseRectifyEntity apply = rectifyService.get(id);
        model.addAttribute("entity", apply);
        return "aqzf/jgzc/view";
    }

    @RequestMapping(value = "view2/{id1}", method = RequestMethod.GET)
    public String viewYhzz(@PathVariable("id1") Long id1, Model model) {
        AQZF_SuperviseRectifyEntity apply = rectifyService.getByID1(id1);
        model.addAttribute("entity", apply);
        return "aqzf/jgzc/view";
    }

}
