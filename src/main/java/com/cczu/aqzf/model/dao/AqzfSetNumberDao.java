package com.cczu.aqzf.model.dao;

import com.cczu.aqzf.model.entity.AQZF_SetNumberEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * 安全执法_设置文书编号DAO
 * @author jason
 * @date 2017年8月3日
 */
@Repository("AqzfSetNumberDao")
public class AqzfSetNumberDao extends BaseDao<AQZF_SetNumberEntity, Long> {

	
	/**
	 * 查询数据库中保存的记录 
	 * @return
	 */
	public AQZF_SetNumberEntity findInfor(){
		String sql="select a.* from aqzf_setnumber a where a.s3=0";
		List<AQZF_SetNumberEntity> list2=findBySql(sql, null, AQZF_SetNumberEntity.class);
		if(list2.size()>0){
			return list2.get(0);
		}
		else{
			AQZF_SetNumberEntity asn = new AQZF_SetNumberEntity();
			Timestamp t= DateUtils.getSysTimestamp();
			asn.setID1(0);
			asn.setS1(t);
			asn.setS2(t);
			asn.setS3(0);
			asn.setM1(1);
			asn.setM2(1);
			asn.setM6(1);
			save(asn);
			return asn;
		}
	}
	
	//还原任务将编号全部置1
	public void reductionTask(){
		String sql=" UPDATE aqzf_setnumber SET m1=1,m2=1,m6=1 ";
		updateBySql(sql);
	}
}
