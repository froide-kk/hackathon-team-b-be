package com.oshiel.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TopicRequestBean {

    /**
     * oshiel会員ID
     */
    @NotNull
    @JsonProperty("oshiel_id")
    private Integer oshielId;

    /**
     * トピックID
     */
    @JsonProperty("topic_id")
    private Integer topicId;

    /**
     * トピック
     */
    @NotBlank
    @JsonProperty("topic")
    private String topic;

}
