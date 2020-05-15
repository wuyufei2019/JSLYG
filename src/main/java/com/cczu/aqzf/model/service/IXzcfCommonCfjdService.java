package com.cczu.aqzf.model.service;


import com.cczu.aqzf.model.entity.XZCF_CfjdInfoEntity;

import java.util.Map;

/**
 * 行政处罚-简单处罚-处罚决定接口
 * @author jason
 *
 */
public interface IXzcfCommonCfjdService {
	
	/**
	 * 添加信息
	 * @param sfr
	 * @return  返回影响条数
	 */
	public Long addInfoReturnID(XZCF_CfjdInfoEntity jce);
	
	/**
	 * 告知分页查询信息
	 * @return 
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) ;
	/**
	 * 删除信息
	 * @param id
	 * @return int 影响条数
	 */
	public void deleteInfo(long id);
	/**
     * 根据id查找信息
     * @param id
     * @return XZCF_JYCFCfjdEntity
     */
	public XZCF_CfjdInfoEntity findInfoById(long id);
	/**
     * 根据立案审批id查找信息
     * @param id
     * @return XZCF_CfjdInfoEntity
     */
	public XZCF_CfjdInfoEntity findInfoByLaId(long lyid);
	/**
	 * 修改信息
	 * @param id
	 * @return int 影响条数
	 */
	public void updateInfo(XZCF_CfjdInfoEntity jce);
	
}