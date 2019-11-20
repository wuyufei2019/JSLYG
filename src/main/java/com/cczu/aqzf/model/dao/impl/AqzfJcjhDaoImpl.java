package com.cczu.aqzf.model.dao.impl;

import com.cczu.aqzf.model.dao.IAqzfJcjhDao;
import com.cczu.aqzf.model.entity.AQZF_SafetyCheckPlanEntity;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Repository("AqzfJcjhDao")
public class AqzfJcjhDaoImpl extends BaseDao<AQZF_SafetyCheckPlanEntity, Long> implements IAqzfJcjhDao {

	@Override
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql = "SELECT TOP " + mapData.get("pageSize") + "a.* FROM ("+
		           "SELECT ROW_NUMBER() OVER(order by b.m1 DESC,convert(int,replace(b.m2,'月','')) DESC,b.m3) rownum,a.id,(CAST(b.m1 as varchar(10))+'年'+CAST(b.m2 as varchar(10)))m1,c.m1 qyname,CAST(STUFF(( SELECT ',' + m1 FROM t_barrio WHERE PATINDEX('%,' + RTRIM(code) + ',%',',' + b.m3 + ',')>0 "
		           + " ORDER BY PATINDEX('%,' + RTRIM(code) + ',%',',' + b.m3 + ',') FOR XML PATH('')), 1, 1, '') as varchar(200)) m3,b.m4,b.m5,a.m1 panduan,a.m2 zfry,tb.m1 wgname"
		           + " FROM aqzf_plan_enterprise a LEFT JOIN aqzf_safetycheckplan b ON b.id = a.id1 "
                   + " LEFT JOIN bis_enterprise c ON c.id = a.id2 "
                   + " left join t_user d on d.id=b.id1 left join t_barrio tb on tb.code = c.id2 WHERE b.s3 = 0 and c.s3 = 0 "+ content+ ") a "+
				   "WHERE rownum >" + mapData.get("pageSize") + "*("+mapData.get("pageNo")+"-1)";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) FROM aqzf_plan_enterprise a"
				+ "  left join aqzf_safetycheckplan b on a.id1=b.id"
				+ "  LEFT JOIN bis_enterprise c ON c.id = a.id2"
				+ "  left join t_user d on d.id=b.id1 left join t_barrio tb on tb.code = c.id2 WHERE b.s3 = 0 and c.s3 = 0 "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	@Override
	public int getfasl(long id1){
		String sql="SELECT COUNT(*) FROM aqzf_safetycheckscheme WHERE s3=0 and id1 = "+id1;
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
		if(mapData.get("year")!=null&&mapData.get("year")!=""){
			content = content + "AND b.m1 like'%"+mapData.get("year")+"%' "; 
		}
		if(mapData.get("month")!=null&&mapData.get("month")!=""){
			content = content + "AND b.m2 like'%"+mapData.get("month")+"%' "; 
		}
		if(mapData.get("M3")!=null&&mapData.get("M3")!=""){
			content = content + "AND b.m3 like'%"+mapData.get("M3")+"%' "; 
		}
		if(mapData.get("M4")!=null&&mapData.get("M4")!=""){
			content = content + "AND b.m4 = '"+mapData.get("M4")+"' "; 
		}
		if(mapData.get("M5")!=null&&mapData.get("M5")!=""){
			content = content + "AND b.m5 like'%"+mapData.get("M5")+"%' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content +" AND d.xzqy = '"+mapData.get("xzqy")+"' "; 
		}
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND d.userroleflg='"+mapData.get("jglx")+"' "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content + "AND c.m1 like '%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("qyfz")!=null&&mapData.get("qyfz")!=""){
			content = content +" AND c.id2 = '"+mapData.get("qyfz")+"' "; 
		}
		return content;
	}

	@Override
	public void addInfo(AQZF_SafetyCheckPlanEntity jcjh) {
		save(jcjh);
	}

	@Override
	public void deleteInfo(long id) {
		String sql=" UPDATE aqzf_safetycheckplan SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	@Override
	public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT (CAST(b.m1 as varchar(10))+'年'+CAST(b.m2 as varchar(10)))m1,c.m1 qyname,CAST(STUFF(( SELECT ',' + m1 FROM t_barrio WHERE PATINDEX('%,' + RTRIM(code) + ',%',',' + b.m3 + ',')>0 "
				+ " ORDER BY PATINDEX('%,' + RTRIM(code) + ',%',',' + b.m3 + ',') FOR XML PATH('')), 1, 1, '') as varchar(200)) m3,(case b.m4 when '1' then '工贸' when '2' then '化工' else b.m4 end) m4,b.m5,a.m2 zfry,tb.m1 wgname FROM aqzf_plan_enterprise a "
				+ " LEFT JOIN aqzf_safetycheckplan b ON b.id = a.id1 "
				+ " LEFT JOIN bis_enterprise c ON c.id = a.id2 "
				+ " left join t_user d on d.id=b.id1 left join t_barrio tb on tb.code = c.id2 WHERE b.s3 = 0 and c.s3 = 0 "+content+" ORDER BY b.m1 DESC,convert(int,replace(b.m2,'月','')) DESC";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}

	@Override
	public long addjcjh(AQZF_SafetyCheckPlanEntity jcjh) {
		save(jcjh);
		return jcjh.getID();
	}

	//根据中间表id查询
	@Override
	public List<Map<String, Object>> findInfoById(long id) {	
		String sql="SELECT a.id1 jhid,a.id2 qyid,b.m1,b.m2,b.m1+'年'+b.m2 jhsj,b.m3,b.m4,b.m5,c.m1 qyname,CAST(STUFF(( SELECT ',' + m1 FROM t_barrio WHERE PATINDEX('%,' + RTRIM(code) + ',%',',' + b.m3 + ',')>0 "
				+ "ORDER BY PATINDEX('%,' + RTRIM(code) + ',%',',' + b.m3 + ',') FOR XML PATH('')), 1, 1, '') as varchar(200)) sd,(case b.m4 when '1' then '工贸' when '2' then '化工' else b.m4 end) hylx,a.m2 zfry,tb.m1 wgname FROM aqzf_plan_enterprise a LEFT JOIN aqzf_safetycheckplan b ON b.id = a.id1 "
				+ "LEFT JOIN bis_enterprise c ON c.id = a.id2 left join t_barrio tb on tb.code = c.id2 WHERE c.s3=0 and b.s3=0 and a.id  = "+id;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}

	@Override
	public Map<String, Object> findHy(String id){
		String sql="select * from tdic_gbtbusiness where cid='"+id+"'";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public AQZF_SafetyCheckPlanEntity findById(long id) {
		return find(id);
	}

	@Override
	public void deletefa(long id1) {
		String sql=" UPDATE aqzf_safetycheckscheme SET s3=1 WHERE id1="+id1;
		updateBySql(sql);
	}
	
	@Override
	public void deletejl(String faids) {
		String sql=" UPDATE aqzf_safetycheckrecord SET s3=1 WHERE id1 in("+faids+")";
		updateBySql(sql);
	}

	@Override
	public List<Map<String, Object>> findJcfaById(long id1) {
		String sql="SELECT * from  aqzf_safetycheckscheme where id1="+id1+" and s3=0";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}

	@Override
	public List<Map<String, Object>> getsjsc(Map<String, Object> mapData) {
		String content="";
		if(mapData.get("sjjglx")!=null&&mapData.get("sjjglx")!=""){
			content = content + " AND a.m36='"+mapData.get("sjjglx")+"' "; 
		}
		if(mapData.get("sjxzqy")!=null&&mapData.get("sjxzqy")!=""){
			if(mapData.get("xj")!=null&&mapData.get("xj").equals("2")){
				content = content + " AND a.ID2 IN ("+mapData.get("sjxzqy")+") "; 
			}else{
				content = content + "AND a.ID2 like'"+mapData.get("xzqy")+"%'"; 
			}
		}
		
		String sjsl = "";
		if(mapData.get("sl")!=null&&mapData.get("sl")!=""){
			sjsl = sjsl + " AND bis.rn <= "+mapData.get("sl")+" "; 
		}
		
		//过滤条件
		if(!mapData.get("gltj").toString().equals("0")){
			String content2 = "";
			if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
				content2 = content2 +" AND c.xzqy = '"+mapData.get("xzqy")+"' "; 
			}
			if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
				content2 = content2 + " AND c.userroleflg = '"+mapData.get("jglx")+"' "; 
			}
			//本年度内
			if(mapData.get("gltj").toString().equals("1")){
				content2 = content2 + " AND b.m1 = year(getdate()) "; 
			}
			content = content + " AND a.id not in (SELECT DISTINCT a.id2 FROM aqzf_plan_enterprise a LEFT JOIN aqzf_safetycheckplan b ON b.id = a.id1 "
					+ "left join t_user c on c.id = b.id1 WHERE 1=1 "+content2+") ";
		}
		String sql="SELECT bis.* FROM (SELECT ROW_NUMBER() OVER (partition by b.code ORDER BY NEWID()) AS rn,b.*,a.id qyid,a.m1 qyname "
				+ "FROM bis_enterprise a LEFT JOIN t_barrio b ON b.code = a.id2 WHERE a.S3=0 and a.M1 is not null " + content +") bis WHERE 1 = 1 "+sjsl;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
	
	@Override
	public List<Map<String,Object>> findAlllist(Map<String,Object> map) {
		String content="";
		if(map.get("xzqy")!=null&&map.get("xzqy")!=""){
			if(map.get("xj")!=null&&map.get("xj").equals("2")){
				content = content + " AND a.ID2 IN ("+map.get("xzqy")+") "; 
			}else{
				content = content + "AND a.ID2 like'"+map.get("xzqy")+"%'"; 
			}
		}
		if(map.get("jglx")!=null&&map.get("jglx")!=""){
			content = content + "AND a.m36='"+map.get("jglx")+"' "; 
		}
		if(map.get("qyname")!=null&&map.get("qyname")!=""){
			content = content + "AND a.M1 like'%"+map.get("qyname")+"%'"; 
		}
		String sql=" SELECT  a.*,b.m1 wgname FROM  bis_enterprise a LEFT JOIN t_barrio b ON b.code = a.id2 WHERE a.S3=0 and a.M1 is not null " + content;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> qydgsj(Map<String, Object> mapData) {
		String content="";
		
		//过滤条件
		if(!mapData.get("gltj").toString().equals("0")){
			String content2 = "";
			if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
				content2 = content2 +" AND c.xzqy = '"+mapData.get("xzqy")+"' "; 
			}
			//本年度内
			if(mapData.get("gltj").toString().equals("1")){
				content2 = content2 + " AND b.m1 = year(getdate()) "; 
			}
			content = content + " AND a.id not in (SELECT DISTINCT a.id2 FROM aqzf_plan_enterprise a LEFT JOIN aqzf_safetycheckplan b ON b.id = a.id1 "
					+ "left join t_user c on c.id = b.id1 WHERE 1=1 "+content2+") ";
		}
		String sql="SELECT a.id qyid,a.m1 qyname,b.* FROM bis_enterprise a LEFT JOIN t_barrio b ON b.code = a.id2 WHERE a.S3=0 and a.M1 is not null "
				+ "AND a.id2 in (SELECT id2 FROM bis_enterprise WHERE id = "+mapData.get("qyid")+") AND a.id not in ("+mapData.get("qyids")+") "+content+" ORDER BY NEWID()";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
}
