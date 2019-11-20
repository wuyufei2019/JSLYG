package com.cczu.aqzf.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 安全生产执法--字典
 * @author jason
 * @date 2017年8月3日
 */
@Entity
@Table(name="aqzf_dict")
public class AQZF_DictEntity  implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5888403945961535718L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号
	
	@Column(name = "M1", nullable = true, columnDefinition="varchar(400)")
	@Setter
	@Getter
	private String M1;//标签
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M2;//类型
	
	@Column(name = "M3", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M3;//类型编码
	
	@Column(name = "M4", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	private Integer M4;//排序

}
