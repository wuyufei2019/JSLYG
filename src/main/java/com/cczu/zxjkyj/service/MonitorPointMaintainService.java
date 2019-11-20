package com.cczu.zxjkyj.service;

import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.service.BaseService;
import com.cczu.util.common.StringUtils;
import com.cczu.zxjkyj.dao.MonitorPointMaintainDao;
import com.cczu.zxjkyj.entity.Monitor_PointMaintainEntity;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class MonitorPointMaintainService extends BaseService<Monitor_PointMaintainEntity, Long> {
    @Autowired
    MonitorPointMaintainDao maintainDao;

    @Override
    public HibernateDao<Monitor_PointMaintainEntity, Long> getEntityDao() {
        return maintainDao;
    }

    public List<Map<String, Object>> listMap() {
        String hql = "select equipCode as EquipmentID,value as CurrentValue ,collectTime as RecordingTime" +
                " from Monitor_PointMaintainEntity where value is not null and (noreport is null or noreport!=1)";
        Query query = maintainDao.createQuery(hql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = query.list();
        return list;
    }


    //查询上传数据的企业

    public List<Map> listQy(Map<String, Object> authorityMap) {
        StringBuffer sql = new StringBuffer("SELECT b.id ,m1 ,count(*) total, count(case when datediff(n,collect_time,GETDATE()" +
                ")>5 THEN 1 else null end ) offline,count(case when collect_time is null then 1 else null end ) nodata " +
                "from monitor_point_maintain a left join bis_enterprise b on b.id = a.qyid where 1=1 ");
        sql.append(StringUtils.qyQueryJGLX(authorityMap, "b"));
        sql.append(" GROUP BY b.id, b.m1");
        List<Map> list = maintainDao.findBySql(sql.toString(), null, Map.class);
        return list;
    }

    public Page<Monitor_PointMaintainEntity> page(Page<Monitor_PointMaintainEntity> page, Map<String, Object> authorityMap) {
        StringBuffer sql = new StringBuffer("SELECT a.* from monitor_point_maintain a left join bis_enterprise b on" +
                " b.id= a.qyid where 1=1 ");
        sql.append(StringUtils.qyQueryJGLX(authorityMap, "b"));
        Page<Monitor_PointMaintainEntity> result = maintainDao.findPageSql(page, Monitor_PointMaintainEntity.class,
                sql.toString());
        return result;
    }

}
