package com.cczu.model.dao;

import com.cczu.model.entity.Bis_InfoChange;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 企业变更Dao
 * @author 
 * @date 2018年06月05日
 */
@Repository("BisInfoChangeDao")
public class BisInfoChangeDao extends BaseDao<Bis_InfoChange, Long> {

	public List<Map<String,String>> dataGrid(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = " SELECT top " + mapData.get("pageSize") 
                    + " * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id desc) AS RowNumber,a.*,bis.m1 qyname"
                    + " FROM bis_infochange a left join bis_enterprise bis on bis.id = a.qyid"
                    + " WHERE a.s3=0 and bis.s3=0 "
					+ content + " ) as a WHERE RowNumber > "
					+ mapData.get("pageSize") + "*(" + mapData.get("pageNo") + "-1) ";
		List<Map<String,String>> list = findBySql(sql, null, Map.class);
		return list;
	}

	public List<Map<String,Object>> dataGridForQy(Map<String, Object> map) {
		String sql = " SELECT a.*,bis.m1 qyname"
                    + " FROM bis_infochange a left join bis_enterprise bis on bis.id = a.qyid"
                    + " WHERE a.s3=0 and bis.s3=0 and a.result is not null "+content(map)+" order by a.id desc";
		List<Map<String,Object>> list = findBySql(sql, null, Map.class);
		return list;
	}

	public int getTotalCount(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = " SELECT COUNT(1) sum  FROM bis_infochange a left join bis_enterprise bis on bis.id = a.qyid"
					+ " WHERE a.s3=0 and bis.s3=0" + content;
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}

	public String content(Map<String, Object> mapData) {
		String content = "";
		if (mapData.get("modification") != null && mapData.get("modification") != "") {
			content = content + " AND a.modification = '" + mapData.get("modification") + "'";
		}
		if (mapData.get("qyname") != null && mapData.get("qyname") != "") {
			content = content + " AND bis.m1 like '%" + mapData.get("qyname") + "%'";
		}
		if (mapData.get("qyid") != null && mapData.get("qyid") != "") {
			content = content + " AND bis.id =" + mapData.get("qyid");
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + " AND bis.id2 LIKE '"+mapData.get("xzqy")+"%' "; 
		}
		if(mapData.get("cjz")!=null&&mapData.get("cjz")!=""){
			content = content + "AND bis.cjz='"+mapData.get("cjz")+"' "; 
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND ','+bis.m36+',' like '%,"+mapData.get("jglx")+",%' "; 
		}
		return content;
	}

	public void deleteInfo(Long id) {
		String sql = " UPDATE bis_infochange SET S3=1 WHERE ID=" + id;
		updateBySql(sql);
	}
	

}
