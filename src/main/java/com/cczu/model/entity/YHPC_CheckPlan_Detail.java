package com.cczu.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 隐患排查---巡检班次巡检点中间表
 * @author jason
 * @date 2017年8月18日
 */
@Entity
@Table(name="yhpc_checkplan_detail")
public class YHPC_CheckPlan_Detail implements Serializable {

	private static final long serialVersionUID = 5739935339538232699L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;
	
	/**
	 * 巡检班次ID 
	 */
	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Long ID1;

	/**
	 * 巡检点ID 
	 */
	@Column(name = "pointid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Long pointid;

	/**
	 * 巡检人用户ID
	 */
	@Column(name = "userid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Integer userid;

	/**
	 * 起始时间(日：时间，周：1-7标识周一到周日，月：天，年：月)
	 */
	@Column(name = "starttime", nullable = false, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String starttime;

	/**
	 * 结束时间
	 */
	@Column(name = "endtime", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String endtime ;
 
	/**
	 * 巡查点类型（1风险点/2隐患排查点、自定义巡检点）
	 */
	@Column(name = "checkpointtype")
	@Setter
	@Getter
	private Integer checkpointtype ;

	@Column(name = "type")
	@Setter
	@Getter
	private String type ;//日周月年检
}
