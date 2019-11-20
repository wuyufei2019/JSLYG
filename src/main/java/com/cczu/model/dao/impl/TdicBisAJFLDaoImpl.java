package com.cczu.model.dao.impl;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.ITdicBisAJFLDao;
import com.cczu.model.entity.Tdic_BisjgflEntity;
import com.cczu.util.dao.BaseDao;

@Repository("ITdicBisAJFLDao")
public class TdicBisAJFLDaoImpl extends BaseDao<Tdic_BisjgflEntity,Long> implements ITdicBisAJFLDao {

	@Override
	public List<Tdic_BisjgflEntity> findAJFLList() {
		return findAll();
	}
	
}
