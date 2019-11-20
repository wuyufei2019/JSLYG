package com.cczu.aqzf.model.controller;


import com.cczu.aqzf.model.entity.XZCF_CssbblEntity;
import com.cczu.aqzf.model.entity.XZCF_YbcfLaspEntity;
import com.cczu.aqzf.model.service.AqzfSetBasicInfoService;
import com.cczu.aqzf.model.service.IXzcfCommonLaspService;
import com.cczu.aqzf.model.service.XzcfCssbblService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Random;

/**
 * 陈述申辩笔录
 * @author zpc
 * @date 2017/08/02
 */
@Controller
@RequestMapping("xzcf/ybcf/cssbbl")
public class PageXzcfCssbblController extends BaseController {

	@Autowired
	private XzcfCssbblService xzcfCssbblService;
	@Autowired
	private IXzcfCommonLaspService xzcfcommonlaspservice;
	@Autowired
	private AqzfSetBasicInfoService aqzfsetbasicinfoservice;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "aqzf/xzcf/ybcf/cssbbl/index";
	}
	
	/**
	 * list页面
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());
		map.put("m11", request.getParameter("aqzf_cssbbl_M11"));
		map.put("labh", request.getParameter("aqzf_cssbbl_labh"));
		return xzcfCssbblService.dataGrid(map);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] aids = ids.split(",");
		try {
			for(int i=0;i<aids.length;i++){
				xzcfCssbblService.deleteInfo(Long.parseLong(aids[i]));
				xzcfCssbblService.updateLaspInfo(Long.parseLong(aids[i]));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			datasuccess="删除失败";
		}
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		XZCF_CssbblEntity cssbbl = xzcfCssbblService.findById(id);
		model.addAttribute("cssbbl", cssbbl);
		return "aqzf/xzcf/ybcf/cssbbl/view";
	}
	
	/**
	 * 添加跳转
	 * id 立案id
	 * @param model
	 */
	@RequestMapping(value = "create/{id}" , method = RequestMethod.GET)
	public String create(@PathVariable("id") Long id,Model model) {
		XZCF_CssbblEntity cssbbl = new XZCF_CssbblEntity();
		XZCF_YbcfLaspEntity ybcf = xzcfcommonlaspservice.findInfoById(id);
		cssbbl.setM11(ybcf.getCasename());
		cssbbl.setID1(id);
		cssbbl.setM7(ybcf.getDsperson());
		cssbbl.setM8(ybcf.getContact());
		cssbbl.setM9(ybcf.getDsaddress());
		cssbbl.setM10(ybcf.getYbcode());
		cssbbl.setM12(ybcf.getEnforcer1());
		cssbbl.setM13(ybcf.getIdentity1());
		cssbbl.setM14(ybcf.getEnforcer2());
		cssbbl.setM15(ybcf.getIdentity2());
		model.addAttribute("cssbbl", cssbbl);
		model.addAttribute("action", "create");
		return "aqzf/xzcf/ybcf/cssbbl/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequestMapping(value = "create" )
	@ResponseBody
	public String create(XZCF_CssbblEntity cssbbl, HttpServletRequest request) {
		String datasuccess="success";
		XZCF_YbcfLaspEntity ybcf = xzcfcommonlaspservice.findInfoById(cssbbl.getID1());
		//添加集体讨论并修改立案标记
		xzcfCssbblService.addInfo(cssbbl);
		ybcf.setSbflag("1");
		xzcfcommonlaspservice.updateInfo(ybcf);
		return datasuccess;
	}
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		XZCF_CssbblEntity cssbbl = xzcfCssbblService.findById(id);
		model.addAttribute("cssbbl", cssbbl);
		model.addAttribute("action", "update");
		return "aqzf/xzcf/ybcf/cssbbl/form";
	}
	
	/**
	 * 修改
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(XZCF_CssbblEntity cssbbl){
		String datasuccess="success";
		xzcfCssbblService.updateInfo(cssbbl);
		return datasuccess;
	}

	/**
	 * 导出word
	 */
	@RequestMapping(value = "export/{flag}/{id}")
	@ResponseBody
	public String getAjWord(@PathVariable("flag") String flag,@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = xzcfCssbblService.getWsdcword(id,flag);
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "xzcssbbl.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
	/**
	 * 总览查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "viewcssb/{id}")
	public String viewcssb(@PathVariable("id") Long id, Model model) {
		XZCF_CssbblEntity cssbbl = xzcfCssbblService.findWordByLaId(id);
		model.addAttribute("cssbbl", cssbbl);
		return "aqzf/xzcf/ybcf/cssbbl/view";
	}
}
