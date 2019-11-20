package com.cczu.model.dao.impl;

import com.cczu.model.entity.ERM_EmergencyTrainPlanWeekEntity;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class ErmTrainPlanWeekDao extends BaseDao<ERM_EmergencyTrainPlanWeekEntity, Long> {

    public Page<ERM_EmergencyTrainPlanWeekEntity> pageList(Page page, Map map) {

        String sql = "select a.*,b.m1 deptname from erm_emergencytrainplanweekentity a left join t_department b " +
                "on a.deptid = b.id where a.s3=0 "+getContent(map);
        Page<ERM_EmergencyTrainPlanWeekEntity> resultPage = findPageSql(page,ERM_EmergencyTrainPlanWeekEntity.class, sql);
        return resultPage;
    }

    public String getContent(Map mapData) {
        String content = "";
        if (mapData.get("qyid") != null && mapData.get("qyid") != "") {
            content = content + " AND a.qyid =" + mapData.get("qyid");
        }
        if (mapData.get("planname") != null && mapData.get("planname") != "") {
            content = content + " AND a.planname like '%" + mapData.get("planname" + "%'");
        }
        if (mapData.get("starttime") != null && mapData.get("starttime") != "") {
            content = content + " AND convert(varchar(10),a.s1,120) >=" + mapData.get("starttime");
        }
        if (mapData.get("endtime") != null && mapData.get("endtime") != "") {
            content = content + " AND convert(varchar(10),a.s1,120) <=" + mapData.get("endtime");
        }
        return content;

    }
}
