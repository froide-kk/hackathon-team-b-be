package com.oshiel.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * ユーザリクエスト
 */
@Data
public class UserResponseBean {

    /**
     * oshiel会員ID
     */
    @JsonProperty("oshiel_id")
    private Integer oshielId;

    /**
     * トピック
     */
    @JsonProperty("topic")
    private String topic;
}
