package com.cczu.aqzf.model.service;

import com.cczu.aqzf.model.dao.XzcfJttlDao;
import com.cczu.aqzf.model.entity.XZCF_JttlEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("XzcfJttlService")
public class XzcfJttlService {

	@Resource
	private XzcfJttlDao xzcfJttlDao;
	
	/**
	 * 查询询问通知list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=xzcfJttlDao.dataGrid(mapData);
		int getTotalCount=xzcfJttlDao.getTotalCount(mapData);
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
		xzcfJttlDao.deleteInfo(id);
	}
	
	/**
	 * 删除后 立案审批的flag置0
	 * @param id
	 */
	public void updateLaspInfo(long id) {
		xzcfJttlDao.updateLaspInfo(id);
	}

	/**
	 * 根据id查找集体讨论
	 * @param id
	 * @return
	 */
	public XZCF_JttlEntity findById(Long id) {
		return xzcfJttlDao.findInfoById(id);
	}
	
	/**
	 * 添加集体讨论
	 * @param jttl
	 */
	public void addInfo(XZCF_JttlEntity jttl) {
		jttl.setS1(new Timestamp(System.currentTimeMillis()));
		jttl.setS2(new Timestamp(System.currentTimeMillis()));
		jttl.setS3(0);
		xzcfJttlDao.save(jttl);
	}
	
	/**
	 * 修改
	 * @param jttl
	 */
	public void updateInfo(XZCF_JttlEntity jttl) {
		jttl.setS2(new Timestamp(System.currentTimeMillis()));
		jttl.setS3(0);
		xzcfJttlDao.save(jttl);
	}
	
	/**
	 * 根据立案id查找符合word的数据
	 * @param id
	 * @return
	 */
	public XZCF_JttlEntity findWordByLaId(Long laid) {
		return xzcfJttlDao.findWordByLaId(laid);
	}

	/**
	 * 获取文书导出word
	 */
	public Map<String, Object> getWsdcword(Long id, String flag) {
		XZCF_JttlEntity jttl = new XZCF_JttlEntity();
		if("la".equals(flag)){
			jttl= xzcfJttlDao.findWordByLaId(id);
		}else{
			jttl= xzcfJttlDao.findInfoById(id);
		}
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("m1", StringUtils.isEmpty(jttl.getM1())?"":jttl.getM1());
		map.put("m2", StringUtils.isEmpty(jttl.getM2())?"":jttl.getM2());
		map.put("m5", StringUtils.isEmpty(jttl.getM5())?"":jttl.getM5());
		map.put("m6", StringUtils.isEmpty(jttl.getM6())?"           ":jttl.getM6());
		map.put("m7", StringUtils.isEmpty(jttl.getM7())?"           ":jttl.getM7());
		map.put("m8", StringUtils.isEmpty(jttl.getM8())?"           ":jttl.getM8());
		map.put("m9", StringUtils.isEmpty(jttl.getM9())?"":jttl.getM9());
		map.put("m10", StringUtils.isEmpty(jttl.getM10())?"":jttl.getM10());
		map.put("m11", StringUtils.isEmpty(jttl.getM11())?"":jttl.getM11());
		map.put("m12", StringUtils.isEmpty(jttl.getM12())?"":jttl.getM12());
		//解析时间
		if(jttl.getM3()!=null){
			map.put("year1", (jttl.getM3().getYear()+1900)+"");
			map.put("month1", jttl.getM3().getMonth() + 1);
			map.put("day1", jttl.getM3().getDate());
			map.put("hour1", jttl.getM3().getHours());
			map.put("min1", jttl.getM3().getMinutes());
		}else{
			map.put("year1", "   ");
			map.put("month1", "  ");
			map.put("day1", "  ");
			map.put("hour1", "  ");
			map.put("min1", "  ");
		}
		if(jttl.getM4()!=null){
			map.put("year2", (jttl.getM4().getYear()+1900)+"");
			map.put("month2", jttl.getM4().getMonth() + 1);
			map.put("day2", jttl.getM4().getDate());
			map.put("hour2", jttl.getM4().getHours());
			map.put("min2", jttl.getM4().getMinutes());
		}else{
			map.put("year2", "   ");
			map.put("month2", "  ");
			map.put("day2", "  ");
			map.put("hour2", "  ");
			map.put("min2", "  ");
		}
		return map;
	}
}
