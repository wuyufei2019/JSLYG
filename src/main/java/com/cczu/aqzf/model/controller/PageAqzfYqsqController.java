package com.cczu.aqzf.model.controller;

import com.cczu.aqzf.model.entity.AQZF_DelayApplyEntity;
import com.cczu.aqzf.model.entity.AQZF_ReformEntity;
import com.cczu.aqzf.model.service.AqzfDelayApplyService;
import com.cczu.aqzf.model.service.AqzfZlzgService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Random;


/**
 * 责令整改Controller
 *
 * @author YZH
 */
@Controller
@RequestMapping("aqzf/yqsq")
public class PageAqzfYqsqController extends BaseController {

    @Autowired
    private AqzfDelayApplyService applyService;
    @Autowired
    private AqzfZlzgService zlzgService;


    /**
     * 列表显示页面
     *
     * @param model
     */
    @RequestMapping(value = "index")
    public String index(Model model) {
        model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
        return "aqzf/yqsq/index";
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
        return getEasyUIData(applyService.listApplyMap(page, getAuthorityMap()));

    }


    /**
     * 添加页面跳转
     *
     * @param model id 记录id
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(@RequestParam("id1") Long id1, Model model) {
        AQZF_DelayApplyEntity apply = new AQZF_DelayApplyEntity();
        //根据检查记录id查找检查记录
        Map<String, Object> map = zlzgService.findInforById(id1);
        apply.setUnitname(map.get("qyname").toString());
        apply.setNumber(map.get("m0").toString());
        apply.setID1(id1);
        apply.setQyid(Long.valueOf(map.get("qyid").toString()));
        apply.setOldTime((Timestamp) map.get("m3"));
        model.addAttribute("entity", apply);
        model.addAttribute("action", "create");
        return "aqzf/yqsq/form";
    }

    /**
     * 添加页面跳转
     *
     * @param model id 记录id
     */
    @RequestMapping(value = "review/{id}", method = RequestMethod.GET)
    public String review(@PathVariable("id") Long id, Model model) {
        AQZF_DelayApplyEntity apply = applyService.getById1(id);
        model.addAttribute("entity", apply);
        model.addAttribute("action", "review");
        return "aqzf/yqsq/review";
    }

    /**
     * 添加责令整改信息
     *
     * @param
     */
    @RequestMapping(value = "create")
    @ResponseBody
    public String create(AQZF_DelayApplyEntity entity) {
        String datasuccess = "success";
        setBaseInfo(entity);
        try {
            AQZF_ReformEntity reform = zlzgService.findById(entity.getID1());
            reform.setM11("1");
            reform.setM3(entity.getM1());
            zlzgService.updateInfo(reform);
            applyService.save(entity);
        } catch (Exception e) {
            datasuccess = "error";
            e.printStackTrace();
        }
        return datasuccess;

    }

    /**
     * 修改页面跳转
     *
     * @param id,model
     */
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") Long id, Model model) {
        AQZF_DelayApplyEntity apply = applyService.get(id);
        //返回页面
        model.addAttribute("action", "update");
        model.addAttribute("entity", apply);
        return "aqzf/yqsq/form";
    }

    /**
     * 修改责令整改信息
     *
     * @param request,model
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public String update(AQZF_DelayApplyEntity entity) {
        String datasuccess = "success";
        entity.setS2(DateUtils.getSysTimestamp());
        applyService.save(entity);
        //返回结果
        return datasuccess;
    }

    /**
     * 审核责令整改信息
     *
     * @param request,model
     */
    @RequestMapping(value = "review")
    @ResponseBody
    public String review(AQZF_DelayApplyEntity entity) {
        String datasuccess = "success";
        entity.setS2(DateUtils.getSysTimestamp());
        applyService.save(entity);
        AQZF_ReformEntity byId = zlzgService.findById(entity.getID1());
        byId.setM11(entity.getM6());
        zlzgService.updateInfo(byId);
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
            applyService.delete(Long.valueOf(arrids[i]));
        }
        return datasuccess;
    }


    /**
     * 导出责令限期整改指令书word
     */
    @RequestMapping(value = "exportword/{id}")
    @ResponseBody
    public String getAjWord(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = null;
        //设置导出的文件名
        String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
        //设置模板路径
        String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
        WordUtil.ireportWord(map, "yqsqzls.ftl", filePath, filename, request);
        return "/download/" + filename;
    }


    /**
     * 修改页面跳转
     *
     * @param id,model
     */
    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") Long id, Model model) {
        AQZF_DelayApplyEntity apply = applyService.get(id);
        //返回页面
        model.addAttribute("entity", apply);
        return "aqzf/yqsq/view";
    }
    /**
     * 修改页面跳转
     *
     * @param id,model
     */
    @RequestMapping(value = "view2/{id}", method = RequestMethod.GET)
    public String view2(@PathVariable("id") Long id, Model model) {
        AQZF_DelayApplyEntity apply = applyService.getById1(id);
        model.addAttribute("entity", apply);
        return "aqzf/yqsq/view";
    }

}
