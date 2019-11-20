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
import com.cczu.model.zrzh.entity.ZrzhDzxxEntiy;
import com.cczu.model.zrzh.service.ZrzhDzxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;
/**
 * 自然灾害类-地震信息快报Controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("zrzh/dzxx")
public class ZrzhDzxxController extends BaseController{
	
	@Autowired
	private BisQyjbxxServiceImpl qyjbxxServiceImpl;
	@Autowired
	private ZrzhDzxxService zrzhDzxxService;
	
	
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
		return "erm/zrzh/dzxx/index";
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
		map.put("m1", request.getParameter("zrzh_dzxx_m1"));
		map.put("m2", request.getParameter("zrzh_dzxx_m2"));
		map.putAll(getAuthorityMap());
		return zrzhDzxxService.dataGrid(map);

	}
	
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("zrzh:dzxx:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createtz(Model model) {
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		model.addAttribute("action", "create");
		return "erm/zrzh/dzxx/form";

	}
	
	
	/**
	 * 添加地震信息
	 * @param dzxx
	 * @param model
	 * @return
	 */
	@RequiresPermissions("zrzh:dzxx:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(ZrzhDzxxEntiy dzxx, Model model) {
		String datasuccess = "success";
		Timestamp t=DateUtils.getSysTimestamp();
		dzxx.setS1(t);
		dzxx.setS2(t);
		dzxx.setS3(0);
		zrzhDzxxService.addInfo(dzxx);
		return datasuccess;
	}
	
	
	/**
	 * 修改页面 跳转
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("zrzh:dzxx:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updatetz(@PathVariable("id") Long id, Model model) {
		ZrzhDzxxEntiy dzxx =zrzhDzxxService.findById(id);
		model.addAttribute("dzxx",dzxx);
		model.addAttribute("action", "update");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "erm/zrzh/dzxx/form";
	}
	
	
	/**
	 * 修改地震信息
	 * @param dzxx
	 * @param model
	 * @return
	 */
	@RequiresPermissions("zrzh:dzxx:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(ZrzhDzxxEntiy dzxx, Model model) {
		String datasuccess = "success";
		ZrzhDzxxEntiy dzxx1 =zrzhDzxxService.findById(dzxx.getID());
		dzxx1.setM0(dzxx.getM0());
		dzxx1.setM0_1(dzxx.getM0_1());
		dzxx1.setM1(dzxx.getM1());
		dzxx1.setM2(dzxx.getM2());
		dzxx1.setM3(dzxx.getM3());
		dzxx1.setM4(dzxx.getM4());
		dzxx1.setM4_1(dzxx.getM4_1());
		dzxx1.setM5(dzxx.getM5());
		dzxx1.setM6(dzxx.getM6());
		dzxx1.setM7(dzxx.getM7());
		dzxx1.setM8(dzxx.getM8());
		dzxx1.setM9(dzxx.getM9());
		dzxx1.setM12(dzxx.getM12());
		dzxx1.setM13(dzxx.getM13());
		dzxx1.setM14(dzxx.getM14());
		dzxx1.setM15(dzxx.getM15());
		dzxx1.setM16(dzxx.getM16());
		dzxx1.setS2(DateUtils.getSysTimestamp());
		zrzhDzxxService.updateInfo(dzxx1);
		return datasuccess;
	}
	
	
	
	/**
	 * 依据id删除地震信息
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("zrzh:dzxx:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		String[] aids = ids.split(",");
		for (int i = 0; i < aids.length; i++) {
			zrzhDzxxService.deleteInfo(Long.parseLong(aids[i]));
		}
		return datasuccess;
	}
	
	
	
	/**
	 * 查看地震信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("zrzh:dzxx:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String viewtz(@PathVariable("id") Long id, Model model) {
		ZrzhDzxxEntiy dzxx =zrzhDzxxService.findById(id);
		model.addAttribute("dzxx",dzxx);
		model.addAttribute("action","view");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		
		return "erm/zrzh/dzxx/view";
	}
	
	
	
	/**
	 * 导出地震信息word
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("zrzh:dzxx:exportword")
	@RequestMapping(value = "exportword/{id}")
	@ResponseBody
	public String getexportword(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = zrzhDzxxService.getExportWord(id);
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "dzxx.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
