package com.cczu.model.controller;

import com.cczu.model.entity.ERM_EmergencyDutyPersonEntity;
import com.cczu.model.service.ErmDutyPersonService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;
import com.cczu.sys.system.service.ShiroRealm;
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
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 值班人员controller
 *
 * @author jason
 * @date 2017年7月1日
 */
@Controller
@RequestMapping("erm/duty/person")
public class PageErmDutyPersonController extends BaseController {

    @Autowired
    private ErmDutyPersonService personService;

    /**
     * 列表显示页面
     *
     * @param model
     */
    @RequestMapping(value = "index")
    public String index(Model model) {
        model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
        return "erm/duty/person/index";
    }

    /**
     * list页面
     *
     * @param request
     */
    @RequiresPermissions("erm:duty-person:view")
    @RequestMapping(value = "list")
    @ResponseBody
    public Map<String, Object> getData(HttpServletRequest request) {
        Page<ERM_EmergencyDutyPersonEntity> page = getPage(request);
        List<PropertyFilter> list = PropertyFilter.buildFromHttpRequest(request);
        personService.search(page, list);
        return getEasyUIData(page);

    }

    /**
     * 添加页面跳转
     *
     * @param model
     */
    @RequiresPermissions("erm:duty-person:add")
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model) {
        ShiroRealm.ShiroUser user = UserUtil.getCurrentShiroUser();
        model.addAttribute("action", "create");
        model.addAttribute("username", user.getName());
        model.addAttribute("qyid", user.getQyid());
        model.addAttribute("usertype", user.getUsertype());
        return "erm/duty/person/form";
    }

    /**
     * 添加避难场所信息
     *
     * @param
     */
    @RequiresPermissions("erm:duty-person:add")
    @RequestMapping(value = "create")
    @ResponseBody
    public String create(ERM_EmergencyDutyPersonEntity ee, HttpServletRequest request) {
        String datasuccess = "success";
        personService.save(ee);
        // 返回结果
        return datasuccess;
    }

    /**
     * 修改页面跳转
     *
     * @param id ,model
     */
    @RequiresPermissions("erm:duty-person:update")
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") Long id, Model model) {

        ERM_EmergencyDutyPersonEntity entity = personService.get(id);
        // 查询选择的避难场所信息
        model.addAttribute("entity", entity);
        return "erm/duty/person/form";
    }

    /**
     * 修改避难场所信息
     *
     * @param request ,model
     */
    @RequiresPermissions("erm:duty-person:update")
    @RequestMapping(value = "update")
    @ResponseBody
    public String update(ERM_EmergencyDutyPersonEntity ee) {
        personService.save(ee);
        return "success";
    }

    /**
     * 删除避难场所信息
     *
     * @throws ParseException
     */
    @RequiresPermissions("erm:duty-person:delete")
    @RequestMapping(value = "delete/{ids}")
    @ResponseBody
    public String delete(@PathVariable("ids") String ids) {
        String datasuccess = "删除成功";
        // 可以批量删除
        String[] arrids = ids.split(",");
        for (int i = 0; i < arrids.length; i++) {
            personService.delete(Long.parseLong(arrids[i]));
        }
        return datasuccess;
    }

    /**
     * 查看页面跳转
     *
     * @param id,model
     */
    @RequiresPermissions("erm:duty-person:view")
    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") Long id, Model model) {
        ERM_EmergencyDutyPersonEntity ee = personService.get(id);
        model.addAttribute("entity", ee);
        return "erm/duty/person/view";
    }


}
