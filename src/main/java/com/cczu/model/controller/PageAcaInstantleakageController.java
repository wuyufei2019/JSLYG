package com.cczu.model.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.ACA_InstantleakageEntity;
import com.cczu.model.service.IAcaInstantleakageService;
import com.cczu.sys.comm.controller.BaseController;

/**
 * 事故后果计算controller
 *
 * @author jason
 */
@Controller
@RequestMapping("sghgjs/instantleakage")
public class PageAcaInstantleakageController extends BaseController {

    @Autowired
    private IAcaInstantleakageService acaInstantleakageService;
    @Autowired
    private BisQyjbxxServiceImpl bisQyjbxxService;

    /**
     * 默认页面
     */
    @RequestMapping(value = "index")
    public String index(Model model) {
        if((UserUtil.getCurrentShiroUser().getUsertype()).equals("1")) {
            BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(UserUtil.getCurrentShiroUser().getQyid());
            String lng = be.getM16();//企业所在位置的经度
            String lat = be.getM17();//企业所在位置的纬度
            model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
            model.addAttribute("lng2", lng);
            model.addAttribute("lat2", lat);
            return "sghgjs/instantleakage/index";
        }else{
            return "sghgjs/instantleakage/index";
        }
    }

    /**
     * @param model
     */
//	@RequiresPermissions("aca:instantleakage:add")
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model) {
//		model.addAttribute("list", new ACA_InstantleakageEntity());
        return "sghgjs/instantleakage/form";
    }

    /**
     * @param model
     */
//	@RequiresPermissions("aca:instantleakage:add")
    @RequestMapping(value = "create")
    @ResponseBody
    public String create(@Valid ACA_InstantleakageEntity aca, Model model) throws Exception {
        return acaInstantleakageService.countSave(aca);
    }

    @RequestMapping(value = "getjwd")
    @ResponseBody
    public BIS_EnterpriseEntity getJwd(Model model, HttpServletRequest request) throws Exception{
        Long qyid = StringUtils.toLong(request.getParameter("qyid"));
        BIS_EnterpriseEntity bs = bisQyjbxxService.findInfoById(qyid);
        return bs;
    }


}
