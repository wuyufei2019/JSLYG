package com.cczu.model.zrzh.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.zrzh.dao.ZrzhShxxDao;
import com.cczu.model.zrzh.entity.ZrzhShxxEntiy;
import com.cczu.sys.comm.utils.DateUtils;

/**
 * 自然灾害类-水旱信息快报Service
 * @author Administrator
 *
 */
@Transactional(readOnly=true)
@Service("ZrzhShxxService")
public class ZrzhShxxService {
	
	@Resource
	private ZrzhShxxDao zrzhShxxDao;

	
	
	/**
	 * 水旱信息list页面
	 * @param map
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=zrzhShxxDao.dataGrid(mapData);
		int getTotalCount=zrzhShxxDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
		
	}


	/**
	 * 添加水旱信息
	 * @param shxx
	 */
	public void addInfo(ZrzhShxxEntiy shxx) {
		zrzhShxxDao.save(shxx);
		
	}

	
	/**
	 * 根据id查询水旱信息
	 * @param id
	 * @return
	 */
	public ZrzhShxxEntiy findById(Long id) {
		return zrzhShxxDao.find(id);
	}
	
	
	/**
	 * 修改信息
	 * @param shxx
	 */
	public void updateInfo(ZrzhShxxEntiy shxx) {
		zrzhShxxDao.save(shxx);
		
	}

	
	/**
	 * 根据id删除
	 * @param parseLong
	 */
	public void deleteInfo(long id) {
		zrzhShxxDao.deleteInfo(id);
		
	}

	
	
	/**
	 * 导出word
	 * @param id
	 * @return
	 */
	public Map<String, Object> getExportWord(Long id) {
		Map<String, Object> map=new HashMap<String, Object>();
		ZrzhShxxEntiy shxx =zrzhShxxDao.find(id);
		
		Calendar date = Calendar.getInstance();
		String year = String.valueOf(date.get(Calendar.YEAR));
		String day = String.valueOf(date.get(Calendar.DAY_OF_MONTH));
		map.put("year", year);
		map.put("day", day);
		
		map.put("bgdw",shxx.getM0()==null||shxx.getM0().toString().equals("")?"":shxx.getM0().toString());//报告单位
		map.put("bgsj",DateUtils.formatDate(Timestamp.valueOf(shxx.getM0_1().toString()), "yyyy年MM月dd日HH:mm"));//报告时间
		
		map.put("zhqy",shxx.getM1()==null||shxx.getM1().toString().equals("")?"":shxx.getM1().toString());//灾害区域
		map.put("zhjk_1",shxx.getM2()==null||shxx.getM2().toString().equals("")?"":shxx.getM2().toString());//灾害简况（一般、较大、重大、特别重大）
		map.put("zhjk_2",shxx.getM2_1()==null||shxx.getM2_1().toString().equals("")?"":shxx.getM2_1().toString());//灾害简况（一般、较大、重大、特别重大）
		
		map.put("zhsj",DateUtils.formatDate(Timestamp.valueOf(shxx.getM3().toString()), "yyyy年MM月dd日HH:mm"));//灾害时间
		map.put("zhdd",shxx.getM4()==null||shxx.getM4().toString().equals("")?"":shxx.getM4().toString());//灾害地点 县（市、区）
		
		map.put("zdxqdd",shxx.getM5()==null||shxx.getM5().toString().equals("")?"":shxx.getM5().toString());//一般洪涝或发生决口或出现重大险情地段
		map.put("slfw",shxx.getM6()==null||shxx.getM6().toString().equals("")?"":shxx.getM6().toString());//受涝范围
		map.put("ghcd",shxx.getM7()==null||shxx.getM7().toString().equals("")?"":shxx.getM7().toString());//干旱程度
		map.put("lsjyl",shxx.getM8()==null||shxx.getM8().toString().equals("")?"":shxx.getM8().toString());//24小时连续降雨量或1小时降雨量（毫米）
		
		map.put("nhswm",shxx.getM9()==null||shxx.getM9().toString().equals("")?"":shxx.getM9().toString());//主要内河水位（米）
		map.put("nhswj",shxx.getM9_1()==null||shxx.getM9_1().toString().equals("")?"":shxx.getM9_1().toString());//主要内河水位（级）
		map.put("nhswms",shxx.getM9_2()==null||shxx.getM9_2().toString().equals("")?"":shxx.getM9_2().toString());//主要内河水位（描述）
		
		map.put("shjk",shxx.getM12()==null||shxx.getM12().toString().equals("")?"":shxx.getM12().toString());//水旱简况
		map.put("czqk",shxx.getM13()==null||shxx.getM13().toString().equals("")?"":shxx.getM13().toString());//应对处置情况
		map.put("bz",shxx.getM14()==null||shxx.getM14().toString().equals("")?"":shxx.getM14().toString());//备注
		
		map.put("bgr",shxx.getM15()==null||shxx.getM15().toString().equals("")?"":shxx.getM15().toString());//报告人
		map.put("phone",shxx.getM16()==null||shxx.getM16().toString().equals("")?"":shxx.getM16().toString());//联系电话
		
		
		
		return map;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
