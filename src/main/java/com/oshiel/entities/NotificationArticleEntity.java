package com.oshiel.entities;

import com.oshiel.entities.primaries.NotificationArticlePrimary;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.util.Date;

/**
 * 通知記事テーブルエンティティ
 */
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(NotificationArticlePrimary.class)
@Table(name = "notification_article")
public class NotificationArticleEntity {

    /**
     * 通知記事ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_article_id")
    private Integer notificationArticleId;

    /**
     * 記事ID
     */
    @Id
    @Column(name = "article_id")
    private Integer articleId;

    /**
     * oshiel会員ID
     */
    @Id
    @Column(name = "oshiel_id")
    private Integer oshielId;

    /**
     * CREATE日時
     */
    @CreatedDate
    @Column(name = "createdate", updatable = false)
    private Date createDate;

    /**
     * UPDATE日時
     */
    @LastModifiedDate
    @Column(name = "updatedate")
    private Date updateDate;

}
