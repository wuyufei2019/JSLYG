package com.cczu.aqzf.model.service;

import com.cczu.aqzf.model.dao.AqzfHiddenDealDao;
import com.cczu.aqzf.model.entity.AQZF_HiddenDealEntity;
import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.service.BaseService;
import com.cczu.util.common.Parameter;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 安全执法_企业隐患整治结果
 */
@Transactional(readOnly = true)
@Service
public class AqzfHiddenDealService extends BaseService<AQZF_HiddenDealEntity, Long> {
    @Autowired
    AqzfHiddenDealDao dealDao;

    @Override
    public HibernateDao<AQZF_HiddenDealEntity, Long> getEntityDao() {
        return dealDao;
    }

    public Page<Map> pageMap(Page<Map> page, Map parmas) {
        String sql = "select a.*,b.m0 number,bis.m1 qyname from aqzf_hiddendeal a left join aqzf_safetycheckrecord b " +
                "on a.id1=b.id left join bis_enterprise bis on bis.id = b.id2 where 1=1";
        if (parmas.get("qyid") != null && parmas.get("qyid") != "") {
            sql += " and bis.id= " + parmas.get("qyid");
        }
        if (parmas.get("number") != null && parmas.get("number") != "") {
            sql += " and b.m0 like '%" + parmas.get("number") + "%'";
        }
        return dealDao.findMapPageSql(page, sql);
    }

    public List<AQZF_HiddenDealEntity> getByID1(Long id1) {
        List<AQZF_HiddenDealEntity> list = dealDao.find(Restrictions.eq("ID1", id1));
        return list;
    }

    public List<Map<String, Object>> listHiddenMap(Long recordId) {
        String sql = "SELECT a.id,a.id2,a.m2 unlaw, a.m3 condi, a.m4 pic, a.m5 que, c.m1 jcdy, b.m2 jcnr, b.m4 , b" +
                ".m5, b.m6,a.m5 name,d.person1,d.person2,d.result,d.pic pic2,d.time from aqzf_safetycheckcontent a "
                + " left JOIN aqzf_safetycheckitem b on a.id2=b.id "
                + " left JOIN aqzf_safetycheckunit c on b.m1=c.id"
                + " left join aqzf_hiddendeal d on d.id2 =a.id "
                + " where a.id1 =:p1 and a.S3=0 and a.m1=0";
        List<Map<String, Object>> list = dealDao.findBySql(sql, new Parameter(recordId), Map.class);
        return list;
    }



}
