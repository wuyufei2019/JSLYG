package com.cczu.aqzf.model.dao;

import com.cczu.aqzf.model.entity.AQZF_SafetyCheckContentEntity;
import com.cczu.util.common.Parameter;
import com.cczu.util.dao.BaseDao;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 安全执法_检查记录DAO
 */
@Repository("AqzfJcnrDao")
public class AqzfJcnrDao extends BaseDao<AQZF_SafetyCheckContentEntity, Long> {
    /**
     * 根据检查记录id和检查内容id获取检查内容对象
     *
     * @param id2
     * @return
     */
    public AQZF_SafetyCheckContentEntity findNr(Long id1, String id2) {
        String sql = " SELECT * from aqzf_safetycheckcontent where id1=" + id1 + " and id2=" + id2 + " and S3=0 ";
        List<AQZF_SafetyCheckContentEntity> list = findBySql(sql, null, AQZF_SafetyCheckContentEntity.class);
        return list.get(0);
    }

    /**
     * 根据检查记录id获取list
     *
     * @param id
     * @return
     */
    public List<AQZF_SafetyCheckContentEntity> findByJlid(Long id) {
        String sql = "select * from aqzf_safetycheckcontent where s3 = 0 and m1=0 and id1=" + id;
        List<AQZF_SafetyCheckContentEntity> list = findBySql(sql, null, AQZF_SafetyCheckContentEntity.class);
        return list;
    }

    /**
     * 根据id删除
     *
     * @param id
     */
    public void deleteCzwt(Long id) {
        String sql = " delete aqzf_safetycheckcontent WHERE id2=0 and id1=" + id;
        updateBySql(sql);
    }

    /**
     * 关联旧自由裁量
     * 根据检查记录id获取详情
     */
    public List<Map<String, Object>> findAllByJlid(Long id) {
        String sql = "select a.id ID,a.m3 wtms,a.m5 zgwt,b.m1,b.m2,b.m3,b.m4,b.m5,b.m6,a.m2 wfxwms from aqzf_safetycheckcontent a left join aqzf_wfxw b on cast(b.id as varchar(200)) = a.m2 where a.s3 = 0 and a.m1=0 and a.id1=" + id;
        List<Map<String, Object>> list = findBySql(sql, null, Map.class);
        List<Map<String, Object>> list1 = new ArrayList<>();
        for (Map<String, Object> map : list) {
            if (map.get("m1") == null) {
                map.put("m1", map.get("wfxwms"));
            }
            list1.add(map);
        }
        return list1;
    }

    /**
     * 关联新自由裁量
     *
     * @param id
     * @return
     */
    public List<Map<String, Object>> findAllByJlidTwo(Long id) {
        String sql = "select a.id ID,a.m3 wtms,a.m5 zgwt,b.m1,b.m2,b.m3,b.m4,b.m5,b.m6,a.m2 wfxwms from aqzf_safetycheckcontent a left join aqzf_wfxwtwo b on cast(b.id as varchar(200)) = a.m2 where a.s3 = 0 and a.m1=0 and a.id1=" + id;
        List<Map<String, Object>> list = findBySql(sql, null, Map.class);
        List<Map<String, Object>> list1 = new ArrayList<>();
        for (Map<String, Object> map : list) {
            if (map.get("m1") == null) {
                map.put("m1", map.get("wfxwms"));
            }
            list1.add(map);
        }
        return list1;
    }

    public List<Map<String, Object>> findAllByids(String ids) {
        if (ids == null || ids.equals("")) {
            return null;
        }
        String sql = "select a.id ID,a.m3 wtms,a.m5 zgwt,b.id wfid,b.m1,b.m2,b.m3,b.m4,b.m5,b.m6,b.m7,a.m2 wfxwms from aqzf_safetycheckcontent a left join aqzf_wfxw b on cast(b.id as varchar(200)) = a.m2 where a.s3 = 0 and a.m1=0 and a.id in (" + ids + ")";
        List<Map<String, Object>> list = findBySql(sql, null, Map.class);
        List<Map<String, Object>> list1 = new ArrayList<>();
        for (Map<String, Object> map : list) {
            if (map.get("m1") == null) {
                map.put("m1", map.get("wfxwms"));
            }
            list1.add(map);
        }
        return list1;
    }

    public List<Map<String, Object>> findAllByidsTwo(String ids) {
        if (ids == null || ids.equals("")) {
            return null;
        }
        String sql = "select a.id ID,a.m3 wtms,a.m5 zgwt,b.id wfid,b.m1,b.m2,b.m3,b.m4,b.m5,b.m6,b.m7,a.m2 wfxwms from aqzf_safetycheckcontent a left join aqzf_wfxwtwo b on cast(b.id as varchar(200)) = a.m2 where a.s3 = 0 and a.m1=0 and a.id in (" + ids + ")";
        List<Map<String, Object>> list = findBySql(sql, null, Map.class);
        List<Map<String, Object>> list1 = new ArrayList<>();
        for (Map<String, Object> map : list) {
            if (map.get("m1") == null) {
                map.put("m1", map.get("wfxwms"));
            }
            list1.add(map);
        }
        return list1;
    }


    public void deleteByids(String ids) {
        String sql = " delete aqzf_safetycheckcontent WHERE id in (" + ids + ")";
        updateBySql(sql);
    }

    public List<AQZF_SafetyCheckContentEntity> listHiddenContent(Long recordId) {
        List<AQZF_SafetyCheckContentEntity> list = find(Restrictions.eq("ID1", recordId),
                Restrictions.eq("S3", 0), Restrictions.eq("M1", 0));
        return list;
    }

    public List<Map<String, Object>> listHiddenMap(Long recordId) {
        String sql = "SELECT a.id,a.id2,a.m2 unlaw, a.m3 condi, a.m4 pic, a.m5 que, c.m1 jcdy, b.m2 jcnr, b.m4 , b" +
                ".m5, b.m6,a.m5 name from aqzf_safetycheckcontent a "
                + " left JOIN aqzf_safetycheckitem b on a.id2=b.id "
                + " left JOIN aqzf_safetycheckunit c on b.m1=c.id"
                + " where a.id1 =:p1 and a.S3=0 and a.m1=0";
        List<Map<String, Object>> list = findBySql(sql, new Parameter(recordId), Map.class);
        return list;
    }


}
