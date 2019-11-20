package com.cczu.aqzf.model.entity;


import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 安全生产执法--现场检查内容
 * @author jason
 * @date 2017年7月26日
 */
@Entity
@Table(name="aqzf_safetycheckcontent")
public class AQZF_SafetyCheckContentEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7133209152728855819L;

	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID1;//检查记录ID（AQZF_SafetyCheckRecordEntity）
	
	@Column(name = "ID2", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID2;//检查内容ID（AQZF_SafetyCheckItemEntity）
	

	@Column(name = "M1", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	private Integer M1;//检查结果（1是，0否）

	@Column(name = "M2", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M2;//违法行为id

	@Column(name = "M3", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M3;//检查情况
	
	@Column(name = "M4", nullable = true, length = 1000)
	@Setter
	@Getter	
	private String M4;//附件
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M5;//需整改的问题

	@Column(name = "M6", nullable = true, columnDefinition = "varchar(10)")
	@Setter
	@Getter
	private String M6;//自由裁量类型（旧：1，新：2）
}
