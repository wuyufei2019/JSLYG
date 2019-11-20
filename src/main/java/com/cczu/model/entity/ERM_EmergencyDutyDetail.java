package com.cczu.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author jason
 * @ClassName: ERM_EmergencyTrainPlanWeekDetail
 * @Description: 应急资源管理_值班计划-中间表
 * @date 2017年5月27日
 */
@Entity
@Table(name = "erm_emergencydutydetail")
public class ERM_EmergencyDutyDetail {

    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = -7333410519348880390L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, columnDefinition = "bigint")
    @Setter
    @Getter
    public Long ID;//编号

    @Column(name = "planId")
    @Setter
    @Getter
    private Long planId;//计划id

    @Column(name = "time")
    @Setter
    @Getter
    private Timestamp time;//时间

    @Column(name = "leader")
    @Setter
    @Getter
    private Long leader;//带班领导 id

    @Column(name = "leadername")
    @Setter
    @Getter
    private String leaderName;//带班领导 名称

    @Column(name = "persons")
    @Setter
    @Getter
    private String persons;//值班人员 ids

    @Column(name = "personnames")
    @Setter
    @Getter
    private String personNames;//值班人员 名称s

    @Column(name = "contact")
    @Setter
    @Getter
    private String contact;//值班电话


}
