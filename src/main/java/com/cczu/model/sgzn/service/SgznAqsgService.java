package com.cczu.model.sgzn.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.entity.BIS_WorkorderEntity;
import com.cczu.model.sgzn.dao.SgznAqsgDao;
import com.cczu.model.sgzn.entity.SgznAqsgEntity;
import com.cczu.sys.comm.utils.DateUtils;

/**
 * 事故灾难类-安全事故信息快报Service
 * @author Administrator
 *
 */
@Transactional(readOnly=true)
@Service("SgznAqsgService")
public class SgznAqsgService {
	@Resource
	private SgznAqsgDao sgznAqsgDao;
	
	
	
	
	/**
	 * 安全事故list页面
	 * @param map
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=sgznAqsgDao.dataGrid(mapData);
		int getTotalCount=sgznAqsgDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}



	/**
	 * 添加安全事故信息
	 * @param aqsg
	 */
	public void addInfo(SgznAqsgEntity aqsg) {
		sgznAqsgDao.save(aqsg);
		
	}


	/**
	 * 根据id查询信息
	 * @param id
	 * @return
	 */
	public SgznAqsgEntity findById(Long id) {
		return sgznAqsgDao.find(id);
	}


	/**
	 * 修改信息
	 * @param aqsg1
	 */
	public void updateInfo(SgznAqsgEntity aqsg) {
		sgznAqsgDao.save(aqsg);

	}


	/**
	 * 根据id删除
	 * @param parseLong
	 */
	public void deleteInfo(long id) {
		sgznAqsgDao.deleteInfo(id);
		
	}


	/**
	 * 导出word
	 * @param id
	 * @return
	 */
	public Map<String, Object> getExportWord(Long id) {
		Map<String, Object> map=new HashMap<String, Object>();
		SgznAqsgEntity aqsg =sgznAqsgDao.find(id);
		
		Calendar date = Calendar.getInstance();
		String year = String.valueOf(date.get(Calendar.YEAR));
		String day = String.valueOf(date.get(Calendar.DAY_OF_MONTH));
		map.put("year", year);
		map.put("day", day);
		
		
		map.put("bgdw",aqsg.getM0()==null||aqsg.getM0().toString().equals("")?"":aqsg.getM0().toString());//报告单位
		map.put("bgsj",DateUtils.formatDate(Timestamp.valueOf(aqsg.getM0_1().toString()), "yyyy年MM月dd日HH:mm"));//报告时间
		map.put("sgdw",aqsg.getM1()==null||aqsg.getM1().toString().equals("")?"":aqsg.getM1().toString());//事故单位名称
		map.put("bgjk",aqsg.getM2()==null||aqsg.getM2().toString().equals("")?"":aqsg.getM2().toString());//报告简况（机械伤害、高处坠落、触电、溺水等）
		map.put("sgsj",DateUtils.formatDate(Timestamp.valueOf(aqsg.getM3().toString()), "yyyy年MM月dd日HH:mm"));//事故时间
		
		
		map.put("sgddx",aqsg.getM4()==null||aqsg.getM4().toString().equals("")?"":aqsg.getM4().toString());//事故地点 县（市、区）
		map.put("sgddz",aqsg.getM4_1()==null||aqsg.getM4_1().toString().equals("")?"":aqsg.getM4_1().toString());//事故地点  镇（街道、园区）
		
		map.put("sxzrs",aqsg.getM5()==null||aqsg.getM5().toString().equals("")?"":aqsg.getM5().toString());//涉险总人数
		map.put("swrs",aqsg.getM6()==null||aqsg.getM6().toString().equals("")?"":aqsg.getM6().toString());//死亡人数
		map.put("szrs",aqsg.getM7()==null||aqsg.getM7().toString().equals("")?"":aqsg.getM7().toString());//失踪人数
		map.put("bkrs",aqsg.getM8()==null||aqsg.getM8().toString().equals("")?"":aqsg.getM8().toString());//被困人数
		map.put("ssrs",aqsg.getM9()==null||aqsg.getM9().toString().equals("")?"":aqsg.getM9().toString());//受伤人数
		map.put("zdrs",aqsg.getM10()==null||aqsg.getM10().toString().equals("")?"":aqsg.getM10().toString());//中毒人数
		
		map.put("jjlx",aqsg.getM11()==null||aqsg.getM11().toString().equals("")?"":aqsg.getM11().toString());//经济类型
		map.put("sgjk",aqsg.getM12()==null||aqsg.getM12().toString().equals("")?"":aqsg.getM12().toString());//事故简况
		map.put("czqk",aqsg.getM13()==null||aqsg.getM13().toString().equals("")?"":aqsg.getM13().toString());//应对处置情况
		map.put("bz",aqsg.getM14()==null||aqsg.getM14().toString().equals("")?"":aqsg.getM14().toString());//备注
		
		map.put("bgr",aqsg.getM15()==null||aqsg.getM15().toString().equals("")?"":aqsg.getM15().toString());//报告人
		map.put("phone",aqsg.getM16()==null||aqsg.getM16().toString().equals("")?"":aqsg.getM16().toString());//联系电话
		
		
		return map;
	}


	
	
	
	
}
