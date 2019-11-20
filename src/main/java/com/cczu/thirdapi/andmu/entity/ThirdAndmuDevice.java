package com.cczu.thirdapi.andmu.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 和目视频  设备id -- 烟花爆竹店名 映射
 *
 * @author zpc
 * @date 2017/07/10
 */
@Entity
@Table(name = "ThirdAndmuDevice")
public class ThirdAndmuDevice implements Serializable {

    private static final long serialVersionUID = -3377886031555894677L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, columnDefinition = "bigint")
    @Setter
    @Getter
    public long ID;//自动编号

    @Column(name = "deviceId", columnDefinition = "varchar(50)")
    @Setter
    @Getter
    public String deviceId;//设备

    @Column(name = "qyname", columnDefinition = "varchar(100)")
    @Setter
    @Getter
    public String qyname;//企业名称

    @Column(name = "xzqy", columnDefinition = "varchar(50)")
    @Setter
    @Getter
    public String xzqy;//行政区域

    @Transient
    @Setter
    @Getter
    public String url;//地址

}
