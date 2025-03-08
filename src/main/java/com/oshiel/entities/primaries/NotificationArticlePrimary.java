package com.oshiel.entities.primaries;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * 通知記事プライマリ
 */
@Data
public class NotificationArticlePrimary {

    /**
     * 通知記事ID
     */
    private String notificationArticleId;

    /**
     * 記事ID
     */
    private String articleId;

    /**
     * oshiel会員ID
     */
    private String oshielId;
}
