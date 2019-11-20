package com.cczu.thirdapi.yunshixun.entity.dto;

import lombok.Getter;
import lombok.Setter;

public class YsxPassword {
    @Setter
    @Getter
    private String strChairman; //主持人密码
    @Setter
    @Getter
    private String strParticipant; //普通用户密码
}
