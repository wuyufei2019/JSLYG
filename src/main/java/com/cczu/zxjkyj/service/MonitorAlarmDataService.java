package com.cczu.zxjkyj.service;

import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.service.BaseService;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.util.common.Parameter;
import com.cczu.util.common.StringUtils;
import com.cczu.zxjkyj.dao.MonitorAlarmDao;
import com.cczu.zxjkyj.entity.Main_MonitoringAlarmDataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class MonitorAlarmDataService extends BaseService<Main_MonitoringAlarmDataEntity, Long> {
    @Autowired
    MonitorAlarmDao alarmDao;

    @Override
    public HibernateDao<Main_MonitoringAlarmDataEntity, Long> getEntityDao() {
        return alarmDao;
    }

    public Page getPage(Page<Map> page, Map<String, Object> authorityMap, String status) {
        String sql = "select a.*,bis.m1,b.target_name from main_monitoring_alarmdata a left join " +
                "monitor_point_maintain b on" +
                " b.id = a.pointid left join bis_enterprise bis on bis.id = a.id1 where 1=1";

        sql+= StringUtils.qyQueryJGLX(authorityMap,"bis");
        if ("1".equals(status)) {
            sql += "and a.status = 1";
        }
        sql += " order by a.alarmtime desc";
        alarmDao.getMapPageBySql(page, sql);
        return page;
    }

    public void updateReason(Long id, String reason) {
        String hql = "update Main_MonitoringAlarmDataEntity set reason = ?0,status =1 where ID =?1 ";
        alarmDao.batchExecute(hql, reason, id);
    }

    public List<Map> listAlarmTwoMin() {
        String sql = "select a.id, a.alarmtype,b.m1,c.bit_no,c.target_name from main_monitoring_alarmdata a left join" +
                " bis_enterprise b on b.id = a.id1 left join monitor_point_maintain c on c.id = a.pointid where a.status is null and " +
                " alarmtime>:p1";
        List<Map> alarms = alarmDao.findBySql(sql,
                new Parameter(DateUtils.formatDateTime(DateUtils.addMinutes(new Date(), -2))), Map.class);
        return alarms;
    }

    @Transactional(readOnly = true)
    public List<Map> listAlarmNoReport() {
        String sql = "select a.id, a.equip_code EquipmentID ,a.value CurrentValue ,a.point_high_alarm " +
                "EquipmentUpperThreshold, a.point_low_alarm EquipmentLowerThreshold ,a.alarmtype WarningType," +
                "a.alarmtime RecordingTime from main_monitoring_alarmdata a left join monitor_point_maintain b on b.id = a.pointid" +
                " where a.report is null and b.noreport is null";
        List<Map> alarms = alarmDao.findBySql(sql, null, Map.class);
        return alarms;
    }

    @Transactional(readOnly = false)
    public void updateReport(String ids) {
        String sql = "update main_monitoring_alarmdata set report =1 where id in (" + ids + ")";
        alarmDao.updateBySql(sql, null);
    }

}
