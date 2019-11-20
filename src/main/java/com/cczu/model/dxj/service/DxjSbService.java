package com.cczu.model.dxj.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.dxj.dao.DxjSbDao;
import com.cczu.model.dxj.entity.DXJ_SbEntity;

/**
 *Service
 */
@Service("DxjSbService")
public class DxjSbService {
	
	@Resource
	private DxjSbDao dxjSbDao;
	
	/**
	 * 记录list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=dxjSbDao.dataGrid(mapData);
		int getTotalCount=dxjSbDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 得到设备list
	 * @return
	 */
	public List<Map<String, Object>> getsblist(Map<String, Object> mapData) {
		return dxjSbDao.getsblist(mapData);
	}

	/**
	 * 添加修改信息
	 * @param jcdy
	 */
	public String addInfo(DXJ_SbEntity jcdy,long id) {
		String datasuccess = "success";
		//判断绑定二维码rfid是否重复
		if(checkSameBuildContent(id, jcdy.getBindcontent())&&(!com.cczu.util.common.StringUtils.isEmpty(jcdy.getBindcontent()))){
			datasuccess = "ewmerror";
		}else if(checkSameRfid(id,jcdy.getRfid())&&(!com.cczu.util.common.StringUtils.isEmpty(jcdy.getRfid()))){
			datasuccess = "rfiderror";
		}else{
			try {
				dxjSbDao.save(jcdy);
			} catch (Exception e) {
				e.printStackTrace();
				datasuccess="error";
			}
		}
		return datasuccess;
	}
	
	//验证二维码重复
	public boolean checkSameBuildContent(long id,String bindcontent) {
		return dxjSbDao.checkSameBuildContent(id,bindcontent);
	}
	
	//验证rfid重复
	public boolean checkSameRfid(long id,String rfid) {
		return dxjSbDao.checkSameRfid(id,rfid);
	}

	/**
	 * 删除
	 * @param parseLong
	 */
	public void deleteInfo(long id) {
		dxjSbDao.deleteInfo(id);
	}

	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public DXJ_SbEntity findById(Long id) {
		return dxjSbDao.find(id);
	}
}
