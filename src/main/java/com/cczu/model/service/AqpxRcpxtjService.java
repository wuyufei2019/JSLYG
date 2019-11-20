package com.cczu.model.service;

import com.cczu.model.dao.AqpxRcpxtjDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 日常培训统计Service
 * @author YZH
 *
 */
@Service("AqpxRcpxtjService")
public class AqpxRcpxtjService {
	
	@Resource
	private AqpxRcpxtjDao aqpxRcpxtjDao;
	
	/**
	 * 员工学习统计list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataPxjhGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=aqpxRcpxtjDao.dataGrid2(mapData);
		int getTotalCount=aqpxRcpxtjDao.getTotalCount2(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	
}
