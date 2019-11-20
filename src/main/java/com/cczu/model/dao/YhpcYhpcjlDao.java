package com.cczu.model.dao;

import com.cczu.model.entity.YHPC_CheckHiddenInfoEntity;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 隐患排查记录dao层
 *
 */
@Repository("YhpcYhpcjlDao")
public class YhpcYhpcjlDao extends BaseDao<YHPC_CheckHiddenInfoEntity, Long> {

	/**
	 * 查询隐患排查记录list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont="order by x.createtime DESC";
		if("wgname".equals(mapData.get("orderBy")))
			ordercont="ORDER BY g.m1 "+mapData.get("order");
		else if("createtime".equals(mapData.get("orderBy")))
			ordercont="ORDER BY x.createtime "+mapData.get("order");
		else if("qyname".equals(mapData.get("orderBy")))
			ordercont="ORDER BY e.m1 "+mapData.get("order");
		else if("yhjb".equals(mapData.get("orderBy")))
			ordercont="ORDER BY d.dangerlevel "+mapData.get("order");
		else if("dangerstatus".equals(mapData.get("orderBy")))
			ordercont="ORDER BY x.dangerstatus "+mapData.get("order");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont
				+") AS RowNumber,a.jcdname xjdname,d.content jcnr,d.dangerlevel yhjb,"
				+ "c.NAME yhfxr,x.*,tu.NAME zgrname,yh.handleuploadphoto zgzp,yh.handletime zgsj,dep.m1 depname,"
				+ "CAST(STUFF(( SELECT ',' + NAME FROM  t_user WHERE  PATINDEX('%,' + RTRIM(ID) + ',%',"
				+ "',' + x.handlepersons + ',')>0 ORDER BY PATINDEX('%,' + RTRIM(ID) + ',%',',' + x.handlepersons + ',') "
				+ " FOR XML PATH('')), 1, 1, '') as varchar(1000)) zdzgr "
				+ " FROM ("
				+ " SELECT a.id,b.m1 jcdname,b.depid,'1' type "
				+ " FROM yhpc_checkhiddeninfo a  "
				+ " LEFT JOIN fxgk_accidentrisk b ON b.id = a.pointid"
				+ " WHERE a.dangerorigin='1' AND b.s3 = 0"
				+ " ) a "
				+ " LEFT JOIN t_department dep ON dep.id = a.depid"
				+ " LEFT JOIN yhpc_checkhiddeninfo x ON x.id = a.id"
				+ " LEFT JOIN yhpc_checkcontent d on d.id=x.checkcontent_id"
				+ " LEFT JOIN t_user c on x.userid=c.ID"
				+ " LEFT JOIN (SELECT a.dangerid,a.userid,a.handleuploadphoto,a.handletime FROM (SELECT ROW_NUMBER() OVER (partition by dangerid ORDER BY handletime DESC) AS r,* FROM yhpc_handlerecord WHERE handletype = 1) a WHERE a.r<=1) yh ON x.id = yh.dangerid "
				+ " LEFT JOIN t_user tu ON tu.ID = yh.userid "
				+ " where 1=1" + content + " ) "
				+ " as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql,null, Map.class);
		return list;
	}

	/**
	 * 查询隐患排查记录list的个数
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT count(*) "
				+ " FROM ( "
				+ " SELECT a.id,b.depid,b.m1 jcdname,'1' type"
				+ " FROM yhpc_checkhiddeninfo a  "
				+ " LEFT JOIN fxgk_accidentrisk b ON b.id = a.pointid"
				+ " WHERE a.dangerorigin='1' AND b.s3 = 0  ) a "
				+ " LEFT JOIN t_department dep ON dep.id = a.depid"
				+ " LEFT JOIN yhpc_checkhiddeninfo x ON x.id = a.id"
				+ " LEFT JOIN yhpc_checkcontent d on d.id=x.checkcontent_id"
				+ " LEFT JOIN t_user c on x.userid=c.ID"
				+ " where 1=1 " + content;
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
		if(mapData.get("starttime")!=null&&mapData.get("starttime")!=""){
			content = content + "AND x.createtime >= '"+mapData.get("starttime")+"' "; 
		}
		if(mapData.get("finishtime")!=null&&mapData.get("finishtime")!=""){
			content = content + "AND x.createtime <= '"+mapData.get("finishtime")+"' "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + "AND x.qyid = "+mapData.get("qyid")+" "; 
		}
		if(mapData.get("xjjlzgxzqy")!=null&&mapData.get("xjjlzgxzqy")!=""){
			content = content + "AND e.id2 = '"+mapData.get("xjjlzgxzqy")+"' "; 
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND e.m36='"+mapData.get("jglx")+"' "; 
		}
		if(mapData.get("yhjb")!=null&&mapData.get("yhjb")!=""){
			content = content + "AND d.dangerlevel = '"+mapData.get("yhjb")+"' "; 
		}
		if(mapData.get("zgzt")!=null&&mapData.get("zgzt")!=""){
			content = content + "AND x.dangerstatus = '"+mapData.get("zgzt")+"' "; 
		}
		if(mapData.get("yhly")!=null&&mapData.get("yhly")!=""){
			content = content + "AND x.dangerorigin = '"+mapData.get("yhly")+"' "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content + "AND e.m1 like '%"+mapData.get("qyname")+"%' "; 
		}
		//app条件
		if(mapData.get("jcdname")!=null&&mapData.get("jcdname")!=""){
			content = content + " AND a.jcdname like '%"+mapData.get("jcdname")+"%' "; 
		}
		
		/*安全台账条件*/
		if(mapData.get("aqtzstarttime")!=null&&mapData.get("aqtzstarttime")!=""){
			content = content +" AND CONVERT(varchar(100), x.createtime, 23) >='"+mapData.get("aqtzstarttime")+"' "; 
		}
		if(mapData.get("aqtzfinishtime")!=null&&mapData.get("aqtzfinishtime")!=""){
			content = content +" AND CONVERT(varchar(100), x.createtime, 23) <='"+mapData.get("aqtzfinishtime")+"' "; 
		}

		//所属部门权限
		if(mapData.get("depids")!=null&&mapData.get("depids")!=""){
			content = content + " and dep.id in ("+mapData.get("depids")+")";
		}else{
			if(mapData.get("depcode1")!=null&&mapData.get("depcode1")!=""){
				content = content + " and dep.code='"+mapData.get("depcode1")+"'";
			}
			if(mapData.get("depcode2")!=null&&mapData.get("depcode2")!=""){
				content = content + " and dep.code like'"+mapData.get("depcode2")+"%'";
			}
		}
		if(mapData.get("depcode3")!=null&&mapData.get("depcode3")!=""){
			content = content + " and dep.code like'"+mapData.get("depcode3")+"%'";
		}
		return content;
	}

	/**
	 * 根据id查询处理措施详细信息
	 * @return
	 */
	public Map<String, Object> findInforById(Long id) {
		String sql =" SELECT x.id,x.dangerdesc yh,x.dangerphoto yhzp,x.createtime,x.dangerstatus,x.sechandletime,(case x.dangerstatus when '0' then '未整改' when '1' then '整改待复查' when '2' then '复查未通过' when '3' then '整改完成' end) yhzt,a.jcdname jcd,c.name jcr,d.checktitle,d.content jcnr,"
				+ " CAST(STUFF(( SELECT ',' + NAME FROM  t_user WHERE  PATINDEX('%,' + RTRIM(ID) + ',%',"
				+ "',' + x.handlepersons + ',')>0 ORDER BY PATINDEX('%,' + RTRIM(ID) + ',%',',' + x.handlepersons + ',') "
				+ " FOR XML PATH('')), 1, 1, '') as varchar(1000)) zdzgr  "
				+ " FROM ("
				+ " SELECT a.id,b.m1 jcdname,'1' type "
				+ " FROM yhpc_checkhiddeninfo a  "
				+ " LEFT JOIN fxgk_accidentrisk b ON b.id = a.pointid"
				+ " WHERE a.dangerorigin='1' AND b.s3 = 0"
				+ " UNION SELECT a.id,b.name jcdname,'2' type"
				+ " FROM yhpc_checkhiddeninfo a  "
				+ " LEFT JOIN yhpc_checkpoint b ON b.id = a.pointid"
				+ " WHERE a.checkpointtype = '2' and a.dangerorigin='1' and b.usetype='2' ) a "
				+ " LEFT JOIN yhpc_checkhiddeninfo x ON x.id = a.id"
				+ " LEFT JOIN yhpc_checkcontent d on d.id=x.checkcontent_id"
				+ " LEFT JOIN t_user c on x.userid=c.ID"
				+ " WHERE  x.id="+id+" ORDER BY x.id";
		List<Map<String, Object>> list=findBySql(sql, null, Map.class);
		return list.get(0);
	}
	
	/*
	 * 根据巡检记录id查询所有隐患记录id
	 */
	public List<Map<String, Object>> findIdByJlid(String jlid){
		String sql="select id from yhpc_checkhiddeninfo where checkresult_id="+jlid;
		List<Map<String, Object>> list=findBySql(sql, null, Map.class);
		return list;
	}
	
	/*
	 * 根据id删除巡检隐患记录
	 * 
	 */
	public void deleteById(String id){
		String sql="delete from yhpc_checkhiddeninfo where id ="+id;
		updateBySql(sql);
	}
	
	/**
     * 导出
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
    	String content=content(mapData);
		String sql="SELECT g.m1 wgname,a.jcdname xjdname,e.m1 qyname,d.content jcnr,case d.dangerlevel when '1' then '一般' else '重大' end yhjb,"
				+ "c.NAME yhfxr,x.*,CONVERT(varchar(100), x.createtime, 120)fxsj,tu.NAME zgrname,yh.handleuploadphoto zgzp,CONVERT(varchar(100), yh.handletime, 120) zgsj,(case x.dangerstatus when '0' then '未整改' when '1' then '整改待复查' when '2' then '复查未通过' when '3' then '整改完成' end) yhzt "
				+ " FROM ("
				+ " SELECT a.id,b.m1 jcdname,'1' type "
				+ " FROM yhpc_checkhiddeninfo a  "
				+ " LEFT JOIN fxgk_accidentrisk b ON b.id = a.pointid"
				+ " WHERE a.checkpointtype = '1' and a.dangerorigin='1' AND b.s3 = 0"
				+ " UNION SELECT a.id,b.name jcdname,'2' type"
				+ " FROM yhpc_checkhiddeninfo a  "
				+ " LEFT JOIN yhpc_checkpoint b ON b.id = a.pointid"
				+ " WHERE a.checkpointtype = '2' and a.dangerorigin='1' and b.usetype='2' ) a "
				+ " LEFT JOIN yhpc_checkhiddeninfo x ON x.id = a.id"
				+ " LEFT JOIN yhpc_checkcontent d on d.id=x.checkcontent_id"
				+ " LEFT JOIN bis_enterprise e on e.id=x.qyid"
				+ " LEFT JOIN t_user c on x.userid=c.ID"
				+ " LEFT JOIN t_barrio g on g.code=e.id2 "
				+ " LEFT JOIN (SELECT a.dangerid,a.userid,a.handleuploadphoto,a.handletime FROM (SELECT ROW_NUMBER() OVER (partition by dangerid ORDER BY handletime DESC) AS r,* FROM yhpc_handlerecord WHERE handletype = 1) a WHERE a.r<=1) yh ON x.id = yh.dangerid "
				+ " LEFT JOIN t_user tu ON tu.ID = yh.userid "
				+ " where e.s3=0 "+content +" order by x.createtime DESC";
		List<Map<String, Object>> list=findBySql(sql, null, Map.class);
		return list;
	}

	/**
	 * 查询隐患排查记录list app
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGridForApp(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY x.createtime desc) AS RowNumber,g.m1 wgname,a.jcdname xjdname,e.m1 qyname,d.content jcnr,d.dangerlevel yhjb,c.NAME yhfxr,x.* "
				+ " FROM ("
				+ " SELECT a.id,b.m1 jcdname,'1' type "
				+ " FROM yhpc_checkhiddeninfo a  "
				+ " LEFT JOIN fxgk_accidentrisk b ON b.id = a.pointid"
				+ " WHERE a.checkpointtype = '1' AND b.s3 = 0"
				+ " UNION SELECT a.id,b.name jcdname,'2' type"
				+ " FROM yhpc_checkhiddeninfo a  "
				+ " LEFT JOIN yhpc_checkpoint b ON b.id = a.pointid"
				+ " WHERE a.checkpointtype = '2' and b.usetype='2' ) a "
				+ " LEFT JOIN yhpc_checkhiddeninfo x ON x.id = a.id"
				+ " LEFT JOIN yhpc_checkcontent d on d.id=x.checkcontent_id"
				+ " LEFT JOIN bis_enterprise e on e.id=x.qyid"
				+ " LEFT JOIN t_user c on x.userid=c.ID"
				+ " LEFT JOIN t_barrio g on g.code=e.id2 "
				+ " where e.s3=0 " + content + " ) "
				+ " as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql,null, Map.class);
		return list;
	}
	
	/**
	 * 首页隐患app
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGridForSyApp(Map<String, Object> mapData) {
		String content=content(mapData);
		String content2 = "";
		if(mapData.get("zgzt")!=null&&mapData.get("zgzt")!=""){
			content2 = content2 + " AND c.dangerstatus = '"+mapData.get("zgzt")+"' "; 
		}
		if(mapData.get("jcdname")!=null&&mapData.get("jcdname")!=""){
			content2 = content2 + " AND b.name like '%"+mapData.get("jcdname")+"%' "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content2 = content2 + " AND d.id = "+mapData.get("qyid")+" "; 
		}
		String content3 = "";
		if(mapData.get("yhtype")!=null&&mapData.get("yhtype")!=""){
			content3 = content3 + " AND zz.yhtype = '"+mapData.get("yhtype")+"' "; 
		}
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY zz.createtime desc) AS RowNumber,* "
				+ "from (SELECT g.m1 wgname,a.jcdname xjdname,e.m1 qyname,d.content jcnr,c.NAME yhfxr,x.*,'1' yhtype FROM (SELECT a.id,b.m1 jcdname,'1' type FROM yhpc_checkhiddeninfo a LEFT JOIN fxgk_accidentrisk b ON b.id = a.pointid WHERE a.checkpointtype = '1' AND b.s3 = 0 "
				+ "UNION SELECT a.id,b.name jcdname,'2' type FROM yhpc_checkhiddeninfo a LEFT JOIN yhpc_checkpoint b ON b.id = a.pointid WHERE a.checkpointtype = '2' and b.usetype='2' ) a LEFT JOIN yhpc_checkhiddeninfo x ON x.id = a.id "
				+ "LEFT JOIN yhpc_checkcontent d on d.id=x.checkcontent_id LEFT JOIN bis_enterprise e on e.id=x.qyid LEFT JOIN t_user c on x.userid=c.ID LEFT JOIN t_barrio g on g.code=e.id2 where e.s3=0 "+content
				+ " UNION SELECT e.m1 wgname,b.name xjdname,d.m1 qyname,h.content jcnr,f.NAME yhfxr,c.*,'2' yhtype FROM yhpc_checkhiddeninfo c LEFT JOIN yhpc_checkpoint b ON c.pointid = b.id LEFT JOIN bis_enterprise d ON d.id = b.id1 LEFT JOIN t_barrio e ON e.code = d.id2 "
				+ "LEFT JOIN t_user f ON f.ID = c.userid LEFT JOIN yhpc_checkcontent h ON c.checkcontent_id = h.id WHERE c.checkpointtype = 2 AND b.usetype = '1' AND d.s3 = 0 AND c.dangerorigin = '4' "+content2+") zz where 1=1 "+content3+") "
				+ " as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql,null, Map.class);
		return list;
	}
	
	/**
	 * 获取整改记录
	 * @param id
	 * @return
	 */
	public List<Map<String, Object>> getzglist(Long id) {
		String sql ="SELECT a.*,b.NAME zgr FROM yhpc_handlerecord a LEFT JOIN t_user b ON b.ID = a.userid where dangerid ="+id+" ORDER BY a.id";
		List<Map<String, Object>> list=findBySql(sql,null, Map.class);
		return list;
	}
}
