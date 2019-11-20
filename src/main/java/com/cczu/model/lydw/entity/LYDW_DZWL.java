package com.cczu.model.lydw.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 蓝牙定位 电子围栏
 */
@Entity
@Table(name = "lydw_dzwl")
public class LYDW_DZWL implements java.io.Serializable{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false,columnDefinition="bigint")
    @Setter
    @Getter
    private long ID;//ID

    @Column(name = "name", nullable = false, columnDefinition="varchar(1000)")
    @Setter
    @Getter
    public String name;//围栏名称

    @Column(name = "floor", nullable = true, columnDefinition="varchar(10)")
    @Setter
    @Getter
    public String floor;//所在楼层

	@Column(name = "mappoint", nullable = true, columnDefinition="varchar(max)")
	@Setter
	@Getter
	public String mappoint;//围栏坐标

	@Column(name = "xbids", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	public String xbids;//围栏内的信标集合

	@Column(name = "m2", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	public String m2;//预留字段2
}
