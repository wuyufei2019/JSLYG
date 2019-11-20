package com.cczu.model.lydw.dao;

import com.cczu.model.lydw.entity.LYDW_DZWL;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 电子围栏
 * @author jason
 * @date 2017年8月3日
 */
@Repository("LYDW_DzwlDao")
public class LYDW_DzwlDao extends BaseDao<LYDW_DZWL, Long> {

	/**
	 * 分页查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql = "SELECT TOP " + mapData.get("pageSize") + " * FROM ("+
		           "SELECT ROW_NUMBER() OVER(order by id) rownum, * FROM lydw_dzwl WHERE 0 = 0 "+content +") a "+
				   "WHERE rownum >" + mapData.get("pageSize") + "*("+mapData.get("pageNo")+"-1) ";
		List<Map<String,Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 分页总数
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) FROM lydw_dzwl WHERE 0 = 0 "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	/**
	 * 电子围栏总览
	 */
	public List<Map<String,Object>> dzwlData() {
		String sql = " SELECT * FROM lydw_dzwl where mappoint <> '' ";
		List<Map<String,Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 电子围栏总览
	 */
	public List<Map<String,Object>> wljson() {
		String sql = " SELECT DISTINCT name FROM lydw_dzwl where mappoint <> '' ";
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
		if(mapData.get("name")!=null&&mapData.get("name")!=""){
			content = content + "AND name like '%"+mapData.get("name")+"%' ";
		}
		if(mapData.get("floor")!=null&&mapData.get("floor")!=""){
			content = content + "AND floor = '"+mapData.get("floor")+"' ";
		}
		return content;
	}
	
}
