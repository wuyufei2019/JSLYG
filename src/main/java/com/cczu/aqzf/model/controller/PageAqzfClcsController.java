package com.cczu.aqzf.model.controller;

import com.alibaba.fastjson.JSON;
import com.cczu.aqzf.model.entity.AQZF_SafetyCheckRecordEntity;
import com.cczu.aqzf.model.entity.AQZF_SetBasicInfoEntity;
import com.cczu.aqzf.model.entity.AQZF_TipstaffEntity;
import com.cczu.aqzf.model.entity.AQZF_TreatmentEntity;
import com.cczu.aqzf.model.service.*;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;
import org.apache.commons.lang3.StringUtils;
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
 * 处理措施Controller
 * @author YZH
 */
@Controller
@RequestMapping("aqzf/clcs")
public class PageAqzfClcsController extends BaseController {

	@Autowired
	private AqzfJcfaService aqzfJcfaService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private AqzfClcsService aqzfClcsService;
	@Autowired
	private AqzfSetBasicInfoService aqzfsetbasicinfoservice;
	@Autowired
	private AqzfJcjlService aqzfJcjlService;
	@Autowired
	private AqzfJcnrService aqzfJcnrService;
	@Autowired
	private AqzfWfxwService aqzfWfxwService;
	@Autowired
	private AqzfZfryService aqzfZfryService;
	
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "aqzf/clcs/index";
	}
	
	/**
	 * list页面 
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("0")){//安监局
			map.put("xzqy",sessionuser.getXzqy());
			if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
				map.put("jglx",sessionuser.getUserroleflg());
		}
		map.put("qyname", request.getParameter("aqzf_clcs_qyname"));
		map.put("M1", request.getParameter("aqzf_clcs_M1"));
		return aqzfClcsService.dataGrid(map);	
		
	}

//	/**
//	 * 添加页面跳转(直接添加暂时弃用)
//	 * @param model
//	 */
//	@RequestMapping(value = "create" , method = RequestMethod.GET)
//	public String create(Model model) {
//		AQZF_SetBasicInfoEntity sbe=aqzfsetbasicinfoservice.findInfor();
//		model.addAttribute("gov", sbe.getGov()==null?"":sbe.getGov());
//		model.addAttribute("court", sbe.getCourt()==null?"":sbe.getCourt());
//		model.addAttribute("action", "create");
//		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
//		return "aqzf/clcs/form";
//	}
	
	/**
	 * 检查记录页面添加现场处理页面跳转
	 * @param model
	 * id 检查记录id
	 */
	@RequestMapping(value = "create2/{id}" , method = RequestMethod.GET)
	public String create2(@PathVariable("id") Long id,Model model) {
		AQZF_TreatmentEntity clcs = new AQZF_TreatmentEntity();
		clcs.setID3(id);
		//设置政府法院
		AQZF_SetBasicInfoEntity sbe=aqzfsetbasicinfoservice.findInfor();
		clcs.setM5(sbe.getGov()==null?"":sbe.getGov());
		clcs.setM6(sbe.getCourt()==null?"":sbe.getCourt());
		//根据检查记录id查找检查记录
		AQZF_SafetyCheckRecordEntity jcjl=aqzfJcjlService.findjl(id);
		if(!StringUtils.isBlank(jcjl.getM8())){
			String []zfry = jcjl.getM8().split(",");
			if(zfry.length>1){
				AQZF_TipstaffEntity xzry1=aqzfZfryService.findBym1(zfry[0]);
				if(xzry1!=null){
					clcs.setM7_1(xzry1.getM1());
					clcs.setM8_1(xzry1.getM3());
				}
				AQZF_TipstaffEntity xzry2=aqzfZfryService.findBym1(zfry[1]);
				if(xzry2!=null){
					clcs.setM7_2(xzry2.getM1());
					clcs.setM8_2(xzry2.getM3());
				}
			}else{
				AQZF_TipstaffEntity xzry=aqzfZfryService.findBym1(zfry[0]);
				if(xzry!=null){
					clcs.setM7_1(xzry.getM1());
					clcs.setM8_1(xzry.getM3());
				}
			}
		}
		clcs.setID2(jcjl.getID2());//设置企业id
		clcs.setM9(jcjl.getM2());//负责人
		clcs.setM1(DateUtils.getSysTimestamp());
		clcs.setM10(jcjl.getM15()+"");
		//存在问题
		List<Map<String,Object>> list = aqzfJcnrService.findAllByJlid(id,jcjl.getM15()+"");
		model.addAttribute("wfxwlist", JSON.toJSONString(list).replace("\\r\\n",""));
		model.addAttribute("clcs", clcs);
		model.addAttribute("action", "create");
		
		return "aqzf/clcs/form2";
	}
	
	/**
	 * 添加处理措施信息
	 * @param entity
	 */
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(AQZF_TreatmentEntity entity, Model model) {
		String datasuccess="success";
		//获取文书编号
		AQZF_SetBasicInfoEntity bh =aqzfsetbasicinfoservice.findInfor();
		AQZF_SafetyCheckRecordEntity jcjl=aqzfJcjlService.findjl(entity.getID3());
		String m0 = jcjl.getM0();
		entity.setM0("（"+bh.getSsqjc()+"）应急现决〔"+m0.substring(m0.indexOf("〔")+1, m0.indexOf("〕"))+"〕"+m0.substring(m0.indexOf("〕")+1, m0.length()));
		
		Timestamp t= DateUtils.getSysTimestamp();
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		entity.setID1(Long.valueOf(sessionuser.getId()));
		aqzfClcsService.addInfo(entity);
		return datasuccess;
		
	}	
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("aqzf:clcs:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的处理措施信息
		AQZF_TreatmentEntity clcs = aqzfClcsService.findById(id);
		model.addAttribute("clcs", clcs);
		//存在问题
		List<Map<String,Object>> list = aqzfJcnrService.findAllByJlid(clcs.getID3(),clcs.getM10()+"");
		model.addAttribute("wfxwlist", JsonMapper.getInstance().toJson(list));
		//返回页面
		model.addAttribute("action", "update");
		return "aqzf/clcs/form2";
	}
	
	/**
	 * 修改处理措施信息
	 * @param model,model
	 */
	@RequiresPermissions("aqzf:clcs:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(AQZF_TreatmentEntity entity, Model model){
		String datasuccess="success";	
		entity.setS1(new Timestamp(new java.util.Date().getTime()));
		entity.setS2(new Timestamp(new java.util.Date().getTime()));
		entity.setS3(0);
		aqzfClcsService.updateInfo(entity);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 删除处理措施信息
	 */
	@RequiresPermissions("aqzf:clcs:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			aqzfClcsService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	 
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("aqzf:clcs:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Map<String, Object> clcs = aqzfClcsService.findInforById(id);
		//事故
		String sgyh = "";
		if(clcs.get("m2")!=null&&!StringUtils.isEmpty(clcs.get("m2").toString())){
			String wfxwids = clcs.get("m2").toString();
			List<Map<String,Object>> list = aqzfJcnrService.findAllByids(wfxwids, clcs.get("m10")+"");
			int i = 1;
			for (Map<String, Object> map : list) {
				sgyh += i+". 检查情况："+(map.get("wtms")==null?"":map.get("wtms").toString())+"<br/>&nbsp;&nbsp;&nbsp;&nbsp;须整改的问题："+(map.get("zgwt")==null?"":map.get("zgwt").toString())+"<br/>&nbsp;&nbsp;&nbsp;&nbsp;涉嫌违法行为："+(map.get("m1")==null?"":map.get("m1").toString())+"<br/>";
				i++;
			}
		}
		model.addAttribute("sgyh", sgyh);
		model.addAttribute("clcs", clcs);
		return "aqzf/clcs/view";
	}

	/**
	 * 导出处理措施word
	 * 
	 */
	@RequiresPermissions("aqzf:clcs:exportword")
	@RequestMapping(value = "exportword/{id}")
	@ResponseBody
	public String getAjWord(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = aqzfClcsService.getAjWord(id);
		//设置导出的文件名
		//String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		String filename = map.get("m0").toString() + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "clcsjds.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
}
