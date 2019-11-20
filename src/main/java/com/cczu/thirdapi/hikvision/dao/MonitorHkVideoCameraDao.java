package com.cczu.thirdapi.hikvision.dao;

import com.cczu.thirdapi.hikvision.entity.ThirdHikvisionCamera;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;


@Repository("MonitorHkVideoCameraDao")
public class MonitorHkVideoCameraDao extends BaseDao<ThirdHikvisionCamera, Long> {
}
