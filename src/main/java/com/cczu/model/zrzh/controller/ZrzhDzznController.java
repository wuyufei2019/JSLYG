package com.cczu.model.zrzh.controller;

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
import com.cczu.model.zrzh.entity.ZrzhDzznEntiy;
import com.cczu.model.zrzh.service.ZrzhDzznService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;
/**
 * 自然灾害类-地质灾难信息快报Controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("zrzh/dzzn")
public class ZrzhDzznController extends BaseController{
	
	
	@Autowired
	private BisQyjbxxServiceImpl qyjbxxServiceImpl;
	@Autowired
	private ZrzhDzznService zrzhDzznService;
	
	
	
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
		return "erm/zrzh/dzzn/index";
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
		map.put("m2", request.getParameter("zrzh_dzzn_m2"));//（小型、中型、大型、特大型）
		map.put("m5", request.getParameter("zrzh_dzzn_m5"));//地质灾害类型（滑坡、崩塌等）
		map.putAll(getAuthorityMap());
		return zrzhDzznService.dataGrid(map);

	}
	
	
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("zrzh:dzzn:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createtz(Model model) {
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		model.addAttribute("action", "create");
		return "erm/zrzh/dzzn/form";

	}
	
	
	/**
	 * 添加地质灾害信息
	 * @param dzzn
	 * @param model
	 * @return
	 */
	@RequiresPermissions("zrzh:dzzn:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(ZrzhDzznEntiy dzzn, Model model) {
		String datasuccess = "success";
		Timestamp t=DateUtils.getSysTimestamp();
		dzzn.setS1(t);
		dzzn.setS2(t);
		dzzn.setS3(0);
		zrzhDzznService.addInfo(dzzn);
		return datasuccess;
	}
	
	
	/**
	 * 修改页面 跳转
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("zrzh:dzzn:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updatetz(@PathVariable("id") Long id, Model model) {
		ZrzhDzznEntiy dzzn =zrzhDzznService.findById(id);
		model.addAttribute("dzzn",dzzn);
		model.addAttribute("action", "update");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "erm/zrzh/dzzn/form";
	}
	
	
	/**
	 * 修改地质灾害信息
	 * @param dzzn
	 * @param model
	 * @return
	 */
	@RequiresPermissions("zrzh:dzzn:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(ZrzhDzznEntiy dzzn, Model model) {
		String datasuccess = "success";
		ZrzhDzznEntiy dzzn1 =zrzhDzznService.findById(dzzn.getID());
		dzzn1.setM0(dzzn.getM0());
		dzzn1.setM0_1(dzzn.getM0_1());
		dzzn1.setM2(dzzn.getM2());
		dzzn1.setM3(dzzn.getM3());
		dzzn1.setM4(dzzn.getM4());
		dzzn1.setM4_1(dzzn.getM4_1());
		dzzn1.setM5(dzzn.getM5());
		dzzn1.setM6(dzzn.getM6());
		dzzn1.setM7(dzzn.getM7());
		dzzn1.setM8(dzzn.getM8());
		dzzn1.setM9(dzzn.getM9());
		dzzn1.setM10(dzzn.getM10());
		dzzn1.setM12(dzzn.getM12());
		dzzn1.setM13(dzzn.getM13());
		dzzn1.setM14(dzzn.getM14());
		dzzn1.setM15(dzzn.getM15());
		dzzn1.setM16(dzzn.getM16());
		dzzn1.setS2(DateUtils.getSysTimestamp());
		zrzhDzznService.updateInfo(dzzn1);
		return datasuccess;
	}
	
	
	/**
	 * 依据id删除地质灾害信息
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("zrzh:dzzn:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		String[] aids = ids.split(",");
		for (int i = 0; i < aids.length; i++) {
			zrzhDzznService.deleteInfo(Long.parseLong(aids[i]));
		}
		return datasuccess;
	}
	
	
	
	/**
	 * 查看地质灾害信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("zrzh:dzzn:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String viewtz(@PathVariable("id") Long id, Model model) {
		ZrzhDzznEntiy dzzn =zrzhDzznService.findById(id);
		model.addAttribute("dzzn",dzzn);
		model.addAttribute("action","view");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		
		return "erm/zrzh/dzzn/view";
	}
	
	
	/**
	 * 导出地质灾害信息word
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("zrzh:dzzn:exportword")
	@RequestMapping(value = "exportword/{id}")
	@ResponseBody
	public String getexportword(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = zrzhDzznService.getExportWord(id);
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "dzzn.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
	

}
