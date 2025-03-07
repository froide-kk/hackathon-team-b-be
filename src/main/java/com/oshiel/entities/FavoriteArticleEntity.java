package com.oshiel.entities;

import com.oshiel.entities.primaries.FavoriteArticlePrimary;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

/**
 * お気に入り記事テーブルエンティティ
 */
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(FavoriteArticlePrimary.class)
@Table(name = "favorite_article")
public class FavoriteArticleEntity {

    /**
     * お気に入り記事ID
     */
    @Id
    @Column(name = "favorite_article_id")
    private String favoriteArticleId;

    /**
     * 記事ID
     */
    @Id
    @Column(name = "article_id")
    private String articleId;

    /**
     * oshiel会員ID
     */
    @Id
    @Column(name = "oshiel_id")
    private String oshielId;

    /**
     * コメント
     */
    @Column(name = "comment")
    private String comment;

    /**
     * 評価
     */
    @Column(name = "evaluation")
    private String evaluation;

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
