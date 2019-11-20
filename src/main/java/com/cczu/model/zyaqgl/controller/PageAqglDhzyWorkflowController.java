package com.cczu.model.zyaqgl.controller;

import com.cczu.activiti.util.WorkFlowConst;
import com.cczu.model.zyaqgl.entity.AQGL_DhzyFxEntity;
import com.cczu.model.zyaqgl.entity.AQGL_FireWork;
import com.cczu.model.zyaqgl.entity.AQGL_SaftyMeasure;
import com.cczu.model.zyaqgl.entity.AQGL_Zyaq_Aqcs;
import com.cczu.model.zyaqgl.service.AqglAqcsService;
import com.cczu.model.zyaqgl.service.AqglDhzyWorkFlowService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.utils.UserUtil;
import com.google.common.collect.Maps;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * 安全生产-动火作业Controller
 *
 * @author YZH
 */
@Controller
@RequestMapping("zyaqgl/dhzy/act")
public class PageAqglDhzyWorkflowController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private AqglDhzyWorkFlowService aqgldhzyService;
    @Autowired
    private AqglAqcsService aqcsService;
    @Autowired
    private TaskService taskService;

    /**
     * 列表显示页面
     *
     * @param model
     */
    @RequestMapping(value = "index")
    public String actindex(Model model) {
        return "zyaqgl/zyaq/dhzy/act/index";
    }

    /**
     * 数据list
     *
     * @param request
     */
    @RequiresPermissions("zyaqgl:dhzy:view")
    @RequestMapping(value = "list")
    @ResponseBody
    public Map<String, Object> getData(HttpServletRequest request) {
        List<PropertyFilter> list = PropertyFilter.buildFromHttpRequest(request);
        list.add(new PropertyFilter("NENS_processInstanceId", null));
        Page<AQGL_FireWork> page = getPage(request);
        return getEasyUIData(aqgldhzyService.page(page, list));

    }

    /**
     * 添加页面跳转
     *
     * @param model
     */
    @RequiresPermissions("zyaqgl:dhzy:add")
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String actCreate(Model model) {
        model.addAttribute("action", "create");
        model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
        return "zyaqgl/zyaq/dhzy/act/form";
    }

    /**
     * 添加动火作业信息
     *
     * @param entity,model
     */
    @RequiresPermissions("zyaqgl:dhzy:add")
    @RequestMapping(value = "create")
    @ResponseBody
    public String create(AQGL_FireWork entity) {
        String result = "success";
        setBaseInfo(entity);
        entity.setM1("DHZY-" + DateUtils.getDateRandom());
        int userId = UserUtil.getCurrentUser().getId();
        entity.setID2((long) userId);
        entity.setApplyer(UserUtil.getCurrentShiroUser().getLoginName());
        Map<String, Object> variables = Maps.newHashMap();
        variables.put("applyer", userId);
        try {
            aqgldhzyService.startWorkflow(entity, variables);
        } catch (ActivitiException e) {
            if (e.getMessage().indexOf("no processes deployed with key") != -1) {
                logger.warn("没有部署流程!", e);
            } else {
                logger.error("启动请假流程失败：", e);
            }
        } catch (Exception e) {
            logger.error("启动审核流程失败：", e);
            result = "error";
        }
        return result;

    }

    @RequestMapping(value = "view/{id}")
    public String view(@PathVariable("id") Long id, Model model) {
        AQGL_FireWork aqgl_fireWork = aqgldhzyService.get(id);
        Task task = taskService.createTaskQuery().processInstanceId(aqgl_fireWork.getProcessInstanceId()).active().singleResult();
        model.addAttribute("entity", aqgl_fireWork);
        model.addAttribute("taskName", task.getName());
        return "zyaqgl/zyaq/dhzy/act/view";
    }

    @RequestMapping(value = "view")
    public String actView(@RequestParam(WorkFlowConst.ACT_BUSKEY) Long id, Model model) {
        AQGL_FireWork aqgl_fireWork = aqgldhzyService.get(id);
        Task task = taskService.createTaskQuery().processInstanceId(aqgl_fireWork.getProcessInstanceId()).active().singleResult();
        model.addAttribute("entity", aqgl_fireWork);
        model.addAttribute("taskName", task.getName());
        return "zyaqgl/zyaq/dhzy/act/view";
    }

    /**
     * 分配任务页面跳转
     *
     * @param id,model
     */
    @RequestMapping(value = "fprw/{id}", method = RequestMethod.GET)
    public String fprw(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
        AQGL_FireWork dhzy = aqgldhzyService.get(id);
        model.addAttribute("dhzy", dhzy);
        model.addAttribute("action", "fprw");
        model.addAttribute(WorkFlowConst.TASK_ID, request.getParameter(WorkFlowConst.TASK_ID));
        return "zyaqgl/zyaq/dhzy/act/fprwform";
    }

    /**
     * 分配任务
     *
     * @param dhzy,model
     */
    @RequestMapping(value = "fprw")
    @ResponseBody
    public String fprw(AQGL_FireWork dhzy, HttpServletRequest request) {
        String datasuccess = "success";
        dhzy.setM27(UserUtil.getCurrentUser().getId() + "");
        dhzy.setM27_1(DateUtils.getSysTimestamp());
        dhzy.setS2(DateUtils.getSysTimestamp());
        aqgldhzyService.save(dhzy);
        taskService.complete(request.getParameter(WorkFlowConst.TASK_ID), getWorkFlowExecutor(request));
        //返回结果
        return datasuccess;
    }

    /**
     * 动火作业安全措施列表显示页面
     *
     * @param model
     */
    @RequestMapping(value = "aqcsindex")
    public String aqcsindex(Model model, HttpServletRequest request) {
        model.addAttribute(WorkFlowConst.TASK_ID, request.getParameter(WorkFlowConst.TASK_ID));
        String buskey = request.getParameter(WorkFlowConst.ACT_BUSKEY);
        AQGL_FireWork fireWork = aqgldhzyService.get(Long.parseLong(buskey));
        model.addAttribute(WorkFlowConst.ASSIGNER, fireWork.getM16());
        model.addAttribute("id", buskey);
        model.addAttribute("type", request.getParameter("type"));
        return "zyaqgl/zyaq/dhzy/act/aqcsindex";
    }

    /**
     * 动火作业安全措施list
     *
     * @param request
     */
    @RequiresPermissions("zyaqgl:dhzy:view")
    @RequestMapping(value = "aqcslist")
    @ResponseBody
    public Map<String, Object> aqcsData(HttpServletRequest request) {
        Map<String, Object> map = getPageMap(request);
        String id1 = request.getParameter("id1");
        if (StringUtils.isNotBlank(id1)) {
            map.put("id1", request.getParameter("id1"));
            return aqcsService.bzaqscdataGrid(map);
        } else {
            return aqcsService.aqscdataGrid(map);
        }

    }

    /**
     * 编制安全措施信息
     *
     * @param id 动火作业id,model
     */
    @RequiresPermissions("zyaqgl:dhzy:add")
    @RequestMapping(value = "createAqcs/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String createAqcs(@PathVariable("id") Long id, @RequestParam("ids") String ids, HttpServletRequest request) {
        String datasuccess = "编制成功";
        //批量关联安全措施
        String[] arrids = ids.split(",");
        for (int i = 0; i < arrids.length; i++) {
            AQGL_Zyaq_Aqcs aqcs = new AQGL_Zyaq_Aqcs();
            AQGL_SaftyMeasure aqcs2 = aqcsService.findInforById(Long.parseLong(arrids[i]));
            aqcs.setID1(id);
            aqcs.setM1(aqcs2.getM1());
            aqcs.setM2("1");
            aqgldhzyService.addAqcs(aqcs);
        }
        //修改动火状态
        // AQGL_FireWork dhzy = aqgldhzyService.get(id);
        //dhzy.setM15_1(DateUtils.getSysTimestamp());
        //aqgldhzyService.save(dhzy);
        taskService.complete(request.getParameter(WorkFlowConst.TASK_ID), getWorkFlowExecutor(request));
        return datasuccess;
    }

    /**
     * 分析页面跳转
     *
     * @param id,model
     */
    @RequestMapping(value = "fx/{id}", method = RequestMethod.GET)
    public String fx(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
        model.addAttribute("dhzyid", id);
        model.addAttribute(WorkFlowConst.TASK_ID, request.getParameter(WorkFlowConst.TASK_ID));
        //返回页面
        model.addAttribute("action", "fx");
        System.out.println(11);
        return "zyaqgl/zyaq/dhzy/act/fxform";
    }

    /**
     * 分析
     *
     * @param request,model
     */
    @RequestMapping(value = "fx")
    @ResponseBody
    public String fx(Long id, HttpServletRequest request) {
        String datasuccess = "success";
        String name[] = request.getParameterValues("name");
        String data[] = request.getParameterValues("data");
        String time[] = request.getParameterValues("time");
        String person[] = request.getParameterValues("person");
        if (name != null) {
            //循环插入分析表
            for (int i = 0; i < name.length; i++) {
                AQGL_DhzyFxEntity sxfx = new AQGL_DhzyFxEntity();
                sxfx.setID1(id);
                sxfx.setM1(name[i]);
                sxfx.setM2(data[i]);
                sxfx.setM3(StringUtils.isBlank(time[i]) ? null : Timestamp.valueOf(time[i]));
                sxfx.setM4(person[i]);
                //aqgldhzyfxService.addInfo(sxfx);
            }
        }
        taskService.complete(request.getParameter(WorkFlowConst.TASK_ID));

        //返回结果
        return datasuccess;
    }


}
