package com.cczu.aqzf.model.service;

import com.cczu.aqzf.model.dao.XzcfSgxwtzDao;
import com.cczu.aqzf.model.entity.XZCF_SgxwtzEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("XzcfSgxwtzService")
public class XzcfSgxwtzService {

	@Resource
	private XzcfSgxwtzDao xzcfSgxwtzDao;
	
	/**
	 * 查询询问通知list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=xzcfSgxwtzDao.dataGrid(mapData);
		int getTotalCount=xzcfSgxwtzDao.getTotalCount(mapData);
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
		xzcfSgxwtzDao.deleteInfo(id);
	}

	/**
	 * 添加询问通知信息
	 * @param zfry
	 */
	public void addInfo(XZCF_SgxwtzEntity zfry) {
		zfry.setS1(new Timestamp(System.currentTimeMillis()));
		zfry.setS2(new Timestamp(System.currentTimeMillis()));
		zfry.setS3(0);
		xzcfSgxwtzDao.addInfo(zfry);
	}

	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public XZCF_SgxwtzEntity findById(Long id) {
		return xzcfSgxwtzDao.findInfoById(id);
	}
	
	/**
	 * 修改
	 * @param zfry
	 */
	public void updateInfo(XZCF_SgxwtzEntity zfry) {
		zfry.setS2(new Timestamp(System.currentTimeMillis()));
		zfry.setS3(0);
	    xzcfSgxwtzDao.updateInfo(zfry);
	}
	
	/**
	 * 根据id查找详细信息
	 * @param id
	 * @return
	 */
	public Map<String,Object> findAllbyid(Long id) {
		return xzcfSgxwtzDao.findAllbyid(id);
	}
	
	public List<Map<String,Object>> dcztjson(String xzqy){
		return xzcfSgxwtzDao.dcztjson(xzqy);
	}
	
	/**
	 * 修改状态
	 */
	public void updateZt(String id) {
	    xzcfSgxwtzDao.updateZt(id);
	}
}
