package com.cczu.aqzf.model.service;

import com.cczu.aqzf.model.dao.XzcfYlaspDao;
import com.cczu.aqzf.model.entity.XZCF_YbcfYlaspEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 行政处罚-一般处罚-预立案审批
 */

@Service("XzcfYlaspService")
public class XzcfYlaspService{
	@Resource
	private XzcfYlaspDao xzcfYlaspDao;

	/**
	 * 添加并返回id
	 * @param yle
	 * @return
	 */
	public Long addInfoReturnID(XZCF_YbcfYlaspEntity ylasp) {
		return xzcfYlaspDao.addInfoReturnID(ylasp);
	}

	/**
	 * 分页展示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> list = xzcfYlaspDao.dataGrid(mapData);
		int count = xzcfYlaspDao.getTotalCount(mapData);
		map.put("rows", list);
		map.put("total", count);
		return map;
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteInfo(long id) {
		xzcfYlaspDao.deleteInfo(id);
	}
	
	/**
	 * 修改预立案审批状态
	 * @param id
	 */
	public void updatezt(long id) {
		xzcfYlaspDao.updatezt(id);
	}

	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public XZCF_YbcfYlaspEntity findInfoById(long id) {
		return xzcfYlaspDao.findInfoById(id);

	}

	/**
	 * 修改
	 * @param ylasp
	 */
	public void updateInfo( XZCF_YbcfYlaspEntity ylasp) {
		xzcfYlaspDao.save(ylasp);
	}
}
