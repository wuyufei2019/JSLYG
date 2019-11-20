package com.cczu.aqzf.model.controller;


import com.cczu.aqzf.model.dao.AqzfZfryDao;
import com.cczu.aqzf.model.entity.AQZF_SetBasicInfoEntity;
import com.cczu.aqzf.model.entity.AQZF_TipstaffEntity;
import com.cczu.aqzf.model.entity.XZCF_InterrogationRecordEntity;
import com.cczu.aqzf.model.service.AqzfSetBasicInfoService;
import com.cczu.aqzf.model.service.IXzcfCommonLaspService;
import com.cczu.aqzf.model.service.XzcfXwblService;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 询问笔录
 * @author zpc
 * @date 2017/08/05
 */
@Controller
@RequestMapping("xzcf/ybcf/xwbl")
public class PageXzcfXwblController extends BaseController {

	@Autowired
	private XzcfXwblService xzcfXwblService;
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
		return "aqzf/xzcf/ybcf/xwbl/index";
	}
	
	/**
	 * list页面
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());
		map.put("qyname", request.getParameter("aqzf_xwbl_qyname"));
		map.put("bh", request.getParameter("aqzf_xwbl_bh"));
		map.put("m1", request.getParameter("aqzf_xwbl_M1"));
		map.put("m2", request.getParameter("aqzf_xwbl_M2"));
		return xzcfXwblService.dataGrid(map);
	}
	
	/**
	 * 删除询问笔录
	 * 
	 * @param ids
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] aids = ids.split(",");
		for(int i=0;i<aids.length;i++){
			xzcfXwblService.deleteInfo(Long.parseLong(aids[i]));
		}
		return datasuccess;
	}

	/**
	 * 添加询问笔录页面跳转
	 * 
	 * @param model
	 */
	@RequestMapping(value = "create/{id}" , method = RequestMethod.GET)
	public String create(@PathVariable("id") Long id,Model model) {
		model.addAttribute("action", "create");
		Map<String,Object> map = xzcfXwtzService.findById3(id);
		XZCF_InterrogationRecordEntity xwbl = new XZCF_InterrogationRecordEntity();
		xwbl.setID2(id);//立案ID
		xwbl.setM3(map.get("m3")==null?"":map.get("m3").toString());//询问地点
		xwbl.setM8(map.get("qyname").toString());//工作单位
		xwbl.setM16(map.get("m1")==null?"":map.get("m1").toString());//案由
		xwbl.setM4(map.get("cfryname")==null?"":map.get("cfryname").toString());
		xwbl.setM5(map.get("cfrysex")==null?"":map.get("cfrysex").toString());
		xwbl.setM6(map.get("cfryage")==null?"":map.get("cfryage").toString());
		AQZF_SetBasicInfoEntity bh =setbasicservice.findInfor();
		
		model.addAttribute("ssqmc",bh.getSsqmc()==null?"":bh.getSsqmc());
		
		AQZF_TipstaffEntity at = aqzfZfryDao.findByM1(UserUtil.getCurrentUser().getName());
		String zjh = "";
		if(at!=null){
			xwbl.setM12(at.getM1());
			String c = at.getM1();
			String[] as3 = c.split(",");
			//证件号
			for(int i=0;i<as3.length;i++){
				AQZF_TipstaffEntity a = aqzfZfryDao.findByM1(as3[i]);
				if(a!=null){
					zjh = zjh + (StringUtils.isEmpty(a.getM3())?"":a.getM3()) + "、";
				}
			}
			zjh=zjh.substring(0,zjh.length()-1);
		}
		//model.addAttribute("cfdxlx",map.get("cfdxlx"));
		model.addAttribute("zjh",zjh);
		model.addAttribute("xwbl", xwbl);
		return "aqzf/xzcf/ybcf/xwbl/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequestMapping(value = "create" )
	@ResponseBody
	public String create(XZCF_InterrogationRecordEntity xwbl, HttpServletRequest request) {
		String datasuccess="success";
		String zw = request.getParameter("zw");
		if(!StringUtils.isEmpty(xwbl.getM9())){
			xwbl.setM9(zw+":"+xwbl.getM9());
		}
		xwbl.setM15(xwbl.getM12());
		ShiroUser su = UserUtil.getCurrentShiroUser();
		xwbl.setID1(su.getId());
		xwbl.setM17(request.getParameter("M17"));
		xzcfXwblService.addInfo(xwbl);
		return datasuccess;
	}
	
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		XZCF_InterrogationRecordEntity xwbl  = xzcfXwblService.findById(id);
		if(!StringUtils.isEmpty(xwbl.getM9())){
			String[] a = xwbl.getM9().split(":");
			model.addAttribute("zw",a[0]);
            xwbl.setM9(a[1]);
		}else{
			model.addAttribute("zw",1);
		}
		model.addAttribute("xwbl", xwbl);
		model.addAttribute("action", "update");
		AQZF_SetBasicInfoEntity bh =setbasicservice.findInfor();
		
		model.addAttribute("ssqmc",bh.getSsqmc()==null?"":bh.getSsqmc());
		
		String zjh = "";
		if(!StringUtils.isEmpty(xwbl.getM15())){
			String c = xwbl.getM15();
			String[] as3 = c.split(",");
			//证件号
			for(int i=0;i<as3.length;i++){
				AQZF_TipstaffEntity a = aqzfZfryDao.findByM1(as3[i]);
				if(a!=null){
					zjh = zjh + (StringUtils.isEmpty(a.getM3())?"":a.getM3()) + "、";
				}
			}
			if(zjh.length()>0)
				zjh=zjh.substring(0,zjh.length()-1);
		}
		model.addAttribute("zjh",zjh);
		return "aqzf/xzcf/ybcf/xwbl/form";
	}
	
	/**
	 * 修改
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(XZCF_InterrogationRecordEntity zfry, Model model, HttpServletRequest request){
		String datasuccess="success";	
		String zw = request.getParameter("zw");
		if(!StringUtils.isEmpty(zfry.getM9())){
			zfry.setM9(zw+":"+zfry.getM9());
		}
		ShiroUser su = UserUtil.getCurrentShiroUser();
		zfry.setID1(su.getId());
		zfry.setM15(zfry.getM12());
		zfry.setM17(request.getParameter("M17"));
		xzcfXwblService.updateInfo(zfry);
		return datasuccess;
	}
	
	/**
	 * 询问人员解析
	 * @param xwr
	 */
	@RequestMapping(value = "xwrjx")
	@ResponseBody
	public Map<String,Object> xwrjx(String xwr){
		Map<String,Object> map = new HashMap<>();
		if(!StringUtils.isEmpty(xwr)){
			String[] as3 = xwr.split(",");
			//证件号
			String zjh = "";
			String m15= "";
			for(int i=0;i<as3.length;i++){
				m15 = m15 + as3[i] + "、";
				AQZF_TipstaffEntity a = aqzfZfryDao.findByM1(as3[i]);
				if(a!=null){
					zjh = zjh + (StringUtils.isEmpty(a.getM3())?"":a.getM3()) + "、";
				}
			}
			zjh=zjh.substring(0,zjh.length()-1);
			m15=m15.substring(0,m15.length()-1);
			map.put("m15", m15);
		    map.put("zjh", zjh);
		}else{
			map.put("m15", "");
			map.put("zjh", "");
		}
		return map;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		//查询选择的询问笔录信息
		XZCF_InterrogationRecordEntity xwbl = xzcfXwblService.findById(id);
		if(!StringUtils.isEmpty(xwbl.getM9())){
			String[] a = xwbl.getM9().split(":");
            xwbl.setM9(a[1]);
		}
		model.addAttribute("xwbl", xwbl);
		return "aqzf/xzcf/ybcf/xwbl/view";
	}
	
	/**
	 * 导出询问笔录书word
	 * 
	 */
	@RequiresPermissions("aqzf:xwbl:export")
	@RequestMapping(value = "export/{id}")
	@ResponseBody
	public String getAjWord(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = xzcfXwblService.getWsdcword(id);
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "xwbl.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
	/**
	 * 定时添加信息
	 * @param request,model
	 */
	@RequestMapping(value = "dscreate" )
	@ResponseBody
	public String dscreate(XZCF_InterrogationRecordEntity xwbl, HttpServletRequest request) {
		String zw = request.getParameter("zw");
		if(!StringUtils.isEmpty(xwbl.getM9())){
			xwbl.setM9(zw+":"+xwbl.getM9());
		}
		xwbl.setM15(xwbl.getM12());
		ShiroUser su = UserUtil.getCurrentShiroUser();
		xwbl.setID1(su.getId());
		xwbl.setM17(request.getParameter("M17"));
		xzcfXwblService.addInfo(xwbl);
		return xwbl.getID().toString();
	}
}
