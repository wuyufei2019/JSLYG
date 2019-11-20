package com.cczu.thirdapi.andmu.service;

import com.cczu.thirdapi.andmu.dao.MonitorVideoMapsDao;
import com.cczu.thirdapi.andmu.entity.ThirdAndmuDevice;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonitorVideoMapsService {

    @Autowired
    private MonitorVideoMapsDao videoMapsDao;

    public List<ThirdAndmuDevice> listAllByXzqy(String xzqy){
        return videoMapsDao.find(Restrictions.like("xzqy",xzqy, MatchMode.START));
    }
}
