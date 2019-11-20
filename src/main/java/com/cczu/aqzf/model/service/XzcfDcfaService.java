package com.cczu.aqzf.model.service;

import com.cczu.aqzf.model.dao.XzcfDcfaDao;
import com.cczu.aqzf.model.entity.XZCF_DCFAEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("XzcfDcfaService")
public class XzcfDcfaService {

	@Resource
	private XzcfDcfaDao xzcfDcfaDao;
	
	/**
	 * 查询调查方案list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=xzcfDcfaDao.dataGrid(mapData);
		int getTotalCount=xzcfDcfaDao.getTotalCount(mapData);
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
		xzcfDcfaDao.deleteInfo(id);
	}

	/**
	 * 删除后 立案审批的xwflag置0
	 * @param id
	 */
	public void updateLaspInfo(long id) {
		xzcfDcfaDao.updateLaspInfo(id);
	}


	/**
	 * 添加调查方案信息
	 * @param zfry
	 */
	public void addInfo(XZCF_DCFAEntity zfry) {
		zfry.setS1(new Timestamp(System.currentTimeMillis()));
		zfry.setS2(new Timestamp(System.currentTimeMillis()));
		zfry.setS3(0);
		xzcfDcfaDao.addInfo(zfry);
	}

	/**
	 * 根据id查找调查方案信息
	 * @param id
	 * @return
	 */
	public XZCF_DCFAEntity findById(Long id) {
		return xzcfDcfaDao.findInfoById(id);
	}
	
	/**
	 * 修改
	 * @param zfry
	 */
	public void updateInfo(XZCF_DCFAEntity zfry) {
		zfry.setS2(new Timestamp(System.currentTimeMillis()));
		zfry.setS3(0);
	    xzcfDcfaDao.updateInfo(zfry);
	}
}
