package com.cczu.model.zdgl.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cczu.model.zdgl.dao.ZdglGlzdDao;
import com.cczu.model.zdgl.entity.ZDGL_GLZDEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.system.utils.UserUtil;

/**
 *  制度管理-安全管理制度Service
 *
 */
@Service("ZdglGlzdService")
public class ZdglGlzdService {

	@Resource
	private ZdglGlzdDao zdglGlzdDao;
	
	/**
	 * list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=zdglGlzdDao.dataGrid(mapData);
		int getTotalCount=zdglGlzdDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	//删除信息
	public void deleteInfo(long id) {
		zdglGlzdDao.deleteInfo(id);
	}
	
	//添加信息
	public void addInfo(ZDGL_GLZDEntity glzd) {
		Timestamp t=DateUtils.getSysTimestamp();
		glzd.setS1(t);
		glzd.setS2(t);
		glzd.setS3(0);
		glzd.setID1(UserUtil.getCurrentUser().getId2());
		glzd.setUserid(Long.parseLong(UserUtil.getCurrentUser().getId().toString()));
		zdglGlzdDao.save(glzd);
	}
	
	public ZDGL_GLZDEntity find(Long id) {
		return zdglGlzdDao.find(id);
	}
	
	//更新信息
	public void updateInfo(ZDGL_GLZDEntity glzd) {
		Timestamp t=DateUtils.getSysTimestamp();
		glzd.setS2(t);
		zdglGlzdDao.save(glzd);
	}
	
	//根据id查找详细信息
	public Map<String,Object> findById(Long id) {
		return zdglGlzdDao.findById(id);
	}
	
	//导出
	public void exportExcel(HttpServletResponse response,
			Map<String, Object> mapData) {
		String fileName="安全管理制度表.xls";
		List<Map<String, Object>> list=zdglGlzdDao.getExport(mapData);
		String[] title={"制度编号","制度名称","版本号","发布日期","制度正文","编辑部门","适用部门","审核人","审核意见","审核日期","批准人","批准意见","批准日期"};  
		String[] keys={"m2","m1","m3","m4","m5","bjbm","sybm","spr","spyj","m11","pzr","pzyj","m14"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			title = mapData.get("coltext").toString().split(",") ;
			keys = mapData.get("colval").toString().split(",") ;
		}
		new ExportExcel(fileName, title, keys, list, response);
	}
	
	public Map<String, Object> getid(){
		return zdglGlzdDao.getid();
	}
}
