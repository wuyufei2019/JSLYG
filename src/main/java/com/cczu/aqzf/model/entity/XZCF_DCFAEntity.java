package com.cczu.aqzf.model.entity;

import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 安全生产执法--调查方案
 * @author zpc
 * @date 2017年08月04日
 */
@Entity
@Table(name="xzcf_dcfa")
public class XZCF_DCFAEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5117595642022874311L;

	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID1;//操作人ID
	

	@Column(name = "ID3", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID3;//立案审批ID

	@Column(name = "dsperson", nullable = true, columnDefinition = "varchar(100)")
	@Setter
	@Getter
	private String dsperson;// （为了导出方便）当事人（企业名称）

	@Column(name = "M1", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M1;//立案审批编号
	
	@Column(name = "M2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M2;//调查开始时间

	@Column(name = "M3", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp M3;//调查结束时间


	@Column(name = "M4", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M4;//调查人员
	
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter	
	private String M5;//调查步骤
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter	
	private String M6;//调查报告

	@Column(name = "M7", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String M7;//部门负责人
	
	@Column(name = "M8", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String M8;//分管领导

	@Column(name = "M9", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M9;//办案部门
}
