package com.cczu.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 隐患排查---巡检班次信息
 *
 * @author jason
 * @date 2017年8月17日
 */
@Entity
@Table(name = "YHPC_CheckTeamSetting")
public class YHPC_CheckTeamSetting implements Serializable {

    private static final long serialVersionUID = -1528033644109760656L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, columnDefinition = "bigint")
    @Setter
    @Getter
    public Long ID;

    @Column(name = "ID1", nullable = false, columnDefinition = "bigint")
    @Setter
    @Getter
    public Long ID1;//班组编号

    @Column(name = "name", columnDefinition = "varchar(50)")
    @Setter
    @Getter
    public String name;//班组名称

    @Column(name = "starttime")
    @Setter
    @Getter
    private Timestamp starttime;//初始时间

    @Column(name = "endtime")
    @Setter
    @Getter
    private Timestamp endtime;//结束时间

    @Column(name = "sort")
    @Setter
    @Getter
    private Integer sort;//排序

    @Column(name = "count")
    @Setter
    @Getter
    private Integer count;//多少天组成一个循环




}
