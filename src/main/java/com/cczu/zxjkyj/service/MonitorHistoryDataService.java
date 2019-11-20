package com.cczu.zxjkyj.service;

import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.service.BaseService;
import com.cczu.zxjkyj.dao.MonitorHistoryDao;
import com.cczu.zxjkyj.entity.Main_MonitoringHistoryDataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MonitorHistoryDataService extends BaseService<Main_MonitoringHistoryDataEntity, Long> {
    @Autowired
    MonitorHistoryDao historyDao;

    @Override
    public HibernateDao<Main_MonitoringHistoryDataEntity, Long> getEntityDao() {
        return historyDao;
    }


    public Page getPageStatistics(Page<Map> page, Map<String, Object> authorityMap, String startTime, String endTime) {

        historyDao.getPageStatistics(page, authorityMap, startTime, endTime);
        return page;

    }

}
