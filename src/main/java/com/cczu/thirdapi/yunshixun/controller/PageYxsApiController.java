package com.cczu.thirdapi.yunshixun.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ServletUtils;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.thirdapi.yunshixun.entity.ThirdYxsEquipment;
import com.cczu.thirdapi.yunshixun.entity.dto.ConfParticularInfo;
import com.cczu.thirdapi.yunshixun.entity.YsxUser;
import com.cczu.thirdapi.yunshixun.service.YsxSbszService;
import com.cczu.thirdapi.yunshixun.service.YsxUserService;
import com.cczu.thirdapi.yunshixun.util.YsxHttpClient;
import com.cczu.thirdapi.yunshixun.util.YsxMethodEnum;
import com.google.common.collect.Maps;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 在线监控预警---烟花爆竹
 *
 * @author jason
 * @date 2017年9月6日
 */
@Controller
@RequestMapping("zxjkyj/ysx")
public class PageYxsApiController extends BaseController {


    private static Logger logger = LoggerFactory.getLogger(PageYxsApiController.class);

    @Autowired
    private YsxSbszService ysxSbszService;

    @Autowired
    private YsxUserService ysxUserService;

    /**
     * 默认页面
     */
    @RequestMapping(value = "index")
    public String index(Model model) {
        model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
        return "zxjkyj/ysx/index";
    }

    @RequestMapping(value = "list")
    @ResponseBody
    public Map<String, Object> getData(@Value("${ysx-userId}") String userId, HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> map = ServletUtils.getParametersStartingWith(request, "filter_");
        int lPageNo = Integer.valueOf(request.getParameter("page"));
        int lPageItemsNo = Integer.valueOf(request.getParameter("rows"));
        map.put("lPageNo", lPageNo);
        map.put("lPageItemsNo", lPageItemsNo);
        map.put("strUserId", getUserIdIMPU(userId));
        long queryType = 4;//查询方式
        if (map.containsKey("strSubject")) {
            queryType = 2;
        } else if (map.containsKey("strBeginTime") || map.containsKey("strEndTime")) {
            queryType = 1;
        }
        if (!map.containsKey("lShowType")) {
            map.put("lShowType", 0);
        }
        map.put("QueryType", queryType);
        map.put("enQryType", "self");
        String result = null;
        try {
            result = YsxHttpClient.doPost(JSONObject.toJSONString(map), YsxMethodEnum.QueryConferenceList.name());
        } catch (Exception e) {
            log.info("接口请求错误");
            e.printStackTrace();
        }
        JSONObject jsonObject = JSON.parseObject(result);
        try {
            if (jsonObject.getInteger("iResult") == 0) {
                resultMap.put("total", jsonObject.getIntValue("lTotalRecords"));
                resultMap.put("rows", jsonObject.getJSONObject("sqSummaryList").getJSONArray("item"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("接口请求错误");
        }
        return resultMap;
    }

    /**
     * 新增
     *
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    @RequiresPermissions("api:yxs:add")
    public String create(Model model) {
        model.addAttribute("action", "create");
        return "zxjkyj/ysx/form";
    }

    /**
     * 修改页面跳转
     *
     * @param id,model
     */
    @RequestMapping(value = "update/{strConfID}", method = RequestMethod.GET)
    @RequiresPermissions("api:yxs:update")
    public String update(@Value("${ysx-userId}") String userId,
                         @PathVariable("strConfID") String strConfID, Model model) {
        JSONObject conferInfo = getConferInfo(userId, strConfID);
        model.addAttribute("entity", conferInfo);
        Date start = DateUtils.strToDateLong(conferInfo.getString("strBeginTime"));
        Date end = DateUtils.strToDateLong(conferInfo.getString("strEndTime"));
        model.addAttribute("action", "update");
        model.addAttribute("timeLen", (double) (end.getTime() - start.getTime()) / (1000 * 60 * 60));
        return "zxjkyj/ysx/form";
    }


    @RequestMapping(value = "create")
    @RequiresPermissions("api:yxs:add")
    @ResponseBody
    public String create(ConfParticularInfo info, @Value("${ysx-userId}") String userId,
                         @Value("${ysx-userId-soft}") String softUserId,
                         @RequestParam("timeLen") Float timeLen, HttpServletRequest request) {
        info.setStrUserId(getUserIdIMPU(userId));
        info.setStrConvener(userId);
        Date startTime = DateUtils.getTimestampFromStr(info.getStrBeginTime());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startTime);
        calendar.add(Calendar.MINUTE, (int) (timeLen * 60));
        info.setStrEndTime(DateUtils.formatDate(calendar.getTime(), "yyyy-MM-dd HH:mm:ss"));
        //String result = YsxHttpClient.doPost(param, YsxMethodEnum.SubscribeConference.name());
        if ("0".equals(UserUtil.getCurrentShiroUser().getUsertype())) {
            //添加硬终端
            String username = UserUtil.getCurrentShiroUser().getLoginName();
            YsxUser ajUser = new YsxUser(getUserIdIMPU(userId), 1, username, userId);
            ajUser.setDefaultMute(false);//默认闭音
            info.getSqMemberList().add(ajUser);
            //添加软终端
            YsxUser softUser = new YsxUser(getSoftUserId(softUserId), 2, username, softUserId);
            softUser.setIsSoftTerminal(true);
            info.getSqMemberList().add(softUser);
            String[] memberIds = request.getParameterValues("memberIds");
            List<ThirdYxsEquipment> all = ysxSbszService.getAll();
            //添加参会用户
            if (memberIds == null || memberIds.length == 0) {
                for (ThirdYxsEquipment map : all) {
                    info.getSqMemberList().add(getAddYxsUser(map));
                }
            } else {
                for (String member : memberIds) {
                    for (ThirdYxsEquipment map : all) {
                        if (member.equals(map.getID() + "")) {
                            info.getSqMemberList().add(getAddYxsUser(map));
                            break;
                        }
                    }
                }
            }
        }
        logger.info(JSON.toJSONString(info, SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteNullStringAsEmpty));
        String result = null;
        try {
            result = YsxHttpClient.doPost(JSON.toJSONString(info, SerializerFeature.WriteNullListAsEmpty,
                    SerializerFeature.WriteNullStringAsEmpty),
                    YsxMethodEnum.CreateConference.name());
        } catch (Exception e) {
            e.printStackTrace();
            return "网络不稳定！创建失败";
        }
        JSONObject jsonResult = JSON.parseObject(result);
        if (jsonResult.getInteger("iResult") == 0) {
            JSONObject confer = (JSONObject) jsonResult.get("strConfID");
            ysxUserService.saveAll(info.getSqMemberList(),confer.getString("value"));

            return "success:" + jsonResult.getJSONObject("strConfID").getString("value");
        } else {
            return "error";
        }
    }

    @RequestMapping(value = "update")
    @RequiresPermissions("api:yxs:update")
    @ResponseBody
    public String update(ConfParticularInfo info, @Value("${ysx-userId}") String userId,
                         @RequestParam("timeLen") Float timeLen) {
        info.setStrUserId(getUserIdIMPU(userId));
        info.setStrConvener(userId);
        Date startTime = DateUtils.getTimestampFromStr(info.getStrBeginTime());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startTime);
        calendar.add(Calendar.MINUTE, (int) (timeLen * 60));
        info.setStrEndTime(DateUtils.formatDate(calendar.getTime(), "yyyy-MM-dd HH:mm:ss"));
        if ("0".equals(UserUtil.getCurrentShiroUser().getUsertype())) {
            YsxUser ysxUser = new YsxUser();
            ysxUser.setRole(1);
            ysxUser.setState("6");
            ysxUser.setStrName(userId);
            ysxUser.setCallID(userId);
            ysxUser.setStrUserID(getUserIdIMPU(userId));
            info.getSqMemberList().add(ysxUser);
        }
        String result = YsxHttpClient.doPost(JSON.toJSONString(info, SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteNullStringAsEmpty),
                YsxMethodEnum.CreateConference.name());
        if (JSON.parseObject(result).getInteger("iResult") == 0) {
            return "success";
        } else {
            return "error";
        }
    }


    /**
     * 查看页面跳转
     *
     * @param id,model
     */
    @RequiresPermissions("api:yxs:view")
    @RequestMapping(value = "view/{strConfID}", method = RequestMethod.GET)
    public String view(@Value("${ysx-userId}") String userId,
                       @PathVariable("strConfID") String strConfID, Model model) {
        model.addAttribute("action", "view");
        model.addAttribute("entity", getConferInfo(userId, strConfID));
        return "zxjkyj/ysx/view";
    }


    /**
     * 视频控制
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "control/{strConfID}", method = RequestMethod.GET)
    @RequiresPermissions("api:yxs:control")
    public String control(@PathVariable("strConfID") String strConfID,
                          @Value("${ysx-userId}") String userId, Model model) {
        JSONObject conferInfo = getConferInfo(userId, strConfID);
        model.addAttribute("entity", conferInfo);
        model.addAttribute("strConfID", strConfID);
        List<YsxUser> ysxUsers = ysxUserService.listSoftUserByConferId(strConfID);
        model.addAttribute("softUsers",ysxUsers);
        return "zxjkyj/ysx/control";
    }

    /**
     * 取消会议
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "cancelConf/{strConfID}", method = RequestMethod.POST)
    @RequiresPermissions("api:yxs:control")
    @ResponseBody
    public String cancelConf(@PathVariable("strConfID") String strConfID,
                             @Value("${ysx-userId}") String userId) {
        String returnresult = "success";
        Map map = getRequestMap(userId, strConfID);
        String result = "";
        try {
            result = YsxHttpClient.doPost(JSON.toJSONString(map), YsxMethodEnum.CancelConference.name());
        } catch (Exception e) {
            returnresult = "网络错误";
            e.printStackTrace();
        }
        JSONObject jsonObject = JSON.parseObject(result);
        if (jsonObject.getInteger("iResult") != 0) {
            returnresult = "请求结果失败";
        }
        return returnresult;
    }

    /**
     * 结束会议
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "removeConf/{strConfID}", method = RequestMethod.POST)
    @RequiresPermissions("api:yxs:control")
    @ResponseBody
    public String removeConf(@PathVariable("strConfID") String strConfID,
                             @Value("${ysx-userId}") String userId) {
        String returnresult = "success";
        HashMap<Object, Object> map = Maps.newHashMap();
        HashMap<Object, Object> confMap = Maps.newHashMap();
        map.put("userId", getUserIdIMPU(userId));
        confMap.put("conferenceID", strConfID);
        confMap.put("subConferenceID", 0);
        map.put("conferenceKey", confMap);
        String result = "";
        try {
            result = YsxHttpClient.doPost(JSON.toJSONString(map), YsxMethodEnum.RemoveConference.name());
        } catch (Exception e) {
            returnresult = "网络错误";
            e.printStackTrace();
        }
        JSONObject jsonObject = JSON.parseObject(result);
        if (jsonObject.getInteger("code") != 200) {
            returnresult = "请求结果失败";
        }
        return returnresult;
    }

    /**
     * 延长会议时间
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "prolongConf/{strConfID}", method = RequestMethod.POST)
    @RequiresPermissions("api:yxs:control")
    @ResponseBody
    public String ProlongConference(@PathVariable("strConfID") String strConfID,
                                    @Value("${ysx-userId}") String userId,
                                    @RequestParam("prolongTime") Integer prolongTime) {
        String returnresult = "success";
        HashMap<Object, Object> map = Maps.newHashMap();
        map.put("userId", getUserIdIMPU(userId));
        map.put("conferenceID", strConfID);
        map.put("length", prolongTime * 1000 * 60);
        String result = "";
        try {
            result = YsxHttpClient.doPost(JSON.toJSONString(map), YsxMethodEnum.ProlongConference.name());
        } catch (Exception e) {
            returnresult = "网络错误";
            e.printStackTrace();
        }
        JSONObject jsonObject = JSON.parseObject(result);
        if (jsonObject.getInteger("code") != 200) {
            returnresult = "请求结果失败";
        }
        return returnresult;
    }

    /**
     * 邀请用户参与会议
     *
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "inviteSubscriber", method = RequestMethod.POST)
    @RequiresPermissions("api:yxs:control")
    @ResponseBody
    public String InviteSubscriber(@RequestParam("param") String param) {
        String result = "";
        try {
            result = YsxHttpClient.doPost(param, YsxMethodEnum.InviteSubscriber.name());
        } catch (Exception e) {
            result = "{result:\"网络错误\"}";
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 订阅会议状态
     *
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "SubscribeConference", method = RequestMethod.POST)
    @RequiresPermissions("api:yxs:control")
    @ResponseBody
    public String subscribeConference(@RequestParam("param") String param) {
        String result = "";
        try {
            result = YsxHttpClient.doPost(param, YsxMethodEnum.SubscribeConference.name());
        } catch (Exception e) {
            result = "{result:\"网络错误\"}";
            e.printStackTrace();
        }
        return result;
    }


    private JSONObject getConferInfo(String userId, String strConfID) {
        Map map = getRequestMap(userId, strConfID);
        String result = YsxHttpClient.doPost(JSON.toJSONString(map), YsxMethodEnum.QueryConferenceInfo.name());
        JSONObject object = JSON.parseObject(result);
        JSONObject confInfo = null;
        try {
            if (object.containsKey("confInfo"))
                confInfo = object.getJSONObject("confInfo");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return confInfo;
    }

    private YsxUser getAddYxsUser(ThirdYxsEquipment map) {
        YsxUser user = new YsxUser();
        user.setRole(2);//普通用户
        user.setStrName(map.getAccName());
        user.setDefaultMute(true);//默认闭音
        user.setCallID(map.getUserId());
        if(map.getSoftTerminal()){
            user.setStrUserID(getSoftUserId(map.getUserId()));
            user.setIsSoftTerminal(true);
        }else {
            user.setStrUserID(getUserIdIMPU(map.getUserId()));
            user.setIsSoftTerminal(false);
        }
        return user;
    }

    private Map getRequestMap(String userId, String strConfID) {
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("strUserId", getUserIdIMPU(userId));
        map.put("strConfID", strConfID);
        map.put("iCycleNo", 0);
        return map;
    }


    /**
     * 对userId加上前后缀
     *
     * @param userId
     * @return
     */
    private String getUserIdIMPU(String userId) {
        return "sip:+86" + userId.substring(1) + "@ims.ge.chinamobile.com";
    }

    /**
     * 软终端对userId加上前后缀
     *
     * @param userId
     * @return
     */
    private String getSoftUserId(String userId) {
        return "tel:+86" + userId;
    }


}

