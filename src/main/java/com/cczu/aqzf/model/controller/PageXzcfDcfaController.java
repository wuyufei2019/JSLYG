package com.cczu.aqzf.model.controller;


import com.cczu.aqzf.model.dao.AqzfZfryDao;
import com.cczu.aqzf.model.entity.XZCF_DCFAEntity;
import com.cczu.aqzf.model.entity.XZCF_YbcfLaspEntity;
import com.cczu.aqzf.model.service.AqzfSetBasicInfoService;
import com.cczu.aqzf.model.service.IXzcfCommonLaspService;
import com.cczu.aqzf.model.service.XzcfDcfaService;
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
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 调查方案
 * @author zpc
 * @date 2017/08/05
 */
@Controller
@RequestMapping("xzcf/ybcf/dcfa")
public class PageXzcfDcfaController extends BaseController{

	@Autowired
	private XzcfDcfaService xzcfDcfaService;
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
		return "aqzf/xzcf/ybcf/dcfa/index";
	}
	
	/**
	 * list页面
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());
		map.put("qyname", request.getParameter("aqzf_dcfa_qyname"));
		return xzcfDcfaService.dataGrid(map);
	}
	
	/**
	 * 删除调查方案
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
				xzcfDcfaService.deleteInfo(Long.parseLong(aids[i]));
				xzcfDcfaService.updateLaspInfo(Long.parseLong(aids[i]));
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			datasuccess="删除失败";
		}
		return datasuccess;
	}

	/**
	 * 添加调查方案页面跳转
	 *
	 * @param model
	 */
	@RequestMapping(value = "create/{id}" , method = RequestMethod.GET)
	public String create(@PathVariable("id") Long id,Model model) {
		XZCF_YbcfLaspEntity lasp = punishcommonlaspservice.findInfoById(id);
		XZCF_DCFAEntity dcfa = new XZCF_DCFAEntity();
		dcfa.setDsperson(lasp.getDsperson());
		dcfa.setM1(lasp.getNumber());
		dcfa.setID3(id);
		model.addAttribute("action", "create");
		model.addAttribute("dcfa", dcfa);
		model.addAttribute("unlaw", lasp.getUnlaw());
		return "aqzf/xzcf/ybcf/dcfa/form";
	}

	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequestMapping(value = "create" )
	@ResponseBody
	public String create(XZCF_DCFAEntity dcfa, HttpServletRequest request) {
		String datasuccess="success";
		Timestamp t = DateUtils.getSysTimestamp();
		dcfa.setS1(t);
		dcfa.setS2(t);
		dcfa.setS3(0);
		ShiroUser su = UserUtil.getCurrentShiroUser();
		dcfa.setID1(su.getId());
		try {
			xzcfDcfaService.addInfo(dcfa);
			XZCF_YbcfLaspEntity yle= punishcommonlaspservice.findInfoById(dcfa.getID3());
			yle.setFaflag("1");
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
		XZCF_DCFAEntity dcfa  = xzcfDcfaService.findById(id);
		model.addAttribute("dcfa", dcfa);
		model.addAttribute("action", "update");
		return "aqzf/xzcf/ybcf/dcfa/form";
	}

	/**
	 * 修改
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(XZCF_DCFAEntity dcfa, Model model, HttpServletRequest request){
		String datasuccess="success";
        Timestamp t = DateUtils.getSysTimestamp();
		ShiroUser su = UserUtil.getCurrentShiroUser();
        dcfa.setID1(su.getId());
        dcfa.setS2(t);
		xzcfDcfaService.updateInfo(dcfa);
		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		//查询选择的调查方案信息
		XZCF_DCFAEntity dcfa = xzcfDcfaService.findById(id);
		model.addAttribute("dcfa", dcfa);
		return "aqzf/xzcf/ybcf/dcfa/view";
	}

	/**
	 * 导出调查方案书word
	 *
	 */
	@RequiresPermissions("ybcf:dcfa:export")
	@RequestMapping(value = "export/{id}")
	@ResponseBody
	public String getAjWord(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		XZCF_DCFAEntity dcfa = xzcfDcfaService.findById(id);
		Map<String, Object> map=new HashMap<String, Object>();
		//调查起始时间解析
		if(dcfa.getM2()!=null&&!dcfa.getM2().toString().equals("")){
			String a = dcfa.getM2().toString();
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
		//调查结束时间解析
		if(dcfa.getM3()!=null&&!dcfa.getM3().toString().equals("")){
			String b = dcfa.getM3().toString();
			String[] as2 = b.substring(0,10).split("-");
            map.put("year2", as2[0]);
			map.put("month2", as2[1]);
			map.put("day2", as2[2]);
			String[] bs2 = b.substring(11,16).split(":");
			map.put("hour2", bs2[0]);
			map.put("min2", bs2[1]);
		}else{
            map.put("year2", "  ");
			map.put("month2", "   ");
			map.put("day2", "  ");
			map.put("hour2", "   ");
			map.put("min2", "   ");
		}
		map.put("m9", StringUtils.isEmpty(dcfa.getM9())?"   ":dcfa.getM9());//办案部门
        map.put("m4", StringUtils.isEmpty(dcfa.getM4())?"   ":dcfa.getM4());//调查人员
		map.put("m5", StringUtils.isEmpty(dcfa.getM5())?"   ":dcfa.getM5());//调查步骤
		map.put("m6", StringUtils.isEmpty(dcfa.getM6())?"   ":dcfa.getM6());//调查结果

		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "dcfa.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
}
