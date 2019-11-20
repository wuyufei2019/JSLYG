package com.cczu.activiti.listener;

import com.cczu.model.zyaqgl.service.AqglDhzyService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;

public class TaskChooseAsigneeListener implements ExecutionListener {
    @Autowired
    private AqglDhzyService aqgldhzyService;
    @Override
    public void notify(DelegateExecution execution) throws Exception {
        String processBusinessKey = execution.getProcessBusinessKey();

        System.out.println("djasfljadsl");
    }
}
