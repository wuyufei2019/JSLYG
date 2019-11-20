package com.cczu.model.service;

import com.cczu.model.dao.AaqpxRcxxpxjlDao;
import com.cczu.model.entity.AQPX_RcxxpxjlEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.comm.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Transactional(readOnly=true)
@Service("AqpxRcxxpxjlService")
public class AqpxRcxxpxjlService {
	
	@Resource
	private AaqpxRcxxpxjlDao aqpxRcxxpxjlDao;
	

	/**
	 * 分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String, Object>> list=aqpxRcxxpxjlDao.dataGrid(mapData);
		int getTotalCount=aqpxRcxxpxjlDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * 添加信息
	 * @param sjjy
	 */
	public void addInfo(AQPX_RcxxpxjlEntity sjjy) {
		sjjy.setS1(DateUtils.getSystemTime());
		sjjy.setS2(DateUtils.getSystemTime());
		sjjy.setS3(0);
		aqpxRcxxpxjlDao.save(sjjy);
	}

	/**
	 * 删除信息
	 * @param id
	 */
	public void deleteInfo(long id) {
		aqpxRcxxpxjlDao.deleteInfo(id);
	}

	/**
	 * 按id获取对象
	 * 
	 * @param id
	 * @return
	 */
	public AQPX_RcxxpxjlEntity findById(Long id) {
		return aqpxRcxxpxjlDao.find(id);
	}

	
	/**
	 * 修改信息
	 * @param sjjy
	 */
	public void updateInfo(AQPX_RcxxpxjlEntity sjjy) {
		sjjy.setS2(DateUtils.getSystemTime());
		 aqpxRcxxpxjlDao.saveUp(sjjy);
	}

	
	/**
	 * 导出 
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String[] title={"培训类别","姓名","工作岗位","培训日期","培训内容","培训人员","培训方式","培训成绩","培训结果","备注" };  
		String[] keys={"m1_4","m1_1","m1_3","rq","m3","m4","state","m7","m8","m9"};
		String fileName="日常线下培训记录.xls";
		List<Map<String, Object>> list=aqpxRcxxpxjlDao.getExport(mapData);
		for (Map<String, Object> map : list) {
			String state = map.get("state").toString();
			if (StringUtils.isNotEmpty(state)) {
				if ("1".equals(state)) {
					map.put("state", "线上");
				} else if("2".equals(state)){
					map.put("state", "线下");
				} 
			}
		}
		new ExportExcel(fileName, title, keys, list, response,true);

	}
	
	
	/**
	 * 导出 
	 * @param response
	 * @param mapData
	 */
	public void exportExcel2(HttpServletResponse response, Map<String, Object> mapData) {
		String[] title={"企业名称","培训类别","姓名","工作岗位","培训日期","培训内容","培训人员","培训方式","培训成绩","培训结果","备注" };  
		String[] keys={"qyname","m1_4","m1_1","m1_3","rq","m3","m4","state","m7","m8","m9"};
		String fileName="日常线下培训记录.xls";
		List<Map<String, Object>> list=aqpxRcxxpxjlDao.getExport(mapData);
		new ExportExcel(fileName, title, keys, list, response,true);

	}
	
	
	
	
	
	

}
