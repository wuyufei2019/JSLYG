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
 * @ClassName: XZCF_JYCFCssbEntity
 * @Description: 行政处罚-简易处罚-处罚决定
 * @author who
 * @date 2018年1月22日
 *
 */
@Entity
@Table(name="xzcf_jycfcfjd")
public class XZCF_JycfCfjdEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "ID1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long ID1;//告知书的id
	
	@Column(name = "M0", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M0;//编号
	
	@Column(name = "M1", nullable = true, columnDefinition="varchar(2)")
	@Setter
	@Getter
	private String M1;//类型（1单位2个人）
	
	//************************************单位*************************************
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M2;//被处罚单位
	
	@Column(name = "M3", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M3;//单位地址
	
	@Column(name = "M4", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String M4;//邮编
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M5;//法定代表人
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M6;//职务
	
	@Column(name = "M7", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M7;//联系电话
	
	//************************************个人***************************************
	
	@Column(name = "M8", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M8;//被处罚人
	
	@Column(name = "M9", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String M9;//性别
	
	@Column(name = "M10", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String M10;//年龄
	
	@Column(name = "M11", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M11;//身份证号
	
	@Column(name = "M12", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M12;//家庭住址
	
	@Column(name = "M13", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M13;//所在单位
	
	@Column(name = "M14", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M14;//职务
	
	@Column(name = "M15", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M15;//单位地址
	
	@Column(name = "M16", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M16;//联系电话
	
	//****************************公用******************************************
	
	@Column(name = "M17", nullable = true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter
	private String M17;//违法事实及证据
	
	@Column(name = "M18", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M18;//违法条款
	
	@Column(name = "M19", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M19;//处罚依据
	
	@Column(name = "M20", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M20;//行政处罚
	
	@Column(name = "M21", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String M21;//罚款履行方式（1：当场2：15日内）
	
	@Column(name = "M22", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String M22;// 执法人员1
	
	@Column(name = "M23", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String M23;// 执法人员2
	
	@Column(name = "M24", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M24;//处罚时间
}
