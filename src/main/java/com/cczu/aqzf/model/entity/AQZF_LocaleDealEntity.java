package com.cczu.aqzf.model.entity;

import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 安全生产执法--整改延期申请
 *
 * @author jason
 * @date 2017年7月26日
 */
@Entity
@Table(name = "aqzf_localedeal")
public class AQZF_LocaleDealEntity extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = -2517097442171787883L;

    @Column(name = "ID1", columnDefinition = "bigint")
    @Setter
    @Getter
    public Long ID1;//检查记录id

    @Column(name = "qyid", columnDefinition = "bigint")
    @Setter
    @Getter
    public Long qyid;//企业id

    @Column(name = "unitname")
    @Setter
    @Getter
    public String unitname;//被检查单位名称

    @Column(name = "person1", columnDefinition = "varchar(50)")
    @Setter
    @Getter
    private String person1;//整改人

    @Column(name = "person2", columnDefinition = "varchar(50)")
    @Setter
    @Getter
    private String person2;//责任人

    @Column(name = "time")
    @Setter
    @Getter
    private Integer time;//整改时限



}
