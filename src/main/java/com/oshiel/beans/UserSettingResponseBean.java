package com.oshiel.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserSettingResponseBean {

    /**
     * oshiel会員ID
     */
    @JsonProperty("oshiel_id")
    private Integer oshielId;

    /**
     * 通知フラグ
     */
    @JsonProperty("notification_flag")
    private Integer notificationFlag;

    /**
     * 通知時間
     */
    @JsonProperty("notification_time")
    private String notificationTime;

}
