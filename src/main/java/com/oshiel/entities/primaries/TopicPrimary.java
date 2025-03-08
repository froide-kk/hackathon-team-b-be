package com.oshiel.entities.primaries;

import lombok.Data;

/**
 * トピックプライマリ
 */
@Data
public class TopicPrimary {

    /**
     * oshiel会員ID
     */
    private Integer oshielId;

    /**
     * トピックID
     */
    private Integer topicId;

}
