package com.cczu.aqzf.model.controller;


import com.cczu.aqzf.model.entity.AQZF_WfxwTwoEntity;
import com.cczu.aqzf.model.service.AqzfWfxwTwoService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.system.utils.UserUtil;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 违法行为(新)
 * @author zpc
 * @date 2017/012/09
 */
@Controller
@RequestMapping("aqzf/wfxwtwo")
public class PageAqzfWfxwTwoController extends BaseController {

	@Autowired
	private AqzfWfxwTwoService aqzfWfxwTwoService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "aqzf/sjwh/wfxwtwo/index";
	}
	
	/**
	 * list页面
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("m1", request.getParameter("aqzf_wfxw_M1"));
		map.put("m6", request.getParameter("aqzf_wfxw_M6"));
		return aqzfWfxwTwoService.dataGrid(map);
	}
	
	/**
	 * 删除违法行为记录
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] aids = ids.split(",");
		for(int i=0;i<aids.length;i++){
			aqzfWfxwTwoService.deleteInfo(Long.parseLong(aids[i]));
		}
		return datasuccess;
	}

	/**
	 * 添加违法行为页面跳转
	 * @param model
	 */
	@RequiresPermissions("aqzf:wfxwtwo:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		return "aqzf/sjwh/wfxwtwo/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequiresPermissions("aqzf:wfxwtwo:add")
	@RequestMapping(value = "create" )
	@ResponseBody
	public String create(AQZF_WfxwTwoEntity wfxw, HttpServletRequest request) {
		String datasuccess="success";
		wfxw.setID1(Long.parseLong(UserUtil.getCurrentUser().getId().toString()));
		aqzfWfxwTwoService.addInfo(wfxw);
		return datasuccess;
	}
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		AQZF_WfxwTwoEntity wfxw = aqzfWfxwTwoService.findById(id);
		model.addAttribute("wfxw", wfxw);
		model.addAttribute("action", "update");
		return "aqzf/sjwh/wfxwtwo/form";
	}
	
	/**
	 * 修改
	 * @param wfxw,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(AQZF_WfxwTwoEntity wfxw, Model model){
		String datasuccess="success";
		aqzfWfxwTwoService.updateInfo(wfxw);
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		AQZF_WfxwTwoEntity wfxw = aqzfWfxwTwoService.findById(id);
		model.addAttribute("wfxw", wfxw);
		return "aqzf/sjwh/wfxwtwo/view";
	}
	
	/**
	 * 违法行为list下拉填充 id，text（违法行为）
	 * @param model
	 * @return
	 */
	@RequestMapping(value="idlist")
	@ResponseBody
	public List<Map<String, Object>> getWfxwIdlist(Model model) {
		return aqzfWfxwTwoService.getWfxwIdlist();
	}
	
	/**
	 * 显示所有列
	 * @param model,model
	 */
	@RequiresPermissions("aqzf:wfxwtwo:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","aqzf/wfxwtwo/export");
		return "common/formexcel";
	}
	
	/**
	 * 导出Excel
	 * 
	 * @param request
	 */
	@RequiresPermissions("aqzf:wfxwtwo:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void getExcel(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.put("m1", request.getParameter("aqzf_wfxw_M1"));
		map.put("m6", request.getParameter("aqzf_wfxw_M6"));
		aqzfWfxwTwoService.exportExcel(response, map);
	}
	
	//导入页面跳转
	@RequestMapping(value = "exinjump", method = RequestMethod.GET)
	public String exin(Model model) {
		model.addAttribute("action", "exin");
		return "common/importForm";
	}
	
	/**
	 * 导入
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
		resultmap = aqzfWfxwTwoService.exinExcel(map);
		return JsonMapper.toJsonString(resultmap);
	}	
	
	/**
	 * 根据ids查找违反条款
	 */
	@RequestMapping(value = "wftk/{wfids}")
	@ResponseBody
	public String findidcard(@PathVariable("wfids") String wfids) {
		String cbyj = "";
		if(!StringUtils.isEmpty(wfids)){
			String[] aids = wfids.split(",");
			for(int i=0;i<aids.length;i++){
				try {
					AQZF_WfxwTwoEntity wfxw = aqzfWfxwTwoService.findById(Long.parseLong(aids[i]));
					if(i==0){
						cbyj += wfxw.getM2();
					}else{
						cbyj += ","+wfxw.getM2();
					}
				} catch (Exception e) {

				}
			}
		}
		return cbyj;
	}
	
	/**
	 * 根据ids查找所有
	 */
	@RequestMapping(value = "wfall/{wfids}")
	@ResponseBody
	public Map<String,Object> findAll(@PathVariable("wfids") String wfids) {
		Map<String,Object> map = new HashMap<>();
		String wftk = "";
		String cfyj = "";
		if(!StringUtils.isEmpty(wfids)){
			String[] aids = wfids.split(",");
			for(int i=0;i<aids.length;i++){
				try {
					AQZF_WfxwTwoEntity wfxw = aqzfWfxwTwoService.findById(Long.parseLong(aids[i]));
					if(i==0){
						wftk += wfxw.getM2();
						cfyj += wfxw.getM4();
					}else{
						wftk += ","+wfxw.getM2();
						cfyj += wfxw.getM4();
					}
				} catch (Exception e) {

				}
			}
		}
		map.put("m2", wftk);
		map.put("m4", cfyj);
		return map;
	}
	
	/**
	 * 获取分类
	 * textjson {"id":"分类名称","text":"分类名称"}
	 * return String
	 */
	@RequestMapping(value="textjson")
	@ResponseBody
	public String textjson() {
		return aqzfWfxwTwoService.gettextjson();
	}
}
