package com.oshiel.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ArticleBean {
  /**
   * 記事ID
   */
  @NotNull
  @JsonProperty("article_id")
  private Integer articleId;

  /**
   * タイトル
   */
  @JsonProperty("title")
  private String title;

  /**
   * URL
   */
  @JsonProperty("url")
  private String url;

  /**
   * 説明
   */
  @JsonProperty("description")
  private String description;

  /**
   * 画像
   */
  @JsonProperty("urlToImage")
  private String urlToImage;

  /**
   * 公開日
   */
  @JsonProperty("publishedAt")
  private String publishedAt;
}
