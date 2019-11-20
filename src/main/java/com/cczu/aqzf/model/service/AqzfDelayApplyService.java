package com.cczu.aqzf.model.service;

import com.cczu.aqzf.model.dao.AqzfDelayApplyDao;
import com.cczu.aqzf.model.entity.AQZF_DelayApplyEntity;
import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.service.BaseService;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 安全执法_检查记录Service
 */
@Transactional(readOnly = true)
@Service
public class AqzfDelayApplyService extends BaseService<AQZF_DelayApplyEntity, Long> {
    @Autowired
    AqzfDelayApplyDao applyDao;

    @Override
    public HibernateDao<AQZF_DelayApplyEntity, Long> getEntityDao() {
        return applyDao;
    }


    public Page<Map> listApplyMap(Page<Map> page, Map parmas) {
        String sql = "Select a.* from aqzf_delayapply a left join aqzf_reform b on " +
                "a.id1=b.id where 1=1 ";
        if (parmas.get("qyid") != null && parmas.get("qyid") != "") {
            sql += " and a.qyid= " + parmas.get("qyid");
        }
        return applyDao.findMapPageSql(page, sql);
    }

    public AQZF_DelayApplyEntity getById1(long id1) {
        AQZF_DelayApplyEntity en = applyDao.findUnique(Restrictions.eq("ID1", id1));
        return en;
    }


}
