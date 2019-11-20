package com.cczu.aqzf.model.controller;

import com.cczu.aqzf.model.service.XzcfLahszService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 行政处罚-一般处罚-立案回收站controller
 * 
 * @author jason
 * 
 */
@Controller
@RequestMapping("xzcf/ybcf/lahsz")
public class PageXzcfLahszController extends BaseController {
	
	@Autowired
	private XzcfLahszService xzcfLahszService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {
		return "aqzf/xzcf/ybcf/lahsz/index";
	}
	
	/**
	 * list页面
	 */
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
		map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());
		return xzcfLahszService.dataGrid(map);
	}
	
	/**
	 * 恢复立案审批 信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "hf/{id}")
	@ResponseBody
	public String delete(@PathVariable("id") String id) {
		String datasuccess = "success";
		xzcfLahszService.hflasp(id);
		return datasuccess;
	}
}
