package com.cczu.aqzf.model.dao;

import com.cczu.aqzf.model.entity.XZCF_FkspEntity;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Repository("XzcfFkspDao")
public class XzcfFkspDao extends BaseDao<XZCF_FkspEntity, Long> {

	/**
	 * 查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql = "SELECT TOP " + mapData.get("pageSize") + "a.* FROM ("+
		           "SELECT ROW_NUMBER() OVER(order by b.id desc) rownum,a.* FROM xzcf_fksp a LEFT JOIN xzcf_ybcflasp b ON a.id1 = b.id left join t_user u on b.userid = u.id "
		           + "WHERE a.s3 = 0 AND b.s3 = 0 "+content +") a "+
				   "WHERE rownum >" + mapData.get("pageSize") + "*("+mapData.get("pageNo")+"-1)  ";
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
		String sql="SELECT COUNT(*) FROM xzcf_fksp a LEFT JOIN xzcf_ybcflasp b ON a.id1 = b.id left join t_user u on b.userid = u.id "
		           + "WHERE a.s3 = 0 AND b.s3 = 0 "+content;
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
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content + "AND a.m1 like '%"+mapData.get("m1")+"%' "; 
		}
		if(mapData.get("m2")!=null&&mapData.get("m2")!=""){
			content = content + "AND a.m2 like '%"+mapData.get("m2")+"%' "; 
		}
		if(mapData.get("m4")!=null&&mapData.get("m4")!=""){
			content = content + "AND a.m4 like '%"+mapData.get("m4")+"%' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content +" AND u.xzqy like '"+mapData.get("xzqy")+"%' ";
		}
		if(mapData.get("apptj")!=null&&mapData.get("apptj")!=""){
			content = content + "AND (a.m1 like '%"+mapData.get("apptj")+"%' OR a.m4 like '%"+mapData.get("apptj")+"%')"; 
		}
		return content;
	}

	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteInfo(long id) {
		String sql=" UPDATE xzcf_fksp SET s3=1 WHERE id="+id;
		updateBySql(sql);
	}
	
	/**
	 * 根据id改变立案审批状态
	 * @param id
	 */
	public void updateLaspInfo(long id) {
		String sql=" update b set fkspflag=0  from  xzcf_fksp  a LEFT JOIN xzcf_ybcflasp b on b.id=a.id1 where a.id= "+id;
		updateBySql(sql);
	}
	
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public XZCF_FkspEntity findInfoById(long id) {	
		XZCF_FkspEntity a = find(id);
		flush();
		clear();
		return a;
	}
	
	/**
	 * 根据立案id获取word数据
	 * @param laid
	 * @return
	 */
	public XZCF_FkspEntity findWordByLaId(long laid) {
		String sql="select a.* FROM xzcf_fksp a left join xzcf_ybcflasp b on a.id1 = b.id where a.s3=0 and b.s3=0 and b.id ="+laid;
		List<XZCF_FkspEntity> list=findBySql(sql,null,XZCF_FkspEntity.class);
		if(list.size()<1){
			return null;
		}
		return list.get(0);
	}
}
