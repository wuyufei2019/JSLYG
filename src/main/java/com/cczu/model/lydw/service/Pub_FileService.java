package com.cczu.model.lydw.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.cczu.sys.comm.mapper.JsonMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cczu.model.lydw.dao.Pub_FileDao;
import com.cczu.model.lydw.entity.Pub_File;

/*
 * 蓝牙定位-工卡管理
 */
@Transactional(readOnly=true)
@Service("Pub_FileService")
public class Pub_FileService{
	
	@Resource
	private Pub_FileDao pfDao;
	

	public void addInfo(Pub_File pf) {
		pfDao.saveInfo(pf);
	}

	public void updateInfo(Pub_File pf) {
		pfDao.saveUp(pf);
	}


	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Pub_File> list=pfDao.dataGrid(mapData);
		int getTotalCount=pfDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}


	public void deleteInfo(Long fileid) {
		// TODO Auto-generated method stub
		pfDao.delete(fileid);
	}

	public Pub_File findById(Long id) {
		return pfDao.findById(id);
	}

	/**
	 * 工卡号json
	 * @return
	 */
	public String jsonlist() {
		return JsonMapper.getInstance().toJson(pfDao.jsonlist());
	}
}
