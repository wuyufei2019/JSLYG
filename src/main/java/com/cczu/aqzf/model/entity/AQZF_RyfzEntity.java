package com.cczu.aqzf.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 安全生产执法--执法人员分组
 * @author zpc
 * @date 2018年2月24日
 */
@Entity
@Table(name="aqzf_ryfz")
public class AQZF_RyfzEntity implements Serializable{

	private static final long serialVersionUID = 6322130668360738922L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号
	
	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID1; //创建者id
	
	@Column(name = "M1", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M1;//组名称

}
