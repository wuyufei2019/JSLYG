package com.cczu.aqzf.model.controller;

import com.cczu.aqzf.model.entity.*;
import com.cczu.aqzf.model.service.*;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.StringUtils;
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
 * 行政处罚--强制执行controller
 * 
 * @author jason
 * 
 */
@Controller
@RequestMapping("xzcf/ybcf/qzzx")
public class PageXzcfCommonQzzxController extends BaseController {

	@Autowired
	private XzcfCommonQzzxService xzcfcommonqzzxservice;
	@Autowired
	private IXzcfCommonLaspService xzcfcommonlaspservice;
	@Autowired
	private IXzcfCfjdService xzcfcfjdservice;
	@Autowired
	private AqzfSetNumberService setNumberService;
	@Autowired
	private AqzfSetBasicInfoService aqzfsetbasicinfoservice;
	@Autowired
	private XzcfCommonSxcgService xzcfcommonsxcgservice;
	@Autowired
	private AqzfZfryService aqzfZfryService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {
		return "aqzf/xzcf/ybcf/qzzx/index";
	}

	/**
	 * list页面
	 */
	@RequiresPermissions("xzcf:qzzx:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("number", request.getParameter("xzcf_qzzx_number"));
		map.put("name", request.getParameter("xzcf_qzzx_name"));
		map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());
		return xzcfcommonqzzxservice.dataGrid(map);
	}
	

	/**
	 * 添加强制执行信息页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("xzcf:qzzx:add")
	@RequestMapping(value = "create/{id}")
	public String create(@PathVariable("id") long id,Model model) {
		XZCF_YbcfSxcgEntity yse=xzcfcommonsxcgservice.findInfoById(id);
		XZCF_CfjdInfoEntity cfjd= xzcfcfjdservice.findInfoByLaId(yse.getId1());
		AQZF_SetBasicInfoEntity sbi=aqzfsetbasicinfoservice.findInfor();
		XZCF_YbcfQzzxEntity yqe =new XZCF_YbcfQzzxEntity();
		yqe.setDsname(cfjd.getPunishname());
		yqe.setCourt(sbi.getCourt());
		
		XZCF_YbcfLaspEntity ybcf = xzcfcommonlaspservice.findInfoById(yse.getId1());
		if(!StringUtils.isEmpty(ybcf.getEnforcer1())){
			AQZF_TipstaffEntity xzry=aqzfZfryService.findBym1(ybcf.getEnforcer1());
			if(xzry!=null){
				yqe.setContactname(xzry.getM1());
				yqe.setPhone(xzry.getM5());
			}
		}
		
		model.addAttribute("id1", yse.getId1());
		model.addAttribute("yqe", yqe);
		model.addAttribute("action", "createSub");
		return "aqzf/xzcf/ybcf/qzzx/form";
	}

	/**
	 * 添加强制执行
	 * @param request
	 */
	@RequiresPermissions("xzcf:qzzx:add")
	@RequestMapping(value = "createSub", method = RequestMethod.POST)
	@ResponseBody
	public String createSub(XZCF_YbcfQzzxEntity yqe,
                            HttpServletRequest request, Model model) throws IOException {
		String datasuccess = "failed";
		Timestamp t = DateUtils.getSysTimestamp();
		yqe.setS1(t);
		yqe.setS2(t);
		yqe.setS3(0);
		
		//设置编号
		AQZF_SetBasicInfoEntity bh =aqzfsetbasicinfoservice.findInfor();
		XZCF_YbcfLaspEntity ybcf = xzcfcommonlaspservice.findInfoById(yqe.getId1());
		String m0 = ybcf.getNumber();
		if(ybcf.getCxflag().equals("1")){
			//已立案
			yqe.setNumber("（"+bh.getSsqjc()+"）应急强执〔"+m0.substring(m0.indexOf("〔")+1, m0.indexOf("〕"))+"〕"+m0.substring(m0.indexOf("〕")+1, m0.length()));
		}else{
			//预立案
			yqe.setNumber("预立案");
		}
		
		if (xzcfcommonqzzxservice.addInfoReturnID(yqe) > 0) {
			XZCF_YbcfLaspEntity yle= xzcfcommonlaspservice.findInfoById(yqe.getId1());
			yle.setQzflag("1");
			xzcfcommonlaspservice.updateInfo(yle);
			datasuccess = "success";
		}
		return datasuccess;
	}

	/**
	 * 修改强制执行信息页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("xzcf:qzzx:update")
	@RequestMapping(value = "update/{id}")
	public String update(@PathVariable("id") long id, Model model) {

		XZCF_YbcfQzzxEntity yqe = xzcfcommonqzzxservice.findInfoById(id);
		model.addAttribute("yqe", yqe);
		model.addAttribute("id1", yqe.getId1());
		model.addAttribute("action", "updateSub");
		return "aqzf/xzcf/ybcf/qzzx/form";
	}

	/**
	 * 修改强制执行信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("xzcf:qzzx:update")
	@RequestMapping(value = "updateSub")
	@ResponseBody
	public String updateSub(XZCF_YbcfQzzxEntity yqe,
                            HttpServletRequest request, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		yqe.setS2(t);
		try {
			xzcfcommonqzzxservice.updateInfo(yqe);
		} catch (Exception e) {
			// TODO: handle exception
			datasuccess="failed";
		}
		return datasuccess;
	}

	/**
	 * 查看强制执行信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("xzcf:qzzx:view")
	@RequestMapping(value = "view/{id}")
	public String view(@PathVariable("id") long id, Model model) {
		XZCF_YbcfQzzxEntity yqe = xzcfcommonqzzxservice.findInfoById(id);
		model.addAttribute("yqe", yqe);
		return "aqzf/xzcf/ybcf/qzzx/view";
	}

	/**
	 * 删除强制执行信息
	 * 
	 * @param id
	 * @return
	 */
	@RequiresPermissions("xzcf:qzzx:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		try {
			for (int i = 0; i < arrids.length; i++) {
				xzcfcommonqzzxservice.deleteInfo(Long.parseLong(arrids[i]));
				xzcfcommonqzzxservice.updateLaspInfo(Long.parseLong(arrids[i]));
			}
		} catch (Exception e) {
			// TODO: handle exception
			datasuccess="删除失败";
		}
		
		return datasuccess;
	}
	
	/**
	 * 导出强制执行记录word
	 */
	@RequiresPermissions("xzcf:qzzx:export")
	@RequestMapping(value = "exportqzzx/{flag}/{id}")
	@ResponseBody
	public String getAjWord(@PathVariable("flag") String  flag,@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = xzcfcommonqzzxservice.getWsdcword(id,flag);
		//设置导出的文件名
		String filename = map.get("number").toString() + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "xzcfqzzx.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
}
