package com.cczu.aqzf.model.controller;


import com.cczu.sys.comm.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 自由裁量
 * @author zpc
 * @date 2018/01/09
 */
@Controller
@RequestMapping("aqzf/zycl")
public class PageAqzfZyclController extends BaseController {
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "aqzf/zycl/index";
	}
	
}
