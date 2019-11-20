package com.cczu.model.zrzh.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.zrzh.dao.ZrzhDzznDao;
import com.cczu.model.zrzh.entity.ZrzhDzznEntiy;
import com.cczu.sys.comm.utils.DateUtils;

/**
 * 自然灾害类-地质灾难信息快报Service
 * @author Administrator
 *
 */
@Transactional(readOnly=true)
@Service("ZrzhDzznService")
public class ZrzhDzznService {
	
	@Resource
	private ZrzhDzznDao zrzhDzznDao;
	
	
	/**
	 * 地质灾难信息list页面
	 * @param map
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=zrzhDzznDao.dataGrid(mapData);
		int getTotalCount=zrzhDzznDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
		
	}
	
	
	/**
	 * 添加地质灾难信息
	 * @param dzzn
	 */
	public void addInfo(ZrzhDzznEntiy dzzn) {
		zrzhDzznDao.save(dzzn);
		
	}
	
	
	
	/**
	 * 根据id查询地质灾难信息
	 * @param id
	 * @return
	 */
	public ZrzhDzznEntiy findById(Long id) {
		return zrzhDzznDao.find(id);
	}
	
	
	/**
	 * 修改信息
	 * @param dzzn
	 */
	public void updateInfo(ZrzhDzznEntiy dzzn) {
		zrzhDzznDao.save(dzzn);
		
	}

	
	/**
	 * 根据id删除
	 * @param parseLong
	 */
	public void deleteInfo(long id) {
		zrzhDzznDao.deleteInfo(id);
		
	}


	/**
	 * 导出word
	 * @param id
	 * @return
	 */
	public Map<String, Object> getExportWord(Long id) {
		Map<String, Object> map=new HashMap<String, Object>();
		ZrzhDzznEntiy dzzn =zrzhDzznDao.find(id);
		
		Calendar date = Calendar.getInstance();
		String year = String.valueOf(date.get(Calendar.YEAR));
		String day = String.valueOf(date.get(Calendar.DAY_OF_MONTH));
		map.put("year", year);
		map.put("day", day);
		
		map.put("bgdw",dzzn.getM0()==null||dzzn.getM0().toString().equals("")?"":dzzn.getM0().toString());//报告单位
		map.put("bgsj",DateUtils.formatDate(Timestamp.valueOf(dzzn.getM0_1().toString()), "yyyy年MM月dd日HH:mm"));//报告时间
		
		map.put("zhddx",dzzn.getM4()==null||dzzn.getM4().toString().equals("")?"":dzzn.getM4().toString());//地点 县（市、区）
		map.put("zhddz",dzzn.getM4_1()==null||dzzn.getM4_1().toString().equals("")?"":dzzn.getM4_1().toString());//地点  镇（街道、园区）
		map.put("bgjk",dzzn.getM2()==null||dzzn.getM2().toString().equals("")?"":dzzn.getM2().toString());//报告简况（一般、较大、重大、特别重大）
		map.put("zhsj",DateUtils.formatDate(Timestamp.valueOf(dzzn.getM3().toString()), "yyyy年MM月dd日HH:mm"));//灾害时间
		
		map.put("dzznlx",dzzn.getM5()==null||dzzn.getM5().toString().equals("")?"":dzzn.getM5().toString());//地质灾害类型（滑坡、崩塌等）
		map.put("zyrs",dzzn.getM7()==null||dzzn.getM7().toString().equals("")?"":dzzn.getM7().toString());//受威胁转移人数
		map.put("ssfw",dzzn.getM10()==null||dzzn.getM10().toString().equals("")?"":dzzn.getM10().toString());//受损房屋
		
		map.put("swrs",dzzn.getM6()==null||dzzn.getM6().toString().equals("")?"":dzzn.getM6().toString());//死亡人数
		map.put("ssrs",dzzn.getM9()==null||dzzn.getM9().toString().equals("")?"":dzzn.getM9().toString());//受伤人数
		map.put("bkszrs",dzzn.getM8()==null||dzzn.getM8().toString().equals("")?"":dzzn.getM8().toString());//被困、失踪人数
		
		map.put("dzzhjk",dzzn.getM12()==null||dzzn.getM12().toString().equals("")?"":dzzn.getM12().toString());//地质灾害简况
		map.put("czqk",dzzn.getM13()==null||dzzn.getM13().toString().equals("")?"":dzzn.getM13().toString());//应对处置情况
		map.put("bz",dzzn.getM14()==null||dzzn.getM14().toString().equals("")?"":dzzn.getM14().toString());//备注
		
		map.put("bgr",dzzn.getM15()==null||dzzn.getM15().toString().equals("")?"":dzzn.getM15().toString());//报告人
		map.put("phone",dzzn.getM16()==null||dzzn.getM16().toString().equals("")?"":dzzn.getM16().toString());//联系电话
		
		
		return map;
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
