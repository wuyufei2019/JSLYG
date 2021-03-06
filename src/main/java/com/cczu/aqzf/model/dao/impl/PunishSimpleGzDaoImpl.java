package com.cczu.aqzf.model.dao.impl;

import com.cczu.aqzf.model.dao.IPunishSimpleGzDao;
import com.cczu.aqzf.model.entity.XZCF_GzsInfoEntity;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Repository("PunishSimpleGzDao")
public class PunishSimpleGzDaoImpl extends BaseDao<XZCF_GzsInfoEntity, Long>
		implements IPunishSimpleGzDao {

	@Override
	public Long addInfoReturnID(XZCF_GzsInfoEntity jie) {
		// TODO Auto-generated method stub
		save(jie);
		return jie.getID();
	}

	@Override
	// 分页！
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content = this.content(mapData);
		String sql = " SELECT TOP "+ mapData.get("pageSize")
				+ " * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY b.id DESC)  AS RowNumber,a.id,a.xzcf,a.number,a.name,a.punishdate,a.illegalact,a.unlaw from xzcf_gz a "
				+ " left join xzcf_ybcflasp b  on a.id1 =b.id  left join bis_enterprise c  on b.id1=c.id left join t_user u on b.userid = u.id where a.s3=0 and b.s3=0 and c.s3=0  "
				+ content + " ) " + "AS a WHERE  RowNumber > "
				+ mapData.get("pageSize") + "*(" + mapData.get("pageNo")
				+ "-1)";
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		return list;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content = content(mapData);
		String sql = "SELECT COUNT(1) SUM  FROM xzcf_gz a left join xzcf_ybcflasp b on a.id1= b.id left join bis_enterprise c on c.id=b.id1 left join t_user u on b.userid = u.id "
				+ "WHERE b.s3=0 and c.s3=0 and a.S3=0 "+ content;
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}

	@Override
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		String sql = " UPDATE xzcf_gz SET S3=1 WHERE ID=" + id;
		updateBySql(sql);
	}

	@Override
	public XZCF_GzsInfoEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		String sql = " SELECT  * FROM  xzcf_gz WHERE ID=" + id;
		List<XZCF_GzsInfoEntity> list = findBySql(sql, null,
				XZCF_GzsInfoEntity.class);
		if(list.size()<1){
			return null;
		}
		return list.get(0);
	}

	@Override
	public void updateInfo(XZCF_GzsInfoEntity jie) {
		// TODO Auto-generated method stub
		save(jie);
	}

	/**
	 * 查询条件判断
	 * 
	 * @return
	 * @throws ParseException
	 */
	public String content(Map<String, Object> mapData) {
		String content = " ";
		if (mapData.get("name") != null && mapData.get("name") != "") {
			content = content + " AND a.name like '%" + mapData.get("name")+ "%'";
		}
		if (mapData.get("startdate") != null && mapData.get("startdate") != "") {
			content = content + " AND a.punishdate >= '"+ mapData.get("startdate") + "'";
		}
		if (mapData.get("enddate") != null && mapData.get("enddate") != "") {
			content = content + " AND a.punishdate <= '"+ mapData.get("enddate") + "'";
		}
		if (mapData.get("cftype") != null && mapData.get("cftype") != "") {
			content = content + " AND a.cftype = '" + mapData.get("cftype")+ "'";
		}
		if (mapData.get("number") != null && mapData.get("number") != "") {
			content = content + " AND a.number like '%" + mapData.get("number")+ "%'";
		}
		if (mapData.get("xzqy") != null && mapData.get("xzqy") != "") {
			content = content +" AND u.xzqy like '"+mapData.get("xzqy")+"%' ";
		}
		// if (mapData.get("month") != null && mapData.get("month") != "") {
		// content = content + " AND SUBSTRING(a.m2,6,len(a.m2)) = '"
		// + mapData.get("month") + "'";
		// }
		// if (mapData.get("id") != null && mapData.get("id") != "") {
		// content = content + " AND PATINDEX('%" + mapData.get("id")
		// + "%', a.qyids)>0 ";
		// }
		if (mapData.get("apptj") != null && mapData.get("apptj") != "") {
			content = content + " AND (a.number like '%" + mapData.get("apptj")+ "%' OR a.name like '%" + mapData.get("apptj")+ "%')";
		}

		return content;
	}

	@Override
	public XZCF_GzsInfoEntity findInfoByLaId(long laid) {
		// TODO Auto-generated method stub
		String sql = "select a.* from xzcf_gz a ,xzcf_ybcflasp b where b.id=a.id1 and a.s3=0 and b.s3=0 and a.id1="+ laid;
		List<XZCF_GzsInfoEntity> list = findBySql(sql, null,
				XZCF_GzsInfoEntity.class);
		if(list.size()<1){
			return null;
		}
		return list.get(0);
	}

	@Override
	public void updateLaspInfo(long id) {
		// TODO Auto-generated method stub
		String sql = " update b set gzflag=0  from  xzcf_gz  a LEFT JOIN xzcf_ybcflasp  b on  b.id=a.id1 where a.id= "+ id;
		updateBySql(sql);
	}

}
