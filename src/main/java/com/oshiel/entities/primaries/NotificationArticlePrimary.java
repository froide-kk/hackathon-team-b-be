package com.oshiel.entities.primaries;

import lombok.Data;

/**
 * 通知記事プライマリ
 */
@Data
public class NotificationArticlePrimary {

    /**
     * 通知記事ID
     */
    private Integer notificationArticleId;

    /**
     * 記事ID
     */
    private Integer articleId;

    /**
     * oshiel会員ID
     */
    private Integer oshielId;
}
