package com.cczu.thirdapi.hikvision.dao;

import com.cczu.thirdapi.hikvision.entity.ThirdHikvisionNVRCamera;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;


@Repository("MonitorHkNVRCameraDao")
public class MonitorHkNVRCameraDao extends BaseDao<ThirdHikvisionNVRCamera, Long> {
}
