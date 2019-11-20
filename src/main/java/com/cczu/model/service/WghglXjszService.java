package com.cczu.model.service;

import com.cczu.model.dao.YhpcCheckPlanDao;
import com.cczu.model.entity.YHPC_CheckPlanEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 巡检班次任务设置
 * @author zpc
 */
@Service("WghglXjszService")
public class WghglXjszService {

	@Resource
	private YhpcCheckPlanDao yhpcCheckPlanDao;

	/**
	 * 查询巡检班次任务list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=yhpcCheckPlanDao.wgbcdataGrid(mapData);
		int getTotalCount=yhpcCheckPlanDao.wgbcgetTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * 查询网格点list
	 * @param map
	 * @return
	 */
	public Map<String, Object> jcddataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=yhpcCheckPlanDao.wgddataGrid(mapData);
		int getTotalCount=yhpcCheckPlanDao.getwgdTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * 根据网格id获取该网格直属网格点
	 * @param mapData
	 * @return
	 */
	public List<Map<String,Object>> getWgdByWgid(long wgid) {
		return yhpcCheckPlanDao.getWgdByWgid(wgid);
	}

	/**
	 * 添加网格点班次任务
	 * @param zfry
	 */
	public long addInfo(YHPC_CheckPlanEntity bcrw) {
		yhpcCheckPlanDao.save(bcrw);
		return bcrw.getID();
	}

	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteInfo(long id) {
		yhpcCheckPlanDao.deleteInfo(id);
	}

	/**
	 * 根据id1删除检查点中间表
	 * @param parseLong
	 */
	public void deletexjbcjcdInfo(long id1) {
		yhpcCheckPlanDao.deletexjbcjcdInfo(id1);
	}
	
	/**
	 * 根据id查找巡检班次任务
	 * @param id
	 * @return
	 */
	public YHPC_CheckPlanEntity findById(Long id) {
		return yhpcCheckPlanDao.find(id);
	}

	/**
	 * 修改
	 * @param zfry
	 */
	public void updateInfo(YHPC_CheckPlanEntity bcrw) {
		yhpcCheckPlanDao.save(bcrw);
	}

	/**
	 * 查看网格员的班次 app
	 * @return
	 */
	public List<Map<String,Object>> wgybcForApp(String xzqy) {
		return yhpcCheckPlanDao.wgybcForApp(xzqy);
	}
}
