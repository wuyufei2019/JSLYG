package com.cczu.model.service;

import com.cczu.model.dao.WghglWgxjjlDao;
import com.cczu.model.dao.WghglWgxjjlzgDao;
import com.cczu.model.dao.YhpcYhpcjlDao;
import com.cczu.model.entity.YHPC_CheckResultEntity;
import com.cczu.sys.comm.utils.ExportExcel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 网格员巡检记录
 * @author zpc
 */
@Service("WghglWgyxjjlService")
public class WghglWgyxjjlService {

	@Resource
	private WghglWgxjjlDao wghglWgxjjlDao;
	@Resource
	private YhpcYhpcjlDao yhpcYhpcjlDao;
	@Resource
	private WghglWgxjjlzgDao wghglWgxjjlzgDao;
	
	/**
	 * 网格巡检list
	 * @param map
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=wghglWgxjjlDao.dataGrid(mapData);
		int getTotalCount=wghglWgxjjlDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 上线网格员（有巡检记录的网格员）
	 * @param mapData
	 * @return
	 */
	public List<Object> getXjjlUser(String xzqy) {
		return wghglWgxjjlDao.getXjjlUser(xzqy);
	}
	
	/**
	 * 查看详细信息
	 * @param map
	 * @return
	 */
	public Map<String,Object> findInforById(Long id) {
		return wghglWgxjjlDao.findInforById(id);
	}

	//网格点巡检内容
	public Map<String, Object> xjnrdataGrid(Map<String, Object> map) {
		return null;
	}
	
	//删除信息
	public void deleteInfo(long id) {
		wghglWgxjjlDao.deleteInfo(id);
	}
	
	//删除巡检隐患记录
	public void deleteYchByYcrid(long id) {
		List<Map<String, Object>> list = yhpcYhpcjlDao.findIdByJlid(Long.toString(id));
		for (Map<String, Object> map : list) {
			wghglWgxjjlzgDao.deleteYhrByYchid(Long.parseLong(map.get("id").toString()));
			wghglWgxjjlzgDao.deleteInfo(Long.parseLong(map.get("id").toString()));
		}
	}
	
	/**
	 * 网格巡检list app
	 * @param map
	 * @return
	 */
	public Map<String, Object> dataGridForApp(Map<String, Object> mapData) {
		List<Map<String,Object>> list=wghglWgxjjlDao.dataGridForApp(mapData);
		int getTotalCount=wghglWgxjjlDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 根据网格id获取该网格点最新的巡检记录 app
	 * @return
	 */
	public Map<String, Object> getnewXjjlForApp(Long checkpointid){
		return wghglWgxjjlDao.getnewXjjlForApp(checkpointid);
	}
	
	/**
	 * 添加巡检记录 App
	 * @param ycr
	 */
	public void addycrForApp(YHPC_CheckResultEntity ycr){
		wghglWgxjjlDao.save(ycr);
	}
	
	
	/**
	 * 导出 
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="网格化巡检记录.xls";
		List<Map<String, Object>> list= wghglWgxjjlDao.getExport(mapData);
		new ExportExcel(fileName, mapData.get("coltext").toString().split(","), mapData.get("colval").toString().split(","), list, response);
	}
	
}
