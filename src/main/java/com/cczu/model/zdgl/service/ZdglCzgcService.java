package com.cczu.model.zdgl.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cczu.model.zdgl.dao.ZdglCzgcDao;
import com.cczu.model.zdgl.entity.ZDGL_CZGCEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.system.utils.UserUtil;

/**
 *  制度管理-安全操作规程Service
 *
 */
@Service("ZdglCzgcService")
public class ZdglCzgcService {

	@Resource
	private ZdglCzgcDao zdglCzgcDao;
	
	/**
	 * list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=zdglCzgcDao.dataGrid(mapData);
		int getTotalCount=zdglCzgcDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	//删除信息
	public void deleteInfo(long id) {
		zdglCzgcDao.deleteInfo(id);
	}
	
	//添加信息
	public void addInfo(ZDGL_CZGCEntity czgc) {
		Timestamp t=DateUtils.getSysTimestamp();
		czgc.setS1(t);
		czgc.setS2(t);
		czgc.setS3(0);
		czgc.setID1(UserUtil.getCurrentUser().getId2());
		czgc.setUserid(Long.parseLong(UserUtil.getCurrentUser().getId().toString()));
		zdglCzgcDao.save(czgc);
	}
	
	public ZDGL_CZGCEntity find(Long id) {
		return zdglCzgcDao.find(id);
	}
	
	//更新信息
	public void updateInfo(ZDGL_CZGCEntity czgc) {
		Timestamp t=DateUtils.getSysTimestamp();
		czgc.setS2(t);
		zdglCzgcDao.save(czgc);
	}
	
	//根据id查找详细信息
	public Map<String,Object> findById(Long id) {
		return zdglCzgcDao.findById(id);
	}
	
	//导出
	public void exportExcel(HttpServletResponse response,
			Map<String, Object> mapData) {
		String fileName="安全操作规程表.xls";
		List<Map<String, Object>> list=zdglCzgcDao.getExport(mapData);
		String[] title={"规程编号","规程名称","版本号","发布日期","规程正文","编辑部门","适用部门","审核人","审核意见","审核日期","批准人","批准意见","批准日期"};  
		String[] keys={"m2","m1","m3","m4","m5","bjbm","sybm","spr","spyj","m11","pzr","pzyj","m14"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			title = mapData.get("coltext").toString().split(",") ;
			keys = mapData.get("colval").toString().split(",") ;
		}
		new ExportExcel(fileName, title, keys, list, response);
	}
	
	public Map<String, Object> getid(){
		return zdglCzgcDao.getid();
	}
}
