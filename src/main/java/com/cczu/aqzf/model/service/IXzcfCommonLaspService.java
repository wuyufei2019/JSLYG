package com.cczu.aqzf.model.service;


import com.cczu.aqzf.model.entity.XZCF_YbcfLaspEntity;

import java.util.List;
import java.util.Map;

/**
 * 行政处罚-一般处罚-立案审批接口
 * @author jason
 *
 */
public interface IXzcfCommonLaspService {
	
	
	/**
	 * 添加信息
	 * @param yle
	 * @return  返回影响条数
	 */
	public Long addInfoReturnID(XZCF_YbcfLaspEntity yle);
	
	/**
	 * 告知分页查询信息
	 * @return 
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) ;
	/**
	 * 分页查询信息2
	 * @return 
	 */
	//public Map<String, Object> dataGrid2(Map<String, Object> mapData) ;
	
	/**
	 * 删除信息
	 * @param id
	 * @return int 影响条数
	 */
	public void deleteInfo(long id);
	/**
     * 根据id查找信息
     * @param id
     * @return XZCF_YBCFLaspEntity
     */
	public XZCF_YbcfLaspEntity findInfoById(long id);
	
	/**
     * 查找一条待补全的数据
     * @param xzqy
     * @return XZCF_YBCFLaspEntity
     */
	public XZCF_YbcfLaspEntity findTempInfo(String xzqy);
	/**
	 * 修改信息
	 * @param yle
	 * @return int 影响条数
	 */
	public void updateInfo(XZCF_YbcfLaspEntity yle);

	/**
	 * 获取待补全的立案审批数量
	 * @param mapData
	 * @return int 影响条数
	 */
	public int getTempCount(Map<String, Object> mapData);
	/**
	 *获取所有和立案审批相关的number记录
	 * @param id
	 * @return int 影响条数
	 */
	public List<Map<String, Object>> getNumberlist(long id, String xzqy);

	/**
	 * 获取文书导出word
	 * @param id
	 * @return
	 */
	public Map<String, Object> getWsdcword(Long id);

	/**
	 * 获取送达回执文书导出word
	 * @param id
	 * @return
	 */
	public Map<String, Object> getWsdcsdhzword(Long id);

	public Map<String, Object> getJzword(Long id);
	
	/**
	 * 获取案件列表
	 */
	//public List<Map<String, Object>> findGzCaseNameList(String xzqy);
}
