package com.cczu.aqzf.model.service;

import com.cczu.aqzf.model.dao.AqzfJdjctjDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @Description: 监督检查统计Service
 * @author: zpc
 * @date: 2018年02月10日
 */
@Service("AqzfJdjctjService")
public class AqzfJdjctjService {
	
	@Resource
	private AqzfJdjctjDao aqzfJdjctjDao;
	
	/**
	 * 查询监督检查统计信息list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=aqzfJdjctjDao.dataGrid(mapData);
		int getTotalCount=aqzfJdjctjDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
}
