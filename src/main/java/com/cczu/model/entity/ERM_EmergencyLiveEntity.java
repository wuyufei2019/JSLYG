package com.cczu.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 
 * @ClassName: ERM_EmergencyLiveEntity
 * @Description: 应急资源管理_应急救援演练直播
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Data
@Table(name="erm_emergencylive")
public class ERM_EmergencyLiveEntity {

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

	@Column(name = "qyid")
	private Long qyid;//qyid

	@Column(name = "userid")
	private Integer userid;//建立者id

	@Column(name = "title",length = 200)
	private String title;//直播间名称

	@Column(name = "uuid",length = 50)
	private String uuid;//直播间id

	@Column(name = "live")
	private Integer live;//是否正在直播

	@Column(name = "describe",length = 200)
	private String describe;//直播间描述

	@Column(name = "tag",length = 200)
	private String tag;//直播间标签

	@Column(name = "xzqy",length = 50)
	private String xzqy;//行政区域

	@Column(name = "type")
	private Integer type;//类型 ，0政府 1 企业 2 公共

}
