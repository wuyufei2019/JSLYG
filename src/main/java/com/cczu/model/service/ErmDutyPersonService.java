package com.cczu.model.service;

import com.cczu.model.dao.ErmDutyPersonDao;
import com.cczu.model.entity.ERM_EmergencyDutyPersonEntity;
import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.comm.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class ErmDutyPersonService extends BaseService<ERM_EmergencyDutyPersonEntity, Long> {

    @Autowired
    ErmDutyPersonDao personDao;

    @Override
    public HibernateDao<ERM_EmergencyDutyPersonEntity, Long> getEntityDao() {
        return personDao;
    }


}
