package com.cczu.aqzf.model.service;

import com.cczu.aqzf.model.dao.XzcfLahszDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("XzcfLahszService")
public class XzcfLahszService {

	@Resource
	private XzcfLahszDao xzcfLahszDao;
	
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> list = xzcfLahszDao.dataGrid(mapData);
		int count = xzcfLahszDao.getTotalCount(mapData);
		map.put("rows", list);
		map.put("total", count);
		return map;
	}
	
	public void hflasp(String id){
		xzcfLahszDao.hflasp(id);
	}
}
