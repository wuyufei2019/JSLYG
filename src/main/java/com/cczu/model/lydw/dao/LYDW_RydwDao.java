package com.cczu.model.lydw.dao;

import com.cczu.model.lydw.entity.Pub_FileRoomTime;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 蓝牙定位-人员定位DAO
 * @author jason
 * @date 2017年8月3日
 */
@Repository("LYDW_RydwDao")
public class LYDW_RydwDao extends BaseDao<Pub_FileRoomTime, Long> {


	/**
	 * 查询人员实时位置
	 */
	public List<Map<String,Object>> rydwData(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = " select ry.m1 name,ry.m3 sex ,ry.m4 gw,xbwz.x,xbwz.y,xbwz.z,xbwz.xbid ,CONVERT(varchar(100), ss.uptime, 20) uptime,dep.m1 bm from bis_employee ry " +
				" LEFT JOIN lydw_emp_pubfile gk on ry.id = gk.empid " +
				" LEFT JOIN pub_fileroomtime ss on gk.fileid = ss.[file] " +
				" LEFT JOIN lydw_xbgl_zb xbwz on ss.room = xbwz.xbid " +
				" LEFT JOIN t_department dep on ry.ID4 = dep.id " +
				" where xbwz.xbid <> '' " + content;
		List<Map<String,Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 根据部门统计在线人数
	 */
	public List<Map<String,Object>> totalOnlinePoeple(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = " select dep.m1 bm , count([file]) count from pub_fileroomtime ss " +
				" LEFT JOIN lydw_emp_pubfile gk  on gk.fileid = ss.[file] " +
				" LEFT JOIN bis_employee ry on ry.id = gk.empid " +
				" LEFT JOIN t_department dep on ry.ID4 = dep.id " +
				" where 0=0 " + content +" group by dep.m1";
		List<Map<String,Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 查询绑定工卡的人员
	 */
	public List<Map<String,Object>> getYGList(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = " select ry.id, ry.m1 text , ry.m3 sex,dep.m1 dep from  bis_employee ry " +
				" LEFT JOIN lydw_emp_pubfile gk on gk.empid = ry.id " +
				" LEFT JOIN t_department dep on ry.ID4 = dep.id " +
				" where gk.id is not null  " + content ;
		List<Map<String,Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String content(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + "AND ry.id3 = "+mapData.get("qyid")+" ";
		}
		if(mapData.get("ygid")!=null&&mapData.get("ygid")!=""){
			content = content + "AND ry.id = "+mapData.get("ygid")+" ";
		}
		if(mapData.get("name")!=null&&mapData.get("name")!=""){
			content = content + "AND name like '%"+mapData.get("name")+"%' ";
		}
		if(mapData.get("floor")!=null&&mapData.get("floor")!=""){
			content = content + "AND floor = '"+mapData.get("floor")+"' ";
		}
		return content;
	}



}
