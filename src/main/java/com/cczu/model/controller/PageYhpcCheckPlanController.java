package com.cczu.model.controller;


import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.YHPC_CheckPlanEntity;
import com.cczu.model.service.FxgkFxxxService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.YhpcCheckPlanService;
import com.cczu.model.service.YhpcPlanDetailService;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.system.entity.Department;
import com.cczu.sys.system.entity.Role;
import com.cczu.sys.system.service.DepartmentService;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 巡检班次任务设置
 *
 * @author zpc
 * @date 2017/08/19
 */
@Controller
@RequestMapping("yhpc/bcrw")
public class PageYhpcCheckPlanController extends BaseController {

    @Autowired
    private YhpcCheckPlanService yhpcCheckPlanService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private FxgkFxxxService sxgkfxxxService;
    @Autowired
    private BisQyjbxxServiceImpl qyjbxxServiceImpl;
    @Autowired
    private IBisQyjbxxService bisQyjbxxService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private YhpcPlanDetailService planDetailService;

    /**
     * 默认页面
     */
    @RequestMapping(value = "index")
    public String index(Model model) {

        ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
        List<Role> list = roleService.findRoleById(sessionuser.getId());
        if (list.size() > 0) {
            if (list.get(0).getRoleCode().equals("companyadmin")) {
                model.addAttribute("userrole", "companyadmin");
            } else if (list.get(0).getRoleCode().equals("company")) {
                model.addAttribute("userrole", "company");
            } else {
                return "../error/403";
            }
            return "yhpc/bcrw/index";
        } else {
            return "../error/403";
        }
    }

    /**
     * list页面
     */
    @RequestMapping(value = "list")
    @ResponseBody
    public Map<String, Object> getData(HttpServletRequest request) {
        Map<String, Object> map = getPageMap(request);
        map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
        map.put("type", request.getParameter("yhpc_bcrw_type"));
        map.putAll(getAuthorityMap());

        //如果选择了部门下拉，则覆盖前面的部门条件，但也仅限于该用户所能看到的部门范围
        String depid = request.getParameter("yhpc_bcrw_depname");
        if (depid != null && (!depid.equals(""))) {
            Department dep = departmentService.get(Long.parseLong(depid));
            map.put("depcode3", dep.getCode());
        }
        return yhpcCheckPlanService.dataGrid(map);
    }

    /**
     * list页面
     */
    @RequestMapping(value = "newlist")
    @ResponseBody
    public Map<String, Object> getNewData(HttpServletRequest request) {
        Map<String, Object> map = getPageMap(request);
        map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
        map.put("type", request.getParameter("yhpc_bcrw_type"));
        map.putAll(getAuthorityMap());

        //如果选择了部门下拉，则覆盖前面的部门条件，但也仅限于该用户所能看到的部门范围
        String depid = request.getParameter("yhpc_bcrw_depname");
        if (depid != null && (!depid.equals(""))) {
            Department dep = departmentService.get(Long.parseLong(depid));
            map.put("depcode3", dep.getCode());
        }
        return yhpcCheckPlanService.dataGrid(map);
    }

    /**
     * 任务list页面
     */
    @RequestMapping(value = "myrwlist")
    @ResponseBody
    public Map<String, Object> getmyrwData(HttpServletRequest request) {
        Map<String, Object> map = getPageMap(request);
        map.put("userid", UserUtil.getCurrentShiroUser().getId());
        map.put("type", request.getParameter("yhpc_bcrw_type2"));
        return yhpcCheckPlanService.myrwdataGrid(map);
    }

    /**
     * 获取班次名
     *
     * @param id,model
     */
    @RequestMapping(value = "bclist")
    @ResponseBody
    public String getUserJson() {
        List<Map<String, Object>> bclist = new ArrayList<Map<String, Object>>();
        ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
        //判断是不是企业
        if (sessionuser.getUsertype().equals("1")) {
            bclist = yhpcCheckPlanService.findbclist(UserUtil.getCurrentShiroUser().getQyid());

        }
        return JsonMapper.getInstance().toJson(bclist);
    }

    /**
     * 添加巡检班次任务设置跳转
     *
     * @param model
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("action", "new-create");
        return "yhpc/bcrw/form";
    }

    /**
     * 添加信息
     *
     * @param request,model
     */
    @RequestMapping(value = "new-create")
    @ResponseBody
    public String create(YHPC_CheckPlanEntity bcrw, HttpServletRequest request) {
        String datasuccess = "success";
        ShiroUser user = UserUtil.getCurrentShiroUser();
        bcrw.setID1(user.getQyid());//企业id
        bcrw.setCreatetime(new Timestamp(System.currentTimeMillis()));//创建时间
        bcrw.setUserid(user.getId().longValue());//建立人id
        //添加任务时间
        String[] stimes = request.getParameterValues("start");
        String[] etimes = request.getParameterValues("end");
        String[] userids = request.getParameter("xjryids").split(",");
        String[] points = request.getParameter("jcdids").split(",");
        yhpcCheckPlanService.addPlanDetail(bcrw, stimes, etimes, userids, points);
        return datasuccess;
    }


    /**
     * 巡检人员选择页面跳转
     *
     * @param model
     */
    @RequestMapping(value = "xjrychoose")
    public String xjrychoosechoose(Model model) {
        model.addAttribute("action", "xjrychoose");
        return "yhpc/bcrw/index_xjrychoose";
    }

    /**
     * 检查点页面跳转
     *
     * @param model
     */
    @RequestMapping(value = "jcdchoose")
    public String jcdchoosechoose(Model model) {
        model.addAttribute("action", "jcdchoose");
        ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
        BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
        //判断是否为集团公司
        if (be.getIsbloc() != null && be.getIsbloc() == 1)
            model.addAttribute("flag", "0");
        else
            model.addAttribute("flag", "1");
        return "yhpc/bcrw/index_jcdchoose";
    }

    /**
     * 检查点list页面
     */
    @RequestMapping(value = "jcdlist")
    @ResponseBody
    public Map<String, Object> getJcdData(HttpServletRequest request) {
        Map<String, Object> map = getPageMap(request);
        ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
        BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
        //判断是否为集团公司
        if (be.getIsbloc() != null && be.getIsbloc() == 1)
            map.put("fid", sessionuser.getQyid());
        else
            map.put("qyid", sessionuser.getQyid());
        //判断用户部门权限
        map.putAll(getAuthorityMap());
        map.put("qyname", request.getParameter("qyname"));
        map.put("jcdname", request.getParameter("jcdname"));
        map.put("fxfj", request.getParameter("fxfj"));
        map.put("zzbm", request.getParameter("zzbm"));
        return yhpcCheckPlanService.jcddataGrid(map);
    }

    /**
     * 删除巡检班次任务
     *
     * @param user
     * @param model
     */
    @RequestMapping(value = "delete/{ids}")
    @ResponseBody
    public String delete(@PathVariable("ids") String ids) {
        String datasuccess = "删除成功";
        //可以批量删除
        String[] aids = ids.split(",");
        for (int i = 0; i < aids.length; i++) {
            yhpcCheckPlanService.deleteInfo(Long.parseLong(aids[i]));
            planDetailService.deleteByPlanId(Long.parseLong(aids[i]));
        }
        return datasuccess;
    }


    @RequestMapping(value = "new-view/{id}", method = RequestMethod.GET)
    public String newView(@PathVariable("id") Long id, Model model) {
        YHPC_CheckPlanEntity plan = yhpcCheckPlanService.findById(id);
        model.addAttribute("plan", plan);
        List<Map<String, Object>> maps = planDetailService.listViewInfo(id);
        List<Map<String, Object>> maps2 = planDetailService.listViewInfo2(id);
        List<String> users = Lists.newArrayList();
        List<String> points = Lists.newArrayList();
        List<Map<String, Object>> times = Lists.newArrayList();
        String[] weeks = new String[]{"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
        String[] months = new String[]{"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};
        for (Map<String, Object> map : maps) {
            if ("2".equals(plan.getType())) {
                map.put("starttime", weeks[(int) map.get("starttime")]);
                map.put("endtime", weeks[(int) map.get("endtime")]);
            } else if ("4".equals(plan.getType())) {
                map.put("starttime", months[(int) map.get("starttime")]);
                map.put("endtime", months[(int) map.get("endtime")]);
            }
            if (!users.contains(map.get("name"))) {
                users.add(map.get("name") + "");
            }
/*            if (!points.contains(map.get("pointname"))) {
                points.add(map.get("pointname") + "");
            }*/
            boolean has = false;
            for (Map<String, Object> time : times) {
                if (map.get("starttime").equals(time.get("starttime"))
                        && map.get("endtime").equals(time.get("endtime"))) {
                    has = true;
                }
            }
            if (!has) {
                HashMap<String, Object> tmp = Maps.newHashMap();
                tmp.put("starttime", map.get("starttime"));
                tmp.put("endtime", map.get("endtime"));
                times.add(tmp);
            }
        }

        for (Map<String, Object> map : maps2) {
            points.add(map.get("pointname") + "");
        }
        model.addAttribute("times", times);
        model.addAttribute("users", users);
        model.addAttribute("points", points);
        return "yhpc/bcrw/view-new";
    }


    /**
     * 修改页面跳转
     *
     * @param id,model
     */
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") Long id, Model model) {
        YHPC_CheckPlanEntity bcrw = yhpcCheckPlanService.findById(id);
        List<Map<String, Object>> list = planDetailService.listViewInfo(id);
        List<Map<String, Object>> list2 = planDetailService.listViewInfo2(id);

        List<String> users = Lists.newArrayList();
        List<String> points = Lists.newArrayList();
        List<Map<String, Object>> times = Lists.newArrayList();
        String idname = "";
        //巡检点传值
        String idname2 = "";
        for (Map<String, Object> map : list) {
            if (!users.contains(map.get("name"))) {
                users.add(map.get("name") + "");
                idname = idname + map.get("uid") + "||" + map.get("name") + "||"
                        + map.get("gender") + "||" + map.get("phone") + ",";

            }
            boolean has = false;
            for (Map<String, Object> time : times) {
                if (map.get("starttime").equals(time.get("starttime"))
                        && map.get("endtime").equals(time.get("endtime"))) {
                    has = true;
                }
            }
            if (!has) {
                HashMap<String, Object> tmp = Maps.newHashMap();
                tmp.put("starttime", map.get("starttime"));
                tmp.put("endtime", map.get("endtime"));
                times.add(tmp);
            }
        }

        for (Map<String, Object> map : list2) {
            points.add(map.get("pointname") + "");
            idname2 = idname2 + map.get("id") + "-" + map.get("checkpointtype")
                    + "||" + map.get("m1") + "||" + map.get("area") + "||" + map.get("bindcontent") + ",";
        }

        model.addAttribute("rwsjlist", JsonMapper.getInstance().toJson(times));
        model.addAttribute("idname", idname);
        model.addAttribute("idname2", idname2);
        model.addAttribute("bcrw", bcrw);
        model.addAttribute("action", "update");
        return "yhpc/bcrw/form";
    }

    /**
     * 修改
     *
     * @param request,model
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public String update(YHPC_CheckPlanEntity bcrw, HttpServletRequest request, Model model) {
        String datasuccess = "success";
        planDetailService.deleteByPlanId(bcrw.getID());
        String[] stimes = request.getParameterValues("start");
        String[] etimes = request.getParameterValues("end");
        String[] userids = request.getParameter("xjryids").split(",");
        String[] points = request.getParameter("jcdids").split(",");
        yhpcCheckPlanService.addPlanDetail(bcrw, stimes, etimes, userids, points);
        return datasuccess;
    }


    /**
     * 首页巡检点页面跳转
     *
     * @param model
     */
    @RequestMapping(value = "syxjdindex")
    public String syxjdindex(Model model) {
        return "yhpc/bcrw/syxjdindex";
    }

    /**
     * 首页巡检点查看页面跳转
     *
     * @param id,model
     */
    @RequestMapping(value = "syxjdview", method = RequestMethod.GET)
    public String syxjdview(Long xjdid, String type, Model model) {
        if (type.equals("1")) {
            Map<String, Object> sgfx = sxgkfxxxService.findInforById(xjdid);
            BIS_EnterpriseEntity entity = qyjbxxServiceImpl.findInfoById(Long.parseLong(sgfx.get("id1").toString()));
            sgfx.put("pmt", entity.getM33_3());

            model.addAttribute("sgfx", sgfx);
            model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
            model.addAttribute("qyname", entity.getM1());
            // 返回页面
            return "fxgk/fxdxx/view";
        } else {
            return "yhpc/yhpcd/view";
        }
    }


}
