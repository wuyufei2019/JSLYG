package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.Tdic_BIS_HazardIdentityEntity;

public interface ITdicBisHazardIdentityService {
	
	public String dataList(String m1);
	public Tdic_BIS_HazardIdentityEntity findAll(Long id);
	/*
	 * 更加条件查询
	 */
	public String findBymap(Map<String, Object> map);
	
	public List<Map<String,Object>> findWzList();

}
