package com.cczu.model.mbgl.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.mbgl.entity.Target_SelfAssessments;
import com.cczu.model.mbgl.service.TargetMbzpService;
import com.cczu.model.mbgl.service.TargetZbfpService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 目标管理-目标设置controller
 * 
 * @author jason
 * 
 */

@Controller
@RequestMapping("target/mbzp")
public class PageTargetMbzpController extends BaseController {

	@Autowired
	private TargetMbzpService targetMbzpService;
	
	@Autowired
	private TargetZbfpService targetZbfpService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;

	/**
	 * 列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model,HttpServletRequest request) {
		String ajqyid = request.getParameter("qyid");
		if(StringUtils.isEmpty(ajqyid)){
			ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
			model.addAttribute("qyid", sessionuser.getQyid());
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(UserUtil.getCurrentShiroUser().getQyid());
			if (be.getIsbloc() != null && be.getIsbloc() == 1) {
				model.addAttribute("usertype", "isbloc");
			}
		}else{
			model.addAttribute("ajqyid", ajqyid);
		}
		return "targetmanger/zbzp/index";
	}

	/**
	 * list页面 企业端
	 * @param request
	 */
	@RequestMapping(value = "qylist")
	@ResponseBody
	public Map<String, Object> getQyData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		String ajqyid = request.getParameter("ajqyid");
		if(StringUtils.isEmpty(ajqyid)){
			User user =UserUtil.getCurrentUser();
			Subject subject = SecurityUtils.getSubject();
			if(!subject.isPermitted("target:mbzp:viewall")){//是否拥有viewall权限
				if(user.getDepartmen()!=null)
					map.put("deptid", user.getDepartmen());
			}
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(user.getId2());
			if (be.getIsbloc() != null && be.getIsbloc() == 1) {//集团公司
				map.put("fid", user.getId2());
				map.put("qyname", request.getParameter("target_mbzp_qyname"));		
			}else{
				map.put("qyid", user.getId2());
			}
		}else{
			map.put("qyid", ajqyid);
		}
		map.put("deptname", request.getParameter("target_mbzp_deptname"));		
		map.put("tname", request.getParameter("target_mbzp_tname"));
		map.put("year", request.getParameter("target_mbzp_year"));
		map.put("quarter", request.getParameter("target_mbzp_quarter"));//季度
		map.put("db", request.getParameter("target_mbzp_db"));//达标情况
		return targetMbzpService.dataGrid(map);
	}

	/**
	 * 添加页面跳转
	 * id(target_distribute id)
	 * @param model
	 */
	@RequiresPermissions("target:mbzp:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		User user = UserUtil.getCurrentUser();
		model.addAttribute("action", "create");
		model.addAttribute("username", user.getName());
		if(user.getDepartmen()!=null){
			model.addAttribute("deptid", user.getDepartmen());
		}
		return "targetmanger/zbzp/initform";
	}

	
	/**
	 * @param model
	 */
/*	@RequiresPermissions("target:zbkh:add")
	@RequestMapping(value = "init", method = RequestMethod.POST)
	@ResponseBody
	public String init(HttpServletRequest request) {
		String datasuccess = "success";
		try {
			targetMbzpService.init( UserUtil.getCurrentShiroUser().getQyid(),request.getParameter("year"),request.getParameter("quarter"));
		} catch (Exception e) {
			// TODO: handle exception
			datasuccess="failed";
		}
		return datasuccess;
	}*/
	/**
	 * 添加目标信息
	 * 
	 * @param request
	 * model
	 */
	@RequiresPermissions("target:mbzp:add")
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public String create(@RequestBody List<Target_SelfAssessments> list) {
		String datasuccess = "success";
		ShiroUser user =UserUtil.getCurrentShiroUser();
		Timestamp t = DateUtils.getSysTimestamp();
		for(Target_SelfAssessments e :list){
			if(e.getID()==null){
				e.setID1(user.getQyid());
				e.setS1(t);
				e.setS2(t);
				e.setS3(0);
				targetMbzpService.addInfo(e);
			}else{
				e.setS2(t);
				targetMbzpService.updateInfoBysql(e);
			}
		}
		// 返回结果
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * 
	 * @param id
	 *            ,model
	 */
	@RequiresPermissions("target:mbzp:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		Target_SelfAssessments target = targetMbzpService.findInfoById(id);
		model.addAttribute("target", target);
		model.addAttribute("action", "update");
		return "targetmanger/zbzp/form";
	}

	/**
	 * 修改目标信息
	 * 
	 * @param request
	 *            ,model
	 */
	@RequiresPermissions("target:mbzp:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(Target_SelfAssessments target, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		target.setS2(t);
		target.setID1(UserUtil.getCurrentShiroUser().getQyid());
		targetMbzpService.updateInfo(target);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 删除目标信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException
	 */
	@RequiresPermissions("target:mbzp:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			targetMbzpService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * 
	 * @param id
	 *            ,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Target_SelfAssessments target = targetMbzpService.findInfoById(id);
		model.addAttribute("target", target);
		return "targetmanger/zbzp/view";
	}
	/**
	 * 查看页面跳转
	 * 
	 * @param id
	 *  ,model
	 */
	@RequestMapping(value = "quarter", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> searchQuart(@RequestParam("year") String year,
			@RequestParam("deptid") long deptid,@RequestParam("quarter") String quarter){
		Map<String, Object> mapData= new HashMap<String, Object>();
		mapData.put("qyid",UserUtil.getCurrentShiroUser().getQyid());
		mapData.put("year",year );
		mapData.put("deptid",deptid );
		mapData.put("quart",quarter );
		Map<String, Object> remap= new HashMap<String, Object>();
		List<Map<String, Object>> list= targetMbzpService.findQuartById(mapData);
		remap.put("rows", list);
		return remap;
	}

}
