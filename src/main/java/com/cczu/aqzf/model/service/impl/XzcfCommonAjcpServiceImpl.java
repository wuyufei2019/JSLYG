package com.cczu.aqzf.model.service.impl;

import com.cczu.aqzf.model.dao.impl.XzcfCommonAjcpDaoImpl;
import com.cczu.aqzf.model.entity.XZCF_YbcfAjcpEntity;
import com.cczu.aqzf.model.service.IXzcfCommonAjcpService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 行政处罚-简单处罚-处罚决定接口实现类
 * 
 * @author jason
 * 
 */

@Service("XzcfCommonAjcpService")
public class XzcfCommonAjcpServiceImpl implements IXzcfCommonAjcpService {
	@Resource
	private XzcfCommonAjcpDaoImpl xzcfcommonajcpdao;

	@Override
	public Long addInfoReturnID(XZCF_YbcfAjcpEntity yae) {
		// TODO Auto-generated method stub
		return xzcfcommonajcpdao.addInfoReturnID(yae);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> list = xzcfcommonajcpdao.dataGrid(mapData);
		int count = xzcfcommonajcpdao.getTotalCount(mapData);
		map.put("rows", list);
		map.put("total", count);
		return map;
	}

	@Override
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		xzcfcommonajcpdao.deleteInfo(id);
	}

	@Override
	public XZCF_YbcfAjcpEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		return xzcfcommonajcpdao.findInfoById(id);

	}

	@Override
	public void updateInfo( XZCF_YbcfAjcpEntity yae) {
		// TODO Auto-generated method stub
		xzcfcommonajcpdao.updateInfo(yae);
	}

	@Override
	public XZCF_YbcfAjcpEntity findInfoByLaId(long id) {
		// TODO Auto-generated method stub
		 return xzcfcommonajcpdao.findInfoByLaId(id);
	}

	@Override
	public void updateLaspInfo(long id) {
		// TODO Auto-generated method stub
	 xzcfcommonajcpdao.updateLaspInfo(id);
	}

	@Override
	public Map<String, Object> getWsdcword(Long id, String flag) {
		XZCF_YbcfAjcpEntity jce ;
		if("la".equals(flag))
		jce=xzcfcommonajcpdao.findInfoByLaId(id);
		else
		jce= xzcfcommonajcpdao.findInfoById(id);
		//XZCF_JYCFInfoEntity jie=punishsimplegzservice.findInfoById(jce.getId1());
		Map<String, Object> map=new HashMap<String, Object>();
		//企业个人公有
		if("1".equals(jce.getPercomflag())){
			map.put("punishname", "");
			map.put("punishdwname", jce.getPunishname());
		}else{
			map.put("punishname",jce.getPunishname());
			map.put("punishdwname","");
		}
		map.put("number",jce.getNumber());
		map.put("casename",jce.getCasename());
		map.put("qyaddress", jce.getQyaddress()==null?"":jce.getQyaddress());
		map.put("qyyb", jce.getQyyb()==null?"":jce.getQyyb());
		map.put("jtyb", jce.getJtyb()==null?"":jce.getJtyb());
		map.put("legal", jce.getLegal()==null?"":jce.getLegal());
		map.put("sex",jce.getSex()==null?"":jce.getSex());
		map.put("age", jce.getAge()==null?"":jce.getAge());
		map.put("dwname", jce.getDwname()==null?"":jce.getDwname());
		map.put("dwaddress", jce.getDwaddress()==null?"":jce.getDwaddress());
		map.put("address", jce.getAddress()==null?"":jce.getAddress());
		map.put("casecondition", jce.getIllegalactandevidence()==null?"":jce.getIllegalactandevidence());
		map.put("mobile", jce.getMobile()==null?"":jce.getMobile());
		map.put("sbrecord", jce.getSbrecord()==null?"":jce.getSbrecord());
		map.put("opinion", jce.getOpinion()==null?"":jce.getOpinion());
		map.put("duty", jce.getDuty()==null?"":jce.getDuty());
		map.put("illegalactandevidence", jce.getIllegalactandevidence()==null?"":jce.getIllegalactandevidence());
		return map;
	}

}
