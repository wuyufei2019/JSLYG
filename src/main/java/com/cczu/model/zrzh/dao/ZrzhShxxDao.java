package com.cczu.model.zrzh.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.zrzh.entity.ZrzhShxxEntiy;
import com.cczu.util.dao.BaseDao;
/**
 * 自然灾害类-水旱信息快报Dao
 * @author Administrator
 *
 */
@Repository("ZrzhShxxDao")
public class ZrzhShxxDao extends BaseDao<ZrzhShxxEntiy, Long> {
	
	
	/**
	 * 分页查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id desc) AS RowNumber,a.* FROM zrzh_shxx a  WHERE a.S3=0  "+content+") "
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
		if(mapData.get("m2_1")!=null&&mapData.get("m2_1")!=""){
			content = content +" AND a.M2_1 LIKE'%"+mapData.get("m2_1")+"%'"; 
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
		String sql=" SELECT COUNT(*) sum  FROM  zrzh_shxx a WHERE S3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	
	
	
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" UPDATE zrzh_shxx SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}
	
	
	
	
	
	
	
	
	
}
