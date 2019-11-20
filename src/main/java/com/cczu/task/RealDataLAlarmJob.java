package com.cczu.task;

import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.zxjkyj.entity.Monitor_PointMaintainEntity;
import com.cczu.zxjkyj.service.MonitorPointMaintainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 定时任务去执行当前维护的点位表状态
 */
@Component
public class RealDataLAlarmJob {

    @Autowired
    private MonitorPointMaintainService maintainService;

    @Scheduled(cron = "0 */10 * * * ?")  //每2分钟执行一次
    // @Scheduled(fixedRate = 10000L)
    public void MonitorData() throws Exception {
        List<Monitor_PointMaintainEntity> all = maintainService.getAll();
        for (Monitor_PointMaintainEntity maintain : all) {
            Timestamp collectTime = maintain.getCollectTime();
            Float value = maintain.getValue();
            if (collectTime == null) {
                maintain.setStatus(1);//无信息
            } else if (DateUtils.truncatedCompareTo(collectTime, new Date(), Calendar.MINUTE) > 5) {
                maintain.setStatus(2);
            } else if (value != null) {
                if ((maintain.getThresholdUp() != null && value > maintain.getThresholdUp()) || (
                        maintain.getThresholdUpplus() != null && value > maintain.getThresholdUpplus())) {
                    maintain.setStatus(4);
                } else if ((maintain.getThresholdDown() != null && value < maintain.getThresholdDown()) || (
                        maintain.getThresholdDownplus() != null && value < maintain.getThresholdDownplus())) {
                    maintain.setStatus(3);
                }else{
                    maintain.setStatus(5);
                }
            }
            maintainService.update(maintain);
        }


    }


}
