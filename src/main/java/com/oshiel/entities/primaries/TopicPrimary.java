package com.oshiel.entities.primaries;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * トピックプライマリ
 */
@Data
public class TopicPrimary {

    /**
     * oshiel会員ID
     */
    private String oshielId;

    /**
     * トピックID
     */
    private String topicId;

}
