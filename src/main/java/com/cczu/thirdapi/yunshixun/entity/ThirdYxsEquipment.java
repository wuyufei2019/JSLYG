package com.cczu.thirdapi.yunshixun.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/*
云视讯-设备设置实体类
 */
@Entity
@Table(name = "ThirdYxsEquipment")
public class ThirdYxsEquipment {
    /**
     *
     */
    private static final long serialVersionUID = -1528033644109760656L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, columnDefinition = "bigint")
    @Setter
    @Getter
    public Long ID;//编号

    @Column(name = "accId", columnDefinition = "bigint")
    @Setter
    @Getter
    private Long accId;//用户id

    @Column(name = "accName", columnDefinition = "varchar(200)")
    @Setter
    @Getter
    private String accName;//用户姓名

    @Column(name = "userId", columnDefinition = "varchar(200)")
    @Setter
    @Getter
    private String userId;//设备id

    @Column(name = "passwd", columnDefinition = "varchar(200)")
    @Setter
    @Getter
    private String passwd;//设备密码

    @Column(name = "contact", columnDefinition = "varchar(50)")
    @Setter
    @Getter
    private String contact;//联系人

    @Column(name = "phone", columnDefinition = "varchar(18)")
    @Setter
    @Getter
    private String phone;//联系人

    @Column(name = "softTerminal")
    @Setter
    @Getter
    private Boolean softTerminal;//是否为软终端
}
