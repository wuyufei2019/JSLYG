package com.cczu.aqzf.model.dao;

import com.cczu.aqzf.model.entity.AQZF_RyfzEntity;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Repository("AqzfRyfzDao")
public class AqzfRyfzDao extends BaseDao<AQZF_RyfzEntity, Long> {

	/**
	 * 人员分组记录list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = "SELECT TOP " + mapData.get("pageSize") + " a.* FROM ("+
		           "SELECT ROW_NUMBER() OVER(order by a.id) rownum,a.* "+
                   "FROM aqzf_ryfz a left join t_user b on b.ID=a.id1 WHERE a.m1 != '' "+content+")a "+
				   "WHERE rownum >" + mapData.get("pageSize") + "*("+mapData.get("pageNo")+"-1)";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
     * 统计人员分组记录数
     * @return
     */
	public int getTotalCount(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql="SELECT COUNT(*) FROM aqzf_ryfz a left join t_user b on b.ID=a.id1 WHERE a.m1 != '' "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	/**
	 * 得到jcdylist
	 * @return
	 */
	public List<Map<String, Object>> getjcdylist(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = "SELECT a.id,a.m1 text FROM aqzf_ryfz a left join t_user b on b.ID=a.id1 WHERE a.m1 != '' "+content;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String content(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content +" AND b.xzqy = '"+mapData.get("xzqy")+"' "; 
		}
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND b.userroleflg='"+mapData.get("jglx")+"' "; 
		}
		return content;
	}
	
	/**
     * 删除
     * @param id
     */
	public void deleteInfo(long id) {
		String sql=" delete aqzf_ryfz WHERE ID="+id;
		updateBySql(sql);
	}
	
	/**
	 * 得到分组情况
	 * @return
	 */
	public List<Map<String, Object>> getFzlist(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = "SELECT a.id,a.m1,COUNT(*) m2 FROM aqzf_ryfz a LEFT JOIN t_user b ON b.ID=a.id1 LEFT JOIN aqzf_tipstaff c ON c.m8 = a.id "
				+ "WHERE a.m1 != '' AND c.s3 = 0 "+content+" GROUP BY a.id,a.m1";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
}
