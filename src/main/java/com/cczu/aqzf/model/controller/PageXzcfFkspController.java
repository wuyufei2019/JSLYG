package com.cczu.aqzf.model.controller;


import com.cczu.aqzf.model.entity.*;
import com.cczu.aqzf.model.service.*;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 缴纳罚款审批
 * @author zpc
 * @date 2018/01/15
 */
@Controller
@RequestMapping("xzcf/ybcf/fksp")
public class PageXzcfFkspController extends BaseController {

	@Autowired
	private XzcfFkspService xzcfFkspService;
	@Autowired
	private IXzcfCommonLaspService xzcfcommonlaspservice;
	@Autowired
	private AqzfSetBasicInfoService aqzfsetbasicinfoservice;
	@Autowired
	private IXzcfCfjdService punishsimplecfjdservice;
	@Autowired
	private IXzcfCommonAjcpService xzcfcommonajcpservice;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "aqzf/xzcf/ybcf/fksp/index";
	}
	
	/**
	 * list页面
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());
		map.put("m1", request.getParameter("aqzf_fksp_M1"));
		map.put("m2", request.getParameter("aqzf_fksp_M2"));
		map.put("m4", request.getParameter("aqzf_fksp_M4"));
		return xzcfFkspService.dataGrid(map);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] aids = ids.split(",");
		try {
			for(int i=0;i<aids.length;i++){
				xzcfFkspService.deleteInfo(Long.parseLong(aids[i]));
				xzcfFkspService.updateLaspInfo(Long.parseLong(aids[i]));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			datasuccess="删除失败";
		}
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		XZCF_FkspEntity fksp = xzcfFkspService.findById(id);
		model.addAttribute("fksp", fksp);
		return "aqzf/xzcf/ybcf/fksp/view";
	}
	
	/**
	 * 添加
	 * id 立案id
	 * @param model
	 */
	@RequestMapping(value = "create/{id}" , method = RequestMethod.GET)
	public String create(@PathVariable("id") Long id,Model model) {
		XZCF_FkspEntity fksp = new XZCF_FkspEntity();
		XZCF_YbcfAjcpEntity ajcp = xzcfcommonajcpservice.findInfoByLaId(id);
		XZCF_CfjdInfoEntity cfjd=punishsimplecfjdservice.findInfoByLaId(id);
		if("1".equals(cfjd.getPercomflag())){
			//单位
			fksp.setM5(cfjd.getAddress());
		}else{
			//个人
			fksp.setM5(cfjd.getDwaddress());
		}
		fksp.setM2(ajcp.getCasename());
		fksp.setM3(cfjd.getNumber());
		fksp.setM4(cfjd.getPunishname());
		fksp.setM6(cfjd.getIllegalactandevidence()+cfjd.getPunishname()+"的上述行为，违反"+cfjd.getUnlaw()+"规定，依据"+cfjd.getEnlaw()+"对其进行行政处罚。");
		fksp.setID1(id);
		fksp.setM9(ajcp.getCbr1());
		fksp.setM10(ajcp.getCbr2());
		model.addAttribute("fksp", fksp);
		model.addAttribute("action", "create");
		return "aqzf/xzcf/ybcf/fksp/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequestMapping(value = "create" )
	@ResponseBody
	public String create(XZCF_FkspEntity fksp, HttpServletRequest request) {
		String datasuccess="success";
		//设置编号
		AQZF_SetBasicInfoEntity bh =aqzfsetbasicinfoservice.findInfor();
		XZCF_YbcfLaspEntity ybcf = xzcfcommonlaspservice.findInfoById(fksp.getID1());
		String m1 = ybcf.getNumber();
		if(ybcf.getCxflag().equals("1")){
			//已立案
			fksp.setM1("（"+bh.getSsqjc()+"）应急缴审〔"+m1.substring(m1.indexOf("〔")+1, m1.indexOf("〕"))+"〕"+m1.substring(m1.indexOf("〕")+1, m1.length()));
		}else{
			//预立案
			fksp.setM1("预立案");
		}
		
		//添加并修改立案标记
		xzcfFkspService.addInfo(fksp);
		ybcf.setFkspflag("1");
		xzcfcommonlaspservice.updateInfo(ybcf);
		return datasuccess;
	}
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		XZCF_FkspEntity fksp = xzcfFkspService.findById(id);
		model.addAttribute("fksp", fksp);
		model.addAttribute("action", "update");
		return "aqzf/xzcf/ybcf/fksp/form";
	}
	
	/**
	 * 修改
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(XZCF_FkspEntity fksp){
		String datasuccess="success";
		xzcfFkspService.updateInfo(fksp);
		return datasuccess;
	}

	/**
	 * 导出word
	 */
	@RequestMapping(value = "export/{flag}/{id}")
	@ResponseBody
	public String getAjWord(@PathVariable("flag") String flag,@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = xzcfFkspService.getWsdcword(id,flag);
		//设置导出的文件名
		String filename = map.get("m1").toString() + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "xzfksp.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
	/**
	 * 总览查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "viewjksp/{id}", method = RequestMethod.GET)
	public String viewjksp(@PathVariable("id") Long id, Model model) {
		XZCF_FkspEntity fksp = xzcfFkspService.findWordByLaId(id);
		model.addAttribute("fksp", fksp);
		return "aqzf/xzcf/ybcf/fksp/view";
	}
}
