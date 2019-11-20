package com.cczu.aqzf.model.service;

import com.cczu.aqzf.model.dao.AqzfSetBasicInfoDao;
import com.cczu.aqzf.model.dao.XzcfCommonQzzxDao;
import com.cczu.aqzf.model.dao.impl.XzcfCfjdDaoImpl;
import com.cczu.aqzf.model.entity.AQZF_SetBasicInfoEntity;
import com.cczu.aqzf.model.entity.XZCF_CfjdInfoEntity;
import com.cczu.aqzf.model.entity.XZCF_YbcfQzzxEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 行政处罚-一般处罚-强制执行实现类
 * 
 * @author jason
 * 
 */

@Service("XzcfCommonQzzxService")
public class XzcfCommonQzzxService {
	
	@Resource
	private XzcfCommonQzzxDao xzcfcommonqzzxdao;
	@Resource
	private AqzfSetBasicInfoDao setbasicdao;
	@Resource
	private XzcfCfjdDaoImpl punishsimplecfjddao;

	 
	public Long addInfoReturnID(XZCF_YbcfQzzxEntity yqe) {
		// TODO Auto-generated method stub
		return xzcfcommonqzzxdao.addInfoReturnID(yqe);
	}

	 
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> list = xzcfcommonqzzxdao.dataGrid(mapData);
		int count = xzcfcommonqzzxdao.getTotalCount(mapData);
		map.put("rows", list);
		map.put("total", count);
		return map;
	}
	 
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		xzcfcommonqzzxdao.deleteInfo(id);
	}

	 
	public XZCF_YbcfQzzxEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		return xzcfcommonqzzxdao.findInfoById(id);

	}

	 
	public void updateInfo( XZCF_YbcfQzzxEntity yqe) {
		// TODO Auto-generated method stub
		xzcfcommonqzzxdao.updateInfo(yqe);
	}

	 
	public XZCF_YbcfQzzxEntity findInfoByLaId(long laid) {
		// TODO Auto-generated method stub
		return xzcfcommonqzzxdao.findInfoByLaId(laid);
	}

	public void updateLaspInfo(long id) {
		// TODO Auto-generated method stub
	 xzcfcommonqzzxdao.updateLaspInfo(id);
		
	}

	/**
	 * 获取文书导出word
	 */
	public Map<String, Object> getWsdcword(Long id, String flag) {
		XZCF_YbcfQzzxEntity yqe;
		AQZF_SetBasicInfoEntity sbe=setbasicdao.findInfor();
		
		if("la".equals(flag)){
			yqe=xzcfcommonqzzxdao.findInfoByLaId(id);
		}
		else
			yqe= xzcfcommonqzzxdao.findInfoById(id);
		XZCF_CfjdInfoEntity cfjd=punishsimplecfjddao.findInfoByLaId(yqe.getId1());
	    Calendar cal = Calendar.getInstance();
		cal.setTime(cfjd.getPunishdate());
		Map<String, Object> map=new HashMap<String, Object>();
		 map.put("number", yqe.getNumber());
		 map.put("dsname", yqe.getDsname());
		 map.put("year",String.valueOf(cal.get(cal.YEAR)));
		 map.put("month",cal.get(cal.MONTH)+1);
		 map.put("day",cal.get(cal.DAY_OF_MONTH));
		 map.put("clname", yqe.getClname());
		 map.put("court", sbe.getCourt()==null?"":sbe.getCourt());
		 map.put("result", cfjd.getXzcf());
		 map.put("cfnumber", cfjd.getNumber());
		 map.put("contact", yqe.getPhone()==null?"":yqe.getPhone());
		 map.put("phone", yqe.getContactname()==null?"":yqe.getContactname());
		 map.put("ssqmc", sbe.getSsqmc()==null?"":sbe.getSsqmc());
		return map;
	}

}
