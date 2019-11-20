package com.cczu.model.service.impl;

import com.cczu.model.dao.impl.ErmTrainPlanWeekDao;
import com.cczu.model.dao.impl.ErmTrainPlanWeekDetailDao;
import com.cczu.model.entity.ERM_EmergencyTrainPlanWeekDetail;
import com.cczu.model.entity.ERM_EmergencyTrainPlanWeekEntity;
import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.service.BaseService;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Map;


@Transactional(readOnly = true)
@Service
public class ErmTrainPlanWeekService extends BaseService<ERM_EmergencyTrainPlanWeekEntity, Long> {

    @Autowired
    private ErmTrainPlanWeekDao trainPlanWeekDao;
    @Autowired
    private ErmTrainPlanWeekDetailDao detailDao;

    @Override
    public HibernateDao<ERM_EmergencyTrainPlanWeekEntity, Long> getEntityDao() {
        return trainPlanWeekDao;
    }

    public Page<ERM_EmergencyTrainPlanWeekEntity> pageList(Page page, Map map) {
        return trainPlanWeekDao.pageList(page, map);
    }

    @Transactional
    public void addInfo(ERM_EmergencyTrainPlanWeekEntity entity,
                        String[] time, String[] time1, String[] hours, String[] content,
                        String[] address, String[] persons, String[] duty, String[] situation) {
        save(entity);
        Long planId = entity.getID();
        Assert.isTrue(time.length * 2 == time1.length);
        for (int i = 0; i < time1.length; i++) {
            ERM_EmergencyTrainPlanWeekDetail detail = new ERM_EmergencyTrainPlanWeekDetail();
            detail.setTime1(time1[i]);
            detail.setTime(DateUtils.getTimestampFromStr(time[i / 2] + " 00:00:00"));
            detail.setAddress(address[i]);
            detail.setContent(content[i]);
            detail.setDuty(duty[i]);
            detail.setHours(StringUtils.isNotBlank(hours[i]) ? Integer.valueOf(hours[i]) : null);
            detail.setPersons(persons[i]);
            detail.setPlanId(planId);
            detail.setSituation(situation[i]);
            detailDao.save(detail);
        }

    }

    @Transactional
    public void updateInfo(ERM_EmergencyTrainPlanWeekEntity entity,
                           String[] time, String[] time1, String[] hours, String[] content,
                           String[] address, String[] persons, String[] duty, String[] situation) {

        detailDao.deleteAllByPlanId(entity.getID());
        addInfo(entity, time, time1, hours, content, address, persons, duty, situation);

    }

    public void deleteFake(long id ){
        String hql = "update ERM_EmergencyTrainPlanWeekEntity set s3=1 where id =?0";
        trainPlanWeekDao.createQuery(hql,id).executeUpdate();
    }
}
