package com.cczu.thirdapi.yunshixun.dao;

import com.cczu.thirdapi.yunshixun.entity.YsxUser;
import com.cczu.util.dao.BaseDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("YsxUserDao")
public class YsxUserDao extends BaseDao<YsxUser, Long> {

}
