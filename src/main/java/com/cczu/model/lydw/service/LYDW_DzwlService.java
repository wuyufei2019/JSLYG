package com.cczu.model.lydw.service;


import com.cczu.model.lydw.dao.LYDW_DzwlDao;
import com.cczu.model.lydw.entity.LYDW_DZWL;
import com.cczu.sys.comm.mapper.JsonMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 蓝牙定位-电子围栏Service
 * @author jason
 * @date 2017年8月3日
 */
@Transactional(readOnly=true)
@Service("LYDW_DzwlService")
public class LYDW_DzwlService {

	@Resource
	private LYDW_DzwlDao lydw_dzwlDao;
	
	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=lydw_dzwlDao.dataGrid(mapData);
		int getTotalCount=lydw_dzwlDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * 电子围栏总览
	 * @return
	 */
	public String dzwlData() {
		List<Map<String, Object>> list = lydw_dzwlDao.dzwlData();
		return JsonMapper.getInstance().toJson(list);
	}

	/**
	 * 围栏json
	 */
	public String wljson() {
		List<Map<String, Object>> list = lydw_dzwlDao.wljson();
		List<Map<String, Object>> arrylist = new ArrayList<Map<String, Object>>();
		for (Map wl:list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", wl.get("name"));
			map.put("text", wl.get("name"));
			arrylist.add(map);
		}
		return JsonMapper.getInstance().toJson(arrylist);
	}

	/**
	 * 添加
	 * @param entity
	 */
	public void addInfo(LYDW_DZWL entity) {
		lydw_dzwlDao.save(entity);
	}
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public LYDW_DZWL findById(Long id) {
		return lydw_dzwlDao.find(id);
	}
	
	/**
	 * 修改
	 * @param entity
	 */
	public void updateInfo(LYDW_DZWL entity) {
		lydw_dzwlDao.save(entity);
	}
	
	/**
	 * 删除信息
	 * @param id
	 */
	public void deleteInfo(long id) {
		lydw_dzwlDao.delete(id);
	}

}
