package com.cczu.model.controller;

import com.cczu.model.entity.ERM_EmergencyTrainPlanVideoEntity;
import com.cczu.model.service.impl.ErmTrainPlanVideoService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.service.ShiroRealm;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**

 */
@Controller
@RequestMapping("erm/train-plan/video")
public class PageErmTrainPlanVideoController extends BaseController {

    @Autowired
    private  ErmTrainPlanVideoService ermTrainPlanVideoService;

    /**
     * 列表显示页面
     * @param model
     */
    @RequestMapping(value="index")
    public String index(Model model, HttpServletRequest request) {
        ShiroRealm.ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
        Map<String, Object> map = new HashMap<>();
        map.put("m1", request.getParameter("m1"));
        String currentPageStr = request.getParameter("pagenum"); //从网页上获取跳转的页数
        int currentPage = 1;  //默认显示第一页
        if(currentPageStr!=null){
            currentPage = StringUtils.toInteger(currentPageStr);
        }
        Page page = ermTrainPlanVideoService.findPage(currentPage,map);
        model.addAttribute("page",page);

        List<Map<String, Object>> list = page.getList();
        model.addAttribute("list", list);
        model.addAttribute("m1", request.getParameter("m1"));
        return "erm/plan/video/index";
    }

    /**
     * 添加页面跳转
     * @param model
     */
    @RequestMapping(value = "create" , method = RequestMethod.GET)
    public String create(Model model, HttpServletRequest request) {
        model.addAttribute("action", "create");
        return "erm/plan/video/form";
    }

    /**
     * 添加信息
     * @param request,model
     * @throws Exception
     *
     */
    @RequestMapping(value = "create" , method = RequestMethod.POST)
    @ResponseBody
    public String create(ERM_EmergencyTrainPlanVideoEntity entity, Model model, HttpServletRequest request) throws Exception {
        String datasuccess="success";
        ShiroRealm.ShiroUser user = UserUtil.getCurrentShiroUser();
        entity.setQyid(user.getQyid());
        Timestamp t = DateUtils.getSysTimestamp();
        entity.setS1(t);
        entity.setS2(t);
        entity.setS3(0);
        //返回结果
        ermTrainPlanVideoService.save(entity);
        return datasuccess;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "delete/{id}")
    @ResponseBody
    public String delete(@PathVariable("id") Long id) {
        String datasuccess="删除成功";
        //删除信息
        ermTrainPlanVideoService.deleteInfoById(id);
        return datasuccess;
    }

}