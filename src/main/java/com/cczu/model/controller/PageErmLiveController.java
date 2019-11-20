package com.cczu.model.controller;

import com.cczu.model.entity.ERM_EmergencyLiveEntity;
import com.cczu.model.service.ErmLiveService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.utils.Global;
import com.cczu.sys.comm.utils.QRCode;
import com.cczu.sys.comm.utils.ServletUtils;
import com.cczu.sys.system.entity.Barrio;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.service.BarrioService;
import com.cczu.sys.system.service.ShiroRealm;
import com.cczu.sys.system.utils.UserUtil;
import com.google.common.collect.Lists;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 训练计划controller
 *
 * @author jason
 * @date 2017年7月1日
 */
@Controller
@RequestMapping("yjjy/live/")
public class PageErmLiveController extends BaseController {

    @Autowired
    private ErmLiveService liveService;
    @Autowired
    private BarrioService barrioService;


    @RequestMapping(value = "index")
    public String live(Model model) {
        List<ERM_EmergencyLiveEntity> lives = Lists.newArrayList();
        ShiroRealm.ShiroUser shiroUser = UserUtil.getCurrentShiroUser();
        //获取公共账号
        lives.addAll(liveService.listLiveOnPub(shiroUser.getXzqy()));
        //获取企业
        lives.addAll(liveService.listLiveOnQy(getAuthorityMap()));
        if (User.USERTYPE.AJ.getUsertype().equals(shiroUser.getUsertype())) {
            lives.addAll(liveService.listLiveOnZf(shiroUser.getXzqy()));
        }
        model.addAttribute("lives", lives);
        model.addAttribute("liveUrl", Global.getConfig("live_server_url"));
        model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
        return "erm/live/index";
    }


    /**
     * 列表显示页面
     *
     * @param model
     */
    @RequestMapping(value = "setting/index")
    public String index(Model model) {
        return "erm/live/setting/index";
    }

    /**
     * 列表显示页面
     *
     * @param model
     */
    @RequestMapping(value = "setting/index2")
    public String index2(@RequestParam(value = "type", required = false) String type, Model model) {
        model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
        model.addAttribute("type", type);
        return "erm/live/setting/index2";
    }


    /**
     * list页面
     *
     * @param request
     */
    @RequiresPermissions("yjjy:live:view")
    @RequestMapping(value = "setting/list")
    @ResponseBody
    public Map<String, Object> getData(HttpServletRequest request) {
        Page<Map> page = getPage(request);
        Map<String, Object> authorityMap = getAuthorityMap();
        Map<String, Object> map = ServletUtils.getParametersStartingWith(request, "view_");
        authorityMap.putAll(map);

        try {
            if ("0".equals(authorityMap.get("type")) || "2".equals(authorityMap.get("type"))) {
                liveService.pageZf(page, authorityMap);
            } else if ("1".equals(authorityMap.get("type"))) {
                liveService.pageQy(page, authorityMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getEasyUIData(page);

    }

    /**
     * 添加页面跳转
     *
     * @param model
     */
    @RequiresPermissions("yjjy:live:add")
    @RequestMapping(value = "setting/create", method = RequestMethod.GET)
    public String create(Model model) {
        ShiroRealm.ShiroUser user = UserUtil.getCurrentShiroUser();
        model.addAttribute("action", "create");
        model.addAttribute("usertype", user.getUsertype());
        return "erm/live/setting/form";
    }

    /**
     * 添加避难场所信息
     *
     * @param
     */
    @RequiresPermissions("yjjy:live:add")
    @RequestMapping(value = "setting/create")
    @ResponseBody
    public String create(ERM_EmergencyLiveEntity ee, HttpServletRequest request) {
        String datasuccess = "success";
        ee.setLive(0);
        ee.setUserid(UserUtil.getCurrentShiroUser().getId());
        liveService.save(ee);
        return datasuccess;
    }

    /**
     * 修改页面跳转
     *
     * @param id ,model
     */
    @RequiresPermissions("yjjy:live:update")
    @RequestMapping(value = "setting/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") Long id, Model model) {
        // 查询选择的避难场所信息
        ERM_EmergencyLiveEntity ee = liveService.get(id);
        model.addAttribute("entity", ee);
        model.addAttribute("action", "update");
        model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
        return "erm/live/setting/form";
    }

    /**
     * 修改避难场所信息
     *
     * @param request ,model
     */
    @RequiresPermissions("yjjy:live:update")
    @RequestMapping(value = "setting/update")
    @ResponseBody
    public String update(ERM_EmergencyLiveEntity ee, HttpServletRequest request) {
        String datasuccess = "success";
        liveService.update(ee);
        // 返回结果
        return datasuccess;
    }

    /**
     * 删除避难场所信息
     *
     * @throws ParseException
     */
    @RequiresPermissions("yjjy:live:delete")
    @RequestMapping(value = "setting/delete/{ids}")
    @ResponseBody
    public String delete(@PathVariable("ids") String ids) {
        String datasuccess = "删除成功";
        // 可以批量删除
        String[] arrids = ids.split(",");
        for (int i = 0; i < arrids.length; i++) {
            liveService.delete(Long.parseLong(arrids[i]));
        }
        return datasuccess;
    }


    /**
     * 生成二维码图片
     */
    @RequestMapping(value = "/erm")
    @ResponseBody
    public String erweima(@RequestParam String uuid, HttpServletRequest request) {
        String serverUrl = Global.getConfig("live_server_url") + uuid;

        // 取得输出流
        String dowmloadPath = request.getSession().getServletContext().getRealPath("/") + "/download";
        String url = "/download/";
        try {
            url = url + QRCode.encode(serverUrl, null, dowmloadPath, true, "");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return url;
    }


    /**
     * list页面
     *
     * @param request
     */
    @RequestMapping(value = "on_publish")
    @ResponseBody
    public void onPublish(HttpServletRequest request) {
        String name = request.getParameter("name");
        liveService.updateLive(1, name);
    }

    /**
     * list页面
     *
     * @param request
     */
    @RequestMapping(value = "on_publish_done")
    @ResponseBody
    public void onPublishDown(HttpServletRequest request) {
        String name = request.getParameter("name");
        liveService.updateLive(0, name);
    }


    /**
     * 修改避难场所信息
     *
     * @param
     */
    @RequestMapping(value = "setting/init")
    @ResponseBody
    public void init() {
        List<Barrio> all = barrioService.getAll();
        Integer uid = UserUtil.getCurrentShiroUser().getId();
        for (Barrio barrio : all) {
            ERM_EmergencyLiveEntity entity = new ERM_EmergencyLiveEntity();
            entity.setLive(0);
            entity.setUserid(uid);
            entity.setXzqy(barrio.getCode());
            entity.setUuid(UUID.randomUUID().toString());
            entity.setType(0);
            for (int i = 1; i < 4; i++) {
                entity.setTitle(barrio.getM1() + "@直播间" + i);
                liveService.save(entity);
                entity.setID(null);
            }

        }

    }
}
