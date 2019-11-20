package com.cczu.aqzf.model.controller;

import com.cczu.aqzf.model.entity.AQZF_HiddenDealEntity;
import com.cczu.aqzf.model.entity.AQZF_SafetyCheckContentEntity;
import com.cczu.aqzf.model.entity.AQZF_SafetyCheckRecordEntity;
import com.cczu.aqzf.model.service.AqzfHiddenDealService;
import com.cczu.aqzf.model.service.AqzfJcjlService;
import com.cczu.aqzf.model.service.AqzfJcnrService;
import com.cczu.aqzf.model.service.AqzfZlzgService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ServletUtils;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.service.ShiroRealm;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Random;


/**
 * 责令整改Controller
 *
 * @author YZH
 */
@Controller
@RequestMapping("aqzf/yhzz")
public class PageAqzfYhzzController extends BaseController {

    @Autowired
    private AqzfHiddenDealService hiddenDealService;
    @Autowired
    private AqzfJcjlService jcjlService;
    @Autowired
    private AqzfJcnrService aqzfJcnrService;


    /**
     * 列表显示页面
     *
     * @param model
     */
    @RequestMapping(value = "index")
    public String index(Model model) {
        model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
        return "aqzf/yhzz/index";
    }

    /**
     * list页面
     *
     * @param request
     */
    @RequiresPermissions("aqzf:yhzz:view")
    @RequestMapping(value = "list")
    @ResponseBody
    public Map<String, Object> getData(HttpServletRequest request) {
        Page<Map> page = getPage(request);
        Map<String, Object> params = ServletUtils.getParametersStartingWith(request, "filter_");
        params.putAll(getAuthorityMap());
        hiddenDealService.pageMap(page,params);
        return getEasyUIData(page);

    }


    /**
     * 添加页面跳转
     *
     * @param model id1 检查记录id
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(@RequestParam("id1") Long id1, Model model) {
        Map<String, Object> record = jcjlService.findById(id1);
        //根据检查记录id查找有问题的
        List<Map<String, Object>> hiddens = aqzfJcnrService.listHiddenMap(id1);
        model.addAttribute("record", record);
        model.addAttribute("hiddens", hiddens);
        model.addAttribute("action", "create");
        return "aqzf/yhzz/form";
    }


    /**
     * 添加责令整改信息
     *
     * @param
     */
    @RequestMapping(value = "create")
    @ResponseBody
    public String create(HttpServletRequest request) {
        String datasuccess = "success";
        Long recordId = Long.valueOf(request.getParameter("ID1"));
        String[] contentIds = request.getParameterValues("ID2");
        String[] person1s = request.getParameterValues("person1");
        String[] person2s = request.getParameterValues("person2");
        String[] times = request.getParameterValues("time");
        String[] pics = request.getParameterValues("pic");
        String[] names = request.getParameterValues("name");
        int length = contentIds.length;
        Assert.isTrue(person1s.length==length && person2s.length==length
        && times.length==length &&pics.length==length && names.length==length);
        //插入隐患整治表
        try {
            for (int i = 0; i < contentIds.length; i++) {
                AQZF_HiddenDealEntity hidden = new AQZF_HiddenDealEntity();
                hidden.setID1(recordId);
                hidden.setID2(Long.valueOf(contentIds[i]));
                hidden.setPerson1(person1s[i]);
                hidden.setPerson2(person2s[i]);
                hidden.setTime(Integer.valueOf(times[i]));
                hidden.setPic(pics[i]);
                hidden.setName(names[i]);
                setBaseInfo(hidden);
                hiddenDealService.save(hidden);
            }
            //修改 隐患记录 隐患整治填写记录
            AQZF_SafetyCheckRecordEntity rec = jcjlService.findjl(recordId);
            rec.setM17("1");
            jcjlService.updateInfo(rec);
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
    @RequiresPermissions("aqzf:jcjl:update")
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") Long id, Model model) {
        AQZF_HiddenDealEntity apply = hiddenDealService.get(id);
        //返回页面
        model.addAttribute("action", "update");
        model.addAttribute("entity", apply);
        return "aqzf/yhzz/formUpd";
    }

    /**
     * 修改责令整改信息
     *
     * @param request,model
     */
    @RequiresPermissions("aqzf:jcjl:update")
    @RequestMapping(value = "update")
    @ResponseBody
    public String update(AQZF_HiddenDealEntity entity) {
        String datasuccess = "success";
        entity.setS2(DateUtils.getSysTimestamp());
        hiddenDealService.save(entity);
        //返回结果
        return datasuccess;
    }


    /**
     * 删除责令整改信息
     */
    @RequiresPermissions("aqzf:yhzz:delete")
    @RequestMapping(value = "delete/{ids}")
    @ResponseBody
    public String delete(@PathVariable("ids") String ids) {
        String datasuccess = "删除成功";
        //可以批量删除
        String[] arrids = ids.split(",");
        for (int i = 0; i < arrids.length; i++) {
            hiddenDealService.delete(Long.valueOf(arrids[i]));
        }
        return datasuccess;
    }


    /**
     * 导出责令限期整改指令书word
     */
    @RequiresPermissions("aqzf:yhzz:exportword")
    @RequestMapping(value = "exportword/{id}")
    @ResponseBody
    public String getAjWord(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = null;
        //设置导出的文件名
        String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
        //设置模板路径
        String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
        WordUtil.ireportWord(map, "yhzzzls.ftl", filePath, filename, request);
        return "/download/" + filename;
    }


    /**
     * 修改页面跳转
     *
     * @param id,model
     */
    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") Long id, Model model) {

        AQZF_HiddenDealEntity apply = hiddenDealService.get(id);
        Map<String, Object> record = jcjlService.findById(apply.getID1());
        model.addAttribute("record", record);
        model.addAttribute("entity", apply);
        return "aqzf/yhzz/view";
    }
    /**
     * 查看隐患整治页面跳转
     *
     * @param id,model
     */
    @RequestMapping(value = "view2/{id1}", method = RequestMethod.GET)
    public String viewYhzz(@PathVariable("id1") Long id1, Model model) {
        List<Map<String,Object>> hiddens = hiddenDealService.listHiddenMap(id1);
        Map<String, Object> record = jcjlService.findById(id1);
        model.addAttribute("record", record);
        model.addAttribute("hiddens", hiddens);
        return "aqzf/yhzz/view";
    }

}
