package com.cczu.model.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.ITdicBisHazardIdentityDao;
import com.cczu.model.entity.Tdic_BIS_HazardIdentityEntity;
import com.cczu.model.service.ITdicBisHazardIdentityService;
import com.cczu.sys.comm.mapper.JsonMapper;

@Transactional(readOnly = true)
@Service("ITdicBisHazardIdentityService")
public class TdicBisHazardIdentityServiceImpl implements ITdicBisHazardIdentityService {

	@Resource
	private ITdicBisHazardIdentityDao iTdicBisHazardIdentityDao;

	@Override
	public String dataList(String m1) {
		List<Tdic_BIS_HazardIdentityEntity> list = iTdicBisHazardIdentityDao.dataList(m1);
		List<Map<String, Object>> arrylist = new ArrayList<Map<String, Object>>();
		for (Tdic_BIS_HazardIdentityEntity tbh : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", tbh.getM2());
			map.put("text", tbh.getM2());
			arrylist.add(map);
		}
		return JsonMapper.getInstance().toJson(arrylist);
	}

	@Override
	public Tdic_BIS_HazardIdentityEntity findAll(Long id) {
		// TODO Auto-generated method stub
		return iTdicBisHazardIdentityDao.findAll(id);
	}

	@Override
	public String findBymap(Map<String, Object> map) {
		Tdic_BIS_HazardIdentityEntity bis = iTdicBisHazardIdentityDao.findBymap(map);
		if (bis != null) {
			return String.valueOf(bis.getM3());
		} else {
			return "0";
		}

	}

	public List<Map<String, Object>> findWzList() {
		return iTdicBisHazardIdentityDao.findWzList();
	}
}
