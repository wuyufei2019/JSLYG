package com.cczu.aqzf.model.controller;

import com.cczu.aqzf.model.entity.*;
import com.cczu.aqzf.model.service.*;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.UserService;
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
import java.util.List;
import java.util.Map;

/**
 * 行政处罚--告知controller
 * 
 * @author jason
 * 
 */
@Controller
@RequestMapping("xzcf/ybcf/cfgz")
public class PageXzcfCommonGzController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	@Autowired
	private IPunishSimpleGzService punishsimplegzservice;
	@Autowired
	private IXzcfCommonLaspService xzcfcommonlaspservice;
	@Autowired
	private AqzfSetNumberService setNumberService;
	@Autowired
	private AqzfSetBasicInfoService aqzfsetbasicinfoservice;
	@Autowired
	private AqzfWfxwService aqzfWfxwService;
	@Autowired
	private XzcfCommonDcService xzcfcommondcservice;
	@Autowired
	private XzcfDCzjService xzcfDCzjService;
	@Autowired
	private AqzfJcnrService aqzfJcnrService;
	@Autowired
	private AqzfZfryService aqzfZfryService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {
		return "aqzf/xzcf/ybcf/cfgz/index";
	}

	/**
	 * list页面
	 */
	@RequiresPermissions("ybcf:cfgz:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("number", request.getParameter("ybcf_cfgz_number"));
		map.put("name", request.getParameter("ybcf_cfgz_name"));
		map.put("startdate", request.getParameter("ybcf_cfgz_startdate"));
		map.put("enddate", request.getParameter("ybcf_cfgz_enddate"));
		map.put("cftype", "1");
		map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());
		return punishsimplegzservice.dataGrid(map);
	}
	
	/**
	 * 添加告知信息页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("ybcf:cfgz:add")
	@RequestMapping(value = "create/{id}")
	public String create(@PathVariable("id") long id,Model model) {
		XZCF_GzsInfoEntity jie = new XZCF_GzsInfoEntity();
		XZCF_YbcfDcbgEntity dcbg = xzcfcommondcservice.findInfoByLaId(id);
		XZCF_YbcfLaspEntity lasp = xzcfcommonlaspservice.findInfoById(id);
		List<Map<String,Object>> list = aqzfJcnrService.findAllByids(dcbg.getSccids(),lasp.getZycllx()+"");
		XZCF_YbcfLaspEntity ybcf = xzcfcommonlaspservice.findInfoById(id);
		if(!StringUtils.isEmpty(ybcf.getEnforcer1())){
			AQZF_TipstaffEntity xzry=aqzfZfryService.findBym1(ybcf.getEnforcer1());
			if(xzry!=null){
				jie.setContactname(xzry.getM1());
				jie.setPhone(xzry.getM5());
			}
		}
		jie.setId1(id);
		if(ybcf.getCfdxlx().equals("2")){
			jie.setName(dcbg.getCfryname());
		}else{
			jie.setName(dcbg.getQyname());
		}

		jie.setIllegalact(dcbg.getResearchresult());
		String wfxw = "";
		String xq = "";
		String zyclbz = "";
		int z = 1;
		if(list!=null){
			if(ybcf.getCfdxlx().equals("2")){
				xq = dcbg.getCfryname();
			}else{
				xq = dcbg.getQyname();
			}
			
			String ajxq = ybcf.getAjjbqk();
			int ajxqi = ajxq.indexOf("发现：");
			if(ajxqi!=-1){
				ajxq = ajxq.substring(ajxqi+3,ajxq.length());
			}
			xq += ajxq;
			String reg=".*[\\.。]$";
			if(xq.matches(reg)){
				xq = xq.substring(0, xq.length()-1)+"，";
			}else{
				xq += "，";
			}

			if(list.size()==1){
				wfxw = list.get(0).get("m1")==null?"":list.get(0).get("m1").toString();
				zyclbz = list.get(0).get("m7")==null?"":list.get(0).get("m7").toString();
			}else{
				for (Map<String, Object> map : list) {
					wfxw += z+"."+(map.get("m1")==null?"":map.get("m1").toString());
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
		jie.setWfxw(wfxw);
		jie.setUnlaw(dcbg.getUnlaw());
		jie.setEnlaw(dcbg.getEnlaw());
		jie.setXzcf(dcbg.getXzcf());
		//详情
		String str=dcbg.getXzcf();
		String reg=".*[\\.。]$";
		if(str.matches(reg)){
		    str = str.substring(0, str.length()-1);
		}
		String cfdx = "";
		if(ybcf.getCfdxlx().equals("2")){
			cfdx = "你";
		}else{
			cfdx = "你公司";
		}
		xq = dcbg.getUnlawall()+xq+"因此违反了上述规定。依据"+dcbg.getPunishresult()+"之规定，参照"+zyclbz+"之规定，拟对"+cfdx+"作出"+str+"的行政处罚。";
		jie.setPunishresult(xq);
		//添加证据
		List<Map<String, Object>> czwt= xzcfDCzjService.dataGridCzwt(dcbg.getID());
	    String evidence = "";
	    if(czwt.size()>0){
			int i = 1;
			for (Map<String, Object> map2 : czwt) {
				evidence += "证据"+i+"."+map2.get("m1").toString()+" ";
				i++;
			}
		}
		jie.setEvidence(evidence);//证据
		model.addAttribute("jie", jie);
		
		model.addAttribute("action", "createSub");
		return "aqzf/xzcf/ybcf/cfgz/form";
	}

	/**
	 * 添加处罚告知
	 * @param request
	 */
	@RequiresPermissions("ybcf:cfgz:add")
	@RequestMapping(value = "createSub", method = RequestMethod.POST)
	@ResponseBody
	public String createSub(XZCF_GzsInfoEntity jie,
                            HttpServletRequest request, Model model) throws IOException {
		String datasuccess = "failed";
		Timestamp t = DateUtils.getSysTimestamp();
		jie.setS1(t);
		jie.setS2(t);
		jie.setS3(0);
		jie.setCftype("1");
		
		//设置编号
		AQZF_SetBasicInfoEntity bh =aqzfsetbasicinfoservice.findInfor();
		XZCF_YbcfLaspEntity ybcf = xzcfcommonlaspservice.findInfoById(jie.getId1());
		String m0 = ybcf.getNumber();
		if(ybcf.getCxflag().equals("1")){
			//已立案
			jie.setNumber("（"+bh.getSsqjc()+"）应急罚告〔"+m0.substring(m0.indexOf("〔")+1, m0.indexOf("〕"))+"〕"+m0.substring(m0.indexOf("〕")+1, m0.length()));
		}else{
			//预立案
			jie.setNumber("预立案");
		}
		
		if (punishsimplegzservice.addInfoReturnID(jie) > 0) {
			XZCF_YbcfLaspEntity yle= xzcfcommonlaspservice.findInfoById(jie.getId1());
			yle.setGzflag("1");
			xzcfcommonlaspservice.updateInfo(yle);
			datasuccess = "success";
		}
		return datasuccess;
	}

	/**
	 * 修改计划信息页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("ybcf:cfgz:update")
	@RequestMapping(value = "update/{id}")
	public String update(@PathVariable("id") long id, Model model) {

		XZCF_GzsInfoEntity jie = punishsimplegzservice.findInfoById(id);
		model.addAttribute("jie", jie);
		model.addAttribute("action", "updateSub");
		return "aqzf/xzcf/ybcf/cfgz/form";
	}

	/**
	 * 修改告知信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("ybcf:cfgz:update")
	@RequestMapping(value = "updateSub")
	@ResponseBody
	public String updateSub(XZCF_GzsInfoEntity jie,
                            HttpServletRequest request, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		jie.setS2(t);
		try {
			punishsimplegzservice.updateInfo(jie);
		} catch (Exception e) {
			datasuccess="failed";
		}
		return datasuccess;
	}

	/**
	 * 查看告知信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("ybcf:cfgz:view")
	@RequestMapping(value = "view/{id}")
	public String view(@PathVariable("id") long id, Model model) {
		XZCF_GzsInfoEntity jie = punishsimplegzservice.findInfoById(id);
		model.addAttribute("jie", jie);
		return "aqzf/xzcf/ybcf/cfgz/view";
	}

	/**
	 * 删除告知信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("ybcf:cfgz:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		try {
			for (int i = 0; i < arrids.length; i++) {
				punishsimplegzservice.deleteInfo(Long.parseLong(arrids[i]));
				punishsimplegzservice.updateLaspInfo(Long.parseLong(arrids[i]));
			}
		} catch (Exception e) {
			datasuccess="删除失败";
		}
		
		return datasuccess;
	}
	
	/**
	 * 导出告知书记录word
	 * 
	 */
	@RequiresPermissions("ybcf:cfgz:export")
	@RequestMapping(value = "exportgzs/{flag}/{id}")
	@ResponseBody
	public String getAjWord(@PathVariable("flag") String  flag,@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
	    Map<String, Object> map = punishsimplegzservice.getWsdcword(id,flag);
		//设置导出的文件名
	    String filename = map.get("number").toString() + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "gzsrecord.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
	/**
	 * 总览查看告知信息跳转
	 * @param model
	 */
	@RequestMapping(value = "viewcfgz/{id}")
	public String viewcfgz(@PathVariable("id") long id, Model model) {
		XZCF_GzsInfoEntity jie = punishsimplegzservice.findInfoByLaId(id);
		model.addAttribute("jie", jie);
		return "aqzf/xzcf/ybcf/cfgz/view";
	}
}
