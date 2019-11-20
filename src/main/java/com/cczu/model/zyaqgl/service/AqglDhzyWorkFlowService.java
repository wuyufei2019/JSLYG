package com.cczu.model.zyaqgl.service;

import com.cczu.model.zyaqgl.dao.AqglAqzyAqcsDao;
import com.cczu.model.zyaqgl.dao.AqglDhzyDao;
import com.cczu.model.zyaqgl.entity.AQGL_FireWork;
import com.cczu.model.zyaqgl.entity.AQGL_Zyaq_Aqcs;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;
import com.google.common.collect.Maps;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 安全管理-动火作业Service
 *
 * @author YZH
 */
@Transactional(readOnly = true)
@Service
public class AqglDhzyWorkFlowService {
    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private TaskService taskService;

    @Resource
    private AqglDhzyDao dhzyDao;
    @Resource
    private AqglAqzyAqcsDao aqcsDao;

    //添加动火作业
    public void startWorkflow(AQGL_FireWork entity, Map variables) {
        dhzyDao.save(entity);
        String businessKey = entity.getID().toString();

        ProcessInstance processInstance = null;
        try {
            // 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
            identityService.setAuthenticatedUserId(entity.getID2().toString());

            processInstance = runtimeService.startProcessInstanceByKey("WORK_FIRE", businessKey, variables);

            String processInstanceId = processInstance.getId();
            Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
            taskService.complete(task.getId());
            entity.setProcessInstanceId(processInstanceId);
            dhzyDao.save(entity);
        } finally {
            identityService.setAuthenticatedUserId(null);
        }
        dhzyDao.save(entity);
    }

    public Page<AQGL_FireWork> page(Page page, List<PropertyFilter> list) {
        page = dhzyDao.findPage(page, list);
        return page;
    }

    public AQGL_FireWork get(long id) {
        return dhzyDao.find(id);
    }

    public void save(AQGL_FireWork entity) {
        dhzyDao.save(entity);
    }

    //编制安全措施
    public void addAqcs(AQGL_Zyaq_Aqcs entity) {
        aqcsDao.save(entity);
    }

    public void deleteSafetyMeasureById1(long id1 ){
        String hql ="delete from AQGL_Zyaq_Aqcs where ID1 =?0";
        aqcsDao.createQuery(hql,id1).executeUpdate();
    }

}
