package com.cczu.aqzf.model.controller;


import com.cczu.aqzf.model.entity.*;
import com.cczu.aqzf.model.service.*;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 监督检查统计
 * @author zpc
 * @date 2018/02/10
 */
@Controller
@RequestMapping("aqzf/jdjctj")
public class PageAqzfJdjctjController extends BaseController {

	@Autowired
	private AqzfJdjctjService aqzfJdjctjService;
	@Autowired
	private IAqzfJcjhService AqzfJcjhService;
	@Autowired
	private AqzfJcfaService aqzfJcfaService;
	@Autowired
	private AqzfJcjlService aqzfJcjlService;
	@Autowired
	private AqzfClcsService aqzfClcsService;
	@Autowired
	private AqzfZlzgService aqzfZlzgService;
	@Autowired
	private AqzfFcyjService aqzfFcyjService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "aqzf/jdjctj/index";
	}
	
	/**
	 * list页面
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		map.put("xzqy",sessionuser.getXzqy());
		if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
			map.put("jglx",sessionuser.getUserroleflg());
		map.put("year", request.getParameter("aqzf_jdjctj_year"));
		map.put("month", request.getParameter("aqzf_jdjctj_month"));
		map.put("qyname", request.getParameter("aqzf_jdjctj_qyname"));
		map.put("M4", request.getParameter("aqzf_jdjctj_M4"));
		return aqzfJdjctjService.dataGrid(map);
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "viewjd/{jhqyid}")
	public String view(@PathVariable("jhqyid") Long jhqyid, Model model) {
		List<Map<String, Object>> jcjhlist = AqzfJcjhService.findById(jhqyid);
		Map<String, Object> jcjh = jcjhlist.get(0);
		AQZF_SafetyCheckSchemeEntity jcfa = aqzfJcfaService.findJcfaByjhqyid(jcjh.get("jhid").toString(),jcjh.get("qyid").toString());
		model.addAttribute("jcjh", jcjh);
		model.addAttribute("jcfa", jcfa);
		model.addAttribute("jcjl", null);
		model.addAttribute("clcs", null);
		model.addAttribute("zlzg", null);
		model.addAttribute("fcyj", null);
		if(jcfa != null){
			AQZF_SafetyCheckRecordEntity jcjl = aqzfJcjlService.findJcjlByfaid(jcfa.getID());
			model.addAttribute("jcjl", jcjl);
			if(jcjl != null){
				AQZF_TreatmentEntity clcs = aqzfClcsService.findByjlid(jcjl.getID());
				model.addAttribute("clcs", clcs);
				AQZF_ReformEntity zlzg = aqzfZlzgService.findInfoById1(jcjl.getID());
				model.addAttribute("zlzg", zlzg);
				if(zlzg != null){
					AQZF_ReviewEntity fcyj = aqzfFcyjService.findInfoById1(zlzg.getID());
					model.addAttribute("fcyj", fcyj);
				}
			}
		}
		return "aqzf/jdjctj/view";
	}
}
