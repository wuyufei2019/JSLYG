package com.cczu.aqzf.model.service.impl;

import com.cczu.aqzf.model.dao.AqzfSetBasicInfoDao;
import com.cczu.aqzf.model.dao.XzcfCommonDcDao;
import com.cczu.aqzf.model.dao.XzcfDCzjDao;
import com.cczu.aqzf.model.dao.impl.PunishSimpleGzDaoImpl;
import com.cczu.aqzf.model.dao.impl.XzcfCommonLaspDaoImpl;
import com.cczu.aqzf.model.entity.AQZF_SetBasicInfoEntity;
import com.cczu.aqzf.model.entity.XZCF_GzsInfoEntity;
import com.cczu.aqzf.model.entity.XZCF_YbcfDcbgEntity;
import com.cczu.aqzf.model.entity.XZCF_YbcfLaspEntity;
import com.cczu.aqzf.model.service.IPunishSimpleGzService;
import com.cczu.sys.comm.utils.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 行政处罚-简单处罚-告知接口实现类
 * 
 * @author jason
 * 
 */

@Service("PunishSimpleGzService")
public class PunishSimpleGzServiceImpl implements IPunishSimpleGzService {
	@Resource
	private PunishSimpleGzDaoImpl punishsimplegzdao;
	@Resource
	private AqzfSetBasicInfoDao setbasicdao;
	@Resource
	private XzcfCommonDcDao xzcfcommondcdao;
	@Resource
	private XzcfDCzjDao xzcfDCzjDao;
	@Resource
	private XzcfCommonLaspDaoImpl punishcommonlaspdao;

	@Override
	public Long addInfoReturnID(XZCF_GzsInfoEntity jie) {
		// TODO Auto-generated method stub
		return punishsimplegzdao.addInfoReturnID(jie);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> list = punishsimplegzdao.dataGrid(mapData);
		int count = punishsimplegzdao.getTotalCount(mapData);
		map.put("rows", list);
		map.put("total", count);
		return map;
	}
//	@Override
//	public Map<String, Object> dataGrid2(Map<String, Object> mapData) {
//		// TODO Auto-generated method stub
//		Map<String, Object> map = new HashMap<String, Object>();
//		List<Map<String,Object>> list = punishsimplegzdao.dataGrid2(mapData);
//		int count = punishsimplegzdao.getTotalCount2(mapData);
//		map.put("rows", list);
//		map.put("total", count);
//		return map;
//	}

	@Override
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		punishsimplegzdao.deleteInfo(id);
	}

	@Override
	public XZCF_GzsInfoEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		return punishsimplegzdao.findInfoById(id);

	}

	@Override
	public void updateInfo( XZCF_GzsInfoEntity jie) {
		// TODO Auto-generated method stub
		punishsimplegzdao.updateInfo(jie);
	}

	@Override
	public XZCF_GzsInfoEntity findInfoByLaId(long laid) {
		// TODO Auto-generated method stub
		return punishsimplegzdao.findInfoByLaId(laid);
	}

	@Override
	public void updateLaspInfo(long id) {
		// TODO Auto-generated method stub
	 punishsimplegzdao.updateLaspInfo(id);
		
	}

	/**
	 * 获取文书导出word
	 */
	@Override
	public Map<String, Object> getWsdcword(Long id, String flag) {
		XZCF_GzsInfoEntity jie;
		AQZF_SetBasicInfoEntity sbe=setbasicdao.findInfor();
		if("gz".equals(flag)){
			jie= punishsimplegzdao.findInfoById(id);
		}else{
			jie=punishsimplegzdao.findInfoByLaId(id);
		}
		XZCF_YbcfDcbgEntity dcbg = xzcfcommondcdao.findInfoByLaId(jie.getId1());
		//证据
	    List<Map<String, Object>> evidencelist= xzcfDCzjDao.dataGridCzwt(dcbg.getID());
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("Evidencelist", evidencelist);
		
		map.put("name", jie.getName()==null?"":jie.getName());
		
		String reg=".*[\\.。]$";
		map.put("illegalact", jie.getIllegalact()==null?"":(jie.getIllegalact().matches(reg)?jie.getIllegalact().substring(0, jie.getIllegalact().length()-1):jie.getIllegalact())+"，");
		
		map.put("wfxw", jie.getWfxw()==null?"":jie.getWfxw());
		map.put("xq", jie.getPunishresult()==null?"":jie.getPunishresult());
		map.put("unlaw", jie.getUnlaw()==null?"":jie.getUnlaw());
		map.put("enlaw",jie.getEnlaw()==null?"":jie.getEnlaw());
		map.put("xzcf", jie.getXzcf()==null?"":jie.getXzcf());
		map.put("address", sbe.getAddress()==null?"":sbe.getAddress());
		map.put("ybcode", sbe.getYbcode()==null?"":sbe.getYbcode());
		map.put("contactname", jie.getContactname()==null?"":jie.getContactname());
		map.put("phone", jie.getPhone()==null?"":jie.getPhone());
	    map.put("number", jie.getNumber());
	    map.put("ssqmc", sbe.getSsqmc()==null?"":sbe.getSsqmc());
	    map.put("cfgzsj",jie.getPunishdate()==null?"年     月     日": DateUtils.formatDate(jie.getPunishdate(), "yyyy 年 MM 月 dd 日"));
	    
	    XZCF_YbcfLaspEntity ybcf = punishcommonlaspdao.findInfoById(jie.getId1());
	    String cfdx = "";
		if(ybcf.getCfdxlx().equals("2")){
			cfdx = "你";
		}else{
			cfdx = "你公司";
		}
		map.put("cfdx", cfdx);
	    
		return map;
	}
}
