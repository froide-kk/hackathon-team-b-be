package com.oshiel.entities.primaries;

import lombok.Data;

/**
 * お気に入り記事プライマリ
 */
@Data
public class FavoriteArticlePrimary {

    /**
     * お気に入り記事ID
     */
    private Integer favoriteArticleId;

    /**
     * oshiel会員ID
     */
    private Integer oshielId;

    /**
     * 記事ID
     */
    private Integer articleId;

}
