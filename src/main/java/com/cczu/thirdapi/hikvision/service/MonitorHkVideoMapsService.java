package com.cczu.thirdapi.hikvision.service;

import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.comm.service.BaseService;
import com.cczu.thirdapi.hikvision.dao.MonitorHkVideoMapsDao;
import com.cczu.thirdapi.hikvision.entity.ThirdHikvisionDevice;
import com.cczu.util.common.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MonitorHkVideoMapsService extends BaseService<ThirdHikvisionDevice, Long> {

    @Autowired
    private MonitorHkVideoMapsDao videoMapsDao;

    @Override
    public HibernateDao<ThirdHikvisionDevice, Long> getEntityDao() {
        return videoMapsDao;
    }

    public void deleteByDeviceSerial(String deviceSerial) {
        String hql = "delete from ThirdHikvisionDevice where deviceSerial=?0";
        videoMapsDao.createQuery(hql, deviceSerial).executeUpdate();
    }

    public ThirdHikvisionDevice getByDeviceSerial(String deviceSerial) {
        ThirdHikvisionDevice entity = videoMapsDao.findUnique(Restrictions.eq("deviceSerial", deviceSerial));
        return entity;
    }

    public ThirdHikvisionDevice getByQyid(Long qyid) {
        ThirdHikvisionDevice entity = videoMapsDao.findUnique(Restrictions.eq("qyid", qyid));
        return entity;
    }

    public void updateDeviceName(String deviceSerial, String name) {
        String hql = "update ThirdHikvisionDevice set deviceName=?0 where deviceSerial=?1";
        videoMapsDao.createQuery(hql, name, deviceSerial).executeUpdate();
    }

    public List<ThirdHikvisionDevice> listByQyname(Map<String, Object> map) {
        List<ThirdHikvisionDevice> l;
        String sql = "SELECT * from Third_Hikvision_Device a left join bis_enterprise bis on bis.id =a.qyid where 1=1 ";
        sql += StringUtils.qyQueryJGLX(map, "bis");
        return videoMapsDao.findBySql(sql, null, ThirdHikvisionDevice.class);
    }

    public List<Map> listMap() {
        String hql = "select qyid as value ,qyname as text from ThirdHikvisionDevice";
        List<Map> list = videoMapsDao.createQuery(hql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        return list;
    }

    public List<Map<String, Object>> listBisInfoVideo(String xzqy) {
        return videoMapsDao.listBisInfoVideo(xzqy);
    }


}
