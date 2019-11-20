package com.cczu.model.dao.impl;

import com.cczu.model.entity.ERM_EmergencyTrainWeekCheckResult;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class ErmTrainWeekCheckResultDao extends BaseDao<ERM_EmergencyTrainWeekCheckResult,Long> {

    public void deleteAllByPlanId(long planId){
        String hql = "delete from ERM_EmergencyTrainWeekCheckResult where planId=?0";
        createQuery(hql,planId).executeUpdate();
    }
}
