package com.cczu.zxjkyj.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 气体浓度信号采集
 * @author jason
 * @date 2017年6月26日
 */
@Entity
@Table(name="main_signal_gas")
public class Main_SignalGasEntity implements Serializable{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = -664067905704589121L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID;//自动编号
	
	@Column(name = "ID1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID1;//气体信息ID（bis_sensor  id）
	
	@Column(name="sensorno", nullable=false, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String sensorno;//位号
	
	@Column(name="level", nullable=true, columnDefinition="float")
	@Setter
	@Getter
	private Float level;//实时浓度(ppm)
	
	@Column(name="colltime", nullable=true, columnDefinition="datetime" )
	@Setter
	@Getter
	private Timestamp colltime;//采集时间
	
	@Column(name="isupload", nullable=true, columnDefinition="int")
	@Setter
	@Getter
	private Integer isupload;//是否上传（0未上传 1已上传  默认0
	
	
}
