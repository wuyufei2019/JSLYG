package com.cczu.aqzf.model.controller;


import com.cczu.aqzf.model.dao.AqzfZfryDao;
import com.cczu.aqzf.model.entity.AQZF_SetBasicInfoEntity;
import com.cczu.aqzf.model.entity.AQZF_TipstaffEntity;
import com.cczu.aqzf.model.entity.XZCF_SgxwblEntity;
import com.cczu.aqzf.model.entity.XZCF_SgxwtzEntity;
import com.cczu.aqzf.model.service.AqzfSetBasicInfoService;
import com.cczu.aqzf.model.service.XzcfSgxwblService;
import com.cczu.aqzf.model.service.XzcfSgxwtzService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.StringUtils;
import com.cczu.util.common.WordUtil;
import org.apache.commons.lang3.StringEscapeUtils;
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
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 事故询问笔录
 * @author zpc
 * @date 2017/08/05
 */
@Controller
@RequestMapping("xzcf/ybcf/sgxwbl")
public class PageXzcfSgxwblController extends BaseController {

	@Autowired
	private XzcfSgxwblService xzcfSgxwblService;
	@Autowired
	private XzcfSgxwtzService xzcfSgxwtzService;
	@Autowired
	private AqzfSetBasicInfoService setbasicservice;
	@Resource
	private AqzfZfryDao aqzfZfryDao;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "aqzf/xzcf/ybcf/sgxwbl/index";
	}
	
	/**
	 * list页面
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());
		map.put("qyname", request.getParameter("aqzf_sgxwbl_qyname"));
		map.put("dczt", request.getParameter("aqzf_sgxwbl_dczt"));
		map.put("m1", request.getParameter("aqzf_sgxwbl_M1"));
		map.put("m2", request.getParameter("aqzf_sgxwbl_M2"));
		return xzcfSgxwblService.dataGrid(map);
	}
	
	/**
	 * 删除事故询问笔录
	 * @param user
	 * @param model
	 * @throws ParseException 
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] aids = ids.split(",");
		for(int i=0;i<aids.length;i++){
			xzcfSgxwblService.deleteInfo(Long.parseLong(aids[i]));
		}
		return datasuccess;
	}

	/**
	 * 添加事故询问笔录页面跳转
	 * @param model
	 * id事故询问通知ID
	 */
	@RequestMapping(value = "create/{id}" , method = RequestMethod.GET)
	public String create(@PathVariable("id") Long id,Model model) {
		model.addAttribute("action", "create");
		Map<String,Object> map = xzcfSgxwtzService.findAllbyid(id);
		XZCF_SgxwblEntity sgxwbl = new XZCF_SgxwblEntity();
		sgxwbl.setID1(id);//事故通知ID
		sgxwbl.setM3(map.get("m3")==null?"":map.get("m3").toString());//询问地点
		sgxwbl.setM8(map.get("qyname").toString());//工作单位
		
		AQZF_SetBasicInfoEntity bh =setbasicservice.findInfor();
		model.addAttribute("ssqmc",bh.getSsqmc()==null?"":bh.getSsqmc());
		
		AQZF_TipstaffEntity at = aqzfZfryDao.findByM1(UserUtil.getCurrentUser().getName());
		String zjh = "";
		if(at!=null){
			sgxwbl.setM12(at.getM1());
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
		model.addAttribute("zjh",zjh);
		model.addAttribute("dczt", map.get("m1")==null?"":map.get("m1").toString());
		model.addAttribute("sgxwbl", sgxwbl);
		return "aqzf/xzcf/ybcf/sgxwbl/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequestMapping(value = "create" )
	@ResponseBody
	public String create(XZCF_SgxwblEntity sgxwbl, HttpServletRequest request) {
		String datasuccess="success";
		String zw = request.getParameter("zw");
		if(!StringUtils.isEmpty(sgxwbl.getM9())){
			sgxwbl.setM9(zw+":"+sgxwbl.getM9());
		}
		sgxwbl.setM15(sgxwbl.getM12());
		sgxwbl.setM16(request.getParameter("M16"));
		xzcfSgxwblService.addInfo(sgxwbl);
		return datasuccess;
	}
	
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		XZCF_SgxwblEntity sgxwbl  = xzcfSgxwblService.findById(id);
		if(!StringUtils.isEmpty(sgxwbl.getM9())){
			String[] a = sgxwbl.getM9().split(":");
			model.addAttribute("zw",a[0]);
            sgxwbl.setM9(a[1]);
		}else{
			model.addAttribute("zw",1);
		}
		model.addAttribute("sgxwbl", sgxwbl);
		model.addAttribute("action", "update");
		AQZF_SetBasicInfoEntity bh =setbasicservice.findInfor();
		
		model.addAttribute("ssqmc",bh.getSsqmc()==null?"":bh.getSsqmc());
		
		String zjh = "";
		if(!StringUtils.isEmpty(sgxwbl.getM15())){
			String c = sgxwbl.getM15();
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
		model.addAttribute("zjh",zjh);
		return "aqzf/xzcf/ybcf/sgxwbl/form";
	}
	
	/**
	 * 修改
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(XZCF_SgxwblEntity zfry, Model model, HttpServletRequest request){
		String datasuccess="success";	
		String zw = request.getParameter("zw");
		if(!StringUtils.isEmpty(zfry.getM9())){
			zfry.setM9(zw+":"+zfry.getM9());
		}
		zfry.setM15(zfry.getM12());
		zfry.setM16(request.getParameter("M16"));
		xzcfSgxwblService.updateInfo(zfry);
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		//查询选择的事故询问笔录信息
		XZCF_SgxwblEntity sgxwbl = xzcfSgxwblService.findById(id);	
		if(!StringUtils.isEmpty(sgxwbl.getM9())){
			String[] a = sgxwbl.getM9().split(":");
            sgxwbl.setM9(a[1]);
		}
		model.addAttribute("sgxwbl", sgxwbl);
		return "aqzf/xzcf/ybcf/sgxwbl/view";
	}
	
	/**
	 * 导出询问笔录书word
	 * 
	 */
	@RequiresPermissions("aqzf:sgxwbl:export")
	@RequestMapping(value = "export/{id}")
	@ResponseBody
	public String getAjWord(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		XZCF_SgxwblEntity xwbl = xzcfSgxwblService.findById(id);
		if(!StringUtils.isEmpty(xwbl.getM9())){
			String[] a = xwbl.getM9().split(":");
            xwbl.setM9(a[1]);
		}
		Map<String, Object> map=new HashMap<String, Object>();
		//询问起始时间解析
		if(xwbl.getM1()!=null&&!xwbl.getM1().toString().equals("")){
			String a = xwbl.getM1().toString();
			String[] as1 = a.substring(0,10).split("-");
			map.put("year", as1[0]);
			map.put("month1", as1[1]);
			map.put("day1", as1[2]);
			String[] bs1 = a.substring(11,16).split(":");
			map.put("hour1", bs1[0]);
			map.put("min1", bs1[1]);
		}else{
			map.put("year", "    ");
			map.put("month1", "   ");
			map.put("day1", "   ");
			map.put("hour1", "   ");
			map.put("min1", "   ");
		}
		//询问结束时间解析
		if(xwbl.getM2()!=null&&!xwbl.getM2().toString().equals("")){
			String b = xwbl.getM2().toString();
			String[] as2 = b.substring(0,10).split("-");
			map.put("month2", as2[1]);
			map.put("day2", as2[2]);
			String[] bs2 = b.substring(11,16).split(":");
			map.put("hour2", bs2[0]);
			map.put("min2", bs2[1]);
		}else{
			map.put("month2", "   ");
			map.put("day2", "  ");
			map.put("hour2", "   ");
			map.put("min2", "   ");
		}
		//检查人员解析
		if(!StringUtils.isEmpty(xwbl.getM15())){
			String c = xwbl.getM15();
			String[] as3 = c.split(",");
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
			map.put("m15", "                 ");
			 map.put("zjh", "                 ");
		}
		map.put("m0", StringUtils.isEmpty(xwbl.getM0())?"   ":xwbl.getM0());
		map.put("m3", StringUtils.isEmpty(xwbl.getM3())?"                   ":xwbl.getM3());
		map.put("m4", StringUtils.isEmpty(xwbl.getM4())?"      ":xwbl.getM4());
		map.put("m5", StringUtils.isEmpty(xwbl.getM5())?"   ":xwbl.getM5());
		map.put("m6", StringUtils.isEmpty(xwbl.getM6())?"   ":xwbl.getM6());
		map.put("m7", StringUtils.isEmpty(xwbl.getM7())?"              ":xwbl.getM7());
		map.put("m8", StringUtils.isEmpty(xwbl.getM8())?"                             ":xwbl.getM8());
		map.put("m9", StringUtils.isEmpty(xwbl.getM9())?"           ":xwbl.getM9());
		map.put("m10", StringUtils.isEmpty(xwbl.getM10())?"                                   ":xwbl.getM10());
		map.put("m11", StringUtils.isEmpty(xwbl.getM11())?"              ":xwbl.getM11());
		
		AQZF_SetBasicInfoEntity bh =setbasicservice.findInfor();
		String ssqmc = bh.getSsqmc()==null?"":bh.getSsqmc();
		map.put("ssqmc", ssqmc);
		//询问人单位职务
		List<Map<String,Object>> list = new ArrayList<>();
		if(!StringUtils.isEmpty(xwbl.getM12())){
			String d = xwbl.getM12();
			String[] ds = d.split(",");
			for(int i=0;i<ds.length;i++){
				Map<String,Object> zmap = new HashMap<>();
				zmap.put("z1", ds[i]);
				AQZF_TipstaffEntity at1 = aqzfZfryDao.findByM1(ds[i]);
				if(at1!=null){
					zmap.put("z2", ssqmc+"安全生产监督管理局"+(StringUtils.isEmpty(at1.getM4())?"":at1.getM4()));
				}else{
					zmap.put("z2", ssqmc+"安全生产监督管理局");
				}
				
				list.add(zmap);
			}
		}else{
			for(int i=0;i<2;i++){
				Map<String,Object> zmap = new HashMap<>();
				zmap.put("z1", "          ");
				zmap.put("z2", "                                  ");
				list.add(zmap);
			}
		}
		map.put("Xwrlist",list);
		//记录人单位职务
		if(!StringUtils.isEmpty(xwbl.getM13())){
			map.put("m13",xwbl.getM13());
			AQZF_TipstaffEntity at2 = aqzfZfryDao.findByM1(xwbl.getM13());
			if(at2!=null){
				map.put("m13_1"," "+ssqmc+"安全生产监督管理局"+(StringUtils.isEmpty(at2.getM4())?"":at2.getM4()));
			}else{
				map.put("m13_1"," "+ssqmc+"安全生产监督管理局");
			}
			
		}else{
			map.put("m13","          ");
			map.put("m13_1","                                  ");
		}	
		map.put("m14", StringUtils.isEmpty(xwbl.getM14())?"                           ":xwbl.getM14());
		XZCF_SgxwtzEntity xwtz = xzcfSgxwtzService.findById(xwbl.getID1());
		map.put("m16", StringUtils.isEmpty(xwtz.getM1())?"                      ":xwtz.getM1());
		//处理m16富文本内容
		String z = xwbl.getM16();
		String M17 = "";
		if(!StringUtils.isEmpty(z)){
			String[] zs = z.split("</p>");
			for(int i=0;i<zs.length;i++){
				M17 += StringEscapeUtils.escapeHtml3(this.getTextFromHtml(zs[i]))+"<w:p />";
			}
		}
		map.put("m17", M17);
		
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "xwbl.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
	//处理富文本
	private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
	private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
	private static final String regEx_space = "\\s*|\t|\r|\n";//定义空格回车换行符
	
	public String delHTMLTag(String htmlStr) {
		Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll(""); // 过滤style标签

		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll(""); // 过滤html标签

		Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
		Matcher m_space = p_space.matcher(htmlStr);
		htmlStr = m_space.replaceAll(""); // 过滤空格回车标签
		return htmlStr.trim(); // 返回文本字符串
	}

	public String getTextFromHtml(String htmlStr){
		htmlStr = this.delHTMLTag(htmlStr);
		htmlStr = htmlStr.replaceAll("&nbsp;", "");
		// htmlStr = htmlStr.substring(0, htmlStr.indexOf("。")+1);
		return htmlStr;
	}
	
	/**
	 * 定时添加信息
	 * @param request,model
	 */
	@RequestMapping(value = "dscreate" )
	@ResponseBody
	public String dscreate(XZCF_SgxwblEntity sgxwbl, HttpServletRequest request) {
		String zw = request.getParameter("zw");
		if(!StringUtils.isEmpty(sgxwbl.getM9())){
			sgxwbl.setM9(zw+":"+sgxwbl.getM9());
		}
		sgxwbl.setM15(sgxwbl.getM12());
		sgxwbl.setM16(request.getParameter("M16"));
		xzcfSgxwblService.addInfo(sgxwbl);
		return sgxwbl.getID().toString();
	}
}
