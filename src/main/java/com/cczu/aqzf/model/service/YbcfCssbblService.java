package com.cczu.aqzf.model.service;

import com.cczu.aqzf.model.dao.YbcfCssbblDao;
import com.cczu.aqzf.model.entity.XZCF_JycfCssbEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("YbcfCssbblService")
public class YbcfCssbblService {

	@Resource
	private YbcfCssbblDao ybcfCssbblDao;
	
	/**
	 * 查询询问通知list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=ybcfCssbblDao.dataGrid(mapData);
		int getTotalCount=ybcfCssbblDao.getTotalCount(mapData);
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
		ybcfCssbblDao.deleteInfo(id);
	}
	
	/**
	 * 删除后 告知书flag置0
	 * @param id
	 */
	public void updateLaspInfo(long id) {
		ybcfCssbblDao.updateLaspInfo(id);
	}

	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public XZCF_JycfCssbEntity findById(Long id) {
		return ybcfCssbblDao.findInfoById(id);
	}
	
	/**
	 * 添加
	 */
	public void addInfo(XZCF_JycfCssbEntity cssb) {
		cssb.setS1(new Timestamp(System.currentTimeMillis()));
		cssb.setS2(new Timestamp(System.currentTimeMillis()));
		cssb.setS3(0);
		ybcfCssbblDao.save(cssb);
	}
	
	/**
	 * 修改
	 * @param jttl
	 */
	public void updateInfo(XZCF_JycfCssbEntity cssb) {
		cssb.setS2(new Timestamp(System.currentTimeMillis()));
		cssb.setS3(0);
		ybcfCssbblDao.save(cssb);
	}
	
	/**
	 * 根据告知id查找符合word的数据
	 * @param id
	 * @return
	 */
	public XZCF_JycfCssbEntity findWordByLaId(Long gzid) {
		return ybcfCssbblDao.findWordByLaId(gzid);
	}
}
