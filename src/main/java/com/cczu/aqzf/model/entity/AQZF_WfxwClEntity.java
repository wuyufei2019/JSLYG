package com.cczu.aqzf.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 安全生产执法--违法行为裁量
 * @author zpc
 * @date 2017年1月8日
 */
@Entity
@Table(name="aqzf_wfxwcl")
public class AQZF_WfxwClEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号

	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID1;//违法行为ID

	@Column(name = "M1", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	private String M1;//处罚档次

	@Column(name = "M2", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	private String M2;//裁量幅度
	
	@Column(name = "M3", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M3;//最小E值
	
	@Column(name = "M4", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M4;//最大E值
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	private String M5;//档位（0：不涉及分档1：一档23）

	@Column(name = "M6", nullable = true, columnDefinition = "varchar(10)")
	@Setter
	@Getter
	private String M6;//自由裁量类型 (新：2)
}
