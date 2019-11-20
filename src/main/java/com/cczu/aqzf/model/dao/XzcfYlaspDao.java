package com.cczu.aqzf.model.dao;

import com.cczu.aqzf.model.entity.XZCF_YbcfYlaspEntity;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Repository("XzcfYlaspDao")
public class XzcfYlaspDao extends BaseDao<XZCF_YbcfYlaspEntity, Long> {

	/**
	 * 添加预立案并返回id
	 * @param ylasp
	 * @return
	 */
	public Long addInfoReturnID(XZCF_YbcfYlaspEntity ylasp) {
		save(ylasp);
		return ylasp.getID();
	}

	/**
	 * 分页显示
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content = this.content(mapData);
		String sql = " SELECT TOP "
				+ mapData.get("pageSize")
				+ " * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.ID DESC)  AS RowNumber,a.id,a.ayname,a.casesource,a.filldate,a.dsperson,a.number,a.ylaflag from xzcf_ybcfylasp a left join bis_enterprise b on a.id1=b.id left join t_user u on a.userid = u.id where a.s3=0 and b.s3=0 "
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
		String content = content(mapData);
		String sql = "SELECT COUNT(1) SUM  FROM xzcf_ybcfylasp a left join bis_enterprise b on a.id1=b.id left join t_user u on a.userid = u.id WHERE a.S3=0 and b.s3=0"
				+ content;
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}
	
	/**
	 * 删除预立案审批
	 * @param id
	 */
	public void deleteInfo(long id) {
		String sql = " UPDATE xzcf_ybcfylasp SET S3=1 WHERE ID=" + id;
		updateBySql(sql);
	}
	
	/**
	 * 修改预立案审批状态
	 * @param id
	 */
	public void updatezt(long id) {
		String sql = " UPDATE xzcf_ybcfylasp SET ylaflag = '1' WHERE ID=" + id;
		updateBySql(sql);
	}

	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public XZCF_YbcfYlaspEntity findInfoById(long id) {
		String sql = " SELECT  * FROM  xzcf_ybcfylasp WHERE ID=" + id;
		List<XZCF_YbcfYlaspEntity> list = findBySql(sql, null,XZCF_YbcfYlaspEntity.class);
		if(list.size()<1){
			return null;
		}
		return list.get(0);
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
		if (mapData.get("xzqy") != null && mapData.get("xzqy") != "") {
			content = content + " AND u.xzqy like '" + mapData.get("xzqy")+ "%'";
		}

		return content;
	}

}
