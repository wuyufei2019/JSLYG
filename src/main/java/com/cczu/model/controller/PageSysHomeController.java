package com.cczu.model.controller;

import com.cczu.model.entity.ISSUE_SafetyProductionDynamicEntity;
import com.cczu.model.service.FxgkTjfxService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.SysHomeService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.system.entity.Barrio;
import com.cczu.sys.system.service.BarrioService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 安监端首页 sysHome:controller
 *
 * @author jason
 */
@Controller
@RequestMapping("syshome")
public class PageSysHomeController extends BaseController {

    @Autowired
    private IBisQyjbxxService bisQyjbxxService;
    @Autowired
    private SysHomeService syshomeservice;
    @Autowired
    private BarrioService barrioservice;
    @Autowired
    private FxgkTjfxService fxgkTjfxService;

    public static String[] shigu = {"物体打击", "机械伤害", "车辆伤害", "起重伤害", "高处坠落", "中毒和窒息", "触电", "淹溺", "灼烫", "火灾", "坍塌", "透水", "放炮", "冒顶片帮", "火药爆炸", "瓦斯爆炸", "锅炉爆炸", "容器爆炸", "其它爆炸", "其它伤害"};


    /**
     * 首页页面跳转
     */
    @RequestMapping(value = "map")
    public String index(Model model) {
        Barrio bro = barrioservice.findPointBycode(UserUtil.getCurrentShiroUser().getXzqy());
        if (bro != null)
            model.addAttribute("mappoint", bro.getMappoint());
        model.addAttribute("forbidScroll", "1");//禁止地图使用滚轮标志位
        return "system/sysHome";
    }

    /**
     * 安监端 获取展示数据json
     *
     * @param model
     */
    @RequestMapping(value = "sjzs")
    @ResponseBody
    String getDateJson(Model model) {

        List<Object> obs;
        Map<String, Object> map = getAuthorityMap();
        try {
            obs = syshomeservice.getDate(map);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            obs = null;
        }
        return obs.toString();
    }

    /**
     * 安监端 可视化数据json
     *
     * @param model
     */
    @RequestMapping(value = "kshsj")
    @ResponseBody
    String getDateJson2(Model model) {

        List<Map<String, Object>> obs;
        Map<String, Object> map = getAuthorityMap();
        try {
            obs = syshomeservice.getDate2(map);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            obs = null;
        }
        return JsonMapper.getInstance().toJson(obs);
    }

    /**
     * 安监端 危险作业报备统计json
     *
     * @param model
     */
    @RequestMapping(value = "wxzybbtj")
    @ResponseBody
    String wxzybbtj(Model model) {
        Map<String, Object> map = getAuthorityMap();
        List<Map<String, Object>> list = syshomeservice.wxzybbtj(map);
        // 返回页面
        return JsonMapper.getInstance().toJson(list);
    }

    /**
     * 安监端 危险作业报备走势json
     *
     * @param model
     */
    @RequestMapping(value = "wxzybbtj2")
    @ResponseBody
    String wxzybbtj2(Model model) {
        Map<String, Object> map = getAuthorityMap();
        List<Map<String, Object>> list = syshomeservice.wxzybbtj2(map);
        // 返回页面
        return JsonMapper.getInstance().toJson(list);
    }

    /**
     * 安监端 大数据到期提醒
     *
     * @param model
     */
    @RequestMapping(value = "dqtx")
    @ResponseBody
    String dqtx(Model model) {
        Map<String, Object> map = getAuthorityMap();
        List<Map<String, Object>> list = syshomeservice.dqtx(map);
        // 返回页面
        return JsonMapper.getInstance().toJson(list);
    }

    /**
     * 安监端 预警信息
     *
     * @param model
     */
    @RequestMapping(value = "alarm")
    @ResponseBody
    String alarm(Model model) {
        Map<String, Object> map = getAuthorityMap();
        List<Map<String, Object>> list = syshomeservice.alarm(map);
        // 返回页面
        return JsonMapper.getInstance().toJson(list);
    }

    /**
     * 安监端 大数据风险预警雷达图
     *
     * @param request
     */
    @RequestMapping(value = "FXDjSg2")
    @ResponseBody
    public Map<String, Object> FXDjSg2(HttpServletRequest request) {
        Map<String, Object> map = getAuthorityMap();
        Map<String, Object> mapO = new HashMap<String, Object>();
        List<Integer> list = new ArrayList<Integer>();
        List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> fl = fxgkTjfxService.FXDjSgSer(map);
        for (int j = 0; j < shigu.length; j++) {
            int num = 0;
            for (int i = 0; i < fl.size(); i++) {
                Map<String, Object> map2 = (Map<String, Object>) fl.get(i);
                String shigu1 = shigu[j];
                if ((map2.get("shigu").toString()).indexOf(shigu1) != -1) {
                    num = num + Integer.parseInt(map2.get("num").toString());
                }
            }
            if (num != 0) {
                list.add(num);
                Map<String, Object> map3 = new HashMap<>();
                map3.put("text", shigu[j]);
                list2.add(map3);
            }
        }
        mapO.put("shigu", list2);
        mapO.put("num", list);
        return mapO;
    }

    /**
     * 安监端 实时隐患排查数json
     *
     * @param model
     */
    @RequestMapping(value = "yhpcs")
    @ResponseBody
    String getweekyhpc(Model model) {
        return syshomeservice.yhpcs();
    }

    /**
     * 安监端 实时隐患整改数json
     *
     * @param model
     */
    @RequestMapping(value = "yhzgs")
    @ResponseBody
    String getweekyhzg(Model model) {
        return syshomeservice.yhzgs();
    }

    /**
     * 企业端首页获取展示数据json
     *
     * @param model
     */
    @RequestMapping(value = "qysjzs")
    @ResponseBody
    String getQyDateJson(Model model) {
        List<Object> obs;
        try {
            ShiroUser user = UserUtil.getCurrentShiroUser();
            Map<String, Object> map = new HashMap<>();
            map.put("qyid", user.getQyid());
            map.put("uid", user.getId());
            obs = syshomeservice.getQyDate(map);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            obs = null;
        }
        return obs.toString();
    }

    /**
     * 最新文件 展示10个
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "issue")
    @ResponseBody
    public List<Map<String, Object>> getIssueInfo(Model model) {
        Map<String, Object> map = new HashMap<String, Object>();
        ShiroUser user = UserUtil.getCurrentShiroUser();
        map.put("pageSize", 10);
        map.put("pageNo", 1);
        if ("0".equals(user.getUsertype())) {
            map.put("xzqy", user.getXzqy());
        } else if ("1".equals(user.getUsertype())) {
            map.put("qyid", user.getQyid());
        }
        List<Map<String, Object>> list = syshomeservice.getIssueInfo(map);
        return list;
    }

    /**
     * 最新动态 展示10个
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "zxdt")
    @ResponseBody
    public List<ISSUE_SafetyProductionDynamicEntity> getZxdtInfo(Model model) {
        Map<String, Object> map = getAuthorityMap();
        map.put("pageSize", 10);
        map.put("pageNo", 1);
        List<ISSUE_SafetyProductionDynamicEntity> list = syshomeservice.getZxdtInfo(map);
        return list;
    }

    /**
     * 报警信息 展示10个
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "bjxx")
    @ResponseBody
    public List<Map<String, Object>> getBjxxInfo(Model model) {
        Map<String, Object> map = getAuthorityMap();
        List<Map<String, Object>> list = syshomeservice.getBjxxInfo(map);
        return list;
    }

}
