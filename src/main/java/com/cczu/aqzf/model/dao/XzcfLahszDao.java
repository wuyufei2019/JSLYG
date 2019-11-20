package com.cczu.aqzf.model.dao;

import com.cczu.aqzf.model.entity.XZCF_YbcfLaspEntity;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Repository("XzcfLahszDao")
public class XzcfLahszDao extends BaseDao<XZCF_YbcfLaspEntity, Long> {

	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content = this.content(mapData);
		String sql = " SELECT TOP "
				+ mapData.get("pageSize")
				+ " * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.ID DESC)  AS RowNumber,a.id,a.ayname,a.casesource,a.filldate,a.dsperson,a.number,a.gzflag,a.tzflag,a.cfjdflag,a.xwflag,a.cbflag,a.dcflag,a.cgflag,a.qzflag,a.jaflag,a.tempflag,a.sbflag,a.tlflag,a.fkspflag,a.cxflag  from xzcf_ybcflasp a left join bis_enterprise b on a.id1=b.id left join t_user u on a.userid = u.id where a.s3=1 and b.s3=0 "
				+ content
				+ " ) "
				+ "AS a WHERE  RowNumber > "
				+ mapData.get("pageSize")
				+ "*("
				+ mapData.get("pageNo")
				+ "-1)";
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		return list;
	}
	
	public int getTotalCount(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content = content(mapData);
		String sql = "SELECT COUNT(1) SUM  FROM xzcf_ybcflasp a left join bis_enterprise b on a.id1=b.id left join t_user u on a.userid = u.id WHERE a.S3=1 and b.s3=0"
				+ content;
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
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
			content = content + " AND a.ayname like '%" + mapData.get("name")+ "%'";
		}
		if (mapData.get("qyname") != null && mapData.get("qyname") != "") {
			content = content + " AND a.dsperson like '%" + mapData.get("qyname")+ "%'";
		}
		if (mapData.get("casesource") != null && mapData.get("casesource") != "") {
			content = content + " AND a.casesource = '" + mapData.get("casesource")+ "'";
		}
		if (mapData.get("startdate") != null && mapData.get("startdate") != "") {
			content = content + " AND a.filldate >= '"+ mapData.get("startdate") + "'";
		}
		if (mapData.get("enddate") != null && mapData.get("enddate") != "") {
			content = content + " AND a.filldate <= '"+ mapData.get("enddate") + "'";
		}
		if (mapData.get("number") != null && mapData.get("number") != "") {
			content = content + " AND a.number like '%" + mapData.get("number")+ "%'";
		}
		if (mapData.get("xzqy") != null && mapData.get("xzqy") != "") {
			content = content + " AND u.xzqy like '" + mapData.get("xzqy")+ "%'";
		}

		return content;
	}
	
	/**
	 * 恢复立案审批
	 */
	public void hflasp(String id){
		String sql = " UPDATE xzcf_ybcflasp SET S3=0 WHERE  ID=" + id;
		updateBySql(sql);
	}
}
