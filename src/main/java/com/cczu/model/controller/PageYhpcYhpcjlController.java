package com.cczu.model.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.YhpcYhpcjlService;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;


/**
 * 隐患排查记录Controller
 * @author YZH
 */
@Controller
@RequestMapping("yhpc/yhpcjl")
public class PageYhpcYhpcjlController extends BaseController {

	@Autowired
	private YhpcYhpcjlService yhpcYhpcjlService;
	@Autowired
	private BisQyjbxxServiceImpl qyjbxxServiceImpl;
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model,HttpServletRequest request) {
		if(request.getParameter("sys")!=null &&"1".equals(request.getParameter("sys"))){
			model.addAttribute("sys", "sys");
			if(request.getParameter("mflag")!=null &&(request.getParameter("mflag"))!="")
				model.addAttribute("cljd", request.getParameter("mflag"));
		}
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					model.addAttribute("type","2");//集团公司
				else
					model.addAttribute("type","1");//子公司
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			model.addAttribute("type","2");//其他
		}	
		return "yhpc/yhpcjl/index";
	}
	
	/**
	 * list页面
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.putAll(getAuthorityMap());
		map.put("qyname",request.getParameter("yhpc_yhpcjl_qyname"));
		map.put("starttime", request.getParameter("yhpc_yhpcjl_starttime"));//检查开始时间
		map.put("finishtime", request.getParameter("yhpc_yhpcjl_finishtime"));//检查结束时间
		map.put("checkresult", request.getParameter("yhpc_yhpcjl_checkresult"));//隐患处理情况
		map.put("yhjb", request.getParameter("yhjb"));//隐患处理情况
		map.put("zgzt", request.getParameter("zgzt"));//隐患整改状态
		map.put("yhly", request.getParameter("yhly"));//隐患来源
		map.put("xjjlzgxzqy", request.getParameter("wghgl_xjjl_xzqy"));//搜索框行政区域
		return yhpcYhpcjlService.dataGrid(map);
	}
	/**
	 * list页面（首页地图tab页获取数据）
	 * @param request
	 */
	@RequestMapping(value="tablist/{qyid}")
	@ResponseBody
	public Map<String, Object> getDataTab(@PathVariable("qyid") Integer qyid, HttpServletRequest request) {
		
		Map<String, Object> map = getPageMap(request);
		map.put("qyid", qyid);
		return yhpcYhpcjlService.dataGrid(map);
	}

	/**
	 * 删除隐患记录信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException
	 */
	@RequiresPermissions("yhpc:yhpcjl:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";

		// 可以批量删除
		String[] aids = ids.split(",");
		for (int i = 0; i < aids.length; i++) {
			yhpcYhpcjlService.deleteById(Long.parseLong(aids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
//	@RequiresPermissions("yhpc:yhpcjl:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Map<String,Object> yhpcjl = yhpcYhpcjlService.findInforById(id);
		
		model.addAttribute("yhpcjl", yhpcjl);
		//返回页面
		model.addAttribute("action", "view");
		return "yhpc/yhpcjl/view";
	}
	
	/**
	 * 查看进度页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "viewjd/{id}", method = RequestMethod.GET)
	public String viewzt(@PathVariable("id") Long id, Model model) {
		Map<String,Object> yhpcjl = yhpcYhpcjlService.findInforById(id);
		model.addAttribute("yhpcjl", yhpcjl);
		List<Map<String, Object>> zglist = yhpcYhpcjlService.getzglist(id);
		model.addAttribute("zglist", zglist);
		return "yhpc/yhpcjl/viewjd";
	}
}
