package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: BIS_DangerProcessEntity
 * @Description: 企业基本信息-高危工艺
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="bis_dangerprocess")
public class BIS_DangerProcessEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -6955834215044303121L;

	@Column(name = "ID1", nullable = true, length = 8)
	@Setter
	@Getter
	private long ID1;//企业编号

	@Column(name = "M1", nullable = true,columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M1;//高危工艺

	@Column(name = "M2", nullable = true,columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M2;//套数	单位为t/a

	@Column(name = "M3", nullable = true,columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String M3;//备注

	@Column(name = "M4", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	private String M4;//现场照片
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	private String M5;//图纸
}
