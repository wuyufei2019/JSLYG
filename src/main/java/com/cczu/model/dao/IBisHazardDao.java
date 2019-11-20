package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.BIS_HazardEntity;

public interface IBisHazardDao {


	/**
	 * 通过id
	 * @param qyid
	 * @return
	 */
	public BIS_HazardEntity findById(Long qyid);
    /**通过企业id进行查询
     * @param qyid
     * @return
     */
	public BIS_HazardEntity findQyId(Long id);
	
	/**通过企业id进行查询(主要危险性名称)
	 * @param qyid
	 * @return
	 */
	public Map<String,Object> findMapQyId(Long qyid);
	
	/**添加
	 * @param bis
	 */
	public void addInfo(BIS_HazardEntity bis);
	
	/**修改
	 * @param bis
	 */
	public void updateInfo(BIS_HazardEntity bis);
	
	/**
	 * 删除
	 * @param id
	 */
	public void deleteInfo(Long id);
	
    /**
     * 分页查询
     * @param mapData
     * @return
     */
    public List<Map<String,Object>> dataGrid(Map<String,Object> mapData);
	
	/**
	 * 查询条数
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData);
	
	public List<Map<String,Object>> getExport(Map<String, Object> mapData);
}
