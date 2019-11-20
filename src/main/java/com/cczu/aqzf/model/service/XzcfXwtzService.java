package com.cczu.aqzf.model.service;

import com.cczu.aqzf.model.dao.AqzfSetBasicInfoDao;
import com.cczu.aqzf.model.dao.AqzfSetNumberDao;
import com.cczu.aqzf.model.dao.IXzcfCommonLaspDao;
import com.cczu.aqzf.model.dao.XzcfXwtzDao;
import com.cczu.aqzf.model.entity.AQZF_SetBasicInfoEntity;
import com.cczu.aqzf.model.entity.AQZF_SetNumberEntity;
import com.cczu.aqzf.model.entity.XZCF_EnquiryNoteEntity;
import com.cczu.aqzf.model.entity.XZCF_YbcfLaspEntity;
import com.cczu.model.dao.IBisQyjbxxDao;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("XzcfXwtzService")
public class XzcfXwtzService {

	@Resource
	private XzcfXwtzDao xzcfXwtzDao;
	@Resource
	private AqzfSetNumberDao aqzfSetNumberDao;
	@Resource
	private IXzcfCommonLaspDao xzcfcommonlaspdao;
	@Resource
	private IBisQyjbxxDao bisqyjbxxdao;
	@Resource
	private AqzfSetBasicInfoDao setbasicdao;
	
	/**
	 * 查询询问通知list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=xzcfXwtzDao.dataGrid(mapData);
		int getTotalCount=xzcfXwtzDao.getTotalCount(mapData);
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
		xzcfXwtzDao.deleteInfo(id);
	}
	/**
	 * 删除后 立案审批的xwflag置0
	 * @param id
	 */
	public void updateLaspInfo(long id) {
		xzcfXwtzDao.updateLaspInfo(id);
	}
	
	/**
	 * 添加询问通知信息
	 * @param zfry
	 */
	public void addInfo(XZCF_EnquiryNoteEntity zfry) {
		zfry.setS1(new Timestamp(System.currentTimeMillis()));
		zfry.setS2(new Timestamp(System.currentTimeMillis()));
		zfry.setS3(0);
		xzcfXwtzDao.addInfo(zfry);
	}

	/**
	 * 根据id查找询问通知信息
	 * @param id
	 * @return
	 */
	public XZCF_EnquiryNoteEntity findById(Long id) {
		return xzcfXwtzDao.findInfoById(id);
	}
	
	/**
	 * 修改
	 * @param zfry
	 */
	public void updateInfo(XZCF_EnquiryNoteEntity zfry) {
		zfry.setS2(new Timestamp(System.currentTimeMillis()));
		zfry.setS3(0);
	    xzcfXwtzDao.updateInfo(zfry);
	}

	/**
	 * 获得文书编号
	 * @return
	 */
	public AQZF_SetNumberEntity findWsbh() {
		AQZF_SetNumberEntity a = aqzfSetNumberDao.findInfor();
		return a;
	}

	/**
	 * 查找符合word的数据
	 * @param id
	 * @return
	 */
	public Map<String, Object> findWord(Long id) {
		return xzcfXwtzDao.findWord(id);
	}
	
	public Map<String, Object> findWordByLaId(Long laid) {
		return xzcfXwtzDao.findWordByLaId(laid);
	}
	
	public XZCF_EnquiryNoteEntity findWordByLaId2(Long laid) {
		return xzcfXwtzDao.findWordByLaId2(laid);
	}

	/**
	 * 根据立案id查找询问通知的数据
	 * @param id
	 * @return
	 */
	public Map<String, Object> findById3(Long id) {
		return xzcfXwtzDao.findById3(id);
	}
	
	
	/**
	 * 修改临时的通知书信息
	 * @param xwtz
	 */
	public String updateTempTzs(XZCF_EnquiryNoteEntity xwtz){
		//修改前id
		Timestamp t = DateUtils.getSysTimestamp();
		long qyidnew=xwtz.getID2();
		long qyidold=  xzcfXwtzDao.findInfoById(xwtz.getID()).getID2();
		String datasuccess="success";
		ShiroUser su = UserUtil.getCurrentShiroUser();
		xwtz.setID1(su.getId());
		xwtz.setS2(t);
		xzcfXwtzDao.updateInfo(xwtz);
		XZCF_YbcfLaspEntity lasp=xzcfcommonlaspdao.findInfoById(xwtz.getID3());
        if("1".equals(lasp.getTempflag())){
        	//判断修改前后的企业id是否一致
        	if(qyidnew!=qyidold){
        		//修改立案审批的企业相关信息
        		BIS_EnterpriseEntity bis= bisqyjbxxdao.findInfoById(qyidnew);
        		lasp.setDsperson(bis.getM1());
        		lasp.setContact(bis.getM6());
        		lasp.setYbcode(bis.getM9());
        		lasp.setDsaddress(bis.getM8());
        		lasp.setLegalperson(bis.getM5());
        		lasp.setId1(qyidnew);
        	}
        		lasp.setS2(t);
        		lasp.setAyname(xwtz.getM1());
        	
        	xzcfcommonlaspdao.updateInfo(lasp);
		}
		return datasuccess;
	}

	/**
	 * 获取文书导出word
	 */
	public Map<String, Object> getWsdcword(Long id, String flag) {
		Map<String, Object> mapret;
		AQZF_SetBasicInfoEntity sbe = setbasicdao.findInfor();
		if("la".equals(flag))
		mapret= xzcfXwtzDao.findWordByLaId(id);
		else
		mapret= xzcfXwtzDao.findWord(id);
		Map<String, Object> map=new HashMap<String, Object>();
		//解析询问时间
		if(mapret.get("m2")!=null&&!mapret.get("m2").toString().equals("")){
			String a = mapret.get("m2").toString();
			String[] as1 = a.substring(0,10).split("-");
			map.put("year", as1[0]);
			map.put("month", as1[1]);
			map.put("day", as1[2]);
			String[] bs1 = a.substring(11,16).split(":");
			map.put("hour", bs1[0]);
		}else{
			map.put("year", "   ");
			map.put("month", "   ");
			map.put("day", "   ");
			map.put("hour", "   ");
		}
		map.put("m0", mapret.get("m0")==null||mapret.get("m0").toString().equals("")?"":mapret.get("m0").toString());
		map.put("address", sbe.getAddress()==null?"":sbe.getAddress());
		if(mapret.get("cfdxlx").equals("2")) {
			map.put("qyname",mapret.get("cfryname"));
		}else {
			map.put("qyname",mapret.get("qyname")==null||mapret.get("qyname").toString().equals("")?"         ":mapret.get("qyname").toString());
		}
		map.put("m1", mapret.get("m1")==null||mapret.get("m1").toString().equals("")?"":mapret.get("m1").toString());
		map.put("m3", mapret.get("m3")==null||mapret.get("m3").toString().equals("")?"                 ":mapret.get("m3").toString());
		map.put("m4", mapret.get("m4")==null||mapret.get("m4").toString().equals("")?"":mapret.get("m4").toString());
		map.put("m6", mapret.get("m6")==null?"":mapret.get("m6").toString());
		map.put("m7", mapret.get("m7")==null?"":mapret.get("m7").toString());
		map.put("ssqmc", sbe.getSsqmc()==null?"":sbe.getSsqmc());
		String html="<w:sym w:font=\"Wingdings 2\" w:char=\"0052\"/>";
		String html2="<w:t>□</w:t>";
		if(mapret.get("m8")!=null&&!mapret.get("m8").toString().equals("")){
			String m8=mapret.get("m8").toString();
			//,,法定代表人身份证明或者
			String [] s= m8.split(",");
			map.put("sfz",html2);
			for(String str:s){
				if(str.equals("身份证"))
					map.put("sfz",html);
			}
			if(m8.contains("营业执照"))
				map.put("yyzz",html);
			else
				map.put("yyzz",html2);
			if(m8.contains("委托书"))
				map.put("wts",html);
			else
				map.put("wts",html2);
			if(StringUtils.isNotBlank(mapret.get("m4").toString()))
				map.put("qt",html);
			else
				map.put("qt",html2);
		}else{
			map.put("sfz",html2);
			map.put("yyzz",html2);
			map.put("wts",html2);
			map.put("qt",html2);
		}
		return map;
	}
	
}
