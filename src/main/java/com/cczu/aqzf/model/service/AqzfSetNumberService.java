package com.cczu.aqzf.model.service;


import com.cczu.aqzf.model.dao.AqzfSetNumberDao;
import com.cczu.aqzf.model.entity.AQZF_SetNumberEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.utils.UserUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;

/**
 * 安全执法_设置文书编号Service
 * @author jason
 * @date 2017年8月3日
 */
@Transactional(readOnly=true)
@Service("AqzfSetNumberService")
public class AqzfSetNumberService {

	@Resource
	private AqzfSetNumberDao setNumberDao;
	
	
	/**
	 * 添加或修改操作
	 * @param entity
	 */
	public void updateInfo(AQZF_SetNumberEntity entity) {
		Timestamp t= DateUtils.getSysTimestamp();
		//添加操作
		if(entity.getID()==null||entity.getID()==0){
			entity.setS1(t);
			entity.setS2(t);
			entity.setS3(0);
		}else{   //修改操作
			entity.setS2(t);
		}
		entity.setID1(UserUtil.getCurrentUser().getId());
		setNumberDao.save(entity);
	}
	
	/**
	 * 获取编号信息
	 * @return
	 */
	public AQZF_SetNumberEntity findInfor(){
		return setNumberDao.findInfor();
	}
	 
	//还原任务将编号全部置1
	public void reductionTask(){
		setNumberDao.reductionTask();
	}
}
