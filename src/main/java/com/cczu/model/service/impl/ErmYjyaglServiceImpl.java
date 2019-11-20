package com.cczu.model.service.impl;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.system.utils.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IErmYjyaglDao;
import com.cczu.model.entity.AQJG_SafetyRecord;
import com.cczu.model.service.IErmYjyaglService;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.ExportExcel;


@Transactional(readOnly=true)
@Service("ErmYjyaglService")
public class ErmYjyaglServiceImpl implements IErmYjyaglService {
	
	@Resource
	private IErmYjyaglDao ermYjyaglDao;

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=ermYjyaglDao.dataGrid(mapData);
		int getTotalCount=ermYjyaglDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public AQJG_SafetyRecord findById(Long id) {
		return ermYjyaglDao.findById(id);
	}

	@Override
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, Map<String, Object> mapData) {
		// TODO Auto-generated method stub		
		if("1".equals(mapData.get("usertype").toString())){
			String[] title={"备案类别","备案日期","备案经手人","备案意见","备注"};  
			String[] keys={"m3","m2","m7","m5","m8"};
			if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
				title = mapData.get("coltext").toString().split(",") ;
				keys = mapData.get("colval").toString().split(",") ;
			}
			String fileName="应急预案管理表.xls";
			List<Map<String, Object>> list=ermYjyaglDao.getExcel(mapData);
			new ExportExcel(fileName, title, keys, list, response);
		}else{
			if("1".equals(mapData.get("cxtype").toString())){
				String[] title={"备案类别","备案日期","备案经手人","备案意见","备注"};  
				String[] keys={"m3","m2","m7","m5","m8"};
				if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
					title = mapData.get("coltext").toString().split(",") ;
					keys = mapData.get("colval").toString().split(",") ;
				}
				String fileName="应急预案管理表.xls";
				List<Map<String, Object>> list=ermYjyaglDao.getExcel(mapData);
				new ExportExcel(fileName, title, keys, list, response);
			}else{
				String[] title={"企业","备案类别","备案日期","备案经手人","备案意见","备注"};  
				String[] keys={"qynm","m3","m2","m7","m5","m8"};
				if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
					if(!mapData.get("colval").toString().equals("qynm")){
						title =("企业名称,"+mapData.get("coltext").toString()).split(",") ;
						keys = ("qynm,"+mapData.get("colval").toString()).split(",") ;
					}
				}
				String fileName="应急预案管理表.xls";
				List<Map<String, Object>> list=ermYjyaglDao.getExcel(mapData);
				new ExportExcel(fileName, title, keys, list, response, true);
			}
		}
	}
	
	@Override
	public String getqylistapp(Map<String, Object> tmap) {
		return JsonMapper.toJsonString(ermYjyaglDao.getqylistapp(tmap));
	}

	@Override
	public Map<String,Object> exinExcel(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String,Object> resultmap = new HashMap<String, Object>();
		int result = 0;
		ExinExcel exin = new ExinExcel();
		List<List<Object>> list = exin.exinExcel(map.get("filename").toString(), (InputStream) map.get("content"));
		int i = 0, error = 0;
		if (list.size() > 3) {
			result = 0;
			resultmap.put("total", list.size()-3);
			resultmap.put("returncode", "success");
			for (List<Object> bis : list) {
				if(i<=2){ //跳过前三行
					i++;
					continue;
				}
				try{
					AQJG_SafetyRecord aqba = new AQJG_SafetyRecord();
					Timestamp t = DateUtils.getSysTimestamp();
					aqba.setS1(t);
					aqba.setS2(t);
					aqba.setS3(0);
					aqba.setM1(bis.get(0).toString());
					aqba.setM2(DateUtils.getTimestampFromStr(bis.get(1).toString()));
					aqba.setM7(bis.get(2).toString());
					aqba.setM5(bis.get(3).toString());
					aqba.setM8(bis.get(4).toString());
					aqba.setM3("yjya");
					aqba.setUserid(Long.parseLong(UserUtil.getCurrentUser().getId().toString()));
					ermYjyaglDao.addInfo(aqba);
					result++;
				}catch(Exception e){
					error++;
				}
				resultmap.put("success",result);
				resultmap.put("error", error);
			}
		}else if(list.size()==3){
			resultmap.put("success",result);
			resultmap.put("error", error);
			resultmap.put("returncode", "warn");
		}else if(list.size()<3){
			resultmap.put("success",result);
			resultmap.put("error", error);
			resultmap.put("returncode", "ext");
		}
		if(Integer.valueOf(resultmap.get("success").toString())==0){
			resultmap.put("returncode", "warn");
		}
		return resultmap;

	}
}
