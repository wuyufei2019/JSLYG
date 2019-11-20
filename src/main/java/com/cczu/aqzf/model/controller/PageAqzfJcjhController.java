package com.cczu.aqzf.model.controller;


import com.cczu.aqzf.model.entity.*;
import com.cczu.aqzf.model.service.*;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import org.apache.commons.lang3.StringUtils;
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
import java.util.*;

/**
 * 检查计划
 *
 * @author zpc
 * @date 2017/07/27
 */
@Controller
@RequestMapping("aqzf/jcjh")
public class PageAqzfJcjhController extends BaseController {

    @Autowired
    private AqzfJcjlService aqzfJcjlService;
    @Autowired
    private IAqzfJcjhService AqzfJcjhService;
    @Autowired
    private com.cczu.aqzf.model.dao.AqzfJcjhQyDao AqzfJcjhQyDao;
    @Autowired
    private AqzfJcfaService aqzfJcfaService;
    @Autowired
    private IBisQyjbxxService bisQyjbxxService;
    @Autowired
    private AqzfRyfzService aqzfRyfzService;
    @Autowired
    private AqzfZfryService aqzfZfryService;
    @Autowired
    private IXzcfCommonLaspService punishcommonlaspservice;

    /**
     * 默认页面
     */
    @RequestMapping(value = "index")
    public String index(Model model) {
        return "aqzf/jcjh/index";
    }

    /**
     * list页面
     */
    @RequestMapping(value = "list")
    @ResponseBody
    public Map<String, Object> getData(HttpServletRequest request) {
        Map<String, Object> map = getPageMap(request);
        ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
        map.put("xzqy", sessionuser.getXzqy());
        if (sessionuser.getUserroleflg() != null && sessionuser.getUserroleflg() != 0)
            map.put("jglx", sessionuser.getUserroleflg());
        map.put("year", request.getParameter("aqzf_jcjh_year"));
        map.put("month", request.getParameter("aqzf_jcjh_month"));
        map.put("qyname", request.getParameter("aqzf_jcjh_qyname"));
        map.put("qyfz", request.getParameter("aqzf_jcjh_qyfz"));
        map.put("M4", request.getParameter("aqzf_jcjh_M4"));
        map.put("M5", request.getParameter("aqzf_jcjh_M5"));
        return AqzfJcjhService.dataGrid(map);
    }

    /**
     * 删除检查计划记录
     *
     * @param ids
     * @param
     * @throws
     */
    @RequestMapping(value = "delete/{ids}")
    @ResponseBody
    public String delete(@PathVariable("ids") String ids) {
        String datasuccess = "删除成功";
        String[] arrids = ids.split(",");
        for (int i = 0; i < arrids.length; i++) {
            AQZF_Plan_EnterpriseEntity ape = AqzfJcjhQyDao.findInfoById(Long.parseLong(arrids[i]));
            AQZF_SafetyCheckSchemeEntity jcfa = aqzfJcfaService.findJcfaByjhqyid(ape.getID1() + "", ape.getID2() + "");
            if (jcfa != null) {
                XZCF_YbcfLaspEntity lasp = aqzfJcjlService.findLaspByfaid(jcfa.getID() + "");
                if (lasp != null) {
                    AQZF_SafetyCheckRecordEntity jcjl = aqzfJcjlService.findJcjlByfaid(jcfa.getID());
                    if (jcjl != null) {
                        //删除立案审批并修改检查记录状态
                        punishcommonlaspservice.deleteInfo(lasp.getID());
                    }
                }
            }
            aqzfJcfaService.deleteByape(ape);
            AqzfJcjhQyDao.deleteById(arrids[i]);
        }
        return datasuccess;
    }

    /**
     * 添加检查计划页面跳转
     *
     * @param model
     */
    @RequiresPermissions("aqzf:jcjh:add")
    @RequestMapping(value = "create2", method = RequestMethod.GET)
    public String create2(Model model) {
        model.addAttribute("action", "create2");
        return "aqzf/jcjh/form2";
    }

    /**
     * 添加检查计划信息
     *
     * @param request,model
     */
    @RequiresPermissions("aqzf:jcjh:add")
    @RequestMapping(value = "create2")
    @ResponseBody
    public String create2(AQZF_SafetyCheckPlanEntity jcjh, String qyids, String ryids, HttpServletRequest request) {
        String datasuccess = "success";
        if (!qyids.equals("") && !ryids.equals("")) {
            //添加检查计划
            jcjh.setID1(UserUtil.getCurrentShiroUser().getId());
            long jhid = AqzfJcjhService.addjcjh(jcjh);

            //获取执法人员名字
            Map<String, Object> map1 = new HashMap<>();
            map1.put("ryids", ryids.substring(0, ryids.length() - 1));
            List<Map<String, Object>> rynamelist = aqzfZfryService.getZfryNames(map1);//执法人员
            String zfrynames = "";
            for (Map<String, Object> ryname : rynamelist) {
                zfrynames += ryname.get("m1").toString() + ",";
            }
            zfrynames = zfrynames.substring(0, zfrynames.length() - 1);

            String[] arrids = qyids.split(",");
            for (int i = 0; i < arrids.length; i++) {
                AQZF_Plan_EnterpriseEntity pe = new AQZF_Plan_EnterpriseEntity();
                pe.setID1(jhid);
                pe.setID2(Long.parseLong(arrids[i]));
                pe.setM1("0");
                pe.setM2(zfrynames);
                AqzfJcjhQyDao.addInfo(pe);
            }
        } else {
            datasuccess = "error";
        }
        return datasuccess;
    }

    /**
     * 添加双随机页面跳转
     *
     * @param model
     */
    @RequiresPermissions("aqzf:jcjh:add")
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("action", "create");
        Map<String, Object> map = new HashMap<>();
        ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
        map.put("xzqy", sessionuser.getXzqy());
        if (sessionuser.getUserroleflg() != null && sessionuser.getUserroleflg() != 0)
            map.put("jglx", sessionuser.getUserroleflg());
        List<Map<String, Object>> fzqk = aqzfRyfzService.getFzlist(map);
        model.addAttribute("fzqk", JsonMapper.getInstance().toJson(fzqk));
        return "aqzf/jcjh/form";
    }

    private List<Integer> getList(int n) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        return list;
    }

    /**
     * 添加双随机信息
     *
     * @param request,model
     */
    @RequiresPermissions("aqzf:jcjh:add")
    @RequestMapping(value = "create")
    @ResponseBody
    public String create(AQZF_SafetyCheckPlanEntity jcjh, String qyids, String ryids, HttpServletRequest request) {
        String datasuccess = "success";
        if (!qyids.equals("") && !ryids.equals("")) {
            jcjh.setID1(UserUtil.getCurrentShiroUser().getId());
            long a = AqzfJcjhService.addjcjh(jcjh);
            Map<String, Object> map1 = new HashMap<>();
            map1.put("ryids", ryids.substring(0, ryids.length() - 1));
            List<Map<String, Object>> zfry = aqzfZfryService.getSjppzfry(map1);//处室人员
            int len = zfry.size();
            List<Integer> tmpList = getList(len);
            List<String> qylist = Arrays.asList(qyids.split(","));
            Random random = new Random();

            //每家企业随机两个人员检查
            for (String qy : qylist) {
                List<Map<String, Object>> rylist = new ArrayList<>();
                String ry = "";
                //随机选择一个政府人员
                int rom ;
                if (tmpList.size() == 0) {
                    tmpList = getList(len);
                    rom=random.nextInt(tmpList.size()-1);
                    ry += zfry.get(tmpList.get(rom)).get("m1")+",";
                    tmpList.remove(tmpList.indexOf(rom));
                } else if (tmpList.size() == 1){
                    ry += zfry.get(tmpList.get(0)).get("m1")+",";
                    tmpList = getList(len);
                }else{
                    rom = random.nextInt(tmpList.size()-1);
                    ry += zfry.get(tmpList.get(rom)).get("m1")+",";
                    tmpList.remove(tmpList.get(rom));
                }

                if (tmpList.size() == 0) {
                    tmpList = getList(len);
                    rom=random.nextInt(tmpList.size()-1);
                    ry += zfry.get(tmpList.get(rom)).get("m1")+",";
                    tmpList.remove(tmpList.indexOf(rom));
                } else if (tmpList.size() == 1){
                    ry += zfry.get(tmpList.get(0)).get("m1")+",";
                    tmpList = getList(len);
                }else{
                    rom = random.nextInt(tmpList.size()-1);
                    ry += zfry.get(tmpList.get(rom)).get("m1")+",";
                    tmpList.remove(tmpList.get(rom));
                }

                addJcjhQy(a, Long.valueOf(qy), ry);
            }


        } else {
            datasuccess = "error";
        }
        return datasuccess;
    }

    /**
     * 随机匹配企业与执法人员  2个执法人员检查两家企业
     */
    public void addJcjhQy(long jhid, Long qyid, String zfry) {
        AQZF_Plan_EnterpriseEntity pe = new AQZF_Plan_EnterpriseEntity();
        pe.setID1(jhid);
        pe.setID2(qyid);
        pe.setM1("0");
        pe.setM2(zfry.substring(0,zfry.length()-1));
        AqzfJcjhQyDao.addInfo(pe);
    }

    /**
     * 修改页面跳转
     *
     * @param id,model
     */
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") Long id, Model model) {
        AQZF_Plan_EnterpriseEntity pe = AqzfJcjhQyDao.findInfoById(id);
        Map<String, Object> jcjh = AqzfJcjhService.findById(id).get(0);
        model.addAttribute("pe", pe);
        model.addAttribute("jcjh", jcjh);
        model.addAttribute("action", "update");
        return "aqzf/jcjh/updateform";
    }

    /**
     * 修改
     *
     * @param request,model
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public String update(AQZF_Plan_EnterpriseEntity pe, HttpServletRequest request) {
        String datasuccess = "success";
        AqzfJcjhQyDao.addInfo(pe);
        return datasuccess;
    }

    /**
     * 查看页面跳转
     *
     * @param id,model
     */
    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") Long id, Model model) {
        List<Map<String, Object>> jcjh = AqzfJcjhService.findById(id);
        model.addAttribute("jcjh", jcjh.get(0));
        return "aqzf/jcjh/view";
    }

    /**
     * 显示所有列
     *
     * @param id,model
     */
    @RequiresPermissions("aqzf:jcjh:export")
    @RequestMapping(value = "colindex", method = RequestMethod.GET)
    public String colindex(Model model) {
        model.addAttribute("url", "aqzf/jcjh/export");
        return "common/formexcel";
    }

    /**
     * 导出Excel
     *
     * @param request
     */
    @RequiresPermissions("aqzf:jcjh:export")
    @RequestMapping(value = "export")
    @ResponseBody
    public void getExcel(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
        map.put("xzqy", sessionuser.getXzqy());
        if (sessionuser.getUserroleflg() != null && sessionuser.getUserroleflg() != 0)
            map.put("jglx", sessionuser.getUserroleflg());
        map.put("colval", request.getParameter("colval"));
        map.put("coltext", request.getParameter("coltext"));
        map.put("year", request.getParameter("aqzf_jcjh_year"));
        map.put("month", request.getParameter("aqzf_jcjh_month"));
        map.put("qyname", request.getParameter("aqzf_jcjh_qyname"));
        map.put("qyfz", request.getParameter("aqzf_jcjh_qyfz"));
        map.put("M4", request.getParameter("aqzf_jcjh_M4"));
        map.put("M5", request.getParameter("aqzf_jcjh_M5"));
        AqzfJcjhService.exportExcel(response, map);
    }

    /**
     * 企业选择页面跳转
     *
     * @param model
     */
    @RequestMapping(value = "choose")
    public String choose(Model model) {
        model.addAttribute("action", "choose");
        return "aqzf/jcjh/index_qychoose";
    }

    /**
     * 选择企业list页面
     */
    @RequestMapping(value = "qylist")
    @ResponseBody
    public Map<String, Object> getQyList(String sd, String hylx, HttpServletRequest request) {
        ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("xzqy", sessionuser.getXzqy());
        if (sessionuser.getUserroleflg() != null && sessionuser.getUserroleflg() != 0) {
            map.put("jglx", sessionuser.getUserroleflg());
        }
        map.put("qyname", request.getParameter("qynm"));
        //属地行业类型
        if (!sd.equals("flag")) {
            map.put("xzqy", sd);
            map.put("xj", "2");
        }
        if (!hylx.equals("flag")) {
            map.put("jglx", hylx);
        }
        return AqzfJcjhService.findAllQyList(map);
    }

    /**
     * 判断该计划是否有企业添加检查发案
     *
     * @param model id 关联表id
     */
    @RequestMapping(value = "pd/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String pd(@PathVariable("id") Long id, Model model) {
        AQZF_Plan_EnterpriseEntity pe = AqzfJcjhQyDao.findInfoById(id);
        AQZF_SafetyCheckPlanEntity jcjh = AqzfJcjhService.findById2(pe.getID1());
        List<AQZF_Plan_EnterpriseEntity> list = AqzfJcjhQyDao.selectList(jcjh.getID());
        String a = "success";
        for (AQZF_Plan_EnterpriseEntity ape : list) {
            if (ape.getM1().equals("1")) {
                a = "error";
                break;
            }
        }
        return a;
    }

    /**
     * 随机生成页面
     */
    @RequestMapping(value = "sjsc")
    @ResponseBody
    public String getsjsc(HttpServletRequest request) {
        ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("xzqy", sessionuser.getXzqy());
        map.put("sjxzqy", sessionuser.getXzqy());
        map.put("xj", "0");
        if (sessionuser.getUserroleflg() != null && sessionuser.getUserroleflg() != 0) {
            map.put("jglx", sessionuser.getUserroleflg());
            map.put("sjjglx", sessionuser.getUserroleflg());
        }
        String sd = request.getParameter("sd");
        String hylx = request.getParameter("hylx");
        String sl = request.getParameter("sl");
        String gltj = request.getParameter("gltj");
        //属地行业类型
        if (!sd.equals("flag")) {
            map.put("sjxzqy", sd);
            map.put("xj", "2");
        }
        if (!hylx.equals("flag")) {
            map.put("sjjglx", hylx);
        }
        map.put("sl", sl);
        map.put("gltj", gltj);
        String idname = "";
        List<Map<String, Object>> list = AqzfJcjhService.getsjsc(map);
        if (list.size() > 0) {
            for (Map<String, Object> map2 : list) {
                idname += map2.get("qyid").toString() + "||" + map2.get("qyname").toString() + "||" + map2.get("m1") + ",";
            }
        }
        return idname;
    }

    /**
     * 执法人员选择页面跳转
     *
     * @param model
     */
    @RequestMapping(value = "rychoose")
    public String rychoose(Model model) {
        model.addAttribute("action", "rychoose");
        return "aqzf/jcjh/index_rychoose";
    }

    /**
     * 选择执法人员list页面
     */
    @RequestMapping(value = "rylist")
    @ResponseBody
    public Map<String, Object> getRyList(HttpServletRequest request) {
        Map<String, Object> map = getAuthorityMap();
        map.put("xm", request.getParameter("rynm"));
        Map<String, Object> mapr = new HashMap<String, Object>();
        mapr.put("rows", aqzfZfryService.getZfryIdlist(map));
        return mapr;
    }


    /**
     * 安全执法检查计划根据所选企业分组获取企业名称和id
     * idjson  {"id":11,"text":"企业名称"}
     * return String
     */
    @RequestMapping(value = "qyidjson")
    @ResponseBody
    public String codesjIdjson(String xzqy, String jglx) {
        Map<String, Object> map = new HashMap<String, Object>();
        ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
        if (StringUtils.isEmpty(xzqy)) {
            map.put("xzqy", sessionuser.getXzqy());
        } else {
            String xzqy2 = "";
            String[] arrids = xzqy.split(",");
            for (int i = 0; i < arrids.length; i++) {
                if (i == 0) {
                    xzqy2 = "'" + arrids[i] + "'";
                } else {
                    xzqy2 += ",'" + arrids[i] + "'";
                }
            }
            map.put("xzqy", xzqy2);
            map.put("xj", "2");
        }
        if (StringUtils.isEmpty(jglx)) {
            if (sessionuser.getUserroleflg() != null && sessionuser.getUserroleflg() != 0) {
                map.put("jglx", sessionuser.getUserroleflg());
            }
        } else {
            map.put("jglx", jglx);
        }
        return bisQyjbxxService.getQyIdjson(map);
    }

    /**
     * 单个人员随机
     */
    @RequestMapping(value = "rydgsj")
    @ResponseBody
    public Map<String, Object> rydgsj(String ryid, String ryids) {
        Map<String, Object> map = new HashMap<String, Object>();
        ryids = ryids.substring(0, ryids.length() - 1);
        map.put("ryid", ryid);
        map.put("ryids", ryids);
        List<Map<String, Object>> list = aqzfZfryService.rydgsj(map);
        Map<String, Object> result = new HashMap<String, Object>();
        if (list.size() > 0) {
            result = list.get(0);
            result.put("flag", "1");
        } else {
            result.put("flag", "0");
        }
        return result;
    }

    /**
     * 单个企业随机
     */
    @RequestMapping(value = "qydgsj")
    @ResponseBody
    public Map<String, Object> qydgsj(String qyid, String qyids, String gltj) {
        Map<String, Object> map = new HashMap<String, Object>();
        qyids = qyids.substring(0, qyids.length() - 1);
        map.put("qyid", qyid);
        map.put("qyids", qyids);
        map.put("gltj", gltj);
        map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());
        List<Map<String, Object>> list = AqzfJcjhService.qydgsj(map);
        Map<String, Object> result = new HashMap<String, Object>();
        if (list.size() > 0) {
            result = list.get(0);
            result.put("flag", "1");
        } else {
            result.put("flag", "0");
        }
        return result;
    }
}
