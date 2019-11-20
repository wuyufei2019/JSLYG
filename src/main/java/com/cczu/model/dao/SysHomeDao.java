package com.cczu.model.dao;

import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 首页获取展示数据jsonDAO
 *
 * @author jason
 * @date 2017年8月3日
 */
@Repository("SysHomeDao")
public class SysHomeDao extends BaseDao<Map, Long> {

    /**
     * 查询数据库中保存的记录 （在数据库中只有一条）
     *
     * @return
     */
    public List<Object> findInfo(Map<String, Object> mapData) {
        StringBuffer sbr = new StringBuffer();
        ShiroUser user = UserUtil.getCurrentShiroUser();
        long userid = user.getId();
        String content = " AND bis.id2 like'" + mapData.get("xzqy") + "%'";
        if (mapData.get("jglx") != null && mapData.get("jglx") != "") {
            content = content + " AND bis.m36='" + mapData.get("jglx") + "' ";
        }
        //企业数量
        sbr.append("SELECT COUNT(1)  FROM bis_enterprise bis WHERE bis.S3=0 and bis.M1 is not null" + content);
        sbr.append(" union all ");
        sbr.append("SELECT COUNT(1)  FROM bis_enterprise bis WHERE bis.S3=0 and bis.m36=1 and bis.M1 is not null" + content);
        sbr.append(" union all ");
        sbr.append("SELECT COUNT(1)  FROM bis_enterprise bis WHERE bis.S3=0 and bis.m36=2 and bis.M1 is not null" + content);
        sbr.append(" union all ");

        sbr.append("SELECT COUNT(1)  FROM bis_enterprise bis WHERE bis.S3=0 and (bis.m36!=2 and bis.m36!=1) and bis.M1 is not null" + content);
        sbr.append(" union all ");
        //风险点总数
        sbr.append(" SELECT COUNT(1) FROM fxgk_accidentrisk a LEFT JOIN bis_enterprise bis ON bis.id = a.id1 WHERE a.s3 = 0 and bis.s3 = 0 " + content);
        sbr.append(" union all");
        //两重点一重大
        //物料
        sbr.append(" SELECT COUNT(1)  FROM bis_mat a left join bis_enterprise bis   on bis.id=a.id1  WHERE a.s3=0  and bis.s3=0  AND a.M12 = '1'" + content);
        sbr.append(" union all ");
        //高危工艺
        sbr.append(" SELECT COUNT(1) FROM bis_dangerprocess b left join bis_enterprise bis on bis.id=b.id1 and bis.s3=0 left join t_dict on t_dict.value=b.m1 where b.S3=0 and bis.s3=0 " + content);
        sbr.append(" union all");
        //重大危险源
        sbr.append(" SELECT COUNT(DISTINCT h.id1) sum FROM bis_hazard h LEFT JOIN bis_enterprise bis on h.id1=bis.id WHERE h.s3=0 AND 1=1 " + content);
        sbr.append(" union all");
        //风险点巡查  上线企业数
        sbr.append(" select count(distinct s.id1) from ( (select a.id1 from fxgk_accidentrisk a where a.s3=0 and ( a.rfid is not null or a.bindcontent is not null ) ) union ( select b.id1 from yhpc_checkpoint b where b.usetype=2 and ( b.rfid is not null or b.bindcontent is not null ) ) ) s left join bis_enterprise bis on s.id1=bis.id where bis.S3=0 and bis.M1 is not null " + content);
        sbr.append(" union all");
        //风险点巡查  巡查记录数
        sbr.append(" SELECT COUNT(s.qyid) FROM (SELECT a.id,a.checkpoint_id,b.id jcdid,'fxd' type,b.id1 qyid FROM yhpc_checkresult a LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '1' AND b.s3 = 0 UNION SELECT a.id,a.checkpoint_id,b.id jcdid,'yhd' type,b.id1 qyid FROM yhpc_checkresult a LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '2' and b.usetype = '2' ) s LEFT JOIN bis_enterprise bis ON bis.id = s.qyid WHERE bis.s3 = 0 " + content);
        sbr.append(" union all");
        //隐患排查(总数)
        sbr.append(" SELECT COUNT(1) FROM (SELECT a.id,b.m1 jcdname,'1' type FROM yhpc_checkhiddeninfo a LEFT JOIN fxgk_accidentrisk b ON b.id = a.pointid WHERE a.checkpointtype = '1' and a.dangerorigin='1' AND b.s3 = 0 UNION SELECT a.id,b.name jcdname,'2' type FROM yhpc_checkhiddeninfo a LEFT JOIN yhpc_checkpoint b ON b.id = a.pointid WHERE a.checkpointtype = '2' and a.dangerorigin='1' and b.usetype='2') a LEFT JOIN yhpc_checkhiddeninfo x ON x.id = a.id LEFT JOIN bis_enterprise bis on bis.id=x.qyid where bis.s3=0 " + content);
        sbr.append(" union all");
        //隐患排查(已整改)
        sbr.append(" SELECT COUNT(1) FROM (SELECT a.id,b.m1 jcdname,'1' type FROM yhpc_checkhiddeninfo a LEFT JOIN fxgk_accidentrisk b ON b.id = a.pointid WHERE a.checkpointtype = '1' and a.dangerorigin='1' AND b.s3 = 0 UNION SELECT a.id,b.name jcdname,'2' type FROM yhpc_checkhiddeninfo a LEFT JOIN yhpc_checkpoint b ON b.id = a.pointid WHERE a.checkpointtype = '2' and a.dangerorigin='1' and b.usetype='2') a LEFT JOIN yhpc_checkhiddeninfo x ON x.id = a.id LEFT JOIN bis_enterprise bis on bis.id=x.qyid where bis.s3=0 AND x.dangerstatus = '3' " + content);
        sbr.append(" union all");
        //隐患排查（未整改）
        sbr.append(" SELECT COUNT(1) FROM (SELECT a.id,b.m1 jcdname,'1' type FROM yhpc_checkhiddeninfo a LEFT JOIN fxgk_accidentrisk b ON b.id = a.pointid WHERE a.checkpointtype = '1' and a.dangerorigin='1' AND b.s3 = 0 UNION SELECT a.id,b.name jcdname,'2' type FROM yhpc_checkhiddeninfo a LEFT JOIN yhpc_checkpoint b ON b.id = a.pointid WHERE a.checkpointtype = '2' and a.dangerorigin='1' and b.usetype='2') a LEFT JOIN yhpc_checkhiddeninfo x ON x.id = a.id LEFT JOIN bis_enterprise bis on bis.id=x.qyid where bis.s3=0 AND x.dangerstatus != '3' " + content);
        sbr.append(" union all");
        //网格监管
        //网格员总数（安监用户数）
        sbr.append(" SELECT COUNT(1) FROM t_user where usertype = '0' AND xzqy like'" + user.getXzqy() + "%'");
        sbr.append(" union all");
        //上线网格员（有巡检记录的网格员）
        sbr.append(" SELECT  COUNT( DISTINCT userid ) from yhpc_checkresult a LEFT JOIN t_user u on u.id = a.userid where u.usertype = 0 AND xzqy like'" + user.getXzqy() + "%'");
        sbr.append(" union all");
        //网格巡查总数
        sbr.append(" SELECT COUNT(1) FROM yhpc_checkresult a LEFT JOIN yhpc_checkpoint b ON a.checkpoint_id = b.id LEFT JOIN yhpc_checkplan c ON a.checkplan_id = c.id LEFT JOIN bis_enterprise bis ON b.id1 = bis.id LEFT JOIN t_barrio e ON e.code = bis.id2 LEFT JOIN t_user f ON f.ID = a.userid WHERE a.checkpointtype = '2' AND b.usetype = '1' AND bis.s3 = 0 " + content);
        sbr.append(" union all");
        //网格巡查发现隐患数
        sbr.append(" SELECT COUNT(1) FROM yhpc_checkresult a LEFT JOIN yhpc_checkpoint b ON a.checkpoint_id = b.id LEFT JOIN yhpc_checkplan c ON a.checkplan_id = c.id LEFT JOIN bis_enterprise bis ON b.id1 = bis.id LEFT JOIN t_barrio e ON e.code = bis.id2 LEFT JOIN t_user f ON f.ID = a.userid WHERE a.checkpointtype = '2' AND b.usetype = '1' AND bis.s3 = 0 AND a.checkresult = '1'  " + content);
        sbr.append(" union all");
        //备案 （危险作业报备）（本厂）
        sbr.append(" SELECT COUNT(1) FROM dw_workapproval a left join bis_enterprise bis on a.id1=bis.id WHERE a.S3=0 " +
                "and convert(varchar(10),a.m8,120)>= convert(varchar(10),GETDATE(),120) and convert(varchar(10),a.m7,120)" +
                "<= convert(varchar(10),GETDATE(),120) and bis.S3=0 and a.M17=2 " + content);
        sbr.append(" union all");
        //备案 （危险作业报备）（外包）
        sbr.append(" SELECT COUNT(1) FROM dw_workapproval a left join bis_enterprise bis on a.id1=bis.id " +
                "WHERE a.S3=0 and convert(varchar(10),a.m8,120)>= convert(varchar(10),GETDATE(),120) and convert(varchar(10),a.m7,120)" +
                "<= convert(varchar(10),GETDATE(),120) and bis.S3=0 and a.M17=1 " + content);
        sbr.append(" union all");
        //到期提醒
        //安全培训
        sbr.append(" SELECT COUNT(1) FROM bis_safetyeducation safe left join bis_enterprise bis  on bis.id=safe.id1 where safe.S3=0 and bis.s3=0  and safe.m5<=GETDATE() " + content);
        sbr.append(" union all");
        //特种设备
        sbr.append(" SELECT COUNT(1) FROM bis_specialequipment a left join bis_enterprise bis on bis.id=a.id1 WHERE a.S3=0 AND bis.S3=0 and DATEDIFF(day, a.M10, GETDATE())>=0 " + content);
        sbr.append(" union all");
        //安全评价报告
        sbr.append(" SELECT COUNT(1) FROM aqjg_saftyrecord a LEFT JOIN bis_enterprise bis ON bis.id = a.id1 WHERE a.S3 = 0 AND bis.S3 = 0 AND a.m3 = '安全评价报告' AND DATEDIFF(day, a.expiration, GETDATE()) >= 0 " + content);
        sbr.append(" union all");
        //职业病危害检测计划
        sbr.append(" SELECT COUNT(1) FROM bis_occupharmexamreport a left join bis_enterprise bis on bis.id=a.id1 WHERE a.S3=0 AND bis.S3=0 and a.m4<=GETDATE() " + content);
        sbr.append(" union all");
        //风险管控（企业）
        //红
        sbr.append(" SELECT COUNT(1) FROM bis_enterprise bis WHERE bis.s3=0 AND bis.m44=1 " + content);
        sbr.append(" union all");
        //橙
        sbr.append(" SELECT COUNT(1) FROM bis_enterprise bis WHERE bis.s3=0 AND bis.m44=2 " + content);
        sbr.append(" union all");
        //黄
        sbr.append(" SELECT COUNT(1) FROM bis_enterprise bis WHERE bis.s3=0 AND bis.m44=3 " + content);
        sbr.append(" union all");
        //蓝
        sbr.append(" SELECT COUNT(1) FROM bis_enterprise bis WHERE bis.s3=0 AND bis.m44=4 " + content);
        sbr.append(" union all");
        //风险点数量
        //红
        sbr.append(" SELECT COUNT(1) FROM fxgk_accidentrisk a LEFT JOIN bis_enterprise bis ON bis.id = a.id1 WHERE a.s3 = 0 and bis.s3 = 0 AND a.m9=1 " + content);
        sbr.append(" union all");
        //橙
        sbr.append(" SELECT COUNT(1) FROM fxgk_accidentrisk a LEFT JOIN bis_enterprise bis ON bis.id = a.id1 WHERE a.s3 = 0 and bis.s3 = 0 AND a.m9=2 " + content);
        sbr.append(" union all");
        //黄
        sbr.append(" SELECT COUNT(1) FROM fxgk_accidentrisk a LEFT JOIN bis_enterprise bis ON bis.id = a.id1 WHERE a.s3 = 0 and bis.s3 = 0 AND a.m9=3 " + content);
        sbr.append(" union all");
        //蓝
        sbr.append(" SELECT COUNT(1) FROM fxgk_accidentrisk a LEFT JOIN bis_enterprise bis ON bis.id = a.id1 WHERE a.s3 = 0 and bis.s3 = 0 AND a.m9=4 " + content);
        sbr.append(" union all");
        //文件
        //发文总数
        sbr.append(" SELECT COUNT(1) FROM issue_securityfilerelease a left join t_user b on a.ID1=b.ID WHERE a.S3=0 AND b.xzqy like '" + user.getXzqy() + "%'");
        sbr.append(" union all");
        //接收总数
        sbr.append(" SELECT COUNT(1) FROM issue_filetransmissionreceiving WHERE S3=0 AND M1=0 AND ID2 =  " + userid);
        sbr.append(" union all");
        //已读
        sbr.append(" SELECT count(1) FROM issue_govermentfilerelease a, issue_zffiletransmissionreceiving a2, t_user b WHERE a.ID = a2.ID1 AND a2.id2 = b.id AND a.s3 = 0 AND a2.s3 = 0 AND a2.m1 = 1 AND a2.ID2 =  " + userid);
        sbr.append(" union all");
        //未读
        sbr.append(" SELECT count(1) FROM issue_govermentfilerelease a, issue_zffiletransmissionreceiving a2, t_user b WHERE a.ID = a2.ID1 AND a2.id2 = b.id AND a.s3 = 0 AND a2.s3 = 0 AND a2.m1 = 0 AND a2.ID2 =  " + userid);
        sbr.append(" union all");
        //报警信息
        //储罐报警
        sbr.append(" SELECT COUNT(1) FROM fmew_alarm a LEFT JOIN bis_enterprise bis ON bis.id = a.ID1 WHERE bis.s3=0 and a.type = '1' AND a.status =0 " + content);
        sbr.append(" union all");
        //浓度报警
        sbr.append(" SELECT COUNT(1) FROM fmew_alarm a LEFT JOIN bis_enterprise bis ON bis.id = a.ID1 WHERE bis.s3=0 and a.type = '2' AND a.status =0 " + content);
        sbr.append(" union all");
        //高危工艺报警
        sbr.append(" SELECT COUNT(1) FROM fmew_alarm a LEFT JOIN bis_enterprise bis ON bis.id = a.ID1 WHERE bis.s3=0 and a.type = '3' AND a.status =0 " + content);
        List<Object> list = findBySql(sbr.toString());
        return list;
    }

    /**
     * 可视化数据
     *
     * @return
     */
    public List<Map<String, Object>> findInfo2(Map<String, Object> mapData) {
        StringBuffer sbr = new StringBuffer();
        ShiroUser user = UserUtil.getCurrentShiroUser();
        long userid = user.getId();
        String content = " AND bis.id2 like'" + mapData.get("xzqy") + "%'";
        if (mapData.get("jglx") != null && mapData.get("jglx") != "") {
            content = content + " AND bis.m36='" + mapData.get("jglx") + "' ";
        }
        sbr.append("SELECT fxgkr,fxgkc,fxgkh,fxgkl,fxdr,fxdc,fxdh,fxdl,qysum,(wlcount+gwgycount+zdwxycount) zdsum,(bcbb+wbbb) wxzy,(cgbjsum+ndbjsum+gwgybjsum) wbsum,wgsum,fxdsum from (select");
        //企业数量
        sbr.append("(SELECT COUNT(1) FROM bis_enterprise bis WHERE bis.S3=0 and bis.M1 is not null "+ content+")qysum,(" );
        //风险点总数
        sbr.append(" SELECT COUNT(1) FROM fxgk_accidentrisk a LEFT JOIN bis_enterprise bis ON bis.id = a.id1 WHERE a.s3 = 0 and bis.s3 = 0 "+ content+")fxdsum,( " );

        //两重点一重大
        //物料
        sbr.append(" SELECT COUNT(1) FROM bis_mat a left join bis_enterprise bis   on bis.id=a.id1  WHERE a.s3=0  and bis.s3=0  AND a.M12 = '1' "+ content+")wlcount,(" );
        //高危工艺
        sbr.append(" SELECT COUNT(1) FROM bis_dangerprocess b left join bis_enterprise bis on bis.id=b.id1 and bis.s3=0 left join t_dict on t_dict.value=b.m1 where b.S3=0 and bis.s3=0 "+ content+")gwgycount,( " );
        //重大危险源
        sbr.append(" SELECT COUNT(DISTINCT h.id1)  FROM bis_hazard h LEFT JOIN bis_enterprise bis on h.id1=bis.id WHERE h.s3=0 AND 1=1 "+ content+")zdwxycount,( " );

        //网格监管
        //网格数
        sbr.append(" SELECT COUNT(*) FROM t_barrio ) wgsum,( ");

        //备案 （危险作业报备）（本厂）
        sbr.append(" SELECT COUNT(1) FROM dw_workapproval a left join bis_enterprise bis on a.id1=bis.id WHERE a.S3=0 and bis.S3=0 and a.M17=2 "+content+") bcbb,( ");
        //备案 （危险作业报备）（外包）
        sbr.append(" SELECT COUNT(1) FROM dw_workapproval a left join bis_enterprise bis on a.id1=bis.id WHERE a.S3=0 and bis.S3=0 and a.M17=1 "+content+") wbbb,( ");


        //风险管控（企业）
        //红
        sbr.append(" SELECT COUNT(1) FROM bis_enterprise bis WHERE bis.s3=0 AND bis.m44=1 "+ content+") fxgkr,( " );
        //橙
        sbr.append(" SELECT COUNT(1) FROM bis_enterprise bis WHERE bis.s3=0 AND bis.m44=2 "+ content+") fxgkc,(" );
        //黄
        sbr.append(" SELECT COUNT(1) FROM bis_enterprise bis WHERE bis.s3=0 AND bis.m44=3 "+ content+") fxgkh,( " );
        //蓝
        sbr.append(" SELECT COUNT(1) FROM bis_enterprise bis WHERE bis.s3=0 AND bis.m44=4 "+ content+") fxgkl,( " );

        //风险点数量
        //红
        sbr.append(" SELECT COUNT(1) FROM fxgk_accidentrisk a LEFT JOIN bis_enterprise bis ON bis.id = a.id1 WHERE a.s3 = 0 and bis.s3 = 0 AND a.m9=1 "+ content+") fxdr,( " );
        //橙
        sbr.append(" SELECT COUNT(1) FROM fxgk_accidentrisk a LEFT JOIN bis_enterprise bis ON bis.id = a.id1 WHERE a.s3 = 0 and bis.s3 = 0 AND a.m9=2 "+ content+") fxdc,(" );
        //黄
        sbr.append(" SELECT COUNT(1) FROM fxgk_accidentrisk a LEFT JOIN bis_enterprise bis ON bis.id = a.id1 WHERE a.s3 = 0 and bis.s3 = 0 AND a.m9=3 " + content+") fxdh,( ");
        //蓝
        sbr.append(" SELECT COUNT(1) FROM fxgk_accidentrisk a LEFT JOIN bis_enterprise bis ON bis.id = a.id1 WHERE a.s3 = 0 and bis.s3 = 0 AND a.m9=4 " + content+") fxdl,( ");

        //报警信息
        //储罐报警
        sbr.append(" SELECT COUNT(1) FROM fmew_alarm a LEFT JOIN bis_enterprise bis ON bis.id = a.ID1 WHERE bis.s3=0 and a.type = '1' AND a.status =0 "+ content+") cgbjsum,( " );
        //浓度报警
        sbr.append(" SELECT COUNT(1) FROM fmew_alarm a LEFT JOIN bis_enterprise bis ON bis.id = a.ID1 WHERE bis.s3=0 and a.type = '2' AND a.status =0 "+ content+") ndbjsum,( " );
        //高危工艺报警
        sbr.append(" SELECT COUNT(1) FROM fmew_alarm a LEFT JOIN bis_enterprise bis ON bis.id = a.ID1 WHERE bis.s3=0 and a.type = '3' AND a.status =0 "+ content+") gwgybjsum)a ");
        List<Map<String, Object>> list= findBySql(sbr.toString(),null,Map.class);
        return list;
    }

    /**
     * 企业信息
     *
     * @return
     */
    public List<Object> findQyInfo(Map<String, Object> mapData) {
        StringBuffer sbr = new StringBuffer();
        String content = "";
        if (mapData.get("qyid") != null && mapData.get("qyid") != "") {
            content = " AND bis.id=" + mapData.get("qyid");
        }
        //未读文件
        sbr.append(" SELECT COUNT(1) SUM FROM issue_securityfilerelease as a , issue_filetransmissionreceiving as a2, bis_enterprise bis where a.ID=a2.ID1 and a2.id2=bis.id and a.s3=0 and a2.s3=0 and bis.s3=0 and a2.m1 =0 AND a2.ID2 =" + mapData.get("qyid"));
        sbr.append(" union all ");
        //两重点一重大
        //物料
        sbr.append(" SELECT COUNT(1)  FROM bis_mat a left join bis_enterprise bis   on bis.id=a.id1  WHERE a.s3=0  and bis.s3=0  AND a.M12 = '1'" + content);
        sbr.append(" union all ");
        //高危工艺
        sbr.append(" SELECT COUNT(1) FROM bis_dangerprocess b left join bis_enterprise bis  on bis.id=b.id1 left join t_dict on t_dict.value=b.m1 where b.S3=0 and bis.s3=0 " + content);
        sbr.append(" union all");
        //重大危险源
        sbr.append(" SELECT COUNT(1) AS sum FROM bis_hazardidentity h left join bis_hazard hd on h.id1=hd.id LEFT JOIN bis_enterprise bis ON hd.id1 = bis.id WHERE h.s3 = 0 and hd.s3=0 AND bis.s3=0 " + content);
        sbr.append(" union all");
        //自查自报
        //检查记录（企业初检数量）
        sbr.append(" SELECT COUNT(1) FROM aqjd_checkrecord b left join aqjd_checkplan c on b.id1=c.id left join bis_enterprise bis on b.id2=bis.id WHERE b.S3=0 and c.s3=0 and bis.s3=0 and b.checkflag =0 " + content);
        sbr.append(" union all");
        //整改复查（企业复查）
        sbr.append(" SELECT COUNT(1) FROM aqjd_checkrecord b left join aqjd_checkplan c on b.id1=c.id left join bis_enterprise bis  on  b.id2=bis.id WHERE b.S3=0 and c.s3=0 and bis.s3=0 AND b.checkflag in(1,2,3) " + content);
        sbr.append(" union all");
        //隐患排查(已整改)
        sbr.append(" SELECT (SELECT COUNT(1) FROM yhpc_checkhiddeninfo x LEFT JOIN yhpc_checkresult a ON x.checkresult_id = a.id LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN t_user c ON c.ID = a.userid LEFT JOIN ( SELECT a.id, b.m1 AS jcdname, d.checktitle, d.dangerlevel AS yhjb FROM yhpc_checkresult a LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id LEFT JOIN yhpc_riskpoint_content c ON a.checkpoint_id = c.id1 LEFT JOIN yhpc_checkcontent d ON c.id2 = d.id WHERE a.checkpointtype = '1' UNION SELECT a.id, b.name AS jcdname, d.checktitle, d.dangerlevel AS yhjb FROM yhpc_checkresult a LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id LEFT JOIN yhpc_checkpoint_content c ON a.checkpoint_id = c.id1 LEFT JOIN yhpc_checkcontent d ON c.id2 = d.id WHERE a.checkpointtype = '2' AND b.usetype = '2' ) d ON d.id = a.checkpoint_id LEFT JOIN bis_enterprise bis ON bis.id = b.id1 WHERE bis.s3 = 0 AND x.dangerstatus = 1 AND x.dangerorigin = '1' " + content + ")+(SELECT COUNT(*) FROM yhpc_checkhiddeninfo c LEFT JOIN yhpc_checkpoint b ON c.pointid = b.id LEFT JOIN bis_enterprise bis ON bis.id = b.id1 LEFT JOIN t_barrio e ON e.code = bis.id2 LEFT JOIN t_user f ON f.ID = c.userid LEFT JOIN yhpc_checkcontent h ON c.checkcontent_id = h.id WHERE c.checkpointtype = 2 AND b.usetype = '1' AND bis.s3 = 0 AND c.dangerorigin = '4' AND c.dangerstatus = 1 " + content + ")");
        sbr.append(" union all");
        //隐患排查（未整改）
        sbr.append(" SELECT (SELECT COUNT(1) FROM yhpc_checkhiddeninfo x LEFT JOIN yhpc_checkresult a ON x.checkresult_id = a.id LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN t_user c ON c.ID = a.userid LEFT JOIN ( SELECT a.id, b.m1 AS jcdname, d.checktitle, d.dangerlevel AS yhjb FROM yhpc_checkresult a LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id LEFT JOIN yhpc_riskpoint_content c ON a.checkpoint_id = c.id1 LEFT JOIN yhpc_checkcontent d ON c.id2 = d.id WHERE a.checkpointtype = '1' UNION SELECT a.id, b.name AS jcdname, d.checktitle, d.dangerlevel AS yhjb FROM yhpc_checkresult a LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id LEFT JOIN yhpc_checkpoint_content c ON a.checkpoint_id = c.id1 LEFT JOIN yhpc_checkcontent d ON c.id2 = d.id WHERE a.checkpointtype = '2' AND b.usetype = '2' ) d ON d.id = a.checkpoint_id LEFT JOIN bis_enterprise bis ON bis.id = b.id1 WHERE bis.s3 = 0 AND x.dangerstatus = 0 AND x.dangerorigin = '1' " + content + ")+(SELECT COUNT(*) FROM yhpc_checkhiddeninfo c LEFT JOIN yhpc_checkpoint b ON c.pointid = b.id LEFT JOIN bis_enterprise bis ON bis.id = b.id1 LEFT JOIN t_barrio e ON e.code = bis.id2 LEFT JOIN t_user f ON f.ID = c.userid LEFT JOIN yhpc_checkcontent h ON c.checkcontent_id = h.id WHERE c.checkpointtype = 2 AND b.usetype = '1' AND bis.s3 = 0 AND c.dangerorigin = '4' AND c.dangerstatus = 0 " + content + ")");
        sbr.append(" union all");
        //备案 （危险作业报备）（本厂）
        sbr.append(" SELECT COUNT(1) FROM dw_workapproval a left join bis_enterprise bis on a.id1=bis.id WHERE a.S3=0 and bis.S3=0 and a.M17=2 " + content);
        sbr.append(" union all");
        //备案 （危险作业报备）（外包）
        sbr.append(" SELECT COUNT(1) FROM dw_workapproval a left join bis_enterprise bis on a.id1=bis.id WHERE a.S3=0 and bis.S3=0 and a.M17=1 " + content);
        sbr.append(" union all");
        //到期提醒
        //安全培训
        sbr.append(" SELECT COUNT(1) FROM bis_safetyeducation safe left join bis_enterprise bis  on bis.id=safe.id1 where safe.S3=0 and bis.s3=0  and safe.m5<=GETDATE() " + content);
        sbr.append(" union all");
        //安全设施
        sbr.append(" SELECT COUNT(1) FROM bis_specialequipment a left join bis_enterprise bis on bis.id=a.id1 WHERE a.S3=0 AND bis.S3=0 and DATEDIFF(day, a.M10, GETDATE())>=0 " + content);
        sbr.append(" union all");
        //检测计划
        sbr.append(" SELECT COUNT(1) FROM bis_occupharmexamreport a left join bis_enterprise bis on bis.id=a.id1 WHERE a.S3=0 AND bis.S3=0 and a.m4<=GETDATE() " + content);
        sbr.append(" union all");
        //风险管控（企业）
        //橙
        sbr.append(" SELECT COUNT(1) FROM fxgk_accidentrisk a LEFT JOIN bis_enterprise bis ON bis.id = a.id1 WHERE a.s3 = 0 and bis.s3 = 0 AND a.m9=2 " + content);
        sbr.append(" union all");
        //红
        sbr.append(" SELECT COUNT(1) FROM fxgk_accidentrisk a LEFT JOIN bis_enterprise bis ON bis.id = a.id1 WHERE a.s3 = 0 and bis.s3 = 0 AND a.m9=1 " + content);
        sbr.append(" union all");
        //蓝
        sbr.append(" SELECT COUNT(1) FROM fxgk_accidentrisk a LEFT JOIN bis_enterprise bis ON bis.id = a.id1 WHERE a.s3 = 0 and bis.s3 = 0 AND a.m9=4 " + content);
        sbr.append(" union all");
        //黄
        sbr.append(" SELECT COUNT(1) FROM fxgk_accidentrisk a LEFT JOIN bis_enterprise bis ON bis.id = a.id1 WHERE a.s3 = 0 and bis.s3 = 0 AND a.m9=3 " + content);
        System.out.println(sbr);
        List<Object> list = findBySql(sbr.toString());
        return list;
    }


    /**
     * 查询数据库中保存的记录 for app
     *
     * @return
     */
    public List<Object> findInfoForApp(Map<String, Object> mapData) {
        StringBuffer sbr = new StringBuffer();
        String content = " AND bis.id2 like'" + mapData.get("xzqy") + "%'";
        if (mapData.get("jglx") != null && mapData.get("jglx") != "") {
            content = content + " AND bis.m36='" + mapData.get("jglx") + "' ";
        }
        String content2 = " AND b.xzqy like '" + mapData.get("xzqy") + "%'";
        if (mapData.get("jglx") != null && mapData.get("jglx") != "") {
            content2 = content2 + " AND b.userroleflg='" + mapData.get("jglx") + "' ";
        }
        //企业数量
        sbr.append("SELECT COUNT(1)  FROM bis_enterprise bis WHERE bis.S3=0 and bis.M1 is not null" + content);
        sbr.append(" union all");
        //文件数量
        sbr.append(" SELECT COUNT(*) SUM  FROM issue_securityfilerelease a left join t_user b on a.ID1=b.ID WHERE a.S3=0" + content2);
        sbr.append(" union all");
        //两重点一重大
        //物料
        sbr.append(" SELECT COUNT(1)  FROM bis_mat a left join bis_enterprise bis   on bis.id=a.id1  WHERE a.s3=0  and bis.s3=0  AND a.M12 = '1'" + content);
        sbr.append(" union all ");
        //高危工艺
        sbr.append(" SELECT COUNT(1) FROM bis_dangerprocess b left join bis_enterprise bis  on bis.id=b.id1 and bis.s3=0 left join t_dict on t_dict.value=b.m1 where b.S3=0 and bis.id1 in(select t_user.id from t_user  where 1=1 " + content + ")");
        sbr.append(" union all");
        //重大危险源
        sbr.append(" SELECT COUNT(DISTINCT h.id) sum FROM bis_hazard h LEFT JOIN bis_enterprise bis on h.id1=bis.id WHERE h.s3=0 AND 1=1 " + content);
        sbr.append(" union all");
        //企业自查自报  检查记录（企业初检数量）
        sbr.append(" SELECT COUNT(1) FROM aqjd_checkrecord b left join aqjd_checkplan c on b.id1=c.id left join bis_enterprise bis  on  b.id2=bis.id WHERE b.S3=0 and c.s3=0 and bis.s3=0 and b.checkflag=0 " + content);
        sbr.append(" union all");
        //企业自查自报  检查记录（企业复检数量）
        sbr.append(" SELECT COUNT(1) FROM aqjd_checkrecord b left join aqjd_checkplan c on b.id1=c.id left join bis_enterprise bis  on  b.id2=bis.id WHERE b.S3=0 and c.s3=0 and bis.s3=0 and b.checkflag=1 " + content);
        sbr.append(" union all");
        //隐患排查(已整改)
        sbr.append(" SELECT COUNT(1) FROM yhpc_checkhiddeninfo x LEFT JOIN yhpc_checkresult a ON x.checkresult_id = a.id LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN t_user c ON c.ID = a.userid LEFT JOIN ( SELECT a.id, b.m1 AS jcdname, d.checktitle, d.dangerlevel AS yhjb FROM yhpc_checkresult a LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id LEFT JOIN yhpc_riskpoint_content c ON a.checkpoint_id = c.id1 LEFT JOIN yhpc_checkcontent d ON c.id2 = d.id WHERE a.checkpointtype = '1' UNION SELECT a.id, b.name AS jcdname, d.checktitle, d.dangerlevel AS yhjb FROM yhpc_checkresult a LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id LEFT JOIN yhpc_checkpoint_content c ON a.checkpoint_id = c.id1 LEFT JOIN yhpc_checkcontent d ON c.id2 = d.id WHERE a.checkpointtype = '2' AND b.usetype = '2' ) d ON d.id = a.checkpoint_id LEFT JOIN bis_enterprise bis ON bis.id = b.id1 WHERE bis.s3 = 0 AND x.dangerstatus = 1 AND x.dangerorigin = '1' " + content);
        sbr.append(" union all");
        //隐患排查（未整改）
        sbr.append(" SELECT COUNT(1) FROM yhpc_checkhiddeninfo x LEFT JOIN yhpc_checkresult a ON x.checkresult_id = a.id LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN t_user c ON c.ID = a.userid LEFT JOIN ( SELECT a.id, b.m1 AS jcdname, d.checktitle, d.dangerlevel AS yhjb FROM yhpc_checkresult a LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id LEFT JOIN yhpc_riskpoint_content c ON a.checkpoint_id = c.id1 LEFT JOIN yhpc_checkcontent d ON c.id2 = d.id WHERE a.checkpointtype = '1' UNION SELECT a.id, b.name AS jcdname, d.checktitle, d.dangerlevel AS yhjb FROM yhpc_checkresult a LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id LEFT JOIN yhpc_checkpoint_content c ON a.checkpoint_id = c.id1 LEFT JOIN yhpc_checkcontent d ON c.id2 = d.id WHERE a.checkpointtype = '2' AND b.usetype = '2' ) d ON d.id = a.checkpoint_id LEFT JOIN bis_enterprise bis ON bis.id = b.id1 WHERE bis.s3 = 0 AND x.dangerstatus = 0 AND x.dangerorigin = '1' " + content);
        sbr.append(" union all");
        //网格监管
        //网格点总数
        sbr.append(" SELECT COUNT(1) FROM yhpc_checkpoint a LEFT JOIN bis_enterprise bis ON bis.id = a.id1 WHERE bis.s3 = 0 AND a.usetype = '1'" + content);
        sbr.append(" union all");
        //未整改数
        sbr.append(" SELECT count(1) FROM yhpc_checkhiddeninfo a LEFT JOIN yhpc_checkpoint b ON b.id = a.pointid LEFT JOIN bis_enterprise bis ON bis.id = b.id1 WHERE a.checkpointtype = 2 AND b.usetype = '1' AND bis.s3 = 0 AND a.dangerstatus = '0' AND a.dangerorigin = '4' " + content);
        sbr.append(" union all");
        //备案 （危险作业报备）（本厂）
        sbr.append(" SELECT COUNT(1) FROM dw_workapproval a left join bis_enterprise bis on a.id1=bis.id WHERE a.S3=0 and bis.S3=0 and a.M17=2 " + content);
        sbr.append(" union all");
        //备案 （危险作业报备）（外包）
        sbr.append(" SELECT COUNT(1) FROM dw_workapproval a left join bis_enterprise bis on a.id1=bis.id WHERE a.S3=0 and bis.S3=0 and a.M17=1 " + content);
        sbr.append(" union all");
        //到期提醒
        //安全培训
        sbr.append(" SELECT COUNT(1) FROM bis_safetyeducation safe left join bis_enterprise bis  on bis.id=safe.id1 where safe.S3=0 and bis.s3=0  and safe.m5<=GETDATE() " + content);
        sbr.append(" union all");
        //安全设施
        sbr.append(" SELECT COUNT(1) FROM bis_specialequipment a left join bis_enterprise bis on bis.id=a.id1 WHERE a.S3=0 AND bis.S3=0 and DATEDIFF(day, a.M10, GETDATE())>=0 " + content);
        sbr.append(" union all");
        //检测计划
        sbr.append(" SELECT COUNT(1) FROM bis_occupharmexamreport a left join bis_enterprise bis on bis.id=a.id1 WHERE a.S3=0 AND bis.S3=0 and a.m4<=GETDATE() " + content);
        sbr.append(" union all");
        //风险管控（企业）
        //橙
        sbr.append(" SELECT COUNT(1) FROM bis_enterprise bis WHERE bis.s3=0 AND bis.m44=2 " + content);
        sbr.append(" union all");
        //红
        sbr.append(" SELECT COUNT(1) FROM bis_enterprise bis WHERE bis.s3=0 AND bis.m44=1 " + content);
        sbr.append(" union all");
        //蓝
        sbr.append(" SELECT COUNT(1) FROM bis_enterprise bis WHERE bis.s3=0 AND bis.m44=4 " + content);
        sbr.append(" union all");
        //黄
        sbr.append(" SELECT COUNT(1) FROM bis_enterprise bis WHERE bis.s3=0 AND bis.m44=3 " + content);
        List<Object> list = findBySql(sbr.toString());
        return list;
    }

    /**
     * 实时风险点隐患排查（本周）
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> weekyhpc(String lx) {
        String sql ="select COALESCE(sum(case when datepart(weekday,a.createtime)=2 then 1 else 0 end),0) as '周一', "
                + " COALESCE(sum(case when datepart(weekday,a.createtime)=3 then 1 else 0 end),0) as '周二', "
                + " COALESCE(sum(case when datepart(weekday,a.createtime)=4 then 1 else 0 end),0) as '周三', "
                + " COALESCE(sum(case when datepart(weekday,a.createtime)=5 then 1 else 0 end),0) as '周四', "
                + " COALESCE(sum(case when datepart(weekday,a.createtime)=6 then 1 else 0 end),0) as '周五', "
                + " COALESCE(sum(case when datepart(weekday,a.createtime)=7 then 1 else 0 end),0) as '周六', "
                + " COALESCE(sum(case when datepart(weekday,a.createtime)=1 then 1 else 0 end),0) as '周七' "
                + " from yhpc_checkresult a LEFT JOIN fxgk_accidentrisk b on a.checkpoint_id=b.id "
                + " where  a.checkpointtype=1 and a.createtime>DATEADD(wk,DATEDIFF(wk,0,getdate()),0) and a.createtime< DATEADD(wk,DATEDIFF(wk,0,getdate()),7) and b.m9="+lx
                + " GROUP BY b.m9 ";
        List<Map<String, Object>> list=findBySql(sql, null,Map.class);
        return list;
    }

    /**
     * 实时自查点隐患排查（本周）
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> weekzcd() {
        String sql ="select COALESCE(sum(case when datepart(weekday,a.createtime)=2 then 1 else 0 end),0) as '周一', "
                + " COALESCE(sum(case when datepart(weekday,a.createtime)=3 then 1 else 0 end),0) as '周二', "
                + " COALESCE(sum(case when datepart(weekday,a.createtime)=4 then 1 else 0 end),0) as '周三', "
                + " COALESCE(sum(case when datepart(weekday,a.createtime)=5 then 1 else 0 end),0) as '周四', "
                + " COALESCE(sum(case when datepart(weekday,a.createtime)=6 then 1 else 0 end),0) as '周五', "
                + " COALESCE(sum(case when datepart(weekday,a.createtime)=7 then 1 else 0 end),0) as '周六', "
                + " COALESCE(sum(case when datepart(weekday,a.createtime)=1 then 1 else 0 end),0) as '周七' "
                + " from yhpc_checkresult a LEFT JOIN yhpc_checkpoint b on a.checkpoint_id=b.id "
                + " where b.usetype=2 and a.checkpointtype=2 and a.createtime>DATEADD(wk,DATEDIFF(wk,0,getdate()),0) and a.createtime< DATEADD(wk,DATEDIFF(wk,0,getdate()),7) ";
        List<Map<String, Object>> list=findBySql(sql, null,Map.class);
        return list;
    }

    /**
     * 实时风险点隐患排查（本周）
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> weekyhzg(String lx) {
        String sql ="select COALESCE(sum(case when datepart(weekday,a.createtime)=2 then 1 else 0 end),0) as '周一', "
                + " COALESCE(sum(case when datepart(weekday,a.createtime)=3 then 1 else 0 end),0) as '周二', "
                + " COALESCE(sum(case when datepart(weekday,a.createtime)=4 then 1 else 0 end),0) as '周三', "
                + " COALESCE(sum(case when datepart(weekday,a.createtime)=5 then 1 else 0 end),0) as '周四', "
                + " COALESCE(sum(case when datepart(weekday,a.createtime)=6 then 1 else 0 end),0) as '周五', "
                + " COALESCE(sum(case when datepart(weekday,a.createtime)=7 then 1 else 0 end),0) as '周六', "
                + " COALESCE(sum(case when datepart(weekday,a.createtime)=1 then 1 else 0 end),0) as '周七' "
                + " from yhpc_checkhiddeninfo a LEFT JOIN fxgk_accidentrisk b on a.pointid=b.id "
                + " where a.dangerstatus='3' and a.checkpointtype=1 and a.createtime>DATEADD(wk,DATEDIFF(wk,0,getdate()),0) and a.createtime< DATEADD(wk,DATEDIFF(wk,0,getdate()),7) and b.m9="+lx
                + " GROUP BY b.m9 ";
        List<Map<String, Object>> list=findBySql(sql, null,Map.class);
        return list;
    }

    /**
     * 实时自查点隐患排查（本周）
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> weekzczg() {
        String sql ="select COALESCE(sum(case when datepart(weekday,a.createtime)=2 then 1 else 0 end),0) as '周一', "
                + " COALESCE(sum(case when datepart(weekday,a.createtime)=3 then 1 else 0 end),0) as '周二', "
                + " COALESCE(sum(case when datepart(weekday,a.createtime)=4 then 1 else 0 end),0) as '周三', "
                + " COALESCE(sum(case when datepart(weekday,a.createtime)=5 then 1 else 0 end),0) as '周四', "
                + " COALESCE(sum(case when datepart(weekday,a.createtime)=6 then 1 else 0 end),0) as '周五', "
                + " COALESCE(sum(case when datepart(weekday,a.createtime)=7 then 1 else 0 end),0) as '周六', "
                + " COALESCE(sum(case when datepart(weekday,a.createtime)=1 then 1 else 0 end),0) as '周七' "
                + " from yhpc_checkhiddeninfo a LEFT JOIN yhpc_checkpoint b on a.pointid=b.id "
                + " where a.dangerstatus='3' and b.usetype=2 and a.checkpointtype=2 and a.createtime>DATEADD(wk,DATEDIFF(wk,0,getdate()),0) and a.createtime< DATEADD(wk,DATEDIFF(wk,0,getdate()),7) ";
        List<Map<String, Object>> list=findBySql(sql, null,Map.class);
        return list;
    }

    /**
     * 危险作业报备统计
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> wxzybbtj(Map<String, Object> mapData) {
        String content = " AND bis.id2 like'" + mapData.get("xzqy") + "%'";
        if (mapData.get("jglx") != null && mapData.get("jglx") != "") {
            content = content + " AND bis.m36='" + mapData.get("jglx") + "' ";
        }
        String sql ="select top 9 a.s1,(case a.m1 when '1' then '动火作业' when '2' then '受限空间作业' when '3' then '管道拆卸作业' "
                + " when '4' then '盲板抽堵作业' when '5' then '高处安全作业' when '6' then '吊装安全作业' "
                + " when '7' then '临时用电安全作业' when '8' then '动土安全作业' when '9' then '断路安全作业' when '10' then '其他安全作业'  end) zylx "
                + " from dw_workapproval a "
                + " left join bis_enterprise bis on bis.id=a.id1 "
                + " WHERE a.s3=0 and bis.s3=0 "+content+" order by a.s1 desc ";
        List<Map<String, Object>> list=findBySql(sql, null,Map.class);
        return list;
    }

    /**
     * 危险作业报备走势
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> wxzybbtj2(Map<String, Object> mapData) {
        String content = " AND bis.id2 like'" + mapData.get("xzqy") + "%'";
        if (mapData.get("jglx") != null && mapData.get("jglx") != "") {
            content = content + " AND bis.m36='" + mapData.get("jglx") + "' ";
        }
        String sql ="select s.date, ISNULL(a.amount, 0) amount from "
                + " (select convert(varchar(7),dateadd(mm,number,DATEADD(month,DATEDIFF(month,0,dateadd(month,-11,getdate())),0) ),120) as [date] from master..spt_values a "
                + " where datediff(month,dateadd(month,number,DATEADD(month,DATEDIFF(month,0,dateadd(month,-11,getdate())),0) ), getdate())>=0  and number>=0 and type='p')s "
                + " LEFT JOIN(select convert(nvarchar(7),Dateadd(month,0,a.s1),23) as date,count(*) amount from dw_workapproval a"
                + " left join bis_enterprise bis on bis.id=a.id1 "
                + " where a.s3=0 and a.s1>=DATEADD(month,DATEDIFF(month,0,dateadd(month,-11,getdate())),0) "+content
                + " GROUP BY convert(nvarchar(7),Dateadd(month,0,a.s1),23) )a on s.date=a.date ORDER by s.date desc";
        List<Map<String, Object>> list=findBySql(sql, null,Map.class);
        return list;
    }

    /**
     * 大数据到期提醒
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> dqtx(Map<String, Object> mapData) {
        String content = " AND b.id2 like'" + mapData.get("xzqy") + "%'";
        if (mapData.get("jglx") != null && mapData.get("jglx") != "") {
            content = content + " AND b.m36='" + mapData.get("jglx") + "' ";
        }
        String sql ="select '职业病危害检查' as lx,COUNT(*) sum FROM bis_occupharmexamreport a  left join bis_enterprise b  on a.id1=b.id WHERE a.S3=0  and b.S3=0 and a.m4<GETDATE() "+content
                + " UNION (SELECT '安全评价报告' AS lx, COUNT(*) AS sum FROM aqjg_saftyrecord a LEFT JOIN bis_enterprise b ON b.id = a.id1 WHERE a.S3 = 0 AND b.S3 = 0 AND a.m3 = '安全评价报告' AND a.expiration < GETDATE() "+content+")"
                + " UNION (SELECT '特种设备' AS lx, COUNT(*) AS sum FROM bis_specialequipment a LEFT JOIN bis_enterprise b ON b.id = a.id1 WHERE a.S3 = 0 AND b.s3 = 0  AND a.m10 < GETDATE() "+content+")"
                + " UNION (SELECT '安全培训' AS lx, COUNT(*) AS sum FROM bis_safetyeducation a LEFT JOIN bis_enterprise b ON b.id = a.id1 WHERE a.S3 = 0 AND b.s3 = 0 AND a.m5 < GETDATE() "+content+")";
        List<Map<String, Object>> list=findBySql(sql, null,Map.class);
        return list;
    }

    /**
     * 大数据预警信息
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> alarm(Map<String, Object> mapData) {
        String content = " AND b.id2 like'" + mapData.get("xzqy") + "%'";
        if (mapData.get("jglx") != null && mapData.get("jglx") != "") {
            content = content + " AND b.m36='" + mapData.get("jglx") + "' ";
        }
        String sql ="SELECT top 9 a.colltime,(case a.type when '1' then '储罐实时监测' when '2' then '可燃有毒气体浓度' when '3' then '高危工艺' end) lx "
                +" FROM fmew_alarm a left join bis_enterprise b on b.id=a.ID1 WHERE 0=0 AND a.status like '%1%' "+content+" order by a.colltime desc";
        List<Map<String, Object>> list=findBySql(sql, null,Map.class);
        return list;
    }

/*    *//**
     * 根据易发事故类型查找风险点数量
     * @param mapData
     * @return
     *//*
    public List<Map<String, Object>> getFXDjSg(Map<String, Object> mapData) {
        String content=content(mapData);
        String sql ="SELECT count(b.m1) num,a.m9 yanse,a.m8 shigu FROM bis_enterprise b inner join fxgk_accidentrisk a on b.id=a.id1 WHERE a.S3='0' AND b.s3='0' " + content + " AND a.m8 is not null GROUP BY a.m8,a.m9 ORDER BY a.m8";
        List<Map<String, Object>> list=findBySql(sql, null,Map.class);
        return list;
    }*/
}
