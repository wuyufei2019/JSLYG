package com.cczu.aqzf.model.dao;

import com.cczu.aqzf.model.entity.AQZF_TipstaffEntity;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Repository("AqzfZfryDao")
public class AqzfZfryDao extends BaseDao<AQZF_TipstaffEntity, Long> {

	/**
	 * 查询执法人员list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql = "SELECT TOP " + mapData.get("pageSize") + "a.* FROM ("+
		           "SELECT ROW_NUMBER() OVER(order by a.m1) rownum, a.*,c.m1 zm FROM aqzf_tipstaff a left join t_user b on b.ID=a.id1 left join aqzf_ryfz c on a.m8 = c.id WHERE a.s3 = 0 "+content +") a "+
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
		String sql="SELECT COUNT(*) FROM aqzf_tipstaff a left join t_user b on b.ID=a.id1 left join aqzf_ryfz c on a.m8 = c.id WHERE a.s3 = 0 "+content;
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
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content +" AND b.xzqy = '"+mapData.get("xzqy")+"' "; 
		}
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND b.userroleflg='"+mapData.get("jglx")+"' "; 
		}
		if(mapData.get("xm")!=null&&mapData.get("xm")!=""){
			content = content + "AND a.m1 like'%"+mapData.get("xm")+"%' "; 
		}
		if(mapData.get("zc")!=null&&mapData.get("zc")!=""){
			content = content + "AND a.m4 like'%"+mapData.get("zc")+"%' "; 
		}
		if(mapData.get("m8")!=null&&mapData.get("m8")!=""){
			content = content + "AND a.m8 = '"+mapData.get("m8")+"' "; 
		}
		if(mapData.get("apptj")!=null&&mapData.get("apptj")!=""){
			content = content + "AND (a.m1 like'%"+mapData.get("apptj")+"%' OR a.m3 like'%"+mapData.get("apptj")+"%')"; 
		}
		return content;
	}

	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteInfo(long id) {
		String sql=" UPDATE aqzf_tipstaff SET s3=1 WHERE id="+id;
		updateBySql(sql);
	}
	
	/**
	 * 添加执行人员信息
	 * @param zfry
	 */
	public void addInfo(AQZF_TipstaffEntity zfry) {
		save(zfry);
	}
	
	/**
	 * 根据id查找执行人员信息
	 * @param id
	 * @return
	 */
	public AQZF_TipstaffEntity findInfoById(long id) {
		AQZF_TipstaffEntity a = find(id);
		flush();
		clear();
		return a;
	}
	
	/**
	 * 根据id查找执行人员详情信息
	 * @param id
	 * @return
	 */
	public Map<String,Object> findAllById(long id) {	
		String sql="SELECT a.*,b.m1 zm,(CASE a.m9 WHEN '1' THEN '处室' WHEN '2' THEN '街道' ELSE '' END)gxlx FROM aqzf_tipstaff a left join aqzf_ryfz b on a.m8 = b.id WHERE a.s3 = 0 AND a.id = "+id;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list.get(0);
	}
	
	/**
	 * 根据m1查找执行人员全部信息
	 */
	public AQZF_TipstaffEntity findBym1(String m1) {
		String sql="SELECT * FROM aqzf_tipstaff WHERE s3 = 0 AND m1 = '"+m1+"'";
		List<AQZF_TipstaffEntity> list=findBySql(sql, null, AQZF_TipstaffEntity.class);
		if(list.size()<1){
			return null;
		}
		return list.get(0);
	}
	
	/**
	 * 修改
	 * @param zfry
	 */
	public void updateInfo(AQZF_TipstaffEntity zfry) {
		save(zfry);
	}
	
	/**
	 * 获得导出list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT a.*,c.m1 zm,(CASE a.m9 WHEN '1' THEN '处室' WHEN '2' THEN '街道' ELSE '' END)gxlx FROM aqzf_tipstaff a left join t_user b on b.ID=a.id1 left join aqzf_ryfz c on a.m8 = c.id WHERE a.s3 = 0 "+content+" order by a.m1";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}

	/**
	 * 获得执法人员list填充下拉框
	 * @return
	 */
	public List<Map<String, Object>> getZfryIdlist(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT a.id as id,a.m1 as text,a.m1+'-'+ISNULL(a.m7,'无分组') ryfz FROM aqzf_tipstaff a left join " +
				"t_user b on b.ID=a.id1 left join aqzf_ryfz c on a.m8 = c.id WHERE a.s3 = 0 "+content+" order by a.m8";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
	
	/**
	 * 根据姓名查找
	 * @param M1
	 * @return
	 */
	public AQZF_TipstaffEntity findByM1(String m1) {
		String sql="SELECT * FROM aqzf_tipstaff WHERE s3 = 0 and m1='"+m1+"'";
		List<AQZF_TipstaffEntity> list=findBySql(sql, null, AQZF_TipstaffEntity.class);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

	/**
	 * 获得执法人员姓名职务list填充下拉框
	 * @return
	 */
	public List<Map<String, Object>> getZfryXmzwlist(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT a.id as id,a.m1+'-'+a.m4 as text FROM aqzf_tipstaff a left join t_user b on b.ID=a.id1 WHERE a.s3 = 0 "+content+" order by a.m1";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
	
	/**
	 * 根据分组id将人员分组置为空
	 * @param id
	 */
	public void updateByM8(String fzid) {
		String sql=" UPDATE aqzf_tipstaff SET m8='' WHERE s3 = 0 and m8 = '"+fzid+"'";
		updateBySql(sql);
	}
	
	/**
	 * 获得执法人员随机list
	 * @return
	 */
	public List<Map<String, Object>> getSjzfry(Map<String, Object> mapData) {
		String content=content(mapData);
		String content2 = "";
		if(mapData.get("rygs")!=null&&mapData.get("rygs")!=""){
			content2 = content2 + "top "+mapData.get("rygs"); 
		}
		String sql="SELECT "+content2+" a.*,a.m1+' — '+a.m7 rynamezm FROM aqzf_tipstaff a left join t_user b on b" +
				".ID=a.id1 left join aqzf_ryfz c on a.m8 = c.id WHERE a.s3 = 0 "+content+" ORDER BY NEWID()";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
	
	/**
	 * 随机匹配执法人员
	 * @return
	 */
	public List<Map<String, Object>> getSjppzfry(Map<String, Object> mapData) {
		String content = "";
		if(mapData.get("ryids")!=null&&mapData.get("ryids")!=""){
			content += " and id in ("+mapData.get("ryids")+") "; 
		}
		if(mapData.get("m9")!=null&&mapData.get("m9")!=""){
			content += " and m9 = '"+mapData.get("m9")+"' "; 
		}
		String sql="SELECT * "
				+ "FROM aqzf_tipstaff WHERE 1=1 "+content + " ORDER BY NEWID()";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
	
	/**
	 * 随机匹配企业
	 * @return
	 */
	public List<Map<String, Object>> getSjppqy(Map<String, Object> mapData) {
		String content = "";
		if(mapData.get("qyids")!=null&&mapData.get("qyids")!=""){
			content += " and a.id in ("+mapData.get("qyids")+") "; 
		}
		String sql="SELECT a.id "
				+ "FROM  bis_enterprise a LEFT JOIN t_barrio b ON b.code = a.id2 WHERE 1=1 "+content + " ORDER BY NEWID()";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
	
	/**
	 * 获得执法人员list填充下拉框 H5
	 * @return
	 */
	public List<Map<String, Object>> getZfryIdlistForH5(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT a.m1 as id,a.m1 as text,a.m3 FROM aqzf_tipstaff a left join t_user b on b.ID=a.id1 left join aqzf_ryfz c on a.m8 = c.id WHERE a.s3 = 0 "+content+" order by a.m8";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
	
	/**
	 * 单个执法人员随机
	 * @return
	 */
	public List<Map<String, Object>> rydgsj(Map<String, Object> mapData) {
		String sql="SELECT a.id,a.m1,b.m1 zname FROM aqzf_tipstaff a LEFT JOIN aqzf_ryfz b ON a.m8 = b.id "
				+ " WHERE a.m8 IN (SELECT m8 FROM aqzf_tipstaff WHERE id = "+mapData.get("ryid")+") AND a.s3 = 0 AND a.id not in ("+mapData.get("ryids")+") ORDER BY NEWID()";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
	
	/**
	 * 获取执法人员名字
	 * @return
	 */
	public List<Map<String, Object>> getZfryNames(Map<String, Object> mapData) {
		String content = "";
		if(mapData.get("ryids")!=null&&mapData.get("ryids")!=""){
			content += " and id in ("+mapData.get("ryids")+") "; 
		}
		String sql="SELECT * "
				+ "FROM aqzf_tipstaff WHERE 1=1 "+content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
}
