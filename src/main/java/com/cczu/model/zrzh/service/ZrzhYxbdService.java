package com.cczu.model.zrzh.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.zrzh.dao.ZrzhYxbdDao;
import com.cczu.model.zrzh.entity.ZrzhYxbdEntiy;
import com.cczu.sys.comm.utils.DateUtils;

/**
 * 自然灾害类-雨雪冰冻信息快报Service
 * @author Administrator
 *
 */
@Transactional(readOnly=true)
@Service("ZrzhYxbdService")
public class ZrzhYxbdService {
	
	@Resource
	private ZrzhYxbdDao zrzhYxbdDao;
	
	
	
	
	
	
	/**
	 * 雨雪冰冻信息list页面
	 * @param map
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=zrzhYxbdDao.dataGrid(mapData);
		int getTotalCount=zrzhYxbdDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
		
	}
	
	
	
	
	/**
	 * 添加地震信息
	 * @param yxbd
	 */
	public void addInfo(ZrzhYxbdEntiy yxbd) {
		zrzhYxbdDao.save(yxbd);
		
	}
	
	
	
	/**
	 * 根据id查询地震信息
	 * @param id
	 * @return
	 */
	public ZrzhYxbdEntiy findById(Long id) {
		return zrzhYxbdDao.find(id);
	}
	
	
	/**
	 * 修改信息
	 * @param yxbd
	 */
	public void updateInfo(ZrzhYxbdEntiy yxbd) {
		zrzhYxbdDao.save(yxbd);
		
	}

	
	/**
	 * 根据id删除
	 * @param parseLong
	 */
	public void deleteInfo(long id) {
		zrzhYxbdDao.deleteInfo(id);
		
	}
	
	
	
	
	/**
	 * 导出word
	 * @param id
	 * @return
	 */
	public Map<String, Object> getExportWord(Long id) {
		Map<String, Object> map=new HashMap<String, Object>();
		ZrzhYxbdEntiy yxbd =zrzhYxbdDao.find(id);
		
		Calendar date = Calendar.getInstance();
		String year = String.valueOf(date.get(Calendar.YEAR));
		String day = String.valueOf(date.get(Calendar.DAY_OF_MONTH));
		map.put("year", year);
		map.put("day", day);
		
		
		map.put("bgdw",yxbd.getM0()==null||yxbd.getM0().toString().equals("")?"":yxbd.getM0().toString());//报告单位
		map.put("bgsj",DateUtils.formatDate(Timestamp.valueOf(yxbd.getM0_1().toString()), "yyyy年MM月dd日HH:mm"));//报告时间
		
		map.put("zhqy",yxbd.getM1()==null||yxbd.getM1().toString().equals("")?"":yxbd.getM1().toString());//灾害区域
		map.put("zhddx",yxbd.getM4()==null||yxbd.getM4().toString().equals("")?"":yxbd.getM4().toString());//地点 县（市、区）
		map.put("zhddz",yxbd.getM4_1()==null||yxbd.getM4_1().toString().equals("")?"":yxbd.getM4_1().toString());//地点  镇（街道、园区）
		map.put("bgjk",yxbd.getM2()==null||yxbd.getM2().toString().equals("")?"":yxbd.getM2().toString());//报告简况（较大及以下、重大及以上、特别重大）
		map.put("zhsj",DateUtils.formatDate(Timestamp.valueOf(yxbd.getM3().toString()), "yyyy年MM月dd日HH:mm"));//灾情时间
		
		map.put("jxl",yxbd.getM5()==null||yxbd.getM5().toString().equals("")?"":yxbd.getM5().toString());//24小时降雪量
		map.put("lxts",yxbd.getM6()==null||yxbd.getM6().toString().equals("")?"":yxbd.getM6().toString());//日平均气温在o℃以下连续天数
		map.put("jxsd",yxbd.getM7()==null||yxbd.getM7().toString().equals("")?"":yxbd.getM7().toString());//积雪深度
		map.put("jbhd",yxbd.getM8()==null||yxbd.getM8().toString().equals("")?"":yxbd.getM8().toString());//雨凇、电线积冰直径或地面结冰厚度
		if("是".equals(yxbd.getM9())){//预报雨雪天气是否持续
			map.put("yxtqs", "F052");//是  F052代表选中
			map.put("yxtqf", "F0A3");//否  F0A3代表未选中
		}else{
			map.put("yxtqs", "F0A3");
			map.put("yxtqf", "F052");
		}
		
		map.put("yxbdjk",yxbd.getM12()==null||yxbd.getM12().toString().equals("")?"":yxbd.getM12().toString());//雨雪冰冻简况
		map.put("czqk",yxbd.getM13()==null||yxbd.getM13().toString().equals("")?"":yxbd.getM13().toString());//应对处置情况
		map.put("bz",yxbd.getM14()==null||yxbd.getM14().toString().equals("")?"":yxbd.getM14().toString());//备注
		
		map.put("bgr",yxbd.getM15()==null||yxbd.getM15().toString().equals("")?"":yxbd.getM15().toString());//报告人
		map.put("phone",yxbd.getM16()==null||yxbd.getM16().toString().equals("")?"":yxbd.getM16().toString());//联系电话
		
		
		return map;
	}



	
	
	
	
	

}
