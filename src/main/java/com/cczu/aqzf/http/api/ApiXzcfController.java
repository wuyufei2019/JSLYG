package com.cczu.aqzf.http.api;

import com.cczu.aqzf.model.entity.*;
import com.cczu.aqzf.model.service.*;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.util.common.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * app行政处罚
 * @author zpc
 * @date 2018/03/21
 */
@Controller
@RequestMapping("api/xzcf")
public class ApiXzcfController extends BaseController {
	
	@Autowired
	private IXzcfCommonLaspService punishcommonlaspservice;
	@Autowired
	private XzcfXwtzService xzcfXwtzService;
	@Autowired
	private XzcfXwblService xzcfXwblService;
	@Autowired
	private XzcfCommonDcService xzcfcommondcservice;
	@Autowired
	private XzcfDCzjService xzcfDCzjService;
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
	
	/**
	 * 立案审批list页面
	 */
	@RequestMapping(value = "lasplist")
	@ResponseBody
	public Map<String, Object> getLaspData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xzqy",request.getParameter("xzqy"));
		map.put("apptj", request.getParameter("apptj"));//搜索框条件-编号/当事人
		map.put("startdate", request.getParameter("startdate"));//起始时间
		map.put("enddate", request.getParameter("enddate"));//结束时间
		return punishcommonlaspservice.dataGrid(map);
	}
	
	/**
	 * 根据立案审批id查询详情
	 * @param id 立案审批id
	 * @return
	 */
	@RequestMapping(value = "laspview")
	@ResponseBody
	public XZCF_YbcfLaspEntity getLaspView(long id) {
		XZCF_YbcfLaspEntity lasp = punishcommonlaspservice.findInfoById(id);
		String ajqk = lasp.getAjjbqk()==null?"":lasp.getAjjbqk();
		ajqk += "经初步调查，"+lasp.getDsperson()+"涉嫌存在安全生产违法行为。";
		lasp.setAjjbqk(ajqk);
		return lasp;
	}
	
	/**
	 * 进度显示
	 * @param id 立案审批id
	 */
	@RequestMapping(value="xzcfjd")
	@ResponseBody
	public Map<String, Object> getXzcfjdData(long id) {
		Map<String,Object> map = new HashMap<>();
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
		map.put("lasp", lasp);
		map.put("xwtz", xwtz);
		map.put("xwbllist", xwbllist);
		map.put("dcbg", dcbg);
		map.put("cfgz", cfgz);
		map.put("cssb", cssb);
		map.put("tzgz", tzgz);
		map.put("ajcp", ajcp);
		map.put("jttl", jttl);
		map.put("cfjd", cfjd);
		map.put("fksp", fksp);
		map.put("fkpzlist", fkpzlist);
		map.put("sxcg", sxcg);
		map.put("qzzx", qzzx);
		map.put("jasp", jasp);
		return map;
	}
	
	/**
	 * 询问通知list页面
	 */
	@RequestMapping(value="xwtzlist")
	@ResponseBody
	public Map<String, Object> getXwtzData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xzqy",request.getParameter("xzqy"));
		map.put("apptj", request.getParameter("apptj"));//搜索框条件-编号/当事人
		map.put("m2", request.getParameter("xwsj"));//询问日期
		return xzcfXwtzService.dataGrid(map);
	}
	
	/**
	 * 查询询问通知详情
	 * @param id 
	 * @param flag（1：询问2：立案）
	 */
	@RequestMapping(value = "xwtzview")
	@ResponseBody
	public Map<String,Object> getXwtzView(long id,String flag) {
		Map<String,Object> xwtz = new HashMap<>();
		if(flag.equals("1")){
			xwtz = xzcfXwtzService.findWord(id);
		}else{
			xwtz = xzcfXwtzService.findWordByLaId(id);
		}
		return xwtz;
	}
	
	/**
	 * 询问笔录list页面
	 */
	@RequestMapping(value="xwbllist")
	@ResponseBody
	public Map<String, Object> getXwblData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xzqy",request.getParameter("xzqy"));
		map.put("apptj", request.getParameter("apptj"));//搜索框条件-编号/当事人
		map.put("m1", request.getParameter("startdate"));//开始日期
		map.put("m2", request.getParameter("enddate"));//结束日期
		return xzcfXwblService.dataGrid(map);
	}
	
	/**
	 * 查询询问笔录详情
	 * @param id 笔录id
	 */
	@RequestMapping(value = "xwblview")
	@ResponseBody
	public XZCF_InterrogationRecordEntity getXwblView(long id) {
		XZCF_InterrogationRecordEntity xwbl = xzcfXwblService.findById(id);
		if(!StringUtils.isEmpty(xwbl.getM9())){
			String[] a = xwbl.getM9().split(":");
            xwbl.setM9(a[1]);
		}
		return xwbl;
	}
	
	/**
	 * 调查报告list页面
	 */
	@RequestMapping(value="dcbglist")
	@ResponseBody
	public Map<String, Object> getDcbgData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xzqy",request.getParameter("xzqy"));
		map.put("apptj", request.getParameter("apptj"));//搜索框条件-编号/当事人
		return xzcfcommondcservice.dataGrid(map);
	}
	
	/**
	 * 查询调查报告详情
	 * @param flag（1：调查报告2：立案）
	 */
	@RequestMapping(value = "dcbgview")
	@ResponseBody
	public Map<String, Object> getDcbgView(long id,String flag) {
		Map<String, Object> map = new HashMap<>();
		XZCF_YbcfDcbgEntity dcbg = new XZCF_YbcfDcbgEntity();
		List<Map<String, Object>> zjlist = new ArrayList<>();
		if(flag.equals("1")){
			dcbg = xzcfcommondcservice.findInfoById(id);
			zjlist= xzcfDCzjService.dataGridCzwt(id);
		}else{
			dcbg = xzcfcommondcservice.findInfoByLaId(id);
			zjlist= xzcfDCzjService.dataGridCzwt(dcbg.getID());
		}
		map.put("dcbg", dcbg);
		map.put("zjlist", zjlist);
		return map;
	}
	
	/**
	 * 处罚告知list页面
	 */
	@RequestMapping(value="cfgzlist")
	@ResponseBody
	public Map<String, Object> getCfgzData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xzqy",request.getParameter("xzqy"));
		map.put("apptj", request.getParameter("apptj"));//搜索框条件-编号/当事人
		map.put("startdate", request.getParameter("startdate"));//开始日期
		map.put("enddate", request.getParameter("enddate"));//结束日期
		map.put("cftype", "1");
		return punishsimplegzservice.dataGrid(map);
	}
	
	/**
	 * 查询处罚告知详情
	 * @param flag（1：处罚告知2：立案）
	 */
	@RequestMapping(value = "cfgzview")
	@ResponseBody
	public XZCF_GzsInfoEntity getCfgzView(long id, String flag) {
		XZCF_GzsInfoEntity cfgz = new XZCF_GzsInfoEntity();
		if(flag.equals("1")){
			cfgz = punishsimplegzservice.findInfoById(id);
		}else{
			cfgz = punishsimplegzservice.findInfoByLaId(id);
		}
		return cfgz;
	}
	
	/**
	 * 陈述申辩list页面
	 */
	@RequestMapping(value="cssblist")
	@ResponseBody
	public Map<String, Object> getCssbData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xzqy",request.getParameter("xzqy"));
		map.put("apptj", request.getParameter("apptj"));//搜索框条件-编号/案件名称
		return xzcfCssbblService.dataGrid(map);
	}
	
	/**
	 * 查询陈述申辩详情
	 * @param flag（1：陈述申辩2：立案）
	 */
	@RequestMapping(value = "cssbview")
	@ResponseBody
	public XZCF_CssbblEntity getCssbView(long id, String flag) {
		XZCF_CssbblEntity cssbbl = new XZCF_CssbblEntity();
		if(flag.equals("1")){
			cssbbl = xzcfCssbblService.findById(id);
		}else{
			cssbbl = xzcfCssbblService.findWordByLaId(id);
		}
		return cssbbl;
	}
	
	/**
	 * 听证告知list页面
	 */
	@RequestMapping(value="tzgzlist")
	@ResponseBody
	public Map<String, Object> getTzgzData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xzqy",request.getParameter("xzqy"));
		map.put("apptj", request.getParameter("apptj"));//搜索框条件-编号/当事人
		map.put("startdate", request.getParameter("startdate"));//开始日期
		map.put("enddate", request.getParameter("enddate"));//结束日期
		return xzcfcommontzservice.dataGrid(map);
	}
	
	/**
	 * 查询听证告知详情
	 * @param flag（1：听证告知2：立案）
	 */
	@RequestMapping(value = "tzgzview")
	@ResponseBody
	public XZCF_YbcfTzgzEntity getTzgzView(long id, String flag) {
		XZCF_YbcfTzgzEntity tzgz = new XZCF_YbcfTzgzEntity();
		if(flag.equals("1")){
			tzgz = xzcfcommontzservice.findInfoById(id);
		}else{
			tzgz = xzcfcommontzservice.findInfoByLaId(id);
		}
		return tzgz;
	}
	
	/**
	 * 案件呈批list页面
	 */
	@RequestMapping(value="ajcplist")
	@ResponseBody
	public Map<String, Object> getAjcpData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xzqy",request.getParameter("xzqy"));
		map.put("apptj", request.getParameter("apptj"));//搜索框条件-编号/当事人
		return xzcfcommonajcpservice.dataGrid(map);
	}
	
	/**
	 * 查询案件呈批详情
	 * @param flag（1：案件呈批2：立案）
	 */
	@RequestMapping(value = "ajcpview")
	@ResponseBody
	public XZCF_YbcfAjcpEntity getAjcpView(long id, String flag) {
		XZCF_YbcfAjcpEntity ajcp = new XZCF_YbcfAjcpEntity();
		if(flag.equals("1")){
			ajcp = xzcfcommonajcpservice.findInfoById(id);
		}else{
			ajcp = xzcfcommonajcpservice.findInfoByLaId(id);
		}
		return ajcp;
	}
	
	/**
	 * 集体讨论list页面
	 */
	@RequestMapping(value="jttllist")
	@ResponseBody
	public Map<String, Object> getJttlData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xzqy",request.getParameter("xzqy"));
		map.put("apptj", request.getParameter("apptj"));//搜索框条件-编号/集体讨论
		return xzcfJttlService.dataGrid(map);
	}
	
	/**
	 * 查询集体讨论详情
	 * @param flag（1：集体讨论2：立案）
	 */
	@RequestMapping(value = "jttlview")
	@ResponseBody
	public XZCF_JttlEntity getJttlView(long id, String flag) {
		XZCF_JttlEntity jttl = new XZCF_JttlEntity();
		if(flag.equals("1")){
			jttl = xzcfJttlService.findById(id);
		}else{
			jttl = xzcfJttlService.findWordByLaId(id);
		}
		return jttl;
	}
	
	/**
	 * 处罚决定list页面
	 */
	@RequestMapping(value="cfjdlist")
	@ResponseBody
	public Map<String, Object> getCfjdData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xzqy",request.getParameter("xzqy"));
		map.put("apptj", request.getParameter("apptj"));//搜索框条件-编号/当事人
		map.put("startdate", request.getParameter("startdate"));//开始日期
		map.put("enddate", request.getParameter("enddate"));//结束日期
		map.put("cftype", "1");
		return punishsimplecfjdservice.dataGrid(map);
	}
	
	/**
	 * 查询处罚决定详情
	 * @param flag（1：处罚决定2：立案）
	 */
	@RequestMapping(value = "cfjdview")
	@ResponseBody
	public XZCF_CfjdInfoEntity getCfjdView(long id, String flag) {
		XZCF_CfjdInfoEntity cfjd = new XZCF_CfjdInfoEntity();
		if(flag.equals("1")){
			cfjd = punishsimplecfjdservice.findInfoById(id);
		}else{
			cfjd = punishsimplecfjdservice.findInfoByLaId(id);
		}
		return cfjd;
	}
	
	/**
	 * 缴款审批list页面
	 */
	@RequestMapping(value="jksplist")
	@ResponseBody
	public Map<String, Object> getJkspData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xzqy",request.getParameter("xzqy"));
		map.put("apptj", request.getParameter("apptj"));//搜索框条件-编号/案件名称
		return xzcfFkspService.dataGrid(map);
	}
	
	/**
	 * 查询缴款审批详情
	 * @param flag（1：缴款审批2：立案）
	 */
	@RequestMapping(value = "jkspview")
	@ResponseBody
	public XZCF_FkspEntity getJkspView(long id,String flag) {
		XZCF_FkspEntity fksp = new XZCF_FkspEntity();
		if(flag.equals("1")){
			fksp = xzcfFkspService.findById(id);
		}else{
			fksp = xzcfFkspService.findWordByLaId(id);
		}
		return fksp;
	}
	
	/**
	 * 缴款批准list页面
	 */
	@RequestMapping(value="jkpzlist")
	@ResponseBody
	public Map<String, Object> getJkpzData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xzqy",request.getParameter("xzqy"));
		map.put("apptj", request.getParameter("apptj"));//搜索框条件-编号/当事人
		map.put("m5",request.getParameter("type"));//类型1:延期2：分期
		return xzcfFkpzService.dataGrid(map);
	}
	
	/**
	 * 查询缴款批准详情
	 */
	@RequestMapping(value = "jkpzview")
	@ResponseBody
	public XZCF_FkpzEntity getJkpzView(long id) {
		XZCF_FkpzEntity fkpz = xzcfFkpzService.findById(id);
		return fkpz;
	}
	
	/**
	 * 事先催告list页面
	 */
	@RequestMapping(value="sxcglist")
	@ResponseBody
	public Map<String, Object> getSxcgData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xzqy",request.getParameter("xzqy"));
		map.put("apptj", request.getParameter("apptj"));//搜索框条件-编号/当事人
		return xzcfcommonsxcgservice.dataGrid(map);
	}
	
	/**
	 * 查询事先催告详情
	 */
	@RequestMapping(value = "sxcgview")
	@ResponseBody
	public XZCF_YbcfSxcgEntity getSxcgView(long id) {
		XZCF_YbcfSxcgEntity sxcg = xzcfcommonsxcgservice.findInfoById(id);
		return sxcg;
	}
	
	/**
	 * 强制执行list页面
	 */
	@RequestMapping(value="qzzxlist")
	@ResponseBody
	public Map<String, Object> getQzzxData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xzqy",request.getParameter("xzqy"));
		map.put("apptj", request.getParameter("apptj"));//搜索框条件-编号/当事人
		return xzcfcommonqzzxservice.dataGrid(map);
	}
	
	/**
	 * 查询强制执行详情
	 */
	@RequestMapping(value = "qzzxview")
	@ResponseBody
	public XZCF_YbcfQzzxEntity getQzzxView(long id) {
		XZCF_YbcfQzzxEntity qzzx = xzcfcommonqzzxservice.findInfoById(id);
		return qzzx;
	}
	
	/**
	 * 结案审批list页面
	 */
	@RequestMapping(value="jasplist")
	@ResponseBody
	public Map<String, Object> getJaspData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xzqy",request.getParameter("xzqy"));
		map.put("apptj", request.getParameter("apptj"));//搜索框条件-编号/当事人
		return XzcfCommonJaspService.dataGrid(map);
	}
	
	/**
	 * 查询结案审批详情
	 * @param flag（1：结案审批2：立案）
	 */
	@RequestMapping(value = "jaspview")
	@ResponseBody
	public XZCF_YbcfJaspEntity getJaspView(long id, String flag) {
		XZCF_YbcfJaspEntity jasp = new XZCF_YbcfJaspEntity();
		if(flag.equals("1")){
			jasp = XzcfCommonJaspService.findInfoById(id);
		}else{
			jasp = XzcfCommonJaspService.findInfoByLaId(id);
		}
		return jasp;
	}
}
