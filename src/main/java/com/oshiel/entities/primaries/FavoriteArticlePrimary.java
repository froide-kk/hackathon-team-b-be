package com.oshiel.entities.primaries;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * お気に入り記事プライマリ
 */
@Data
public class FavoriteArticlePrimary {

    /**
     * お気に入り記事ID
     */
    private String favoriteArticleId;

    /**
     * oshiel会員ID
     */
    private Integer oshielId;

    /**
     * 記事ID
     */
    private Integer articleId;

}
