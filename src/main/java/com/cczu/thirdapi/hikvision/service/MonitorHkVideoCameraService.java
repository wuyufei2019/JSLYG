package com.cczu.thirdapi.hikvision.service;

import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.comm.persistence.PropertyFilter;
import com.cczu.sys.comm.service.BaseService;
import com.cczu.thirdapi.hikvision.dao.MonitorHkVideoCameraDao;
import com.cczu.thirdapi.hikvision.entity.ThirdHikvisionCamera;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonitorHkVideoCameraService extends BaseService<ThirdHikvisionCamera, Long> {

    @Autowired
    private MonitorHkVideoCameraDao cameraDao;

    @Override
    public HibernateDao<ThirdHikvisionCamera, Long> getEntityDao() {
        return cameraDao;
    }

    public void deleteByDeviceSerialChannelNo(String deviceSerial, int channelNo) {
        String hql = "delete from ThirdHikvisionCamera where deviceSerial=?0 and channelNo=?1";
        cameraDao.createQuery(hql, deviceSerial, channelNo).executeUpdate();
    }

    public List<ThirdHikvisionCamera> listByAttribute(List<PropertyFilter> list) {
        List<ThirdHikvisionCamera> l = cameraDao.find(list);
        return l;
    }

    public List<ThirdHikvisionCamera> listByDeviceSerial(String deviceSerial) {
        List<ThirdHikvisionCamera> l = cameraDao.find(Restrictions.eq("deviceSerial", deviceSerial));
        return l;
    }


/*

    public void deleteByDeviceSerial(String deviceSerial) {
        String hql = "delete from ThirdHikvisionDevice where deviceSerial=?0";
        videoMapsDao.createQuery(hql, deviceSerial).executeUpdate();
    }

    public ThirdHikvisionDevice getByDeviceSerial(String deviceSerial) {
        ThirdHikvisionDevice entity = videoMapsDao.findUnique(Restrictions.eq("deviceSerial", deviceSerial));
        return entity;
    }

    public void updateDeviceName(String deviceSerial, String name) {
        String hql = "update ThirdHikvisionDevice set deviceName=?0 where deviceSerial=?1";
        videoMapsDao.createQuery(hql, name, deviceSerial).executeUpdate();
    }


    public List<Map> listMap() {
        String hql = "select qyid as value ,qyname as text from ThirdHikvisionDevice";
        List<Map> list = videoMapsDao.createQuery(hql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        return list;
    }

*/

}
