package com.cczu.model.service.impl;

import com.cczu.model.dao.impl.ErmTrainWeekCheckResultDao;
import com.cczu.model.entity.ERM_EmergencyTrainWeekCheckResult;
import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.comm.service.BaseService;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional(readOnly = true)
@Service
public class ErmTrainWeekCheckResultService extends BaseService<ERM_EmergencyTrainWeekCheckResult, Long> {

    @Autowired
    private ErmTrainWeekCheckResultDao checkResultDao;

    @Override
    public HibernateDao<ERM_EmergencyTrainWeekCheckResult, Long> getEntityDao() {
        return checkResultDao;
    }


    public List<ERM_EmergencyTrainWeekCheckResult> listAllByPlanId(long planId) {
        List<ERM_EmergencyTrainWeekCheckResult> details = checkResultDao.find(Restrictions.eq("planId", planId));
        return details;
    }

}
