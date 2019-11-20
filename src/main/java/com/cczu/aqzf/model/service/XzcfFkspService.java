package com.cczu.aqzf.model.service;

import com.cczu.aqzf.model.dao.XzcfFkspDao;
import com.cczu.aqzf.model.entity.XZCF_FkspEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("XzcfFkspService")
public class XzcfFkspService {

	@Resource
	private XzcfFkspDao xzcfFkspDao;
	
	/**
	 * 查询list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=xzcfFkspDao.dataGrid(mapData);
		int getTotalCount=xzcfFkspDao.getTotalCount(mapData);
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
		xzcfFkspDao.deleteInfo(id);
	}
	
	/**
	 * 删除后 立案审批的flag置0
	 * @param id
	 */
	public void updateLaspInfo(long id) {
		xzcfFkspDao.updateLaspInfo(id);
	}

	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public XZCF_FkspEntity findById(Long id) {
		return xzcfFkspDao.findInfoById(id);
	}
	
	/**
	 * 添加
	 * @param jttl
	 */
	public void addInfo(XZCF_FkspEntity jttl) {	
		jttl.setS1(new Timestamp(System.currentTimeMillis()));
		jttl.setS2(new Timestamp(System.currentTimeMillis()));
		jttl.setS3(0);
		xzcfFkspDao.save(jttl);
	}
	
	/**
	 * 修改
	 * @param jttl
	 */
	public void updateInfo(XZCF_FkspEntity jttl) {
		jttl.setS2(new Timestamp(System.currentTimeMillis()));
		jttl.setS3(0);
		xzcfFkspDao.save(jttl);
	}
	
	/**
	 * 根据立案id查找符合word的数据
	 * @param id
	 * @return
	 */
	public XZCF_FkspEntity findWordByLaId(Long laid) {
		return xzcfFkspDao.findWordByLaId(laid);
	}

	/**
	 * 获取文书导出word
	 */
	public Map<String, Object> getWsdcword(Long id, String flag) {
		XZCF_FkspEntity fksp = new XZCF_FkspEntity();
		if("la".equals(flag)){
			fksp= xzcfFkspDao.findWordByLaId(id);
		}else{
			fksp= xzcfFkspDao.findInfoById(id);
		}
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("m1", StringUtils.isEmpty(fksp.getM1())?"":fksp.getM1());
		map.put("m2", StringUtils.isEmpty(fksp.getM2())?"":fksp.getM2());
		map.put("m3", StringUtils.isEmpty(fksp.getM3())?"":fksp.getM3());
		map.put("m4", StringUtils.isEmpty(fksp.getM4())?"":fksp.getM4());
		map.put("m5", StringUtils.isEmpty(fksp.getM5())?"":fksp.getM5());
		map.put("m6", StringUtils.isEmpty(fksp.getM6())?"":fksp.getM6());
		map.put("m7", StringUtils.isEmpty(fksp.getM7())?"":fksp.getM7());
		map.put("m8", StringUtils.isEmpty(fksp.getM8())?"":fksp.getM8());
		return map;
	}
}
