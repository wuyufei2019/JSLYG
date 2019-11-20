package com.cczu.aqzf.model.controller;

import com.cczu.aqzf.model.entity.AQZF_SetBasicInfoEntity;
import com.cczu.aqzf.model.entity.XZCF_CfjdInfoEntity;
import com.cczu.aqzf.model.entity.XZCF_FkpzEntity;
import com.cczu.aqzf.model.entity.XZCF_YbcfLaspEntity;
import com.cczu.aqzf.model.service.AqzfSetBasicInfoService;
import com.cczu.aqzf.model.service.IXzcfCfjdService;
import com.cczu.aqzf.model.service.IXzcfCommonLaspService;
import com.cczu.aqzf.model.service.XzcfFkpzService;
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
 * 罚款批准
 * @author zpc
 * @date 2018/01/15
 */
@Controller
@RequestMapping("xzcf/ybcf/fkpz")
public class PageXzcfFkpzController extends BaseController {

	@Autowired
	private XzcfFkpzService xzcfFkpzService;
	@Autowired
	private IXzcfCommonLaspService xzcfcommonlaspservice;
	@Autowired
	private AqzfSetBasicInfoService aqzfsetbasicinfoservice;
	@Autowired
	private IXzcfCfjdService punishsimplecfjdservice;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "aqzf/xzcf/ybcf/fkpz/index";
	}
	
	/**
	 * list页面
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());
		map.put("m1", request.getParameter("aqzf_fkpz_M1"));
		map.put("m2", request.getParameter("aqzf_fkpz_M2"));
		map.put("m5", request.getParameter("aqzf_fkpz_M5"));
		return xzcfFkpzService.dataGrid(map);
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
				xzcfFkpzService.deleteInfo(Long.parseLong(aids[i]));
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
		XZCF_FkpzEntity fkpz = xzcfFkpzService.findById(id);
		model.addAttribute("fkpz", fkpz);
		return "aqzf/xzcf/ybcf/fkpz/view";
	}
	
	/**
	 * 添加跳转
	 * id 立案id
	 * @param model
	 */
	@RequestMapping(value = "create/{id}" , method = RequestMethod.GET)
	public String create(@PathVariable("id") Long id,Model model) {
		XZCF_CfjdInfoEntity cfjd=punishsimplecfjdservice.findInfoByLaId(id);
		XZCF_FkpzEntity fkpz = new XZCF_FkpzEntity();
		fkpz.setM2(cfjd.getPunishname());
		fkpz.setM3(cfjd.getXzcf());
		fkpz.setID1(id);
		model.addAttribute("fkpz", fkpz);
		model.addAttribute("action", "create");
		return "aqzf/xzcf/ybcf/fkpz/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequestMapping(value = "create" )
	@ResponseBody
	public String create(XZCF_FkpzEntity fkpz, HttpServletRequest request) {
		String datasuccess="success";
		//设置编号
		AQZF_SetBasicInfoEntity bh =aqzfsetbasicinfoservice.findInfor();
		XZCF_YbcfLaspEntity ybcf = xzcfcommonlaspservice.findInfoById(fkpz.getID1());
		String m1 = ybcf.getNumber();
		if(ybcf.getCxflag().equals("1")){
			//已立案
			fkpz.setM1("（"+bh.getSsqjc()+"）应急缴批〔"+m1.substring(m1.indexOf("〔")+1, m1.indexOf("〕"))+"〕"+m1.substring(m1.indexOf("〕")+1, m1.length()));
		}else{
			//预立案
			fkpz.setM1("预立案");
		}
		
		xzcfFkpzService.addInfo(fkpz);
		return datasuccess;
	}
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		XZCF_FkpzEntity fkpz = xzcfFkpzService.findById(id);
		model.addAttribute("fkpz", fkpz);
		model.addAttribute("action", "update");
		return "aqzf/xzcf/ybcf/fkpz/form";
	}
	
	/**
	 * 修改
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(XZCF_FkpzEntity fkpz){
		String datasuccess="success";
		xzcfFkpzService.updateInfo(fkpz);
		return datasuccess;
	}

	/**
	 * 导出word
	 */
	@RequestMapping(value = "export/{flag}/{id}")
	@ResponseBody
	public String getAjWord(@PathVariable("flag") String flag,@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = xzcfFkpzService.getWsdcword(id,flag);
		//设置导出的文件名
		String filename = map.get("m1").toString() + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "xzfkpz.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
}
