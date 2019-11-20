package com.cczu.model.controller;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.FxgkFxxxService;
import com.cczu.model.service.YhpcStoppointService;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.QRCode;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
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
import java.util.Map;
import java.util.UUID;


/**
 * 隐患排查点Controller
 *
 * @author YZH
 */
@Controller
@RequestMapping("yhpc/yhpcd")
public class PageYhpcYhpcdController extends BaseController {


    @Autowired
    private BisQyjbxxServiceImpl qyjbxxServiceImpl;
    @Autowired
    private YhpcStoppointService yhpcStoppointService;
    @Autowired
    private FxgkFxxxService fxxxService;

    /**
     * 列表显示页面
     *
     * @param model
     */
    @RequestMapping(value = "index")
    public String index(Model model) {
        model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
        return "yhpc/yhpcd/index";
    }

    /**
     * 隐患排查点list页面
     *
     * @param request
     */
    @RequestMapping(value = "list")
    @ResponseBody
    public Map<String, Object> getData(HttpServletRequest request, Model model) {
        //删除过期停产数据
        yhpcStoppointService.deleteStaleData();

        Map<String, Object> map = getPageMap(request);
        map.putAll(getAuthorityMap());
        map.put("name", request.getParameter("fxgk_fxxx_name"));
        map.put("type", "2");
        return fxxxService.dataGrid(map);

    }

    /**
     * 添加页面跳转
     *
     * @param model
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model) {
        Map<String, Object> yhpcd = new HashMap<>();
        ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
        //如果是企业，获取企业平面图
        if (sessionuser.getUsertype().equals("1")) {
            BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(sessionuser.getQyid());
            model.addAttribute("lng", be.getM16());
            model.addAttribute("lat", be.getM17());
            model.addAttribute("pmt", be.getM33_3());
            model.addAttribute("qyid", be.getID());
        }
        //生成二维码编码
        yhpcd.put("bindcontent", UUID.randomUUID().toString().replaceAll("-", ""));
        model.addAttribute("action", "createSub");
        model.addAttribute("yhpcd", yhpcd);
        model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
        return "yhpc/yhpcd/form";
    }

    /**
     * 修改页面跳转
     *
     * @param id,model
     */
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") Long id, Model model) {
        //查询选择的隐患排查点信息
        Map<String, Object> yhpcd = fxxxService.findInforById(id);
        model.addAttribute("yhpcd", yhpcd);

        //根据企业id获取企业对象
        BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(StringUtils.toLong(yhpcd.get("id1")));
        //获取企业平面图
        //如果无隐患点坐标获取企业坐标
        if (yhpcd.get("lng") == null || yhpcd.get("lng").equals("")) {
            model.addAttribute("lng", be.getM16());
            model.addAttribute("lat", be.getM17());
        }

        //返回页面
        model.addAttribute("action", "updateSub");
        model.addAttribute("pmt", StringUtils.defaultString(be.getM33_3()));
        model.addAttribute("qyid", be.getID());
        model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
        return "yhpc/yhpcd/form";
    }


    /**
     * 删除隐患排查点信息
     */
    @RequestMapping(value = "delete/{ids}")
    @ResponseBody
    public String delete(@PathVariable("ids") String ids) {
        String datasuccess = "删除成功";
        //可以批量删除
        String[] arrids = ids.split(",");
        for (int i = 0; i < arrids.length; i++) {
        }
        return datasuccess;
    }

    /**
     * 查看页面跳转
     *
     * @param id,model
     */
    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") Long id, Model model) {


        Map<String, Object> yhpcd = fxxxService.findInforById(id);
        BIS_EnterpriseEntity entity = qyjbxxServiceImpl.findInfoById(Long.parseLong(yhpcd.get("id1").toString()));
        yhpcd.put("pmt", entity.getM33_3());
        model.addAttribute("yhpcd", yhpcd);
        return "yhpc/yhpcd/view";
    }


    /**
     * 显示所有列
     */
    @RequiresPermissions("yhpc:yhpcd:export")
    @RequestMapping(value = "colindex", method = RequestMethod.GET)
    public String colindex(Model model) {
        model.addAttribute("url", "yhpc/yhpcd/export");
        return "/common/formexcel";
    }

    //导入页面跳转
    @RequestMapping(value = "exinjump", method = RequestMethod.GET)
    public String exin(Model model) {
        model.addAttribute("action", "exin");
        return "common/importForm";
    }


    //跳转已绑定巡检内容list
    @RequestMapping(value = "bdxjnr/{id}", method = RequestMethod.GET)
    public String bdxjnr(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
        String qyid = request.getParameter("qyid");
        model.addAttribute("id1", id);
        model.addAttribute("qyid", qyid);
        return "yhpc/yhpcd/xjnr";
    }

    //跳转巡检内容list
    @RequestMapping(value = "xjnrcreate/{id1},{qyid}", method = RequestMethod.GET)
    public String xjnrcreate(@PathVariable("id1") Long id, @PathVariable("qyid") Long qyid, Model model, HttpServletRequest request) {
        model.addAttribute("id1", id);
        model.addAttribute("qyid", qyid);
        return "yhpc/yhpcd/xjnrall";
    }

    /**
     * 生成二维码图片
     */
    @RequestMapping(value = "erm")
    @ResponseBody
    public String erweima(Long id, HttpServletResponse response, HttpServletRequest request) {
        Map<String, Object> yhpcd = fxxxService.findInforById(id);
        String text = " ";
        if (yhpcd.get("bindcontent") != null)
            text = yhpcd.get("bindcontent").toString();
        // 取得输出流
        String dowmloadPath = request.getSession().getServletContext().getRealPath("/") + "/download";
        String url = "/download/";
        try {
            url = url + QRCode.encode(text, null, dowmloadPath, true, "");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return url;


    }

}
