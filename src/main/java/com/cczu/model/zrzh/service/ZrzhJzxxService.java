package com.cczu.model.zrzh.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.zrzh.dao.ZrzhJzxxDao;
import com.cczu.model.zrzh.entity.ZrzhJzxxEntiy;
import com.cczu.sys.comm.utils.DateUtils;

/**
 * 自然灾害类-救助信息快报Service
 * @author Administrator
 *
 */
@Transactional(readOnly=true)
@Service("ZrzhJzxxService")
public class ZrzhJzxxService {
	
	@Resource
	private ZrzhJzxxDao zrzhJzxxDao;
	
	
	
	/**
	 * 救助信息list页面
	 * @param map
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=zrzhJzxxDao.dataGrid(mapData);
		int getTotalCount=zrzhJzxxDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
		
	}
	
	
	
	/**
	 * 添加救助信息
	 * @param jzxx
	 */
	public void addInfo(ZrzhJzxxEntiy jzxx) {
		zrzhJzxxDao.save(jzxx);
		
	}
	
	
	
	/**
	 * 根据id查询救助信息
	 * @param id
	 * @return
	 */
	public ZrzhJzxxEntiy findById(Long id) {
		return zrzhJzxxDao.find(id);
	}
	
	
	/**
	 * 修改信息
	 * @param jzxx
	 */
	public void updateInfo(ZrzhJzxxEntiy jzxx) {
		zrzhJzxxDao.save(jzxx);
		
	}

	
	/**
	 * 根据id删除
	 * @param parseLong
	 */
	public void deleteInfo(long id) {
		zrzhJzxxDao.deleteInfo(id);
		
	}
	
	
	
	
	/**
	 * 导出word
	 * @param id
	 * @return
	 */
	public Map<String, Object> getExportWord(Long id) {
		Map<String, Object> map=new HashMap<String, Object>();
		ZrzhJzxxEntiy jzxx =zrzhJzxxDao.find(id);
		
		
		Calendar date = Calendar.getInstance();
		String year = String.valueOf(date.get(Calendar.YEAR));
		String day = String.valueOf(date.get(Calendar.DAY_OF_MONTH));
		map.put("year", year);
		map.put("day", day);
		
		map.put("bgdw",jzxx.getM0()==null||jzxx.getM0().toString().equals("")?"":jzxx.getM0().toString());//报告单位
		map.put("bgsj",DateUtils.formatDate(Timestamp.valueOf(jzxx.getM0_1().toString()), "yyyy年MM月dd日HH:mm"));//报告时间
		
		map.put("zhddx",jzxx.getM4()==null||jzxx.getM4().toString().equals("")?"":jzxx.getM4().toString());//地点 县（市、区）
		map.put("zhddz",jzxx.getM4_1()==null||jzxx.getM4_1().toString().equals("")?"":jzxx.getM4_1().toString());//地点  镇（街道、园区）
		map.put("bgjk",jzxx.getM2()==null||jzxx.getM2().toString().equals("")?"":jzxx.getM2().toString());//报告简况（洪涝、雨雪冰冻等）
		map.put("zhsj",DateUtils.formatDate(Timestamp.valueOf(jzxx.getM3().toString()), "yyyy年MM月dd日HH:mm"));//灾害时间
		
		
		map.put("dj",jzxx.getM5()==null||jzxx.getM5().toString().equals("")?"":jzxx.getM5().toString());//等级
		map.put("swrs",jzxx.getM6()==null||jzxx.getM6().toString().equals("")?"":jzxx.getM6().toString());//死亡人数
		map.put("shjzrs",jzxx.getM7()==null||jzxx.getM7().toString().equals("")?"":jzxx.getM7().toString());//紧急转移安置或需紧急生活救助人数
		map.put("zfjzrs",jzxx.getM7_1()==null||jzxx.getM7_1().toString().equals("")?"":jzxx.getM7_1().toString());//需政府救助人数
		map.put("shfw",jzxx.getM10()==null||jzxx.getM10().toString().equals("")?"":jzxx.getM10().toString());//倒塌和严重损坏房屋数
		map.put("rkbl",jzxx.getM11()==null||jzxx.getM11().toString().equals("")?"":jzxx.getM11().toString());//占灾害发生地县（市、区）农业人口比例
		
		map.put("zhjk",jzxx.getM12()==null||jzxx.getM12().toString().equals("")?"":jzxx.getM12().toString());//灾害简况
		map.put("czqk",jzxx.getM13()==null||jzxx.getM13().toString().equals("")?"":jzxx.getM13().toString());//应对处置情况
		map.put("bz",jzxx.getM14()==null||jzxx.getM14().toString().equals("")?"":jzxx.getM14().toString());//备注
		
		map.put("bgr",jzxx.getM15()==null||jzxx.getM15().toString().equals("")?"":jzxx.getM15().toString());//报告人
		map.put("phone",jzxx.getM16()==null||jzxx.getM16().toString().equals("")?"":jzxx.getM16().toString());//联系电话
		
		
		
		return map;
	}
	
	

}
