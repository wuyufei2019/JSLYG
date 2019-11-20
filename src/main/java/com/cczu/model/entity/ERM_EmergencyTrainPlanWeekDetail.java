package com.cczu.model.entity;

import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 
 * @ClassName: ERM_EmergencyTrainPlanWeekDetail
 * @Description: 应急资源管理_应急救援训练周计划-中间表
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="erm_emergencytrainplanweekdetail")
public class ERM_EmergencyTrainPlanWeekDetail  {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -7333410519348880390L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号

	@Column(name = "planId")
	@Setter
	@Getter
	private Long planId;//计划id

	@Column(name = "time")
	@Setter
	@Getter
	private Timestamp time;//时间

	@Column(name = "time1")
	@Setter
	@Getter
	private String time1;//上午 ，下午

	@Column(name = "hours")
	@Setter
	@Getter
	private Integer hours;//用时

	@Column(name = "content")
	@Setter
	@Getter
	private String content;//训练内容

	@Column(name = "address")
	@Setter
	@Getter
	private String address;//训练地点

	@Column(name = "persons")
	@Setter
	@Getter
	private String persons;//参加人员

	@Column(name = "duty")
	@Setter
	@Getter
	private String duty;//组训责任人

	@Column(name = "situation")
	@Setter
	@Getter
	private String situation;//落实情况

}
