package com.cczu.aqzf.model.service;

import com.cczu.aqzf.model.dao.AqzfZfryDao;
import com.cczu.aqzf.model.entity.AQZF_TipstaffEntity;
import com.cczu.sys.comm.utils.ExportExcel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("AqzfZfryService")
public class AqzfZfryService {

	@Resource
	private AqzfZfryDao aqzfZfryDao;
	
	/**
	 * 查询执法人员list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=aqzfZfryDao.dataGrid(mapData);
		int getTotalCount=aqzfZfryDao.getTotalCount(mapData);
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
		aqzfZfryDao.deleteInfo(id);
	}
	
	/**
	 * 添加执行人员信息
	 * @param zfry
	 */
	public void addInfo(AQZF_TipstaffEntity zfry) {
		zfry.setS1(new Timestamp(System.currentTimeMillis()));
		zfry.setS2(new Timestamp(System.currentTimeMillis()));
		zfry.setS3(0);
		aqzfZfryDao.addInfo(zfry);
	}

	/**
	 * 根据id查找执行人员信息
	 * @param id
	 * @return
	 */
	public AQZF_TipstaffEntity findById(Long id) {
		return aqzfZfryDao.findInfoById(id);
	}
	
	/**
	 * 根据id查找执行人员详情信息
	 * @param id
	 * @return
	 */
	public Map<String,Object> findAllById(Long id) {
		return aqzfZfryDao.findAllById(id);
	}
	
	/**
	 * 根据m1查找执行人员全部信息
	 */
	public AQZF_TipstaffEntity findBym1(String m1) {
		return aqzfZfryDao.findBym1(m1);
	}
	
	/**
	 * 修改
	 * @param zfry
	 */
	public void updateInfo(AQZF_TipstaffEntity zfry) {
		zfry.setS2(new Timestamp(System.currentTimeMillis()));
		zfry.setS3(0);
	    aqzfZfryDao.updateInfo(zfry);
	}
	
	/**
	 * 导出 
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="执法人员记录表.xls";
		List<Map<String, Object>> list=aqzfZfryDao.getExport(mapData);
		String[] title={"姓名","性别","证件号","职称","联系电话","专家类型","人员分组","管辖类型"};  
		String[] keys={"m1","m2","m3","m4","m5","m7","zm","gxlx"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			 title = (mapData.get("coltext").toString()).split(",") ;
			 keys = (mapData.get("colval").toString()).split(",") ;
			}
		new ExportExcel(fileName, title, keys, list, response);
	}
	
	/**
	 * 获得执法人员list填充下拉框
	 * @return
	 */
	public List<Map<String, Object>> getZfryIdlist(Map<String, Object> mapData) {
		return aqzfZfryDao.getZfryIdlist(mapData);
	}
	
	/**
	 * 获得执法人员姓名职务list填充下拉框
	 * @return
	 */
	public List<Map<String, Object>> getZfryXmzwlist(Map<String, Object> mapData) {
		return aqzfZfryDao.getZfryXmzwlist(mapData);
	}
	
	/**
	 * 根据分组id将人员分组置为空
	 */
	public void updateByM8(String fzid) {
	    aqzfZfryDao.updateByM8(fzid);
	}
	
	/**
	 * 获得执法人员随机list
	 * @return
	 */
	public List<Map<String, Object>> getSjzfry(Map<String, Object> mapData) {
		return aqzfZfryDao.getSjzfry(mapData);
	}
	
	/**
	 * 随机匹配执法人员
	 * @return
	 */
	public List<Map<String, Object>> getSjppzfry(Map<String, Object> mapData) {
		return aqzfZfryDao.getSjppzfry(mapData);
	}
	
	/**
	 * 随机匹配企业
	 * @return
	 */
	public List<Map<String, Object>> getSjppqy(Map<String, Object> mapData) {
		return aqzfZfryDao.getSjppqy(mapData);
	}
	
	/**
	 * 获得执法人员list填充下拉框 H5
	 * @return
	 */
	public List<Map<String, Object>> getZfryIdlistForH5(Map<String, Object> mapData) {
		return aqzfZfryDao.getZfryIdlistForH5(mapData);
	}
	
	/**
	 * 单个执法人员随机
	 * @return
	 */
	public List<Map<String, Object>> rydgsj(Map<String, Object> mapData) {
		return aqzfZfryDao.rydgsj(mapData);
	}

	/**
	 * 获取执法人员名字
	 * @param map1
	 * @return
	 */
	public List<Map<String, Object>> getZfryNames(Map<String, Object> mapData) {
		return aqzfZfryDao.getZfryNames(mapData);
	}
}
