package com.cczu.aqzf.model.entity;

import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 安全生产执法--违法行为库
 * @author zpc
 * @date 2017年12月9日
 */
@Entity
@Table(name="aqzf_wfxwtwo")
public class AQZF_WfxwTwoEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2739094661745108104L;

	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID1;//添加人ID

	@Column(name = "M1", nullable = true, columnDefinition="varchar(400)")
	@Setter
	@Getter
	private String M1;//违法行为

	@Column(name = "M2", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M2;//违反条款

	@Column(name = "M3", nullable = true, columnDefinition="varchar(4000)")
	@Setter
	@Getter	
	private String M3;//违反条款详情

	@Column(name = "M4", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M4;//处罚依据
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(4000)")
	@Setter
	@Getter	
	private String M5;//处罚标准
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M6;//分类
	
	@Column(name = "M7", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M7;//自由裁量依据
 
}
