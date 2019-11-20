package com.cczu.aqzf.http.api;

import com.cczu.aqzf.model.dao.AqzfJcjlDao;
import com.cczu.aqzf.model.entity.*;
import com.cczu.aqzf.model.service.*;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * app监督检查
 * @author zpc
 * @date 2018/03/22
 */
@Controller
@RequestMapping("api/jdjc")
public class ApiJdjcController extends BaseController {
	
	@Autowired
	private IAqzfJcjhService AqzfJcjhService;
	@Autowired
	private AqzfJcfaService aqzfJcfaService;
	@Autowired
	private AqzfJcjlService aqzfJcjlService;
	@Autowired
	private AqzfClcsService aqzfClcsService;
	@Autowired
	private AqzfJcnrService aqzfJcnrService;
	@Autowired
	private AqzfZlzgService aqzfZlzgService;
	@Autowired
	private AqzfFcyjService aqzfFcyjService;
	@Resource
	private AqzfJcjlDao aqzfJcjlDao;
	@Autowired
	private AqzfJdjctjService aqzfJdjctjService;
	@Autowired
	private AqzfSetBasicInfoService setbasicservice;
	@Autowired
	private AqzfWfxwService aqzfWfxwService;
	@Autowired
	private AqzfZfryService aqzfZfryService;
	
	/**
	 * 统计list页面
	 */
	@RequestMapping(value = "jctjlist")
	@ResponseBody
	public Map<String, Object> getJctjData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xzqy",request.getParameter("xzqy"));
		map.put("qyname", request.getParameter("apptj"));
		String jhsj = request.getParameter("jhsj");
		if(jhsj!=null && jhsj!=""){
			try {
				Date date = new SimpleDateFormat("yyyy-MM").parse(jhsj);
				map.put("year",date.getYear()+1900);
				map.put("month",(date.getMonth()+1)+"月");
			} catch (ParseException e) {
				map.put("year","");
				map.put("month","");
			}
		}
		return aqzfJdjctjService.dataGrid(map);
	}
	
	/**
	 * 监督检查进度详情页面
	 */
	@RequestMapping(value = "jctjjd")
	@ResponseBody
	public Map<String, Object> getJctjjdData(long id) {
		Map<String, Object> mapall = new HashMap<>();
		List<Map<String, Object>> jcjhlist = AqzfJcjhService.findById(id);
		Map<String, Object> jcjh = jcjhlist.get(0);
		AQZF_SafetyCheckSchemeEntity jcfa = aqzfJcfaService.findJcfaByjhqyid(jcjh.get("jhid").toString(),jcjh.get("qyid").toString());
		mapall.put("jcjh", jcjh);
		mapall.put("jcfa", jcfa);
		mapall.put("jcjl", null);
		mapall.put("clcs", null);
		mapall.put("zlzg", null);
		mapall.put("fcyj", null);
		if(jcfa != null){
			AQZF_SafetyCheckRecordEntity jcjl = aqzfJcjlService.findJcjlByfaid(jcfa.getID());
			mapall.put("jcjl", jcjl);
			if(jcjl != null){
				AQZF_TreatmentEntity clcs = aqzfClcsService.findByjlid(jcjl.getID());
				mapall.put("clcs", clcs);
				AQZF_ReformEntity zlzg = aqzfZlzgService.findInfoById1(jcjl.getID());
				mapall.put("zlzg", zlzg);
				if(zlzg != null){
					AQZF_ReviewEntity fcyj = aqzfFcyjService.findInfoById1(zlzg.getID());
					mapall.put("fcyj", fcyj);
				}
			}
		}
		return mapall;
	}
	
	/**
	 * 检查计划list页面
	 */
	@RequestMapping(value = "jcjhlist")
	@ResponseBody
	public Map<String, Object> getJcjhData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xzqy",request.getParameter("xzqy"));
		map.put("qyname", request.getParameter("apptj"));
		String jhsj = request.getParameter("jhsj");
		if(jhsj!=null && jhsj!=""){
			try {
				Date date = new SimpleDateFormat("yyyy-MM").parse(jhsj);
				map.put("year",date.getYear()+1900);
				map.put("month",(date.getMonth()+1)+"月");
			} catch (ParseException e) {
				map.put("year","");
				map.put("month","");
			}
		}
		return AqzfJcjhService.dataGrid(map);
	}
	
	/**
	 * 检查方案list页面
	 */
	@RequestMapping(value = "jcfalist")
	@ResponseBody
	public Map<String, Object> getJcfaData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xzqy",request.getParameter("xzqy"));
		map.put("cz", request.getParameter("type"));
		map.put("qyname", request.getParameter("apptj"));
		String jhsj = request.getParameter("jhsj");
		if(jhsj!=null && jhsj!=""){
			try {
				Date date = new SimpleDateFormat("yyyy-MM").parse(jhsj);
				map.put("year",date.getYear()+1900);
				map.put("month",(date.getMonth()+1)+"月");
			} catch (ParseException e) {
				map.put("year","");
				map.put("month","");
			}
		}
		return aqzfJcfaService.dataGrid(map);
	}
	
	/**
	 * 根据检查方案id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "jcfaview")
	@ResponseBody
	public Map<String, Object> getJcfaView(long id) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> jcfa= aqzfJcfaService.findById(id);
		map.put("jcfa", jcfa);
		map.put("jcnrlist", aqzfJcfaService.getJcnrForApp(id));
		return map;
	}
	
	/**
	 * 检查记录list页面
	 */
	@RequestMapping(value = "jcjllist")
	@ResponseBody
	public Map<String, Object> getJcjlData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xzqy",request.getParameter("xzqy"));
		map.put("apptj", request.getParameter("apptj"));
		return aqzfJcjlService.dataGrid(map);
	}
	
	/**
	 * 根据检查记录id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "jcjlview")
	@ResponseBody
	public Map<String, Object> getJcjlView(long id) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> jcjl= aqzfJcjlService.findById(id);
		map.put("jcjl", jcjl);
		List<Map<String,Object>> jcnrlist=aqzfJcjlDao.dataGridNr2(id+"");
		map.put("jcnrlist", jcnrlist);
		List<Map<String, Object>> czwtlist = aqzfJcjlService.dataGridCzwt(id);
		map.put("czwtlist", czwtlist);
		return map;
	}
	
	/**
	 * 现场处理list页面
	 */
	@RequestMapping(value = "clcslist")
	@ResponseBody
	public Map<String, Object> getClcsData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xzqy",request.getParameter("xzqy"));
		map.put("apptj", request.getParameter("apptj"));
		return aqzfClcsService.dataGrid(map);
	}
	
	/**
	 * 根据现场处理id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "clcsview")
	@ResponseBody
	public Map<String, Object> getClcsView(long id) {
		Map<String, Object> hmap = new HashMap<>();
		Map<String, Object> clcs = aqzfClcsService.findInforById(id);
		//事故
		String sgyh = "";
		if(clcs.get("m2")!=null&&!StringUtils.isEmpty(clcs.get("m2").toString())){
			String wfxwids = clcs.get("m2").toString();
			List<Map<String,Object>> list = aqzfJcnrService.findAllByids(wfxwids,clcs.get("m10")+"");
			int i = 1;
			for (Map<String, Object> map : list) {
				sgyh += i+". "+(map.get("wtms")==null?"":map.get("wtms").toString())+"<br/>&nbsp;&nbsp;&nbsp;&nbsp;涉嫌违法行为："+(map.get("m1")==null?"":map.get("m1").toString())+"<br/>";
				i++;
			}
		}
		hmap.put("sgyh", sgyh);
		hmap.put("clcs", clcs);
		return hmap;
	}
	
	/**
	 * 责令整改list页面
	 */
	@RequestMapping(value = "zlzglist")
	@ResponseBody
	public Map<String, Object> getZlzgData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xzqy",request.getParameter("xzqy"));
		map.put("apptj", request.getParameter("apptj"));
		return aqzfZlzgService.dataGrid(map);
	}
	
	/**
	 * 根据责令整改id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "zlzgview")
	@ResponseBody
	public Map<String, Object> getZlzgView(long id) {
		Map<String, Object> hmap = new HashMap<>();
		Map<String, Object> zlzg = aqzfZlzgService.findInforById(id);
		//存在问题
		String sgyh = "";
		if(zlzg.get("m2")!=null&&!StringUtils.isEmpty(zlzg.get("m2").toString())){
			String wfxwids = zlzg.get("m2").toString();
			List<Map<String,Object>> list = aqzfJcnrService.findAllByids(wfxwids,zlzg.get("m10")+"");
			int i = 1;
			for (Map<String, Object> map : list) {
				sgyh += i+". "+(map.get("wtms")==null?"":map.get("wtms").toString())+"<br/>&nbsp;&nbsp;&nbsp;&nbsp;涉嫌违法行为："+(map.get("m1")==null?"":map.get("m1").toString())+"<br/>";
				i++;
			}
		}
		hmap.put("czwt", sgyh);
		hmap.put("zlzg", zlzg);
		return hmap;
	}
	
	/**
	 * 复查意见list页面
	 */
	@RequestMapping(value = "fcyjlist")
	@ResponseBody
	public Map<String, Object> getFcyjData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xzqy",request.getParameter("xzqy"));
		map.put("apptj", request.getParameter("apptj"));
		return aqzfFcyjService.dataGrid(map);
	}
	
	/**
	 * 根据复查意见id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "fcyjview")
	@ResponseBody
	public Map<String, Object> getFcyjView(long id) {
		Map<String, Object> fcyj = aqzfFcyjService.findInforById(id);
		return fcyj;
	}
	
	
	/************************************添加操作************************************/
	/**
	 * 添加将查记录页面跳转
	 */
	@RequestMapping(value = "jcjladdindex")
	@ResponseBody
	public Map<String, Object> jcjladdindex(long id) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> jcfa=aqzfJcfaService.findById(id);
		map.put("jcfa", jcfa);//传检查方案id和企业信息过去
		Map<String, Object> jcjl=new HashMap<>();
		jcjl.put("m8", jcfa.get("m5"));
		map.put("jcjl", jcjl);
		return map;
	}
	
	/**
	 * 获取检查记录检查内容
	 * id 方案ID
	 */
	@RequestMapping(value = "jcnrlist")
	@ResponseBody
	public List<Map<String, Object>> getjcnrlist(long id) {
		return aqzfJcfaService.getJcnrForApp(id);
	}
	
	/**
	 * 添加检查记录信息
	 * @param request,model
	 */
	@RequestMapping(value = "addjcjl")
	@ResponseBody
	public String addjcjl(AQZF_SafetyCheckRecordEntity jcjl, String qtwtnum, String xzqy, HttpServletRequest request) {
		String datasuccess="success";	
		//获取内容id数组并split
		String nr=request.getParameter("nrid");
		String[] nrid=nr.split(",");
		//获取文书编号
		AQZF_SetBasicInfoEntity bh =setbasicservice.findInfor();
		Map<String, Object> jcfa=aqzfJcfaService.findById(jcjl.getID1());
		Calendar a=Calendar.getInstance();
		String year=a.get(Calendar.YEAR)+"";
		String m0 = jcfa.get("m0").toString();
		jcjl.setM0("（"+bh.getSsqjc()+"）应急检记〔"+year+"〕"+m0.substring(m0.indexOf("〕")+1, m0.length()));
		Long jcjlid=aqzfJcjlService.addInfoReturnID(jcjl);//添加检查记录并返回id
		aqzfJcjlService.updateState(jcjl.getID1());//修改检查方案的检查状态
		//循环获取检查内容及状态
		if(!StringUtils.isEmpty(nr)){
			for(int i=1;i<=nrid.length;i++){
				AQZF_SafetyCheckContentEntity jcnr=new AQZF_SafetyCheckContentEntity();
				String jcjg=request.getParameter("ra_"+nrid[i-1]);//获取检查结果（1是，0否）
				String jcnrid=request.getParameter("jcnrid_"+nrid[i-1]);//获取检查内容id
				String wtms = "";
				String jcwt = "";
				String url = "";
				if(jcjg.equals("1")){
					jcnr.setM1(1);//符合
				}else{
					jcnr.setM1(0);//不符合
					wtms=request.getParameter("wtms_"+nrid[i-1]);//获取问题描述
					wtms = wtms.replace(",", "，");
					jcwt=request.getParameter("jcwt_"+nrid[i-1]);//获取违法行为
					url=request.getParameter("url_"+nrid[i-1]);//获取地址
				}	
				jcnr.setID1(jcjlid);//检查记录id
				jcnr.setID2(Long.parseLong(jcnrid));//检查内容id
				jcnr.setM3(wtms);//问题描述
				jcnr.setM2(jcwt);//存在问题
				jcnr.setM4(url);//图片地址
				aqzfJcnrService.addInfo(jcnr);//检查检查内容
			}
		}
		
		//获取存在的其他问题
		if(!qtwtnum.equals("0")){
			String[] aids = qtwtnum.split(",");
			
			for(int i=1;i<aids.length;i++){
				String qtwtms=request.getParameter("qtwtms_"+aids[i]);
				String czwt=request.getParameter("czwt_"+aids[i]);
				String czwturl=request.getParameter("czwturl_"+aids[i]);
				
				AQZF_SafetyCheckContentEntity wt=new AQZF_SafetyCheckContentEntity();
				wt.setID1(jcjlid);//检查记录id
				wt.setID2(0);//检查内容id
				wt.setM1(0);//存在问题
				wt.setM2(czwt);//违法行为
				wt.setM3(qtwtms);//问题描述
				wt.setM4(czwturl);//图片地址
				aqzfJcnrService.addInfo(wt);//检查内容
			}
		}
		
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 违法行为list下拉填充 id，text（违法行为）
	 * @param model
	 * @return
	 */
	@RequestMapping(value="wfxwidlist")
	@ResponseBody
	public List<Map<String, Object>> getWfxwIdlist(String m1,Model model) {
		Map<String,Object> map = new HashMap<>();
		map.put("m1", m1);
		return aqzfWfxwService.getWfxwIdlistForH5(map);
	}
	
	/**
	 * 添加处理措施页面跳转
	 * id 检查记录id
	 */
	@RequestMapping(value = "clcsaddindex")
	@ResponseBody
	public Map<String, Object> clcsaddindex(long id,String xzqy) {
		Map<String, Object> map = new HashMap<>();
		AQZF_TreatmentEntity clcs = new AQZF_TreatmentEntity();
		clcs.setID3(id);
		//设置政府法院
		AQZF_SetBasicInfoEntity sbe=setbasicservice.findInfor();
		clcs.setM5(sbe.getGov()==null?"":sbe.getGov());
		clcs.setM6(sbe.getCourt()==null?"":sbe.getCourt());
		//根据检查记录id查找检查记录
		AQZF_SafetyCheckRecordEntity jcjl=aqzfJcjlService.findjl(id);
		clcs.setID2(jcjl.getID2());//设置企业id
		clcs.setM9(jcjl.getM2());//负责人
		clcs.setM1(DateUtils.getSysTimestamp());
		
		//存在问题
		List<Map<String,Object>> list = aqzfJcnrService.findAllByJlid(id,clcs.getM10()+"");
		map.put("wfxwlist", JsonMapper.getInstance().toJson(list));
		
		map.put("clcs", clcs);
		return map;
	}
	
	/**
	 * 执法人员list下拉填充 id，text。。。
	 * @param model
	 * @return
	 */
	@RequestMapping(value="zfryidlist")
	@ResponseBody
	public List<Map<String, Object>> getZfryIdlist(String m1,String xzqy,Model model) {
		Map<String,Object> map = new HashMap<>();
		map.put("xzqy", xzqy);
		map.put("xm", m1);
		return aqzfZfryService.getZfryIdlistForH5(map);
	}
	
	/**
	 * 添加现场处理信息
	 * @param xzqy,model
	 */
	@RequestMapping(value = "addclcs")
	@ResponseBody
	public String addclcs(AQZF_TreatmentEntity entity, String xzqy, long userid) {
		String datasuccess="success";	
		//获取文书编号
		AQZF_SetBasicInfoEntity bh =setbasicservice.findInfor();
		AQZF_SafetyCheckRecordEntity jcjl=aqzfJcjlService.findjl(entity.getID3());
		Calendar a=Calendar.getInstance();
		String year=a.get(Calendar.YEAR)+"";
		String m0 = jcjl.getM0();
		entity.setM0("（"+bh.getSsqjc()+"）应急现决〔"+year+"〕"+m0.substring(m0.indexOf("〕")+1, m0.length()));
		
		Timestamp t= DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		entity.setID1(userid);
		aqzfClcsService.addInfo(entity);
		
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 责令整改页面跳转
	 * id 检查记录id
	 */
	@RequestMapping(value = "zlzgaddindex")
	@ResponseBody
	public Map<String, Object> zlzgaddindex(long id,String xzqy) {
		Map<String, Object> map = new HashMap<>();
		AQZF_ReformEntity zlzg=new AQZF_ReformEntity();
		//根据检查记录id查找检查记录
		AQZF_SafetyCheckRecordEntity jcjl=aqzfJcjlService.findjl(id);
		
		//设置政府法院
		AQZF_SetBasicInfoEntity sbe=setbasicservice.findInfor();
		zlzg.setM4(sbe.getGov()==null?"":sbe.getGov());
		zlzg.setM5(sbe.getCourt()==null?"":sbe.getCourt());
		zlzg.setM8(jcjl.getM2());//负责人
		zlzg.setID2(jcjl.getID2());//企业id
		zlzg.setID1(jcjl.getID());//检查记录id
		zlzg.setM1(jcjl.getM6());//检查日期
		//存在问题
		List<Map<String,Object>> list = aqzfJcnrService.findAllByJlid(id,jcjl.getM15()+"");
		map.put("wfxwlist", JsonMapper.getInstance().toJson(list));
		map.put("zlzg",zlzg);
		return map;
	}
	
	/**
	 * 添加责令整改
	 * @param xzqy
	 */
	@RequestMapping(value = "addzlzg")
	@ResponseBody
	public String addzlzg(AQZF_ReformEntity entity, String xzqy) {
		String datasuccess="success";	
		//获取文书编号
		AQZF_SetBasicInfoEntity bh =setbasicservice.findInfor();
		AQZF_SafetyCheckRecordEntity jcjl=aqzfJcjlService.findjl(entity.getID1());
		Calendar a=Calendar.getInstance();
		String year=a.get(Calendar.YEAR)+"";
		String m0 = jcjl.getM0();
		entity.setM0("（"+bh.getSsqjc()+"）应急责改〔"+year+"〕"+m0.substring(m0.indexOf("〕")+1, m0.length()));
		
		Timestamp t= DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		aqzfZlzgService.addInfo(entity);
		
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 复查意见页面跳转
	 * id 检查记录id
	 */
	@RequestMapping(value = "fcyjaddindex")
	@ResponseBody
	public AQZF_ReviewEntity fcyjaddindex(long id) {
		AQZF_ReformEntity zlzg = aqzfZlzgService.findById(id);
		
		AQZF_ReviewEntity fcyj = new AQZF_ReviewEntity();
		fcyj.setID1(zlzg.getID());//责令整改id
		fcyj.setID2(zlzg.getID2());//被检查单位id
		fcyj.setM5(zlzg.getM8());//负责人
		
		//存在问题
		String czwt = "";
		if(!StringUtils.isEmpty(zlzg.getM2())){
			String wfxwids = zlzg.getM2();
			List<Map<String,Object>> list = aqzfJcnrService.findAllByids(wfxwids,zlzg.getM10()+"");
			int i = 1;
			for (Map<String, Object> map : list) {
				czwt += i+". "+(map.get("wtms")==null?"":map.get("wtms").toString())+"  ";
				i++;
			}
		}
		fcyj.setM2(czwt);
		fcyj.setM3_1(zlzg.getM6_1());//执法人员1
		fcyj.setM3_2(zlzg.getM6_2());//执法人员2
		fcyj.setM4_1(zlzg.getM7_1());//执法人员证号1
		fcyj.setM4_2(zlzg.getM7_2());//执法人员证号2
		
		return fcyj;
	}
	
	/**
	 * 添加复查意见
	 * @param xzqy
	 */
	@RequestMapping(value = "addfcyj")
	@ResponseBody
	public String addfcyj(AQZF_ReviewEntity entity, String xzqy) {
		String datasuccess="success";	
		//获取文书编号
		AQZF_SetBasicInfoEntity bh =setbasicservice.findInfor();
		AQZF_ReformEntity zlzg = aqzfZlzgService.findById(entity.getID1());
		Calendar a=Calendar.getInstance();
		String year=a.get(Calendar.YEAR)+"";
		String m0 = zlzg.getM0();
		entity.setM0("（"+bh.getSsqjc()+"）应急复查〔"+year+"〕"+m0.substring(m0.indexOf("〕")+1, m0.length()));
		
		Timestamp t= DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		aqzfFcyjService.addInfo(entity);
		
		//返回结果
		return datasuccess;
	}
}
