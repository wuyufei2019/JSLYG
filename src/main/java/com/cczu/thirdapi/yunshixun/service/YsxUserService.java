package com.cczu.thirdapi.yunshixun.service;

import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.comm.service.BaseService;
import com.cczu.thirdapi.yunshixun.dao.YsxUserDao;
import com.cczu.thirdapi.yunshixun.entity.YsxUser;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional(readOnly = true)
@Service("YsxUserService")
public class YsxUserService extends BaseService<YsxUser, Long> {

    @Autowired
    private YsxUserDao ysxUserDao;

    @Override
    public HibernateDao<YsxUser, Long> getEntityDao() {
        return ysxUserDao;
    }

    public void saveAll(List<YsxUser> users, String conferId) {
        for (YsxUser user : users) {
            user.setConferId(conferId);
            ysxUserDao.save(user);
        }
        ysxUserDao.flush();
        ysxUserDao.clear();
    }

    public List<YsxUser> listSoftUserByConferId(String conferId) {
        return ysxUserDao.find(Restrictions.eq("conferId", conferId));
    }


}
