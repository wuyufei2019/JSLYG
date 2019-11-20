package com.cczu.thirdapi.yunshixun.entity.dto;

import lombok.Getter;
import lombok.Setter;

public class ConferenceType {

    @Getter
    @Setter
    private Boolean bAudio;// 音频。为真表示音频媒体可用
    @Getter
    @Setter
    private Boolean bVideo;// 标清视频。（此参数集成商须填写为假false）
    @Getter
    @Setter
    private Boolean bHDVideo;// 高清视频。为真表示高清视频媒体可用
    @Getter
    @Setter
    private Boolean bData;// 数据协同。为真表示数据协同可用

    public ConferenceType() {
        this.bAudio = true;
        this.bVideo = false;
        this.bHDVideo = true;
        this.bData = false;
    }
}
