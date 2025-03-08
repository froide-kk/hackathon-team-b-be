package com.oshiel.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

/**
 * 記事テーブルエンティティ
 */
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "article")
public class ArticleEntity {

    /**
     * 記事ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Integer articleId;

    /**
     * タイトル
     */
    @Column(name = "title")
    @NotBlank
    private String title;

    /**
     * URL
     */
    @Column(name = "url")
    @NotBlank
    private String url;

    /**
     * 説明
     */
    @Column(name = "description")
    private String description;

    /**
     * 画像
     */
    @Column(name = "urlToImage")
    private String urlToImage;

    /**
     * 公開日
     */
    @Column(name = "publishedAt")
    @NotBlank
    private String publishedAt;

    /**
     * CREATE日時
     */
    @CreatedDate
    @Column(name = "createdate", updatable = false, columnDefinition = "DATETIME(0)")
    private Date createDate;

    /**
     * UPDATE日時
     */
    @LastModifiedDate
    @Column(name = "updatedate", columnDefinition = "DATETIME(0)")
    private Date updateDate;

}
