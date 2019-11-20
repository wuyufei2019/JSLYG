package com.cczu.aqzf.model.controller;

import com.cczu.aqzf.model.entity.AQZF_WfxwClEntity;
import com.cczu.aqzf.model.service.AqzfWfxwClService;
import com.cczu.sys.comm.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 违法行为-处罚裁量
 *
 * @author zpc
 * @date 2018/01/09
 */
@Controller
@RequestMapping("aqzf/cfcl")
public class PageAqzfCfclController extends BaseController {

    @Autowired
    private AqzfWfxwClService aqzfWfxwClService;

    /**
     * 默认页面
     */
    @RequestMapping(value = "index/{id}")
    public String index(@PathVariable("id") long id1, Model model, HttpServletRequest request) {
        String version = request.getParameter("version");
        model.addAttribute("version", version);
        model.addAttribute("id1", id1);
        return "aqzf/sjwh/wfxw/clindex";
    }

    /**
     * list页面
     */
    @RequestMapping(value = "list")
    @ResponseBody
    public Map<String, Object> getData(HttpServletRequest request) {
        Map<String, Object> map = getPageMap(request);
        map.put("id1", request.getParameter("id1"));
        map.put("m6", request.getParameter("version"));
        return aqzfWfxwClService.dataGrid(map);
    }

    /**
     * 删除违法行为记录
     */
    @RequestMapping(value = "delete/{ids}")
    @ResponseBody
    public String delete(@PathVariable("ids") String ids) {
        String datasuccess = "删除成功";
        //可以批量删除
        String[] aids = ids.split(",");
        for (int i = 0; i < aids.length; i++) {
            aqzfWfxwClService.deleteInfo(Long.parseLong(aids[i]));
        }
        return datasuccess;
    }

    /**
     * 添加裁量页面跳转
     *
     * @param model
     */
    @RequestMapping(value = "create/{id1}", method = RequestMethod.GET)
    public String create(@PathVariable("id1") long id1, Model model, HttpServletRequest request) {
        model.addAttribute("action", "create");
        String version = request.getParameter("version");
        AQZF_WfxwClEntity wfxwcl = new AQZF_WfxwClEntity();
        ;
        wfxwcl.setID1(id1);
        wfxwcl.setM6(version);
        model.addAttribute("wfxwcl", wfxwcl);
        return "aqzf/sjwh/wfxw/clform";
    }

    /**
     * 添加信息
     *
     * @param request,model
     */
    @RequestMapping(value = "create")
    @ResponseBody
    public String create(AQZF_WfxwClEntity wfxwcl, HttpServletRequest request) {
        String datasuccess = "success";
        aqzfWfxwClService.addInfo(wfxwcl);
        return datasuccess;
    }

    /**
     * 修改页面跳转
     *
     * @param id,model
     */
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") Long id, Model model) {
        AQZF_WfxwClEntity wfxwcl = aqzfWfxwClService.findById(id);
        model.addAttribute("wfxwcl", wfxwcl);
        model.addAttribute("action", "update");
        return "aqzf/sjwh/wfxw/clform";
    }

    /**
     * 修改
     *
     * @param wfxwcl,model
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public String update(AQZF_WfxwClEntity wfxwcl, Model model) {
        String datasuccess = "success";
        aqzfWfxwClService.updateInfo(wfxwcl);
        return datasuccess;
    }

    /**
     * 裁量标准
     */
    @RequestMapping(value = "clbz/{id1}")
    @ResponseBody
    public String getAllByid1(@PathVariable("id1") Long id1, Model model) {
        return aqzfWfxwClService.getAllByid1(id1);
    }
}
