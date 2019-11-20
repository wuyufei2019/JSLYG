package com.cczu.model.sgzn.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.sgzn.entity.SgznAqsgEntity;
import com.cczu.util.dao.BaseDao;
/**
 * 事故灾难类-安全事故信息快报Dao
 * @author Administrator
 *
 */
@Repository("SgznAqsgDao")
public class SgznAqsgDao extends BaseDao<SgznAqsgEntity, Long>{
	
	
	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id desc) AS RowNumber,a.* FROM sgzn_aqsg a  WHERE a.S3=0  "+content+") "
				  + "as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		
		return list;
	}
	
	
	
	/**
     * 查询条件
     * @param mapData
     * @return
     */
    public String content(Map<String, Object> mapData) {
    	String content ="";
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content +" AND a.M1 LIKE'%"+mapData.get("m1")+"%'"; 
		}
    	
    	
    	
    	return content;
    }
	
	 /**
     * 分页统计数量
     * @param mapData
     * @return
     */
    public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM  sgzn_aqsg a WHERE S3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	
	
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" UPDATE sgzn_aqsg SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}
	
	
	
	
	
	
	
}
