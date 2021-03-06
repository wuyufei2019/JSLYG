package com.cczu.zxjkyj.controller;


import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.TS_Video;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.TsVideoService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.thirdapi.hikvision.entity.ThirdHikvisionDevice;
import com.cczu.thirdapi.hikvision.service.MonitorHkVideoMapsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 在线监控预警---实时视频监控controller
 * @author jason
 * @date 2017年9月6日
 */
@Controller
@RequestMapping("zxjkyj/spjk")
public class PageMonitorSpjkController extends BaseController {

	@Autowired 
	TsVideoService tsVideoService;
	@Autowired
	private IBisQyjbxxService qyjbxxService;
	@Autowired
	private MonitorHkVideoMapsService mapsService;

	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model,HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Map<String, Object> mapData=new HashMap<String, Object>();
		//根据企业名称查询
		String qyname=request.getParameter("qyname");
		if(qyname!=null){
			mapData.put("qyname", qyname);
			model.addAttribute("qyname", qyname);
		}
		
		//企业查看自己的视频监控信息
		if("1".equals(sessionuser.getUsertype())){
			BIS_EnterpriseEntity be = qyjbxxService.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1){//判断是否为集团公司
					mapData.put("fid", sessionuser.getQyid());
					List<Map<String, Object>> list=tsVideoService.findQyList(mapData);
					model.addAttribute("qylist", list);
					return "zxjkyj/ssjc/sp/index";
				}
			}
			model.addAttribute("qyid", sessionuser.getQyid());
			return "zxjkyj/ssjc/sp/show";
		}else{
			//非系统管理员只能查看自己管辖网格企业的
			if(!"9".equals(sessionuser.getUsertype())){
				mapData.put("xzqy", sessionuser.getXzqy());
			}
			List<Map<String, Object>> list=tsVideoService.findQyList(mapData);
			model.addAttribute("qylist", list);
		}
		return "zxjkyj/ssjc/sp/index";
	}

	/**
	 * 查看企业视频页面跳转
	 * @param qyid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="showqysp/{qyid}")
	public String showQYSP(@PathVariable Long qyid, Model model) {
		ThirdHikvisionDevice device = mapsService.getByQyid(qyid);
		if(device!=null){
			return "redirect:/zxjkyj/hikvision/show?rows=4&filter_EQS_deviceSerial="+device.getDeviceSerial();
		}else{
			return "redirect:/zxjkyj/spjk/showqysp/?rows=4&filter_EQL_ID1=" + qyid;
		}
	}


	/**
	 * 查看企业视频页面跳转
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping(value="showqysp")
	public String showQYSP( Model model,HttpServletRequest request) {
		Page<TS_Video> page = getPage(request);
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		tsVideoService.search(page, filters);
		model.addAttribute("page", page);
//		model.addAttribute("qyid", qyid);
		model.addAttribute("qyid", request.getParameter("filter_EQL_ID1"));
		return "zxjkyj/ssjc/sp/show";
	}
	
	@RequestMapping(value="listjson")
	@ResponseBody
	public String videoJson(String id,Model model){
//		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
//		Map<String, Object> mapData=new HashMap<String, Object>();
//		if(!"9".equals(UserUtil.getCurrentShiroUser().getUsertype())){
//			mapData.put("xzqy", sessionuser.getXzqy());
//		}
//		List<Map<String, Object>> list=tsVideoService.findByQyid(mapData);
		long qyid=0;
		if(id!=null&&!id.equals("")){
			qyid=StringUtils.toLong(id);
		}
		return tsVideoService.findByQyid(qyid);
	} 
	
	/**
	 * 企业list页面
	 * @param request
	 */
	@RequestMapping(value="qylist")
	@ResponseBody
	public  List<Map<String,Object>> getQyList(HttpServletRequest request) {
		return tsVideoService.findQyList(getAuthorityMap());
	}
	/**
	 * 企业maplist页面
	 * @param request
	 */
	@RequestMapping(value="qymaplist")
	@ResponseBody
	public  List<Map<String,Object>> getQyMapList(HttpServletRequest request,Model model) {
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		Map<String, Object> mapData = new HashMap<String, Object>();
		List<Map<String,Object>> resultList  =  new ArrayList<>();
		//企业查看自己的视频监控信息
		if (!"1".equals(sessionuser.getUsertype())) {
			List<Map<String, Object>> list = tsVideoService.findQyMapList(mapData);
			List<Map<String, Object>> devices = mapsService.listBisInfoVideo(sessionuser.getXzqy());
			resultList.addAll(list);
			resultList.addAll(devices);
		}

      	return resultList;
	}
	
	/**
	 * 根据摄像头id  查看直播视频
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="showsp/{id}")
	public String showSP(@PathVariable Long id, Model model) {
		TS_Video ts= tsVideoService.findById(id);
		model.addAttribute("video", ts);
		return "zxjkyj/ssjc/sp/showone";
	}
}

