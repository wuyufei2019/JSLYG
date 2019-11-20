package com.cczu.model.dao;

import com.cczu.model.entity.YHPC_CheckPlan_Detail;
import com.cczu.util.common.Parameter;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class YhpcPlanDetailDao extends BaseDao<YHPC_CheckPlan_Detail, Long> {

    public List<Map<String, Object>> listViewInfo(long id1) {
        String sql = "select acc.id,acc.type checkpointtype, a.starttime,a.endtime,u.name,acc.m1 pointname, " +
                " u.id uid ,u.gender,u.phone, acc.m1,acc.bindcontent,acc.areaname" +
                " from yhpc_checkplan_detail a "
                + " left join t_user u on a.userid=u.id "
                + " left join fxgk_accidentrisk acc on a.pointid = acc.id "
                + " where a.id1 != 0 and a.id1=" + id1;
        List<Map<String, Object>> list = findBySql(sql, null, Map.class);
        return list;
    }

    public List<Map<String, Object>> listViewInfo2(long id1) {
        String sql = "select distinct(acc.id),acc.m1 pointname,acc.m1,acc.type checkpointtype,acc.bindcontent "
                + " from yhpc_checkplan_detail a "
                + " left join fxgk_accidentrisk acc on a.pointid = acc.id "
                + " where a.id1 != 0 and a.id1=" + id1;
        List<Map<String, Object>> list = findBySql(sql, null, Map.class);
        return list;
    }

    public List<Map<String, Object>> listViewInfoPlan(String type) {
        String sql = "select a.id as detailId, a.ID1,a.pointid,a.userid,a.checkpointtype,a.starttime as st,a.endtime " +
                " as et ,a.type,de.id deptid from yhpc_checkplan_detail a left join t_user u on a.userid=u.id" +
                " left join t_department de on de.id = u.departmen " +
                " where a.id1 != 0 and a.type=:p1 ";
        List<Map<String, Object>> list = findBySql(sql, new Parameter(type), Map.class);
        return list;
    }

}
