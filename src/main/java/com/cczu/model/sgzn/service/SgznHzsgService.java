package com.cczu.model.sgzn.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.sgzn.dao.SgznHzsgDao;
import com.cczu.model.sgzn.entity.SgznHzsgEntity;
import com.cczu.sys.comm.utils.DateUtils;

/**
 * 事故灾难类-火灾事故信息快报Service
 * @author Administrator
 *
 */
@Transactional(readOnly=true)
@Service("SgznHzsgService")
public class SgznHzsgService {
	
	@Resource
	private SgznHzsgDao sgznHzsgDao;
	
	
	
	/**
	 * 火灾事故list页面
	 * @param map
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=sgznHzsgDao.dataGrid(mapData);
		int getTotalCount=sgznHzsgDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}


	/**
	 * 添加火灾事故信息
	 * @param hzsg
	 */
	public void addInfo(SgznHzsgEntity hzsg) {
		sgznHzsgDao.save(hzsg);
		
	}
	
	/**
	 * 根据id查询火灾事故信息
	 * @param id
	 * @return
	 */
	public SgznHzsgEntity findById(Long id) {
		return sgznHzsgDao.find(id);
	}

	
	/**
	 * 修改火灾事故信息
	 * @param hzsg
	 */
	public void updateInfo(SgznHzsgEntity hzsg) {
		sgznHzsgDao.save(hzsg);
		
	}

	/**
	 * 依据id删除
	 * @param parseLong
	 */
	public void deleteInfo(long id) {
		sgznHzsgDao.deleteInfo(id);
		
	}
	
	
	
	/**
	 * 导出word
	 * @param id
	 * @return
	 */
	public Map<String, Object> getExportWord(Long id) {
		Map<String, Object> map=new HashMap<String, Object>();
		SgznHzsgEntity hzsg =sgznHzsgDao.find(id);
		
		Calendar date = Calendar.getInstance();
		String year = String.valueOf(date.get(Calendar.YEAR));
		String day = String.valueOf(date.get(Calendar.DAY_OF_MONTH));
		map.put("year", year);
		map.put("day", day);
		
		map.put("bgdw",hzsg.getM0()==null||hzsg.getM0().toString().equals("")?"":hzsg.getM0().toString());//报告单位
		map.put("bgsj",DateUtils.formatDate(Timestamp.valueOf(hzsg.getM0_1().toString()), "yyyy年MM月dd日HH:mm"));//报告时间
		map.put("sgdw",hzsg.getM1()==null||hzsg.getM1().toString().equals("")?"":hzsg.getM1().toString());//事故单位名称
		map.put("bgjk",hzsg.getM2()==null||hzsg.getM2().toString().equals("")?"":hzsg.getM2().toString());//报告简况（火灾事故）
		map.put("sgsj",DateUtils.formatDate(Timestamp.valueOf(hzsg.getM3().toString()), "yyyy年MM月dd日HH:mm"));//事故时间
		
		
		map.put("sgddx",hzsg.getM4()==null||hzsg.getM4().toString().equals("")?"":hzsg.getM4().toString());//事故地点 县（市、区）
		map.put("sgddz",hzsg.getM4_1()==null||hzsg.getM4_1().toString().equals("")?"":hzsg.getM4_1().toString());//事故地点  镇（街道、园区）
		
		map.put("swrs",hzsg.getM6()==null||hzsg.getM6().toString().equals("")?"":hzsg.getM6().toString());//死亡人数
		map.put("szrs",hzsg.getM7()==null||hzsg.getM7().toString().equals("")?"":hzsg.getM7().toString());//失踪人数
		map.put("ssrs",hzsg.getM9()==null||hzsg.getM9().toString().equals("")?"":hzsg.getM9().toString());//受伤人数
		
		map.put("jjss",hzsg.getM11()==null||hzsg.getM11().toString().equals("")?"":hzsg.getM11().toString());//经济损失
		map.put("sgjk",hzsg.getM12()==null||hzsg.getM12().toString().equals("")?"":hzsg.getM12().toString());//事故简况
		map.put("czqk",hzsg.getM13()==null||hzsg.getM13().toString().equals("")?"":hzsg.getM13().toString());//应对处置情况
		map.put("bz",hzsg.getM14()==null||hzsg.getM14().toString().equals("")?"":hzsg.getM14().toString());//备注
		
		map.put("bgr",hzsg.getM15()==null||hzsg.getM15().toString().equals("")?"":hzsg.getM15().toString());//报告人
		map.put("phone",hzsg.getM16()==null||hzsg.getM16().toString().equals("")?"":hzsg.getM16().toString());//联系电话
		
		
		
		
		return map;
	}
	
	
	
	
	
	
	

}
