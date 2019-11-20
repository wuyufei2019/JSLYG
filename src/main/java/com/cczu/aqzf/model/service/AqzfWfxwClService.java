package com.cczu.aqzf.model.service;

import com.cczu.aqzf.model.dao.AqzfWfxwClDao;
import com.cczu.aqzf.model.entity.AQZF_WfxwClEntity;
import com.cczu.sys.comm.mapper.JsonMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("AqzfWfxwClService")
public class AqzfWfxwClService {

	@Resource
	private AqzfWfxwClDao aqzfWfxwClDao;
	
	/**
	 * 查询裁量list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=aqzfWfxwClDao.dataGrid(mapData);
		int getTotalCount=aqzfWfxwClDao.getTotalCount(mapData);
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
		aqzfWfxwClDao.deleteInfo(id);
	}
	
	/**
	 * 根据id1删除
	 * @param id1
	 */
	public void deleteInfoByid1(long id1) {
		aqzfWfxwClDao.deleteInfoByid1(id1);
	}
	
	/**
	 * 添加裁量信息
	 */
	public void addInfo(AQZF_WfxwClEntity wfxwcl) {
		aqzfWfxwClDao.addInfo(wfxwcl);
	}

	/**
	 * 根据id查找裁量信息
	 * @param id
	 * @return
	 */
	public AQZF_WfxwClEntity findById(Long id) {
		return aqzfWfxwClDao.findInfoById(id);
	}
	
	/**
	 * 修改
	 */
	public void updateInfo(AQZF_WfxwClEntity wfxwcl) {
		aqzfWfxwClDao.updateInfo(wfxwcl);
	}
	
	public String getAllByid1(long id1) {
		return JsonMapper.getInstance().toJson(aqzfWfxwClDao.getAllByid1(id1));
	}
}
