package com.cczu.aqzf.model.controller;

import com.cczu.aqzf.model.entity.AQZF_RyfzEntity;
import com.cczu.aqzf.model.service.AqzfRyfzService;
import com.cczu.aqzf.model.service.AqzfZfryService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 人员分组
 * @author zpc
 * @date 2018/02/24
 */
@Controller
@RequestMapping("aqzf/ryfz")
public class PageAqzfRyfzController extends BaseController {

	@Autowired
	private AqzfRyfzService aqzfRyfzService;
	@Autowired
	private AqzfZfryService aqzfZfryService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index",method = RequestMethod.GET)
	public String index(Model model) {
		return "aqzf/sjwh/ryfz/index";
	}
	
	/**
	 * list页面
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		map.put("xzqy", sessionuser.getXzqy());
		if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
			map.put("jglx",sessionuser.getUserroleflg());
		return aqzfRyfzService.dataGrid(map);
	}
	
	@RequestMapping(value="ryfzlist")
	@ResponseBody
	public List<Map<String, Object>> getJcdylist(Model model) {
		Map<String,Object> map = new HashMap<String, Object>();
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		map.put("xzqy", sessionuser.getXzqy());
		if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
			map.put("jglx",sessionuser.getUserroleflg());
		return aqzfRyfzService.getjcdylist(map);
	}
	
	/**
	 * 添加人员分组页面跳转
	 * 
	 * @param model
	 */
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		return "aqzf/sjwh/ryfz/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequestMapping(value = "create" )
	@ResponseBody
	public String create(AQZF_RyfzEntity ryfz, HttpServletRequest request) {
		String datasuccess="success";
		ShiroUser su = UserUtil.getCurrentShiroUser();
		ryfz.setID1(su.getId());
		aqzfRyfzService.addInfo(ryfz);
		return datasuccess;
	}
	
	/**
	 * 删除人员分组
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException 
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] aids = ids.split(",");
		for(int i=0;i<aids.length;i++){
			aqzfZfryService.updateByM8(aids[i]);
			aqzfRyfzService.deleteInfo(Long.parseLong(aids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的成员信息
		AQZF_RyfzEntity ryfz  = aqzfRyfzService.findById(id);
		model.addAttribute("ryfz", ryfz);
		//返回页面
		model.addAttribute("action", "update");
		return "aqzf/sjwh/ryfz/form";
	}
	
	/**
	 * 修改
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(AQZF_RyfzEntity ryfz, Model model){
		String datasuccess="success";	
		aqzfRyfzService.updateInfo(ryfz);
		return datasuccess;
	}

}
