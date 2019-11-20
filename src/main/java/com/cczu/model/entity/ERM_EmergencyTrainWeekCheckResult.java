package com.cczu.model.entity;

import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 
 * @ClassName: ERM_EmergencyTrainWeekCheckResult
 * @Description: 应急资源管理_应急救援训练考核记录
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="erm_emergencytrainweekcheckresult")
public class ERM_EmergencyTrainWeekCheckResult extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -7333410519348880390L;

	@Column(name = "planid")
	@Setter
	@Getter
	private Long planId;//计划id

	@Column(name = "lister", length = 50)
	@Setter
	@Getter
	private String lister;//制表人

	@Column(name = "reviewer", length = 50)
	@Setter
	@Getter
	private String reviewer;//审批人

	@Column(name = "content", length = 1000)
	@Setter
	@Getter
	private String content;//考核内容

	@Column(name = "standard", length = 1000)
	@Setter
	@Getter
	private String standard;//考核标准

	@Column(name = "note", length = 1000)
	@Setter
	@Getter
	private String note;//备注

	@Column(name = "type", length = 2)
	@Setter
	@Getter
	private String type;//1、周计划考核 2、年计划考核


}
