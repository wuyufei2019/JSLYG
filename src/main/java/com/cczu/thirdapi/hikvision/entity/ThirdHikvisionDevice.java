package com.cczu.thirdapi.hikvision.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 海康威视  设备id -- 烟花爆竹店名 映射
 *
 * @author zpc
 * @date 2017/07/10
 */
@Entity
@Table(name = "ThirdHikvisionDevice")
public class ThirdHikvisionDevice implements Serializable {

    private static final long serialVersionUID = -3377886031555894677L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, columnDefinition = "bigint")
    @Setter
    @Getter
    public long ID;//自动编号

    @Column(name = "deviceSerial", columnDefinition = "varchar(50)")
    @Setter
    @Getter
    public String deviceSerial;//设备序列号

    @Column(name = "validateCode", columnDefinition = "varchar(50)")
    @Setter
    @Getter
    public String validateCode;//设备验证码

    @Column(name = "qyname", columnDefinition = "varchar(100)")
    @Setter
    @Getter
    public String qyname;//企业名称

    @Column(name = "devicename", columnDefinition = "varchar(100)")
    @Setter
    @Getter
    public String deviceName;//设备名称

    @Column(name = "qyid")
    @Setter
    @Getter
    public Long qyid;//企业id

    @Column(name = "status")
    @Setter
    @Getter
    public int status;//状态

    @Column(name = "bind")
    @Setter
    @Getter
    public Integer bind;//萤石云是否绑定设备



}
