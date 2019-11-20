package com.cczu.aqzf.model.dao;

import com.cczu.aqzf.model.entity.XZCF_SgxwtzEntity;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Repository("XzcfSgxwtzDao")
public class XzcfSgxwtzDao extends BaseDao<XZCF_SgxwtzEntity, Long> {

	/**
	 * 查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql = "SELECT TOP " + mapData.get("pageSize") + "a.* FROM ("+
		           "SELECT ROW_NUMBER() OVER(order by a.id desc) rownum,a.*,b.m1 qyname FROM xzcf_sgxwtz a LEFT JOIN bis_enterprise b ON b.id = a.id2 left join t_user u on a.id1 = u.id "
		           + "WHERE a.s3 = 0 AND b.s3=0 "+content +") a "+
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
		String sql="SELECT COUNT(*) FROM xzcf_sgxwtz a LEFT JOIN bis_enterprise b ON b.id = a.id2 left join t_user u on a.id1 = u.id WHERE a.s3 = 0 and b.s3 = 0 "+content;
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
			content = content + "AND b.m1 like '%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content + "AND a.m1 like '%"+mapData.get("m1")+"%' "; 
		}
		if(mapData.get("m2")!=null&&mapData.get("m2")!=""){
			content = content + "AND a.m2 >= '"+mapData.get("m2")+" 00:00:00' and a.m2 <= '"+mapData.get("m2")+" 23:59:59' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content +" AND u.xzqy like '"+mapData.get("xzqy")+"%' ";
		}
		if(mapData.get("flag")!=null&&mapData.get("flag")!=""){
			content = content + "AND a.flag = '"+mapData.get("flag")+"' "; 
		}
		return content;
	}

	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteInfo(long id) {
		String sql=" UPDATE xzcf_sgxwtz SET s3=1 WHERE id="+id;
		updateBySql(sql);
	}
	
	/**
	 * 修改状态
	 */
	public void updateZt(String id) {
		String sql=" UPDATE xzcf_sgxwtz SET flag=1 WHERE id="+id;
		updateBySql(sql);
	}
	
	/**
	 * 添加信息
	 * @param zfry
	 */
	public void addInfo(XZCF_SgxwtzEntity zfry) {
		save(zfry);
	}
	
	/**
	 * 根据id查找信息
	 * @param id
	 * @return
	 */
	public XZCF_SgxwtzEntity findInfoById(long id) {	
		XZCF_SgxwtzEntity a = find(id);
		flush();
		clear();
		return a;
	}
	
	/**
	 * 修改
	 * @param zfry
	 */
	public void updateInfo(XZCF_SgxwtzEntity zfry) {
		save(zfry);
	}
	
	/**
	 * 根据id查找详细信息
	 * @param id
	 * @return
	 */
	public Map<String, Object> findAllbyid(long id) {
		String sql = "select a.*,b.m1 qyname from xzcf_sgxwtz a LEFT JOIN bis_enterprise b ON b.id = a.id2 WHERE a.id="+id;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list.get(0);
	}
	
	public List<Map<String, Object>> dcztjson(String xzqy) {
		String sql = "select a.id,a.m1 text FROM xzcf_sgxwtz a LEFT JOIN bis_enterprise b ON b.id = a.id2 left join t_user u on a.id1 = u.id "
				+ "WHERE a.s3 = 0 AND b.s3=0";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
}
