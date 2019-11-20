package com.cczu.model.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.AQJG_DSFPjEntity;
import com.cczu.model.service.IAqjgPjService;
import com.cczu.sys.comm.controller.BaseController;


/**
 * 第三方评价记录
 * @author zpc
 * @date 2017/07/10
 */
@Controller
@RequestMapping("dsffw/pj")
public class PageAqjgDSFPjController extends BaseController{

		@Autowired
		private IAqjgPjService AqjgPjService;
		
		/**
		 * 默认页面
		 */
		@RequestMapping(value="index")
		public String index(Model model) {
			return "aqjg/dsf/pj/index";
		}
		
		/**
		 * list页面
		 */
		@RequestMapping(value="list")
		@ResponseBody
		public Map<String, Object> getData(HttpServletRequest request) {
			Map<String, Object> map = getPageMap(request);
			map.put("dsfname", request.getParameter("aqjg_dsf_pj_M1"));
			map.put("dsfpj", request.getParameter("aqjg_dsf_pj_M2"));
			map.put("dsfnd", request.getParameter("aqjg_dsf_pj_M4"));
			return AqjgPjService.dataGrid(map);
		}
		
		@RequestMapping(value = "ndlist")
		@ResponseBody
		public List<Map<String, Object>> getNdList(HttpServletRequest request) {
			return AqjgPjService.findNdList();
		}
		
		/**
		 * 删除第三方评价记录
		 * 
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
				AqjgPjService.deleteInfo(Long.parseLong(aids[i]));
			}
			return datasuccess;
		}
		
		/**
		 * 显示所有列
		 * 
		 * @param id,model
		 */
		@RequiresPermissions("dsffw:pj:export")
		@RequestMapping(value = "colindex", method = RequestMethod.GET)
		public String colindex(Model model) {
			model.addAttribute("url","dsffw/pj/export");
			return "common/formexcel";
		}
		
		/**
		 * 导出Excel
		 * 
		 * @param request
		 */
		@RequiresPermissions("dsffw:pj:export")
		@RequestMapping(value = "export")
		@ResponseBody
		public void getExcel(HttpServletRequest request, HttpServletResponse response) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("colval", request.getParameter("colval"));
			map.put("coltext", request.getParameter("coltext"));
			AqjgPjService.exportExcel(response, map);
		}
		
		/**
		 * list页面
		 */
		@RequestMapping(value="dnamelist")
		@ResponseBody
		public List<Map<String, Object>> getdname(HttpServletRequest request) {
			return AqjgPjService.getdname();
		}
		
		/**
		 * list页面
		 */
		@RequestMapping(value="dnamelist2")
		@ResponseBody
		public List<Map<String, Object>> getdname2(HttpServletRequest request) {
			return AqjgPjService.getdname2();
		}
		
		/**
		 * 添加评分信息页面跳转
		 * 
		 * @param model
		 */
		@RequiresPermissions("dsffw:pj:add")
		@RequestMapping(value = "create" , method = RequestMethod.GET)
		public String create(Model model) {
			model.addAttribute("action", "create");
			return "aqjg/dsf/pj/form";
		}
		
		/**
		 * 添加信息
		 * @param request,model
		 */
		@RequiresPermissions("dsffw:pj:add")
		@RequestMapping(value = "create" )
		@ResponseBody
		public String create(AQJG_DSFPjEntity pjlist, HttpServletRequest request) {
			String datasuccess="success";
			AqjgPjService.addInfo(pjlist);
			//返回结果
			return datasuccess;
		}
		
		/**
		 * 修改页面跳转
		 * @param id,model
		 */
		@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
		public String update(@PathVariable("id") Long id, Model model) {
			AQJG_DSFPjEntity pjlist  = AqjgPjService.findById(id);
			model.addAttribute("pjlist", pjlist);
			model.addAttribute("action", "update");
			return "aqjg/dsf/pj/form";
		}
		
		/**
		 * 修改
		 * @param request,model
		 */
		@RequestMapping(value = "update")
		@ResponseBody
		public String update(AQJG_DSFPjEntity pjlist,  HttpServletRequest request){
			String datasuccess="success";	
			AqjgPjService.updateInfo(pjlist);
			return datasuccess;
		}
		
		
		/**
		 * 查看页面跳转
		 * @param id,model
		 */
		@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
		public String view(@PathVariable("id") Long id, Model model) {
			//查询选择的安全备案信息
			AQJG_DSFPjEntity pjlist = AqjgPjService.findById(id);		
			model.addAttribute("pjlist", pjlist);
			return "aqjg/dsf/pj/view";
		}

}

