package com.cczu.model.zrzh.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 自然灾害类-水旱信息快报Entity
 * @author Administrator
 *
 */
@Entity
@Table(name="zrzh_shxx")
public class ZrzhShxxEntiy extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -727465041048885286L;
	
	
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
	public String M1;//灾害区域
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M2;//灾害简况（一般、较大、重大、特别重大）
	
	@Column(name = "M2_1", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M2_1;//灾害简况（洪水、干旱）
	
	@Column(name = "M3", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M3;//灾害时间
	
	@Column(name = "M4", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M4;//灾害地点  县（市、区）
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	public String M5;//一般洪涝或发生决口或出现重大险情地段
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	public String M6;//受涝范围
	
	@Column(name = "M7", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	public String M7;//干旱程度
	
	@Column(name = "M8", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M8;//24小时连续降雨量或1小时降雨量（毫米）
	
	@Column(name = "M9", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M9;//主要内河水位（米）
	
	@Column(name = "M9_1", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M9_1;//主要内河水位（级）
	
	@Column(name = "M9_2", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	public String M9_2;//主要内河水位（描述）
	
	@Column(name = "M9_3", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M9_3;//主要内河水位（级）
	
	@Column(name = "M12", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M12;//水旱简况
	
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
