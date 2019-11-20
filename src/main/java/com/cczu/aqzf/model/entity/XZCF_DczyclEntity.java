package com.cczu.aqzf.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 
 * @Description: 行政处罚-调查报告自由裁量
 * @author who
 * @date 2017年12月20日
 * 
 */
@Entity
@Table(name = "xzcf_dczycl")
public class XZCF_DczyclEntity implements Serializable {
	
	
	private static final long serialVersionUID = -1528033644109760656L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号
	
	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Long  ID1  ;//调查报告id
	
	@Column(name = "wfxwid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Long  wfxwid  ;//违法行为id
	
	@Column(name = "M1", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	private String M1;//处罚档次
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	private String M2;//具体裁量计算
	
	@Column(name = "M3", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	private String M3;//处罚金额
}
