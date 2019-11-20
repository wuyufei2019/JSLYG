package com.cczu.aqzf.model.service.impl;

import com.cczu.aqzf.model.dao.AqzfSetBasicInfoDao;
import com.cczu.aqzf.model.dao.XzcfCommonDcDao;
import com.cczu.aqzf.model.dao.XzcfDCzjDao;
import com.cczu.aqzf.model.dao.impl.PunishSimpleGzDaoImpl;
import com.cczu.aqzf.model.dao.impl.XzcfCfjdDaoImpl;
import com.cczu.aqzf.model.entity.AQZF_SetBasicInfoEntity;
import com.cczu.aqzf.model.entity.XZCF_CfjdInfoEntity;
import com.cczu.aqzf.model.entity.XZCF_GzsInfoEntity;
import com.cczu.aqzf.model.entity.XZCF_YbcfDcbgEntity;
import com.cczu.aqzf.model.service.IXzcfCfjdService;
import com.cczu.sys.comm.utils.DateUtils;
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

@Service("PunishSimpleCfjdService")
public class XzcfCfjdServiceImpl implements IXzcfCfjdService {
	@Resource
	private XzcfCfjdDaoImpl punishsimplecfjddao;
	@Resource
	private AqzfSetBasicInfoDao setbasicdao;
	@Resource
	private PunishSimpleGzDaoImpl punishsimplegzdao;
	@Resource
	private XzcfCommonDcDao xzcfcommondcdao;
	@Resource
	private XzcfDCzjDao xzcfDCzjDao;

	@Override
	public Long addInfoReturnID(XZCF_CfjdInfoEntity jce) {
		// TODO Auto-generated method stub
		return punishsimplecfjddao.addInfoReturnID(jce);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> list = punishsimplecfjddao.dataGrid(mapData);
		int count = punishsimplecfjddao.getTotalCount(mapData);
		map.put("rows", list);
		map.put("total", count);
		return map;
	}

	@Override
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		punishsimplecfjddao.deleteInfo(id);
	}

	@Override
	public XZCF_CfjdInfoEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		return punishsimplecfjddao.findInfoById(id);

	}

	@Override
	public void updateInfo( XZCF_CfjdInfoEntity jce) {
		// TODO Auto-generated method stub
		punishsimplecfjddao.updateInfo(jce);
	}

	@Override
	public XZCF_CfjdInfoEntity findInfoByLaId(long id) {
		// TODO Auto-generated method stub
		 return punishsimplecfjddao.findInfoByLaId(id);
	}

	@Override
	public void updateLaspInfo(long id) {
		// TODO Auto-generated method stub
	 punishsimplecfjddao.updateLaspInfo(id);
	}

	@Override
	public Map<String, Object> getWsdcword(Long id, String flag) {
		XZCF_CfjdInfoEntity jce ;
		AQZF_SetBasicInfoEntity sbe=setbasicdao.findInfor();
		if("la".equals(flag)){
			jce=punishsimplecfjddao.findInfoByLaId(id);
		}else{
			jce= punishsimplecfjddao.findInfoById(id);
		}
		XZCF_YbcfDcbgEntity dcbg = xzcfcommondcdao.findInfoByLaId(jce.getId1());
		//证据
	    List<Map<String, Object>> evidencelist= xzcfDCzjDao.dataGridCzwt(dcbg.getID());
		
		XZCF_GzsInfoEntity cfgz=punishsimplegzdao.findInfoByLaId(jce.getId1());
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("Evidencelist", evidencelist);
		//企业个人公有
		map.put("bankname", sbe.getBankname()==null?"":sbe.getBankname());
		map.put("account", sbe.getAccount()==null?"":sbe.getAccount());
		map.put("gov", sbe.getGov()==null?"":sbe.getGov());
		map.put("highgov", sbe.getHighgov()==null?"":sbe.getHighgov());
		map.put("court", sbe.getCourt()==null?"":sbe.getCourt());
		map.put("number",jce.getNumber());
		map.put("punishname", jce.getPunishname());
		map.put("address", jce.getAddress());
		map.put("mobile", jce.getMobile());
		map.put("duty", jce.getDuty());
		String reg=".*[\\.。]$";
		map.put("illegalactandevidence", jce.getIllegalactandevidence()==null?"":(jce.getIllegalactandevidence().matches(reg)?jce.getIllegalactandevidence().substring(0, jce.getIllegalactandevidence().length()-1):jce.getIllegalactandevidence())+"，");
		map.put("unlaw", jce.getUnlaw()==null?"":jce.getUnlaw());
		map.put("enlaw",jce.getEnlaw()==null?"":jce.getEnlaw());
		map.put("punishresult", jce.getPunishresult()==null?"":jce.getPunishresult());
		map.put("ybcode", jce.getYbcode()==null?"":jce.getYbcode());
		map.put("ssqmc", sbe.getSsqmc()==null?"":sbe.getSsqmc());
		
		map.put("wfxw", jce.getWfxw()==null?"":(jce.getWfxw().matches(reg)?jce.getWfxw().substring(0, jce.getWfxw().length()-1):jce.getWfxw()));
		//map.put("evidence", jce.getEvidence()==null?"":jce.getEvidence());
		map.put("xzcf", jce.getXzcf()==null?"":jce.getXzcf());
		map.put("cfgzsj", cfgz.getPunishdate()==null?"": DateUtils.formatDate(cfgz.getPunishdate(), "yyyy年MM月dd日"));
		map.put("cfgzsbh", cfgz.getNumber());
		if("1".equals(jce.getPercomflag())){
			map.put("legal", jce.getLegal());
		}else{
			map.put("sex",jce.getSex()==null?"":jce.getSex());
			map.put("age", jce.getAge()==null?"":jce.getAge());
			map.put("identity1", jce.getIdentity1()==null?"":jce.getIdentity1());
			map.put("dwname", jce.getDwname()==null?"":jce.getDwname());
			map.put("dwaddress", jce.getDwaddress()==null?"":jce.getDwaddress());
		}
		map.put("cfjdsj",jce.getPunishdate()==null?"年     月     日": DateUtils.formatDate(jce.getPunishdate(), "yyyy 年 MM 月 dd 日"));
		return map;
	}

}
