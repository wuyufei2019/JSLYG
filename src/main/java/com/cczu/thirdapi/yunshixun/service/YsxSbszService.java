package com.cczu.thirdapi.yunshixun.service;

import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.comm.service.BaseService;
import com.cczu.thirdapi.yunshixun.dao.YsxSbszDao;
import com.cczu.thirdapi.yunshixun.entity.ThirdYxsEquipment;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Transactional(readOnly = true)
@Service("YsxSbszService")
public class YsxSbszService extends BaseService<ThirdYxsEquipment, Long> {

    @Autowired
    private YsxSbszDao ysxSbszDao;

    @Override
    public HibernateDao<ThirdYxsEquipment, Long> getEntityDao() {
        return ysxSbszDao;
    }

    public List<Map> listMapQyname() {
        String hql = "select ID as id ,accName as text from ThirdYxsEquipment";
        List<Map> list = ysxSbszDao.createQuery(hql).
                setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        return list;
    }


}
