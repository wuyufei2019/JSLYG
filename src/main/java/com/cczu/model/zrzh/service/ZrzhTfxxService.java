package com.cczu.model.zrzh.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.zrzh.dao.ZrzhTfxxDao;
import com.cczu.model.zrzh.entity.ZrzhTfxxEntiy;
import com.cczu.sys.comm.utils.DateUtils;

/**
 * 自然灾害类-台风信息快报Service
 * @author Administrator
 *
 */
@Transactional(readOnly=true)
@Service("ZrzhTfxxService")
public class ZrzhTfxxService {
	
	@Resource
	private ZrzhTfxxDao zrzhTfxxDao;
	
	
	
	/**
	 * 台风信息list页面
	 * @param map
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=zrzhTfxxDao.dataGrid(mapData);
		int getTotalCount=zrzhTfxxDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
		
	}
	
	
	
	/**
	 * 添加台风信息
	 * @param tfxx
	 */
	public void addInfo(ZrzhTfxxEntiy tfxx) {
		zrzhTfxxDao.save(tfxx);
		
	}

	
	/**
	 * 根据id查询台风信息
	 * @param id
	 * @return
	 */
	public ZrzhTfxxEntiy findById(Long id) {
		return zrzhTfxxDao.find(id);
	}
	
	
	/**
	 * 修改信息
	 * @param tfxx
	 */
	public void updateInfo(ZrzhTfxxEntiy tfxx) {
		zrzhTfxxDao.save(tfxx);
		
	}

	
	/**
	 * 根据id删除
	 * @param parseLong
	 */
	public void deleteInfo(long id) {
		zrzhTfxxDao.deleteInfo(id);
		
	}

	
	/**
	 * 导出word
	 * @param id
	 * @return
	 */
	public Map<String, Object> getExportWord(Long id) {
		Map<String, Object> map=new HashMap<String, Object>();
		ZrzhTfxxEntiy tfxx =zrzhTfxxDao.find(id);
		
		Calendar date = Calendar.getInstance();
		String year = String.valueOf(date.get(Calendar.YEAR));
		String day = String.valueOf(date.get(Calendar.DAY_OF_MONTH));
		map.put("year", year);
		map.put("day", day);
		
		map.put("bgdw",tfxx.getM0()==null||tfxx.getM0().toString().equals("")?"":tfxx.getM0().toString());//报告单位
		map.put("bgsj",DateUtils.formatDate(Timestamp.valueOf(tfxx.getM0_1().toString()), "yyyy年MM月dd日HH:mm"));//报告时间
		
		map.put("zhqy",tfxx.getM1()==null||tfxx.getM1().toString().equals("")?"":tfxx.getM1().toString());//灾害区域
		map.put("zhjk",tfxx.getM2()==null||tfxx.getM2().toString().equals("")?"":tfxx.getM2().toString());//灾害简况（台风、强台风、超强台风）
		
		map.put("zhsj",DateUtils.formatDate(Timestamp.valueOf(tfxx.getM3().toString()), "yyyy年MM月dd日HH:mm"));//灾害时间
		map.put("zhdd",tfxx.getM4()==null||tfxx.getM4().toString().equals("")?"":tfxx.getM4().toString());//灾害地点 县（市、区）
		map.put("xss",tfxx.getM5()==null||tfxx.getM5().toString().equals("")?"":tfxx.getM5().toString());////受热带气旋影响小时数
		map.put("fldj",tfxx.getM6()==null||tfxx.getM6().toString().equals("")?"":tfxx.getM6().toString());//风力等级
		map.put("zfdj",tfxx.getM7()==null||tfxx.getM7().toString().equals("")?"":tfxx.getM7().toString());//阵风等级
		if("是".equals(tfxx.getM7_1())){//阵风是否持续
			map.put("zfdjs", "F052");//是  F052代表选中
			map.put("zfdjf", "F0A3");//否  F0A3代表未选中
		}else{
			map.put("zfdjs", "F0A3");
			map.put("zfdjf", "F052");
		}
		
		
		map.put("xsjy",tfxx.getM8()==null||tfxx.getM8().toString().equals("")?"":tfxx.getM8().toString());//小时内降雨
		map.put("jyl",tfxx.getM8_1()==null||tfxx.getM8_1().toString().equals("")?"":tfxx.getM8_1().toString());//降雨量
		map.put("ysjyl",tfxx.getM8_2()==null||tfxx.getM8_2().toString().equals("")?"":tfxx.getM8_2().toString());//以上降雨量（毫米）
		if("是".equals(tfxx.getM8_3())){//降雨是否持续
			map.put("cxjys", "F052");
			map.put("cxjyf", "F0A3");
		}else{
			map.put("cxjys", "F0A3");
			map.put("cxjyf", "F052");
		}
		
		
		map.put("hl",tfxx.getM9()==null||tfxx.getM9().toString().equals("")?"":tfxx.getM9().toString());//河流
		map.put("hlcw",tfxx.getM9_1()==null||tfxx.getM9_1().toString().equals("")?"":tfxx.getM9_1().toString());//警戒线、历史最高潮位、历史最高水位）
		if("是".equals(tfxx.getM9_2())){//是否超过警戒线
			map.put("hlcws", "F052");
			map.put("hlcwf", "F0A3");
		}else{
			map.put("hlcws", "F0A3");
			map.put("hlcwf", "F052");
		}
		
		
		map.put("jjcw",tfxx.getM10()==null||tfxx.getM10().toString().equals("")?"":tfxx.getM10().toString());//警戒潮位（黄色、橙色、红色）
		if("是".equals(tfxx.getM10_1())){//是否达到警戒潮位
			map.put("jjcws", "F052");
			map.put("jjcwf", "F0A3");
		}else{
			map.put("jjcws", "F0A3");
			map.put("jjcwf", "F052");
		}
		
		map.put("tfjk",tfxx.getM12()==null||tfxx.getM12().toString().equals("")?"":tfxx.getM12().toString());//台风简况
		map.put("czqk",tfxx.getM13()==null||tfxx.getM13().toString().equals("")?"":tfxx.getM13().toString());//应对处置情况
		map.put("bz",tfxx.getM14()==null||tfxx.getM14().toString().equals("")?"":tfxx.getM14().toString());//备注
		
		map.put("bgr",tfxx.getM15()==null||tfxx.getM15().toString().equals("")?"":tfxx.getM15().toString());//报告人
		map.put("phone",tfxx.getM16()==null||tfxx.getM16().toString().equals("")?"":tfxx.getM16().toString());//联系电话
		
		
		return map;
	}
	
	
	
	
	
	
	
	
	
	
	

}
