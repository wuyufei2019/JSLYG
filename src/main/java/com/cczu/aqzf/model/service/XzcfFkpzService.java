package com.cczu.aqzf.model.service;

import com.cczu.aqzf.model.dao.XzcfFkpzDao;
import com.cczu.aqzf.model.dao.impl.XzcfCfjdDaoImpl;
import com.cczu.aqzf.model.entity.XZCF_CfjdInfoEntity;
import com.cczu.aqzf.model.entity.XZCF_FkpzEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("XzcfFkpzService")
public class XzcfFkpzService {

	@Resource
	private XzcfFkpzDao xzcfFkpzDao;
	@Resource
	private XzcfCfjdDaoImpl punishsimplecfjddao;
	
	/**
	 * 查询list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=xzcfFkpzDao.dataGrid(mapData);
		int getTotalCount=xzcfFkpzDao.getTotalCount(mapData);
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
		xzcfFkpzDao.deleteInfo(id);
	}
	

	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public XZCF_FkpzEntity findById(Long id) {
		return xzcfFkpzDao.findInfoById(id);
	}
	
	/**
	 * 添加
	 * @param jttl
	 */
	public void addInfo(XZCF_FkpzEntity jttl) {	
		jttl.setS1(new Timestamp(System.currentTimeMillis()));
		jttl.setS2(new Timestamp(System.currentTimeMillis()));
		jttl.setS3(0);
		xzcfFkpzDao.save(jttl);
	}
	
	/**
	 * 修改
	 * @param jttl
	 */
	public void updateInfo(XZCF_FkpzEntity jttl) {
		jttl.setS2(new Timestamp(System.currentTimeMillis()));
		jttl.setS3(0);
		xzcfFkpzDao.save(jttl);
	}
	
	/**
	 * 根据立案id获得list
	 * @param id
	 * @return
	 */
	public List<XZCF_FkpzEntity> findWordByLaId(Long laid) {
		return xzcfFkpzDao.findWordByLaId(laid);
	}

	/**
	 * 获取文书导出word
	 */
	public Map<String, Object> getWsdcword(Long id,String flag) {
		XZCF_FkpzEntity fkpz= xzcfFkpzDao.findInfoById(id);
		XZCF_CfjdInfoEntity cfjd=punishsimplecfjddao.findInfoByLaId(fkpz.getID1());
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("m1", StringUtils.isEmpty(fkpz.getM1())?"":fkpz.getM1());
		map.put("m2", StringUtils.isEmpty(fkpz.getM2())?"            ":fkpz.getM2());
		map.put("m4", StringUtils.isEmpty(fkpz.getM4())?"        ":fkpz.getM4());
		map.put("m7", StringUtils.isEmpty(fkpz.getM7())?"    ":fkpz.getM7());
		map.put("m9", StringUtils.isEmpty(fkpz.getM9())?"          ":fkpz.getM9());
		map.put("m10", StringUtils.isEmpty(fkpz.getM10())?"          ":fkpz.getM10());
		if(cfjd.getPunishdate()!=null){
			map.put("year", (cfjd.getPunishdate().getYear()+1900)+"");
			map.put("month", cfjd.getPunishdate().getMonth() + 1);
			map.put("day", cfjd.getPunishdate().getDate());
		}else{
			map.put("year", "     ");
			map.put("month", "   ");
			map.put("day", "   ");
		}
		map.put("cfjd", cfjd.getNumber());
		if(fkpz.getM6()!=null){
			map.put("year2", (fkpz.getM6().getYear()+1900)+"");
			map.put("month2", fkpz.getM6().getMonth() + 1);
			map.put("day2", fkpz.getM6().getDate());
		}else{
			map.put("year2", "     ");
			map.put("month2", "   ");
			map.put("day2", "   ");
		}
		if(fkpz.getM8()!=null){
			map.put("year3", (fkpz.getM8().getYear()+1900)+"");
			map.put("month3", fkpz.getM8().getMonth() + 1);
			map.put("day3", fkpz.getM8().getDate());
		}else{
			map.put("year3", "     ");
			map.put("month3", "   ");
			map.put("day3", "   ");
		}
		String html="<w:sym w:font=\"Wingdings 2\" w:char=\"0052\"/>";
		String html2 = "<w:t>□</w:t>";
		if(fkpz.getM5().equals("1")){
			map.put("a", html);
			map.put("b", html2);
		}else{
			map.put("a", html2);
			map.put("b", html);
		}
		return map;
	}
}
