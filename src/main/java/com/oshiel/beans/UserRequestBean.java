package com.oshiel.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserRequestBean {

    /**
     * oshiel会員ID
     */
    @JsonProperty("oshiel_id")
    private Integer oshielId;

    /**
     * slackId
     */
    @JsonProperty("slack_id")
    private String slackId;

    /**
     * slackToken
     */
    @JsonProperty("slack_token")
    private String slackToken;

    /**
     * slackExpire
     */
    @JsonProperty("slack_expire")
    private String slackExpire;

}
