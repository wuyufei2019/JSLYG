package com.cczu.model.sgzn.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;
/**
 * 事故灾难-火灾事故信息快报Entity
 * @author Administrator
 *
 */
@Entity
@Table(name="sgzn_hzsg")
public class SgznHzsgEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8080448485437202066L;
	
	
	@Column(name = "M0", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	public String M0;//报告单位
	
	@Column(name = "M0_1", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M0_1;//报告时间
	
	@Column(name = "M1", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	public String M1;//事故单位名称
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M2;//报告简况（火灾事故）
	
	@Column(name = "M3", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M3;//事故时间
	
	@Column(name = "M4", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	public String M4;//地点（县）
	
	@Column(name = "M4_1", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	public String M4_1;//地点（镇）
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M6;//死亡人数
	
	@Column(name = "M7", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M7;//失踪人数
	
	@Column(name = "M9", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M9;//受伤人
	
	@Column(name = "M11", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M11;//经济损失
	
	@Column(name = "M12", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M12;//事故简况
	
	@Column(name = "M13", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M13;//应对处置情况
	
	@Column(name = "M14", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M14;//备注
	
	@Column(name = "M15", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M15;//报告人
	
	@Column(name = "M16", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M16;//联系电话
	
	
	
	
	
	
	
	
	
	
	
}
