package com.cczu.aqzf.model.dao;

import com.cczu.aqzf.model.entity.XZCF_YbcfSxcgEntity;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Repository("XzcfCommonSxcgDao")
public class XzcfCommonSxcgDao extends BaseDao<XZCF_YbcfSxcgEntity, Long> {

	 
	public Long addInfoReturnID(XZCF_YbcfSxcgEntity yse) {
		// TODO Auto-generated method stub
		save(yse);
		return yse.getID();
	}

	 
	// 分页！
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content = this.content(mapData);
		String sql = " SELECT TOP "+ mapData.get("pageSize")
				+ " * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY b.id DESC)  AS RowNumber,a.id,a.number,a.qyname,a.xzjd,a.extraxzjd,a.finedate,a.allfine,a.sxcgsj,b.qzflag,b.jaflag from xzcf_ybcfsxcg a "
				+ " left join xzcf_ybcflasp b  on a.id1 =b.id  left join bis_enterprise c  on b.id1=c.id left join t_user u on b.userid = u.id where a.s3=0 and b.s3=0 and c.s3=0  "
				+ content + " ) " + "AS a WHERE  RowNumber > "
				+ mapData.get("pageSize") + "*(" + mapData.get("pageNo")
				+ "-1)";
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		return list;
	}

	 
	public int getTotalCount(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content = content(mapData);
		String sql = "SELECT COUNT(1) SUM  FROM xzcf_ybcfsxcg a left join xzcf_ybcflasp b on a.id1= b.id left join bis_enterprise c on c.id=b.id1 left join t_user u on b.userid = u.id "
				+ "WHERE b.s3=0 and c.s3=0 and a.S3=0 "+ content;
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}

	 
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		String sql = " UPDATE xzcf_ybcfsxcg SET S3=1 WHERE ID=" + id;
		updateBySql(sql);
	}

	 
	public XZCF_YbcfSxcgEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		String sql = " SELECT  * FROM  xzcf_ybcfsxcg WHERE ID=" + id;
		List<XZCF_YbcfSxcgEntity> list = findBySql(sql, null,
				XZCF_YbcfSxcgEntity.class);
		return list.get(0);
	}

	 
	public void updateInfo(XZCF_YbcfSxcgEntity yse) {
		// TODO Auto-generated method stub
		save(yse);
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
			content = content + " AND a.qyname like '%" + mapData.get("name")+ "%'";
		}
		if (mapData.get("number") != null && mapData.get("number") != "") {
			content = content + " AND a.number like '%" + mapData.get("number")+ "%'";
		}
		//是否满足催告条件
		if (mapData.get("type") != null && mapData.get("type") != "") {
			if("1".equals(mapData.get("type"))){
				content+=" and b.jaflag !=1 and b.qzflag!=1 and DATEDIFF (day,a.sxcgsj,getDate())>50";
			}else
				content+=" AND (b.jaflag =1 or b.qzflag=1 or DATEDIFF (day,a.sxcgsj,getDate())<=50)";
		}
	/*	if (mapData.get("startdate") != null && mapData.get("startdate") != "") {
			content = content + " AND a.punishdate >= '"+ mapData.get("startdate") + "'";
		}
		if (mapData.get("enddate") != null && mapData.get("enddate") != "") {
			content = content + " AND a.punishdate <= '"+ mapData.get("enddate") + "'";
		}*/
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
			content = content + " AND (a.number like '%" + mapData.get("apptj")+ "%' OR a.qyname like '%" + mapData.get("apptj")+ "%')";
		}

		return content;
	}

	 
	public XZCF_YbcfSxcgEntity findInfoByLaId(long laid) {
		// TODO Auto-generated method stub
		String sql = "select a.* from xzcf_ybcfsxcg a ,xzcf_ybcflasp b where b.id=a.id1 and a.s3=0 and b.s3=0 and a.id1="+ laid;
		List<XZCF_YbcfSxcgEntity> list = findBySql(sql, null,
				XZCF_YbcfSxcgEntity.class);
		if(list.size()<1){
			return null;
		}
		return list.get(0);
	}

	 
	public void updateLaspInfo(long id) {
		// TODO Auto-generated method stub
		String sql = " update b set cgflag=0  from  xzcf_ybcfsxcg  a LEFT JOIN xzcf_ybcflasp  b on  b.id=a.id1 where a.id= "+ id;
		updateBySql(sql);
	}

}
