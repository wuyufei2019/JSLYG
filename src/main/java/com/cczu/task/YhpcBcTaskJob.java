package com.cczu.task;

import com.cczu.model.service.YhpcCheckPlanService;
import com.cczu.model.service.YhpcPlanDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component("yhpcBcTaskJob")
public class YhpcBcTaskJob {


    @Autowired
    private YhpcCheckPlanService yhpcCheckPlanService;
    @Autowired
    private YhpcPlanDetailService planDetailService;

    @Scheduled(cron = "0 0 5 ? * MON")  //每个星期一早上5点
    public void job1() {
        yhpcCheckPlanService.BcMsg();
    }

    //日检 每天0点
    //@Scheduled(cron = "0 0 0 1/1 * ? ")
    @Scheduled(fixedRate = 10000)
    public void job2() {
        planDetailService.generatorDaysCheckTask();
    }


    //生成周检任务
    @Scheduled(cron = "0 0 0 ? * MON ")
    public void job3() {
        planDetailService.generatorWeeksORMonthCheckTask("2");
    }

    //生成月检任务
    @Scheduled(cron = "0 0 0 1 * ? ")
    public void job4() {
        planDetailService.generatorWeeksORMonthCheckTask("3");
    }

    //生成年检任务
    @Scheduled(cron = "0 0 0 1 1 ? ")
    //@Scheduled(fixedRate = 10000)
    public void job5() {
        planDetailService.generatorYearsCheckTask();
    }



}
