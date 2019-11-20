package com.cczu.aqzf.model.controller;

import com.cczu.aqzf.model.entity.*;
import com.cczu.aqzf.model.service.*;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.UserService;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 行政处罚-一般处罚-立案审批controller
 * 
 * @author jason
 * 
 */
@Controller
@RequestMapping("xzcf/ybcf/lasp")
public class PageXzcfCommonLaspController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	@Autowired
	private IXzcfCommonLaspService punishcommonlaspservice;
	@Autowired
	private AqzfSetNumberService setNumberService;
	@Autowired
	private AqzfJcjlService aqzfJcjlService;
	@Autowired
	private AqzfJcnrService aqzfJcnrService;
	@Autowired
	private AqzfWfxwService aqzfWfxwService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private AqzfSetBasicInfoService setbasicservice;
	@Autowired
	private XzcfSgxwtzService xzcfSgxwtzService;
	@Autowired
	private XzcfXwtzService xzcfXwtzService;
	@Autowired
	private XzcfSgxwblService xzcfSgxwblService;
	@Autowired
	private XzcfXwblService xzcfXwblService;
	@Autowired
	private XzcfCommonDcService xzcfcommondcservice;
	@Autowired
	private IPunishSimpleGzService punishsimplegzservice;
	@Autowired
	private XzcfCssbblService xzcfCssbblService;
	@Autowired
	private IXzcfCommonTzService xzcfcommontzservice;
	@Autowired
	private IXzcfCommonAjcpService xzcfcommonajcpservice;
	@Autowired
	private XzcfJttlService xzcfJttlService;
	@Autowired
	private IXzcfCfjdService punishsimplecfjdservice;
	@Autowired
	private XzcfFkspService xzcfFkspService;
	@Autowired
	private XzcfFkpzService xzcfFkpzService;
	@Autowired
	private XzcfCommonSxcgService xzcfcommonsxcgservice;
	@Autowired
	private XzcfCommonQzzxService xzcfcommonqzzxservice;
	@Autowired
	private XzcfCommonJaspService XzcfCommonJaspService;
	@Autowired
	private AqzfZfryService aqzfZfryService;

	/**
	 * 查看案件进度
	 * @param model
	 */
	@RequiresPermissions("ybcf:lasp:view")
	@RequestMapping(value = "viewjd/{id}")
	public String viewjd(@PathVariable("id") long id, Model model) {
		XZCF_YbcfLaspEntity lasp = punishcommonlaspservice.findInfoById(id);//立案审批
		Map<String, Object> xwtz = xzcfXwtzService.findWordByLaId(id);//询问通知
		List<XZCF_InterrogationRecordEntity> xwbllist = xzcfXwblService.findByLaid(id);//询问笔录（多条）
		XZCF_YbcfDcbgEntity dcbg = xzcfcommondcservice.findInfoByLaId(id);//调查报告
		XZCF_GzsInfoEntity cfgz = punishsimplegzservice.findInfoByLaId(id);//处罚告知
		XZCF_CssbblEntity cssb = xzcfCssbblService.findWordByLaId(id);//陈述申辩
		XZCF_YbcfTzgzEntity tzgz = xzcfcommontzservice.findInfoByLaId(id);//听证告知
		XZCF_YbcfAjcpEntity ajcp = xzcfcommonajcpservice.findInfoByLaId(id);//案件呈批
		XZCF_JttlEntity jttl = xzcfJttlService.findWordByLaId(id);//集体讨论
		XZCF_CfjdInfoEntity cfjd = punishsimplecfjdservice.findInfoByLaId(id);//处罚决定
		XZCF_FkspEntity fksp = xzcfFkspService.findWordByLaId(id);//缴纳罚款审批
		List<XZCF_FkpzEntity> fkpzlist = xzcfFkpzService.findWordByLaId(id);//缴纳罚款批准（多条）
		XZCF_YbcfSxcgEntity sxcg = xzcfcommonsxcgservice.findInfoByLaId(id);//事先催告
		XZCF_YbcfQzzxEntity qzzx = xzcfcommonqzzxservice.findInfoByLaId(id);//强制执行
		XZCF_YbcfJaspEntity jasp = XzcfCommonJaspService.findInfoByLaId(id);//结案审批
		model.addAttribute("lasp", lasp);
		model.addAttribute("xwtz", xwtz);
		model.addAttribute("xwbllist", xwbllist);
		model.addAttribute("dcbg", dcbg);
		model.addAttribute("cfgz", cfgz);
		model.addAttribute("cssb", cssb);
		model.addAttribute("tzgz", tzgz);
		model.addAttribute("ajcp", ajcp);
		model.addAttribute("jttl", jttl);
		model.addAttribute("cfjd", cfjd);
		model.addAttribute("fksp", fksp);
		model.addAttribute("fkpzlist", fkpzlist);
		model.addAttribute("sxcg", sxcg);
		model.addAttribute("qzzx", qzzx);
		model.addAttribute("jasp", jasp);
		return "aqzf/xzcf/ybcf/lasp/viewjd";
	}
	
	/**
	 * 预立案改为真立案
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "updateLabh/{id}")
	@ResponseBody
	public String updateLabh(@PathVariable("id") Long id) {
		String datasuccess = "success";
		AQZF_SetNumberEntity bh =setNumberService.findInfor();
		AQZF_SetBasicInfoEntity asb =setbasicservice.findInfor();
		
		XZCF_YbcfLaspEntity lasp = punishcommonlaspservice.findInfoById(id);//立案审批
		lasp.setNumber("（"+asb.getSsqjc()+"）应急立〔"+ DateUtils.getYear()+"〕"+(bh.M6)+" 号");
		lasp.setSdhznumber("（"+asb.getSsqjc()+"）应急回〔"+ DateUtils.getYear()+"〕"+(bh.M6)+" 号");
		lasp.setCxflag("1");
		punishcommonlaspservice.updateInfo(lasp);
		
		XZCF_EnquiryNoteEntity xwtz = xzcfXwtzService.findWordByLaId2(id);//询问通知
		if(xwtz!=null){
			xwtz.setM0("（"+asb.getSsqjc()+"）应急询〔"+ DateUtils.getYear()+"〕"+(bh.M6)+" 号");
			xzcfXwtzService.updateInfo(xwtz);
		}
		
		XZCF_YbcfDcbgEntity dcbg = xzcfcommondcservice.findInfoByLaId(id);//调查报告
		if(dcbg!=null){
			dcbg.setBgbh("（"+asb.getSsqjc()+"）应急终报〔"+ DateUtils.getYear()+"〕"+(bh.M6)+" 号");
			xzcfcommondcservice.updateInfo(dcbg);
		}
		
		XZCF_GzsInfoEntity cfgz = punishsimplegzservice.findInfoByLaId(id);//处罚告知
		if(cfgz!=null){
			cfgz.setNumber("（"+asb.getSsqjc()+"）应急罚告〔"+ DateUtils.getYear()+"〕"+(bh.M6)+" 号");
			punishsimplegzservice.updateInfo(cfgz);
		}
		
		XZCF_YbcfTzgzEntity tzgz = xzcfcommontzservice.findInfoByLaId(id);//听证告知
		if(tzgz!=null){
			tzgz.setNumber("（"+asb.getSsqjc()+"）应急听告〔"+ DateUtils.getYear()+"〕"+(bh.M6)+" 号");
			xzcfcommontzservice.updateInfo(tzgz);
		}
		
		XZCF_YbcfAjcpEntity ajcp = xzcfcommonajcpservice.findInfoByLaId(id);//案件呈批
		if(ajcp!=null){
			ajcp.setNumber("（"+asb.getSsqjc()+"）应急处呈〔"+ DateUtils.getYear()+"〕"+(bh.M6)+" 号");
			xzcfcommonajcpservice.updateInfo(ajcp);
		}
		
		XZCF_JttlEntity jttl = xzcfJttlService.findWordByLaId(id);//集体讨论
		if(jttl!=null){
			jttl.setM1("（"+asb.getSsqjc()+"）应急罚集〔"+ DateUtils.getYear()+"〕"+(bh.M6)+" 号");
			xzcfJttlService.updateInfo(jttl);
		}
		
		XZCF_CfjdInfoEntity cfjd = punishsimplecfjdservice.findInfoByLaId(id);//处罚决定
		if(cfjd!=null){
			cfjd.setNumber("（"+asb.getSsqjc()+"）应急罚〔"+ DateUtils.getYear()+"〕"+(bh.M6)+" 号");
			punishsimplecfjdservice.updateInfo(cfjd);
		}
		
		XZCF_FkspEntity fksp = xzcfFkspService.findWordByLaId(id);//缴纳罚款审批
		if(fksp!=null){
			fksp.setM1("（"+asb.getSsqjc()+"）应急缴审〔"+ DateUtils.getYear()+"〕"+(bh.M6)+" 号");
			xzcfFkspService.updateInfo(fksp);
		}
		
		List<XZCF_FkpzEntity> fkpzlist = xzcfFkpzService.findWordByLaId(id);//缴纳罚款批准（多条）
		if(fkpzlist.size()>0){
			for (XZCF_FkpzEntity fkpz : fkpzlist) {
				fkpz.setM1("（"+asb.getSsqjc()+"）应急缴批〔"+ DateUtils.getYear()+"〕"+(bh.M6)+" 号");
				xzcfFkpzService.updateInfo(fkpz);
			}
		}
		
		XZCF_YbcfSxcgEntity sxcg = xzcfcommonsxcgservice.findInfoByLaId(id);//事先催告
		if(sxcg!=null){
			sxcg.setNumber("（"+asb.getSsqjc()+"）应急执行催告〔"+ DateUtils.getYear()+"〕"+(bh.M6)+" 号");
			xzcfcommonsxcgservice.updateInfo(sxcg);
		}
		
		XZCF_YbcfQzzxEntity qzzx = xzcfcommonqzzxservice.findInfoByLaId(id);//强制执行
		if(qzzx!=null){
			qzzx.setNumber("（"+asb.getSsqjc()+"）应急强执〔"+ DateUtils.getYear()+"〕"+(bh.M6)+" 号");
			xzcfcommonqzzxservice.updateInfo(qzzx);
		}
		
		XZCF_YbcfJaspEntity jasp = XzcfCommonJaspService.findInfoByLaId(id);//结案审批
		if(jasp!=null){
			jasp.setNumber("（"+asb.getSsqjc()+"）应急结〔"+ DateUtils.getYear()+"〕"+(bh.M6)+" 号");
			XzcfCommonJaspService.updateInfo(jasp);
		}
		
		bh.setM6(bh.M6+1);
		//修改编号
		setNumberService.updateInfo(bh);
		return datasuccess;
	}
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());
		int count=punishcommonlaspservice.getTempCount(map);
		model.addAttribute("count",count);
		return "aqzf/xzcf/ybcf/lasp/index";
	}

	/**
	 * list页面
	 */
	@RequiresPermissions("ybcf:lasp:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("number", request.getParameter("ybcf_lasp_number"));
		map.put("name", request.getParameter("ybcf_lasp_name"));
		map.put("qyname", request.getParameter("ybcf_lasp_qyname"));
		map.put("casesource", request.getParameter("ybcf_lasp_casesource"));
		map.put("startdate", request.getParameter("ybcf_lasp_startdate"));
		map.put("enddate", request.getParameter("ybcf_lasp_enddate"));
		map.put("type", request.getParameter("ybcf_lasp_type"));
		map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());
		return punishcommonlaspservice.dataGrid(map);
	}
	
	/**
	 * 添加信息页面跳转
	 * @param model
	 */
	@RequestMapping(value = "create/{version}")
	public String create(@PathVariable("version") String version, Model model) {
		XZCF_YbcfLaspEntity yle = new XZCF_YbcfLaspEntity();
		yle.setID2(0);//设置检查记录id
		yle.setZycllx(version);
		model.addAttribute("yle", yle);
		model.addAttribute("action", "createSub");
		return "aqzf/xzcf/ybcf/lasp/form";
	}
	
	/**
	 * 事故添加信息页面跳转
	 * @param model
	 */
	@RequestMapping(value = "create3")
	public String create3(Model model) {
		XZCF_YbcfLaspEntity yle = new XZCF_YbcfLaspEntity();
		yle.setID2(0);//设置检查记录id
		model.addAttribute("yle", yle);
		model.addAttribute("action", "createsg");
		return "aqzf/xzcf/ybcf/lasp/sgform";
	}
	
	/**
	 * 检查记录添加立案审批跳转
	 * @param id 检查记录id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "create2/{id}")
	public String create2(@PathVariable("id") Long  id, Model model) {
		//根据检查记录id查找检查记录
		AQZF_SafetyCheckRecordEntity jcjl=aqzfJcjlService.findjl(id);
		String jcsj = DateUtils.formatDate(jcjl.getM6(), "yyyy年MM月dd日");
		model.addAttribute("jcsj", jcsj);
		
		//存在问题
		List<Map<String,Object>> list = aqzfJcnrService.findAllByJlid(id,jcjl.getM15()+"");
		model.addAttribute("wfxwlist", JsonMapper.getInstance().toJson(list));
		
		//设置基本信息
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(jcjl.getID2());
		XZCF_YbcfLaspEntity yle = new XZCF_YbcfLaspEntity();
		yle.setID2(id);//设置检查记录id
		yle.setId1(jcjl.getID2());//设置企业id
		yle.setContact(jcjl.getM4());//设置联系电话
		yle.setDsaddress(jcjl.getM1());//设置地址
		yle.setLegalperson(jcjl.getM2());//设置法人
		yle.setDsperson(be.getM1());//设置企业名
		yle.setYbcode(be.getM9());//设置邮编

		//设置执法人员
		if(!StringUtils.isBlank(jcjl.getM8())){
			String []zfry = jcjl.getM8().split(",");
			if(zfry.length>1){
				AQZF_TipstaffEntity xzry1=aqzfZfryService.findBym1(zfry[0]);
				if(xzry1!=null){
					yle.setEnforcer1(xzry1.getM1());
					yle.setIdentity1(xzry1.getM3());
				}
				AQZF_TipstaffEntity xzry2=aqzfZfryService.findBym1(zfry[1]);
				if(xzry2!=null){
					yle.setEnforcer2(xzry2.getM1());
					yle.setIdentity2(xzry2.getM3());
				}
			}else{
				AQZF_TipstaffEntity xzry=aqzfZfryService.findBym1(zfry[0]);
				if(xzry!=null){
					yle.setEnforcer1(xzry.getM1());
					yle.setIdentity1(xzry.getM3());
				}
			}
		}
		model.addAttribute("version",jcjl.getM15());//自由裁量
		model.addAttribute("yle", yle);
		
		model.addAttribute("action", "createSub");
		return "aqzf/xzcf/ybcf/lasp/form2";
	}

	/**
	 * 添加立案审批
	 * @param request
	 */
	@RequestMapping(value = "createSub", method = RequestMethod.POST)
	@ResponseBody
	public String createSub(XZCF_YbcfLaspEntity yle, String wtms, String wfxw, HttpServletRequest request, Model model) throws IOException {
		String datasuccess = "failed";
		
		String casecondition = "";
		//添加问题描述违法行为表
		if(!StringUtils.isEmpty(wtms)){
			String[] wtms2=wtms.split(",");//问题描述数组
			String[] wfxw2=wfxw.split(",");//违法行为数组
			for(int i=0;i<wtms2.length;i++){
				AQZF_SafetyCheckContentEntity wt=new AQZF_SafetyCheckContentEntity();
				wt.setID1(0);//检查记录id
				wt.setID2(0);//检查内容id
				wt.setM1(0);//存在问题
				wt.setM2(wfxw2[i]);//违法行为
				wt.setM3(wtms2[i]);//问题描述
				wt.setM6(yle.getZycllx());//自由裁量类型
				aqzfJcnrService.addInfo(wt);//检查检查内容
				if(i==0){
					casecondition += wt.getID();
				}else{
					casecondition += ","+wt.getID();
				}
			}
			yle.setCasecondition(casecondition);
		}
		
		Timestamp t = DateUtils.getSysTimestamp();
		yle.setNumber("预立案");
		yle.setSdhznumber("预立案");
		
		//bh.setM6(bh.M6+1);
		yle.setS1(t);
		yle.setS2(t);
		yle.setS3(0);
		yle.setXwflag("0");
		yle.setCbflag("0");
		yle.setGzflag("0");
		yle.setTzflag("0");
		yle.setCfjdflag("0");
		yle.setTempflag("0");
		yle.setDcflag("0");
		yle.setCgflag("0");
		yle.setQzflag("0");
		yle.setJaflag("0");
		yle.setSbflag("0");
		yle.setTlflag("0");
		yle.setFkspflag("0");
		yle.setCxflag("0");
		yle.setUserid(UserUtil.getCurrentUser().getId());
		
	
		if (punishcommonlaspservice.addInfoReturnID(yle) > 0){
			//修改检查记录状态
			if(yle.getID2()!=0){
				//根据检查记录id查找检查记录
				AQZF_SafetyCheckRecordEntity jcjl=aqzfJcjlService.findjl(yle.getID2());
				jcjl.setM14("1");
			    aqzfJcjlService.updateInfo(jcjl);
			}
			datasuccess = "success";
		}
		return datasuccess;
	}
	
	/**
	 * 添加事故立案审批
	 * @param request
	 */
	@RequestMapping(value = "createsg", method = RequestMethod.POST)
	@ResponseBody
	public String createsg(XZCF_YbcfLaspEntity yle, String wtms, String wfxw, HttpServletRequest request, Model model) throws IOException {
		String datasuccess = "failed";
		
		String casecondition = "";
		//添加问题描述违法行为表
		if(!StringUtils.isEmpty(wtms)){
			String[] wtms2=wtms.split(",");//问题描述数组
			String[] wfxw2=wfxw.split(",");//违法行为数组
			for(int i=0;i<wtms2.length;i++){
				AQZF_SafetyCheckContentEntity wt=new AQZF_SafetyCheckContentEntity();
				wt.setID1(0);//检查记录id
				wt.setID2(0);//检查内容id
				wt.setM1(0);//存在问题
				wt.setM2(wfxw2[i]);//违法行为
				wt.setM3(wtms2[i]);//问题描述
				aqzfJcnrService.addInfo(wt);//检查检查内容
				if(i==0){
					casecondition += wt.getID();
				}else{
					casecondition += ","+wt.getID();
				}
			}
			yle.setCasecondition(casecondition);
		}
		
		Timestamp t = DateUtils.getSysTimestamp();
		AQZF_SetNumberEntity bh =setNumberService.findInfor();
		AQZF_SetBasicInfoEntity asb =setbasicservice.findInfor();
		yle.setNumber("（"+asb.getSsqjc()+"）应急立〔"+ DateUtils.getYear()+"〕"+(bh.M6)+" 号");
		yle.setSdhznumber("（"+asb.getSsqjc()+"）应急回〔"+ DateUtils.getYear()+"〕"+(bh.M6)+" 号");
		bh.setM6(bh.M6+1);
		yle.setS1(t);
		yle.setS2(t);
		yle.setS3(0);
		yle.setXwflag("0");
		yle.setCbflag("0");
		yle.setGzflag("0");
		yle.setTzflag("0");
		yle.setCfjdflag("0");
		yle.setTempflag("0");
		yle.setDcflag("0");
		yle.setCgflag("0");
		yle.setQzflag("0");
		yle.setJaflag("0");
		yle.setSbflag("0");
		yle.setTlflag("0");
		yle.setFkspflag("0");
		yle.setCxflag("1");
		yle.setUserid(UserUtil.getCurrentUser().getId());
		
	
		if (punishcommonlaspservice.addInfoReturnID(yle) > 0){
			//修改编号
			setNumberService.updateInfo(bh);
			
			if(!StringUtils.isEmpty(yle.getSgtzid())){
				//关联询问通知
				XZCF_SgxwtzEntity sgxwtz  = xzcfSgxwtzService.findById(Long.parseLong(yle.getSgtzid()));
				XZCF_EnquiryNoteEntity xwtz = new XZCF_EnquiryNoteEntity();
				xwtz.setID1(UserUtil.getCurrentUser().getId());//操作人id
				xwtz.setID2(sgxwtz.getID2());//企业id
				xwtz.setID3(yle.getID());//立案审批id
				//检查编号
				String m0 = yle.getNumber();
				xwtz.setM0("（"+asb.getSsqjc()+"）应急询〔"+ DateUtils.getYear()+"〕"+m0.substring(m0.indexOf("〕")+1, m0.length()));
				xwtz.setM1(sgxwtz.getM1());//调查主题
				xwtz.setM2(sgxwtz.getM2());//询问时间
				xwtz.setM3(sgxwtz.getM3());//询问地址
				xwtz.setM4(sgxwtz.getM4());//额外证件材料
				xwtz.setM8(sgxwtz.getM5());//材料
				xwtz.setM6(sgxwtz.getM6());
				xwtz.setM7(sgxwtz.getM7());
				xzcfXwtzService.addInfo(xwtz);
				
				//修改状态
				yle.setXwflag("1");
				punishcommonlaspservice.updateInfo(yle);
				
				//关联询问笔录
				List<XZCF_SgxwblEntity> sgbllist = xzcfSgxwblService.findByTzid(Long.parseLong(yle.getSgtzid()));
				for (XZCF_SgxwblEntity sgxwbl : sgbllist) {
					XZCF_InterrogationRecordEntity xwbl = new XZCF_InterrogationRecordEntity();
					xwbl.setID1(UserUtil.getCurrentUser().getId());
					xwbl.setID2(yle.getID());
					xwbl.setM0(sgxwbl.getM0());
					xwbl.setM1(sgxwbl.getM1());
					xwbl.setM2(sgxwbl.getM2());
					xwbl.setM3(sgxwbl.getM3());
					xwbl.setM4(sgxwbl.getM4());
					xwbl.setM5(sgxwbl.getM5());
					xwbl.setM6(sgxwbl.getM6());
					xwbl.setM7(sgxwbl.getM7());
					xwbl.setM8(sgxwbl.getM8());
					xwbl.setM9(sgxwbl.getM9());
					xwbl.setM10(sgxwbl.getM10());
					xwbl.setM11(sgxwbl.getM11());
					xwbl.setM12(sgxwbl.getM12());
					xwbl.setM13(sgxwbl.getM13());
					xwbl.setM14(sgxwbl.getM14());
					xwbl.setM15(sgxwbl.getM15());
					xwbl.setM17(sgxwbl.getM16());
					xwbl.setM16(sgxwtz.getM1());
					xzcfXwblService.addInfo(xwbl);
				}
			}
			
			xzcfSgxwtzService.updateZt(yle.getSgtzid());
			datasuccess = "success";
		}
		return datasuccess;
	}

	/**
	 * 修改信息页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("ybcf:lasp:update")
	@RequestMapping(value = "update/{id}")
	public String update(@PathVariable("id") String  id, Model model) {
		XZCF_YbcfLaspEntity yle;
		if(!id.equals("temp")){
			 yle = punishcommonlaspservice.findInfoById(Long.parseLong(id));
			 if(yle.getID2()!=0){
				 //跟检查记录有关联
				 model.addAttribute("version",yle.getZycllx());
				 model.addAttribute("yle", yle);
				 model.addAttribute("action", "updateSub2");
				 
				//根据检查记录id查找检查记录
				AQZF_SafetyCheckRecordEntity jcjl=aqzfJcjlService.findjl(yle.getID2());
				String jcsj = DateUtils.formatDate(jcjl.getM6(), "yyyy年MM月dd日");
				model.addAttribute("jcsj", jcsj); 
				 
				//存在问题
				List<Map<String,Object>> list = aqzfJcnrService.findAllByJlid(yle.getID2(),jcjl.getM15()+"");
				model.addAttribute("wfxwlist", JsonMapper.getInstance().toJson(list));
					
				return "aqzf/xzcf/ybcf/lasp/form2";
			 }else{
				List<Map<String,Object>> list = aqzfJcnrService.findAllByids(yle.getCasecondition(),yle.getZycllx()+"");
				model.addAttribute("wfxwlist", JsonMapper.getInstance().toJson(list));
			 }
		}else{
			 yle =punishcommonlaspservice.findTempInfo(UserUtil.getCurrentShiroUser().getXzqy());
			 model.addAttribute("wfxwlist", JsonMapper.getInstance().toJson(null));
		}
		model.addAttribute("yle", yle);
		model.addAttribute("action", "updateSub");
		return "aqzf/xzcf/ybcf/lasp/form";
	}

	/**
	 * 修改信息1
	 * @param model
	 */
	@RequiresPermissions("ybcf:lasp:update")
	@RequestMapping(value = "updateSub")
	@ResponseBody
	public String updateSub(XZCF_YbcfLaspEntity yle, String wtms, String wfxw, HttpServletRequest request, Model model) {
		String datasuccess = "success";
		if(!StringUtils.isEmpty(yle.getCasecondition())){
			aqzfJcnrService.deleteByids(yle.getCasecondition());
		}
		//添加问题描述违法行为表
		String casecondition = "";
		if(!StringUtils.isEmpty(wtms)){
			String[] wtms2=wtms.split(",");//问题描述数组
			String[] wfxw2=wfxw.split(",");//违法行为数组
			for(int i=0;i<wtms2.length;i++){
				AQZF_SafetyCheckContentEntity wt=new AQZF_SafetyCheckContentEntity();
				wt.setID1(0);//检查记录id
				wt.setID2(0);//检查内容id
				wt.setM1(0);//存在问题
				wt.setM2(wfxw2[i]);//违法行为
				wt.setM3(wtms2[i]);//问题描述
				aqzfJcnrService.addInfo(wt);//检查检查内容
				if(i==0){
					casecondition += wt.getID();
				}else{
					casecondition += ","+wt.getID();
				}
			}
		}
		yle.setCasecondition(casecondition);
		Timestamp t = DateUtils.getSysTimestamp();
		if("1".equals(yle.getTempflag())){
			yle.setTempflag("2");
		}
		yle.setS2(t);
		try {
			punishcommonlaspservice.updateInfo(yle);
		} catch (Exception e) {
			// TODO: handle exception
			datasuccess="failed";
		}
		return datasuccess;
	}
	
	/**
	 * 修改信息2
	 * @param model
	 */
	@RequiresPermissions("ybcf:lasp:update")
	@RequestMapping(value = "updateSub2")
	@ResponseBody
	public String updateSub2(XZCF_YbcfLaspEntity yle, HttpServletRequest request, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		yle.setS2(t);
		try {
			punishcommonlaspservice.updateInfo(yle);
		} catch (Exception e) {
			// TODO: handle exception
			datasuccess="failed";
		}
		return datasuccess;
	}

	/**
	 * 查看立案审批信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("ybcf:lasp:view")
	@RequestMapping(value = "view/{id}")
	public String view(@PathVariable("id") long id, Model model) {
		XZCF_YbcfLaspEntity yle = punishcommonlaspservice.findInfoById(id);
		String ajqk = yle.getAjjbqk()==null?"":yle.getAjjbqk();
		String cfdx = "";
		if(yle.getCfdxlx().equals("2"))
			cfdx = yle.getCfryname();
		else
			cfdx = yle.getDsperson();
		ajqk += "经初步调查，"+cfdx+"涉嫌存在安全生产违法行为。";
		model.addAttribute("ajqk", ajqk);
		
		model.addAttribute("yle", yle);
		return "aqzf/xzcf/ybcf/lasp/view";
	}

	/**
	 * 删除立案审批 信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("ybcf:lasp:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "success";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			XZCF_YbcfLaspEntity yle = punishcommonlaspservice.findInfoById(Long.parseLong(arrids[i]));
			punishcommonlaspservice.deleteInfo(Long.parseLong(arrids[i]));
			if(yle.getID2()!=0){
				//与检查记录有关,根据检查记录id查找检查记录
				AQZF_SafetyCheckRecordEntity jcjl=aqzfJcjlService.findjl(yle.getID2());
				jcjl.setM14("0");
			    aqzfJcjlService.updateInfo(jcjl);
			}
		}
		return datasuccess;
	}
	
	/**
	 * 获取所有和立案审批相关的number记录
	 * @param id
	 * @return
	 */
	@RequestMapping(value="numberlist/{id}")
	@ResponseBody
	public List<Map<String, Object>> getNumberlist(@PathVariable("id") Long id) {
		List<Map<String, Object>> list=null;
		try {
			list = punishcommonlaspservice.getNumberlist(id, UserUtil.getCurrentShiroUser().getXzqy());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 导出立案审批记录word
	 * 
	 */
	@RequiresPermissions("ybcf:lasp:export")
	@RequestMapping(value = "exportlasp/{id}")
	@ResponseBody
	public String getAjWord(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = punishcommonlaspservice.getWsdcword(id);
		//设置导出的文件名
		//String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		String filename = map.get("number").toString() + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "lasprecord.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
	/**
	 * 导出送达回执word
	 * 
	 */
	@RequiresPermissions("ybcf:lasp:export")
	@RequestMapping(value = "exportsdhz/{id}")
	@ResponseBody
	public String getSdhzWord(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = punishcommonlaspservice.getWsdcsdhzword(id);
		//设置导出的文件名
		String filename = map.get("number").toString() + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "xzcfsdhz.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
	/**
	 * 导出文书界面跳转
	 * @param model
	 */
	@RequiresPermissions("ybcf:lasp:export")
	@RequestMapping(value = "exportws/{id}")
	public String exportws(@PathVariable("id") long id, Model model) {
		XZCF_YbcfLaspEntity lasp = punishcommonlaspservice.findInfoById(id);
		model.addAttribute("lasp", lasp);
		return "aqzf/xzcf/ybcf/lasp/dcwsview";
	}
	
	/**
	 * 导出卷宗界面跳转
	 * @param model
	 */
	@RequiresPermissions("ybcf:lasp:export")
	@RequestMapping(value = "exportjzindex/{id}")
	public String exportjzindex(@PathVariable("id") Long id, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("larq", new Timestamp(System.currentTimeMillis()));
		model.addAttribute("jarq", new Timestamp(System.currentTimeMillis()));
		model.addAttribute("gdrq", new Timestamp(System.currentTimeMillis()));
		return "aqzf/xzcf/ybcf/lasp/jzform";
	}
	
	/**
	 * 导出卷宗word
	 */
	@RequiresPermissions("ybcf:lasp:export")
	@RequestMapping(value = "exportjz")
	@ResponseBody
	public String getJzWord(Long id,Timestamp larq,Timestamp jarq,Timestamp gdrq,String gdh,String bcqx,HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = punishcommonlaspservice.getJzword(id);
		
		map.put("larq", DateUtils.formatDate(larq, "yyyy年MM月dd日"));
		map.put("jarq", DateUtils.formatDate(jarq, "yyyy年MM月dd日"));
		map.put("gdrq", DateUtils.formatDate(gdrq, "yyyy年MM月dd日"));
		map.put("gdh", gdh);
		map.put("bcqx", bcqx);

		//设置导出的文件名
		String filename = map.get("jzbh") + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "jzrecord.ftl", filePath, filename, request);
		
		return "/download/" + filename;
	}
	
	/**
	 * @param fileList 合并多个word文档内容
	 * @param savepaths 合并保存新文档
	 */
	public static void mergeWordDataDoc(List<String> fileList, String savepaths) {  
        if (fileList.size() == 0 || fileList == null) {  
            return;  
        }
        // 判断文件是否存在
        if( new File(savepaths).exists() ){
        	try {
        		new File(savepaths).createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
        }else{
        	try {
        		new File(savepaths).delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        //打开word  
        ActiveXComponent app = new ActiveXComponent("Word.Application"); 
        try {  
            // 设置word不可见  
            app.setProperty("Visible", new Variant(false));  
            //获得documents对象  
            Object docs = app.getProperty("Documents").toDispatch();  
            //打开第一个文件  
            Object doc = Dispatch  
                .invoke(  
                        (Dispatch) docs,  
                        "Open",  
                        Dispatch.Method,  
                        new Object[] { (String) fileList.get(0),  
                                new Variant(false), new Variant(true) },  
                        new int[3]).toDispatch();  
            //追加文件  
            for (int i = 1; i < fileList.size(); i++) {  
                Dispatch.invoke(app.getProperty("Selection").toDispatch(),  
                    "insertFile", Dispatch.Method, new Object[] {  
                            (String) fileList.get(i), "",  
                            new Variant(false), new Variant(false),  
                            new Variant(false) }, new int[3]);  
            }  
            //保存新的word文件  
            Dispatch.invoke((Dispatch) doc, "SaveAs", Dispatch.Method, new Object[] { savepaths, new Variant(1) }, new int[3]);  
            Variant f = new Variant(false);  
            Dispatch.call((Dispatch) doc, "Close", f);  
        } catch (Exception e) {  
            System.out.println("合并word文件出错.原因:" + e);  
        } finally {  
            app.invoke("Quit", new Variant[] {});  
        }  
    } 
}
