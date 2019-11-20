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
import com.cczu.model.zrzh.entity.ZrzhYxbdEntiy;
import com.cczu.model.zrzh.service.ZrzhYxbdService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;
/**
 * 自然灾害类-雨雪冰冻信息快报Controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("zrzh/yxbd")
public class ZrzhYxbdController extends BaseController{
	
	@Autowired
	private BisQyjbxxServiceImpl qyjbxxServiceImpl;
	@Autowired
	private ZrzhYxbdService zrzhYxbdService;
	
	
	
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
		return "erm/zrzh/yxbd/index";
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
		map.put("m1", request.getParameter("zrzh_yxbd_m1"));
		map.put("m2", request.getParameter("zrzh_yxbd_m2"));
		map.putAll(getAuthorityMap());
		return zrzhYxbdService.dataGrid(map);

	}
	
	
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("zrzh:yxbd:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createtz(Model model) {
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		model.addAttribute("action", "create");
		return "erm/zrzh/yxbd/form";

	}
	
	
	/**
	 * 添加雨雪冰冻信息
	 * @param yxbd
	 * @param model
	 * @return
	 */
	@RequiresPermissions("zrzh:yxbd:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(ZrzhYxbdEntiy yxbd, Model model) {
		String datasuccess = "success";
		Timestamp t=DateUtils.getSysTimestamp();
		yxbd.setS1(t);
		yxbd.setS2(t);
		yxbd.setS3(0);
		zrzhYxbdService.addInfo(yxbd);
		return datasuccess;
	}
	
	
	/**
	 * 修改页面 跳转
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("zrzh:yxbd:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updatetz(@PathVariable("id") Long id, Model model) {
		ZrzhYxbdEntiy yxbd =zrzhYxbdService.findById(id);
		model.addAttribute("yxbd",yxbd);
		model.addAttribute("action", "update");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "erm/zrzh/yxbd/form";
	}
	
	
	/**
	 * 修改雨雪冰冻信息
	 * @param yxbd
	 * @param model
	 * @return
	 */
	@RequiresPermissions("zrzh:yxbd:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(ZrzhYxbdEntiy yxbd, Model model) {
		String datasuccess = "success";
		ZrzhYxbdEntiy yxbd1 =zrzhYxbdService.findById(yxbd.getID());
		yxbd1.setM0(yxbd.getM0());
		yxbd1.setM0_1(yxbd.getM0_1());
		yxbd1.setM1(yxbd.getM1());
		yxbd1.setM2(yxbd.getM2());
		yxbd1.setM3(yxbd.getM3());
		yxbd1.setM4(yxbd.getM4());
		yxbd1.setM4_1(yxbd.getM4_1());
		yxbd1.setM5(yxbd.getM5());
		yxbd1.setM6(yxbd.getM6());
		yxbd1.setM7(yxbd.getM7());
		yxbd1.setM8(yxbd.getM8());
		yxbd1.setM9(yxbd.getM9());
		yxbd1.setM12(yxbd.getM12());
		yxbd1.setM13(yxbd.getM13());
		yxbd1.setM14(yxbd.getM14());
		yxbd1.setM15(yxbd.getM15());
		yxbd1.setM16(yxbd.getM16());
		yxbd1.setS2(DateUtils.getSysTimestamp());
		zrzhYxbdService.updateInfo(yxbd1);
		return datasuccess;
	}
	
	
	/**
	 * 依据id删除雨雪冰冻信息
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("zrzh:yxbd:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		String[] aids = ids.split(",");
		for (int i = 0; i < aids.length; i++) {
			zrzhYxbdService.deleteInfo(Long.parseLong(aids[i]));
		}
		return datasuccess;
	}
	
	
	
	/**
	 * 查看雨雪冰冻信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("zrzh:yxbd:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String viewtz(@PathVariable("id") Long id, Model model) {
		ZrzhYxbdEntiy yxbd =zrzhYxbdService.findById(id);
		model.addAttribute("yxbd",yxbd);
		model.addAttribute("action","view");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		
		return "erm/zrzh/yxbd/view";
	}
	
	
	/**
	 * 导出雨雪冰冻信息word
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("zrzh:yxbd:exportword")
	@RequestMapping(value = "exportword/{id}")
	@ResponseBody
	public String getexportword(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = zrzhYxbdService.getExportWord(id);
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "yxbd.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
