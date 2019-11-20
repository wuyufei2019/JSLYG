package com.cczu.model.sgzn.controller;

import java.sql.Timestamp;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.model.sgzn.entity.SgznHzsgEntity;
import com.cczu.model.sgzn.service.SgznHzsgService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;
/**
 * 事故灾难类-火灾事故信息快报Controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("sgzn/hzsg")
public class SgznHzsgController extends BaseController{
	
	@Autowired
	private BisQyjbxxServiceImpl qyjbxxServiceImpl;
	@Autowired
	private SgznHzsgService sgznHzsgService;
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		model.addAttribute("userid", UserUtil.getCurrentShiroUser().getId());
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		model.addAttribute("qyid", sessionuser.getQyid());
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					model.addAttribute("type", "2");
				else
					model.addAttribute("type", "1");
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			model.addAttribute("type", "2");
		}	
		return "erm/sgzn/hzsg/index";
	}
	
	
	
	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> ajgetData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("m1", request.getParameter("sgzn_hzsg_m1"));
		map.putAll(getAuthorityMap());
		return sgznHzsgService.dataGrid(map);

	}
	
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("sgzn:hzsg:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createtz(Model model) {
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		model.addAttribute("action", "create");
		return "erm/sgzn/hzsg/form";

	}
	
	
	/**
	 * 添加火灾事故信息
	 * @param hzsg
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sgzn:hzsg:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(SgznHzsgEntity hzsg, Model model) {
		String datasuccess = "success";
		Timestamp t=DateUtils.getSysTimestamp();
		hzsg.setS1(t);
		hzsg.setS2(t);
		hzsg.setS3(0);
		sgznHzsgService.addInfo(hzsg);
		return datasuccess;
	}
	
	
	/**
	 * 修改页面 跳转
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sgzn:hzsg:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updatetz(@PathVariable("id") Long id, Model model) {
		SgznHzsgEntity hzsg =sgznHzsgService.findById(id);
		model.addAttribute("hzsg",hzsg);
		model.addAttribute("action", "update");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "erm/sgzn/hzsg/form";
	}
	
	
	
	
	/**
	 * 修改安全事故信息
	 * @param hzsg
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sgzn:hzsg:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(SgznHzsgEntity hzsg, Model model) {
		String datasuccess = "success";
		SgznHzsgEntity hzsg1 =sgznHzsgService.findById(hzsg.getID());
		hzsg1.setM0(hzsg.getM0());
		hzsg1.setM0_1(hzsg.getM0_1());
		hzsg1.setM1(hzsg.getM1());
		hzsg1.setM2(hzsg.getM2());
		hzsg1.setM3(hzsg.getM3());
		hzsg1.setM4(hzsg.getM4());
		hzsg1.setM4_1(hzsg.getM4_1());
		hzsg1.setM6(hzsg.getM6());
		hzsg1.setM7(hzsg.getM7());
		hzsg1.setM9(hzsg.getM9());
		hzsg1.setM11(hzsg.getM11());
		hzsg1.setM12(hzsg.getM12());
		hzsg1.setM13(hzsg.getM13());
		hzsg1.setM14(hzsg.getM14());
		hzsg1.setM15(hzsg.getM15());
		hzsg1.setM16(hzsg.getM16());
		hzsg1.setS2(DateUtils.getSysTimestamp());
		sgznHzsgService.updateInfo(hzsg1);
		return datasuccess;
	}
	
	
	/**
	 * 依据id删除火灾事故信息
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("sgzn:hzsg:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		String[] aids = ids.split(",");
		for (int i = 0; i < aids.length; i++) {
			sgznHzsgService.deleteInfo(Long.parseLong(aids[i]));
		}
		return datasuccess;
	}
	
	
	/**
	 * 查看火灾事故信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sgzn:hzsg:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String viewtz(@PathVariable("id") Long id, Model model) {
		SgznHzsgEntity hzsg =sgznHzsgService.findById(id);
		model.addAttribute("hzsg",hzsg);
		model.addAttribute("action","view");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		
		return "erm/sgzn/hzsg/view";
	}
	
	
	
	/**
	 * 导出火灾事故word
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("sgzn:hzsg:exportword")
	@RequestMapping(value = "exportword/{id}")
	@ResponseBody
	public String getexportword(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = sgznHzsgService.getExportWord(id);
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "hzsg.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
	
	
	
	
	
}
