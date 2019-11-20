package com.cczu.model.controller;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.ERM_EmergencyTrainPlanYearEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.impl.ErmNdxljhService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
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
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * 年度应急救援技能训练计划信息controller
 * 
 * @author ll
 * @date 2019年5月24日
 */
@Controller
@RequestMapping("erm/train-plan/year")
public class PageErmTrainPlanYearController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private ErmNdxljhService ermNdxljhService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;

	/**
	 * 列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					model.addAttribute("type", 2);
				else
					model.addAttribute("type", 1);
			}
		}
		String usertype = UserUtil.getCurrentShiroUser().getUsertype();
		model.addAttribute("usertype", usertype);
		return "erm/plan/year/index";
	}

	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequiresPermissions("erm:train-plan:year:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {

		Map<String, Object> map = getPageMap(request);
		map.putAll(getAuthorityMap());
		map.put("qynm", request.getParameter("erm_year_qyname"));
		map.put("depname", request.getParameter("erm_year_dep"));
		map.put("m2", request.getParameter("erm_yljh_cs_m2"));
		map.put("m3", request.getParameter("erm_yljh_cs_m3"));
		return ermNdxljhService.dataGrid(map);

	}
	
	/**
	 * 导出Excel
	 * 
	 * @param request
	 */
	@RequiresPermissions("erm:train-plan:year:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void getExcel(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qynm", request.getParameter("erm_yljh_qy_name"));
		map.put("dwname", request.getParameter("erm_yljh_cs_name"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			map.put("qyid",(UserUtil.getCurrentShiroUser().getQyid()));
		}
		ermNdxljhService.exportExcel(response, map);
		
	}

	/**
	 * 显示所有列
	 * @param id,model
	 */
	@RequiresPermissions("erm:train-plan:year:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","erm/plan/year/export");
		return "common/formexcel";
	}
	
	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("erm:train-plan:year:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "erm/plan/year/form";
	}

	/**
	 * 添加演练计划信息
	 * 
	 * @param request
     *     ,model
	 */
	@RequiresPermissions("erm:train-plan:year:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(ERM_EmergencyTrainPlanYearEntity ee, Model model) {
		String datasuccess = "success";
		Timestamp t=DateUtils.getSysTimestamp();
		ee.setS2(t);
		ee.setS3(0);
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			ee.setQyid(UserUtil.getCurrentShiroUser().getQyid());
		}
		ermNdxljhService.addInfo(ee);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * 
	 * @param id
	 *            ,model
	 */
	@RequiresPermissions("erm:train-plan:year:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Integer id, Model model) {
		// 查询选择的演练计划信息
		long id1 = id;
        Map<String, Object> ee = ermNdxljhService.findById(id1);
		model.addAttribute("year", ee);
		// 返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "erm/plan/year/form";
	}

	/**
	 * 修改演练计划信息
	 * 
	 * @param request
	 *            ,model
	 */
	@RequiresPermissions("erm:train-plan:year:update")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public String update(ERM_EmergencyTrainPlanYearEntity ee, Model model) {
		String datasuccess = "success";
		Timestamp t=DateUtils.getSysTimestamp();
		ee.setS2(t);
		ermNdxljhService.updateInfo(ee);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 删除演练计划信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException
	 */
	@RequiresPermissions("erm:train-plan:year:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			ermNdxljhService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("erm:train-plan:year:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model model) {
		//查询选择的产品信息
		 
		long id1 = id;
        Map<String, Object> ee = ermNdxljhService.findById(id1);
		model.addAttribute("year", ee);
		//返回页面
		model.addAttribute("action", "view");
		return "erm/plan/year/view";
	}
	
	//导入页面跳转
	@RequestMapping(value = "exinjump", method = RequestMethod.GET)
	public String exin(Model model) {
		model.addAttribute("action", "exin");
		return "common/importForm";
	}
}
