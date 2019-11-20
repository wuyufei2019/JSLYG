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
import com.cczu.model.zrzh.entity.ZrzhJzxxEntiy;
import com.cczu.model.zrzh.service.ZrzhJzxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;
/**
 * 自然灾害类-救助信息快报Controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("zrzh/jzxx")
public class ZrzhJzxxController extends BaseController{
	
	
	@Autowired
	private BisQyjbxxServiceImpl qyjbxxServiceImpl;
	@Autowired
	private ZrzhJzxxService zrzhJzxxService;
	
	
	
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
		return "erm/zrzh/jzxx/index";
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
		map.put("m2", request.getParameter("zrzh_jzxx_m2"));//（洪涝、雨雪冰冻等）
		map.putAll(getAuthorityMap());
		return zrzhJzxxService.dataGrid(map);

	}
	
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("zrzh:jzxx:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createtz(Model model) {
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		model.addAttribute("action", "create");
		return "erm/zrzh/jzxx/form";

	}
	
	
	/**
	 * 添加救助信息
	 * @param jzxx
	 * @param model
	 * @return
	 */
	@RequiresPermissions("zrzh:jzxx:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(ZrzhJzxxEntiy jzxx, Model model) {
		String datasuccess = "success";
		Timestamp t=DateUtils.getSysTimestamp();
		jzxx.setS1(t);
		jzxx.setS2(t);
		jzxx.setS3(0);
		zrzhJzxxService.addInfo(jzxx);
		return datasuccess;
	}
	
	
	/**
	 * 修改页面 跳转
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("zrzh:jzxx:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updatetz(@PathVariable("id") Long id, Model model) {
		ZrzhJzxxEntiy jzxx =zrzhJzxxService.findById(id);
		model.addAttribute("jzxx",jzxx);
		model.addAttribute("action", "update");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "erm/zrzh/jzxx/form";
	}
	
	
	
	
	/**
	 * 修改救助信息
	 * @param jzxx
	 * @param model
	 * @return
	 */
	@RequiresPermissions("zrzh:jzxx:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(ZrzhJzxxEntiy jzxx, Model model) {
		String datasuccess = "success";
		ZrzhJzxxEntiy jzxx1 =zrzhJzxxService.findById(jzxx.getID());
		jzxx1.setM0(jzxx.getM0());
		jzxx1.setM0_1(jzxx.getM0_1());
		jzxx1.setM2(jzxx.getM2());
		jzxx1.setM3(jzxx.getM3());
		jzxx1.setM4(jzxx.getM4());
		jzxx1.setM4_1(jzxx.getM4_1());
		jzxx1.setM5(jzxx.getM5());
		jzxx1.setM6(jzxx.getM6());
		jzxx1.setM7(jzxx.getM7());
		jzxx1.setM7_1(jzxx.getM7_1());
		jzxx1.setM10(jzxx.getM10());
		jzxx1.setM11(jzxx.getM11());
		jzxx1.setM12(jzxx.getM12());
		jzxx1.setM13(jzxx.getM13());
		jzxx1.setM14(jzxx.getM14());
		jzxx1.setM15(jzxx.getM15());
		jzxx1.setM16(jzxx.getM16());
		jzxx1.setS2(DateUtils.getSysTimestamp());
		zrzhJzxxService.updateInfo(jzxx1);
		return datasuccess;
	}
	
	
	/**
	 * 依据id删除救助信息
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("zrzh:jzxx:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		String[] aids = ids.split(",");
		for (int i = 0; i < aids.length; i++) {
			zrzhJzxxService.deleteInfo(Long.parseLong(aids[i]));
		}
		return datasuccess;
	}
	
	
	
	/**
	 * 查看救助信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("zrzh:jzxx:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String viewtz(@PathVariable("id") Long id, Model model) {
		ZrzhJzxxEntiy jzxx =zrzhJzxxService.findById(id);
		model.addAttribute("jzxx",jzxx);
		model.addAttribute("action","view");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		
		return "erm/zrzh/jzxx/view";
	}
	
	
	
	/**
	 * 导出救助信息word
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("zrzh:jzxx:exportword")
	@RequestMapping(value = "exportword/{id}")
	@ResponseBody
	public String getexportword(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = zrzhJzxxService.getExportWord(id);
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "jzxx.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
	
	
	
	
	
	
	
	
	
	
}
