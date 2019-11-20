package com.cczu.model.service;

import com.cczu.model.dao.YhpcPlanDetailDao;
import com.cczu.model.dao.YhpcPlanTaskDao;
import com.cczu.model.dao.YhpcTeamSettingDao;
import com.cczu.model.entity.YHPC_CheckPlan_Task;
import com.cczu.model.entity.YHPC_CheckTeamSetting;
import com.cczu.sys.comm.utils.DateUtils;
import com.google.common.collect.Lists;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @Description: 隐患排查点Service
 * @author: YZH
 * @date: 2017年12月27日
 */
@Transactional(readOnly = true)
@Service
public class YhpcPlanDetailService {
    @Resource
    private YhpcPlanDetailDao detailDao;
    @Resource
    private YhpcTeamSettingDao teamSettingDao;
    @Resource
    private YhpcPlanTaskDao planTaskDao;

    /**
     * 生成日检 每日任务
     */
    public void generatorDaysCheckTask() {
        //未完成任务置为过期
        expirationTask("1");
        //查询 班组任务 的详细detail表信息和其他相关信息
        List<Map<String, Object>> list = detailDao.listViewInfoPlan("1");
        //查询倒班设置
        List<YHPC_CheckTeamSetting> settings = teamSettingDao.findAll();
        if (settings.size() == 0) {
            return;
        }
        List<Long> workTeam = getWorkTeam(settings);
        for (Map<String, Object> map : list) {
            //人员需绑定部门
            if (map.get("deptid") != null) {
                if (workTeam.contains(Long.parseLong(map.get("deptid").toString()))) {
                    //生成任务
                    YHPC_CheckPlan_Task task = new YHPC_CheckPlan_Task();
                    try {
                        BeanUtils.populate(task, map);
                        Timestamp st = Timestamp.valueOf(DateUtils.getDate() + " " + map.get("st"));
                        task.setStarttime(st);
                        Timestamp et = Timestamp.valueOf(DateUtils.getDate() + " " + map.get("et"));
                        if (st.after(et)) {
                            et.setTime(et.getTime() + 1000 * 60 * 60 * 24);
                        }
                        task.setEndtime(et);
                        task.setStatus(YHPC_CheckPlan_Task.CHECK_STATUS_GEN);
                        planTaskDao.save(task);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }

    }

    /**
     * 生成周检 周任务
     */
    public void generatorWeeksORMonthCheckTask(String type) {
        //未完成任务置为过期
        expirationTask(type);
        //查询 班组任务 的详细detail表信息和其他相关信息
        List<Map<String, Object>> list = detailDao.listViewInfoPlan(type);
        //查询倒班设置
        for (Map<String, Object> map : list) {
            //人员需绑定部门
            if (map.get("deptid") != null) {
                //生成任务
                YHPC_CheckPlan_Task task = new YHPC_CheckPlan_Task();
                try {
                    BeanUtils.populate(task, map);
                    //获取执行任务的开始、结束时间
                    Date now = DateUtils.getNow();
                    Date st1 = DateUtils.addDays(now, Integer.valueOf(map.get("st").toString()));
                    Date st2 = DateUtils.addDays(now, Integer.valueOf(map.get("et").toString()));
                    Date dateStart = DateUtils.getDateStart(st1);
                    Date dateEnd = DateUtils.getDateEnd(st2);

                    task.setStarttime(new Timestamp(dateStart.getTime()));
                    task.setEndtime(new Timestamp(dateEnd.getTime()));
                    task.setStatus(YHPC_CheckPlan_Task.CHECK_STATUS_GEN);
                    planTaskDao.save(task);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


    }

    /**
     * 生成年检 任务
     */
    public void generatorYearsCheckTask() {
        //未完成任务置为过期
        expirationTask("4");
        //查询 班组任务 的详细detail表信息和其他相关信息
        List<Map<String, Object>> list = detailDao.listViewInfoPlan("4");
        //查询倒班设置
        for (Map<String, Object> map : list) {
            //人员需绑定部门
            if (map.get("deptid") != null) {
                //生成任务
                YHPC_CheckPlan_Task task = new YHPC_CheckPlan_Task();
                try {
                    BeanUtils.populate(task, map);
                    //获取执行任务的开始、结束时间
                    Date now = DateUtils.getNow();
                    Date st1 = DateUtils.addMonths(now, Integer.valueOf(map.get("st").toString()));
                    Date st2 = DateUtils.addMonths(now, Integer.valueOf(map.get("et").toString()));
                    Date dateStart = DateUtils.getDateStart(st1);
                    Date dateEnd = DateUtils.getDateEnd(st2);
                    task.setStarttime(new Timestamp(dateStart.getTime()));
                    task.setEndtime(new Timestamp(dateEnd.getTime()));
                    task.setStatus(YHPC_CheckPlan_Task.CHECK_STATUS_GEN);
                    planTaskDao.save(task);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


    }


    public List<Map<String, Object>> listViewInfo(long id) {
        return detailDao.listViewInfo(id);
    }

    public List<Map<String, Object>> listViewInfo2(long id) {
        return detailDao.listViewInfo2(id);
    }

    //获取上班的班组
    private List<Long> getWorkTeam(List<YHPC_CheckTeamSetting> settings) {
        Date now = DateUtils.getNow();
        int cycle = settings.get(0).getCount();//循环次数
        List<Long> workings = Lists.newArrayList();
        for (YHPC_CheckTeamSetting setting : settings) {
            Timestamp starttime = setting.getStarttime();
            if (starttime != null) {//非休息时间
                int days = (int) ((now.getTime() - starttime.getTime()) / (1000 * 3600 * 24));
                if (days % cycle == 0 && !workings.contains(setting.getID1())) {
                    workings.add(setting.getID1());
                }
            }
        }
        return workings;
    }

    //日检未完成任务设置为过期
    private void expirationTask(String type) {
        String hql = "update YHPC_CheckPlan_Task set status=3 where status = 1 and type=?0 and getdate()> endtime";
        planTaskDao.createQuery(hql, type).executeUpdate();
    }


    public void deleteByPlanId(long id) {
        String sql = "delete from YHPC_CheckPlan_Detail where id1 =" + id;
        planTaskDao.createQuery(sql).executeUpdate();
    }
}
