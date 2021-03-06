package com.cczu.model.sbssgl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.sbssgl.entity.SBSSGL_NDBBEntity;
import com.cczu.model.sbssgl.entity.SBSSGL_SBDXEntity;
import com.cczu.model.sbssgl.service.SBSSGLNdbbService;
import com.cczu.model.sbssgl.service.SBSSGLSbdxService;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 设备设施管理-设备大修controller
 */
@Controller
@RequestMapping("sbssgl/sbdx")
public class PageSbssglSbdxController extends BaseController {

	@Autowired
	private SBSSGLSbdxService sBSSGLSbdxService;
	@Autowired
	private BisQyjbxxServiceImpl qyjbxxServiceImpl;
	@Autowired
	private SBSSGLNdbbService sBSSGLNdbbService;
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model, HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					model.addAttribute("type","2");//集团公司
				else
					model.addAttribute("type","1");//子公司
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else{
			return "../error/403";
		}
		
		if ("tzsb".equals(request.getParameter("sbtype"))) {
			model.addAttribute("sbtype","1");//特种设备
		} else {
			model.addAttribute("sbtype","0");//普通设备
		}
		return "sbssgl/sbdx/index";
	}
	
	/**
	 * list页面
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("sbtype", request.getParameter("sbtype"));
		map.put("qyname", request.getParameter("qyname"));
		map.put("m7", request.getParameter("m7"));
		map.put("m2", request.getParameter("m2"));
		map.put("m5", request.getParameter("m5"));
		map.put("m18", request.getParameter("m18"));
		map.put("m8", request.getParameter("m8"));
		map.put("m19", request.getParameter("m19"));
		map.putAll(getAuthorityMap());
		return sBSSGLSbdxService.dataGrid(map);
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
		for(int i=0;i<aids.length;i++){
			//删除点巡检信息
			sBSSGLSbdxService.deleteInfoById(Long.parseLong(aids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					model.addAttribute("type","2");//集团公司
				else
					model.addAttribute("type","1");//子公司
			}
		}
		//获取点巡检信息
		Map<String,Object> sbdx = sBSSGLSbdxService.findById(id);	
		model.addAttribute("sbdx", sbdx);
		return "sbssgl/sbdx/view";
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model, HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					model.addAttribute("type","2");//集团公司
				else
					model.addAttribute("type","1");//子公司
			}
		}
		model.addAttribute("action", "create");
		model.addAttribute("sbtype", request.getParameter("sbtype"));
		return "sbssgl/sbdx/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequestMapping(value = "create" , method = RequestMethod.POST)
	@ResponseBody
	public String create(SBSSGL_SBDXEntity entity,HttpServletRequest request) {
		String datasuccess="success";
		String m18 = entity.getM18();//计划修理时间
		entity.setM7(m18.substring(0,4));//年份
		
		sBSSGLSbdxService.addInfo(entity);
		//返回结果
		return datasuccess;
	}
	

	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					model.addAttribute("type","2");//集团公司
				else
					model.addAttribute("type","1");//子公司
			}
		}
		SBSSGL_SBDXEntity sbdx = sBSSGLSbdxService.find(id);	
		model.addAttribute("sbdx", sbdx);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("sbtype", request.getParameter("sbtype"));
		return "sbssgl/sbdx/form";
	}
	
	/**
	 * 修改信息
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(SBSSGL_SBDXEntity entity, Model model,HttpServletRequest request){
		String datasuccess="success";
		String type = request.getParameter("type");
		if ("1".equals(type)) {//子公司
			ShiroUser user = UserUtil.getCurrentShiroUser();
			entity.setQyid(user.getQyid());
		}
		sBSSGLSbdxService.updateInfo(entity);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 修改上传状态
	 * 
	 */
	@RequestMapping(value = "updsczt/{id}")
	@ResponseBody
	public String updsczt(@PathVariable("id") Long id){
		String datasuccess="success";
		sBSSGLSbdxService.updStatus(id);
		//返回结果
		return datasuccess;
	}
	
	//导入页面跳转
  	@RequestMapping(value = "exinjump", method = RequestMethod.GET)
  	public String exin(Model model) {
  		model.addAttribute("action", "exin");
  		return "common/importForm";
  	}
  	
  	/**
  	 * 导入设备大项修计划信息
  	 * 
  	 * @param response
  	 * @param request
  	 * @return
  	 */
  	@RequestMapping(value = "exin")
  	@ResponseBody
  	public String expiExcel(HttpServletResponse response, HttpServletRequest request) {
  		Map<String, Object> map = uploadExcel(request, response);
  		Map<String, Object> resultmap = new HashMap<String, Object>();
  		if (map.get("content") != null) {
  			resultmap = sBSSGLSbdxService.exinExcel(map);
  		} else {
  			resultmap.put("returncode", -2);
  		}
  		return JsonMapper.toJsonString(resultmap);
  	}
  	
	/**
	 * 普通设备显示所有列
	 * @param id,model 
	 */
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","sbssgl/sbdx/export");
		return "common/formexcel";
	}
	
	/**
	 * 导出设备大项修计划
	 */
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qyname", request.getParameter("qyname"));
		map.put("m7", request.getParameter("m7"));//年度
		map.put("type", request.getParameter("type"));
		map.put("m2", request.getParameter("m2"));
		map.put("m5", request.getParameter("m5"));
		map.put("m8", request.getParameter("m8"));
		map.put("m18", request.getParameter("m18"));
		map.put("m19", request.getParameter("m19"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.putAll(getAuthorityMap());
		sBSSGLSbdxService.exportExcel(response, map, request);
	}
	
	/**
	 * 导出大项修计划附件
	 */
	@RequestMapping(value = "exportfj", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String,Object>> exportFj(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("m7", request.getParameter("m7"));//年度
		map.put("m5", request.getParameter("m5"));
		map.putAll(getAuthorityMap());
		return sBSSGLSbdxService.exportFj(map);
	}
	
	/**
	 * 年度报表页面跳转
	 * @param id,model 
	 */
	@RequestMapping(value = "ndbb", method = RequestMethod.GET)
	public String ndbb(Model model) {
		return "sbssgl/sbdx/ndbb";
	}
	
	/**
	 * 年度报表页面
	 * @param request
	 */
	@RequestMapping(value="ndbblist")
	@ResponseBody
	public Map<String, Object> ndbbList(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("type", "0");//设备大项修计划
		map.putAll(getAuthorityMap());
		return sBSSGLNdbbService.dataGrid(map);
	}
	
	/**
	 * 上传年度报表页面跳转
	 * @param id,model 
	 */
	@RequestMapping(value = "uploadndbb", method = RequestMethod.GET)
	public String uploadNdbb(Model model) {
		model.addAttribute("action", "uploadndbb");
		return "sbssgl/sbdx/uploadndbb";
	}
	
	/**
	 * 保存年度报表信息
	 * 
	 */
	@RequestMapping(value = "uploadndbb", method = RequestMethod.POST)
	@ResponseBody
	public String uploadndbb(SBSSGL_NDBBEntity entity){
		String datasuccess="success";
		entity.setM4("0");//类别是设备大项修计划
		sBSSGLNdbbService.addInfo(entity);
		//返回结果
		return datasuccess;
	}
	
}
