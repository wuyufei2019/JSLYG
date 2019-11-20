package com.cczu.model.lydw.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/*
 * 蓝牙定位-设备管理-标签房间历史表实体类
 */
@Entity
@Table(name = "Pub_fileroomtimehis")
public class Pub_FileRoomTimehis implements Serializable {

	private static final long serialVersionUID = -2895729790841030699L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, columnDefinition="int")
	@Setter
	@Getter
	public int id;
	
	@Column(name = "[file]", nullable = false, columnDefinition="int")
	@Setter
	@Getter
	public int file;//标签号
	
	@Column(name = "room", nullable = false, columnDefinition="int")
	@Setter
	@Getter
	public int room;//标签所在信标区域
	
	@Column(name = "intime", nullable = false, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp intime;//载入时间
	
	@Column(name = "uptime", nullable = false, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp uptime;//更新时间
	
	@Column(name = "RSSI", nullable = false, columnDefinition="int")
	@Setter
	@Getter
	public int RSSI;//标签接收rssi值
	
	@Column(name = "tolong", nullable = false, columnDefinition="varchar(255)")
	@Setter
	@Getter
	public String tolong;//滞留时间
	
	

}
