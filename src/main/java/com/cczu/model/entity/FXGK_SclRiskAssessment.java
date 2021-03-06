package com.cczu.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 风险管控-风险评估SCL
 * @author XY
 *
 */

@Entity
@Table(name="fxgk_sclriskassessment")
public class FXGK_SclRiskAssessment extends BaseEntity{

	private static final long serialVersionUID = 4793560532817752303L;
	
	@Column(name = "qyid", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private long qyid;//企业ID
	
	@Column(name = "deptid", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private long deptid;//部门ID
	
	@Column(name = "M1", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M1;//工段
	
	@Column(name = "equipment", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String equipment;//装置/设备
	
	@Column(name = "analysisper", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String analysisper;//分析人
	
	@Column(name = "analysistime", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp analysistime;//分析时间
	
	@Column(name = "reviewer", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String reviewer;//审核人
	
	@Column(name = "auditor", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String auditor;//审定人
	
}
