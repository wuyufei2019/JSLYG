package com.cczu.aqzf.model.service;

import com.cczu.aqzf.model.dao.XzcfCommonJaspDao;
import com.cczu.aqzf.model.entity.XZCF_YbcfJaspEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 行政处罚-一般处罚-结案审批
 * 
 * @author jason
 * 
 */

@Service("XzcfCommonJaspService")
public class XzcfCommonJaspService  {
	@Resource
	private XzcfCommonJaspDao xzcfcommonjaspdao;

	
	public Long addInfoReturnID(XZCF_YbcfJaspEntity yje) {
		// TODO Auto-generated method stub
		return xzcfcommonjaspdao.addInfoReturnID(yje);
	}

	
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> list = xzcfcommonjaspdao.dataGrid(mapData);
		int count = xzcfcommonjaspdao.getTotalCount(mapData);
		map.put("rows", list);
		map.put("total", count);
		return map;
	}

	
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		xzcfcommonjaspdao.deleteInfo(id);
	}

	
	public XZCF_YbcfJaspEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		return xzcfcommonjaspdao.findInfoById(id);

	}

	
	public void updateInfo( XZCF_YbcfJaspEntity yje) {
		// TODO Auto-generated method stub
		xzcfcommonjaspdao.updateInfo(yje);
	}

	
	public XZCF_YbcfJaspEntity findInfoByLaId(long id) {
		// TODO Auto-generated method stub
		 return xzcfcommonjaspdao.findInfoByLaId(id);
	}

	
	public void updateLaspInfo(long id) {
		// TODO Auto-generated method stub
	 xzcfcommonjaspdao.updateLaspInfo(id);
	}

	/**
	 * 获取文书导出word
	 */
	public Map<String, Object> getWsdcword(Long id, String flag) {
		XZCF_YbcfJaspEntity yje;
		if ("la".equals(flag))
			yje = xzcfcommonjaspdao.findInfoByLaId(id);
		else
			yje = xzcfcommonjaspdao.findInfoById(id);
		// XZCF_JYCFInfoEntity
		// jie=punishsimplegzservice.findInfoById(yje.getId1());
		Map<String, Object> map = new HashMap<String, Object>();
		//企业个人公有
		if("1".equals(yje.getPercomflag())){
			map.put("punishname", "");
			map.put("punishdwname", yje.getPunishname());
		}else{
			map.put("punishname",yje.getPunishname());
			map.put("punishdwname","");
			}
		map.put("number", yje.getNumber());
		map.put("casename", yje.getCasename());
		map.put("qyaddress", yje.getQyaddress()==null?"":yje.getQyaddress());
		map.put("qyyb", yje.getQyyb()==null?"":yje.getQyyb());
		map.put("jtyb", yje.getJtyb()==null?"":yje.getJtyb());
		map.put("legal", yje.getLegal()==null?"":yje.getLegal());
		map.put("sex", yje.getSex()==null?"":yje.getSex());
		map.put("age", yje.getAge() == null ? "" : yje.getAge());
		map.put("dwname", yje.getDwname()==null?"":yje.getDwname());
		map.put("dwaddress", yje.getDwaddress()==null?"":yje.getDwaddress());
		map.put("duty", yje.getDuty() == null ? "" : yje.getDuty());
		map.put("addresss", yje.getAddress() == null ? "" : yje.getAddress());
		map.put("result", yje.getResult()==null?"":yje.getResult());
		map.put("mobile", yje.getMobile() == null ? "" : yje.getMobile());
		map.put("exeucondition", yje.getExeucondition());
		return map;
	}
}
