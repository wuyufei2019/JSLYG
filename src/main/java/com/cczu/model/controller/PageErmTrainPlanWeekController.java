package com.cczu.model.controller;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.ERM_EmergencyTrainPlanWeekDetail;
import com.cczu.model.entity.ERM_EmergencyTrainPlanWeekEntity;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.model.service.impl.ErmTrainPlanWeekDetailService;
import com.cczu.model.service.impl.ErmTrainPlanWeekService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.service.ShiroRealm;
import com.cczu.sys.system.utils.UserUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 训练计划controller
 *
 * @author jason
 * @date 2017年7月1日
 */
@Controller
@RequestMapping("erm/train-plan/week")
public class PageErmTrainPlanWeekController extends BaseController {

    @Autowired
    private ErmTrainPlanWeekService trainPlanWeekService;
    @Autowired
    private ErmTrainPlanWeekDetailService trainPlanWeekDetailService;
    @Autowired
    private BisQyjbxxServiceImpl qyjbxxService;

    /**
     * 列表显示页面
     *
     * @param model
     */
    @RequestMapping(value = "index")
    public String index(Model model) {
        model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
        return "erm/plan/week/index";
    }

    /**
     * list页面
     *
     * @param request
     */
    @RequiresPermissions("erm:train-plan:week:view")
    @RequestMapping(value = "list")
    @ResponseBody
    public Map<String, Object> getData(HttpServletRequest request) {
        Page<ERM_EmergencyTrainPlanWeekEntity> page = getPage(request);
        List<PropertyFilter> list = PropertyFilter.buildFromHttpRequest(request);
        list.add(new PropertyFilter("EQL_qyid", UserUtil.getCurrentShiroUser().getQyid() + ""));
        list.add(new PropertyFilter("EQI_S3", "0"));
        trainPlanWeekService.search(page, list);
        return getEasyUIData(page);

    }

    /**
     * list页面
     *
     * @param request
     */
    @RequiresPermissions("erm:train-plan:week:view")
    @RequestMapping(value = "person-list")
    @ResponseBody
    public Map<String, Object> getData2(@RequestParam Long planId) {

        List<ERM_EmergencyTrainPlanWeekDetail> details = trainPlanWeekDetailService.listAllByPlanId(planId);
        List<String> persons = Lists.newArrayList();
        List<Map> resultList = Lists.newArrayList();
        for (ERM_EmergencyTrainPlanWeekDetail detail : details) {
            String persons1 = detail.getPersons();
            if (StringUtils.isNotBlank(persons1)) {
                String[] split = persons1.split(",");
                for (String s : split) {
                    if (!persons.contains(s)) {
                        persons.add(s);
                    }
                }
            }
        }
        for (String person : persons) {
            HashMap<Object, Object> h = Maps.newHashMap();
            h.put("name",person);
            resultList.add(h);
        }

        Map map = new HashMap();
        map.put("total", resultList.size());
        map.put("rows", resultList);
        return map;

    }

    /**
     * 添加页面跳转
     *
     * @param model
     */
    @RequiresPermissions("erm:train-plan:week:add")
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model) {
        ShiroRealm.ShiroUser user = UserUtil.getCurrentShiroUser();
        model.addAttribute("action", "create");
        model.addAttribute("username", user.getName());
        model.addAttribute("qyid", user.getQyid());
        model.addAttribute("usertype", user.getUsertype());
        return "erm/plan/week/form";
    }

    /**
     * 添加避难场所信息
     *
     * @param
     */
    @RequiresPermissions("erm:train-plan:week:add")
    @RequestMapping(value = "create")
    @ResponseBody
    public String create(ERM_EmergencyTrainPlanWeekEntity ee, HttpServletRequest request) {
        String datasuccess = "success";
        setBaseInfo(ee);
        Long qyid = UserUtil.getCurrentShiroUser().getQyid();
        BIS_EnterpriseEntity bis = qyjbxxService.findInfoById(qyid);
        ee.setQyid(qyid);
        ee.setQyName(bis.getM1());
        String[] time = request.getParameterValues("time");
        String[] time1 = request.getParameterValues("time1");
        String[] hours = request.getParameterValues("hours");
        String[] content = request.getParameterValues("content");
        String[] address = request.getParameterValues("address");
        String[] persons = request.getParameterValues("persons");
        String[] duty = request.getParameterValues("duty");
        String[] situation = request.getParameterValues("situation");
        trainPlanWeekService.addInfo(ee, time, time1, hours, content, address, persons, duty, situation);
        // 返回结果
        return datasuccess;
    }

    /**
     * 修改页面跳转
     *
     * @param id ,model
     */
    @RequiresPermissions("erm:train-plan:week:update")
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") Long id, Model model) {
        // 查询选择的避难场所信息
        ERM_EmergencyTrainPlanWeekEntity ee = trainPlanWeekService.get(id);
        model.addAttribute("entity", ee);
        model.addAttribute("action", "update");
        model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
        List<ERM_EmergencyTrainPlanWeekDetail> details = trainPlanWeekDetailService.listAllByPlanId(ee.getID());
        model.addAttribute("details", details);
        return "erm/plan/week/form";
    }

    /**
     * 修改避难场所信息
     *
     * @param request ,model
     */
    @RequiresPermissions("erm:train-plan:week:update")
    @RequestMapping(value = "update")
    @ResponseBody
    public String update(ERM_EmergencyTrainPlanWeekEntity ee, HttpServletRequest request) {
        String datasuccess = "success";
        ee.setS2(DateUtils.getSysTimestamp());
        String[] time = request.getParameterValues("time");
        String[] time1 = request.getParameterValues("time1");
        String[] hours = request.getParameterValues("hours");
        String[] content = request.getParameterValues("content");
        String[] address = request.getParameterValues("address");
        String[] persons = request.getParameterValues("persons");
        String[] duty = request.getParameterValues("duty");
        String[] situation = request.getParameterValues("situation");
        trainPlanWeekService.updateInfo(ee, time, time1, hours, content, address, persons, duty, situation);

        // 返回结果
        return datasuccess;
    }

    /**
     * 删除避难场所信息
     *
     * @throws ParseException
     */
    @RequiresPermissions("erm:train-plan:week:delete")
    @RequestMapping(value = "delete/{ids}")
    @ResponseBody
    public String delete(@PathVariable("ids") String ids) {
        String datasuccess = "删除成功";
        // 可以批量删除
        String[] arrids = ids.split(",");
        for (int i = 0; i < arrids.length; i++) {
            trainPlanWeekService.deleteFake(Long.parseLong(arrids[i]));
        }
        return datasuccess;
    }

    /**
     * 查看页面跳转
     *
     * @param id,model
     */
    @RequiresPermissions("erm:train-plan:week:view")
    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") Long id, Model model) {
        ERM_EmergencyTrainPlanWeekEntity ee = trainPlanWeekService.get(id);
        model.addAttribute("entity", ee);
        List<ERM_EmergencyTrainPlanWeekDetail> details = trainPlanWeekDetailService.listAllByPlanId(ee.getID());
        model.addAttribute("details", details);
        return "erm/plan/week/view";
    }


}
