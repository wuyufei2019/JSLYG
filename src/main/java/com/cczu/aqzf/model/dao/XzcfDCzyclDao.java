package com.cczu.aqzf.model.dao;


import com.cczu.aqzf.model.entity.XZCF_DczyclEntity;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 调查自由裁量
 * @author zpc
 * @date 2017年12月20日
 */
@Repository("XzcfDCzyclDao")
public class XzcfDCzyclDao extends BaseDao<XZCF_DczyclEntity, Long> {
	
	/**
	 * 根据调查报告id查询
	 */
	public List<XZCF_DczyclEntity> dataGrid(Long id) {
		String sql =" SELECT a.*  from xzcf_dczycl a "
				+ " where a.id1 ="+id ;
		List<XZCF_DczyclEntity> list=findBySql(sql, null,XZCF_DczyclEntity.class);
		return list;
	}
	
	 /**
     * 根据调查报告id删除
     */
    public void deletebyId1(Long id) {
		String sql=" delete xzcf_dczycl  WHERE ID1="+id;
		updateBySql(sql);
	}
    
    /**
	 * 根据调查报告id查询详情
	 */
	public List<Map<String,Object>> findInfoByDcid(Long dcid) {
		String sql =" SELECT a.*,b.m1 wfxwname  from xzcf_dczycl a left join aqzf_wfxw b on a.wfxwid = b.id "
				+ " where b.s3 = 0 and a.id1 ="+dcid ;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	/**
	 * 新自由裁量
	 * @param dcid
	 * @return
	 */
	public List<Map<String,Object>> findInfoByDcidTwo(Long dcid) {
		String sql =" SELECT a.*,b.m1 wfxwname  from xzcf_dczycl a left join aqzf_wfxwtwo b on a.wfxwid = b.id "
				+ " where b.s3 = 0 and a.id1 ="+dcid ;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
}
