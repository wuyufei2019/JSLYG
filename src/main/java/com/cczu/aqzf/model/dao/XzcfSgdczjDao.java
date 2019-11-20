package com.cczu.aqzf.model.dao;

import com.cczu.aqzf.model.entity.XZCF_SgdczjEntity;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Repository("XzcfSgdczjDao")
public class XzcfSgdczjDao extends BaseDao<XZCF_SgdczjEntity, Long> {

	/**
	 * 查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql = "SELECT TOP " + mapData.get("pageSize") + "a.* FROM ("+
		           "SELECT ROW_NUMBER() OVER(order by b.id desc,a.id) rownum,a.*,b.m1 dczt,c.m1 qyname FROM xzcf_sgdczj a left join xzcf_sgxwtz b on b.id = a.id1 left join bis_enterprise c on c.id = b.id2 left join t_user u on b.id1 = u.id "
		           + "WHERE a.s3 = 0 AND b.s3=0 AND c.s3=0 "+content +") a "+
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
		String sql="SELECT COUNT(*) FROM xzcf_sgdczj a left join xzcf_sgxwtz b on b.id = a.id1 left join bis_enterprise c on c.id = b.id2 left join t_user u on b.id1 = u.id WHERE a.s3 = 0 and b.s3 = 0 and c.s3 = 0 "+content;
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
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content + "AND c.m1 like '%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("dczt")!=null&&mapData.get("dczt")!=""){
			content = content + "AND b.m1 like '%"+mapData.get("dczt")+"%' "; 
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
		String sql=" UPDATE xzcf_sgdczj SET s3=1 WHERE id="+id;
		updateBySql(sql);
	}
	
	/**
	 * 添加信息
	 * @param zfry
	 */
	public void addInfo(XZCF_SgdczjEntity zfry) {
		save(zfry);
	}
	
	/**
	 * 根据id查找信息
	 * @param id
	 * @return
	 */
	public XZCF_SgdczjEntity findInfoById(long id) {	
		XZCF_SgdczjEntity a = find(id);
		flush();
		clear();
		return a;
	}
	
	/**
	 * 修改
	 * @param zfry
	 */
	public void updateInfo(XZCF_SgdczjEntity zfry) {
		save(zfry);
	}
	
	/**
	 * 根据id查找详细信息
	 * @param id
	 * @return
	 */
	public Map<String, Object> findAllbyid(long id) {
		String sql = "select a.*,b.m1 dczt,c.m1 qyname FROM xzcf_sgdczj a left join xzcf_sgxwtz b on b.id = a.id1 left join bis_enterprise c on c.id = b.id2 WHERE a.id="+id;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list.get(0);
	}
	
	public List<XZCF_SgdczjEntity> findbyTzid(Long id) {
		String sql = "SELECT * FROM xzcf_sgdczj WHERE s3 = 0 and id1="+id;
		List<XZCF_SgdczjEntity> list=findBySql(sql,null,XZCF_SgdczjEntity.class);
		return list;
	}
}
