package com.cczu.model.service;


import com.cczu.model.dao.IIssueAqscdtDao;
import com.cczu.model.dao.IIssueAqwjfbDao;
import com.cczu.model.dao.SysHomeDao;
import com.cczu.model.dao.TsWarningDataDao;
import com.cczu.model.entity.ISSUE_SafetyProductionDynamicEntity;
import com.cczu.sys.comm.mapper.JsonMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 首页获取展示数据jsonService
 * @author jason
 * @date 2017年8月3日
 */
@Transactional(readOnly=true)
@Service("SysHomeService")
public class SysHomeService {

	@Resource
	private SysHomeDao syshomedao;
	@Resource
	private IIssueAqwjfbDao issueaqwjfbdao;
	@Resource
	private IIssueAqscdtDao issueaqscdtdao;
	@Resource
	private TsWarningDataDao tswarningdatadao;
	
	public List<Object> getDate(Map<String, Object>map){
		return syshomedao.findInfo(map);
	}

	public List<Map<String, Object>> getDate2(Map<String, Object>map){
		return syshomedao.findInfo2(map);
	}

	public String yhpcs(){
		List<Map<String, Object>> list1=syshomedao.weekyhpc("1");//红
		List<Map<String, Object>> list2=syshomedao.weekyhpc("2");//橙
		List<Map<String, Object>> list3=syshomedao.weekyhpc("3");//黄
		List<Map<String, Object>> list4=syshomedao.weekyhpc("4");//蓝
		List<Map<String, Object>> list5=syshomedao.weekzcd();//自定义
		Map<String, Object> map=new HashMap<>();
		map.put("red",cal(list1));
		map.put("orange",cal(list2));
		map.put("yellow",cal(list3));
		map.put("blue",cal(list4));
		map.put("custpoint",cal(list5));

		return JsonMapper.getInstance().toJson(map);
	}

	public String yhzgs(){
		List<Map<String, Object>> list1=syshomedao.weekyhzg("1");//红
		List<Map<String, Object>> list2=syshomedao.weekyhzg("2");//橙
		List<Map<String, Object>> list3=syshomedao.weekyhzg("3");//黄
		List<Map<String, Object>> list4=syshomedao.weekyhzg("4");//蓝
		List<Map<String, Object>> list5=syshomedao.weekzczg();//自定义
		Map<String, Object> map=new HashMap<>();
		map.put("red",cal(list1));
		map.put("orange",cal(list2));
		map.put("yellow",cal(list3));
		map.put("blue",cal(list4));
		map.put("custpoint",cal(list5));

		return JsonMapper.getInstance().toJson(map);
	}

	//处理数据
	public  List<String> cal(List<Map<String, Object>> list){
		List<String> colorlist=new ArrayList<>();
		if(list.size()==0){
			for(int i=0;i<7;i++){
				colorlist.add("0");
			}
		}else{
			colorlist.add(list.get(0).get("周一").toString());
			colorlist.add(list.get(0).get("周二").toString());
			colorlist.add(list.get(0).get("周三").toString());
			colorlist.add(list.get(0).get("周四").toString());
			colorlist.add(list.get(0).get("周五").toString());
			colorlist.add(list.get(0).get("周六").toString());
			colorlist.add(list.get(0).get("周七").toString());
		}
		return colorlist;
	}

	public List<Object> getQyDate(Map<String ,Object> map){
		return syshomedao.findQyInfo(map);
	}
	
	public List<Map<String,Object>> getIssueInfo(Map<String ,Object> map){
		if(map.get("xzqy")!=null&&map.get("xzqy")!=""){
			return 	issueaqwjfbdao.dataGrid(map);
		}else{
			return 	issueaqwjfbdao.dataGrid2(map);
		}
	}
	
    public List<ISSUE_SafetyProductionDynamicEntity> getZxdtInfo(Map<String ,Object> map){
		
		return 	issueaqscdtdao.dataGrid(map);
	}
    public List<Map<String,Object>> getBjxxInfo(Map<String ,Object> map){
    	return 	tswarningdatadao.getBjxx(map);
    }
	 
    public List<Object> getDateForApp(Map<String, Object>map){
		return syshomedao.findInfoForApp(map);
	}

    /**
     * 危险作业报备统计
     * @param mapData
     * @return
     */
	public List<Map<String,Object>> wxzybbtj(Map<String, Object> mapData){
		return 	syshomedao.wxzybbtj(mapData);
	}

    /**
     * 危险作业报备走势
     * @param mapData
     * @return
     */
    public List<Map<String,Object>> wxzybbtj2(Map<String, Object> mapData){
        return 	syshomedao.wxzybbtj2(mapData);
    }

    /**
     * 大数据到期提醒
     * @param mapData
     * @return
     */
    public List<Map<String,Object>> dqtx(Map<String, Object> mapData){
        return 	syshomedao.dqtx(mapData);
    }

	/**
	 * 大数据预警信息
	 * @param mapData
	 * @return
	 */
	public List<Map<String,Object>> alarm(Map<String, Object> mapData){
		return 	syshomedao.alarm(mapData);
	}
}
