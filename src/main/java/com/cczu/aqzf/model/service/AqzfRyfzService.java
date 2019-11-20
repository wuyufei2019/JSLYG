package com.cczu.aqzf.model.service;

import com.cczu.aqzf.model.dao.AqzfRyfzDao;
import com.cczu.aqzf.model.entity.AQZF_RyfzEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("AqzfRyfzService")
public class AqzfRyfzService {

	@Resource
	private AqzfRyfzDao aqzfRyfzDao;
	
	/**
	 * 查询人员分组记录list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=aqzfRyfzDao.dataGrid(mapData);
		int getTotalCount=aqzfRyfzDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 得到jcdylist
	 * @return
	 */
	public List<Map<String, Object>> getjcdylist(Map<String, Object> mapData) {
		return aqzfRyfzDao.getjcdylist(mapData);
	}

	/**
	 * 添加信息
	 * @param jcdy
	 */
	public void addInfo(AQZF_RyfzEntity jcdy) {
		aqzfRyfzDao.save(jcdy);
	}

	/**
	 * 删除
	 * @param parseLong
	 */
	public void deleteInfo(long id) {
		aqzfRyfzDao.deleteInfo(id);
	}
	
	/**
	 * 修改
	 * @param jcdy
	 */
	public void updateInfo(AQZF_RyfzEntity jcdy) {
		aqzfRyfzDao.save(jcdy);
	}

	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public AQZF_RyfzEntity findById(Long id) {
		return aqzfRyfzDao.find(id);
	}
	
	/**
	 * 得到分组情况
	 * @return
	 */
	public List<Map<String, Object>> getFzlist(Map<String, Object> map) {
		return aqzfRyfzDao.getFzlist(map);
	}
}
