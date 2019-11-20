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
import java.util.List;
import java.util.Map;

/**
 * 行政处罚-简易处罚-处罚决定controller
 * 
 * @author jason
 * 
 */
@Controller
@RequestMapping("xzcf/ybcf/cfjd")
public class PageXzcfCommonCfjdController extends BaseController {

	@Autowired
	private IXzcfCfjdService punishsimplecfjdservice;
	@Autowired
	private IPunishSimpleGzService punishsimplegzservice;
	@Autowired
	private IXzcfCommonLaspService xzcfcommonlaspservice;
	@Autowired
	private AqzfSetNumberService setNumberService;
	@Autowired
	private AqzfSetBasicInfoService aqzfsetbasicinfoservice;
	@Autowired
	private IXzcfCommonAjcpService xzcfcommonajcpservice;
	@Autowired
	private XzcfCommonDcService xzcfcommondcservice;
	@Autowired
	private AqzfJcnrService aqzfJcnrService;
	@Autowired
	private XzcfJttlService xzcfJttlService;
	@Autowired
	private XzcfDCzjService xzcfDCzjService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {
		return "aqzf/xzcf/ybcf/cfjd/index";
	}

	/**
	 * list页面
	 */
	@RequiresPermissions("ybcf:cfjd:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("number", request.getParameter("ybcf_cfjd_number"));
		map.put("type", request.getParameter("ybcf_cfjd_type"));
		map.put("startdate", request.getParameter("ybcf_cfjd_startdate"));
		map.put("enddate", request.getParameter("ybcf_cfjd_enddate"));
		map.put("cfdw", request.getParameter("xzcf_sxcg_name"));
		map.put("cftype", "1");
		map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());
		return punishsimplecfjdservice.dataGrid(map);
	}
	
	/**
	 * 添加处罚信息页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("ybcf:cfjd:add")
	@RequestMapping(value = "create/{id}")
	public String create(@PathVariable("id") long id,Model model) {
		XZCF_GzsInfoEntity gie=punishsimplegzservice.findInfoByLaId(id);
		XZCF_YbcfAjcpEntity ajcp=xzcfcommonajcpservice.findInfoByLaId(id);
		XZCF_YbcfLaspEntity lasp = xzcfcommonlaspservice.findInfoById(id);
		model.addAttribute("id1", id);
		model.addAttribute("ajcp", ajcp);
		model.addAttribute("gie", gie);
		model.addAttribute("lasp", lasp);
		model.addAttribute("action", "createSub");
		
		XZCF_YbcfDcbgEntity dcbg = xzcfcommondcservice.findInfoByLaId(id);
		List<Map<String,Object>> list = aqzfJcnrService.findAllByids(dcbg.getSccids(),lasp.getZycllx()+"");
		String xq = "";
		
		String reg=".*[\\.。]$";
		
		String zyclbz = "";
		int z = 1;
		if(list!=null){
			if(lasp.getCfdxlx().equals("2")){
				xq = dcbg.getCfryname();
			}else{
				xq = dcbg.getQyname();
			}
			
			String ajxq = lasp.getAjjbqk();
			int ajxqi = ajxq.indexOf("发现：");
			if(ajxqi!=-1){
				ajxq = ajxq.substring(ajxqi+3,ajxq.length());
			}
			xq += ajxq;
			if(xq.matches(reg)){
				xq = xq.substring(0, xq.length()-1)+"，";
			}else{
				xq += "，";
			}
			
			if(list.size()==1){
				zyclbz = list.get(0).get("m7")==null?"":list.get(0).get("m7").toString();
			}else{
				for (Map<String, Object> map : list) {
					if(z == 1){
						zyclbz += map.get("m7")==null?"":map.get("m7").toString()+"，";
					}else{
						zyclbz += map.get("m7")==null?"":map.get("m7").toString().substring(map.get("m7").toString().indexOf("》")+1)+"，";
					}
					z++;
				}
				zyclbz = zyclbz.substring(0, zyclbz.length()-1);
			}
		}
		//详情
		String str=dcbg.getXzcf();
		if(str.matches(reg)){
		    str = str.substring(0, str.length()-1);
		}
		XZCF_JttlEntity jttl = xzcfJttlService.findWordByLaId(id);
		String jttlword = "";
		if(jttl!=null){
			jttlword = (jttl.getM3()!=null? DateUtils.formatDate(jttl.getM3(), "yyyy年MM月dd日"):"")+"，本机关案审委对本案案情及拟做出的行政处罚进行了集体讨论。";
		}
		String cfdx = "";
		if(lasp.getCfdxlx().equals("2")){
			cfdx = "你";
		}else{
			cfdx = "你公司";
		}
		xq = dcbg.getUnlawall()+xq+"因此违反了上述规定。"+jttlword+"依据"+dcbg.getPunishresult()+"之规定，参照"+zyclbz+"之规定，对"+cfdx+"作出"+str+"的行政处罚。";
		model.addAttribute("xq", xq);
		
		return "aqzf/xzcf/ybcf/cfjd/form";
	}

	/**
	 * 添加出处罚
	 * @param request
	 */
	@RequiresPermissions("ybcf:cfjd:add")
	@RequestMapping(value = "createSub", method = RequestMethod.POST)
	@ResponseBody
	public String createSub(XZCF_CfjdInfoEntity jce, HttpServletRequest request, Model model){
		String datasuccess = "error";
		Timestamp t = DateUtils.getSysTimestamp();
		jce.setS1(t);
		jce.setS2(t);
		jce.setS3(0);
		jce.setS3(0);
		jce.setPunishname(request.getParameter("punishname").toString());
		
		//设置编号
		AQZF_SetBasicInfoEntity bh =aqzfsetbasicinfoservice.findInfor();
		XZCF_YbcfLaspEntity ybcf = xzcfcommonlaspservice.findInfoById(jce.getId1());
		String m0 = ybcf.getNumber();
		if(ybcf.getCxflag().equals("1")){
			//已立案
			jce.setNumber("（"+bh.getSsqjc()+"）应急罚〔"+m0.substring(m0.indexOf("〔")+1, m0.indexOf("〕"))+"〕"+m0.substring(m0.indexOf("〕")+1, m0.length()));
		}else{
			//预立案
			jce.setNumber("预立案");
		}
		
		if (punishsimplecfjdservice.addInfoReturnID(jce) > 0) {
			try {
				XZCF_YbcfLaspEntity yle= xzcfcommonlaspservice.findInfoById(jce.getId1());
				yle.setCfjdflag("1");
				xzcfcommonlaspservice.updateInfo(yle);
				datasuccess = "success";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return datasuccess;
	}

	/**
	 * 修改处罚信息页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("ybcf:cfjd:update")
	@RequestMapping(value = "update/{id}")
	public String update(@PathVariable("id") long id, Model model) {

		XZCF_CfjdInfoEntity jce = punishsimplecfjdservice.findInfoById(id);
		model.addAttribute("jce", jce);
		model.addAttribute("flag", jce.getPercomflag());
		model.addAttribute("action", "updateSub");
		return "aqzf/xzcf/ybcf/cfjd/form";
	}

	/**
	 * 修改处罚信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("ybcf:cfjd:update")
	@RequestMapping(value = "updateSub")
	@ResponseBody
	public String updateSub(XZCF_CfjdInfoEntity jce,
                            HttpServletRequest request, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		String qyname=request.getParameter("punishname");
		jce.setPunishname(qyname);
		jce.setS2(t);
		try {
			punishsimplecfjdservice.updateInfo(jce);
		} catch (Exception e) {
			// TODO: handle exception
			datasuccess="error";
		}
		return datasuccess;
	}

	/**
	 * 查看处罚信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("ybcf:cfjd:view")
	@RequestMapping(value = "view/{id}")
	public String view(@PathVariable("id") long id, Model model) {
		XZCF_CfjdInfoEntity jce = punishsimplecfjdservice.findInfoById(id);
		model.addAttribute("jce", jce);
		return "aqzf/xzcf/ybcf/cfjd/view";
	}

	/**
	 * 删除处罚信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("ybcf:cfjd:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		try {
			for (int i = 0; i < arrids.length; i++) {
				punishsimplecfjdservice.deleteInfo(Long.parseLong(arrids[i]));
				punishsimplecfjdservice.updateLaspInfo(Long.parseLong(arrids[i]));
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
	@RequiresPermissions("ybcf:cfjd:export")
	@RequestMapping(value = "exportcfjd/{flag}/{id}")
	@ResponseBody
	public String getAjWord(@PathVariable("flag") String flag,@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		XZCF_CfjdInfoEntity jce ;
		if("la".equals(flag)){
			jce=punishsimplecfjdservice.findInfoByLaId(id);
		}else{
			jce= punishsimplecfjdservice.findInfoById(id);
		}
		Map<String, Object> map = punishsimplecfjdservice.getWsdcword(id,flag);
		String filename = map.get("number").toString() + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		if("1".equals(jce.getPercomflag())){
			WordUtil.ireportWord(map, "comcfjd.ftl", filePath, filename, request);
		}else{
			WordUtil.ireportWord(map, "percfjd.ftl", filePath, filename, request);
		}
		return "/download/" + filename;
	}
	
	/**
	 * 总览查看处罚信息跳转
	 * @param model
	 */
	@RequestMapping(value = "viewcfjd/{id}")
	public String viewcfjd(@PathVariable("id") long id, Model model) {
		XZCF_CfjdInfoEntity jce = punishsimplecfjdservice.findInfoByLaId(id);
		model.addAttribute("jce", jce);
		return "aqzf/xzcf/ybcf/cfjd/view";
	}
}
