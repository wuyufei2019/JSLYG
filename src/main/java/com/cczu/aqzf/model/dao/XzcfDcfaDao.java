package com.cczu.aqzf.model.dao;

import com.cczu.aqzf.model.entity.XZCF_DCFAEntity;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Repository("XzcfDcfaDao")
public class XzcfDcfaDao extends BaseDao<XZCF_DCFAEntity, Long>{

	/**
	 * 查询调查报告list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql = "SELECT TOP " + mapData.get("pageSize") + "a.* FROM ("+
		           "SELECT ROW_NUMBER() OVER(order by a.id desc) rownum,a.* from xzcf_dcfa a left join xzcf_ybcflasp b on b.id = a.id3 left join bis_enterprise c on c.id = b.id1 left join t_user u on b.userid = u.id WHERE a.s3 = 0 and b.s3=0 and c.s3=0 "+content +") a "+
				   "WHERE rownum >" + mapData.get("pageSize") + "*("+mapData.get("pageNo")+"-1) ORDER BY a.id";
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
		String sql="SELECT COUNT(a.id) FROM xzcf_dcfa a left join xzcf_ybcflasp b on b.id = a.id3 left join bis_enterprise c on c.id = b.id1 left join t_user u on b.userid = u.id WHERE a.s3 = 0 and b.s3=0 and c.s3=0 "+content;
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
			content = content + " AND a.dsperson like '%"+mapData.get("qyname")+"%' ";
		}
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content + " AND a.m1 >='"+mapData.get("m1")+"' "; 
		}
		if(mapData.get("m2")!=null&&mapData.get("m2")!=""){
			content = content + " AND a.m2 <='"+mapData.get("m2")+"' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content +" AND u.xzqy = '"+mapData.get("xzqy")+"' "; 
		}
		return content;
	}

	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteInfo(long id) {
		String sql=" UPDATE xzcf_dcfa SET s3=1 WHERE id="+id;
		updateBySql(sql);
	}

	/**
	 * 根据id删除
	 * @param id
	 */
	public void updateLaspInfo(long id) {
		String sql=" update b set faflag=0  from  xzcf_dcfa  a LEFT JOIN xzcf_ybcflasp  b on  b.id=a.id3 where a.id= "+id;
		updateBySql(sql);
	}
	
	/**
	 * 添加调查报告信息
	 * @param zfry
	 */
	public void addInfo(XZCF_DCFAEntity zfry) {
		save(zfry);
	}
	
	/**
	 * 根据id查找调查报告信息
	 * @param id
	 * @return
	 */
	public XZCF_DCFAEntity findInfoById(long id) {	
		XZCF_DCFAEntity a = find(id);
		flush();
		clear();
		return a;
	}
	
	/**
	 * 修改
	 * @param zfry
	 */
	public void updateInfo(XZCF_DCFAEntity zfry) {
		save(zfry);
	}
}
