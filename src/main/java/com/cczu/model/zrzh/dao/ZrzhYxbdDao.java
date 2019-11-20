package com.cczu.model.zrzh.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.zrzh.entity.ZrzhYxbdEntiy;
import com.cczu.util.dao.BaseDao;
/**
 * 自然灾害类-雨雪冰冻信息快报Dao
 * @author Administrator
 *
 */
@Repository("ZrzhYxbdDao")
public class ZrzhYxbdDao extends BaseDao<ZrzhYxbdEntiy,Long>{
	
	
	
	
	
	/**
	 * 分页查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id desc) AS RowNumber,a.* FROM zrzh_yxbd a  WHERE a.S3=0  "+content+") "
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
			content = content +" AND a.m1 ='"+mapData.get("m1")+"'"; 
		}
		if(mapData.get("m2")!=null&&mapData.get("m2")!=""){
			content = content +" AND a.m2 ='"+mapData.get("m2")+"'"; 
		}
    	
    	
    	return content;
    }
	
	
	/**
	 * 统计数量
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM  zrzh_yxbd a WHERE S3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	
	
	
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" UPDATE zrzh_yxbd SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}
	
	
	
	
	
	
	
	
}
