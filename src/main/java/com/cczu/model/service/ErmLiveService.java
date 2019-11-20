package com.cczu.model.service;

import com.cczu.model.dao.ErmLiveDao;
import com.cczu.model.entity.ERM_EmergencyLiveEntity;
import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.service.BaseService;
import com.cczu.util.common.StringUtils;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional(readOnly = true)
@Service
public class ErmLiveService extends BaseService<ERM_EmergencyLiveEntity, Long> {

    @Autowired
    ErmLiveDao liveDao;

    @Override
    public HibernateDao<ERM_EmergencyLiveEntity, Long> getEntityDao() {
        return liveDao;
    }


    //正在直播的房间
    public List<ERM_EmergencyLiveEntity> listLiveOnQy(Map<String, Object> map) {
        String sql = "select a.* from erm_emergencylive a left join bis_enterprise b on b.id = a.qyid where a.type=1 " +
                "and a.live=1 ";
        sql += StringUtils.qyQueryJGLX(map, "b");
        List<ERM_EmergencyLiveEntity> lives = liveDao.findBySql(sql, null, ERM_EmergencyLiveEntity.class);
        return lives;
    }

    public List<ERM_EmergencyLiveEntity> listLiveOnZf(String xzqy) {
        List<ERM_EmergencyLiveEntity> lives = liveDao.find(Restrictions.eq("live", 1),
                Restrictions.eq("type", 0),
                Restrictions.like("xzqy", xzqy, MatchMode.START));
        return lives;
    }

    public List<ERM_EmergencyLiveEntity> listLiveOnPub(String xzqy) {
        List<ERM_EmergencyLiveEntity> lives = liveDao.find(Restrictions.eq("live", 1),
                Restrictions.eq("type", 2), Restrictions.like("xzqy", xzqy, MatchMode.START));
        return lives;
    }


    public ERM_EmergencyLiveEntity getByUUid(String uuid) {
        ERM_EmergencyLiveEntity em = liveDao.findUnique(Restrictions.eq("uuid", uuid));
        return em;
    }

    public void updateLive(Integer state, String uuid) {
        String hql = "update ERM_EmergencyLiveEntity set live =?0 where uuid =?1";
        liveDao.batchExecute(hql, state, uuid);
    }

    public Page<Map> pageQy(Page<Map> page, Map<String, Object> mapData) {
        StringBuffer sql = new StringBuffer("SELECT a.* ,b.m1 from erm_emergencylive a left join bis_enterprise b " +
                "on b.id= a.qyid where type=1 ");
        sql.append(StringUtils.qyQueryJGLX(mapData, "b"));
        sql.append(getStr(mapData));
        sql.append(" order by b.id,a.id ");
        Page<Map> result = liveDao.findMapPageSql(page, sql.toString());
        return result;
    }

    public Page<Map> pageZf(Page<Map> page, Map<String, Object> mapData) {
        StringBuffer sql = new StringBuffer("SELECT a.* ,b.m1 from erm_emergencylive a left join t_barrio b " +
                "on b.code= a.xzqy where type= " + mapData.get("type"));
        if (mapData.get("xzqy") != null && mapData.get("xzqy") != "") {
            sql.append(" and a.xzqy like '" + mapData.get("xzqy") + "%'");
        }
        sql.append(getStr(mapData));
        Page<Map> result = liveDao.findMapPageSql(page, sql.toString());
        return result;
    }

    private String getStr(Map<String, Object> mapData) {
        String sql = "";
        if (mapData.get("title") != null && mapData.get("title") != "") {
            sql += " and a.title like '%" + mapData.get("title") + "%'";
        }
        if (mapData.get("live") != null && mapData.get("live") != "") {
            sql += " and a.live = " + mapData.get("live");
        }
        return sql;
    }


}
