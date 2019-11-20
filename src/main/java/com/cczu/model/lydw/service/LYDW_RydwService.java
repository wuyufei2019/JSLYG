package com.cczu.model.lydw.service;


import com.cczu.model.lydw.dao.LYDW_DzwlDao;
import com.cczu.model.lydw.dao.LYDW_RydwDao;
import com.cczu.model.lydw.entity.LYDW_DZWL;
import com.cczu.sys.comm.mapper.JsonMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 蓝牙定位-人员定位Service
 * @author jason
 * @date 2019年月9日
 */
@Transactional(readOnly=true)
@Service("LYDW_RydwService")
public class LYDW_RydwService {

	@Resource
	private LYDW_RydwDao lydw_rydwDao;
	
	/**
	 * 人员定位
	 * @return
	 */
	public String rydwData(Map<String, Object> mapData) {
		List<Map<String, Object>> list = lydw_rydwDao.rydwData(mapData);
		return JsonMapper.getInstance().toJson(list);
	}
	/**
	 * 根据部门统计在线人数
	 */
	public String totalOnlinePoeple(Map<String, Object> mapData) {
		List<Map<String, Object>> list = lydw_rydwDao.totalOnlinePoeple(mapData);
		return JsonMapper.getInstance().toJson(list);
	}

	/**
	 * 查询绑定工卡的人员
	 */
	public String getYGList(Map<String, Object> mapData) {
		List<Map<String, Object>> list = lydw_rydwDao.getYGList(mapData);
		return JsonMapper.getInstance().toJson(list);
	}

    public String getHisGjList(Map<String, Object> mapData){
        String sql="select xbwz.x,xbwz.y,xbwz.z ,CONVERT(varchar(100), ss.uptime, 20) uptime from lydw_emp_pubfile gk LEFT JOIN pub_fileroomtimehis ss on gk.fileid = ss.[file] LEFT JOIN lydw_xbgl_zb xbwz on ss.room = xbwz.xbid "+
                " where gk.empid = "+ mapData.get("ygid")+ " and ss.uptime >= '"+mapData.get("starttime")+"' and ss.uptime <= '"+mapData.get("finishtime")+"' order by ss.uptime";
        List<Map<String,Object>> list=lydw_rydwDao.findBySql(sql,null,Map.class);
        return JsonMapper.getInstance().toJson(list);
    }
}
