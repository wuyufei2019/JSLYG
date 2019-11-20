package com.cczu.aqzf.http.api;

import com.cczu.aqzf.model.entity.AQZF_SetBasicInfoEntity;
import com.cczu.aqzf.model.service.AqzfSetBasicInfoService;
import com.cczu.aqzf.model.service.AqzfZfryService;
import com.cczu.sys.comm.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * app自定义
 * @author zpc
 * @date 2018/03/22
 */
@Controller
@RequestMapping("api/zdy")
public class ApiZdyController extends BaseController {
	
	@Autowired
	private AqzfZfryService aqzfZfryService;
	@Autowired
	private AqzfSetBasicInfoService setbasicservice;
	
	/**
	 * 执法人员list页面
	 */
	@RequestMapping(value = "zfrylist")
	@ResponseBody
	public Map<String, Object> getZfryData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xzqy",request.getParameter("xzqy"));
		map.put("apptj", request.getParameter("apptj"));//搜索框条件-姓名/证件号
		return aqzfZfryService.dataGrid(map);
	}
	
	/**
	 * 根据执法人员id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "zfryview")
	@ResponseBody
	public Map<String,Object> getZfryView(long id) {
		Map<String,Object> zfry = aqzfZfryService.findAllById(id);
		return zfry;
	}
	
	/**
	 * 获得相关信息详情
	 * @param xzqy
	 * @param userid 登录人id
	 * @return
	 */
	@RequestMapping(value = "xgxxview")
	@ResponseBody
	public AQZF_SetBasicInfoEntity getXgxxView(String xzqy, long userid) {
		AQZF_SetBasicInfoEntity xgxx = setbasicservice.findInfor();
		return xgxx;
	}
}
