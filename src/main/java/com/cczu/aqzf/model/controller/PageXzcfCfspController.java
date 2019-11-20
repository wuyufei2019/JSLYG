package com.cczu.aqzf.model.controller;


import com.cczu.aqzf.model.dao.AqzfZfryDao;
import com.cczu.aqzf.model.entity.XZCF_CFSPEntity;
import com.cczu.aqzf.model.entity.XZCF_YbcfLaspEntity;
import com.cczu.aqzf.model.service.AqzfSetBasicInfoService;
import com.cczu.aqzf.model.service.IXzcfCommonLaspService;
import com.cczu.aqzf.model.service.XzcfCfspService;
import com.cczu.aqzf.model.service.XzcfXwtzService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.StringUtils;
import com.cczu.util.common.WordUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 处罚审批
 * @author zpc
 * @date 2017/08/05
 */
@Controller
@RequestMapping("xzcf/ybcf/cfsp")
public class PageXzcfCfspController extends BaseController{

	@Autowired
	private XzcfCfspService xzcfCfspService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService; 
	@Autowired
	private XzcfXwtzService xzcfXwtzService;
	@Autowired
	private IXzcfCommonLaspService punishcommonlaspservice;
	@Resource
	private AqzfZfryDao aqzfZfryDao;
	@Autowired
	private AqzfSetBasicInfoService setbasicservice;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "aqzf/xzcf/ybcf/cfsp/index";
	}
	
	/**
	 * list页面
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());
		map.put("qyname", request.getParameter("aqzf_cfsp_qyname"));
		return xzcfCfspService.dataGrid(map);
	}
	
	/**
	 * 删除处罚审批
	 * 
	 * @param
	 * @param
	 * @throws
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] aids = ids.split(",");
		try {
			for(int i=0;i<aids.length;i++){
				xzcfCfspService.deleteInfo(Long.parseLong(aids[i]));
				xzcfCfspService.updateLaspInfo(Long.parseLong(aids[i]));
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			datasuccess="删除失败";
		}
		return datasuccess;
	}

	/**
	 * 添加处罚审批页面跳转
	 *
	 * @param model
	 */
	@RequestMapping(value = "create/{id}" , method = RequestMethod.GET)
	public String create(@PathVariable("id") Long id,Model model) {
		XZCF_YbcfLaspEntity lasp = punishcommonlaspservice.findInfoById(id);
		XZCF_CFSPEntity cfsp = new XZCF_CFSPEntity();

		cfsp.setDsperson(lasp.getDsperson());
		cfsp.setM1(lasp.getNumber());
		cfsp.setCasename(lasp.getCasename());
		cfsp.setM3(lasp.getLegalperson());
		cfsp.setM4(lasp.getDsaddress());
		cfsp.setM5(lasp.getCasesource());
		cfsp.setID3(id);
		model.addAttribute("action", "create");
		model.addAttribute("cfsp", cfsp);
		return "aqzf/xzcf/ybcf/cfsp/form";
	}

	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequestMapping(value = "create" )
	@ResponseBody
	public String create(XZCF_CFSPEntity cfsp, HttpServletRequest request) {
		String datasuccess="success";
		Timestamp t = DateUtils.getSysTimestamp();
		cfsp.setS1(t);
		cfsp.setS2(t);
		cfsp.setS3(0);
		ShiroUser su = UserUtil.getCurrentShiroUser();
		cfsp.setID1(su.getId());
		try {
			xzcfCfspService.addInfo(cfsp);
			XZCF_YbcfLaspEntity yle= punishcommonlaspservice.findInfoById(cfsp.getID3());
			yle.setCfspflag("1");
			punishcommonlaspservice.updateInfo(yle);
		} catch (Exception e) {
			e.printStackTrace();
			datasuccess="error";
		}
		return datasuccess;
	}


	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		XZCF_CFSPEntity cfsp  = xzcfCfspService.findById(id);
		model.addAttribute("cfsp", cfsp);
		model.addAttribute("action", "update");
		return "aqzf/xzcf/ybcf/cfsp/form";
	}

	/**
	 * 修改
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(XZCF_CFSPEntity cfsp, Model model, HttpServletRequest request){
		String datasuccess="success";
        Timestamp t = DateUtils.getSysTimestamp();
		ShiroUser su = UserUtil.getCurrentShiroUser();
		cfsp.setID1(su.getId());
		cfsp.setS2(t);
		xzcfCfspService.updateInfo(cfsp);
		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		//查询选择的处罚审批信息
		XZCF_CFSPEntity cfsp = xzcfCfspService.findById(id);
		model.addAttribute("cfsp", cfsp);
		return "aqzf/xzcf/ybcf/cfsp/view";
	}

	/**
	 * 导出处罚审批书word
	 *
	 */
	@RequiresPermissions("ybcf:cfsp:export")
	@RequestMapping(value = "export/{id}")
	@ResponseBody
	public String getAjWord(@PathVariable("id") Long id,HttpServletRequest request) {
		XZCF_CFSPEntity cfsp = xzcfCfspService.findById(id);
		Map<String, Object> map=new HashMap<String, Object>();
		//时间解析
		if(cfsp.getM2()!=null&&!cfsp.getM2().toString().equals("")){
			String a = cfsp.getM2().toString();
			String[] as1 = a.substring(0,10).split("-");
			map.put("year1", as1[0]);
			map.put("month1", as1[1]);
			map.put("day1", as1[2]);
			String[] bs1 = a.substring(11,16).split(":");
			map.put("hour1", bs1[0]);
			map.put("min1", bs1[1]);
		}else{
			map.put("year1", "    ");
			map.put("month1", "   ");
			map.put("day1", "   ");
			map.put("hour1", "   ");
			map.put("min1", "   ");
		}
		map.put("casename", StringUtils.isEmpty(cfsp.getCasename())?"   ":cfsp.getCasename());//案件名称
        map.put("dsperson", StringUtils.isEmpty(cfsp.getDsperson())?"   ":cfsp.getDsperson());//当事人
		map.put("m3", StringUtils.isEmpty(cfsp.getM3())?"   ":cfsp.getM3());//法定代表人
		map.put("m4", StringUtils.isEmpty(cfsp.getM4())?"   ":cfsp.getM4());//地址
		map.put("m5", StringUtils.isEmpty(cfsp.getM5())?"   ":cfsp.getM5());//案件来源
		map.put("m6", StringUtils.isEmpty(cfsp.getM6())?"   ":cfsp.getM6());//案件经办人
		map.put("m7", StringUtils.isEmpty(cfsp.getM7())?"   ":cfsp.getM7());//办案部门负责人意见
		map.put("m8", StringUtils.isEmpty(cfsp.getM8())?"   ":cfsp.getM8());//法制部门合法性审查意见
		map.put("m10", StringUtils.isEmpty(cfsp.getM10())?"   ":cfsp.getM10());//分管领导审核意见
		map.put("m9", StringUtils.isEmpty(cfsp.getM9())?"   ":cfsp.getM9());//分管领导
		map.put("m11", StringUtils.isEmpty(cfsp.getM11())?"   ":cfsp.getM11());//主要领导
		map.put("m12", StringUtils.isEmpty(cfsp.getM12())?"   ":cfsp.getM12());//主要领导审批意见

		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "cfsp.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
}
