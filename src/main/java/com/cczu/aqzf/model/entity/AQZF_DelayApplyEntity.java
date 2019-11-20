package com.cczu.aqzf.model.entity;

import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 安全生产执法--整改延期申请
 *
 * @author jason
 * @date 2017年7月26日
 */
@Entity
@Table(name = "aqzf_delayapply")
public class AQZF_DelayApplyEntity extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = -2517097442171787883L;

    @Column(name = "ID1", columnDefinition = "bigint")
    @Setter
    @Getter
    public Long ID1;//责令整改id

    @Column(name = "qyid", columnDefinition = "bigint")
    @Setter
    @Getter
    public Long qyid;//企业id

    @Column(name = "unitname")
    @Setter
    @Getter
    public String unitname;//被检查单位名称

    @Column(name = "M1", columnDefinition = "datetime")
    @Setter
    @Getter
    private Timestamp M1;//延期日期至

    @Column(name = "oldtime", columnDefinition = "datetime")
    @Setter
    @Getter
    private Timestamp oldTime;//未延期的时间

    @Column(name = "M2", columnDefinition = "varchar(1000)")
    @Setter
    @Getter
    private String M2;//延期整改隐患内容及原因

    @Column(name = "M3", columnDefinition = "datetime")
    @Setter
    @Getter
    private Timestamp M3;//整改完毕日期

    @Column(name = "M4")
    @Setter
    @Getter
    private Integer M4;//延期时长

    @Column(name = "M5", columnDefinition = "varchar(500)")
    @Setter
    @Getter
    private String M5;//不通过原因

    @Column(name = "M6", columnDefinition = "varchar(10)")
    @Setter
    @Getter
    private String M6;//审批状态 null 未审核 0不通过 1通过

    @Column(name = "number", columnDefinition = "varchar(50)")
   /* @Transient*/
    @Setter
    @Getter
    private String number;//编号


}
