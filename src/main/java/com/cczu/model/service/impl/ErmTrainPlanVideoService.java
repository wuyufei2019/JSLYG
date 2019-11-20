package com.cczu.model.service.impl;

import com.cczu.model.dao.impl.ErmTrainPlanVideoDao;
import com.cczu.model.entity.ERM_EmergencyTrainPlanVideoEntity;
import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.comm.service.BaseService;
import com.cczu.util.common.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional(readOnly = true)
@Service
public class ErmTrainPlanVideoService extends BaseService<ERM_EmergencyTrainPlanVideoEntity,Long> {
    @Autowired
    ErmTrainPlanVideoDao ermTrainPlanVideoDao;
    @Override
    public HibernateDao<ERM_EmergencyTrainPlanVideoEntity, Long> getEntityDao() {
        return ermTrainPlanVideoDao;
    }


    /**
     * list
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> findListByMap(Map<String, Object> mapData) {
        return ermTrainPlanVideoDao.findByMap(mapData);
    }

    /**
     * 删除图片信息
     * @param id
     */
    public void deleteInfoById(long id) {
        ermTrainPlanVideoDao.deleteInfo(id);
    }

    //与页码相对应的每个列表
    public Page findPage(long currentPage,Map<String, Object> mapData) {
        long totalRecords = ermTrainPlanVideoDao.getTotalRecord();//总记录数
        Page page = new Page(currentPage, totalRecords);
        List<Map<String,Object>> clist = ermTrainPlanVideoDao.findpage(page.getStartIndex(), page.getPageSize(),mapData);
        page.setList(clist);
        return page;
    }
}
