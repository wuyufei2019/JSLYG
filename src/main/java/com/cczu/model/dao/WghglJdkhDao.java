package com.cczu.model.dao;

import com.cczu.model.entity.YHPC_CheckPointEntity;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 监督考核dao层
 */
@Repository("WghglJdkhDao")
public class WghglJdkhDao extends BaseDao<YHPC_CheckPointEntity, Long> {
	
	//年检list
	public List<Map<String, Object>> njdataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="SELECT top "+mapData.get("pageSize")+" * FROM ("+
		        "SELECT ROW_NUMBER() OVER (ORDER BY c.m1,b.ID) AS RowNumber,d.wgdid,b.ID userid,c.m1 wgname,b.NAME wgyname,d.wgdname,d.xccs,e.yxcs FROM t_user b "
		        + "LEFT JOIN t_barrio c ON b.xzqy = c.code LEFT JOIN (SELECT a.userid,b.id wgdid,b.name wgdname,COUNT(a.userid) xccs FROM (SELECT a.* FROM("
		        + "SELECT ROW_NUMBER() OVER (partition by a.checkplan_id,a.checkpoint_id,c.starttime,c.endtime,convert(varchar(4),a.createtime,120) ORDER BY a.createtime DESC) AS rn,a.* "
		        + "FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 WHERE a.checkpointtype = '2' AND b.id1 = 0 AND b.type = '4' "
		        + "AND MONTH(a.createtime) >= c.starttime AND MONTH(a.createtime) <= c.endtime) a where a.rn = 1) a "
		        + "LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id "
		        + "WHERE a.checkpointtype = '2' AND b.usetype = '1' AND convert(varchar(4),a.createtime,120)= '"+mapData.get("nf")+"' "
		        + "GROUP BY a.userid,b.name,b.id) d ON d.userid = b.ID LEFT JOIN (SELECT bis.id2 xzqy,COUNT(*) yxcs FROM yhpc_checkplan a LEFT JOIN yhpc_checkplan_point b ON a.id = b.id1 "
		        + "LEFT JOIN yhpc_checkplan_time c ON c.id1 = a.id LEFT JOIN yhpc_checkpoint d ON d.id = b.id2 LEFT JOIN bis_enterprise bis ON bis.id = d.id1 "
		        + "WHERE a.id1 = 0 AND a.type = '4' AND bis.s3 = 0 GROUP BY bis.id2) e ON e.xzqy = c.code WHERE b.usertype = 0 "+content+")as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	//年检list数量
	public int getnjTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="select count(*) FROM t_user b LEFT JOIN t_barrio c ON b.xzqy = c.code LEFT JOIN (SELECT a.userid,b.id wgdid,b.name wgdname,COUNT(a.userid) xccs FROM (SELECT a.* "
				+ "FROM(SELECT ROW_NUMBER() OVER (partition by a.checkplan_id,a.checkpoint_id,c.starttime,c.endtime,convert(varchar(4),a.createtime,120) ORDER BY a.createtime DESC) AS rn,a.* "
				+ "FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 WHERE a.checkpointtype = '2' AND b.id1 = 0 AND b.type = '4' "
				+ "AND MONTH(a.createtime) >= c.starttime AND MONTH(a.createtime) <= c.endtime) a where a.rn = 1) a "
		        + "LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id "
		        + "WHERE a.checkpointtype = '2' AND b.usetype = '1' AND convert(varchar(4),a.createtime,120)= '"+mapData.get("nf")+"' "
		        + "GROUP BY a.userid,b.name,b.id) d ON d.userid = b.ID LEFT JOIN (SELECT bis.id2 xzqy,COUNT(*) yxcs FROM yhpc_checkplan a LEFT JOIN yhpc_checkplan_point b ON a.id = b.id1 "
		        + "LEFT JOIN yhpc_checkplan_time c ON c.id1 = a.id LEFT JOIN yhpc_checkpoint d ON d.id = b.id2 LEFT JOIN bis_enterprise bis ON bis.id = d.id1 "
		        + "WHERE a.id1 = 0 AND a.type = '4' AND bis.s3 = 0 GROUP BY bis.id2) e ON e.xzqy = c.code WHERE b.usertype = 0 "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	//月检list
	public List<Map<String, Object>> yjdataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="SELECT top "+mapData.get("pageSize")+" * FROM ("+
		        "SELECT ROW_NUMBER() OVER (ORDER BY c.m1,b.ID) AS RowNumber,d.wgdid,b.ID userid,c.m1 wgname,b.NAME wgyname,d.wgdname,d.xccs,e.yxcs FROM t_user b "
		        + "LEFT JOIN t_barrio c ON b.xzqy = c.code LEFT JOIN (SELECT a.userid,b.id wgdid,b.name wgdname,COUNT(a.userid) xccs FROM (SELECT a.* "
		        + "FROM(SELECT ROW_NUMBER() OVER (partition by a.checkplan_id,a.checkpoint_id,c.starttime,c.endtime,convert(varchar(7),a.createtime,120) ORDER BY a.createtime DESC) AS rn,a.* "
		        + "FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 "
		        + "WHERE a.checkpointtype = '2' AND b.id1 = 0 AND b.type = '3' AND Day(a.createtime) >= c.starttime AND Day(a.createtime) <= c.endtime) a where a.rn = 1) a "
		        + "LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '2' AND b.usetype = '1' AND convert(varchar(7),a.createtime,120)= '"+mapData.get("yf")+"' "
		        + "GROUP BY a.userid,b.name,b.id) d ON d.userid = b.ID LEFT JOIN (SELECT bis.id2 xzqy,COUNT(*) yxcs FROM yhpc_checkplan a LEFT JOIN yhpc_checkplan_point b ON a.id = b.id1 "
		        + "LEFT JOIN yhpc_checkplan_time c ON c.id1 = a.id LEFT JOIN yhpc_checkpoint d ON d.id = b.id2 LEFT JOIN bis_enterprise bis ON bis.id = d.id1 "
		        + "WHERE a.id1 = 0 AND a.type = '3' AND bis.s3 = 0 GROUP BY bis.id2) e ON e.xzqy = c.code WHERE b.usertype = 0 "+content+")as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	//月检list（当年）
	public List<Map<String, Object>> yjdataGrid2(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="select top 10 a.fid,ISNULL(Round(CAST(ISNULL(a.xccs,0) AS float) / CAST(nullif(a.yxcs, 0) AS float)*100,2),0) bl,a.wgname,isnull(sum(a.xccs),0) xccs,isnull(a.yxcs,0) yxcs from("
				+ "SELECT c.fid,c.m1 wgname,d.xccs,e.yxcs FROM t_user b "
				+ "LEFT JOIN t_barrio c ON b.xzqy = c.code LEFT JOIN (SELECT a.userid,b.id wgdid,b.name wgdname,COUNT(a.userid) xccs FROM (SELECT a.* "
				+ "FROM(SELECT ROW_NUMBER() OVER (partition by a.checkplan_id,a.checkpoint_id,c.starttime,c.endtime,convert(varchar(7),a.createtime,120) ORDER BY a.createtime DESC) AS rn,a.* "
				+ "FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 "
				+ "WHERE a.checkpointtype = '2' AND b.id1 = 0 AND b.type = '3' AND Day(a.createtime) >= c.starttime AND Day(a.createtime) <= c.endtime) a where a.rn = 1) a "
				+ "LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '2' AND b.usetype = '1' AND convert(varchar(4),a.createtime,120)= '"+mapData.get("nf")+"' "
				+ "GROUP BY a.userid,b.name,b.id) d ON d.userid = b.ID LEFT JOIN (SELECT bis.id2 xzqy,COUNT(*) yxcs FROM yhpc_checkplan a LEFT JOIN yhpc_checkplan_point b ON a.id = b.id1 "
				+ "LEFT JOIN yhpc_checkplan_time c ON c.id1 = a.id LEFT JOIN yhpc_checkpoint d ON d.id = b.id2 LEFT JOIN bis_enterprise bis ON bis.id = d.id1 "
				+ "WHERE a.id1 = 0 AND a.type = '3' AND bis.s3 = 0 GROUP BY bis.id2) e ON e.xzqy = c.code WHERE b.usertype = 0 "+content
				+ ")a left join t_barrio tb on tb.id=a.fid where tb.code='"+mapData.get("xzqy")+"' GROUP BY a.fid,a.wgname,a.yxcs,ISNULL(Round(CAST(ISNULL(a.xccs,0) AS float) / CAST(nullif(a.yxcs, 0) AS float)*100,2),0) ORDER BY  ISNULL(Round(CAST(ISNULL(a.xccs,0) AS float) / CAST(nullif(a.yxcs, 0) AS float)*100,2),0) desc";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	//月检list（当年）
	public List<Map<String, Object>> wghph(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="select top 10 a.fid,ISNULL(Round(CAST(ISNULL(a.xccs,0) AS float) / CAST(nullif(a.yxcs, 0) AS float)*100,2),0) bl,a.wgname,isnull(sum(a.xccs),0) xccs,isnull(a.yxcs,0) yxcs from("
				+ "SELECT c.fid,c.m1 wgname,d.xccs,e.yxcs FROM t_user b "
				+ "LEFT JOIN t_barrio c ON b.xzqy = c.code LEFT JOIN (SELECT a.userid,b.id wgdid,b.name wgdname,COUNT(a.userid) xccs FROM (SELECT a.* "
				+ "FROM(SELECT ROW_NUMBER() OVER (partition by a.checkplan_id,a.checkpoint_id,c.starttime,c.endtime,convert(varchar(7),a.createtime,120) ORDER BY a.createtime DESC) AS rn,a.* "
				+ "FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 "
				+ "WHERE a.checkpointtype = '2' AND b.id1 = 0 AND b.type = '3' AND Day(a.createtime) >= c.starttime AND Day(a.createtime) <= c.endtime) a where a.rn = 1) a "
				+ "LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '2' AND b.usetype = '1' AND convert(varchar(4),a.createtime,120)= '"+mapData.get("nf")+"' "
				+ "GROUP BY a.userid,b.name,b.id) d ON d.userid = b.ID LEFT JOIN (SELECT bis.id2 xzqy,COUNT(*) yxcs FROM yhpc_checkplan a LEFT JOIN yhpc_checkplan_point b ON a.id = b.id1 "
				+ "LEFT JOIN yhpc_checkplan_time c ON c.id1 = a.id LEFT JOIN yhpc_checkpoint d ON d.id = b.id2 LEFT JOIN bis_enterprise bis ON bis.id = d.id1 "
				+ "WHERE a.id1 = 0 AND a.type = '3' AND bis.s3 = 0 GROUP BY bis.id2) e ON e.xzqy = c.code WHERE b.usertype = 0 "+content
				+ ")a left join t_barrio tb on tb.id=a.fid where tb.code='"+mapData.get("xzqy")+"' GROUP BY a.fid,a.wgname,a.yxcs,ISNULL(Round(CAST(ISNULL(a.xccs,0) AS float) / CAST(nullif(a.yxcs, 0) AS float)*100,2),0) ORDER BY  ISNULL(Round(CAST(ISNULL(a.xccs,0) AS float) / CAST(nullif(a.yxcs, 0) AS float)*100,2),0) desc";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	//网格风险点数量排行
	public List<Map<String, Object>> wgfxdph(Map<String, Object> mapData) {
		String sql ="SELECT top 6 tb.m1,sum(case when a.m9=1 THEN 1 else 0 END) red,sum(case when a.m9=2 THEN 1 else 0 END) orange,sum(case when a.m9=3 THEN 1 else 0 END) yellow,sum(case when a.m9=4 THEN 1 else 0 END) blue,count(*) amount " +
				"FROM t_barrio tb LEFT JOIN bis_enterprise b ON tb.code=b.id2 " +
				"LEFT JOIN fxgk_accidentrisk a ON a.id1=b.id LEFT JOIN t_barrio tb2 ON tb2.id=tb.fid " +
				"WHERE a.s3 = 0 and b.s3 = 0 AND tb2.code='"+mapData.get("xzqy")+"' GROUP BY tb.m1 ORDER BY count(*) desc";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	//月检list数量
	public int getyjTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="select count(*) FROM t_user b LEFT JOIN t_barrio c ON b.xzqy = c.code LEFT JOIN (SELECT a.userid,b.id wgdid,b.name wgdname,COUNT(a.userid) xccs FROM (SELECT a.* "
				+ "FROM(SELECT ROW_NUMBER() OVER (partition by a.checkplan_id,a.checkpoint_id,c.starttime,c.endtime,convert(varchar(7),a.createtime,120) ORDER BY a.createtime DESC) AS rn,a.* "
				+ "FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 "
				+ "WHERE a.checkpointtype = '2' AND b.id1 = 0 AND b.type = '3' AND Day(a.createtime) >= c.starttime AND Day(a.createtime) <= c.endtime) a where a.rn = 1) a "
		        + "LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id "
		        + "WHERE a.checkpointtype = '2' AND b.usetype = '1' AND convert(varchar(7),a.createtime,120)= '"+mapData.get("yf")+"' "
		        + "GROUP BY a.userid,b.name,b.id) d ON d.userid = b.ID LEFT JOIN (SELECT bis.id2 xzqy,COUNT(*) yxcs FROM yhpc_checkplan a LEFT JOIN yhpc_checkplan_point b ON a.id = b.id1 "
		        + "LEFT JOIN yhpc_checkplan_time c ON c.id1 = a.id LEFT JOIN yhpc_checkpoint d ON d.id = b.id2 LEFT JOIN bis_enterprise bis ON bis.id = d.id1 "
		        + "WHERE a.id1 = 0 AND a.type = '3' AND bis.s3 = 0 GROUP BY bis.id2) e ON e.xzqy = c.code WHERE b.usertype = 0 "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String content(Map<String, Object> mapData) {
		String content="";
		//user xzqy和jglx
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + " AND b.xzqy like '"+mapData.get("xzqy")+"%' "; 
		}
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + " AND b.userroleflg='"+mapData.get("jglx")+"' "; 
		}
		if(mapData.get("ryxzqy")!=null&&mapData.get("ryxzqy")!=""){
			content = content + " AND b.xzqy = '"+mapData.get("ryxzqy")+"' "; 
		}
		if(mapData.get("ryname")!=null&&mapData.get("ryname")!=""){
			content = content + " AND b.NAME like '%"+mapData.get("ryname")+"%' "; 
		}
		return content;
	}
}
