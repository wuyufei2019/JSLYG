package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.Tdic_BIS_HazardIdentityEntity;

public interface ITdicBisHazardIdentityDao {
	public List<Tdic_BIS_HazardIdentityEntity> dataList(String m1);
	
	public Tdic_BIS_HazardIdentityEntity findAll(Long id);

	public Tdic_BIS_HazardIdentityEntity findBymap(Map<String,Object> map);
	
	public List<Map<String,Object>> findWzList();
}
