package com.cczu.thirdapi.yunshixun.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


/*
云视讯-软终端需要自己保存账号
 */
@Entity
@Table(name = "YsxUser")
public class YsxUser {
    private static final long serialVersionUID = -1528033644109760656L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, columnDefinition = "bigint")
    @Setter
    @Getter
    @JsonIgnore
    public Long ID;//编号

    @Column(name = "conferId")
    @Getter
    @Setter
    private String conferId;//会议编号

    @Column(name = "strUserID")
    @Getter
    @Setter
    private String strUserID;//会议成员用户标识 必填

    @Column(name = "role")
    @Getter
    @Setter
    private Integer role;//用户角色，1：主持人，2：普通用户 必填

    @Column(name = "strName")
    @Getter
    @Setter
    private String strName;//用户姓名 选填

    @Getter
    @Setter
    private String state;//状态 选填

    @Column(name = "smsNumber")
    @Getter
    @Setter
    private String smsNumber;//手机号码

    @Column(name = "callID")
    @Getter
    @Setter
    private String callID;//会议成员呼叫标识 选填

    public boolean getIsSoftTerminal() {
        return isSoftTerminal;
    }


    public void setIsSoftTerminal(boolean softTerminal) {
        isSoftTerminal = softTerminal;
    }

    @Column(name = "softTerminal")
    private boolean isSoftTerminal;//高清会议参会人为软终端时需添加 "isSoftTerminal":"true"参数

    public boolean getIsDefaultMute() {
        return isDefaultMute;
    }

    public void setDefaultMute(boolean defaultMute) {
        isDefaultMute = defaultMute;
    }

    private boolean isDefaultMute;//会议成员呼叫标识 选填

    public YsxUser() {

    }

    public YsxUser(String strUserID, Integer role, String strName, String callID) {
        this.strUserID = strUserID;
        this.role = role;
        this.strName = strName;
        this.callID = callID;
    }
}
