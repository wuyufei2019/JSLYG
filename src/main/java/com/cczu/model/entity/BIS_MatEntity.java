package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: BIS_MatEntity
 * @Description: 企业基本信息-企业原料信息
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="bis_mat")
public class BIS_MatEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -7293698814167710734L;

	
	@Column(name = "ID1", nullable = true, length = 8)
	@Setter
	@Getter
	private long ID1;//企业编号

	@Column(name = "M1", nullable = true, length = 1000)
	@Setter
	@Getter
	private String M1;//原料名称

	@Column(name = "M2", nullable = true )
	@Setter
	@Getter
	private Float M2;//年用量

	@Column(name = "M3", nullable = true )
	@Setter
	@Getter	
	private Float M3;//最大储量

	@Column(name = "M4", nullable = true, length = 200)
	@Setter
	@Getter	
	private String M4;//CAS号
	
	@Column(name = "M5", nullable = true, length = 100)
	@Setter
	@Getter	
	private String M5;//储存方式
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter	
	private String M6;//主要危险性
	
	@Column(name = "M7", nullable = true, length = 200)
	@Setter
	@Getter	
	private String M7;//备注
	
	@Column(name = "M8", nullable = true, length = 200)
	@Setter
	@Getter	
	private String M8;//物料类别

	@Column(name = "M9", nullable = true, length = 4)
	@Setter
	@Getter	
	private String M9;//状态

	@Column(name = "M10", nullable = true, length = 2)
	@Setter
	@Getter	
	private String M10;//是否领证    是/否
	
	@Column(name = "M11", nullable = true, length = 2)
	@Setter
	@Getter	
	private Integer M11;//标识（1原料/2产品）
	
	@Column(name = "M12", nullable = true, length = 10, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String M12;//是否为重点监管危险化学品:1:是
	
	@Column(name = "M13", nullable = true, length = 10, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String M13;//是否为剧毒化学品:1:是
	
	@Column(name = "M14", nullable = true, length = 10, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String M14;//是否为易制毒化学品:1:是
}
