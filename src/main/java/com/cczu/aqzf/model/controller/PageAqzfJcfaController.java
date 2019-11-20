package com.cczu.aqzf.model.controller;

import com.cczu.aqzf.model.entity.*;
import com.cczu.aqzf.model.service.*;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.dto.Tree_CheckContent;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 安全执法_检查方案controller
 */
@Controller
@RequestMapping("aqzf/jcfa")
public class PageAqzfJcfaController extends BaseController {

	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private AqzfJcfaService aqzfJcfaService;
	@Autowired
	private IAqzfJcjhService AqzfJcjhService;
	@Autowired
	private AqzfSetBasicInfoService setbasicservice;
	@Autowired
	private IXzcfCommonLaspService punishcommonlaspservice;
	@Autowired
	private AqzfJcjlService aqzfJcjlService;

	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "aqzf/jcfa/index";
	}

	/**
	 * list集合(JSON)
	 */
	@RequiresPermissions("aqzf:jcfa:view")
	@RequestMapping(value="jcnr") 
	@ResponseBody
	public String DateList(Model model){
		List<Tree_CheckContent> list= aqzfJcfaService.getAllTreeList();
		
		return JsonMapper.getInstance().toJson(list);
	}
	
	/**
	 * list页面
	 * @param request
	 */
	@RequiresPermissions("aqzf:jcfa:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("0")){//安监局
			map.put("xzqy",sessionuser.getXzqy());
			if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
				map.put("jglx",sessionuser.getUserroleflg());
		}
		map.put("year", request.getParameter("aqzf_jcfa_year"));
		map.put("month", request.getParameter("aqzf_jcfa_month"));
		map.put("qyname", request.getParameter("aqzf_jcfa_qyname"));
		map.put("cz", request.getParameter("aqzf_jcfa_cz"));
		return aqzfJcfaService.dataGrid(map);
	}

	/**
	 * 添加检查方案跳转
	 * @param request,model
	 */
	@RequestMapping(value = "addFa/{id}" , method = RequestMethod.GET)
	public String createFa(@PathVariable("id") Long id, Model model) {
		AQZF_Plan_EnterpriseEntity plan=aqzfJcfaService.findPlan(id);
		//根据id查询检查计划
		AQZF_SafetyCheckPlanEntity jcjh=AqzfJcjhService.findById2(plan.getID1());
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(plan.getID2());
		model.addAttribute("action", "create");
		model.addAttribute("plan", plan);//传检查计划id和企业id过去
		model.addAttribute("qyname", be.getM1());//传企业名称过去
		model.addAttribute("leibie", be.getM36());//传行业类别过去
		Map<String,Object> jcfamap = new HashMap<>();
		jcfamap.put("m1", be.getM33());
		jcfamap.put("m2", be.getM6_1());
		//jcfamap.put("m3", jcjh.getM4());
		jcfamap.put("m5", plan.getM2());
		model.addAttribute("jcfa", jcfamap);
		return "aqzf/jcfa/form";	
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequiresPermissions("aqzf:jcfa:add")
	@RequestMapping(value = "create" , method = RequestMethod.POST)
	@ResponseBody
	public String create(AQZF_SafetyCheckSchemeEntity jcfa, Model model ) {
		String datasuccess="success";	
		//添加文书编号
		AQZF_SetBasicInfoEntity bh =setbasicservice.findInfor();
		AQZF_SetNumberEntity whbh=aqzfJcfaService.findWsbh();
		Calendar a=Calendar.getInstance();
		String year=a.get(Calendar.YEAR)+"";
		jcfa.setM0("（"+bh.getSsqjc()+"）应急检查〔"+year+"〕"+whbh.getM1()+"号");
		aqzfJcfaService.addInfo(jcfa);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * list页面
	 * @param request
	 */
	@RequestMapping(value="tablist/{qyid}")
	@ResponseBody
	public Map<String, Object> getDataTab(@PathVariable("qyid") Integer qyid, HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyid", qyid);
		return aqzfJcfaService.dataGrid(map);
	}
	
	/**
	 * 修改检查方案页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的成员信息
		Map<String, Object> jcfa= aqzfJcfaService.findById(id);
		model.addAttribute("jcfa", jcfa);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "aqzf/jcfa/form";
	}
	
	/**
	 * 修改检查方案信息
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(AQZF_SafetyCheckSchemeEntity jcfa, Model model){
		String datasuccess="success";	 
		aqzfJcfaService.updateInfo(jcfa);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 删除第检查方案信息
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
			XZCF_YbcfLaspEntity lasp = aqzfJcjlService.findLaspByfaid(aids[i]);
			if(lasp!=null){
				AQZF_SafetyCheckRecordEntity jcjl = aqzfJcjlService.findJcjlByfaid(Long.parseLong(aids[i]));
				if(jcjl!=null){
					//删除立案审批并修改检查记录状态
					punishcommonlaspservice.deleteInfo(lasp.getID());
				}
			}
			aqzfJcfaService.deleteInfo(Long.parseLong(aids[i]));
		}
		return datasuccess;
	}

	/**
	 * 导出现场检查方案word
	 * 
	 */
	@RequiresPermissions("aqzf:jcfa:exportword")
	@RequestMapping(value = "export/{id}")
	@ResponseBody
	public String getAjWord(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = aqzfJcfaService.getAjWord(id);
		//设置导出的文件名
		//String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		String filename = map.get("m0").toString() + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "xcjcfa.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		//查询选择的成员信息
		Map<String, Object> jcfa= aqzfJcfaService.findById(id);
		model.addAttribute("jcfa", jcfa);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "aqzf/jcfa/view";
	}
	
}
