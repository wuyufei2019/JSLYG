package com.cczu.aqzf.model.dao;

import com.cczu.aqzf.model.entity.XZCF_YbcfAjcpEntity;

import java.util.List;
import java.util.Map;

public interface IZxcfCommonAjcpDao {
	/**
	 * 添加信息
	 * @param sfr
	 * @return 返回影响记录条数
	 */
	public Long addInfoReturnID(XZCF_YbcfAjcpEntity yae);
	
	/**
	 * 分页查询信息
	 * @return 
	 */
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) ;
	
	/**
     * 查询数据表格_总记录数
     * @return
     */
	public int getTotalCount(Map<String, Object> mapData);
	
	/**
	 * 删除信息
	 * @param id
	 * @return int 影响条数
	 */
	public void deleteInfo(long id);
	/**
     * 根据id查找件信息
     * @param id
     * @return XZCF_YbcfAjcpEntity
     */
	public XZCF_YbcfAjcpEntity findInfoById(long id);
	/**
     * 根据立案id查找件信息
     * @param id
     * @return XZCF_YbcfAjcpEntity
     */
	public XZCF_YbcfAjcpEntity findInfoByLaId(long laid);
	
	
	/**
	 * 修改信息
	 * @param id
	 * @return int 影响条数
	 */
	public void updateInfo(XZCF_YbcfAjcpEntity yae);
	
	public void updateLaspInfo(long id);
	
}



