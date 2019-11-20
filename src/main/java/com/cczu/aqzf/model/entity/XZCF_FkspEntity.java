package com.cczu.aqzf.model.entity;

import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 
 * @Description: 行政处罚-缴纳罚款审批表
 * @author who
 * @date 2018年1月15日
 * 
 */
@Entity
@Table(name = "xzcf_fksp")
public class XZCF_FkspEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "ID1", nullable = false, columnDefinition = "bigint")
	@Setter
	@Getter
	private long ID1;// （立案审批id）
	
	@Column(name = "M1", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String M1;// 编号
	
	@Column(name = "M2", nullable = true, columnDefinition = "varchar(500)")
	@Setter
	@Getter
	private String M2;// 案件名称
	
	@Column(name = "M3", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String M3;// 处罚决定书文号
	
	@Column(name = "M4", nullable = true, columnDefinition = "varchar(200)")
	@Setter
	@Getter
	private String M4;// 当事人
	
	@Column(name = "M5", nullable = true, columnDefinition = "varchar(200)")
	@Setter
	@Getter
	private String M5;// 地址
	
	@Column(name = "M6", nullable = true, columnDefinition = "varchar(MAX)")
	@Setter
	@Getter
	private String M6;// 违法事实及处罚决定
	
	@Column(name = "M7", nullable = true, columnDefinition = "varchar(1000)")
	@Setter
	@Getter
	private String M7;// 申请延期理由
	
	@Column(name = "M8", nullable = true, columnDefinition = "varchar(1000)")
	@Setter
	@Getter
	private String M8;// 承办人意见
	
	@Column(name = "M9", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String M9;// 承办人一

	@Column(name = "M10", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String M10;// 承办人二
	
	@Column(name = "M11", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M11;//承办时间
	
	@Column(name = "M12", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String M12;// 审核意见
	
	@Column(name = "M13", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String M13;// 审核人
	
	@Column(name = "M14", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M14;//审核时间
	
	@Column(name = "M15", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String M15;// 审批意见
	
	@Column(name = "M16", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String M16;// 审批人
	
	@Column(name = "M17", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M17;//审批时间
}
