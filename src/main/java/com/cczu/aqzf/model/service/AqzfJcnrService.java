package com.cczu.aqzf.model.service;

import com.cczu.aqzf.model.dao.AqzfJcnrDao;
import com.cczu.aqzf.model.entity.AQZF_SafetyCheckContentEntity;
import com.cczu.sys.comm.utils.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * 安全执法_检查内容Service
 */
@Transactional(readOnly = true)
@Service("AqzfJcnrService")
public class AqzfJcnrService {

    @Resource
    private AqzfJcnrDao aqzfJcnrDao;

    //添加信息
    public void addInfo(AQZF_SafetyCheckContentEntity jcnr) {
        //添加检查方案
        Timestamp t = DateUtils.getSysTimestamp();
        jcnr.setS1(t);
        jcnr.setS2(t);
        jcnr.setS3(0);

        aqzfJcnrDao.save(jcnr);
    }

    //更新信息
    public void updateInfo(AQZF_SafetyCheckContentEntity jcnr) {
        Timestamp t = DateUtils.getSysTimestamp();
        jcnr.setS2(t);
        jcnr.setS3(0);
        aqzfJcnrDao.save(jcnr);
    }

    /**
     * 根据检查记录id和检查内容id获取检查内容对象
     *
     * @param id2
     * @return
     */
    public AQZF_SafetyCheckContentEntity findNr(Long id1, String id2) {
        //获取中间表字段并修改操作状态
        AQZF_SafetyCheckContentEntity a = aqzfJcnrDao.findNr(id1, id2);
        return a;

    }

    /**
     * 根据检查记录id删除存在问题
     *
     * @param id1
     * @return
     */
    public void deleteCzwt(Long id1) {
        //获取中间表字段并修改操作状态
        aqzfJcnrDao.deleteCzwt(id1);
    }

    /**
     * 根据检查记录id获取list
     *
     * @param id
     * @return
     */
    public List<AQZF_SafetyCheckContentEntity> findByJlid(Long id) {
        return aqzfJcnrDao.findByJlid(id);
    }

    public List<Map<String, Object>> findAllByJlid(Long id, String version) {
        if (version.equals("2")) {
            return aqzfJcnrDao.findAllByJlidTwo(id);
        } else {
            return aqzfJcnrDao.findAllByJlid(id);
        }
    }

    public List<Map<String, Object>> findAllByids(String ids, String version) {
        if (version.equals("2")) {
            return aqzfJcnrDao.findAllByidsTwo(ids);
        } else {
            return aqzfJcnrDao.findAllByids(ids);
        }
    }

    public List<AQZF_SafetyCheckContentEntity> listHiddenContent(Long recordId) {
        return aqzfJcnrDao.listHiddenContent(recordId);
    }
    public List<Map<String, Object>>  listHiddenMap(Long recordId) {
        return aqzfJcnrDao.listHiddenMap(recordId);
    }



    public void deleteByids(String ids) {
        aqzfJcnrDao.deleteByids(ids);
    }
}
