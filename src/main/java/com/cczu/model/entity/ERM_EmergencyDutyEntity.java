package com.cczu.model.entity;

import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 
 * @ClassName: ERM_EmergencyDutyEntity
 * @Description: 应急资源管理_应急值守
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="erm_emergencyduty")
public class ERM_EmergencyDutyEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -7333410519348880390L;


	@Column(name = "planname", length = 100)
	@Setter
	@Getter
	private String planName;//计划名称

	@Column(name = "lister", length = 50)
	@Setter
	@Getter
	private String lister;//制表人

	@Column(name = "reviewer", length = 50)
	@Setter
	@Getter
	private String reviewer;//审批人

	@Column(name = "starttime")
	@Setter
	@Getter
	private Timestamp starttime;//时间

	@Column(name = "endtime")
	@Setter
	@Getter
	private Timestamp endtime;//时间

    @Column(name = "require", length = 500)
    @Setter
    @Getter
    private String require;//要求


}
