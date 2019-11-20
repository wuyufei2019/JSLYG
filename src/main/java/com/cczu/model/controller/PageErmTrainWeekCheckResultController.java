package com.cczu.model.controller;

import com.cczu.model.entity.ERM_EmergencyTrainPlanWeekEntity;
import com.cczu.model.entity.ERM_EmergencyTrainWeekCheckResult;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.model.service.impl.ErmTrainWeekCheckResultService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;
import com.cczu.sys.comm.utils.DateUtils;
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
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 训练计划controller
 *
 * @author jason
 * @date 2017年7月1日
 */
@Controller
@RequestMapping("erm/train/check-result")
public class PageErmTrainWeekCheckResultController extends BaseController {

    @Autowired
    private ErmTrainWeekCheckResultService checkResultService;


    /**
     * 列表显示页面
     *
     * @param model
     */
    @RequestMapping(value = "index/{planId}")
    public String index(@PathVariable Long planId, Model model) {
        model.addAttribute("planId",planId);
        model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
        return "erm/plan/week/check/index";
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
        Page<ERM_EmergencyTrainWeekCheckResult> page = getPage(request);
        List<PropertyFilter> list = PropertyFilter.buildFromHttpRequest(request);
        list.add(new PropertyFilter("EQI_S3", "0"));
        checkResultService.search(page, list);
        return getEasyUIData(page);
    }

    /**
     * 添加页面跳转
     *
     * @param model
     */
    @RequiresPermissions("erm:train-plan:week:add")
    @RequestMapping(value = "create/{planId}", method = RequestMethod.GET)
    public String create(@PathVariable Long planId, Model model) {
        ShiroRealm.ShiroUser user = UserUtil.getCurrentShiroUser();
        model.addAttribute("action", "create");
        model.addAttribute("username", user.getName());
        model.addAttribute("planId", planId);
        model.addAttribute("usertype", user.getUsertype());
        return "erm/plan/week/check/form";
    }

    /**
     * 添加避难场所信息
     *
     * @param
     */
    @RequiresPermissions("erm:train-plan:week:add")
    @RequestMapping(value = "create")
    @ResponseBody
    public String create(ERM_EmergencyTrainWeekCheckResult ee, HttpServletRequest request) {
        String datasuccess = "success";
        setBaseInfo(ee);
        Long qyid = UserUtil.getCurrentShiroUser().getQyid();
        checkResultService.save(ee);
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
        ERM_EmergencyTrainWeekCheckResult ee = checkResultService.get(id);
        model.addAttribute("entity", ee);
        model.addAttribute("action", "update");
        model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
        List<ERM_EmergencyTrainWeekCheckResult> details = checkResultService.listAllByPlanId(ee.getID());
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
    public String update(ERM_EmergencyTrainWeekCheckResult ee, HttpServletRequest request) {
        String datasuccess = "success";
        ee.setS2(DateUtils.getSysTimestamp());
        checkResultService.save(ee);
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
            checkResultService.delete(Long.parseLong(arrids[i]));
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
        ERM_EmergencyTrainWeekCheckResult ee = checkResultService.get(id);
        model.addAttribute("entity", ee);
        List<ERM_EmergencyTrainWeekCheckResult> details = checkResultService.listAllByPlanId(ee.getID());
        model.addAttribute("details", details);
        return "erm/plan/week/view";
    }


}
