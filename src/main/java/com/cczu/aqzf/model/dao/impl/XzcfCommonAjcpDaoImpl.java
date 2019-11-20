package com.cczu.aqzf.model.dao.impl;

import com.cczu.aqzf.model.dao.IZxcfCommonAjcpDao;
import com.cczu.aqzf.model.entity.XZCF_YbcfAjcpEntity;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Repository("XzcfCommonAjcpDao")
public class XzcfCommonAjcpDaoImpl extends BaseDao<XZCF_YbcfAjcpEntity, Long>
		implements IZxcfCommonAjcpDao {

	@Override
	public Long addInfoReturnID(XZCF_YbcfAjcpEntity yae) {
		// TODO Auto-generated method stub
		save(yae);
		return yae.getID();
	}

	@Override
	//分页！
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content = this.content(mapData);
		String sql = " SELECT TOP "+ mapData.get("pageSize")
				+ " * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY b.id DESC)  AS RowNumber,a.id,a.punishname,a.cbr1,a.cbr2,a.number,a.casename,a.illegalactandevidence from xzcf_ybcfajcp a"
				+ " left join xzcf_ybcflasp b on a.id1= b.id left join bis_enterprise c on c.id=b.id1 left join t_user u on b.userid = u.id where a.s3=0  and b.s3=0 and c.s3=0 "+ content
				+ " ) AS a WHERE  RowNumber > "+ mapData.get("pageSize")+ "*("+ mapData.get("pageNo")+ "-1)";
		List<Map<String,Object>> list = findBySql(sql, null, Map.class);
		return list;
	}
	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content = content(mapData);
		String sql = "SELECT COUNT(1) SUM  FROM xzcf_ybcfajcp a  left join xzcf_ybcflasp b on a.id1= b.id left join bis_enterprise c on c.id=b.id1 left join t_user u on b.userid = u.id"
				+ " where a.s3=0  and b.s3=0 and c.s3=0 "+ content;
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}

	@Override
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		String sql = " UPDATE xzcf_ybcfajcp SET S3=1 WHERE ID=" + id;
		updateBySql(sql);
	}

	@Override
	public XZCF_YbcfAjcpEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		String sql = " SELECT  * FROM  xzcf_ybcfajcp WHERE ID=" + id;
		List<XZCF_YbcfAjcpEntity> list = findBySql(sql, null,
				XZCF_YbcfAjcpEntity.class);
		return list.get(0);
	}
	@Override
	public XZCF_YbcfAjcpEntity findInfoByLaId(long laid) {
		// TODO Auto-generated method stub
		String sql="select a.* from xzcf_ybcfajcp a ,xzcf_ybcflasp b where b.id=a.id1 and a.s3=0 and b.s3=0 and a.id1="+laid;
		List<XZCF_YbcfAjcpEntity> list= findBySql(sql, null, XZCF_YbcfAjcpEntity.class);
		if(list.size()<1){
			return null;
		}
		return list.get(0);
	}

	@Override
	public void updateInfo(XZCF_YbcfAjcpEntity yae) {
		// TODO Auto-generated method stub
		save(yae);
	}
	@Override
	public void updateLaspInfo(long id) {
		// TODO Auto-generated method stub
		String sql=" update b set cbflag=0  from  xzcf_ybcfajcp  a LEFT JOIN xzcf_ybcflasp  b on  b.id=a.id1 where a.id= "+id;
		updateBySql(sql);
	}

	/**
	 * 查询条件判断
	 * 
	 * @return
	 * @throws ParseException
	 */
	public String content(Map<String, Object> mapData) {
		String content = " ";
		if (mapData.get("casename") != null && mapData.get("casename") != "") {
			content = content + " AND a.casename like '%" + mapData.get("casename")+ "%'";
		}
		if (mapData.get("punishname") != null && mapData.get("punishname") != "") {
			content = content + " AND a.punishname like '%" + mapData.get("punishname")+ "%'";
		}
		if (mapData.get("number") != null && mapData.get("number") != "") {
			content = content + " AND a.number like '%" + mapData.get("number")+ "%'";
		}
		if (mapData.get("xzqy") != null && mapData.get("xzqy") != "") {
			content = content +" AND u.xzqy like '"+mapData.get("xzqy")+"%' ";
		}
		if (mapData.get("apptj") != null && mapData.get("apptj") != "") {
			content = content + " AND (a.number like '%" + mapData.get("apptj")+ "%' OR a.punishname like '%" + mapData.get("apptj")+ "%')";
		}
		return content;
	}

}
