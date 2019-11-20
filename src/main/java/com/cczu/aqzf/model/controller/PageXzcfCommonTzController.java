package com.cczu.aqzf.model.controller;

import com.cczu.aqzf.model.entity.AQZF_SetBasicInfoEntity;
import com.cczu.aqzf.model.entity.XZCF_GzsInfoEntity;
import com.cczu.aqzf.model.entity.XZCF_YbcfLaspEntity;
import com.cczu.aqzf.model.entity.XZCF_YbcfTzgzEntity;
import com.cczu.aqzf.model.service.*;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.UserService;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;
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
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Map;

/**
 * 行政处罚-简易处罚-告知controller
 * 
 * @author jason
 * 
 */
@Controller
@RequestMapping("xzcf/ybcf/tzgz")
public class PageXzcfCommonTzController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	@Autowired
	private IXzcfCommonTzService xzcfcommontzservice;
	@Autowired
	private IPunishSimpleGzService punishsimplegzservice;
	@Autowired
	private IXzcfCommonLaspService xzcfcommonlaspservice;
	@Autowired
	private AqzfSetNumberService setNumberService;
	@Autowired
	private AqzfSetBasicInfoService aqzfsetbasicinfoservice;
	@Autowired
	private XzcfCommonDcService xzcfcommondcservice;
	@Autowired
	private AqzfJcnrService aqzfJcnrService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {
		return "aqzf/xzcf/ybcf/tzgz/index";
	}

	/**
	 * list页面
	 */
	@RequiresPermissions("ybcf:tzgz:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("name", request.getParameter("ybcf_tzgz_name"));
		map.put("number", request.getParameter("ybcf_tzgz_number"));
		map.put("startdate", request.getParameter("ybcf_tzgz_startdate"));
		map.put("enddate", request.getParameter("ybcf_tzgz_enddate"));
		map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());
		return xzcfcommontzservice.dataGrid(map);
	}
	
	/**
	 * 添加告知信息页面跳转
	 * @param model
	 */
	@RequiresPermissions("ybcf:tzgz:add")
	@RequestMapping(value = "create/{id}")
	public String create(@PathVariable("id") long id,Model model) {
		XZCF_GzsInfoEntity gie=punishsimplegzservice.findInfoByLaId(id);
		XZCF_YbcfTzgzEntity tzgz = new XZCF_YbcfTzgzEntity();
		tzgz.setName(gie.getName());
		tzgz.setIllegalact(gie.getIllegalact());
		tzgz.setWfxw(gie.getWfxw());
		tzgz.setEvidence(gie.getEvidence());
		tzgz.setUnlaw(gie.getUnlaw());
		tzgz.setEnlaw(gie.getEnlaw());
		tzgz.setXzcf(gie.getXzcf());
		tzgz.setPunishresult(gie.getPunishresult());
		tzgz.setId1(id);
		tzgz.setContactname(gie.getContactname());
		tzgz.setPhone(gie.getPhone());
		model.addAttribute("id1", id);
		model.addAttribute("yte", tzgz);
		model.addAttribute("action", "createSub");
		return "aqzf/xzcf/ybcf/tzgz/form";
	}

	/**
	 * 添加听证告知
	 * @param request
	 */
	@RequiresPermissions("ybcf:tzgz:add")
	@RequestMapping(value = "createSub", method = RequestMethod.POST)
	@ResponseBody
	public String createSub(XZCF_YbcfTzgzEntity yte,
                            HttpServletRequest request, Model model) throws IOException {
		String datasuccess = "failed";
		Timestamp t = DateUtils.getSysTimestamp();
		yte.setS1(t);
		yte.setS2(t);
		yte.setS3(0);
		
		//设置编号
		AQZF_SetBasicInfoEntity sbe=aqzfsetbasicinfoservice.findInfor();
		XZCF_YbcfLaspEntity ybcf = xzcfcommonlaspservice.findInfoById(yte.getId1());
		String m0 = ybcf.getNumber();
		if(ybcf.getCxflag().equals("1")){
			//已立案
			yte.setNumber("（"+sbe.getSsqjc()+"）应急听告〔"+m0.substring(m0.indexOf("〔")+1, m0.indexOf("〕"))+"〕"+m0.substring(m0.indexOf("〕")+1, m0.length()));
		}else{
			//预立案
			yte.setNumber("预立案");
		}
		
		if (xzcfcommontzservice.addInfoReturnID(yte) > 0) {
			XZCF_YbcfLaspEntity yle= xzcfcommonlaspservice.findInfoById(yte.getId1());
			yle.setTzflag("1");
			xzcfcommonlaspservice.updateInfo(yle);
			datasuccess = "success";
		}
		return datasuccess;
	}

	/**
	 * 修改计划信息页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("ybcf:tzgz:update")
	@RequestMapping(value = "update/{id}")
	public String update(@PathVariable("id") long id, Model model) {

		XZCF_YbcfTzgzEntity yte = xzcfcommontzservice.findInfoById(id);
		model.addAttribute("yte", yte);
		//model.addAttribute("jid", id);
		model.addAttribute("action", "updateSub");
		return "aqzf/xzcf/ybcf/tzgz/form";
	}

	/**
	 * 修改告知信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("ybcf:tzgz:update")
	@RequestMapping(value = "updateSub")
	@ResponseBody
	public String updateSub(XZCF_YbcfTzgzEntity yte,
                            HttpServletRequest request, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		yte.setS2(t);
		try {
			xzcfcommontzservice.updateInfo(yte);
		} catch (Exception e) {
			// TODO: handle exception
			datasuccess="failed";
		}
		return datasuccess;
	}

	/**
	 * 查看告知信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("ybcf:tzgz:view")
	@RequestMapping(value = "view/{id}")
	public String view(@PathVariable("id") long id, Model model) {
		XZCF_YbcfTzgzEntity yte = xzcfcommontzservice.findInfoById(id);
		model.addAttribute("yte", yte);
		return "aqzf/xzcf/ybcf/tzgz/view";
	}

	/**
	 * 删除告知信息
	 * 
	 * @param id
	 * @return
	 */
	@RequiresPermissions("ybcf:tzgz:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功！";
		// 可以批量删除
		String[] arrids = ids.split(",");
		try {
			for (int i = 0; i < arrids.length; i++) {
				xzcfcommontzservice.deleteInfo(Long.parseLong(arrids[i]));
				xzcfcommontzservice.updateLaspInfo(Long.parseLong(arrids[i]));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			datasuccess="删除失败";
		}
		return datasuccess;
	}
	
	
	/**
	 * 导出告知书记录word
	 * 
	 */
	@RequiresPermissions("ybcf:tzgz:export")
	@RequestMapping(value = "exporttz/{flag}/{id}")
	@ResponseBody
	public String getAjWord(@PathVariable("flag") String  flag,@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
	    Map<String, Object> map = xzcfcommontzservice.getWsdcword(id,flag);
		//设置导出的文件名
	    String filename = map.get("number").toString() + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "tzgzrecord.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
	/**
	 * 总览查看告知信息跳转
	 * @param model
	 */
	@RequestMapping(value = "viewtzgz/{id}")
	public String viewtzgz(@PathVariable("id") long id, Model model) {
		XZCF_YbcfTzgzEntity yte = xzcfcommontzservice.findInfoByLaId(id);
		model.addAttribute("yte", yte);
		return "aqzf/xzcf/ybcf/tzgz/view";
	}
}
