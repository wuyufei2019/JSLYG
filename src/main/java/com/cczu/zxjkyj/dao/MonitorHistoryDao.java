package com.cczu.zxjkyj.dao;

import com.cczu.sys.comm.persistence.Page;
import com.cczu.util.common.StringUtils;
import com.cczu.util.dao.BaseDao;
import com.cczu.zxjkyj.entity.Main_MonitoringHistoryDataEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class MonitorHistoryDao extends BaseDao<Main_MonitoringHistoryDataEntity, Long> {


    public Page getPageStatistics(Page<Map> page, Map<String, Object> authorityMap, String startTime, String endTime) {
        String sql = "select a.*,b.maxvalue,b.minvalue,b.avgvalue from monitor_point_maintain a LEFT JOIN( SELECT pointid ,max" +
                "([value]) maxvalue,min([value]) minvalue,AVG(value) avgvalue from main_monitoring_historydata where updatetime>?0 " +
                "and updatetime<?1 GROUP BY pointid ) b on a.id = b.pointid left join bis_enterprise bis on bis.id = " +
                "a.qyid where 1=1 ";
        sql += StringUtils.qyQueryJGLX(authorityMap, "bis");
        findMapPageSql(page, sql, startTime, endTime);
        return page;
    }
}
