package com.cczu.model.service.impl;

import com.cczu.model.dao.IAqpxCjxxDao;
import com.cczu.model.entity.AQPX_ExamresultsEntity;
import com.cczu.model.service.IAqpxCjxxService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;

@Transactional(readOnly=true)
@Service("AqpxCjxxService")
public class AqpxCjxxServiceImpl implements IAqpxCjxxService {

	@Resource
	private IAqpxCjxxDao aqpxcjxxdao;
	
	@Override
	public void addInfo(AQPX_ExamresultsEntity ae) {
		// TODO Auto-generated method stub
		aqpxcjxxdao.addInfo(ae);
	}

	@Override
	public void updateInfo(AQPX_ExamresultsEntity ae) {
		// TODO Auto-generated method stub
		aqpxcjxxdao.updateInfo(ae);
	}

	@Override
	public List<AQPX_ExamresultsEntity> getlist(Long ygid, Long kcid, Date kssj) {
		// TODO Auto-generated method stub
		return aqpxcjxxdao.getlist(ygid, kcid, kssj);
	}

}
