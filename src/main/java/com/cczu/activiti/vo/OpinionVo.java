package com.cczu.activiti.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class OpinionVo {

    @Getter
    @Setter
    private String opinion;
    @Getter
    @Setter
    private String taskId;
    @Getter
    @Setter
    private Date createTime;
    @Getter
    @Setter
    private String handlerName;

    public OpinionVo() {
    }

    public OpinionVo(String opinion, String handlerName, Date createTime) {
        this.opinion = opinion;
        this.handlerName = handlerName;
        this.createTime = createTime;
    }


}