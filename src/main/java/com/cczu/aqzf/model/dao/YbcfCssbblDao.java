package com.cczu.aqzf.model.dao;

import com.cczu.aqzf.model.entity.XZCF_JycfCssbEntity;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Repository("YbcfCssbblDao")
public class YbcfCssbblDao extends BaseDao<XZCF_JycfCssbEntity, Long> {

	/**
	 * 查询陈述申辩list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql = "SELECT TOP " + mapData.get("pageSize") + "a.* FROM ("+
		           "SELECT ROW_NUMBER() OVER(order by b.id desc) rownum,a.*,b.m0 gzbh FROM xzcf_jycfcssb a LEFT JOIN xzcf_jycfinfo b ON a.id1 = b.id left join t_user u on b.userid = u.id "
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
		String sql="SELECT COUNT(*) FROM xzcf_jycfcssb a LEFT JOIN xzcf_jycfinfo b ON a.id1 = b.id left join t_user u on b.userid = u.id "
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
		if(mapData.get("m11")!=null&&mapData.get("m11")!=""){
			content = content + "AND a.m11 like '%"+mapData.get("m11")+"%' "; 
		}
		if(mapData.get("gzbh")!=null&&mapData.get("gzbh")!=""){
			content = content + "AND b.m0 like '%"+mapData.get("gzbh")+"%' "; 
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
		String sql=" UPDATE xzcf_jycfcssb SET s3=1 WHERE id="+id;
		updateBySql(sql);
	}
	
	/**
	 * 根据id改变告知书状态
	 * @param id
	 */
	public void updateLaspInfo(long id) {
		String sql=" update b set csflag=0  from  xzcf_jycfcssb  a LEFT JOIN xzcf_jycfinfo b on b.id=a.id1 where a.id= "+id;
		updateBySql(sql);
	}
	
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public XZCF_JycfCssbEntity findInfoById(long id) {
		XZCF_JycfCssbEntity a = find(id);
		flush();
		clear();
		return a;
	}
	
	/**
	 * 根据告知书id获取word数据
	 * @param laid
	 * @return
	 */
	public XZCF_JycfCssbEntity findWordByLaId(long gzid) {
		String sql="select a.* FROM xzcf_jycfcssb a left join xzcf_jycfinfo b on a.id1 = b.id where a.s3=0 and b.s3=0 and b.id ="+gzid;
		List<XZCF_JycfCssbEntity> list=findBySql(sql,null, XZCF_JycfCssbEntity.class);
		return list.get(0);
	}
}
