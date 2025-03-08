package com.oshiel.beans;

import java.util.List;
import lombok.Data;

/**
 * お気に入り記事一覧
 */
@Data
public class FavoriteArticleBean {
    /**
     * oshiel会員ID
     */
    private Integer oshielId;

    /**
     * お気に入り記事ID
     */
    private List<ArticleBean> article;

}
