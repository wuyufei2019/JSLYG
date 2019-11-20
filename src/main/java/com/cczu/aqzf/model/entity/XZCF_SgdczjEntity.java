package com.cczu.aqzf.model.entity;

import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * @Description: 事故调查证据
 * @author who
 * @date 2018年1月18日
 * 
 */
@Entity
@Table(name = "xzcf_sgdczj")
public class XZCF_SgdczjEntity extends BaseEntity {
	
	private static final long serialVersionUID = 5117595642022874311L;
	
	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Long  ID1  ;//事故询问id
	
	@Column(name = "M1", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M1;//证据
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	private String M2;//照片
	
}
