package com.cczu.aqzf.model.entity;

import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 安全生产执法--隐患整治
 *
 * @author jason
 * @date 2017年7月26日
 */
@Entity
@Table(name = "aqzf_hiddendeal")
public class AQZF_HiddenDealEntity extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = -2517097442171787883L;

    @Column(name = "ID1", columnDefinition = "bigint")
    @Setter
    @Getter
    public Long ID1;//检查记录id

    @Column(name = "ID2", columnDefinition = "bigint")
    @Setter
    @Getter
    public Long ID2;//现场检查内容 有隐患  的id

    @Column(name = "name", columnDefinition="varchar(500)")
    @Setter
    @Getter
    private String name;//隐患名称
    @Column(name = "person1", columnDefinition="varchar(50)")
    @Setter
    @Getter
    private String person1;//整改人

    @Column(name = "person2", columnDefinition="varchar(50)")
    @Setter
    @Getter
    private String person2;//负责人

    @Column(name = "time")
    @Setter
    @Getter
    private Integer time;//整改时限

    @Column(name = "result", columnDefinition="varchar(1000)")
    @Setter
    @Getter
    private String result;//结果

    @Column(name = "pic", length = 1000)
    @Setter
    @Getter
    private String pic;//附件

}
