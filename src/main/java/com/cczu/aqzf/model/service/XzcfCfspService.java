package com.cczu.aqzf.model.service;

import com.cczu.aqzf.model.dao.XzcfCfspDao;
import com.cczu.aqzf.model.entity.XZCF_CFSPEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("XzcfCfspService")
public class XzcfCfspService {

	@Resource
	private XzcfCfspDao xzcfCfspDao;
	
	/**
	 * 查询调查方案list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=xzcfCfspDao.dataGrid(mapData);
		int getTotalCount=xzcfCfspDao.getTotalCount(mapData);
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
		xzcfCfspDao.deleteInfo(id);
	}

	/**
	 * 删除后 立案审批的xwflag置0
	 * @param id
	 */
	public void updateLaspInfo(long id) {
		xzcfCfspDao.updateLaspInfo(id);
	}
	
	/**
	 * 添加调查方案信息
	 * @param zfry
	 */
	public void addInfo(XZCF_CFSPEntity zfry) {
		zfry.setS1(new Timestamp(System.currentTimeMillis()));
		zfry.setS2(new Timestamp(System.currentTimeMillis()));
		zfry.setS3(0);
		xzcfCfspDao.addInfo(zfry);
	}

	/**
	 * 根据id查找调查方案信息
	 * @param id
	 * @return
	 */
	public XZCF_CFSPEntity findById(Long id) {
		return xzcfCfspDao.findInfoById(id);
	}
	
	/**
	 * 修改
	 * @param zfry
	 */
	public void updateInfo(XZCF_CFSPEntity zfry) {
		zfry.setS2(new Timestamp(System.currentTimeMillis()));
		zfry.setS3(0);
	    xzcfCfspDao.updateInfo(zfry);
	}
}
