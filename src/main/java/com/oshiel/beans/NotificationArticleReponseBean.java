package com.oshiel.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.oshiel.entities.ArticleEntity;
import lombok.Data;

import java.util.List;

@Data
public class NotificationArticleReponseBean {

    /**
     * oshiel会員ID
     */
    @JsonProperty("oshiel_id")
    private Integer oshielId;

    /**
     * 記事リスト
     */
    @JsonProperty("article")
    private List<Article> article;
}
