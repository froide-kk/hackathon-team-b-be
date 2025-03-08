package com.oshiel.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * お気に入り記事一覧
 */
@Data
public class NewsAPIResponseBean {
    /**
     * タイトル
     */
    @JsonProperty("title")
    @NotBlank
    private String title;

    /**
     * URL
     */
    @JsonProperty("url")
    @NotBlank
    private String url;

    /**
     * 説明
     */
    @JsonProperty("description")
    private String description;

    /**
     * 画像
     */
    @JsonProperty("url_to_image")
    private String urlToImage;

    /**
     * 公開日
     */
    @JsonProperty("published_at")
    @NotBlank
    private String publishedAt;
}
