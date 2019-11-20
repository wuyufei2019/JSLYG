package com.cczu.aqzf.model.entity;

import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 安全生产执法--监管整饬
 *
 * @author jason
 * @date 2017年7月26日
 */
@Entity
@Table(name = "aqzf_superviserectify")
public class AQZF_SuperviseRectifyEntity extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = -2517097442171787883L;

    @Column(name = "ID1", columnDefinition = "bigint")
    @Setter
    @Getter
    public Long ID1;//检查记录id


    @Column(name = "reason", columnDefinition="varchar(MAX)")
    @Setter
    @Getter
    private String reason;//逐一分析问题隐患隐患形成的主、客观原因

    @Column(name = "danger", columnDefinition="varchar(MAX)")
    @Setter
    @Getter
    private String danger;//公司、班组两级分贝展开套路，剖析危害

    @Column(name = "measure", columnDefinition="varchar(MAX)")
    @Setter
    @Getter
    private String measure;//进一步完善公司制度规定、考核标准、实施细节和奖惩措施

    @Column(name = "method", columnDefinition="varchar(MAX)")
    @Setter
    @Getter
    private String method;//厘清相关安全管理人员责任，给予有个负责人相应处罚

    @Column(name = "situation", columnDefinition="varchar(MAX)")
    @Setter
    @Getter
    private String situation;//展开警示教育情况


}
