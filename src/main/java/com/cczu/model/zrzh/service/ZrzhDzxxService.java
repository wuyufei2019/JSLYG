package com.cczu.model.zrzh.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.zrzh.dao.ZrzhDzxxDao;
import com.cczu.model.zrzh.entity.ZrzhDzxxEntiy;
import com.cczu.sys.comm.utils.DateUtils;

/**
 * 自然灾害类-地震信息快报Service
 * @author Administrator
 *
 */
@Transactional(readOnly=true)
@Service("ZrzhDzxxService")
public class ZrzhDzxxService {
	
	@Resource
	private ZrzhDzxxDao zrzhDzxxDao;
	
	
	/**
	 * 地震信息list页面
	 * @param map
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=zrzhDzxxDao.dataGrid(mapData);
		int getTotalCount=zrzhDzxxDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
		
	}
	
	
	/**
	 * 添加地震信息
	 * @param dzxx
	 */
	public void addInfo(ZrzhDzxxEntiy dzxx) {
		zrzhDzxxDao.save(dzxx);
		
	}
	
	
	
	/**
	 * 根据id查询地震信息
	 * @param id
	 * @return
	 */
	public ZrzhDzxxEntiy findById(Long id) {
		return zrzhDzxxDao.find(id);
	}
	
	
	/**
	 * 修改信息
	 * @param dzxx
	 */
	public void updateInfo(ZrzhDzxxEntiy dzxx) {
		zrzhDzxxDao.save(dzxx);
		
	}

	
	/**
	 * 根据id删除
	 * @param parseLong
	 */
	public void deleteInfo(long id) {
		zrzhDzxxDao.deleteInfo(id);
		
	}


	/**
	 * 导出word
	 * @param id
	 * @return
	 */
	public Map<String, Object> getExportWord(Long id) {
		Map<String, Object> map=new HashMap<String, Object>();
		ZrzhDzxxEntiy dzxx =zrzhDzxxDao.find(id);
		
		Calendar date = Calendar.getInstance();
		String year = String.valueOf(date.get(Calendar.YEAR));
		String day = String.valueOf(date.get(Calendar.DAY_OF_MONTH));
		map.put("year", year);
		map.put("day", day);
		
		map.put("bgdw",dzxx.getM0()==null||dzxx.getM0().toString().equals("")?"":dzxx.getM0().toString());//报告单位
		map.put("bgsj",DateUtils.formatDate(Timestamp.valueOf(dzxx.getM0_1().toString()), "yyyy年MM月dd日HH:mm"));//报告时间
		
		map.put("zhqy",dzxx.getM1()==null||dzxx.getM1().toString().equals("")?"":dzxx.getM1().toString());//灾害区域
		map.put("zj",dzxx.getM4()==null||dzxx.getM4().toString().equals("")?"":dzxx.getM4().toString());//震级
		map.put("bgjk",dzxx.getM2()==null||dzxx.getM2().toString().equals("")?"":dzxx.getM2().toString());//报告简况（一般、较大、重大、特别重大）
		
		map.put("zhsj",DateUtils.formatDate(Timestamp.valueOf(dzxx.getM3().toString()), "yyyy年MM月dd日HH:mm"));//灾害时间
		map.put("zyjd",dzxx.getM4_1()==null||dzxx.getM4_1().toString().equals("")?"":dzxx.getM4_1().toString());//震源经度
		map.put("zywd",dzxx.getM4_2()==null||dzxx.getM4_2().toString().equals("")?"":dzxx.getM4_2().toString());//震源经度
		
		map.put("sxzrs",dzxx.getM5()==null||dzxx.getM5().toString().equals("")?"":dzxx.getM5().toString());//涉险总人数
		map.put("swrs",dzxx.getM6()==null||dzxx.getM6().toString().equals("")?"":dzxx.getM6().toString());//死亡人数
		map.put("szrs",dzxx.getM7()==null||dzxx.getM7().toString().equals("")?"":dzxx.getM7().toString());//失踪人数
		map.put("bkrs",dzxx.getM8()==null||dzxx.getM8().toString().equals("")?"":dzxx.getM8().toString());//被困人数
		map.put("ssrs",dzxx.getM9()==null||dzxx.getM9().toString().equals("")?"":dzxx.getM9().toString());//受伤人数
		
		map.put("dzjk",dzxx.getM12()==null||dzxx.getM12().toString().equals("")?"":dzxx.getM12().toString());//地震简况
		map.put("czqk",dzxx.getM13()==null||dzxx.getM13().toString().equals("")?"":dzxx.getM13().toString());//应对处置情况
		map.put("bz",dzxx.getM14()==null||dzxx.getM14().toString().equals("")?"":dzxx.getM14().toString());//备注
		
		map.put("bgr",dzxx.getM15()==null||dzxx.getM15().toString().equals("")?"":dzxx.getM15().toString());//报告人
		map.put("phone",dzxx.getM16()==null||dzxx.getM16().toString().equals("")?"":dzxx.getM16().toString());//联系电话
		
		
		return map;
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
