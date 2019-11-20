package com.cczu.model.controller;

import com.alibaba.fastjson.JSON;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.Bis_InfoChange;
import com.cczu.model.service.BisInfoChangeService;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.ServletUtils;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.Constant;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 企业变更controller
 *
 * @author
 * @date 2018年06月05日
 */
@Controller
@RequestMapping("bis/qybg")
public class PageBisInfoChangeController extends BaseController {

    @Autowired
    private BisInfoChangeService bisInfoChangeService;
    @Autowired
    private BisQyjbxxServiceImpl bisQyjbxxService;

    /**
     * 页面跳转
     *
     * @param model
     */
    @RequestMapping(value = "index")
    public String index(Model model) {
        ShiroUser user = UserUtil.getCurrentShiroUser();

        if (User.USERTYPE.QY.getUsertype().equals(user.getUsertype())) {
            BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(user.getQyid());
            if (be == null || be.getM1() == null)
                return "../error/001";
        }
        model.addAttribute("usertype", user.getUsertype());
        model.addAttribute("username", user.getName());
        return "qyxx/qybg/index";
    }

    /**
     * 页面跳转
     *
     * @param model
     */
    @RequestMapping(value = "tabindex")
    public String tabIndex(@RequestParam("id") long id, Model model) {
        model.addAttribute("qyid", id);
        return "qyxx/qybg/tabindex";
    }

    /**
     * list界面
     *
     * @param request
     */
    @RequiresPermissions("bis:qybg:view")
    @RequestMapping(value = "list")
    @ResponseBody
    public Map<String, Object> getData(HttpServletRequest request) {
        Map<String, Object> dataMap = getPageMap(request);
        dataMap.putAll(getAuthorityMap());
        dataMap.putAll(ServletUtils.getParametersStartingWith(request, "view_"));
        return bisInfoChangeService.dataGrid(dataMap);
    }

    /**
     * 查询单个企业的变更记录
     *
     * @param id
     */
    @RequiresPermissions("bis:qybg:view")
    @RequestMapping(value = "tablist/{id}")
    @ResponseBody
    public List<Map<String, Object>> getBisData(@PathVariable long id, HttpServletRequest request) {
        Map<String, Object> map = ServletUtils.getParametersStartingWith(request, "view_");
        map.put("qyid", id);
        return bisInfoChangeService.dataGridForQy(map);
    }


    /**
     * 添加页面跳转
     *
     * @param model
     */
    @RequiresPermissions("bis:qybg:add")
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model) {
        ShiroUser user = UserUtil.getCurrentShiroUser();
        model.addAttribute("action", "create");
        model.addAttribute("qyid", user.getQyid());
        model.addAttribute("username", user.getName());
        return "qyxx/qybg/form";
    }

    /**
     * 添加信息
     *
     * @param entity
     */
    @RequiresPermissions("bis:qybg:add")
    @RequestMapping(value = "create")
    @ResponseBody
    public String create(Bis_InfoChange entity, @RequestParam("bisjson") String bisjson) {
        String datasuccess = "success";
        try {
            bisInfoChangeService.addInfo(entity, bisjson);
        } catch (Exception e) {
            datasuccess = "failed";
        }
        return datasuccess;
    }

    /**
     * 审核页面跳转
     *
     * @param id,model
     */
    @RequestMapping(value = "review/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") long id, Model model, HttpServletRequest request) {
        Bis_InfoChange entity = bisInfoChangeService.findById(id);
        model.addAttribute("entity", entity);
        model.addAttribute("qylist", JSON.parseObject(entity.getBisoldjson(), Map.class));
        model.addAttribute("qylistnew", JSON.parseObject(entity.getBisnewjson(), Map.class));
        model.addAttribute("action", "review");
        //  model.addAttribute("diflist", JSON.toJSON(dif));
        model.addAttribute("username", UserUtil.getCurrentShiroUser().getName());
        String action = request.getParameter("action");
        if (StringUtils.isNotEmpty(action)) {
            model.addAttribute("isview", Constant.ENGLISH_YES);
        }
        return "qyxx/qybg/reviewform";
    }

    /**
     * 审核信息
     *
     * @param request,model
     */
    @RequiresPermissions("bis:qybg:sh")
    @RequestMapping(value = "review")
    @ResponseBody
    public String update(HttpServletRequest request) {
        String datasuccess = "success";
        long id = Long.parseLong(request.getParameter("ID"));
        String result = request.getParameter("result");
        String reviewer = request.getParameter("reviewer");
        try {
            bisInfoChangeService.reviewInfo(id, result, reviewer);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            datasuccess = "failed";
        }
        return datasuccess;
    }

    /**
     * 删除信息
     *
     * @param ids
     */
    @RequiresPermissions("bis:qybg:delete")
    @RequestMapping(value = "delete/{ids}")
    @ResponseBody
    public String delete(@PathVariable("ids") String ids) {
        String datasuccess = "success";
        String[] arrids = ids.split(",");
        for (int i = 0; i < arrids.length; i++) {
            bisInfoChangeService.deleteInfo(Long.parseLong(arrids[i]));
        }
        return datasuccess;
    }

    /**
     * 查看页面跳转
     *
     * @param id
     */
    @RequiresPermissions("bis:qybg:view")
    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public String sview(@PathVariable("id") long id, Model model) {
        Bis_InfoChange entity = bisInfoChangeService.findById(id);
        model.addAttribute("entity", entity);
        return "qyxx/qybg/view";
    }

    /**
     * 查询企业未审核记录总数
     */
    @RequestMapping(value = "notreview", method = RequestMethod.GET)
    @ResponseBody
    public int getNotReviewCount() {
        ShiroUser user = UserUtil.getCurrentShiroUser();
        if (User.USERTYPE.QY.getUsertype().equals(user.getUsertype())) {
            List<Bis_InfoChange> list = bisInfoChangeService.FindNotReviewListByQyid(user.getQyid());
            return list.size();
        }
        return 0;
    }

}
