package com.cczu.aqzf.model.service;

import com.cczu.aqzf.model.dao.XzcfSgdczjDao;
import com.cczu.aqzf.model.entity.XZCF_SgdczjEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("XzcfSgdczjService")
public class XzcfSgdczjService {

	@Resource
	private XzcfSgdczjDao xzcfSgdczjDao;
	
	/**
	 * 查询事故调查证据list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=xzcfSgdczjDao.dataGrid(mapData);
		int getTotalCount=xzcfSgdczjDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteInfo(long id) {
		xzcfSgdczjDao.deleteInfo(id);
	}

	/**
	 * 添加事故调查证据信息
	 * @param zfry
	 */
	public void addInfo(XZCF_SgdczjEntity zfry) {
		zfry.setS1(new Timestamp(System.currentTimeMillis()));
		zfry.setS2(new Timestamp(System.currentTimeMillis()));
		zfry.setS3(0);
		xzcfSgdczjDao.addInfo(zfry);
	}

	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public XZCF_SgdczjEntity findById(Long id) {
		return xzcfSgdczjDao.findInfoById(id);
	}
	
	/**
	 * 修改
	 * @param zfry
	 */
	public void updateInfo(XZCF_SgdczjEntity zfry) {
		zfry.setS2(new Timestamp(System.currentTimeMillis()));
		zfry.setS3(0);
	    xzcfSgdczjDao.updateInfo(zfry);
	}
	
	/**
	 * 根据id查找详细信息
	 * @param id
	 * @return
	 */
	public Map<String,Object> findAllbyid(Long id) {
		return xzcfSgdczjDao.findAllbyid(id);
	}
	
	public List<XZCF_SgdczjEntity> findbyTzid(Long id) {
		return xzcfSgdczjDao.findbyTzid(id);
	}
}
