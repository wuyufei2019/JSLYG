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
import com.cczu.model.zrzh.entity.ZrzhShxxEntiy;
import com.cczu.model.zrzh.service.ZrzhShxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;
/**
 * 自然灾害类-水旱信息快报Controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("zrzh/shxx")
public class ZrzhShxxController extends BaseController{
	
	
	@Autowired
	private BisQyjbxxServiceImpl qyjbxxServiceImpl;
	@Autowired
	private ZrzhShxxService zrzhShxxService;
	
	
	
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
		return "erm/zrzh/shxx/index";
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
		map.put("m1", request.getParameter("zrzh_shxx_m1"));
		map.put("m2_1", request.getParameter("zrzh_shxx_m2_1"));
		map.putAll(getAuthorityMap());
		return zrzhShxxService.dataGrid(map);

	}
	
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("zrzh:shxx:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createtz(Model model) {
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		model.addAttribute("action", "create");
		return "erm/zrzh/shxx/form";

	}
	
	
	/**
	 * 添加水旱信息
	 * @param shxx
	 * @param model
	 * @return
	 */
	@RequiresPermissions("zrzh:shxx:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(ZrzhShxxEntiy shxx, Model model) {
		String datasuccess = "success";
		Timestamp t=DateUtils.getSysTimestamp();
		shxx.setS1(t);
		shxx.setS2(t);
		shxx.setS3(0);
		zrzhShxxService.addInfo(shxx);
		return datasuccess;
	}
	
	
	/**
	 * 修改页面 跳转
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("zrzh:shxx:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updatetz(@PathVariable("id") Long id, Model model) {
		ZrzhShxxEntiy shxx =zrzhShxxService.findById(id);
		model.addAttribute("shxx",shxx);
		model.addAttribute("action", "update");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "erm/zrzh/shxx/form";
	}
	
	
	
	/**
	 * 修改安全事故信息
	 * @param shxx
	 * @param model
	 * @return
	 */
	@RequiresPermissions("zrzh:shxx:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(ZrzhShxxEntiy shxx, Model model) {
		String datasuccess = "success";
		ZrzhShxxEntiy shxx1 =zrzhShxxService.findById(shxx.getID());
		shxx1.setM0(shxx.getM0());
		shxx1.setM0_1(shxx.getM0_1());
		shxx1.setM1(shxx.getM1());
		shxx1.setM2(shxx.getM2());
		shxx1.setM2_1(shxx.getM2_1());
		shxx1.setM3(shxx.getM3());
		shxx1.setM4(shxx.getM4());
		shxx1.setM5(shxx.getM5());
		shxx1.setM6(shxx.getM6());
		shxx1.setM7(shxx.getM7());
		shxx1.setM8(shxx.getM8());
		shxx1.setM9(shxx.getM9());
		shxx1.setM9_1(shxx.getM9_1());
		shxx1.setM9_2(shxx.getM9_2());
		shxx1.setM9_3(shxx.getM9_3());
		shxx1.setM12(shxx.getM12());
		shxx1.setM13(shxx.getM13());
		shxx1.setM14(shxx.getM14());
		shxx1.setM15(shxx.getM15());
		shxx1.setM16(shxx.getM16());
		shxx1.setS2(DateUtils.getSysTimestamp());
		zrzhShxxService.updateInfo(shxx1);
		return datasuccess;
	}
	
	
	/**
	 * 依据id删除水旱事故信息
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("zrzh:shxx:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		String[] aids = ids.split(",");
		for (int i = 0; i < aids.length; i++) {
			zrzhShxxService.deleteInfo(Long.parseLong(aids[i]));
		}
		return datasuccess;
	}
	
	
	
	/**
	 * 查看水旱信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("zrzh:shxx:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String viewtz(@PathVariable("id") Long id, Model model) {
		ZrzhShxxEntiy shxx =zrzhShxxService.findById(id);
		model.addAttribute("shxx",shxx);
		model.addAttribute("action","view");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		
		return "erm/zrzh/shxx/view";
	}
	
	
	
	/**
	 * 导出水旱信息word
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("zrzh:shxx:exportword")
	@RequestMapping(value = "exportword/{id}")
	@ResponseBody
	public String getexportword(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = zrzhShxxService.getExportWord(id);
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "shxx.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
