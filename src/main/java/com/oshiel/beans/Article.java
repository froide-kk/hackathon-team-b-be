package com.oshiel.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Article {

    @JsonProperty("article_id")
    private Integer articleId;

    @JsonProperty("description")
    private String description;

    @JsonProperty("published_at")
    private String publishedAt;

    @JsonProperty("title")
    private String title;

    @JsonProperty("url")
    private String url;

    @JsonProperty("url_to_image")
    private String urlToImage;

}
