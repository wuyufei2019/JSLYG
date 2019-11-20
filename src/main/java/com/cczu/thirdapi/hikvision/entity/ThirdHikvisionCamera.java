package com.cczu.thirdapi.hikvision.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 海康威视 摄像头 列表（将萤石云所有的摄像头信息保存在本地）
 *
 * @author zpc
 * @date 2017/07/10
 */
@Entity
@Table(name = "ThirdHikvisionCamera")
public class ThirdHikvisionCamera implements Serializable {

    private static final long serialVersionUID = -3377886031555894677L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, columnDefinition = "bigint")
    @Setter
    @Getter
    public long ID;//自动编号

    @Column(name = "qyid")
    @Setter
    @Getter
    public Long qyid;//企业id

    @Column(name = "deviceSerial", columnDefinition = "varchar(50)")
    @Setter
    @Getter
    public String deviceSerial;//设备序列号

    @Column(name = "deviceName", columnDefinition = "varchar(100)")
    @Setter
    @Getter
    public String deviceName;//设备名称

    @Column(name = "channelNo", columnDefinition = "varchar(50)")
    @Setter
    @Getter
    public Integer channelNo;//通道号

    @Column(name = "channelName", columnDefinition = "varchar(50)")
    @Setter
    @Getter
    public String channelName;//通道名


    @Column(name = "picUrl", columnDefinition = "varchar(100)")
    @Setter
    @Getter
    public String picUrl;//封面

    @Column(name = "hls", columnDefinition = "varchar(100)")
    @Setter
    @Getter
    public String hls;//HLS流畅直播地址
    @Column(name = "hlsHd", columnDefinition = "varchar(100)")
    @Setter
    @Getter
    public String hlsHd;//HLS高清直播地址
    @Column(name = "rtmp", columnDefinition = "varchar(100)")
    @Setter
    @Getter
    public String rtmp;//rtmp直播地址
    @Column(name = "rtmpHd", columnDefinition = "varchar(100)")
    @Setter
    @Getter
    public String rtmpHd;//rtmpHd直播地址

    public ThirdHikvisionCamera() {
    }

    public ThirdHikvisionCamera(String deviceSerial, Integer channelNo) {
        this.deviceSerial = deviceSerial;
        this.channelNo = channelNo;
    }
}
