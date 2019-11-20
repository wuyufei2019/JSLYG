package com.cczu.aqzf.model.controller;

import com.cczu.aqzf.model.entity.*;
import com.cczu.aqzf.model.service.*;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.utils.UserUtil;
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
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * 行政处罚-一般处罚-预立案审批controller
 */
@Controller
@RequestMapping("xzcf/ybcf/ylasp")
public class PageXzcfCommonYlaspController extends BaseController {

	@Autowired
	private XzcfYlaspService xzcfYlaspService;
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
	private AqzfSetNumberService setNumberService;
	@Autowired
	private IXzcfCommonLaspService punishcommonlaspservice;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {
		return "aqzf/xzcf/ybcf/ylasp/index";
	}

	/**
	 * list页面
	 */
	@RequiresPermissions("ybcf:ylasp:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("name", request.getParameter("ybcf_ylasp_name"));
		map.put("qyname", request.getParameter("ybcf_ylasp_qyname"));
		map.put("casesource", request.getParameter("ybcf_ylasp_casesource"));
		map.put("startdate", request.getParameter("ybcf_ylasp_startdate"));
		map.put("enddate", request.getParameter("ybcf_ylasp_enddate"));
		map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());
		return xzcfYlaspService.dataGrid(map);
	}
	
	/**
	 * 添加信息页面跳转
	 * @param model
	 */
	@RequestMapping(value = "create")
	public String create(Model model) {
		XZCF_YbcfYlaspEntity yle = new XZCF_YbcfYlaspEntity();
		yle.setID2(0);//设置检查记录id
		model.addAttribute("yle", yle);
		model.addAttribute("action", "createSub");
		return "aqzf/xzcf/ybcf/ylasp/form";
	}

	/**
	 * 添加预立案审批
	 * @param request
	 */
	@RequestMapping(value = "createSub", method = RequestMethod.POST)
	@ResponseBody
	public String createSub(XZCF_YbcfYlaspEntity yle,String wtms,String wfxw,HttpServletRequest request, Model model) throws IOException {
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
		yle.setS1(t);
		yle.setS2(t);
		yle.setS3(0);
		yle.setYlaflag("0");
		yle.setUserid(UserUtil.getCurrentUser().getId());
		
		if (xzcfYlaspService.addInfoReturnID(yle) > 0){
			datasuccess = "success";
		}
		return datasuccess;
	}

	/**
	 * 修改信息页面跳转
	 * @param model
	 */
	@RequiresPermissions("ybcf:ylasp:update")
	@RequestMapping(value = "update/{id}")
	public String update(@PathVariable("id") String id, Model model) {
		XZCF_YbcfYlaspEntity yle = xzcfYlaspService.findInfoById(Long.parseLong(id));
		List<Map<String,Object>> list = aqzfJcnrService.findAllByids(yle.getCasecondition(),yle.getZycllx()+"");
		model.addAttribute("wfxwlist", JsonMapper.getInstance().toJson(list));
		model.addAttribute("yle", yle);
		model.addAttribute("action", "updateSub");
		return "aqzf/xzcf/ybcf/ylasp/form";
	}

	/**
	 * 修改信息
	 * @param model
	 */
	@RequiresPermissions("ybcf:ylasp:update")
	@RequestMapping(value = "updateSub")
	@ResponseBody
	public String updateSub(XZCF_YbcfYlaspEntity yle,String wtms,String wfxw,HttpServletRequest request, Model model) {
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
		yle.setS2(t);
		try {
			xzcfYlaspService.updateInfo(yle);
		} catch (Exception e) {
			// TODO: handle exception
			datasuccess="failed";
		}
		return datasuccess;
	}

	/**
	 * 查看预立案审批信息
	 * @param model
	 */
	@RequiresPermissions("ybcf:ylasp:view")
	@RequestMapping(value = "view/{id}")
	public String view(@PathVariable("id") long id, Model model) {
		XZCF_YbcfYlaspEntity yle = xzcfYlaspService.findInfoById(id);
		String ajqk = yle.getAjjbqk()==null?"":yle.getAjjbqk();
		ajqk += "经初步调查，"+yle.getDsperson()+"涉嫌存在安全生产违法行为。";
		model.addAttribute("ajqk", ajqk);
		model.addAttribute("yle", yle);
		return "aqzf/xzcf/ybcf/ylasp/view";
	}

	/**
	 * 删除预立案审批 信息
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("ybcf:ylasp:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "success";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			xzcfYlaspService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * @param id 立案
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "createlasp/{id}")
	@ResponseBody
	public String createlasp(@PathVariable("id") long id, Model model) {
		String datasuccess = "error";
		XZCF_YbcfYlaspEntity ylasp = xzcfYlaspService.findInfoById(id);
		XZCF_YbcfLaspEntity yle = new XZCF_YbcfLaspEntity();
		Timestamp t = DateUtils.getSysTimestamp();
		AQZF_SetNumberEntity bh =setNumberService.findInfor();
		AQZF_SetBasicInfoEntity asb =setbasicservice.findInfor();
		yle.setNumber("（"+asb.getSsqjc()+"）应急立〔"+ DateUtils.getYear()+"〕"+(bh.M6)+" 号");
		yle.setSdhznumber("（"+asb.getSsqjc()+"）应急回〔"+ DateUtils.getYear()+"〕"+(bh.M6)+" 号");
		bh.setM6(bh.M6+1);
		yle.setId1(ylasp.getId1());
		yle.setID2(ylasp.getID2());
		yle.setAyname(ylasp.getAyname());
		yle.setCasesource(ylasp.getCasesource());
		yle.setFilldate(t);
		yle.setCasename(ylasp.getCasename());
		yle.setDsperson(ylasp.getDsperson());
		yle.setContact(ylasp.getContact());
		yle.setLegalperson(ylasp.getLegalperson());
		yle.setDsaddress(ylasp.getDsaddress());
		yle.setYbcode(ylasp.getYbcode());
		yle.setCasecondition(ylasp.getCasecondition());
		yle.setAjjbqk(ylasp.getAjjbqk());
		yle.setOpinion(ylasp.getOpinion());
		yle.setEnforcer1(ylasp.getEnforcer1());
		yle.setIdentity1(ylasp.getIdentity1());
		yle.setEnforcer2(ylasp.getEnforcer2());
		yle.setIdentity2(ylasp.getIdentity2());
		yle.setCbsj(ylasp.getCbsj());
		yle.setFzshyj(ylasp.getFzshyj());
		yle.setFzshr(ylasp.getFzshr());
		yle.setFzshsj(ylasp.getFzshsj());
		yle.setShyj(ylasp.getShyj());
		yle.setShr(ylasp.getShr());
		yle.setShsj(ylasp.getShsj());
		yle.setSpyj(ylasp.getSpyj());
		yle.setSpsj(ylasp.getSpsj());
		yle.setSpr(ylasp.getSpr());
		yle.setUrl(ylasp.getUrl());
		yle.setFxsj(ylasp.getFxsj());
		yle.setCfdxlx(ylasp.getCfdxlx());
		yle.setZycllx(ylasp.getZycllx());
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
			//修改编号
			setNumberService.updateInfo(bh);
			xzcfYlaspService.updatezt(id);
			datasuccess = "立案成功";
		}
		return datasuccess;
	}
}
