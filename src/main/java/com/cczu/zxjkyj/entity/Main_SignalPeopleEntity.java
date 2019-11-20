package com.cczu.zxjkyj.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 二道门人数采集
 * @author jason
 * @date 2017年6月26日
 */
@Entity
@Table(name="main_signal_people")
public class Main_SignalPeopleEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8222976296554237963L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID;//自动编号
	
	@Column(name = "ID1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID1;//企业ID
	
	@Column(name="M1", nullable=false, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String M1;//部门名称
	
	@Column(name="M2", nullable=true, columnDefinition="float")
	@Setter
	@Getter
	private Integer M2;//排序
	
	@Column(name="M3", nullable=false, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String M3;//备注
	
	@Column(name="M4", nullable=false, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String M4;//部门编号
	
	@Column(name="M5", nullable=true, columnDefinition="datetime" )
	@Setter
	@Getter
	private Timestamp M5;//采集时间
	
	@Column(name="M6", nullable=true, columnDefinition="int")
	@Setter
	@Getter
	private Integer M6;//是否上传（0未上传 1已上传  默认0
	
	
}
