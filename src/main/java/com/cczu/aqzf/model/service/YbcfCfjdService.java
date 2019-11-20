package com.cczu.aqzf.model.service;

import com.cczu.aqzf.model.dao.YbcfCfjdDao;
import com.cczu.aqzf.model.entity.XZCF_JycfCfjdEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("YbcfCfjdService")
public class YbcfCfjdService {

	@Resource
	private YbcfCfjdDao ybcfCfjdDao;
	
	/**
	 * 查询询问通知list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=ybcfCfjdDao.dataGrid(mapData);
		int getTotalCount=ybcfCfjdDao.getTotalCount(mapData);
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
		ybcfCfjdDao.deleteInfo(id);
	}
	
	/**
	 * 删除后 告知书flag置0
	 * @param id
	 */
	public void updateLaspInfo(long id) {
		ybcfCfjdDao.updateLaspInfo(id);
	}

	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public XZCF_JycfCfjdEntity findById(Long id) {
		return ybcfCfjdDao.findInfoById(id);
	}
	
	/**
	 * 添加
	 */
	public void addInfo(XZCF_JycfCfjdEntity cssb) {	
		cssb.setS1(new Timestamp(System.currentTimeMillis()));
		cssb.setS2(new Timestamp(System.currentTimeMillis()));
		cssb.setS3(0);
		ybcfCfjdDao.save(cssb);
	}
	
	/**
	 * 修改
	 * @param jttl
	 */
	public void updateInfo(XZCF_JycfCfjdEntity cssb) {
		cssb.setS2(new Timestamp(System.currentTimeMillis()));
		cssb.setS3(0);
		ybcfCfjdDao.save(cssb);
	}
	
	/**
	 * 根据告知id查找符合word的数据
	 * @param id
	 * @return
	 */
	public XZCF_JycfCfjdEntity findWordByLaId(Long gzid) {
		return ybcfCfjdDao.findWordByLaId(gzid);
	}
}
