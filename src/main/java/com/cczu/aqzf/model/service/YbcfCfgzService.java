package com.cczu.aqzf.model.service;

import com.cczu.aqzf.model.dao.YbcfCfgzDao;
import com.cczu.aqzf.model.entity.XZCF_JycfInfoEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("YbcfCfgzService")
public class YbcfCfgzService {

	@Resource
	private YbcfCfgzDao ybcfCfgzDao;
	
	/**
	 * 查询list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=ybcfCfgzDao.dataGrid(mapData);
		int getTotalCount=ybcfCfgzDao.getTotalCount(mapData);
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
		ybcfCfgzDao.deleteInfo(id);
	}

	/**
	 * 添加信息
	 * @param zfry
	 */
	public void addInfo(XZCF_JycfInfoEntity zfry) {
		zfry.setS1(new Timestamp(System.currentTimeMillis()));
		zfry.setS2(new Timestamp(System.currentTimeMillis()));
		zfry.setS3(0);
		ybcfCfgzDao.addInfo(zfry);
	}

	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public XZCF_JycfInfoEntity findById(Long id) {
		return ybcfCfgzDao.findInfoById(id);
	}
	
	/**
	 * 修改
	 * @param zfry
	 */
	public void updateInfo(XZCF_JycfInfoEntity zfry) {
		zfry.setS2(new Timestamp(System.currentTimeMillis()));
		zfry.setS3(0);
	    ybcfCfgzDao.updateInfo(zfry);
	}
}
