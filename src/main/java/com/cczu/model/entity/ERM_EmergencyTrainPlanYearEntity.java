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
 * @ClassName: ERM_EmergencyTrainPlanYearEntity
 * @Description: 应急资源管理_年度应急救援技能训练计划
 * @author ll
 * @date 2019年5月24日
 *
 */
@Entity
@Table(name="erm_emergencytrainplanyear")
public class ERM_EmergencyTrainPlanYearEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -7942863682398738928L;

	@Column(name = "qyid", nullable = true, length = 8)
	@Setter
	@Getter
	public Long qyid;//企业id

	@Column(name = "depid", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long depid;//部门ID

	@Column(name = "m1", nullable = true, length = 1000)
	@Setter
	@Getter
	private String M1;//训练内容

	@Column(name = "m1_2", nullable = true, length = 50)
	@Setter
	@Getter
	private String M1_2;//组训方式

	@Column(name = "m1_3", nullable = true, length = 100)
	@Setter
	@Getter	
	private String M1_3;//组训责任人

	@Column(name = "m2", nullable = true, length = 1000)
	@Setter
	@Getter
	private String M2;//训练内容

	@Column(name = "m2_2", nullable = true, length = 50)
	@Setter
	@Getter
	private String M2_2;//组训方式

	@Column(name = "m2_3", nullable = true, length = 100)
	@Setter
	@Getter
	private String M2_3;//组训责任人

	@Column(name = "m3", nullable = true, length = 1000)
	@Setter
	@Getter
	private String M3;//训练内容

	@Column(name = "m3_2", nullable = true, length = 50)
	@Setter
	@Getter
	private String M3_2;//组训方式

	@Column(name = "m3_3", nullable = true, length = 100)
	@Setter
	@Getter
	private String M3_3;//组训责任人

	@Column(name = "m4", nullable = true, length = 1000)
	@Setter
	@Getter
	private String M4;//训练内容

	@Column(name = "m4_2", nullable = true, length = 50)
	@Setter
	@Getter
	private String M4_2;//组训方式

	@Column(name = "m4_3", nullable = true, length = 100)
	@Setter
	@Getter
	private String M4_3;//组训责任人

	@Column(name = "m5", nullable = true, length = 1000)
	@Setter
	@Getter
	private String M5;//训练内容

	@Column(name = "m5_2", nullable = true, length = 50)
	@Setter
	@Getter
	private String M5_2;//组训方式

	@Column(name = "m5_3", nullable = true, length = 100)
	@Setter
	@Getter
	private String M5_3;//组训责任人

	@Column(name = "m6", nullable = true, length = 1000)
	@Setter
	@Getter
	private String M6;//训练内容

	@Column(name = "m6_2", nullable = true, length = 50)
	@Setter
	@Getter
	private String M6_2;//组训方式

	@Column(name = "m6_3", nullable = true, length = 100)
	@Setter
	@Getter
	private String M6_3;//组训责任人

	@Column(name = "m7", nullable = true, length = 1000)
	@Setter
	@Getter
	private String M7;//训练内容

	@Column(name = "m7_2", nullable = true, length = 50)
	@Setter
	@Getter
	private String M7_2;//组训方式

	@Column(name = "m7_3", nullable = true, length = 100)
	@Setter
	@Getter
	private String M7_3;//组训责任人

	@Column(name = "m8", nullable = true, length = 1000)
	@Setter
	@Getter
	private String M8;//训练内容

	@Column(name = "m8_2", nullable = true, length = 50)
	@Setter
	@Getter
	private String M8_2;//组训方式

	@Column(name = "m8_3", nullable = true, length = 100)
	@Setter
	@Getter
	private String M8_3;//组训责任人

	@Column(name = "m9", nullable = true, length = 1000)
	@Setter
	@Getter
	private String M9;//训练内容

	@Column(name = "m9_2", nullable = true, length = 50)
	@Setter
	@Getter
	private String M9_2;//组训方式

	@Column(name = "m9_3", nullable = true, length = 100)
	@Setter
	@Getter
	private String M9_3;//组训责任人

	@Column(name = "m10", nullable = true, length = 1000)
	@Setter
	@Getter
	private String M10;//训练内容

	@Column(name = "m10_2", nullable = true, length = 50)
	@Setter
	@Getter
	private String M10_2;//组训方式

	@Column(name = "m10_3", nullable = true, length = 100)
	@Setter
	@Getter
	private String M10_3;//组训责任人

	@Column(name = "m11", nullable = true, length = 1000)
	@Setter
	@Getter
	private String M11;//训练内容

	@Column(name = "m11_2", nullable = true, length = 50)
	@Setter
	@Getter
	private String M11_2;//组训方式

	@Column(name = "m11_3", nullable = true, length = 100)
	@Setter
	@Getter
	private String M11_3;//组训责任人

	@Column(name = "m12", nullable = true, length = 1000)
	@Setter
	@Getter
	private String M12;//训练内容

	@Column(name = "m12_2", nullable = true, length = 50)
	@Setter
	@Getter
	private String M12_2;//组训方式

	@Column(name = "m12_3", nullable = true, length = 100)
	@Setter
	@Getter
	private String M12_3;//组训责任人

	@Column(name = "m13", nullable = true, length = 1000)
	@Setter
	@Getter
	private String M13;//要求

	@Column(name = "m14", nullable = true, length = 50)
	@Setter
	@Getter
	private String M14;//制表人

	@Column(name = "m15", nullable = true, length = 50)
	@Setter
	@Getter
	private String M15;//批准人
}
