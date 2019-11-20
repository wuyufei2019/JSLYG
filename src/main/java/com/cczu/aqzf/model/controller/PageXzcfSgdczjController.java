package com.cczu.aqzf.model.controller;

import com.cczu.aqzf.model.entity.XZCF_SgdczjEntity;
import com.cczu.aqzf.model.service.XzcfSgdczjService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 事故调查证据
 * @author zpc
 * @date 2018/1/18
 */
@Controller
@RequestMapping("xzcf/ybcf/sgdczj")
public class PageXzcfSgdczjController extends BaseController {

	@Autowired
	private XzcfSgdczjService xzcfSgdczjService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "aqzf/xzcf/ybcf/sgdczj/index";
	}
	
	/**
	 * list页面
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());
		map.put("dczt", request.getParameter("aqzf_sgdczj_dczt"));
		map.put("qyname", request.getParameter("aqzf_sgdczj_qyname"));
		return xzcfSgdczjService.dataGrid(map);
	}
	
	/**
	 * 删除
	 * @param user
	 * @param model
	 * 
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] aids = ids.split(",");
		try {
			for(int i=0;i<aids.length;i++){
				xzcfSgdczjService.deleteInfo(Long.parseLong(aids[i]));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			datasuccess="删除失败";
		}
		return datasuccess;
	}

	/**
	 * 添加跳转
	 * @param model
	 */
	@RequestMapping(value = "create/{id}" , method = RequestMethod.GET)
	public String create(@PathVariable("id") Long id,Model model) {
		XZCF_SgdczjEntity sgdczj  = new XZCF_SgdczjEntity();
		sgdczj.setID1(id);
		model.addAttribute("sgdczj", sgdczj);
		model.addAttribute("action", "create");
		return "aqzf/xzcf/ybcf/sgdczj/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequestMapping(value = "create" )
	@ResponseBody
	public String create(XZCF_SgdczjEntity sgdczj, HttpServletRequest request) {
		String datasuccess="success";
		xzcfSgdczjService.addInfo(sgdczj);
		return datasuccess;
	}
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		XZCF_SgdczjEntity sgdczj  = xzcfSgdczjService.findById(id);
		model.addAttribute("sgdczj", sgdczj);
		model.addAttribute("action", "update");
		return "aqzf/xzcf/ybcf/sgdczj/form";
	}
	
	/**
	 * 修改
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(XZCF_SgdczjEntity sgdczj){
		String datasuccess="success";
		xzcfSgdczjService.updateInfo(sgdczj);
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Map<String,Object> sgdczj = xzcfSgdczjService.findAllbyid(id);	
		model.addAttribute("sgdczj", sgdczj);
		return "aqzf/xzcf/ybcf/sgdczj/view";
	}
	
}
