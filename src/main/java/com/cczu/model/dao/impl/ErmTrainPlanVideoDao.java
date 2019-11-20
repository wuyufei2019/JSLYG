package com.cczu.model.dao.impl;

import com.cczu.model.entity.ERM_EmergencyTrainPlanVideoEntity;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository("ErmTrainPlanVideoDao")
public class ErmTrainPlanVideoDao extends BaseDao<ERM_EmergencyTrainPlanVideoEntity, Long> {

    public String content(Map<String, Object> mapData) {
        String content = "";

        if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
            content = content +" AND m1 like '%"+mapData.get("m1")+"%' ";
        }
        return content;
    }

    /**
     * 根据Map查找结果集
     * @param
     * @return
     */
    public List<Map<String, Object>> findByMap(Map<String, Object> mapData) {
        String content=content(mapData);
        String sql ="SELECT * FROM erm_emergencytrainplanvideo a WHERE a.s3 = 0 " +content;
        List<Map<String, Object>> list=findBySql(sql, null,Map.class);
        return list;
    }

    /**
     * 删除视频信息
     * @param id
     */
    public void deleteInfo(Long id) {
        String sql="UPDATE erm_emergencytrainplanvideo SET s3=1 WHERE id="+id;
        updateBySql(sql);
    }

    //查询总记录数
    public long getTotalRecord() {
        String sql  = "select count(1) from erm_emergencytrainplanvideo WHERE s3 = 0";
        List<Object> list=findBySql(sql);
        return StringUtils.toLong(list.get(0));
    }

    //查询每页记录数据
    public List<Map<String,Object>> findpage(long startIndex, long pageSize,Map<String, Object> mapData) {
        String content  = content(mapData);
        String sql = "select top ("+pageSize+") * from erm_emergencytrainplanvideo where s3 = 0 "+content+"and id not in (\n" +
                "select top ("+startIndex+") id from erm_emergencytrainplanvideo WHERE s3 = 0) ";
        Object[] params = {startIndex,pageSize};
        List<Map<String, Object>> list=findBySql(sql, null,Map.class);
        return list;
    }
}
