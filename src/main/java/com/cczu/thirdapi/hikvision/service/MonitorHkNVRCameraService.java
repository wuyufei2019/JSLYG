package com.cczu.thirdapi.hikvision.service;

import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.comm.service.BaseService;
import com.cczu.thirdapi.hikvision.dao.MonitorHkNVRCameraDao;
import com.cczu.thirdapi.hikvision.entity.ThirdHikvisionNVRCamera;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MonitorHkNVRCameraService extends BaseService<ThirdHikvisionNVRCamera, Long> {

    @Autowired
    private MonitorHkNVRCameraDao cameraDao;

    @Override
    public HibernateDao<ThirdHikvisionNVRCamera, Long> getEntityDao() {
        return cameraDao;
    }

    public void deleteByDeviceSerial(String deviceSerial) {
        String hql = "delete from ThirdHikvisionNVRCamera where deviceSerial=?0";
        cameraDao.createQuery(hql, deviceSerial).executeUpdate();
    }

    public ThirdHikvisionNVRCamera getByDeviceSerial(String deviceSerial) {
        ThirdHikvisionNVRCamera entity = cameraDao.findUnique(Restrictions.eq("deviceSerial", deviceSerial));
        return entity;
    }

    public void updateDeviceName(String deviceSerial, String name) {
        String hql = "update ThirdHikvisionNVRCamera set deviceName=?0 where deviceSerial=?1";
        cameraDao.createQuery(hql, name, deviceSerial).executeUpdate();
    }

    public List<ThirdHikvisionNVRCamera> listByQyname(String qyname) {
        List<ThirdHikvisionNVRCamera> l = cameraDao.find(Restrictions.like("qyname", qyname));
        return l;
    }

    public List<Map> listMap() {
        String hql = "select qyid as value ,qyname as text from ThirdHikvisionNVRCamera";
        List<Map> list = cameraDao.createQuery(hql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        return list;
    }


}
