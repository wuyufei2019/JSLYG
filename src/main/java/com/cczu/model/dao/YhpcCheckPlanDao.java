package com.cczu.model.dao;

import com.cczu.model.entity.YHPC_CheckPlanEntity;
import com.cczu.util.common.StringUtils;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 巡检班次任务设置
 *
 * @author zpc
 */
@Repository("YhpcCheckPlanDao")
public class YhpcCheckPlanDao extends BaseDao<YHPC_CheckPlanEntity, Long> {

    /**
     * 查询巡检班次任务list
     *
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
        String content = content(mapData);
        String sql = "SELECT TOP " + mapData.get("pageSize") + "a.* FROM (" +
                "SELECT ROW_NUMBER() OVER(order by dep.id,a.id) rownum,a.* ,dep.m1 depname FROM yhpc_checkplan a "
                + " left join t_user c on a.userid=c.id "
                + " left join t_department dep on c.departmen = dep.id "
                + " where a.id1 != 0 " + content + " ) a "
                + "WHERE rownum >" + mapData.get("pageSize") + "*(" + mapData.get("pageNo") + "-1) ";
        List<Map<String, Object>> list = findBySql(sql, null, Map.class);
        return list;
    }


    /**
     * 查询list的个数
     *
     * @param mapData
     * @return
     */
    public int getTotalCount(Map<String, Object> mapData) {
        String content = content(mapData);
        String sql = "SELECT COUNT(*) FROM yhpc_checkplan a " +
                " left join t_user c on a.userid=c.id " +
                " left join t_department dep on c.departmen = dep.id " +
                " where a.id1 != 0 " + content;
        List<Object> list = findBySql(sql);
        return (int) list.get(0);
    }

    /**
     * 查网格点班次任务list
     *
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> wgbcdataGrid(Map<String, Object> mapData) {
        String content = content(mapData);
        String sql = "SELECT TOP " + mapData.get("pageSize") + "a.* FROM (" +
                "SELECT ROW_NUMBER() OVER(order by b.id,a.type) rownum,a.*,b.m1 wgname FROM yhpc_checkplan a left join t_barrio b on b.id = a.wgid where a.id1 = 0 " + content + " ) a " +
                "WHERE rownum >" + mapData.get("pageSize") + "*(" + mapData.get("pageNo") + "-1) ";
        List<Map<String, Object>> list = findBySql(sql, null, Map.class);
        return list;
    }

    /**
     * 查询网格点班次list的个数
     *
     * @param mapData
     * @return
     */
    public int wgbcgetTotalCount(Map<String, Object> mapData) {
        String content = content(mapData);
        String sql = "SELECT COUNT(*) FROM yhpc_checkplan a left join t_barrio b on b.id = a.wgid where a.id1 = 0 " + content;
        List<Object> list = findBySql(sql);
        return (int) list.get(0);
    }

    /**
     * 查询网格点list
     *
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> wgddataGrid(Map<String, Object> mapData) {
        String content = content(mapData);
        String sql = "SELECT TOP " + mapData.get("pageSize") + " a.* FROM (" +
                "SELECT ROW_NUMBER() OVER(order by a.id1) rownum,* FROM (SELECT a.id,a.id1,a.name,a.area,bindcontent,b.m1 qyname,b.id2 xzqy,b.m36 FROM yhpc_checkpoint a "
                + "left join bis_enterprise b on b.id = a.id1 where a.usetype = '1' and b.s3 = 0) a where 1=1 " + content + " ) a " +
                "WHERE rownum >" + mapData.get("pageSize") + "*(" + mapData.get("pageNo") + "-1) ";
        List<Map<String, Object>> list = findBySql(sql, null, Map.class);
        return list;
    }

    /**
     * 查询网格点list的个数
     *
     * @param mapData
     * @return
     */
    public int getwgdTotalCount(Map<String, Object> mapData) {
        String content = content(mapData);
        String sql = "SELECT COUNT(*) FROM (SELECT a.id,a.id1,a.name,a.area,bindcontent,b.m1 qyname,b.id2 xzqy,b.m36 FROM yhpc_checkpoint a "
                + "left join bis_enterprise b on b.id = a.id1 where a.usetype = '1' and b.s3 = 0) a where 1=1 " + content;
        List<Object> list = findBySql(sql);
        return (int) list.get(0);
    }

    /**
     * 根据网格id获取该网格直属网格点
     *
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> getWgdByWgid(long wgid) {
        String sql = "SELECT a.id,a.name FROM yhpc_checkpoint a LEFT JOIN bis_enterprise b ON a.id1 = b.id LEFT JOIN t_barrio c ON c.code = b.id2 "
                + "WHERE b.s3 = 0 AND a.usetype = '1' AND c.id = " + wgid;
        List<Map<String, Object>> list = findBySql(sql, null, Map.class);
        return list;
    }

    /**
     * 查询条件判断
     *
     * @return
     * @throws ParseException
     */
    public String content(Map<String, Object> mapData) {
        String content = " ";
        if (mapData.get("type") != null && mapData.get("type") != "") {
            content = content + " AND a.type =" + mapData.get("type");
        }
        if (mapData.get("jcdname") != null && mapData.get("jcdname") != "") {
            content = content + " AND a.name like '%" + mapData.get("jcdname") + "%' ";
        }
        if (mapData.get("jcdtype") != null && mapData.get("jcdtype") != "") {
            content = content + " AND a.checkpointtype = " + mapData.get("jcdtype") + " ";
        }
        if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
            content = content + " AND a.id1 ="+mapData.get("qyid");
        }
        if (mapData.get("bcname") != null && mapData.get("bcname") != "") {
            content = content + " AND a.name like '%" + mapData.get("bcname") + "%' ";
        }
        if (mapData.get("userid") != null && mapData.get("userid") != "") {
            content = content + " AND c.id2 = '" + mapData.get("userid") + "' ";
        }

        if (mapData.get("depids") != null && mapData.get("depids") != "") {
            content = content + " and dep.id in (" + mapData.get("depids") + ")";
        } else {
            if (mapData.get("depcode1") != null && mapData.get("depcode1") != "") {
                content = content + " and dep.code='" + mapData.get("depcode1") + "'";
            }
            if (mapData.get("depcode2") != null && mapData.get("depcode2") != "") {
                content = content + " and dep.code like'" + mapData.get("depcode2") + "%'";
            }
        }
        if (mapData.get("depcode3") != null && mapData.get("depcode3") != "") {
            content = content + " and dep.code like'" + mapData.get("depcode3") + "%'";
        }
        return content;
    }

    /**
     * 添加巡检班次任务
     *
     * @param zfry
     */
    public long fasaddInfo(YHPC_CheckPlanEntity bcrw) {
        save(bcrw);
        return bcrw.getID();
    }

    /**
     * 查询检查点list
     *
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> jcddataGrid(Map<String, Object> mapData) {
        String content2 = content2(mapData);
        String sql = "SELECT TOP " + mapData.get("pageSize") + "a.* FROM (" +
                "SELECT ROW_NUMBER() OVER(order by a.depname,a.fxfj) rownum,a.* FROM (SELECT a.id,a.id1,a.m1 name,a.areaname," +
                "a.m9 fxfj,a.checkpointadderss area,a.bindcontent,dep.m1 depname,a.type FROM fxgk_accidentrisk a "
                + " left join t_department dep on a.depid=dep.id "
                + " WHERE a.s3=0 " + content2 + " ) a)a " +
                "WHERE rownum >" + mapData.get("pageSize") + "*(" + mapData.get("pageNo") + "-1) ";
        List<Map<String, Object>> list = findBySql(sql, null, Map.class);
        return list;
    }

    /* 查询条件判断
     * @return
     * @throws ParseException
     */
    public String content2(Map<String, Object> mapData) {
        String content = " ";
        if (mapData.get("jcdname") != null && mapData.get("jcdname") != "") {
            content = content + " AND a.m1 like '%" + mapData.get("jcdname") + "%' ";
        }
        if (mapData.get("fxfj") != null && mapData.get("fxfj") != "") {
            content = content + " AND a.m9 = '" + mapData.get("fxfj") + "' ";
        }
        if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
            content = content + " AND a.id1 ="+mapData.get("qyid");
        }
        if (mapData.get("zzbm") != null && mapData.get("zzbm") != "") {
            content = content + " AND a.areaname like '%" + mapData.get("zzbm") + "%' ";
        }
        //所属部门权限
        if (mapData.get("depids") != null && mapData.get("depids") != "") {
            content = content + " and dep.id in (" + mapData.get("depids") + ")";
        } else {
            if (mapData.get("depcode1") != null && mapData.get("depcode1") != "") {
                content = content + " and dep.code='" + mapData.get("depcode1") + "'";
            }
            if (mapData.get("depcode2") != null && mapData.get("depcode2") != "") {
                content = content + " and dep.code like'" + mapData.get("depcode2") + "%'";
            }
        }
        return content;
    }

    /**
     * 查询检查点list的个数
     *
     * @param mapData
     * @return
     */
    public int getjcdTotalCount(Map<String, Object> mapData) {
        String content = content(mapData);
        String content2 = content2(mapData);
        String sql = "SELECT COUNT(*) FROM (SELECT a.id,a.id1,a.m1 name,a.m9 fxfj,a.checkpointadderss area,a.bindcontent,dep.id depid,checkpointtype=1 FROM fxgk_accidentrisk a "
                + " left join t_department dep on a.depid=dep.id "
                + " WHERE a.s3=0 " + content2
                + ") a " +
                " left join t_department dep on a.depid = dep.id where 1=1 " + content;
        List<Object> list = findBySql(sql);
        return (int) list.get(0);
    }

    //根据id1删除时间中间表
    public void deletexjbcsjInfo(long id1) {
        String sql = " delete yhpc_checkplan_time WHERE id1=" + id1;
        updateBySql(sql);
    }

    //根据id1删除人员中间表
    public void deletexjbcryInfo(long id1) {
        String sql = " delete yhpc_checkplan_user WHERE id1=" + id1;
        updateBySql(sql);
    }

    //根据id1删除检查点中间表
    public void deletexjbcjcdInfo(long id1) {
        String sql = " delete yhpc_checkplan_point WHERE id1=" + id1;
        updateBySql(sql);
    }

    //根据id删除
    public void deleteInfo(long id) {
        String sql = " delete yhpc_checkplan WHERE id=" + id;
        updateBySql(sql);
    }

    //巡检人员
    public List<Map<String, Object>> getidname(long id1) {
        String sql = "SELECT b.ID,b.NAME,b.GENDER,b.PHONE FROM yhpc_checkplan_user a LEFT JOIN t_user b ON a.id2 = b.ID "
                + "WHERE a.id1 = " + id1;
        List<Map<String, Object>> list = findBySql(sql, null, Map.class);
        return list;
    }

    //风险点
    public Map<String, Object> getidname2(long id) {
        String sql = "SELECT b.id,b.id1,b.m1 name,b.checkpointadderss area,b.bindcontent,a.checkpointtype,c.m1 qyname FROM yhpc_checkplan_point a"
                + " LEFT JOIN fxgk_accidentrisk b ON a.id2 = b.id LEFT JOIN bis_enterprise c ON b.id1 = c.id WHERE c.s3 = 0 AND b.s3 = 0 AND a.id =" + id;
        List<Map<String, Object>> list = findBySql(sql, null, Map.class);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    //隐患点
    public Map<String, Object> getidname3(long id) {
        String sql = "SELECT b.id,b.id1,b.name,b.area,b.bindcontent,a.checkpointtype,c.m1 qyname FROM yhpc_checkplan_point a "
                + "LEFT JOIN yhpc_checkpoint b ON a.id2 = b.id LEFT JOIN bis_enterprise c ON b.id1 = c.id "
                + "WHERE c.s3 = 0 AND a.id = " + id;
        List<Map<String, Object>> list = findBySql(sql, null, Map.class);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    //根据qyid查找班次信息
    public List<Map<String, Object>> findbclist(Long qyid) {
        String sql = "select id,name text from yhpc_checkplan where id1=" + qyid;
        List<Map<String, Object>> list = findBySql(sql, null, Map.class);
        return list;
    }

    /**
     * 我的任务list
     *
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> myrwdataGrid(Map<String, Object> mapData) {
        String sql = "SELECT TOP " + mapData.get("pageSize") + "a.* FROM (" +
                "SELECT ROW_NUMBER() OVER(order by a.id) rownum," +
                "a.name,a.type,t.status,t.starttime,t.endtime,f.m1 " +
                " FROM yhpc_checkplan_task t left join fxgk_accidentrisk f on f.id = t.pointid" +
                " left join yhpc_checkplan a on a.id = t.id1 where " +
                " t.status=1 and t.id1 != 0 and t.userid=" + mapData.get("userid") +
                " ) a WHERE rownum >" + mapData.get("pageSize") + "*(" + mapData.get("pageNo") + "-1) ";
        List<Map<String, Object>> list = findBySql(sql, null, Map.class);
        return list;
    }


    /**
     * 我的任务list个数
     *
     * @param mapData
     * @return
     */
    public int getmyrwTotalCount(Map<String, Object> mapData) {
        String sql = "SELECT COUNT(*) FROM yhpc_checkplan_task t left join fxgk_accidentrisk f on f.id = t.pointid "+
                " where t.status=1 and t.id1 != 0 and t.userid = " + mapData.get("userid");
        List<Object> list = findBySql(sql);
        return (int) list.get(0);
    }

    /**
     * 查看网格员的班次 app
     *
     * @return
     */
    public List<Map<String, Object>> wgybcForApp(String xzqy) {
        String sql = "SELECT a.*,b.m1 wgname FROM yhpc_checkplan a left join t_barrio b on b.id = a.wgid where a.id1 = 0 and b.code = '" + xzqy + "'";
        List<Map<String, Object>> list = findBySql(sql, null, Map.class);
        return list;
    }

    /**
     * 查看企业安全员的班次 app
     *
     * @return
     */
    public List<Map<String, Object>> qyxjbcForApp(Map<String, Object> mapData) {
        String content = "";
        if (mapData.get("cptype") != null && mapData.get("cptype") != "") {
            content = content + " AND b.checkpointtype = '" + mapData.get("cptype") + "' ";
        }
        if (mapData.get("cpid") != null && mapData.get("cpid") != "") {
            content = content + " AND b.id2 = " + mapData.get("cpid") + " ";
        }
        if (mapData.get("userid") != null && mapData.get("userid") != "") {
            content = content + " AND c.id2 = " + mapData.get("userid") + " ";
        }
        String sql = "SELECT a.* FROM yhpc_checkplan a LEFT JOIN yhpc_checkplan_point b ON a.id = b.id1 LEFT JOIN yhpc_checkplan_user c ON a.id = c.id1 WHERE 1=1 " + content;
        List<Map<String, Object>> list = findBySql(sql, null, Map.class);
        return list;
    }

    /**
     * 班次消息提醒
     */
    public List<Map<String, Object>> bctx(Map<String, Object> mapData) {
        String timecontent = "AND a.createtime between '" + mapData.get("start") + "' and '" + mapData.get("end") + "' ";
        String content = "";
        if (mapData.get("qyid") != null && mapData.get("qyid") != "") {
            content = content + "AND t.id2 = '" + mapData.get("qyid") + "' ";
        }
        String sql = " SELECT * FROM(SELECT ROW_NUMBER() OVER ( ORDER BY Round(CAST(ISNULL(a.yxcs, 0) AS float) / CAST(nullif(a.yccs, 0) AS float) * 100, 2) desc) AS RowNumber, a.userid, a.username, a.yxcs, a.yccs, ISNULL(Round(CAST(ISNULL(a.yxcs, 0) AS float) / CAST(nullif(a.yccs, 0) AS float) * 100, 2), 0) bl"
                + " from ( SELECT t.id userid, t.name username, (a.yccs1*" + mapData.get("rj") + "+b.yccs2*" + mapData.get("zj") + "+c.yccs3*" + mapData.get("yj") + "+d.yccs4*" + mapData.get("nj") + ") yccs, (a.yxcs1+b.yxcs2+c.yxcs3+d.yxcs4) yxcs FROM t_user t left join ( SELECT b.ID userid, b.name username, (ISNULL(d.yhcs, 0)+ISNULL(c.fxcs, 0)) yxcs1, ISNULL(e.yccs, 0) yccs1 FROM t_user b"
                + " LEFT JOIN ( SELECT a.userid, a.name username, COUNT(a.userid) yhcs FROM (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, convert(varchar(10), a.createtime, 120) ORDER BY a.createtime DESC) AS rn, a.*,d.name"
                + " FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 LEFT JOIN t_user d ON d.id = a.userid WHERE a.checkpointtype = '2' AND b.id1 != 0 AND b.type = '1' AND CONVERT(varchar(100), a.createtime, 8) >= c.starttime AND CONVERT(varchar(100), a.createtime, 8) <= c.endtime) a where a.rn = 1) a"
                + " LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '2' AND b.usetype = '2' " + timecontent + " GROUP BY a.userid, a.name) d ON d.userid = b.ID LEFT JOIN ( SELECT a.userid, a.name username, COUNT(a.userid) fxcs FROM (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, convert(varchar(10), a.createtime, 120) ORDER BY a.createtime DESC) AS rn, a.* ,d.name"
                + " FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 LEFT JOIN t_user d ON d.id = a.userid WHERE a.checkpointtype = '1' AND b.id1 != 0 AND b.type = '1' AND CONVERT(varchar(100), a.createtime, 8) >= c.starttime AND CONVERT(varchar(100), a.createtime, 8) <= c.endtime) a where a.rn = 1 ) a"
                + " LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '1' " + timecontent + " GROUP BY a.userid, a.name) c ON c.userid = b.ID LEFT JOIN ( SELECT f.id2 userid, g.name, COUNT(*) yccs FROM yhpc_checkplan a LEFT JOIN t_user b ON a.userid = b.ID LEFT JOIN yhpc_checkplan_time d ON d.id1 = a.id"
                + " LEFT JOIN yhpc_checkplan_point e ON e.id1 = a.id LEFT JOIN yhpc_checkplan_user f ON f.id1 = a.id LEFT JOIN t_user g on g.id=f.id2 WHERE a.id1 != 0 AND a.type = '1' GROUP BY f.id2, g.name ) e ON e.userid = b.id ) a on t.id=a.userid left join ( SELECT b.ID userid, b.name username, (ISNULL(d.yhcs, 0)+ISNULL(c.fxcs, 0)) yxcs2, ISNULL(e.yccs, 0) yccs2 FROM t_user b"
                + " LEFT JOIN ( SELECT a.userid, a.name username, COUNT(a.qyid) yhcs FROM (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, convert(varchar(7), a.createtime, 120), DATEPART(week, a.createtime) ORDER BY a.createtime DESC) AS rn, a.* ,d.name"
                + " FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 LEFT JOIN t_user d ON d.id = a.userid WHERE a.checkpointtype = '2' AND b.id1 != 0 AND b.type = '2' AND Datepart(weekday, a.createtime) >= c.starttime AND Datepart(weekday, a.createtime) <= c.endtime) a where a.rn = 1 ) a"
                + " LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '2' AND b.usetype = '2' " + timecontent + " GROUP BY a.userid, a.name) d ON d.userid = b.ID LEFT JOIN ( SELECT a.userid, a.name username, COUNT(a.userid) fxcs FROM (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, d.name, convert(varchar(7), a.createtime, 120), DATEPART(week, a.createtime) ORDER BY a.createtime DESC) AS rn, a.* ,d.name"
                + " FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 LEFT JOIN t_user d ON d.id = a.userid WHERE a.checkpointtype = '1' AND b.id1 != 0 AND b.type = '2' AND Datepart(weekday, a.createtime) >= c.starttime AND Datepart(weekday, a.createtime) <= c.endtime) a where a.rn = 1 ) a"
                + " LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '1' " + timecontent + " GROUP BY a.userid, a.name) c ON c.userid = b.ID"
                + " LEFT JOIN ( SELECT f.id2 userid, g.name, COUNT(*) yccs FROM yhpc_checkplan a LEFT JOIN t_user b ON a.userid = b.ID LEFT JOIN yhpc_checkplan_time d ON d.id1 = a.id LEFT JOIN yhpc_checkplan_point e ON e.id1 = a.id LEFT JOIN yhpc_checkplan_user f ON f.id1 = a.id LEFT JOIN t_user g on g.id=f.id2 WHERE a.id1 != 0 AND a.type = '2' GROUP BY f.id2, g.name ) e ON e.userid = b.id )b on t.id=b.userid left join ( SELECT b.ID userid, b.name username, (ISNULL(d.yhcs, 0)+ISNULL(c.fxcs, 0)) yxcs3, ISNULL(e.yccs, 0) yccs3 FROM t_user b"
                + " LEFT JOIN ( SELECT a.userid, a.name username, COUNT(a.userid) yhcs FROM (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, convert(varchar(7), a.createtime, 120) ORDER BY a.createtime DESC) AS rn, a.* ,d.name"
                + " FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 LEFT JOIN t_user d ON d.id = a.userid WHERE a.checkpointtype = '2' AND b.id1 != 0 AND b.type = '3' AND Day(a.createtime) >= c.starttime AND Day(a.createtime) <= c.endtime) a where a.rn = 1 ) a"
                + " LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '2' AND b.usetype = '2' " + timecontent + " GROUP BY a.userid, a.name) d ON d.userid = b.ID LEFT JOIN ( SELECT a.userid, a.name username, COUNT(a.userid) fxcs FROM (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, convert(varchar(7), a.createtime, 120) ORDER BY a.createtime DESC) AS rn, a.* ,d.name"
                + " FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 LEFT JOIN t_user d ON d.id = a.userid WHERE a.checkpointtype = '1' AND b.id1 != 0 AND b.type = '3' AND Day(a.createtime) >= c.starttime AND Day(a.createtime) <= c.endtime) a where a.rn = 1 ) a"
                + " LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '1' " + timecontent + " GROUP BY a.userid, a.name) c ON c.userid = b.ID LEFT JOIN ( SELECT f.id2 userid, g.name, COUNT(*) yccs FROM yhpc_checkplan a LEFT JOIN t_user b ON a.userid = b.ID"
                + " LEFT JOIN yhpc_checkplan_time d ON d.id1 = a.id LEFT JOIN yhpc_checkplan_point e ON e.id1 = a.id LEFT JOIN yhpc_checkplan_user f ON f.id1 = a.id LEFT JOIN t_user g on g.id=f.id2 WHERE a.id1 != 0 AND a.type = '3' GROUP BY f.id2, g.name ) e ON e.userid = b.id )c on t.id=c.userid left join ( SELECT b.ID userid, b.name username, (ISNULL(d.yhcs, 0)+ISNULL(c.fxcs, 0)) yxcs4, ISNULL(e.yccs, 0) yccs4 FROM t_user b"
                + " LEFT JOIN ( SELECT a.userid, a.name username, COUNT(a.userid) yhcs FROM (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, convert(varchar(4), a.createtime, 120) ORDER BY a.createtime DESC) AS rn, a.* ,d.name"
                + " FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 LEFT JOIN t_user d ON d.id = a.userid WHERE a.checkpointtype = '2' AND b.id1 != 0 AND b.type = '4' AND MONTH(a.createtime) >= c.starttime AND MONTH(a.createtime) <= c.endtime) a where a.rn = 1 ) a"
                + " LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '2' AND b.usetype = '2' " + timecontent + " GROUP BY a.userid, a.name) d ON d.userid = b.ID LEFT JOIN ( SELECT a.userid, a.name username, COUNT(a.userid) fxcs FROM (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, convert(varchar(4), a.createtime, 120) ORDER BY a.createtime DESC) AS rn, a.*,d.name"
                + " FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 LEFT JOIN t_user d ON d.id = a.userid WHERE a.checkpointtype = '1' AND b.id1 != 0 AND b.type = '4' AND MONTH(a.createtime) >= c.starttime AND MONTH(a.createtime) <= c.endtime) a where a.rn = 1 ) a"
                + " LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '1' " + timecontent + " GROUP BY a.userid, a.name) c ON c.userid = b.ID"
                + " LEFT JOIN ( SELECT f.id2 userid, g.name, COUNT(*) yccs FROM yhpc_checkplan a LEFT JOIN t_user b ON a.userid = b.ID LEFT JOIN yhpc_checkplan_time d ON d.id1 = a.id LEFT JOIN yhpc_checkplan_point e ON e.id1 = a.id LEFT JOIN yhpc_checkplan_user f ON f.id1 = a.id LEFT JOIN t_user g on g.id=f.id2 WHERE a.id1 != 0 AND a.type = '4' GROUP BY f.id2, g.name ) e ON e.userid = b.id )d on t.id=d.userid"
                + " where 1=1 " + content + " )a )as s WHERE yccs!=0 and yccs!=yxcs";
        List<Map<String, Object>> list = findBySql(sql, null, Map.class);
        return list;
    }

    /**
     * 查询设置班次的企业
     */
    public List<Map<String, String>> qyidslist() {
        String sql = "SELECT DISTINCT a.id1 qyid FROM yhpc_checkplan a LEFT JOIN bis_enterprise bis ON bis.id = a.id1 WHERE bis.s3 = 0";
        List<Map<String, String>> list = findBySql(sql, null, Map.class);
        return list;
    }
}
