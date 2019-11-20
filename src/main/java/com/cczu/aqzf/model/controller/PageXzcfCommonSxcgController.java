package com.cczu.aqzf.model.controller;

import com.cczu.aqzf.model.entity.AQZF_SetBasicInfoEntity;
import com.cczu.aqzf.model.entity.XZCF_CfjdInfoEntity;
import com.cczu.aqzf.model.entity.XZCF_YbcfLaspEntity;
import com.cczu.aqzf.model.entity.XZCF_YbcfSxcgEntity;
import com.cczu.aqzf.model.service.*;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
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
 * 行政处罚--事先催告controller
 * 
 * @author jason
 * 
 */
@Controller
@RequestMapping("xzcf/ybcf/sxcg")
public class PageXzcfCommonSxcgController extends BaseController {

	@Autowired
	private XzcfCommonSxcgService xzcfcommonsxcgservice;
	@Autowired
	private IXzcfCommonLaspService xzcfcommonlaspservice;
	@Autowired
	private IXzcfCfjdService xzcfcfjdservice;
	@Autowired
	private AqzfSetNumberService setNumberService;
	@Autowired
	private AqzfSetBasicInfoService aqzfsetbasicinfoservice;
	/**
	 * 默认页面
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {
		return "aqzf/xzcf/ybcf/sxcg/index";
	}

	/**
	 * list页面
	 */
	@RequiresPermissions("xzcf:sxcg:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("number", request.getParameter("xzcf_sxcg_number"));
		map.put("name", request.getParameter("xzcf_sxcg_name"));
		map.put("type", request.getParameter("xzcf_sxcg_type"));
		map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());
		return xzcfcommonsxcgservice.dataGrid(map);
	}
	

	/**
	 * 添加事先催告信息页面跳转
	 * id:处罚决定id
	 * @param model
	 */
	@RequiresPermissions("xzcf:sxcg:add")
	@RequestMapping(value = "create/{id}")
	public String create(@PathVariable("id") long id,Model model) {
		
		XZCF_CfjdInfoEntity cfjd= xzcfcfjdservice.findInfoById(id);
		model.addAttribute("id1", cfjd.getId1());
		XZCF_YbcfSxcgEntity yse =new XZCF_YbcfSxcgEntity();
		yse.setQyname(cfjd.getPunishname());
		yse.setXzjd(cfjd.getXzcf());
		model.addAttribute("yse", yse);
		model.addAttribute("action", "createSub");
		return "aqzf/xzcf/ybcf/sxcg/form";
	}

	/**
	 * 添加
	 * @param request
	 */
	@RequiresPermissions("xzcf:sxcg:add")
	@RequestMapping(value = "createSub", method = RequestMethod.POST)
	@ResponseBody
	public String createSub(XZCF_YbcfSxcgEntity yse,
                            HttpServletRequest request, Model model) throws IOException {
		String datasuccess = "failed";
		Timestamp t = DateUtils.getSysTimestamp();
		yse.setS1(t);
		yse.setS2(t);
		yse.setS3(0);
		
		//设置编号
		AQZF_SetBasicInfoEntity bh =aqzfsetbasicinfoservice.findInfor();
		XZCF_YbcfLaspEntity ybcf = xzcfcommonlaspservice.findInfoById(yse.getId1());
		String m0 = ybcf.getNumber();
		if(ybcf.getCxflag().equals("1")){
			//已立案
			yse.setNumber("（"+bh.getSsqjc()+"）应急执行催告〔"+m0.substring(m0.indexOf("〔")+1, m0.indexOf("〕"))+"〕"+m0.substring(m0.indexOf("〕")+1, m0.length()));
		}else{
			//预立案
			yse.setNumber("预立案");
		}
		
		if (xzcfcommonsxcgservice.addInfoReturnID(yse) > 0) {
			XZCF_YbcfLaspEntity yle= xzcfcommonlaspservice.findInfoById(yse.getId1());
			yle.setCgflag("1");
			xzcfcommonlaspservice.updateInfo(yle);
			datasuccess = "success";
		}
		return datasuccess;
	}

	/**
	 * 修改事先催告信息页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("xzcf:sxcg:update")
	@RequestMapping(value = "update/{id}")
	public String update(@PathVariable("id") long id, Model model) {

		XZCF_YbcfSxcgEntity yse = xzcfcommonsxcgservice.findInfoById(id);
		model.addAttribute("yse", yse);
		//model.addAttribute("jid", id);
		model.addAttribute("action", "updateSub");
		return "aqzf/xzcf/ybcf/sxcg/form";
	}

	/**
	 * 修改事先催告信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("xzcf:sxcg:update")
	@RequestMapping(value = "updateSub")
	@ResponseBody
	public String updateSub(XZCF_YbcfSxcgEntity yse,
                            HttpServletRequest request, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		yse.setS2(t);
		try {
			xzcfcommonsxcgservice.updateInfo(yse);
		} catch (Exception e) {
			// TODO: handle exception
			datasuccess="failed";
		}
		return datasuccess;
	}

	/**
	 * 查看事先催告信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("xzcf:sxcg:view")
	@RequestMapping(value = "view/{id}")
	public String view(@PathVariable("id") long id, Model model) {
		XZCF_YbcfSxcgEntity yse = xzcfcommonsxcgservice.findInfoById(id);
		model.addAttribute("yse", yse);
		return "aqzf/xzcf/ybcf/sxcg/view";
	}

	/**
	 * 删除事先催告信息
	 * 
	 * @param id
	 * @return
	 */
	@RequiresPermissions("xzcf:sxcg:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		try {
			for (int i = 0; i < arrids.length; i++) {
				xzcfcommonsxcgservice.deleteInfo(Long.parseLong(arrids[i]));
				xzcfcommonsxcgservice.updateLaspInfo(Long.parseLong(arrids[i]));
			}
		} catch (Exception e) {
			// TODO: handle exception
			datasuccess="删除失败";
		}
		
		return datasuccess;
	}
	
	/**
	 * 导出事先催告记录word
	 * 
	 */
	@RequiresPermissions("xzcf:sxcg:export")
	@RequestMapping(value = "exportsxcg/{flag}/{id}")
	@ResponseBody
	public String getAjWord(@PathVariable("flag") String  flag,@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = xzcfcommonsxcgservice.getWsdcword(id,flag);
		//设置导出的文件名
		String filename = map.get("number").toString() + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "xzcfsxcg.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
}
