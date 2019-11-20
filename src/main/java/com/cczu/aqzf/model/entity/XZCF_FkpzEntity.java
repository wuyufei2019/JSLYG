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
 * @Description: 行政处罚-缴纳罚款批准书
 * @author who
 * @date 2018年1月15日
 * 
 */
@Entity
@Table(name = "xzcf_fkpz")
public class XZCF_FkpzEntity extends BaseEntity {

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
	
	@Column(name = "M2", nullable = true, columnDefinition = "varchar(200)")
	@Setter
	@Getter
	private String M2;// 当事人
	
	@Column(name = "M3", nullable = true, columnDefinition = "varchar(500)")
	@Setter
	@Getter
	private String M3;// 行政处罚
	
	@Column(name = "M4", nullable = true, columnDefinition = "varchar(100)")
	@Setter
	@Getter
	private String M4;// 罚款（大写）
	
	@Column(name = "M5", nullable = true, columnDefinition = "varchar(10)")
	@Setter
	@Getter
	private String M5;// 类型(1：延期2：分期)
	
	@Column(name = "M6", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M6;//延期截止日期
	
	@Column(name = "M7", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String M7;// 期数
	
	@Column(name = "M8", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M8;//分期截止日期
	
	@Column(name = "M9", nullable = true, columnDefinition = "varchar(100)")
	@Setter
	@Getter
	private String M9;//缴纳罚款（大写）
	
	@Column(name = "M10", nullable = true, columnDefinition = "varchar(100)")
	@Setter
	@Getter
	private String M10;// 未缴纳罚款（大写）
	
	@Column(name = "M11", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M11;//批准日期
	
}
