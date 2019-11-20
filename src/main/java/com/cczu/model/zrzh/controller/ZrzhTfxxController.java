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
import com.cczu.model.sgzn.entity.SgznHzsgEntity;
import com.cczu.model.zrzh.entity.ZrzhTfxxEntiy;
import com.cczu.model.zrzh.service.ZrzhTfxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;
/**
 * 自然灾害类-台风信息快报Controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("zrzh/tfxx")
public class ZrzhTfxxController extends BaseController{
	
	
	@Autowired
	private BisQyjbxxServiceImpl qyjbxxServiceImpl;
	
	@Autowired
	private ZrzhTfxxService zrzhTfxxService;
	
	
	
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
		return "erm/zrzh/tfxx/index";
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
		map.put("m1", request.getParameter("zrzh_tfxx_m1"));
		map.put("m2", request.getParameter("zrzh_tfxx_m2"));
		map.putAll(getAuthorityMap());
		return zrzhTfxxService.dataGrid(map);

	}
	
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("zrzh:tfxx:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createtz(Model model) {
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		model.addAttribute("action", "create");
		return "erm/zrzh/tfxx/form";

	}
	
	
	
	/**
	 * 添加台风信息
	 * @param tfxx
	 * @param model
	 * @return
	 */
	@RequiresPermissions("zrzh:tfxx:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(ZrzhTfxxEntiy tfxx, Model model) {
		String datasuccess = "success";
		Timestamp t=DateUtils.getSysTimestamp();
		tfxx.setS1(t);
		tfxx.setS2(t);
		tfxx.setS3(0);
		zrzhTfxxService.addInfo(tfxx);
		return datasuccess;
	}
	
	
	/**
	 * 修改页面 跳转
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("zrzh:tfxx:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updatetz(@PathVariable("id") Long id, Model model) {
		ZrzhTfxxEntiy tfxx =zrzhTfxxService.findById(id);
		model.addAttribute("tfxx",tfxx);
		model.addAttribute("action", "update");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "erm/zrzh/tfxx/form";
	}
	
	
	
	/**
	 * 修改安全事故信息
	 * @param tfxx
	 * @param model
	 * @return
	 */
	@RequiresPermissions("zrzh:tfxx:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(ZrzhTfxxEntiy tfxx, Model model) {
		String datasuccess = "success";
		ZrzhTfxxEntiy tfxx1 =zrzhTfxxService.findById(tfxx.getID());
		tfxx1.setM0(tfxx.getM0());
		tfxx1.setM0_1(tfxx.getM0_1());
		tfxx1.setM1(tfxx.getM1());
		tfxx1.setM2(tfxx.getM2());
		tfxx1.setM3(tfxx.getM3());
		tfxx1.setM4(tfxx.getM4());
		tfxx1.setM5(tfxx.getM5());
		tfxx1.setM6(tfxx.getM6());
		tfxx1.setM7(tfxx.getM7());
		tfxx1.setM7_1(tfxx.getM7_1());
		tfxx1.setM8(tfxx.getM8());
		tfxx1.setM8_1(tfxx.getM8_1());
		tfxx1.setM8_2(tfxx.getM8_2());
		tfxx1.setM8_3(tfxx.getM8_3());
		tfxx1.setM9(tfxx.getM9());
		tfxx1.setM9_1(tfxx.getM9_1());
		tfxx1.setM9_2(tfxx.getM9_2());
		tfxx1.setM10(tfxx.getM10());
		tfxx1.setM10_1(tfxx.getM10_1());
		tfxx1.setM12(tfxx.getM12());
		tfxx1.setM13(tfxx.getM13());
		tfxx1.setM14(tfxx.getM14());
		tfxx1.setM15(tfxx.getM15());
		tfxx1.setM16(tfxx.getM16());
		tfxx1.setS2(DateUtils.getSysTimestamp());
		zrzhTfxxService.updateInfo(tfxx1);
		return datasuccess;
	}
	
	
	/**
	 * 依据id删除火灾事故信息
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("zrzh:tfxx:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		String[] aids = ids.split(",");
		for (int i = 0; i < aids.length; i++) {
			zrzhTfxxService.deleteInfo(Long.parseLong(aids[i]));
		}
		return datasuccess;
	}
	
	
	/**
	 * 查看台风信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("zrzh:tfxx:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String viewtz(@PathVariable("id") Long id, Model model) {
		ZrzhTfxxEntiy tfxx =zrzhTfxxService.findById(id);
		model.addAttribute("tfxx",tfxx);
		model.addAttribute("action","view");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		
		return "erm/zrzh/tfxx/view";
	}
	
	
	/**
	 * 导出火灾事故word
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("zrzh:tfxx:exportword")
	@RequestMapping(value = "exportword/{id}")
	@ResponseBody
	public String getexportword(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = zrzhTfxxService.getExportWord(id);
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "tfxx.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
	
	
	
	
	
	
	
	
	
}
