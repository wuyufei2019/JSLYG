package com.cczu.aqzf.model.service.impl;

import com.cczu.aqzf.model.dao.AqzfSetBasicInfoDao;
import com.cczu.aqzf.model.dao.XzcfCommonDcDao;
import com.cczu.aqzf.model.dao.XzcfDCzjDao;
import com.cczu.aqzf.model.dao.impl.XzcfCommonLaspDaoImpl;
import com.cczu.aqzf.model.dao.impl.XzcfCommonTzDaoImpl;
import com.cczu.aqzf.model.entity.AQZF_SetBasicInfoEntity;
import com.cczu.aqzf.model.entity.XZCF_YbcfDcbgEntity;
import com.cczu.aqzf.model.entity.XZCF_YbcfLaspEntity;
import com.cczu.aqzf.model.entity.XZCF_YbcfTzgzEntity;
import com.cczu.aqzf.model.service.IXzcfCommonTzService;
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

@Service("XzcfCommonTzService")
public class XzcfCommonTzServiceImpl implements IXzcfCommonTzService {
	
	@Resource
	private XzcfCommonTzDaoImpl xzcfcommontzdao;
	@Resource
	private AqzfSetBasicInfoDao setbasicdao;
	@Resource
	private XzcfCommonDcDao xzcfcommondcdao;
	@Resource
	private XzcfDCzjDao xzcfDCzjDao;
	@Resource
	private XzcfCommonLaspDaoImpl punishcommonlaspdao;

	@Override
	public Long addInfoReturnID(XZCF_YbcfTzgzEntity yte) {
		// TODO Auto-generated method stub
		return xzcfcommontzdao.addInfoReturnID(yte);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> list = xzcfcommontzdao.dataGrid(mapData);
		int count = xzcfcommontzdao.getTotalCount(mapData);
		map.put("rows", list);
		map.put("total", count);
		return map;
	}

	@Override
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		xzcfcommontzdao.deleteInfo(id);
	}

	@Override
	public XZCF_YbcfTzgzEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		return xzcfcommontzdao.findInfoById(id);

	}

	@Override
	public void updateInfo( XZCF_YbcfTzgzEntity yte) {
		// TODO Auto-generated method stub
		xzcfcommontzdao.updateInfo(yte);
	}

	@Override
	public XZCF_YbcfTzgzEntity findInfoByLaId(long laid) {
		// TODO Auto-generated method stub
		return xzcfcommontzdao.findInfoByLaId(laid);
	}

	@Override
	public void updateLaspInfo(long id) {
		// TODO Auto-generated method stub
		
		xzcfcommontzdao.updateLaspInfo(id);
	}

	@Override
	public Map<String, Object> getWsdcword(Long id, String flag) {
		AQZF_SetBasicInfoEntity sbe=setbasicdao.findInfor();
		XZCF_YbcfTzgzEntity yte ;
		if("la".equals(flag))
			yte=xzcfcommontzdao.findInfoByLaId(id);
		else
			yte= xzcfcommontzdao.findInfoById(id);
		
		XZCF_YbcfDcbgEntity dcbg = xzcfcommondcdao.findInfoByLaId(yte.getId1());
		//证据
	    List<Map<String, Object>> evidencelist= xzcfDCzjDao.dataGridCzwt(dcbg.getID());
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("Evidencelist", evidencelist);

		map.put("name", yte.getName()==null?"":yte.getName());
		
		//验证结尾是否是，
		String reg=".*[\\.。]$";
		map.put("illegalact", yte.getIllegalact()==null?"":(yte.getIllegalact().matches(reg)?yte.getIllegalact().substring(0, yte.getIllegalact().length()-1):yte.getIllegalact())+"，");
		
		map.put("wfxw", yte.getWfxw()==null?"":yte.getWfxw());
		map.put("xq", yte.getPunishresult()==null?"":yte.getPunishresult());
		map.put("unlaw", yte.getUnlaw()==null?"":yte.getUnlaw());
		map.put("enlaw",yte.getEnlaw()==null?"":yte.getEnlaw());
		map.put("xzcf", yte.getXzcf()==null?"":yte.getXzcf());
		map.put("address", sbe.getAddress()==null?"":sbe.getAddress());
		map.put("ybcode", sbe.getYbcode()==null?"":sbe.getYbcode());
		map.put("contactname", yte.getContactname()==null?"":yte.getContactname());
		map.put("phone", yte.getPhone()==null?"":yte.getPhone());
	    map.put("number", yte.getNumber());
	    map.put("ssqmc", sbe.getSsqmc()==null?"":sbe.getSsqmc());
	    map.put("tzgzsj",yte.getPunishdate()==null?"年     月     日": DateUtils.formatDate(yte.getPunishdate(), "yyyy 年 MM 月 dd 日"));
	    
	    XZCF_YbcfLaspEntity ybcf = punishcommonlaspdao.findInfoById(yte.getId1());
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
