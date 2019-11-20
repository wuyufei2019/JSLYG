package com.cczu.aqzf.model.dao;

import com.cczu.aqzf.model.entity.XZCF_JycfInfoEntity;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Repository("YbcfCfgzDao")
public class YbcfCfgzDao extends BaseDao<XZCF_JycfInfoEntity, Long> {

    /**
     * 查询list
     *
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
        String content = content(mapData);
        String sql = "SELECT TOP " + mapData.get("pageSize") + "a.* FROM (" +
                "SELECT ROW_NUMBER() OVER(order by a.id desc) rownum,a.* FROM xzcf_jycfinfo a LEFT JOIN bis_enterprise b ON b.id = a.id1 left join t_user u on a.userid = u.id "
                + "WHERE a.s3 = 0 AND b.s3=0 " + content + ") a " +
                "WHERE rownum >" + mapData.get("pageSize") + "*(" + mapData.get("pageNo") + "-1)  ";
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
        String sql = "SELECT COUNT(*) FROM xzcf_jycfinfo a LEFT JOIN bis_enterprise b ON b.id = a.id1 left join t_user u on a.userid = u.id WHERE a.s3 = 0 and b.s3 = 0 " + content;
        List<Object> list = findBySql(sql);
        return (int) list.get(0);
    }

    /**
     * 查询条件判断
     *
     * @return
     * @throws ParseException
     */
    public String content(Map<String, Object> mapData) {
        String content = " ";
        if (mapData.get("m0") != null && mapData.get("m0") != "") {
            content = content + " AND a.m0 like '%" + mapData.get("m0") + "%' ";
        }
        if (mapData.get("m1") != null && mapData.get("m1") != "") {
            content = content + " AND a.m1 like '%" + mapData.get("m1") + "%' ";
        }
        if (mapData.get("xzqy") != null && mapData.get("xzqy") != "") {
            content = content + " AND u.xzqy like '" + mapData.get("xzqy") + "%' ";
        }
        return content;
    }

    /**
     * 根据id删除
     *
     * @param id
     */
    public void deleteInfo(long id) {
        String sql = " UPDATE xzcf_jycfinfo SET s3=1 WHERE id=" + id;
        updateBySql(sql);
    }

    /**
     * 添加信息
     *
     * @param zfry
     */
    public void addInfo(XZCF_JycfInfoEntity zfry) {
        save(zfry);
    }

    /**
     * 根据id查找信息
     *
     * @param id
     * @return
     */
    public XZCF_JycfInfoEntity findInfoById(long id) {
        XZCF_JycfInfoEntity a = find(id);
        flush();
        clear();
        return a;
    }

    /**
     * 修改
     *
     * @param zfry
     */
    public void updateInfo(XZCF_JycfInfoEntity zfry) {
        save(zfry);
    }
}
