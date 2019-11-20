package com.cczu.thirdapi.hikvision.dao;

import com.cczu.thirdapi.hikvision.entity.ThirdHikvisionDevice;
import com.cczu.util.common.Parameter;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository("MonitorHkVideoMapsDao")
public class MonitorHkVideoMapsDao extends BaseDao<ThirdHikvisionDevice, Long> {

    public List<Map<String, Object>> listBisInfoVideo(String xzqy) {
        String sql = "select bis.id,bis.m1 title,bis.m33 address,bis.m16 pointx,bis.m17 pointy,'/static/model/images/map/video.png' icon,'spjk' type" +
                " from Third_Hikvision_Device a left join bis_enterprise bis on bis.id =a.qyid " +
                " where bis.s3=0 and bis.ID2 like :p1 ";
        List<Map<String, Object>> reuslt = findBySql(sql, new Parameter("%"+xzqy+"%"), Map.class);
        return reuslt;
    }
}
