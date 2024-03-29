package com.cczu.model.sggl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.BIS_EmployeeEntity;
import com.cczu.model.sggl.entity.SGGL_AccidentManageEntity;
import com.cczu.util.dao.BaseDao;

@Repository("SgglSgxxDao")
public class SgglSgxxDao extends BaseDao<SGGL_AccidentManageEntity,Long>  {
	
	public List<SGGL_AccidentManageEntity> findAllInfo() {
		String sql ="SELECT * FROM sggl_accidentmanage WHERE s3=0";
		List<SGGL_AccidentManageEntity> list=findBySql(sql, null,SGGL_AccidentManageEntity.class);
		return list;
		
	}

	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.m5 desc) AS RowNumber,c.qs,c.zs,c.sw,b.m1 qynm,a.*,d.m1 bmname "
				+ " FROM sggl_accidentmanage a "
				+ " left join bis_enterprise b on a.qyid=b.id "
				+ " left join (select id1, sum(case when m1=1 then 1 else 0 end) as qs,sum(case when m1=2 then 1 else 0 end) as zs,sum(case when m1=3 then 1 else 0 end) as sw "
				+ " from sggl_casualty group by id1) c on c.id1=a.id "
				+ " left join t_department d on a.m23 = d.id "
				+ " WHERE b.s3=0 and a.s3=0 and a.qyid is not null " + content + " ) "
				+ " as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		
		return list;
	}

	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM sggl_accidentmanage a"
				+ " left join bis_enterprise b on a.qyid=b.id "
				+ " WHERE a.s3=0 and b.s3=0 and a.qyid is not null " + content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	//获取本年度最新的事故编号
	public String getMaxNum(Long qyid) {
		String sql=" select a.m1 from sggl_accidentmanage a"
				+ " where a.s3=0 and a.id=(select  max(id) from sggl_accidentmanage)"
				+ " and YEAR(a.m5)=DATENAME(YEAR,GETDATE()) and a.qyid=" + qyid;
		List<Object> list=findBySql(sql);
		if(list.size()>0){
			return (String) list.get(0);
		}else{
			return "";
		}
	}
	
	public List<Map<String, Object>> swrylist(Map<String, Object> mapData) {
		String sql  ="SELECT top  "+mapData.get("pageSize")+" * FROM ("
				+ "SELECT ROW_NUMBER() OVER (ORDER BY b.id) AS RowNumber,b.*,a.m1 shcd FROM sggl_casualty a LEFT JOIN bis_employee b ON a.id2 = b.id where a.id1 ="+ mapData.get("id1")+") "
				+ " as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	public List<BIS_EmployeeEntity> swrylist2(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql  ="SELECT top  "+mapData.get("pageSize")+" * FROM ("
				+ "SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber,a.*,b.m1 gw,'' shcd FROM bis_employee a "
				+ " left join bis_jobpostentity b on a.m4=b.id "
				+ " where a.s3=0 and a.id3="+ mapData.get("qyid")+content
						+ " and a.id not in(select id2 from sggl_casualty where id1 ="+ mapData.get("id1")+"))"
				+ " as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)";
		List<BIS_EmployeeEntity> list=findBySql(sql,null,BIS_EmployeeEntity.class);
		return list;
	}

    /**
     * 查询条件
     * @param mapData
     * @return
     */
    public String content(Map<String, Object> mapData) {
		
		String content="";
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content +" AND a.M1 LIKE '%"+mapData.get("m1")+"%' "; 
		}
		if(mapData.get("m2")!=null&&mapData.get("m2")!=""){
			content = content +" AND a.M2 like'%"+mapData.get("m2")+"%' "; 
		}
		if(mapData.get("m5")!=null&&mapData.get("m5")!=""){
			content = content +" AND CONVERT(varchar(100), a.M5, 23) ='"+mapData.get("m5")+"' "; 
		}
		if(mapData.get("qyid")!=null && mapData.get("qyid")!=""){
			content = content +" AND a.qyid ="+ mapData.get("qyid"); 
		}
		if(mapData.get("qynm")!=null && mapData.get("qynm")!=""){
			content = content +" AND b.M1 LIKE'%"+mapData.get("qynm")+"%'"; 
		}
		if (mapData.get("sgtype") != null && mapData.get("sgtype") != "") {
			content = content + " AND a.m3 = '"+ mapData.get("sgtype") + "'";
		}
		if (mapData.get("sglevel") != null && mapData.get("sglevel") != "") {
			content = content + " AND a.m4 = '"+ mapData.get("sglevel") + "'";
		}
		//echarts条件
		if (mapData.get("year") != null && mapData.get("year") != "") {
			content = content + " AND YEAR(a.m5) = '"+ mapData.get("year") + "'";
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + " AND ( b.fid='"+mapData.get("fid")+"' or b.id="+mapData.get("fid")+") "; 
		}
		return content;
	}

	public void deleteInfo(Long id) {
		String sql=" UPDATE sggl_accidentmanage SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}
	
	public SGGL_AccidentManageEntity findById(Long id) {
		String sql ="SELECT * FROM sggl_accidentmanage WHERE s3=0 AND ID="+id;
		List<SGGL_AccidentManageEntity> list=findBySql(sql, null,SGGL_AccidentManageEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	//获取伤亡人员list
	public List<Map<String, Object>> swrydataGrid(Map<String, Object> mapData) {
		String sql  ="SELECT top  "+mapData.get("pageSize")+" * FROM ("
				+ "SELECT ROW_NUMBER() OVER (ORDER BY b.dangerlevel) AS RowNumber,a.id id,a.m1 name,a.m3 sex,DATEDIFF (day,a.m10,getDate()) age,a.m4 gw,a.m5 xl,a.m7 zw,b.m1 shcd FROM bis_employee a LEFT JOIN sggl_casualty b ON a.id = b.id2 where a.S3=0 and b.id1 = "+ mapData.get("sgid")+") "
				+ " as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	//获取伤亡人员数量
	public int getswryTotalCount(Map<String, Object> mapData) {
		String sql="SELECT COUNT(*) FROM bis_employee a LEFT JOIN sggl_casualty b ON a.id = b.id2 where a.S3=0 and b.id1 ="+ mapData.get("sgid");
	    List<Object>list = findBySql(sql);
		return (int) list.get(0);
	}

	//删除伤亡人员
	public void deleteSwry(long id) {
		String sql=" delete sggl_casualty WHERE id="+id;
		updateBySql(sql);
	}

	//按月份统计事故数
	public List<Map<String, Object>> getCountEveryMonth(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = "SELECT month(a.m5) month,COUNT(1) count FROM sggl_accidentmanage a WHERE a.S3=0 " + content+" GROUP BY month(a.m5) ";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	//按类型统计事故数
	public List<Map<String, Object>> getCountEveryType(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = "SELECT a.m3,COUNT(a.id) sl FROM sggl_accidentmanage a WHERE a.S3=0 " + content+" GROUP BY a.m3";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	//事故统计获取年份下拉
	public Object[] getMaxYearAndMinYear() {
		String sql = "SELECT Max(CONVERT(VARCHAR(4),M5,120)) max,Min(CONVERT(VARCHAR(4),M5,120))min  FROM sggl_accidentmanage WHERE S3=0";
		List<Object[]>list=null;
		try {
			
			list=findBySql(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list.get(0);
	}
	
	//按类型统计事故数
	public List<Map<String, Object>> getCountEveryBm(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = "SELECT bm.m1 bmname,ISNULL(z.sl, 0) sl FROM t_department bm LEFT JOIN (SELECT a.m23 bmid,COUNT(a.m23) sl "
				+ "FROM sggl_accidentmanage a WHERE a.s3 = 0 " + content+" GROUP BY a.m23) z ON bm.id = z.bmid WHERE bm.id2 ="+ mapData.get("qyid");
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
}
