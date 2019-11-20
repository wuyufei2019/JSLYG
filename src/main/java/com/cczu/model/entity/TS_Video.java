package com.cczu.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * 视频监控信息视频监控信息
 * @author jason
 * @date 2017年9月6日
 */
@Entity
@Table(name="ts_video")
public class TS_Video implements Serializable{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -9106735751593001193L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号
	
	@Column(name = "ID1", nullable = true,columnDefinition="bigint")
	@Setter
	@Getter
	private Long ID1;//企业编号
	
	@Column(name="name", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String name;//名称
	
	@Column(name="rtsp", nullable=true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String rtsp;//RTSP视频流
	
	@Column(name="apiaddress", nullable=true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String apiaddress;//接口请求地址
	
	@Column(name="playaddress", nullable=true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String playaddress;//视频播放地址
	
//	@Column(name="password", nullable=true, columnDefinition="varchar(50)")
//	@Setter
//	@Getter
//	private String password;//密码
	
	
	@Column(name="url", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String url;//生成视频流地址
	
	@Column(name="beizhu", nullable=true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String beizhu;//备注
	
}
