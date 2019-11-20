package com.cczu.model.sgzn.controller;

import java.sql.Timestamp;
import java.util.Calendar;
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
import com.cczu.model.entity.BIS_WorkorderEntity;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.model.sgzn.entity.SgznAqsgEntity;
import com.cczu.model.sgzn.service.SgznAqsgService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;

/**
 * 事故灾难类-安全事故信息快报Controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("sgzn/aqsg")
public class SgznAqsgController extends BaseController{
	
	
	@Autowired
	private BisQyjbxxServiceImpl qyjbxxServiceImpl;
	@Autowired
	private SgznAqsgService sgznAqsgService ;
	
	
	
	
	
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
		return "erm/sgzn/aqsg/index";
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
		map.put("m1", request.getParameter("sgzn_aqsg_m1"));
		map.putAll(getAuthorityMap());
		return sgznAqsgService.dataGrid(map);

	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("sgzn:aqsg:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createtz(Model model) {
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		model.addAttribute("action", "create");
		return "erm/sgzn/aqsg/form";

	}
	
	/**
	 * 添加安全事故信息
	 * @param aqsg
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sgzn:aqsg:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(SgznAqsgEntity aqsg, Model model) {
		String datasuccess = "success";
		Timestamp t=DateUtils.getSysTimestamp();
		aqsg.setS1(t);
		aqsg.setS2(t);
		aqsg.setS3(0);
		sgznAqsgService.addInfo(aqsg);
		return datasuccess;
	}
	
	
	
	/**
	 * 修改页面 跳转
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sgzn:aqsg:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updatetz(@PathVariable("id") Long id, Model model) {
		SgznAqsgEntity aqsg =sgznAqsgService.findById(id);
		model.addAttribute("aqsg",aqsg );
		model.addAttribute("action", "update");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "erm/sgzn/aqsg/form";
	}
	
	
	/**
	 * 修改安全事故信息
	 * @param aqsg
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sgzn:aqsg:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(SgznAqsgEntity aqsg, Model model) {
		String datasuccess = "success";
		SgznAqsgEntity aqsg1 =sgznAqsgService.findById(aqsg.getID());
		aqsg1.setM0(aqsg.getM0());
		aqsg1.setM0_1(aqsg.getM0_1());
		aqsg1.setM1(aqsg.getM1());
		aqsg1.setM2(aqsg.getM2());
		aqsg1.setM3(aqsg.getM3());
		aqsg1.setM4(aqsg.getM4());
		aqsg1.setM4_1(aqsg.getM4_1());
		aqsg1.setM5(aqsg.getM5());
		aqsg1.setM6(aqsg.getM6());
		aqsg1.setM7(aqsg.getM7());
		aqsg1.setM8(aqsg.getM8());
		aqsg1.setM9(aqsg.getM9());
		aqsg1.setM10(aqsg.getM10());
		aqsg1.setM11(aqsg.getM11());
		aqsg1.setM12(aqsg.getM12());
		aqsg1.setM13(aqsg.getM13());
		aqsg1.setM14(aqsg.getM14());
		aqsg1.setM15(aqsg.getM15());
		aqsg1.setM16(aqsg.getM16());
		aqsg1.setS2(DateUtils.getSysTimestamp());
		sgznAqsgService.updateInfo(aqsg1);
		return datasuccess;
	}
	
	
	/**
	 * 依据id删除安全事故信息
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("sgzn:aqsg:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		String[] aids = ids.split(",");
		for (int i = 0; i < aids.length; i++) {
			sgznAqsgService.deleteInfo(Long.parseLong(aids[i]));
		}
		return datasuccess;
	}
	
	
	/**
	 * 查看安全事故信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sgzn:aqsg:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		SgznAqsgEntity aqsg =sgznAqsgService.findById(id);
		model.addAttribute("aqsg",aqsg);
		model.addAttribute("action","view");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		
		return "erm/sgzn/aqsg/view";
	}
	
	
	
	/**
	 * 导出安全事故word
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("sgzn:aqsg:exportword")
	@RequestMapping(value = "exportword/{id}")
	@ResponseBody
	public String getexportword(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = sgznAqsgService.getExportWord(id);
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "aqsg.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
	
	
	
	public static void main(String[] args) { 

		Calendar date = Calendar.getInstance();
		String year = String.valueOf(date.get(Calendar.YEAR));
		System.out.println(year);

	}

	
	
	
	
	
	
	
}
