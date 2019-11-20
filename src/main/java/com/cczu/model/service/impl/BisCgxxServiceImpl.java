package com.cczu.model.service.impl;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.cczu.model.dao.IBisCgxxDao;
import com.cczu.model.entity.BIS_TankEntity;
import com.cczu.model.service.IBisCgxxService;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;

@Service("BisCgxxService")
public class BisCgxxServiceImpl implements IBisCgxxService {
	
	@Resource
	private IBisCgxxDao bisCgxxDao;

	@Override
	public List<BIS_TankEntity> findAll(long qyid) {
		return bisCgxxDao.findAll(qyid);
	}

	@Override
	public void addInfo(BIS_TankEntity bis) {
		bisCgxxDao.addInfo(bis);
	}

	@Override
	public void updateInfo(BIS_TankEntity bt) {
		bisCgxxDao.updateInfo(bt);
	}

	@Override
	public void deleteInfo(long id) {
		bisCgxxDao.deleteInfo(id);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<BIS_TankEntity> list = bisCgxxDao.dataGrid(mapData);
		int getTotalCount = bisCgxxDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String , Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public BIS_TankEntity findById(Long id) {
		return bisCgxxDao.findById(id);
	}
	
	@Override
	public BIS_TankEntity findById2(Long id) {
		return bisCgxxDao.findById2(id);
	}

	@Override
	public void exportExcel(HttpServletResponse response,
			Map<String, Object> mapData) {
		String fileName="储罐信息表.xls";
		List<Map<String, Object>> list = bisCgxxDao.getExport(mapData);
		String[] title=mapData.get("coltext").toString().split(",");  
		String[] keys=mapData.get("colval").toString().split(",");  
		new ExportExcel(fileName, title, keys, list, response, true);
	}

	@Override
	public  Map<String, Object>  dataGridAJ(Map<String, Object> map) {
		List<Map<String, Object>> list=bisCgxxDao.dataGridAJ(map);
		int getTotalCount=bisCgxxDao.getTotalCountAJ(map);
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("rows", list);
		mapData.put("total", getTotalCount);
		return mapData;
	}
	
	@Override
	public Map<String,Object> exinExcel(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String,Object> resultmap = new HashMap<String, Object>();
		int result = 0;
		ExinExcel exin = new ExinExcel();
		List<List<Object>> list = exin.exinExcel(map.get("filename").toString(), (InputStream) map.get("content"));
		int i = 0,  error = 0;
		if (list.size() > 3) {
			result = 0;
			resultmap.put("total", list.size()-3);
			resultmap.put("returncode", "success");
			for (List<Object> bis : list) {
				if(i<=2){ //跳过前三行
					i++;
					continue;
				}
				 
				try{
					BIS_TankEntity cg = new BIS_TankEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					cg.setS1(t);
					cg.setS2(t);
					cg.setS3(0);
					cg.setID1(Long.valueOf(map.get("qyid").toString()));
					cg.setM9(bis.get(0).toString());
					cg.setM1(bis.get(1).toString());
					cg.setM7(bis.get(2).toString());
					cg.setM10(bis.get(3).toString());
					cg.setM11(bis.get(4).toString());
					
					if(bis.get(5)!=null&&bis.get(5).toString()!=""){
						String m2=bis.get(5).toString();
						switch (m2) {
						case "立式圆筒形储罐":cg.setM2("1");break;
						case "卧式圆筒形储罐":cg.setM2("2");break;
						case "球形储罐":cg.setM2("3");break;
						case "其他储罐":cg.setM2("4");break;
						default:
							break;
						}
					}
					
					cg.setM3(bis.get(6).toString());
					
					if(bis.get(7)!=null&&bis.get(7).toString()!=""){
						String m4=bis.get(7).toString();
						switch (m4) {
						case "滚塑":cg.setM4("1");break;
						case "玻璃钢":cg.setM4("2");break;
						case "碳钢":cg.setM4("3");break;
						case "陶瓷":cg.setM4("4");break;
						case "橡胶":cg.setM4("5");break;
						case "不锈钢":cg.setM4("7");break;
						case "其他":cg.setM4("6");break;
						default:
							break;
						}
					}

					cg.setM12(bis.get(8).toString());
					
					if(bis.get(9)!=null&&bis.get(9).toString()!=""){
						String m13=bis.get(9).toString();
						switch (m13) {
						case "有":cg.setM13("1");break;
						case "无":cg.setM13("0");break;
						default:
							break;
						}
					}

					cg.setM14(bis.get(10).toString());
					
					if(bis.get(11)!=null&&bis.get(11).toString()!=""){
						String m6=bis.get(11).toString();
						switch (m6) {
						case "甲类":cg.setM6("1");break;
						case "乙类":cg.setM6("2");break;
						case "丙类":cg.setM6("3");break;
						case "丁类":cg.setM6("4");break;
						case "戊类":cg.setM6("5");break;
						default:
							break;
						}
					}
					cg.setM8(bis.get(12).toString());
					bisCgxxDao.addInfo(cg);
					result++;
				}catch(Exception e){
					error++;
				}
				resultmap.put("success",result);
				resultmap.put("error", error);
			}
		}else if(list.size()==3){
			resultmap.put("success",result);
			resultmap.put("error", error);
			resultmap.put("returncode", "warn");
		}else if(list.size()<3){
			resultmap.put("success",result);
			resultmap.put("error", error);
			resultmap.put("returncode", "ext");
		}
		if(Integer.valueOf(resultmap.get("success").toString())==0){
			resultmap.put("returncode", "warn");
		}
		return resultmap;

	}

	@Override
	public void updateInfo2(BIS_TankEntity bt) {
		BIS_TankEntity bis = bisCgxxDao.findById(bt.getID());
		bis.setLevel1(bt.getLevel1());
		bis.setLevel2(bt.getLevel2());
		bis.setTemperature1(bt.getTemperature1());
		bis.setTemperature2(bt.getTemperature2());
		bis.setPressure1(bt.getPressure1());
		bis.setPressure2(bt.getPressure2());
		bisCgxxDao.updateInfo(bis);
	}

	@Override
	public void updateInfo3(BIS_TankEntity bt) {
		BIS_TankEntity bis = bisCgxxDao.findById(bt.getID());
		bis.setLevel1(bt.getLevel1());
		bis.setLevel2(bt.getLevel2());
		bis.setTemperature1(bt.getTemperature1());
		bis.setTemperature2(bt.getTemperature2());
		bis.setPressure1(bt.getPressure1());
		bis.setPressure2(bt.getPressure2());
		bis.setR1(bt.getR1());
		bis.setR2(bt.getR2());
		bis.setR3(bt.getR3());
		bis.setR4(bt.getR4());
		bisCgxxDao.updateInfo(bis);
	}

	@Override
	public Map<String, Object> dataGrid2(Map<String, Object> mapData) {
		List<Map<String, Object>> list = bisCgxxDao.dataGrid2(mapData);
		int getTotalCount = bisCgxxDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String , Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public List<Map<String, Object>> getCgMapJson(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		return bisCgxxDao.getCgMapJson(mapData);
	}
	
		@Override
	public Map<String, Object> statistics(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return bisCgxxDao.statistics(map);
	}
}
