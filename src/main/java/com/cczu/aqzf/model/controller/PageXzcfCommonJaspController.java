package com.cczu.aqzf.model.controller;

import com.cczu.aqzf.model.entity.*;
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
import java.sql.Timestamp;
import java.util.Map;

/**
 * 行政结案审批-一般结案审批-结案审批controller
 * 
 * @author jason
 * 
 */
@Controller
@RequestMapping("xzcf/ybcf/jasp")
public class PageXzcfCommonJaspController extends BaseController {

	@Autowired
	private XzcfCommonJaspService XzcfCommonJaspService;
	@Autowired
	private IXzcfCfjdService punishsimplecfjdservice;
	@Autowired
	private IXzcfCommonAjcpService xzcfcommonajcpservice;
	@Autowired
	private IXzcfCommonLaspService xzcfcommonlaspservice;
	@Autowired
	private AqzfSetNumberService setNumberService;
	@Autowired
	private AqzfSetBasicInfoService setbasicservice;

	/**
	 * 默认页面
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {
		return "aqzf/xzcf/ybcf/jasp/index";
	}

	/**
	 * list页面
	 */
	@RequiresPermissions("xzcf:jasp:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("name", request.getParameter("xzcf_qzzx_name"));
		map.put("number", request.getParameter("xzcf_qzzx_number"));
		map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());
		return XzcfCommonJaspService.dataGrid(map);
	}

	/**
	 * 添加结案审批信息页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("xzcf:jasp:add")
	@RequestMapping(value = "create/{id}")
	public String create(@PathVariable("id") long id, Model model) {
		XZCF_YbcfAjcpEntity yae = xzcfcommonajcpservice.findInfoByLaId(id);
		XZCF_CfjdInfoEntity cfjd=punishsimplecfjdservice.findInfoByLaId(id);
		XZCF_YbcfJaspEntity yje= new XZCF_YbcfJaspEntity();
		yje.setCasename(yae.getCasename());
		yje.setPunishname(yae.getPunishname());
		yje.setResult(cfjd.getIllegalactandevidence()+yae.getPunishname()+"的上述行为，违反"+cfjd.getUnlaw()+"规定，依据"+cfjd.getEnlaw()+"对其进行行政处罚："+cfjd.getXzcf());
		String type = ""; 
		if("1".equals(yae.getPercomflag())){
			yje.setQyaddress(yae.getQyaddress());
			yje.setLegal(yae.getLegal());
			yje.setDuty(yae.getDuty());
			yje.setQyyb(yae.getQyyb());
			yje.setPercomflag("1");
			type = "单位";
		}else{
			yje.setAge(yae.getAge());
			yje.setSex(yae.getSex());
			yje.setMobile(yae.getMobile());
			yje.setDwname(yae.getDwname());
			yje.setDwaddress(yae.getDwaddress());
			yje.setAddress(yae.getAddress());
			yje.setJtyb(yae.getJtyb());
			yje.setPercomflag("0");
			type = "个人";
		}
		yje.setExeucondition(DateUtils.formatDate(cfjd.getPunishdate(), "yyyy年MM月dd日")+"，承办人向被处罚人直接送达了《行政处罚决定书（"+type+"）》（"+cfjd.getNumber()+"）。被处罚人于XX月XX日缴纳了罚款。至此，本案已办理完毕，建议结案。");
		yje.setCbr1(yae.getCbr1());
		yje.setCbr2(yae.getCbr2());
		model.addAttribute("id1", id);
		model.addAttribute("yje", yje);
		model.addAttribute("action", "createSub");
		return "aqzf/xzcf/ybcf/jasp/form";
	}

	/**
	 * 添加出结案审批
	 * 
	 * @param request
	 */
	@RequiresPermissions("xzcf:jasp:add")
	@RequestMapping(value = "createSub", method = RequestMethod.POST)
	@ResponseBody
	public String createSub(XZCF_YbcfJaspEntity yje,
                            HttpServletRequest request, Model model) {
		String datasuccess = "error";
		Timestamp t = DateUtils.getSysTimestamp();
		yje.setS1(t);
		yje.setS2(t);
		yje.setS3(0);
		yje.setS3(0);
		
		//设置编号
		AQZF_SetBasicInfoEntity bh =setbasicservice.findInfor();
		XZCF_YbcfLaspEntity ybcf = xzcfcommonlaspservice.findInfoById(yje.getId1());
		String m0 = ybcf.getNumber();
		if(ybcf.getCxflag().equals("1")){
			//已立案
			yje.setNumber("（"+bh.getSsqjc()+"）应急结〔" + m0.substring(m0.indexOf("〔")+1, m0.indexOf("〕")) + "〕"+m0.substring(m0.indexOf("〕")+1, m0.length()));
		}else{
			//预立案
			yje.setNumber("预立案");
		}
		
		if (XzcfCommonJaspService.addInfoReturnID(yje) > 0) {
			try {
				XZCF_YbcfLaspEntity yle = xzcfcommonlaspservice.findInfoById(yje.getId1());
				yle.setJaflag("1");
				xzcfcommonlaspservice.updateInfo(yle);
				datasuccess = "success";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return datasuccess;
	}

	/**
	 * 修改结案审批信息页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("xzcf:jasp:update")
	@RequestMapping(value = "update/{id}")
	public String update(@PathVariable("id") long id, Model model) {

		XZCF_YbcfJaspEntity yje = XzcfCommonJaspService.findInfoById(id);
		model.addAttribute("yje", yje);
		model.addAttribute("flag", yje.getPercomflag());
		model.addAttribute("action", "updateSub");
		return "aqzf/xzcf/ybcf/jasp/form";
	}

	/**
	 * 修改结案审批信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("xzcf:jasp:update")
	@RequestMapping(value = "updateSub")
	@ResponseBody
	public String updateSub(XZCF_YbcfJaspEntity yje,
                            HttpServletRequest request, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		String qyname = request.getParameter("punishname");
		yje.setPunishname(qyname);
		yje.setS2(t);
		try {
			XzcfCommonJaspService.updateInfo(yje);
		} catch (Exception e) {
			datasuccess = "error";
		}
		return datasuccess;
	}

	/**
	 * 查看结案审批信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("xzcf:jasp:view")
	@RequestMapping(value = "view/{id}")
	public String view(@PathVariable("id") long id, Model model) {
		XZCF_YbcfJaspEntity yje = XzcfCommonJaspService.findInfoById(id);
		model.addAttribute("yje", yje);
		return "aqzf/xzcf/ybcf/jasp/view";
	}

	/**
	 * 删除结案审批信息
	 * 
	 * @param id
	 * @return
	 */
	@RequiresPermissions("xzcf:jasp:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		try {
			for (int i = 0; i < arrids.length; i++) {
				XzcfCommonJaspService.deleteInfo(Long.parseLong(arrids[i]));
				XzcfCommonJaspService.updateLaspInfo(Long.parseLong(arrids[i]));
			}

		} catch (Exception e) {
			datasuccess = "删除失败";
		}
		return datasuccess;
	}

	/**
	 * 导出案件呈批记录word
	 * 
	 */
	@RequiresPermissions("xzcf:jasp:export")
	@RequestMapping(value = "exportjasp/{flag}/{id}")
	@ResponseBody
	public String getAjWord(@PathVariable("flag") String flag,@PathVariable("id") Long id, HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = XzcfCommonJaspService.getWsdcword(id,flag);
		// 设置导出的文件名
		String filename = map.get("number").toString() + ".doc";
		// 设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/")+ "download/";
		WordUtil.ireportWord(map, "xzcfjasp.ftl", filePath, filename, request);

		return "/download/" + filename;
	}

	/**
	 * 总览查看结案审批信息跳转
	 * @param model
	 */
	@RequestMapping(value = "viewjasp/{id}")
	public String viewjasp(@PathVariable("id") long id, Model model) {
		XZCF_YbcfJaspEntity yje = XzcfCommonJaspService.findInfoByLaId(id);
		model.addAttribute("yje", yje);
		return "aqzf/xzcf/ybcf/jasp/view";
	}
}
