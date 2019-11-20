package com.cczu.aqzf.model.dao;

import com.cczu.aqzf.model.entity.AQZF_SafetyCheckPlanEntity;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 监督检查统计DAO
 */
@Repository("AqzfJdjctjDao")
public class AqzfJdjctjDao extends BaseDao<AQZF_SafetyCheckPlanEntity, Long> {

	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="SELECT TOP " + mapData.get("pageSize") + "a.* FROM ("+
		           "SELECT ROW_NUMBER() OVER(order by  b.m1 DESC,convert(int,replace(b.m2,'月','')) DESC) rownum,a.id,(CAST(b.m1 as varchar(10))+'年'+CAST(b.m2 as varchar(10)))m1,c.m1 qyname,CAST(STUFF(( SELECT ',' + m1 FROM t_barrio WHERE PATINDEX('%,' + RTRIM(code) + ',%',',' + b.m3 + ',')>0 "
		           + " ORDER BY PATINDEX('%,' + RTRIM(code) + ',%',',' + b.m3 + ',') FOR XML PATH('')), 1, 1, '') as varchar(200)) m3,b.m4,b.m5,a.m2 zfry,ISNULL(a.m1,'0') panduan,"
		           + " f.id faid,ISNULL(f.m11,'0') jlcz,g.id jlid,ISNULL(g.m12,'0') clcz,ISNULL(g.m13,'0') zlcz,ISNULL(g.m14,'0') lacz,h.id clid,i.id zlid,ISNULL(i.m9,'0') fccz,j.id fcid,tb.m1 wgname "
		           + " FROM aqzf_plan_enterprise a"
		           + " LEFT JOIN aqzf_safetycheckplan b ON b.id = a.id1 "
                   + " LEFT JOIN bis_enterprise c ON c.id = a.id2 "
                   + " left join t_user d on d.id=b.id1"
                   + " LEFT JOIN (SELECT * FROM aqzf_safetycheckscheme WHERE s3 = 0) f ON a.id1 = f.id1 AND a.id2 = f.id2"
                   + " LEFT JOIN (SELECT * FROM aqzf_safetycheckrecord WHERE s3 = 0) g ON f.id = g.id1"
                   + " LEFT JOIN (SELECT * FROM aqzf_treatment WHERE s3 = 0) h ON h.id3 = g.id"
                   + " LEFT JOIN (SELECT * FROM aqzf_reform WHERE s3 = 0) i ON i.id1 = g.id"
                   + " LEFT JOIN (SELECT * FROM aqzf_review WHERE s3 = 0) j ON j.id1 = i.id"
                   + " left join t_barrio tb on tb.code = c.id2"
                   + " WHERE b.s3 = 0 and c.s3 = 0 "+ content+ ") a "+
				   "WHERE rownum >" + mapData.get("pageSize") + "*("+mapData.get("pageNo")+"-1)";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		
		return list;
	}
	
	 /**
     * 分页统计
     * @param mapData
     * @return
     */
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) FROM aqzf_plan_enterprise a"
				+ " left join aqzf_safetycheckplan b on a.id1=b.id"
				+ " LEFT JOIN bis_enterprise c ON c.id = a.id2"
				+ " left join t_user d on d.id=b.id1"
				+ " WHERE b.s3 = 0 and c.s3 = 0 "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	/**
     * 查询条件
     * @param mapData
     * @return
     */
    public String content(Map<String, Object> mapData) {
		
		String content="";
		if(mapData.get("year")!=null&&mapData.get("year")!=""){
			content = content + "AND b.m1 like'%"+mapData.get("year")+"%' "; 
		}
		if(mapData.get("month")!=null&&mapData.get("month")!=""){
			content = content + "AND b.m2 like'%"+mapData.get("month")+"%' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + " AND d.xzqy = '"+mapData.get("xzqy")+"' "; 
		}
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + " AND d.userroleflg='"+mapData.get("jglx")+"' "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content + " AND c.m1 like '%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("M3")!=null&&mapData.get("M3")!=""){
			content = content + " AND b.m3 like'%"+mapData.get("M3")+"%' "; 
		}
		if(mapData.get("M4")!=null&&mapData.get("M4")!=""){
			content = content + " AND b.m4 like'%"+mapData.get("M4")+"%' "; 
		}
		return content;
		
	}
}
