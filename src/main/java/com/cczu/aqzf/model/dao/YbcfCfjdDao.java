package com.cczu.aqzf.model.dao;

import com.cczu.aqzf.model.entity.XZCF_JycfCfjdEntity;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Repository("YbcfCfjdDao")
public class YbcfCfjdDao extends BaseDao<XZCF_JycfCfjdEntity, Long> {

	/**
	 * 查询处罚决定list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql = "SELECT TOP " + mapData.get("pageSize") + "a.* FROM ("+
		           "SELECT ROW_NUMBER() OVER(order by b.id desc) rownum,a.* FROM xzcf_jycfcfjd a LEFT JOIN xzcf_jycfinfo b ON a.id1 = b.id left join t_user u on b.userid = u.id "
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
		String sql="SELECT COUNT(*) FROM xzcf_jycfcfjd a LEFT JOIN xzcf_jycfinfo b ON a.id1 = b.id left join t_user u on b.userid = u.id "
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
		if(mapData.get("dsr")!=null&&mapData.get("dsr")!=""){
			content = content + "AND ( a.m2 like '%"+mapData.get("dsr")+"%' or a.m8 like '%"+mapData.get("dsr")+"%' )"; 
		}
		if(mapData.get("m0")!=null&&mapData.get("m0")!=""){
			content = content + "AND a.m0 like '%"+mapData.get("m0")+"%' "; 
		}
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content + "AND a.m1 = '"+mapData.get("m1")+"' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content +" AND u.xzqy like '"+mapData.get("xzqy")+"%' ";
		}
		return content;
	}

	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteInfo(long id) {
		String sql=" UPDATE xzcf_jycfcfjd SET s3=1 WHERE id="+id;
		updateBySql(sql);
	}
	
	/**
	 * 根据id改变告知书状态
	 * @param id
	 */
	public void updateLaspInfo(long id) {
		String sql=" update b set jdflag=0  from  xzcf_jycfcfjd  a LEFT JOIN xzcf_jycfinfo b on b.id=a.id1 where a.id= "+id;
		updateBySql(sql);
	}
	
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public XZCF_JycfCfjdEntity findInfoById(long id) {	
		XZCF_JycfCfjdEntity a = find(id);
		flush();
		clear();
		return a;
	}
	
	/**
	 * 根据告知书id获取word数据
	 * @param laid
	 * @return
	 */
	public XZCF_JycfCfjdEntity findWordByLaId(long gzid) {
		String sql="select a.* FROM xzcf_jycfcfjd a left join xzcf_jycfinfo b on a.id1 = b.id where a.s3=0 and b.s3=0 and b.id ="+gzid;
		List<XZCF_JycfCfjdEntity> list=findBySql(sql,null,XZCF_JycfCfjdEntity.class);
		return list.get(0);
	}
}
