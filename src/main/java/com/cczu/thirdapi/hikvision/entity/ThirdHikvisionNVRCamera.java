package com.cczu.thirdapi.hikvision.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 海康威视 摄像头 nvr 对应关系
 *
 * @author zpc
 * @date 2017/07/10
 */
@Entity
@Table(name = "ThirdHikvisionNVRCamera")
public class ThirdHikvisionNVRCamera implements Serializable {

    private static final long serialVersionUID = -3377886031555894677L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, columnDefinition = "bigint")
    @Setter
    @Getter
    public long ID;//自动编号

    @Column(name = "NVRDeviceSerial", columnDefinition = "varchar(50)")
    @Setter
    @Getter
    public String NVRDeviceSerial;//nvr设备序列号

    @Column(name = "CameraDeviceSerial", columnDefinition = "varchar(50)")
    @Setter
    @Getter
    public String CameraDeviceSerial;//摄像头设备序列号

    public ThirdHikvisionNVRCamera() {
    }
}
