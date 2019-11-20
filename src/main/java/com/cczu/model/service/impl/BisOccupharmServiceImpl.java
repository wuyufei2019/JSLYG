package com.cczu.model.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IBisOccupharmDao;
import com.cczu.model.dao.IBisYgxxDao;
import com.cczu.model.dao.ITdicZybflDao;
import com.cczu.model.dao.impl.BisYgxxDaoImpl;
import com.cczu.model.entity.BIS_EmployeeEntity;
import com.cczu.model.entity.BIS_OccupharmExamEntity;
import com.cczu.model.service.IBisOccupharmService;
import com.cczu.sys.comm.utils.ExportExcel;

@Transactional(readOnly=true)
@Service("BisOccupharmService")
public class BisOccupharmServiceImpl implements IBisOccupharmService {
	
	@Resource
	private IBisOccupharmDao biszybDao;
	@Resource
	private ITdicZybflDao tdiczybfldao;
	@Resource
	private BisYgxxDaoImpl bisYgxxDaoImpl;

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<BIS_OccupharmExamEntity> list = biszybDao.dataGrid(mapData);
		
		for (BIS_OccupharmExamEntity bis : list) {
			String m9 = bis.getM9();
			//按逗号分隔带有员工id的字符串，根据每个id得到员工姓名，将得到的结果进行拼接，再封装到实体中
			bis.setM9(getEmployeeName(m9));
		}
		
		int getTotalCount = biszybDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String , Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public Map<String, Object> ajdataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list = biszybDao.ajdataGrid(mapData);
		int getTotalCount = biszybDao.ajgetTotalCount(mapData);
		Map<String, Object> map = new HashMap<String , Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	
	@Override
	public List<BIS_OccupharmExamEntity> findAll(Long qyid) {
		return biszybDao.findAll(qyid);
	}

	@Override
	public void addInfo(BIS_OccupharmExamEntity bis) {
		biszybDao.addInfo(bis);
	}

	@Override
	public BIS_OccupharmExamEntity findById(Long id) {
		BIS_OccupharmExamEntity bis = biszybDao.findById(id);
		bis.setM9(bis.getM9() + "||" + getEmployeeName(bis.getM9()));
		return bis;
	}

	@Override
	public void updateInfo(BIS_OccupharmExamEntity bis) {
		biszybDao.updateInfo(bis);
	}

	@Override
	public void deleteInfo(Long id) {
		biszybDao.deleteInfo(id);
	}

	@Override
	public void exportExcel(HttpServletResponse response,
			Map<String, Object> mapData) {
		String fileName="职业病危害因素信息表.xls"; 
		List<Map<String, Object>> list=biszybDao.getExport(mapData);
		for (Map<String, Object> map : list) {
			String m9 = (String)map.get("m9");
			map.put("m9", getEmployeeName(m9));
		}
		if("1".equals(mapData.get("usertype").toString())){
			String[] title={"职业病危害类别","职业病危害名称","危害程度","可能导致的职业病","存在部门","存在岗位","接触人数","接触名单","备注"};  
			String[] keys={"m1","m2","m3","m5","m6","m7","m8","m9","m4"};
			if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
				title = mapData.get("coltext").toString().split(",") ;
				keys = mapData.get("colval").toString().split(",") ;
			}
			new ExportExcel(fileName, title, keys, list, response);
		}else{
			String[] title={"企业","职业病危害类别","职业病危害名称","危害程度","可能导致的职业病","存在部门","存在岗位","接触人数","接触名单","备注"};  
			String[] keys={"qynm","m1","m2","m3","m5","m6","m7","m8","m9","m4"};
			if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
				title = ("企业名称,"+mapData.get("coltext").toString()).split(",") ;
				keys = ("qynm,"+mapData.get("colval").toString()).split(",") ;
			}
			new ExportExcel(fileName, title, keys, list, response, true);
		}
	}

	@Override
	public BIS_OccupharmExamEntity findById2(Long id) {
		BIS_OccupharmExamEntity bis = biszybDao.findById2(id);
		String m9 = bis.getM9();
		bis.setM9(getEmployeeName(m9));
		return bis;
	}

	@Override
	public Map<String, Object> statistics(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String,Object> remap = new HashMap<String,Object>();
		List<Integer> list = new ArrayList<>();
		remap.put("whys", biszybDao.statistics(map));
		for(int i=1;i<4;i++){
			map.put("m3", i+"");
			list.add(biszybDao.ajgetTotalCount(map));
			map.remove("m3");
		}
		remap.put("yzcd", list);
		return remap;
	}
	
	@Override
	public List<Map<String, Object>> getJcmd(Map<String, Object> map) {
		
		return  bisYgxxDaoImpl.findInfoByQyID(map);
	}
	
	@Override
	public Map<String, Object> getJcmdDatagrid(Map<String, Object> map) {
		List<Map<String, Object>> list = bisYgxxDaoImpl.findInfoByQyID(map);
		int total = bisYgxxDaoImpl.getTotalCountByQyid(map);
		map.put("rows", list);
		map.put("total", total);
		return map;
	}
	
	/**
	 * 按逗号分割带有员工id的字符串，根据每个id获取相应的员工名称，使用逗号拼接员工名称
	 * 
	 * @param m9  带有员工id和逗号的字符串   (id,id1,id2...)
	 * 
	 * @return 使用逗号拼接员工名称的字符串  (小红, 小明， 小军...)
	 */
	public String getEmployeeName(String m9) {
		String m9str = "";
		if (StringUtils.isNotBlank(m9)) {
			String[] arr_m9 = m9.split(",");
			if (arr_m9.length > 1) {
				for (int i = 0; i < arr_m9.length; i++) {
					BIS_EmployeeEntity entity = bisYgxxDaoImpl.find(Long.parseLong(arr_m9[i]));
					if (i != (arr_m9.length-1)) {
						m9str += (entity.getM1()+",");
					} else {
						m9str += entity.getM1();
					}
				}
			} else if (arr_m9.length == 1) {
				BIS_EmployeeEntity entity = bisYgxxDaoImpl.find(Long.parseLong(arr_m9[0]));
				m9str += entity.getM1();
			}
		}
		return m9str;
    }
	
}
