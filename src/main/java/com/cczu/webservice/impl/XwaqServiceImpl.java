package com.cczu.webservice.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.YHPC_Observations_Main;
import com.cczu.model.entity.YHPC_Observations_Sec;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.YhpcObserveCountService;
import com.cczu.model.service.YhpcObserveService;
import com.cczu.model.service.YhpcUnsafeActService;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.system.service.DepartmentService;
import com.cczu.webservice.XwaqService;
import com.fasterxml.jackson.databind.JavaType;

@WebService(endpointInterface = "com.cczu.webservice.XwaqService")
public class XwaqServiceImpl implements XwaqService {

	@Autowired
	private YhpcUnsafeActService yhpcUnsafeActService;//不安全行为
	@Autowired
	private YhpcObserveService yhpcObserveService;//观察记录
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;//企业service
	@Autowired
	private DepartmentService departmentService;//部门service
	@Autowired
	private YhpcObserveCountService yhpcObserveCountService;//观察统计分析service
	
	/**
	 * 行为类型list
	 * str1（企业id）
	 */
	@Override
	public String Xwlx(String str1) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,企业id为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			tmap.put("qyid", str1);
			map.put("data", JsonMapper.getInstance().toJson(yhpcUnsafeActService.dataGridXwlxApp(tmap)));
			System.out.println(map);
		}
		return JsonMapper.getInstance().toJson(map);
	}

	/**
	 * 添加观察记录
	 * str1：观察记录主表(yhpc_observations_main)  str2:观察记录副表list(对象yhpc_observations_sec) 
	 */
	@Override
	public String addgcjl(String str1, String str2) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("status", "正常");
			map.put("result", "正常");
			map.put("data", "添加成功");
			YHPC_Observations_Main main=(YHPC_Observations_Main)JsonMapper.fromJsonString(str1, YHPC_Observations_Main.class);
			//添加观察记录主表
			yhpcObserveService.addMainForApp(main);
			
			//添加观察记录副表
			if(!StringUtils.isEmpty(str2)){
				JsonMapper jsonMapper=new JsonMapper();
				JavaType javatype = jsonMapper.createCollectionType(ArrayList.class,YHPC_Observations_Sec.class);
				List<YHPC_Observations_Sec> seclist = jsonMapper.fromJson(str2, javatype);
				if(seclist.size()>0){
					for(YHPC_Observations_Sec sec:seclist){
						sec.setID1(main.getID());
						yhpcObserveService.addSecForApp(sec);
					}
				}
			}
		} catch (Exception e) {
			map.put("status", "异常");
			map.put("result", "异常");
			map.put("data", "网络异常!" + e.getMessage());
		}
		return JsonMapper.getInstance().toJson(map);
	}

	/**
	 * 观察记录页面
	 * str1：页码  str2:页数  str3:企业id str4:观察时间起  str5:观察时间止
	 */
	@Override
	public String Gcjl(String str1, String str2, String str3, String str4, String str5) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,页码为空");
		}
		if(str2.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,页数为空");
		}
		if(str3.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,企业id为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			tmap.put("pageNo", str1);
			tmap.put("pageSize", str2);
			tmap.put("starttime", str4);
			tmap.put("endtime", str5);
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(Long.parseLong(str3));
			if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
				tmap.put("fid", str3);
			else
				tmap.put("qyid", str3);
			map.put("data", JsonMapper.getInstance().toJson(yhpcObserveService.dataGrid(tmap)));
		}
		return JsonMapper.getInstance().toJson(map);
	}

	/**
	 * 观察记录详细页面
	 * str1：观察记录id
	 */
	@Override
	public String GcjlView(String str1) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,观察记录id为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			tmap.put("pid", str1);
			map.put("data", JsonMapper.getInstance().toJson(yhpcObserveService.findObserveListForApp(tmap)));
		}
		return JsonMapper.getInstance().toJson(map);
	}

	/**
	 * 观察记录统计页面
	 * str1：企业id
	 */
	@Override
	public String GcjlCount(String str1) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tmap = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,企业id为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(Long.parseLong(str1));
			if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
				tmap.put("fid", str1);
			else
				tmap.put("qyid", str1);
			map.put("data", JsonMapper.getInstance().toJson(yhpcObserveCountService.jlcount(tmap)));
		}
		return JsonMapper.getInstance().toJson(map);
	}

	/**
	 * 行为安全详细页面（即观察记录副表）
	 * str1：企业id
	 */
	@Override
	public String XwaqView(String str1) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,观察记录id为空");
		}else{
			map.put("status", "正常");
			map.put("result", "成功");
			map.put("data", JsonMapper.getInstance().toJson(yhpcObserveService.findObserveViewForApp(str1)));
		}
		return JsonMapper.getInstance().toJson(map);
	}

	/*
	 * 部门下拉
	 * str1：企业id str2：用户类型(usertype)
	 */
	@Override
	public String Depart(String str1,String str2) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		if(str1.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,企业id为空");
		}
		if(str2.equals("")){
			map.put("status", "异常");
			map.put("result", "异常,usertype为空");
		}
		try {
			map.put("status", "正常");
			map.put("result", "成功");
			map.put("data", departmentService.getAlllistForApp(str1,str2));
		} catch (Exception e) {
			map.put("status", "异常");
			map.put("result", "异常");
			map.put("data", "网络异常!" + e.getMessage());
		}
		return JsonMapper.getInstance().toJson(map);
	}

}
