package com.cczu.aqzf.model.controller;

import com.cczu.aqzf.model.entity.XZCF_SgxwtzEntity;
import com.cczu.aqzf.model.entity.XZCF_YbcfLaspEntity;
import com.cczu.aqzf.model.service.XzcfSgxwtzService;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 事故询问通知
 * @author zpc
 * @date 2018/1/18
 */
@Controller
@RequestMapping("xzcf/ybcf/sgxwtz")
public class PageXzcfSgxwtzController extends BaseController {

	@Autowired
	private XzcfSgxwtzService xzcfSgxwtzService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "aqzf/xzcf/ybcf/sgxwtz/index";
	}
	
	/**
	 * list页面
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());
		map.put("m1", request.getParameter("aqzf_sgxwtz_M1"));
		map.put("qyname", request.getParameter("aqzf_sgxwtz_qyname"));
		map.put("m2", request.getParameter("aqzf_sgxwtz_M2"));
		map.put("flag", request.getParameter("aqzf_sgxwtz_flag"));
		return xzcfSgxwtzService.dataGrid(map);
	}
	
	/**
	 * 删除询问通知记录
	 * @param ids
	 *
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] aids = ids.split(",");
		try {
			for(int i=0;i<aids.length;i++){
				xzcfSgxwtzService.deleteInfo(Long.parseLong(aids[i]));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			datasuccess="删除失败";
		}
		return datasuccess;
	}

	/**
	 * 添加跳转
	 * @param model
	 */
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		return "aqzf/xzcf/ybcf/sgxwtz/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequestMapping(value = "create" )
	@ResponseBody
	public String create(XZCF_SgxwtzEntity sgxwtz, HttpServletRequest request) {
		String datasuccess="success";
		ShiroUser su = UserUtil.getCurrentShiroUser();
		sgxwtz.setID1(su.getId());
		sgxwtz.setFlag("0");
		xzcfSgxwtzService.addInfo(sgxwtz);
		return datasuccess;
	}
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		XZCF_SgxwtzEntity sgxwtz  = xzcfSgxwtzService.findById(id);
		model.addAttribute("sgxwtz", sgxwtz);
		model.addAttribute("action", "update");
		return "aqzf/xzcf/ybcf/sgxwtz/form";
	}
	
	/**
	 * 修改
	 * @param sgxwtz
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(XZCF_SgxwtzEntity sgxwtz){
		String datasuccess="success";
		xzcfSgxwtzService.updateInfo(sgxwtz);
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Map<String,Object> sgxwtz = xzcfSgxwtzService.findAllbyid(id);	
		model.addAttribute("sgxwtz", sgxwtz);
		return "aqzf/xzcf/ybcf/sgxwtz/view";
	}
	
	/**
	 * id，m1
	 */
	@RequestMapping(value = "dcztjson")
	@ResponseBody
	public List<Map<String,Object>> dcztjson(){
		return xzcfSgxwtzService.dcztjson(UserUtil.getCurrentShiroUser().getXzqy());
	}
	
	/**
	 * 获取企业信息
	 * @param id,model
	 */
	@RequestMapping(value = "qydetail/{id}")
	@ResponseBody
	public String getQyDetailJson(@PathVariable("id") Long id) {
		XZCF_SgxwtzEntity sgxwtz  = xzcfSgxwtzService.findById(id);
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sgxwtz.getID2());
		return JsonMapper.getInstance().toJson(be);
	}
	
	/**
	 * 添加立案信息页面跳转
	 * @param model
	 */
	@RequestMapping(value = "createla/{version}")
	public String createla(@PathVariable("version") String version, Model model, HttpServletRequest request) {
		long sgtzid = Long.parseLong(request.getParameter("sgtzid").toString());
		XZCF_SgxwtzEntity sgxwtz  = xzcfSgxwtzService.findById(sgtzid);
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sgxwtz.getID2());
		
		XZCF_YbcfLaspEntity yle = new XZCF_YbcfLaspEntity();
		yle.setID2(0);//设置检查记录id
		yle.setId1(be.getID());
		yle.setLegalperson(be.getM5());
		yle.setContact(be.getM6());
		yle.setYbcode(be.getM9());
		yle.setDsaddress(be.getM8());
		yle.setDsperson(be.getM1());
		yle.setSgtzid(sgtzid+"");

		yle.setZycllx(version);
		model.addAttribute("yle", yle);
		model.addAttribute("action", "createsg");
		return "aqzf/xzcf/ybcf/lasp/sgform";
	}
}
