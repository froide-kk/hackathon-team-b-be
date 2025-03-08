package com.oshiel.beans;

import com.oshiel.entities.ArticleEntity;
import lombok.Data;

import java.util.List;

@Data
public class NotificationArticleReponseBean {

    /**
     * oshiel会員ID
     */
    private Integer oshielId;

    /**
     * 記事リスト
     */
    private List<Article> article;
}
