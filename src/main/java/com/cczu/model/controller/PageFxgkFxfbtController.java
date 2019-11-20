package com.cczu.model.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cczu.sys.system.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.FxgkFxxxService;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.system.entity.Barrio;
import com.cczu.sys.system.service.BarrioService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
/**
 * 风险管控-风险分布图controller
 * @author jason
 * @date 2017年6月23日
 */
@Controller
@RequestMapping("fxgk/fxfb")
public class PageFxgkFxfbtController extends BaseController {
	@Autowired
	private BarrioService barrioservice;
	@Autowired
	private FxgkFxxxService fxgkFxxxService;
	@Autowired
	private BisQyjbxxServiceImpl bisQyjbxxService;

	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					model.addAttribute("type", "2");
				else
					model.addAttribute("type", "1");
			}
		}else {//非企业用户页面
			model.addAttribute("type", "2");
		}	
		if(UserUtil.getCurrentShiroUser().getUsertype().equals("1")){
			long qyid = UserUtil.getCurrentShiroUser().getQyid();
			//企业平面图
			BIS_EnterpriseEntity bis = bisQyjbxxService.findInfoById(qyid);
			model.addAttribute("bis", bis);
			//风险点信息
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("qyid", qyid);
			List<Map<String,Object>> list = fxgkFxxxService.getAllByQyid(map);
			model.addAttribute("fxdlist", JsonMapper.getInstance().toJson(list));
			return "fxgk/fxyt/mapindex";
		}else{
			Barrio bro=barrioservice.findPointBycode(UserUtil.getCurrentShiroUser().getXzqy());
			if(bro!=null)
			model.addAttribute("mappoint", bro.getMappoint());
			return "fxgk/fxfbt/mapindex";
		}
	}
	
	/**
	 * list页面
	 */
	@RequestMapping(value="fxdslist")
	@ResponseBody
	public Map<String, Object> getFxdslist(HttpServletRequest request) {
		Map<String,Object> mapdate = new HashMap<>();
		Map<String,Object> map = new HashMap<>();
		long qyid = UserUtil.getCurrentShiroUser().getQyid();
		map.put("m1", request.getParameter("keyword"));
		map.put("qyid", qyid);
		List<Map<String,Object>> list = fxgkFxxxService.getAllByQyid(map);
		mapdate.put("fxdlist", JsonMapper.getInstance().toJson(list));
//		//企业平面图
//		BIS_EnterpriseEntity bis = bisQyjbxxService.findInfoById(qyid);
//		mapdate.put("pmt", bis.getM33_3());
		return mapdate;
	}
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="timemap")
	public String timemap(Model model) {
		Barrio bro=barrioservice.findPointBycode(UserUtil.getCurrentShiroUser().getXzqy());
		if(bro!=null)
			model.addAttribute("mappoint", bro.getMappoint());
		return "fxgk/fxfbt/timemapindex";
	}
	/**
	 * 企业云图
	 */
	@RequestMapping(value="showfxyt")
	@ResponseBody
	public String showQyLocation(Model model) {
		Map<String, Object> mapdata=getAuthorityMap();
		return JsonMapper.getInstance().toJson(fxgkFxxxService.getQyfxzJson(mapdata));
	}
	/**
	 * 重点重大（风险云图用）
	 */
	@RequestMapping(value="showzdzd")
	@ResponseBody
	public String showZdzdJson(Model model) {
			Map<String, Object> mapdata = getAuthorityMap();
			List<Map<String,Object>> list=bisQyjbxxService.findZdzdMapList(mapdata);
			return JsonMapper.getInstance().toJson(list);
		}
	/**
	 * 重点重大（风险云图用）
	 */
	@RequestMapping(value="barrocolor")
	@ResponseBody
	public String showBarrioJson(Model model) {
		Map<String, Object> mapdata = getAuthorityMap();
		return JsonMapper.getInstance().toJson(fxgkFxxxService.getBarriofxzJson(mapdata));
	}
	
	/**
	 * 获取企业名称集合
	 * @return
	 */
	@RequestMapping(value="qynamelist")
	@ResponseBody
	public List<BIS_EnterpriseEntity> getQynameList() {
		Map<String, Object> map = new HashMap<>();
		ShiroUser shiroUser = UserUtil.getCurrentShiroUser();
		List<BIS_EnterpriseEntity> list;
		if(User.USERTYPE.QY.getUsertype().equals(shiroUser.getUsertype())){
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(shiroUser.getQyid());
			map.put("fid", be.getID());
			 list= bisQyjbxxService.dataListE(map);
		}else {
			map.putAll(getAuthorityMap());
			list = (List<BIS_EnterpriseEntity>)bisQyjbxxService.findAllQyList(map).get("rows");
		}

		return list;
	}
	
	
	/**
	 * 根据企业id获取对应的风险平面图
	 * @return
	 */
	@RequestMapping(value="fxpmt/{qyid}")
	@ResponseBody
	public String getfxpmtByQyid(@PathVariable(value="qyid") Long qyid) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", qyid);
		List<BIS_EnterpriseEntity> list = bisQyjbxxService.dataListE(map);
		String fxpmtUrl = "";
		if (list.size() > 0) {
			BIS_EnterpriseEntity be = list.get(0);
			fxpmtUrl = be.getM33_4();//风险平面图
		}
		return fxpmtUrl;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
