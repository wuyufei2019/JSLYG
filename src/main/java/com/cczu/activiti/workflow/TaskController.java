package com.cczu.activiti.workflow;

import com.alibaba.fastjson.JSON;
import com.cczu.activiti.util.WorkFlowConst;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.utils.ServletUtils;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.service.ShiroRealm;
import com.cczu.sys.system.utils.UserUtil;
import com.google.common.collect.Lists;
import org.activiti.engine.*;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskInfo;
import org.apache.tools.ant.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("task/")
public class TaskController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected RepositoryService repositoryService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private FormService formService;

    protected static Map<String, ProcessDefinition> PROCESS_DEFINITION_CACHE = new HashMap<String, ProcessDefinition>();

    /**
     * 签收任务
     */
    @RequestMapping(value = "claim/{id}")
    @ResponseBody
    public String claim(@PathVariable("id") String taskId) {
        String userName = UserUtil.getCurrentShiroUser().getLoginName();
        taskService.claim(taskId, userName);
        return "success";
    }


    @RequestMapping(value = "todo/index", method = RequestMethod.GET)
    public String todoIndex() {
        return "workflow/task/todoindex";
    }

    @RequestMapping(value = "history/index", method = RequestMethod.GET)
    public String historyIndex() {
        return "workflow/task/historyindex";
    }


    /**
     * 未结束的流程界面流转-----如果当前任务的表单id为空：流转至通用的工作流审批表单（url为工作命名空间/主键id）
     * 如果当前任务的表单id 不为空，则根据表单id的url进行流转
     * 若表单开头为 busikey_U 则用url/主键id的方式重定向 若为busikey_P 则用 url?id=主键id的方式重定向
     *
     * @param processInstanceId
     * @param type
     * @param url
     * @param taskId
     * @param model
     * @return
     */
    @RequestMapping(value = "{type}/business/{processInstanceId}", method = RequestMethod.GET)
    public String businessIndex(@PathVariable("processInstanceId") String processInstanceId,
                                @PathVariable("type") String type, @RequestParam("url") String url,
                                @RequestParam(WorkFlowConst.TASK_ID) String taskId, Model model) {

        ProcessInstance instance = null;
        String formUrl = "";
        try {
            instance = runtimeService.createProcessInstanceQuery().
                    processInstanceId(processInstanceId).active().singleResult();
            //流程还未结束
            if (instance != null) {
                //获取当前任务表单id --url
                formUrl = formService.getTaskFormData(taskId).getFormKey();
                if (StringUtils.isNotBlank(formUrl)) {
                    if (formUrl.indexOf(WorkFlowConst.BUSIKEY_U) == 0)
                        return "redirect:" + formUrl.substring(WorkFlowConst.BUSIKEY_U.length()) + "/" + instance.getBusinessKey()
                                + "?taskId=" + taskId + "&processInstanceId" + processInstanceId;
                    else if (formUrl.indexOf(WorkFlowConst.BUSIKEY_P) == 0)
                        return "redirect:" + formUrl.substring(WorkFlowConst.BUSIKEY_P.length()) + "?" + WorkFlowConst.ACT_BUSKEY + "=" + instance.getBusinessKey()
                                + "&taskId=" + taskId + "&processInstanceId=" + processInstanceId;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //当前任务包含表单id
        if (StringUtils.isNotBlank(formUrl)) {
            url = formUrl;
        }
        url = url + (url.contains("?") ? "&" : "?")
                + WorkFlowConst.ACT_BUSKEY + "=" + instance.getBusinessKey();
        model.addAttribute("url", url);
        if (url.contains(WorkFlowConst.HANDLER + "=" + WorkFlowConst.HANDLER_UNNEED)) {
            model.addAttribute(WorkFlowConst.HANDLER, WorkFlowConst.HANDLER_UNNEED);
        }
        //意见列表
        List<Comment> commentsList = taskService.getProcessInstanceComments(processInstanceId);
        model.addAttribute("commentsList", commentsList);
        model.addAttribute("type", type);
        model.addAttribute(WorkFlowConst.TASK_ID, taskId);
        model.addAttribute("processInstanceId", processInstanceId);
        return "workflow/task/opinion";
    }

    @RequestMapping(value = "business/noopinion/view/{processInstanceId}", method = RequestMethod.GET)
    public String businessNoOpinionIndex(@PathVariable("processInstanceId") String processInstanceId,
                                         @RequestParam("url") String url) {
        ProcessInstance instance = runtimeService.createProcessInstanceQuery().
                processInstanceId(processInstanceId).active().singleResult();
        return "redirect:" + url + instance.getBusinessKey();
    }

    @RequestMapping(value = "getHandleBtn/{processInstanceId}", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getHandleBtn(@PathVariable("processInstanceId") String processInstanceId) {
        List<String> btnStrs = getBtn(processInstanceId);
        return btnStrs;
    }


    /**
     * 待办任务--Portlet
     */
    @RequestMapping(value = "todo/list")
    @ResponseBody
    public Map<String, Object> todoList() {
        ShiroRealm.ShiroUser user = UserUtil.getCurrentShiroUser();
        // 已经签收的任务
        List<Task> todoList = taskService.createTaskQuery().taskCandidateOrAssigned(user.getLoginName()).active().list();
        List<Map<String, Object>> result = Lists.newArrayList();
        for (Task task : todoList) {
            String processDefinitionId = task.getProcessDefinitionId();
            ProcessDefinition processDefinition = getProcessDefinition(processDefinitionId);

            Map<String, Object> singleTask = packageTaskInfo(task, processDefinition);
            singleTask.put("status", "todo");
            result.add(singleTask);
        }
        return getEasyUIByList(result);
    }


    /**
     * 历史任务--Portlet
     */
    @RequestMapping(value = "history/list")
    @ResponseBody
    public Map<String, Object> historyList(HttpServletRequest request) {
        Page page = getPage(request);
        ShiroRealm.ShiroUser user = UserUtil.getCurrentShiroUser();
        List<Map<String, Object>> result = new ArrayList<>();
        // 已经签收的任务
        List<HistoricTaskInstance> historyList =
                historyService.createHistoricTaskInstanceQuery().taskAssignee(user.getLoginName()).listPage(page.getFirst() - 1, page.getPageSize());
        for (HistoricTaskInstance task : historyList) {
            String processDefinitionId = task.getProcessDefinitionId();
            ProcessDefinition processDefinition = getProcessDefinition(processDefinitionId);
            Map<String, Object> singleTask = packageTaskInfo(task, processDefinition);
            result.add(singleTask);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("total", result.size());
        map.put("rows", result);
        return map;
    }


    /**
     * 完成任务
     *
     * @param taskId
     * @return
     */
    @RequestMapping(value = "complete/{id}", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String complete(@PathVariable("id") String taskId,
                           @RequestParam(value = "processInstanceId", required = false) String processInstanceId,
                           HttpServletRequest request) {
        try {
            Map<String, Object> variables = ServletUtils.getParametersStartingWith(request, WorkFlowConst.ACT_VARIABLES);
            //是否有意见上传
            if (variables.containsKey(WorkFlowConst.OPINION)) {
                //设置批注人
                Authentication.setAuthenticatedUserId(UserUtil.getCurrentShiroUser().getLoginName());
                taskService.addComment(taskId, processInstanceId, variables.get(WorkFlowConst.OPINION).toString());
            }
            //是否有文件上传
            if (variables.containsKey(WorkFlowConst.FILE)) {
                taskService.createAttachment("", taskId, processInstanceId, "", "", variables.get(WorkFlowConst.FILE).toString());
            }
            for (Map.Entry<String, Object> entry : variables.entrySet()) {
                if("false".equals(entry.getValue())|| "true".equals(entry.getValue())){
                    entry.setValue(Boolean.valueOf(entry.getValue().toString()));
                }
            }
            this.taskService.complete(taskId, variables);
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }


    private ProcessDefinition getProcessDefinition(String processDefinitionId) {
        ProcessDefinition processDefinition = PROCESS_DEFINITION_CACHE.get(processDefinitionId);
        if (processDefinition == null) {
            processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
            PROCESS_DEFINITION_CACHE.put(processDefinitionId, processDefinition);
        }
        return processDefinition;
    }


    public List<String> getBtn(String pid) {
        List<String> transNames = new ArrayList<>();
        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(pid).singleResult();
        ProcessDefinitionEntity pd = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(pi.getProcessDefinitionId());
        ActivityImpl activity = pd.findActivity(pi.getActivityId());
        //获取当前节点的流转方向
        List<PvmTransition> transitions = activity.getOutgoingTransitions();
        //如果只有一条流转方向 并且下一个方向为排他网关，设置网关设置的按钮
        if (transitions.size() == 1) {
            PvmActivity nextAct = transitions.get(0).getDestination();
            if ("exclusiveGateway".equals(nextAct.getProperty("type"))) {
                List<PvmTransition> nexTransitions = nextAct.getOutgoingTransitions();
                for (PvmTransition trans : nexTransitions) {
                    String transName = (String) trans.getProperty("name");
                    if (StringUtils.isNotBlank(transName)) {
                        transNames.add(transName);
                    }
                }
            }

        }
        if (transNames.size() == 0) {
            transNames.add("提交");
        }
        return transNames;
    }

    private Map<String, Object> packageTaskInfo(TaskInfo task, ProcessDefinition processDefinition) {
        Map<String, Object> singleTask = new HashMap<>();
        singleTask.put("id", task.getId());
        singleTask.put("name", task.getName());
        singleTask.put("createTime", DateUtils.format(task.getCreateTime(), "yyyy-MM-dd hh:mm:ss"));
        singleTask.put("pdname", processDefinition.getName());
        singleTask.put("pdversion", processDefinition.getVersion());
        singleTask.put("pid", task.getProcessInstanceId());
        singleTask.put("url", processDefinition.getCategory());
        singleTask.put("assignee", task.getAssignee());
        return singleTask;
    }


}