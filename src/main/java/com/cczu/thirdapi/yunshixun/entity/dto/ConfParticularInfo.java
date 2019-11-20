package com.cczu.thirdapi.yunshixun.entity.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.cczu.thirdapi.yunshixun.entity.YsxUser;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class ConfParticularInfo {

    @Getter
    @Setter
    private String operateID;//发起方式 0：预约会议；1：即时会议，2：周期会议
    @Getter
    @Setter
    private String strUserId;//操作者的 ID 必填
    @Getter
    @Setter
    private String strConvener;//会议召集者 ID 必填
    @Getter
    @Setter
    private String strConfID;//会议ID 可选
    @Getter
    @Setter
    private String strSubject;//会议主题 必填
    @Getter
    @Setter
    private Integer iMaxMember;//本次创建会议的最大人数   必填
    @Getter
    @Setter
    private String strBeginTime;//会议开始时间  必填
    @Getter
    @Setter
    private String strEndTime;//会议开始时间  必填

    @JSONField(name = "ConfCtrlFlag")
    public String getConfCtrlFlag() {
        return ConfCtrlFlag;
    }

    public void setConfCtrlFlag(String confCtrlFlag) {
        ConfCtrlFlag = confCtrlFlag;
    }


    private String ConfCtrlFlag;//会议控制位  必填
    @Getter
    @Setter
    private String iCycleNo;//周期会议中的序号，从 1 开始；如果不是周期会议，则为 0 必填
    @Getter
    @Setter
    private String subAddr;//订阅地址 必填
    @Getter
    @Setter
    private ConferenceType stConferenceType;//会议类型 必填
    @Getter
    @Setter
    private List<YsxUser> sqMemberList;//与会成员列表  可选
    @Getter
    @Setter
    private List<String> sqRecordList;//文件列表  可选
    @Getter
    @Setter
    private String confState;//会议状态  可选
    @Getter
    @Setter
    private String strAgenda;//会议议程  可选
    @Getter
    @Setter
    private YsxPassword stPassword;//会议用户密码  可选
    @Getter
    @Setter
    private Boolean createLive;//是否创建直播 可选


    public ConfParticularInfo() {
        this.stConferenceType = new ConferenceType();
        this.sqMemberList = new ArrayList<>();
        this.iMaxMember = 50;
        this.ConfCtrlFlag = "00000101";
        this.iCycleNo = "0";
        this.confState = "0";
        sqRecordList = new ArrayList<>();
        stPassword = new YsxPassword();
        subAddr ="";
    }

}
