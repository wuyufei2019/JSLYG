package com.cczu.aqzf.model.service;


import com.cczu.aqzf.model.dao.XzcfDCzjDao;
import com.cczu.aqzf.model.entity.XZCF_DczjEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 调查证据Service
 */
@Service("XzcfDCzjService")
public class XzcfDCzjService {

	@Resource
	private XzcfDCzjDao xzcfDCzjDao;
	
	//添加
	public void addInfo(XZCF_DczjEntity dczj){
		xzcfDCzjDao.save(dczj);
	}
	
	//根据调查报告id查询
	public List<Map<String, Object>> dataGridCzwt(Long id){
		return xzcfDCzjDao.dataGridCzwt(id);
	}
	
	//根据调查报告id删除
	public void deletebyId1(Long id){
		xzcfDCzjDao.deletebyId1(id);
	}
}
