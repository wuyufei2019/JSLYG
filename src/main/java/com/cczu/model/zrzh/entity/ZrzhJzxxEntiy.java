package com.cczu.model.zrzh.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;
/**
 * 自然灾害类-救助信息快报Entity
 * @author Administrator
 *
 */
@Entity
@Table(name="zrzh_jzxx")
public class ZrzhJzxxEntiy extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4630863129557637362L;
	
	
	
	@Column(name = "M0", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	public String M0;//报告单位
	
	@Column(name = "M0_1", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M0_1;//报告时间
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M2;//报告简况（洪涝、雨雪冰冻等）
	
	@Column(name = "M3", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M3;//灾情时间
	
	@Column(name = "M4", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M4;//地点（县）
	
	@Column(name = "M4_1", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M4_1;//地点（镇）
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M5;//等级
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M6;//死亡人数
	
	@Column(name = "M7", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M7;//紧急转移安置或需紧急生活救助人数
	
	@Column(name = "M7_1", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M7_1;//需政府救助人数
	
	@Column(name = "M10", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M10;//倒塌和严重损坏房屋数
	
	@Column(name = "M11", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M11;//占灾害发生地县（市、区）农业人口比例
	
	@Column(name = "M12", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M12;//灾害简况
	
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
