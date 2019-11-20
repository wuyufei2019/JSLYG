package com.cczu.model.service;

import com.cczu.model.dao.YhpcTeamSettingDao;
import com.cczu.model.entity.YHPC_CheckTeamSetting;
import com.cczu.sys.comm.utils.StringUtils;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;


/**
 * @Description: 隐患排查点Service
 * @author: YZH
 * @date: 2017年12月27日
 */
@Transactional(readOnly = true)
@Service
public class YhpcTeamSettingService {
    @Resource
    private YhpcTeamSettingDao settingDao;

    public void saveInfo(String[] depts, String[] dates, String[] worktimes) {
        int deptLen = depts.length;
        int dateLen = dates.length;
        int workTimeLen = worktimes.length;
        Assert.isTrue(deptLen * dateLen == workTimeLen);
        convertWorkTime(dates, worktimes);
        for (int i = 0; i < workTimeLen; i++) {
            try {
                YHPC_CheckTeamSetting setting = new YHPC_CheckTeamSetting();
                setting.setID1(Long.valueOf(depts[i % deptLen]));
                if (StringUtils.isNotBlank(worktimes[i])) {
                    String[] times = worktimes[i].split("\\|");
                    Timestamp st = Timestamp.valueOf(times[0]);
                    Timestamp et = Timestamp.valueOf(times[1]);
                    if (st.after(et)) {
                        et.setTime(et.getTime() + 1000 * 60 * 60 * 24);
                    }
                    setting.setStarttime(st);
                    setting.setEndtime(et);
                }

                setting.setCount(dateLen);
                setting.setSort(i / dateLen);
                settingDao.save(setting);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    private void convertWorkTime(String[] dates, String[] worktimes) {
        int worktimeLen = worktimes.length;
        int dateLen = dates.length;
        for (int i = 0; i < worktimeLen; i++) {
            if (StringUtils.isNotBlank(worktimes[i])) {
                String[] smallTime = worktimes[i].split("-");
                String fulltime = dates[i / dateLen].trim() + " " + smallTime[0].trim() +
                        "|" + dates[i / dateLen].trim() + " " + smallTime[1].trim();
                worktimes[i] = fulltime;
            }
        }
    }


    public List<Map<String, Object>> listAllMap() {
        String hql = "select a.starttime as starttime,a.endtime as endtime,b.m1 as m1" +
                " from YHPC_CheckTeamSetting a ,Department b where a.ID1 = b.id ";
        Query query = null;
        try {
            query = settingDao.createQuery(hql);
            query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Map<String, Object>> maps = query.list();
        return maps;
    }

    public List<YHPC_CheckTeamSetting> listAll() {
        return settingDao.findAll();
    }

    public void deleteAll() {
        String hql = "delete from YHPC_CheckTeamSetting";
        settingDao.createQuery(hql).executeUpdate();
    }


}
