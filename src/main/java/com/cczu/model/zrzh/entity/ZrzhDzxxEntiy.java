package com.cczu.model.zrzh.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;
/**
 * 自然灾害类-地震信息快报Entity
 * @author Administrator
 *
 */
@Entity
@Table(name="zrzh_dzxx")
public class ZrzhDzxxEntiy extends BaseEntity {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5237683708079225001L;

	
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
	public String M1;//灾难区域
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M2;//报告简况（一般、较大、熏大、特别重大）
	
	@Column(name = "M3", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M3;//时间
	
	@Column(name = "M4", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M4;//震级
	
	@Column(name = "M4_1", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M4_1;//震源（经度）
	
	@Column(name = "M4_2", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M4_2;//震源（纬度）
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M5;//涉险总人数
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M6;//死亡人数
	
	@Column(name = "M7", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M7;//失踪人数
	
	@Column(name = "M8", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M8;//被困人数
	
	@Column(name = "M9", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M9;//受伤人数
	
	@Column(name = "M12", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M12;//地震简况
	
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
