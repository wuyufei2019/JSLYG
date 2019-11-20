package com.cczu.model.dao.impl;

import com.cczu.model.entity.ERM_EmergencyTrainPlanWeekDetail;
import com.cczu.model.entity.ERM_EmergencyTrainPlanWeekEntity;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class ErmTrainPlanWeekDetailDao extends BaseDao<ERM_EmergencyTrainPlanWeekDetail,Long> {

    public void deleteAllByPlanId(long planId){
        String hql = "delete from ERM_EmergencyTrainPlanWeekDetail where planId=?0";
        createQuery(hql,planId).executeUpdate();
    }
}
