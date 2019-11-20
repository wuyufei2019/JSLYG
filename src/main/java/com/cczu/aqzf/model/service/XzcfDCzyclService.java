package com.cczu.aqzf.model.service;


import com.cczu.aqzf.model.dao.XzcfDCzyclDao;
import com.cczu.aqzf.model.entity.XZCF_DczyclEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 调查自由裁量Service
 */
@Service("XzcfDCzyclService")
public class XzcfDCzyclService {

	@Resource
	private XzcfDCzyclDao xzcfDCzyclDao;
	
	//添加
	public void addInfo(XZCF_DczyclEntity dczj){
		xzcfDCzyclDao.save(dczj);
	}
	
	//根据调查报告id查询
	public List<XZCF_DczyclEntity> dataGrid(Long id){
		return xzcfDCzyclDao.dataGrid(id);
	}
	
	//根据调查报告id删除
	public void deletebyId1(Long id){
		xzcfDCzyclDao.deletebyId1(id);
	}
	
	//根据调查报告id查询详情
		public List<Map<String,Object>> findInfoByDcid(Long dcid){
			return xzcfDCzyclDao.findInfoByDcid(dcid);
		}
}
