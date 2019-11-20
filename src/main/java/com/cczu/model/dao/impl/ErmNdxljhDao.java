package com.cczu.model.dao.impl;

import com.cczu.model.entity.ERM_EmergencyTrainPlanYearEntity;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("ErmNdxljhDao")
public class ErmNdxljhDao extends BaseDao<ERM_EmergencyTrainPlanYearEntity,Long>  {

	/**
	 * 查询所有年度训练计划信息
	 * @return
	 */
	public List<Map<String, Object>> findAllInfo() {
		String sql ="SELECT * FROM erm_emergencytrainplanyear WHERE s3=0";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
		
	}

	/**
	 * 添加年度训练计划信息
	 * @param erm
	 */
	public void addInfo(ERM_EmergencyTrainPlanYearEntity erm) {
		save(erm);
	}

	/**
	 * 修改年度训练计划信息
	 * @param erm
	 */
	public void updateInfo(ERM_EmergencyTrainPlanYearEntity erm) {
		save(erm);
	}

	/**
	 * 删除年度训练计划信息
	 * @param id
	 */
	public void deleteInfo(Long id) {
		String sql=" UPDATE erm_emergencytrainplanyear SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	/**
	 * 查询数据
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.qyid,a.id) AS RowNumber,b.m1 qynm,a.*,dep.m1 depname "
				+ " FROM erm_emergencytrainplanyear a "
				+ " left join bis_enterprise b on a.qyid=b.id "
				+ " left join t_department dep on a.depid=dep.id "
				+ " WHERE b.s3=0 and a.s3=0 and a.qyid is not null " + content + " ) "
				+ " as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		
		return list;
	}

	/**
	 * 统计年度训练计划数量
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM erm_emergencytrainplanyear a"
				+ " left join bis_enterprise b on a.qyid=b.id "
				+ " left join t_department dep on a.depid=dep.id "
				+ " WHERE a.s3=0 and b.s3=0 and a.qyid is not null " + content;
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
		if(mapData.get("m2")!=null&&mapData.get("m2")!=""){
			content = content +" AND a.M2 ='"+mapData.get("m2")+"' "; 
		}
		if(mapData.get("m3")!=null&&mapData.get("m3")!=""){
			content = content +" AND a.M3 ='"+mapData.get("m3")+"' "; 
		}
		if(mapData.get("qyid")!=null && mapData.get("qyid")!=""){
			content = content +" AND a.qyid ="+ mapData.get("qyid")+" "; 
		}
		if(mapData.get("qynm")!=null && mapData.get("qynm")!=""){
			content = content +" AND b.M1 LIKE'%"+mapData.get("qynm")+"%' "; 
		}
		if(mapData.get("depid")!=null && mapData.get("depid")!=""){
			content = content +" AND a.depid ="+ mapData.get("depid")+" ";
		}
		if(mapData.get("depname")!=null && mapData.get("depname")!=""){
			content = content +" AND dep.m1 like'%"+ mapData.get("depname")+"%' ";
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + " AND ( b.fid='"+mapData.get("fid")+"' or b.id="+mapData.get("fid")+") "; 
		}		
		return content;
	}
    
    /**
     * 查询条件
     * @param mapData
     * @return
     */
    public String content2(Map<String, Object> mapData) {
		
		String content="";
		if(mapData.get("m2")!=null&&mapData.get("m2")!=""){
			content = content +"AND M2 ="+mapData.get("m2")+"%'"; 
		}
		if(mapData.get("m3")!=null&&mapData.get("m3")!=""){
			content = content +"AND M3 ="+mapData.get("m3")+"%'"; 
		}
		if(mapData.get("qyid")!=null && mapData.get("qyid")!=""){
			content = content +"AND qyid ="+ mapData.get("qyid"); 
		}
		return content;
	}

	/**
	 *
	 * 通过id查找年度训练计划信息
	 * @param id
	 * @return
	 */
	public Map<String, Object> findById(Long id) {
		String sql ="SELECT a.*,b.m1 qyname,dep.m1 depname FROM erm_emergencytrainplanyear a "
				+ " left join bis_enterprise b on a.qyid=b.id "
				+ " left join t_department dep on a.depid=dep.id "
				+ " WHERE a.s3=0 AND a.ID="+id;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	/**
	 * 获得导出数据
	 * @return
	 */
	public List<Map<String, Object>> getExcel(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="SELECT b.m1 qynm,a.* "
		+ " FROM erm_emergencytrainplanyear a "
		+ " left join bis_enterprise b on a.qyid=b.id "
		+ " WHERE b.s3=0 and a.s3=0 and a.qyid is not null " + content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
}
