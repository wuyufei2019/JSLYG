package com.cczu.aqzf.model.dao;

import com.cczu.aqzf.model.entity.AQZF_WfxwClEntity;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Repository("AqzfWfxwClDao")
public class AqzfWfxwClDao extends BaseDao<AQZF_WfxwClEntity, Long> {
	/**
	 * 查询裁量list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql = "SELECT TOP " + mapData.get("pageSize") + "a.* FROM ("+
		           "SELECT ROW_NUMBER() OVER(order by m5) rownum,* FROM aqzf_wfxwcl WHERE 1=1 "+content +") a "+
				   "WHERE rownum >" + mapData.get("pageSize") + "*("+mapData.get("pageNo")+"-1) ";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 查询list的个数
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) FROM aqzf_wfxwcl WHERE 1=1 "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String content(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("id1")!=null&&mapData.get("id1")!=""){
			content = content +" AND id1 = "+mapData.get("id1")+" "; 
		}
		if(mapData.get("m6") == null || mapData.get("m6").equals("") || mapData.get("m6").equals("1")) {
			content = content + " and (m6 is null or m6 = '' or m6 =1)";
		}else if(mapData.get("m6").equals("2")){
			content = content + " and m6 = " + mapData.get("m6") + " ";
		}
		return content;
	}

	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteInfo(long id) {
		String sql=" delete aqzf_wfxwcl WHERE id="+id;
		updateBySql(sql);
	}
	
	/**
	 * 根据id1删除
	 * @param id1
	 */
	public void deleteInfoByid1(long id1) {
		String sql=" delete aqzf_wfxwcl WHERE id1="+id1;
		updateBySql(sql);
	}
	
	/**
	 * 添加裁量信息
	 */
	public void addInfo(AQZF_WfxwClEntity wfxwcl) {
		save(wfxwcl);
	}
	
	/**
	 * 根据id查找裁量信息
	 * @param id
	 * @return
	 */
	public AQZF_WfxwClEntity findInfoById(long id) {	
		AQZF_WfxwClEntity a = find(id);
		flush();
		clear();
		return a;
	}
	
	/**
	 * 修改
	 */
	public void updateInfo(AQZF_WfxwClEntity wfxwcl) {
		save(wfxwcl);
	}
	
	public List<Map<String, Object>> getAllByid1(long id1) {
		String sql = "SELECT * FROM aqzf_wfxwcl WHERE id1 = "+id1;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
}
