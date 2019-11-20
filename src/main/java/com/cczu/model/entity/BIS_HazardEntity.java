package com.cczu.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: BIS_HazardEntity
 * @Description: 企业基本信息-重大危险源-重大危险源信息
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="bis_hazard")
public class BIS_HazardEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -2272338633536100579L;

	@Column(name = "ID1", nullable = true,columnDefinition="bigint")
	@Setter
	@Getter
	private long ID1;//企业编号

	@Column(name = "M1", nullable = true,columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M1;//重大危险源级别
	
	@Column(name = "M2", nullable = true,columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M2;//R值

	@Column(name = "M3", nullable = true,columnDefinition="int")
	@Setter
	@Getter	
	private Integer M3;//厂区人数
	
	@Column(name = "M4", nullable = true,columnDefinition="int")
	@Setter
	@Getter	
	private Integer M4;//是否有安全监控预警系统( 0/1  否/是  )
	
	@Column(name = "M5", nullable = true,columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M5;//主要危险性（选）

	
	@Column(name = "M6", nullable = true,columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M6;//联系人
	
	
	@Column(name = "M7", nullable = true,columnDefinition="varchar(30)")
	@Setter
	@Getter	
	private String M7;//联系电话

	
	@Column(name = "M8", nullable = true,columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String M8;//安全监控措施
	
	@Column(name = "M9", nullable = true,columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String M9;//重大危险源计算Q值
	@Column(name = "M9_1", nullable = true,columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String M9_1;//增加暴露人数
	@Column(name = "M10", nullable = true,columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String M10;//易导致事故类型
	
	@Column(name = "M11", nullable = true,columnDefinition="varchar(2000)")
	@Setter
	@Getter	
	private String M11;//照片

	@Column(name = "M12", nullable = true,columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M12;//重大危险源名称

}
