package com.cczu.model.service.impl;

import com.cczu.model.dao.impl.ErmTrainPlanWeekDao;
import com.cczu.model.dao.impl.ErmTrainPlanWeekDetailDao;
import com.cczu.model.entity.ERM_EmergencyTrainPlanWeekDetail;
import com.cczu.model.entity.ERM_EmergencyTrainPlanWeekEntity;
import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.comm.service.BaseService;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional(readOnly = true)
@Service
public class ErmTrainPlanWeekDetailService extends BaseService<ERM_EmergencyTrainPlanWeekDetail, Long> {

    @Autowired
    private ErmTrainPlanWeekDetailDao trainPlanWeekDetailDao;

    @Override
    public HibernateDao<ERM_EmergencyTrainPlanWeekDetail, Long> getEntityDao() {
        return trainPlanWeekDetailDao;
    }


    public List<ERM_EmergencyTrainPlanWeekDetail> listAllByPlanId(long planId){
        List<ERM_EmergencyTrainPlanWeekDetail> details = trainPlanWeekDetailDao.find(Restrictions.eq("planId", planId));
        return details;
    }

}
