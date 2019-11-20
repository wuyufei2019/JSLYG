package com.cczu.aqzf.model.service;

import com.cczu.aqzf.model.dao.AqzfSetBasicInfoDao;
import com.cczu.aqzf.model.dao.XzcfCssbblDao;
import com.cczu.aqzf.model.entity.AQZF_SetBasicInfoEntity;
import com.cczu.aqzf.model.entity.XZCF_CssbblEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("XzcfCssbblService")
public class XzcfCssbblService {

	@Resource
	private XzcfCssbblDao xzcfCssbblDao;
	@Resource
	private AqzfSetBasicInfoDao setbasicdao;
	
	/**
	 * 查询询问通知list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=xzcfCssbblDao.dataGrid(mapData);
		int getTotalCount=xzcfCssbblDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteInfo(long id) {
		xzcfCssbblDao.deleteInfo(id);
	}
	
	/**
	 * 删除后 立案审批的flag置0
	 * @param id
	 */
	public void updateLaspInfo(long id) {
		xzcfCssbblDao.updateLaspInfo(id);
	}

	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public XZCF_CssbblEntity findById(Long id) {
		return xzcfCssbblDao.findInfoById(id);
	}
	
	/**
	 * 添加
	 */
	public void addInfo(XZCF_CssbblEntity cssb) {
		cssb.setS1(new Timestamp(System.currentTimeMillis()));
		cssb.setS2(new Timestamp(System.currentTimeMillis()));
		cssb.setS3(0);
		xzcfCssbblDao.save(cssb);
	}
	
	/**
	 * 修改
	 * @param jttl
	 */
	public void updateInfo(XZCF_CssbblEntity cssb) {
		cssb.setS2(new Timestamp(System.currentTimeMillis()));
		cssb.setS3(0);
		xzcfCssbblDao.save(cssb);
	}
	
	/**
	 * 根据立案id查找符合word的数据
	 * @param id
	 * @return
	 */
	public XZCF_CssbblEntity findWordByLaId(Long laid) {
		return xzcfCssbblDao.findWordByLaId(laid);
	}

	/**
	 * 获取文书导出word
	 */
	public Map<String, Object> getWsdcword(Long id, String flag) {
		XZCF_CssbblEntity jttl = new XZCF_CssbblEntity();
		if("la".equals(flag)){
			jttl= xzcfCssbblDao.findWordByLaId(id);
		}else{
			jttl= xzcfCssbblDao.findInfoById(id);
		}
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("m3", StringUtils.isEmpty(jttl.getM3())?"":jttl.getM3());
		map.put("m4", StringUtils.isEmpty(jttl.getM4())?"         ":jttl.getM4());
		map.put("m5", StringUtils.isEmpty(jttl.getM5())?"  ":jttl.getM5());
		map.put("m6", StringUtils.isEmpty(jttl.getM6())?"      ":jttl.getM6());
		map.put("m7", StringUtils.isEmpty(jttl.getM7())?"                             ":jttl.getM7());
		map.put("m8", StringUtils.isEmpty(jttl.getM8())?"":jttl.getM8());
		map.put("m9", StringUtils.isEmpty(jttl.getM9())?"                             ":jttl.getM9());
		map.put("m10", StringUtils.isEmpty(jttl.getM10())?"":jttl.getM10());
		map.put("m11", StringUtils.isEmpty(jttl.getM11())?"                   ":jttl.getM11());
		map.put("m12", StringUtils.isEmpty(jttl.getM12())?"         ":jttl.getM12());
		map.put("m13", StringUtils.isEmpty(jttl.getM13())?"         ":jttl.getM13());
		map.put("m14", StringUtils.isEmpty(jttl.getM14())?"         ":jttl.getM14());
		map.put("m15", StringUtils.isEmpty(jttl.getM15())?"         ":jttl.getM15());
		map.put("m16", StringUtils.isEmpty(jttl.getM16())?"":jttl.getM16());
		map.put("m17", StringUtils.isEmpty(jttl.getM17())?"         ":jttl.getM17());
		//解析时间
		if(jttl.getM1()!=null){
			map.put("year", (jttl.getM1().getYear()+1900)+"");
			map.put("month1", jttl.getM1().getMonth() + 1);
			map.put("day1", jttl.getM1().getDate());
			map.put("hour1", jttl.getM1().getHours());
			map.put("min1", jttl.getM1().getMinutes());
		}else{
			map.put("year", "   ");
			map.put("month1", "  ");
			map.put("day1", "  ");
			map.put("hour1", "  ");
			map.put("min1", "  ");
		}
		if(jttl.getM2()!=null){
			map.put("month2", jttl.getM2().getMonth() + 1);
			map.put("day2", jttl.getM2().getDate());
			map.put("hour2", jttl.getM2().getHours());
			map.put("min2", jttl.getM2().getMinutes());
		}else{
			map.put("month2", "  ");
			map.put("day2", "  ");
			map.put("hour2", "  ");
			map.put("min2", "  ");
		}

		 AQZF_SetBasicInfoEntity bh =setbasicdao.findInfor();
		 map.put("ssqmc", bh.getSsqmc()==null?"":bh.getSsqmc());
		return map;
	}
}
