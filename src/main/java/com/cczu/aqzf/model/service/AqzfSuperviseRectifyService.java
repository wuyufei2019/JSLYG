package com.cczu.aqzf.model.service;

import com.cczu.aqzf.model.dao.AqzfSuperviseRectifyDao;
import com.cczu.aqzf.model.entity.AQZF_SuperviseRectifyEntity;
import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.service.BaseService;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 安全执法_监管整饬
 */
@Transactional(readOnly = true)
@Service
public class AqzfSuperviseRectifyService extends BaseService<AQZF_SuperviseRectifyEntity, Long> {
    @Autowired
    AqzfSuperviseRectifyDao rectifyDao;

    @Override
    public HibernateDao<AQZF_SuperviseRectifyEntity, Long> getEntityDao() {
        return rectifyDao;
    }

    public Page<Map> pageMap(Page<Map> page, Map parmas) {
        String sql = "select a.*,b.m0 number,bis.m1 qyname from aqzf_superviserectify a left join aqzf_safetycheckrecord b " +
                "on a.id1=b.id left join bis_enterprise bis on bis.id = b.id2 where 1=1";
        if (parmas.get("qyid") != null && parmas.get("qyid") != "") {
            sql += " and bis.id= " + parmas.get("qyid");
        }
        if (parmas.get("number") != null && parmas.get("number") != "") {
            sql += " and b.m0 like '%" + parmas.get("number") + "%'";
        }
        return rectifyDao.findMapPageSql(page, sql);
    }

    public AQZF_SuperviseRectifyEntity getByID1(Long id1) {
        AQZF_SuperviseRectifyEntity en = rectifyDao.findUnique(Restrictions.eq("ID1", id1));
        return en;
    }


}
