package com.cczu.model.entity;

import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "erm_emergencytrainplanvideo")
public class ERM_EmergencyTrainPlanVideoEntity extends BaseEntity {

    @Column(name = "qyid", nullable = true, columnDefinition="bigint")
    @Setter
    @Getter
    public Long qyid;//企业id

    @Column(name = "m1", nullable = true, columnDefinition="varchar(100)")
    @Getter
    @Setter
    private String m1;//视频主题


    @Column(name = "m2", nullable = true, columnDefinition="varchar(200)")
    @Getter
    @Setter
    private String m2;//视频简介

    @Column(name = "m3", nullable = false, columnDefinition="varchar(1000)")
    @Getter
    @Setter
    private String m3;//上传的视频
}
