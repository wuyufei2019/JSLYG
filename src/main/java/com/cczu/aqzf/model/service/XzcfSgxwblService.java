package com.cczu.aqzf.model.service;

import com.cczu.aqzf.model.dao.XzcfSgxwblDao;
import com.cczu.aqzf.model.entity.XZCF_SgxwblEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("XzcfSgxwblService")
public class XzcfSgxwblService {

	@Resource
	private XzcfSgxwblDao xzcfSgxwblDao;
	
	/**
	 * 查询事故询问笔录list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=xzcfSgxwblDao.dataGrid(mapData);
		int getTotalCount=xzcfSgxwblDao.getTotalCount(mapData);
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
		xzcfSgxwblDao.deleteInfo(id);
	}
	
	/**
	 * 添加事故询问笔录信息
	 * @param zfry
	 */
	public void addInfo(XZCF_SgxwblEntity zfry) {
		zfry.setS1(new Timestamp(System.currentTimeMillis()));
		zfry.setS2(new Timestamp(System.currentTimeMillis()));
		zfry.setS3(0);
		xzcfSgxwblDao.addInfo(zfry);
	}

	/**
	 * 根据id查找事故询问笔录信息
	 * @param id
	 * @return
	 */
	public XZCF_SgxwblEntity findById(Long id) {
		return xzcfSgxwblDao.findInfoById(id);
	}
	
	/**
	 * 修改
	 * @param zfry
	 */
	public void updateInfo(XZCF_SgxwblEntity zfry) {
		zfry.setS2(new Timestamp(System.currentTimeMillis()));
		zfry.setS3(0);
	    xzcfSgxwblDao.updateInfo(zfry);
	}
	
	public List<XZCF_SgxwblEntity> findByTzid(long tzid){
		return xzcfSgxwblDao.findByTzid(tzid);
	}
}
