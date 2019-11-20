package com.cczu.model.service.impl;

import com.cczu.model.dao.impl.ErmNdxljhDao;
import com.cczu.model.entity.ERM_EmergencyTrainPlanYearEntity;
import com.cczu.sys.comm.utils.ExportExcel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(readOnly=true)
@Service("ErmNdxljhService")
public class ErmNdxljhService {
	
	@Resource
	private ErmNdxljhDao ermNdxljhDao;

	/**
	 * 查询所有年度训练计划信息
	 * @return
	 */
	public List<Map<String, Object>> findAll() {
		return ermNdxljhDao.findAllInfo();
	}

	/**
	 * 添加年度训练计划信息
	 * @param erm
	 */
	public void addInfo(ERM_EmergencyTrainPlanYearEntity erm) {
		ermNdxljhDao.addInfo(erm);
		
	}

	/**
	 * 修改年度训练计划信息
	 * @param erm
	 */
	public void updateInfo(ERM_EmergencyTrainPlanYearEntity erm) {
		ermNdxljhDao.updateInfo(erm);
	}

	/**
	 * 删除年度训练计划信息
	 * @param id
	 */
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		ermNdxljhDao.deleteInfo(id);
	}

	public String content(Map<String, Object> mapData) {
		return ermNdxljhDao.content(mapData);
	}

	/**
	 * 查询数据
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=ermNdxljhDao.dataGrid(mapData);
		int getTotalCount=ermNdxljhDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 *
	 * 通过id查找年度训练计划信息
	 * @param id
	 * @return
	 */
	public Map<String, Object> findById(Long id) {
		return ermNdxljhDao.findById(id);
	}

	/**
	 * 获得导出数据
	 * @return
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		if("1".equals(mapData.get("usertype").toString())){
			String[] title=mapData.get("coltext").toString().split(",");
			String[] keys=mapData.get("colval").toString().split(",");
			if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
				title = mapData.get("coltext").toString().split(",") ;
				keys = mapData.get("colval").toString().split(",") ;
			}
			String fileName="演练计划.xls";
			List<Map<String, Object>> list=ermNdxljhDao.getExcel(mapData);
			new ExportExcel(fileName, title, keys, list, response, true);
		}else{
			String[] title=mapData.get("coltext").toString().split(",");
			String[] keys=mapData.get("colval").toString().split(",");
			if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
				title = mapData.get("coltext").toString().split(",") ;
				keys = mapData.get("colval").toString().split(",") ;
			}
			String fileName="演练计划.xls";
			List<Map<String, Object>> list=ermNdxljhDao.getExcel(mapData);
			new ExportExcel(fileName, title, keys, list, response, true);
		}
	}
}
