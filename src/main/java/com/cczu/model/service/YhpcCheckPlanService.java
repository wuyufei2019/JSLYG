package com.cczu.model.service;

import com.alibaba.fastjson.JSON;
import com.cczu.model.dao.YhpcCheckPlanDao;
import com.cczu.model.dao.YhpcPlanDetailDao;
import com.cczu.model.entity.YHPC_CheckPlanEntity;
import com.cczu.model.entity.YHPC_CheckPlan_Detail;
import com.cczu.model.entity.YHPC_CheckPlan_Time;
import com.cczu.sys.system.entity.Message;
import com.cczu.sys.system.utils.MessageUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 巡检班次任务设置
 *
 * @author zpc
 */
@Service("YhpcCheckPlanService")
public class YhpcCheckPlanService {

    @Resource
    private YhpcCheckPlanDao yhpcCheckPlanDao;
    @Resource
    private YhpcPlanDetailDao detailDao;

    /**
     * 查询巡检班次任务list
     *
     * @param mapData
     * @return
     */
    public Map<String, Object> dataGrid(Map<String, Object> mapData) {
        List<Map<String, Object>> list = yhpcCheckPlanDao.dataGrid(mapData);
        int getTotalCount = yhpcCheckPlanDao.getTotalCount(mapData);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", list);
        map.put("total", getTotalCount);
        return map;
    }


    public List<Map<String, Object>> newDataGrid(long id1) {

        return detailDao.listViewInfo(id1);
    }

    /**
     * 添加巡检班次任务
     *
     * @param bcrw
     */
    public long addInfo(YHPC_CheckPlanEntity bcrw) {
        yhpcCheckPlanDao.save(bcrw);
        return bcrw.getID();
    }

    public void addPlanDetail(YHPC_CheckPlanEntity bcrw, String[] stimes, String[] etimes,
                              String[] userids, String[] points) {
        yhpcCheckPlanDao.save(bcrw);
        long id = bcrw.getID();
        String type = bcrw.getType();
        Assert.isTrue(stimes.length == etimes.length);
        for (int i = 0; i < stimes.length; i++) {
            YHPC_CheckPlan_Detail detail = new YHPC_CheckPlan_Detail();
            detail.setID1(id);
            detail.setStarttime(stimes[i]);
            detail.setEndtime(etimes[i]);
            detail.setType(type);
            for (String userid : userids) {
                detail.setUserid(Integer.valueOf(userid));
                for (String point : points) {
                    String[] id_type = point.split("-");
                    detail.setPointid(Long.valueOf(id_type[0]));
                    detail.setCheckpointtype("2".equals(id_type[1]) ? 2 : 1);
                    detailDao.save(detail);
                    detailDao.clear();
                    detail.setID(null);
                }
            }
        }
    }

    /**
     * 查询检查点list
     *
     * @param map
     * @return
     */
    public Map<String, Object> jcddataGrid(Map<String, Object> mapData) {
        List<Map<String, Object>> list = yhpcCheckPlanDao.jcddataGrid(mapData);
        int getTotalCount = yhpcCheckPlanDao.getjcdTotalCount(mapData);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", list);
        map.put("total", getTotalCount);
        return map;
    }


    /**
     * 根据id删除
     *
     * @param id
     */
    public void deleteInfo(long id) {
        yhpcCheckPlanDao.deleteInfo(id);
    }

    /**
     * 根据id1删除时间中间表
     *
     * @param parseLong
     */
    public void deletexjbcsjInfo(long id1) {
        yhpcCheckPlanDao.deletexjbcsjInfo(id1);
    }

    /**
     * 根据id1删除人员中间表
     *
     * @param parseLong
     */
    public void deletexjbcryInfo(long id1) {
        yhpcCheckPlanDao.deletexjbcryInfo(id1);
    }

    /**
     * 根据id1删除检查点中间表
     *
     * @param parseLong
     */
    public void deletexjbcjcdInfo(long id1) {
        yhpcCheckPlanDao.deletexjbcjcdInfo(id1);
    }

    /**
     * 根据id查找巡检班次任务
     *
     * @param id
     * @return
     */
    public YHPC_CheckPlanEntity findById(Long id) {
        return yhpcCheckPlanDao.find(id);
    }

    public List<Map<String, Object>> getidname(long id1) {
        return yhpcCheckPlanDao.getidname(id1);
    }


    /**
     * 修改
     *
     * @param zfry
     */
    public void updateInfo(YHPC_CheckPlanEntity bcrw) {
        yhpcCheckPlanDao.save(bcrw);
    }

    public List<Map<String, Object>> findbclist(Long qyid) {
        return yhpcCheckPlanDao.findbclist(qyid);
    }

    /**
     * 我的任务list
     *
     * @param map
     * @return
     */
    public Map<String, Object> myrwdataGrid(Map<String, Object> mapData) {
        List<Map<String,Object>> list = yhpcCheckPlanDao.myrwdataGrid(mapData);
        int getTotalCount = yhpcCheckPlanDao.getmyrwTotalCount(mapData);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", list);
        map.put("total", getTotalCount);
        return map;
    }

    /**
     * 查看企业安全员的班次 app
     *
     * @return
     */
    public List<Map<String, Object>> qyxjbcForApp(Map<String, Object> mapData) {
        return yhpcCheckPlanDao.qyxjbcForApp(mapData);
    }

    /**
     * 班次消息
     */
    public void BcMsg() {
        Map<String, Object> map = new HashMap<>();
        //刚进来时设置默认时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String start = dateFormat.format(getThisWeekMonday(new Date()));
        String end = dateFormat.format(getNextWeekMonday(new Date()));
        try {
            //计算年检的应查次数乘积
            int nj = (int) Math.ceil(Integer.parseInt(end.substring(0, 3)) - Integer.parseInt(start.substring(0, 3))) + 1;
            //计算月检的应查次数乘积
            Date beginDate = dateFormat.parse(start);
            Date endDate = dateFormat.parse(end);
            long day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);//相差天数
            int yj = (int) (day / 31) + 1;
            //计算周检的应查次数乘积
            int zj = (int) (day / 7) + 1;
            //计算日检的应查次数乘积
            int rj = (int) day;
            map.put("nj", nj);
            map.put("yj", yj);
            map.put("zj", zj);
            map.put("rj", rj);
            map.put("start", start);
            map.put("end", end);

            List<Map<String, String>> qyids = yhpcCheckPlanDao.qyidslist();
            for (Map<String, String> qyid : qyids) {
                map.put("qyid", qyid.get("qyid"));
                List<Map<String, Object>> msglist = yhpcCheckPlanDao.bctx(map);
                for (Map<String, Object> msg : msglist) {
                    //发送msg消息
                    Map<String, Object> msgmap = new HashMap<String, Object>();
                    msgmap.put(Message.MSGTARGET_H5, "weixin/enterprise/danger/checkplan/dangerbanci.jsp");
                    msgmap.put(Message.MSGTARGET_PC, "yhpc/bcrw/index");
                    MessageUtil.sendMsg(msg.get("userid").toString(), msg.get("userid").toString(), "你有班次未巡检，请立即巡检！", Message.MessageType.DXJ.getMsgType(), JSON.toJSONString(msgmap), "你有班次未巡检，请立即巡检！");
                }
            }
        } catch (Exception e) {
            System.out.println("班次消息提醒出错");
        }
    }

    public Date getThisWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 获得当前日期是一个星期的第几天  
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一  
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天  
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值  
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal.getTime();
    }

    public Date getNextWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getThisWeekMonday(date));
        cal.add(Calendar.DATE, 7);
        return cal.getTime();
    }


}
