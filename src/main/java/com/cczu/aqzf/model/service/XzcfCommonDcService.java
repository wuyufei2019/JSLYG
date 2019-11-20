package com.cczu.aqzf.model.service;

import com.cczu.aqzf.model.dao.XzcfCommonDcDao;
import com.cczu.aqzf.model.dao.XzcfDCzjDao;
import com.cczu.aqzf.model.dao.XzcfDCzyclDao;
import com.cczu.aqzf.model.dao.impl.XzcfCommonLaspDaoImpl;
import com.cczu.aqzf.model.entity.XZCF_YbcfDcbgEntity;
import com.cczu.aqzf.model.entity.XZCF_YbcfLaspEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 行政处罚-一般处罚-调查实现类
 * 
 * @author jason
 * 
 */

@Service("XzcfCommonDcService")
public class XzcfCommonDcService {
	@Resource
	private XzcfCommonDcDao xzcfcommondcdao;
	@Resource
	private XzcfDCzjDao xzcfDCzjDao;
	@Resource
	private XzcfCommonLaspDaoImpl punishcommonlaspdao;
	@Resource
	private XzcfDCzyclDao xzcfDCzyclDao;

	 
	public Long addInfoReturnID(XZCF_YbcfDcbgEntity jie) {
		// TODO Auto-generated method stub
		return xzcfcommondcdao.addInfoReturnID(jie);
	}

	 
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> list = xzcfcommondcdao.dataGrid(mapData);
		int count = xzcfcommondcdao.getTotalCount(mapData);
		map.put("rows", list);
		map.put("total", count);
		return map;
	}
//	 
//	public Map<String, Object> dataGrid2(Map<String, Object> mapData) {
//		// TODO Auto-generated method stub
//		Map<String, Object> map = new HashMap<String, Object>();
//		List<Map<String,Object>> list = xzcfcommondcdao.dataGrid2(mapData);
//		int count = xzcfcommondcdao.getTotalCount2(mapData);
//		map.put("rows", list);
//		map.put("total", count);
//		return map;
//	}

	 
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		xzcfcommondcdao.deleteInfo(id);
	}

	 
	public XZCF_YbcfDcbgEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		return xzcfcommondcdao.findInfoById(id);

	}

	 
	public void updateInfo( XZCF_YbcfDcbgEntity jie) {
		// TODO Auto-generated method stub
		xzcfcommondcdao.updateInfo(jie);
	}

	 
	public XZCF_YbcfDcbgEntity findInfoByLaId(long laid) {
		// TODO Auto-generated method stub
		return xzcfcommondcdao.findInfoByLaId(laid);
	}

	public void updateLaspInfo(long id) {
		// TODO Auto-generated method stub
	 xzcfcommondcdao.updateLaspInfo(id);
		
	}

	/**
	 * 获取文书导出word
	 */
	public Map<String, Object> getWsdcword(Long id,String flag) {
		XZCF_YbcfDcbgEntity ybe;
		//XZCF_YbcfLaspEntity yle;
		if("la".equals(flag)){
			//yle= xzcfcommonlaspservice.findInfoById(id);
			ybe=xzcfcommondcdao.findInfoByLaId(id);
		}
		else{
			ybe= xzcfcommondcdao.findInfoById(id);
			//yle=xzcfcommonlaspservice.findInfoById(ybe.getId1());
		}
		
		XZCF_YbcfLaspEntity lasp = punishcommonlaspdao.findInfoById(ybe.getId1());
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("bgbh", ybe.getBgbh());
		if(lasp.getCfdxlx().equals("2")) {
			map.put("qyname",ybe.getCfryname());
		}else {
			map.put("qyname", ybe.getQyname());
		}
		map.put("anyname", ybe.getAyname()==null?"":ybe.getAyname());
		map.put("cbjg", ybe.getCbjg()==null?"":ybe.getCbjg());
		map.put("researchresult", ybe.getResearchresult()==null?"":ybe.getResearchresult());
		map.put("unlaw", ybe.getUnlaw()==null?"":ybe.getUnlaw());
		map.put("enlaw", ybe.getEnlaw()==null?"":ybe.getEnlaw());
		map.put("xzcf", ybe.getXzcf()==null?"":ybe.getXzcf());
		map.put("cbr", ybe.getEnforcer1()+(StringUtils.isEmpty(ybe.getEnforcer2())?"":("   "+ybe.getEnforcer2())));
	    
	    //证据
	    List<Map<String, Object>> czwt= xzcfDCzjDao.dataGridCzwt(ybe.getID());
	    String evidence = "";
	    if(czwt.size()>0){
	    	int i = 1;
	    	for (Map<String, Object> map2 : czwt) {
	    		evidence += "    证据"+i+"："+map2.get("m1").toString()+"<w:p />";
	    		i++;
			}
	    }
	    map.put("getevidence", evidence);
	    
	    if(lasp.getZycllx().equals("2")){
	    	//新自由裁量
			List<Map<String, Object>> zyclList = new ArrayList<>();
			if(lasp.getZycllx().equals("2")) {
				zyclList = xzcfDCzyclDao.findInfoByDcidTwo(ybe.getID());
			}else {
				zyclList = xzcfDCzyclDao.findInfoByDcid(ybe.getID());
			}
	    	String zycl = "";
	    	if(zyclList.size()>0){
	    		if(zyclList.size()==1){
	    			zycl += "1、处罚档次：<w:p />";
	    			zycl += "   定为"+zyclList.get(0).get("m1").toString()+"。<w:p />";
	    			zycl += "2、削减裁量因素为：<w:p />";
	    			zycl += "   "+clys(zyclList.get(0).get("m2").toString())+"。<w:p />";
	    			zycl += "3、具体裁量计算：<w:p />";
	    			zycl += "   S="+zyclList.get(0).get("m2").toString()+"。<w:p />";
	    		}else{
	    			int i = 1;
	    			String s = "";
	    			String gs = "";
	    			float jg = 0;
	    			for (Map<String, Object> map2 : zyclList) {
	    				zycl += "   "+i+"、"+map2.get("wfxwname").toString()+"<w:p />";
	    				zycl += "   1）处罚档次：<w:p />";
	    				zycl += "   定为"+map2.get("m1").toString()+"。<w:p />";
	    				zycl += "   2）削减裁量因素为：<w:p />";
	    				zycl += "   "+clys(map2.get("m2").toString())+"。<w:p />";
	    				zycl += "   3）具体裁量计算：<w:p />";
	    				zycl += "   S"+i+"="+map2.get("m2").toString()+"。<w:p />";
	    				if(i==1){
	    					s = "S=S"+i;
	    					gs = "="+map2.get("m3").toString();
	    				}else{
	    					s += "+S"+i;
	    					gs += "+"+map2.get("m3").toString();
	    				}
	    				jg += Float.parseFloat(map2.get("m3").toString());
	    				i++;
					}
	    			zycl += "   "+i+"、合并处罚："+s+gs+"="+(int)jg+"元。<w:p />";
	    		}
	    	}
	    	map.put("zycl", zycl);
	    }
	    
		return map;
	}

	/**
	 * 根据裁量公式得出削减裁量因素
	 * @param jtcl
	 * @return
	 */
	public String clys(String jtcl){
		String result = "";
		String []sqcf = jtcl.split("a");
		int i = 1;
		for (String s1 : sqcf) {
			if(i!=1){
				int a = Integer.valueOf(s1.substring(0, 1));
				if(a==1){
					result += "对违法行为认识到位（a1）；";
				}else if(a==2){
					result += "立即采取措施纠正（a2）；";
				}else if(a==3){
					result += "通过安全生产标准化考评（a3）；";
				}else if(a==4){
					result += "积极组织或支持安全生产公益活动（a4）；";
				}else if(a==5){
					result += "企业安全风险辨识管控机制建设是否到位（a5）；";
				}else if(a==6){
					result += "特殊岗位机械化换人、自动化减人是否到位（a6）；";
				}
			}
			i++;
		}
		
		String []sgcf = jtcl.split("b");
		int j = 1;
		for (String s2 : sgcf) {
			if(j!=1){
				int b = Integer.valueOf(s2.substring(0, 1));
				if(b==1){
					result += "对事故调查积极配合（b1）；";
				}else if(b==2){
					result += "对事故善后处理妥善积极（b2）；";
				}else if(b==3){
					result += "对事故隐患按照‘四不放过’原则积极采取措施整治到位（b3）；";
				}else if(b==4){
					result += "积极组织或支持安全生产公益活动（b4）；";
				}else if(b==5){
					result += "企业安全风险辨识管控机制建设是否到位（b5）；";
				}else if(b==6){
					result += "特殊岗位机械化换人、自动化减人是否到位（b6）；";
				}
			}
			j++;
		}
		
		return result.substring(0,result.length()-1);
	}
}
