package com.cczu.aqzf.model.controller;

import com.cczu.aqzf.model.service.impl.XzcfCommonLaspServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 行政处罚-一般处罚-统计controller
 * 
 * @author jason
 * 
 */
@Controller
@RequestMapping("xzcf/ybcf/tj")
public class PageXzcfTjController extends BaseController {
	
	@Autowired
	private XzcfCommonLaspServiceImpl punishcommonlaspservice;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {
		return "aqzf/xzcf/ybcf/xzcftj/index";
	}
	
	/**
	 * 显示所有列
	 * @param id,model 
	 */
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","xzcf/ybcf/tj/export");
		return "common/formexcel";
	}
	
	/**
	 * 导出excel
	 */
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("number", request.getParameter("ybcf_lasp_number"));
		map.put("name", request.getParameter("ybcf_lasp_name"));
		map.put("qyname", request.getParameter("ybcf_lasp_qyname"));
		map.put("casesource", request.getParameter("ybcf_lasp_casesource"));
		map.put("startdate", request.getParameter("ybcf_lasp_startdate"));
		map.put("enddate", request.getParameter("ybcf_lasp_enddate"));
		map.put("type", request.getParameter("ybcf_lasp_type"));
		map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());
		
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		punishcommonlaspservice.exportExcel(response, map);
	}
}
