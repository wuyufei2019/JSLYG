package com.cczu.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cczu.model.service.IMsgService;

/**
 * @author jason
 *
 */
@Component("msgTaskJob")  
public class MsgTaskJob {

	
	@Autowired
	private IMsgService msgService;

//	@Scheduled(cron = "*/5 * * * * ?")  //每5秒执行一次
	@Scheduled(cron = "0 0 0 * * ?")  //每天0点执行一次
    public void job1() {  
		msgService.getTask();
    }   

}
