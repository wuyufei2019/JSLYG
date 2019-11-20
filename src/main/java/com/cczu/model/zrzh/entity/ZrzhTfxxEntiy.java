package com.cczu.model.zrzh.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;
/**
 * 自然灾害类-台风信息快报Entity
 * @author Administrator
 *
 */
@Entity
@Table(name="zrzh_tfxx")
public class ZrzhTfxxEntiy extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2830728831652503830L;
	
	
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
	public String M2;//灾害简况（台风、强台风、超强台风）
	
	@Column(name = "M3", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M3;//灾害时间
	
	@Column(name = "M4", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M4;//灾害地点 县（市、区）
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M5;//受热带气旋影响小时数
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M6;//风力等级
	
	@Column(name = "M7", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M7;//阵风等级
	
	@Column(name = "M7_1", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M7_1;//是否持续（阵风等级）0否，1是
	
	@Column(name = "M8", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M8;//小时内降雨
	
	@Column(name = "M8_1", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M8_1;//降雨量（毫米）
	
	@Column(name = "M8_2", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M8_2;//以上降雨量（50、100）
	
	@Column(name = "M8_3", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M8_3;//是否持续（降雨）0否，1是
	
	@Column(name = "M9", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M9;//河流
	
	@Column(name = "M9_1", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M9_1;//警戒线、历史最高潮位、历史最高水位
	
	@Column(name = "M9_2", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M9_2;//是否超过（警戒线、历史最高潮位、历史最高水位）0否，1是
	
	@Column(name = "M10", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M10;//警戒潮位（黄色、橙色、红色）
	
	@Column(name = "M10_1", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M10_1;//是否达到（警戒潮位）0否，1是
	
	@Column(name = "M12", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M12;//台风简况
	
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
