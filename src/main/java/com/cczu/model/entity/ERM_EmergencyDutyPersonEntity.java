package com.cczu.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author jason
 * @ClassName: ERM_EmergencyDutyPersonEntity
 * @Description: 应急资源管理_值班人员
 * @date 2017年5月27日
 */
@Entity
@Table(name = "erm_emergencydutyperson")
public class ERM_EmergencyDutyPersonEntity{

    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = -7333410519348880390L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, columnDefinition="bigint")
    @Setter
    @Getter
    public Long ID;//编号

    @Column(name = "userid", nullable = false)
    @Setter
    @Getter
    private Integer userid;//用户id

    @Column(name = "name", length = 100)
    @Setter
    @Getter
    private String name;//姓名

    @Column(name = "phone", length = 20)
    @Setter
    @Getter
    private String phone;//手机号

    @Column(name = "tel", length = 50)
    @Setter
    @Getter
    private String tel;//座机号


}
