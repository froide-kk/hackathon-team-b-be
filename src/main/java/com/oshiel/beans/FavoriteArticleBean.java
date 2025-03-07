package com.oshiel.beans;

import java.util.List;

import com.oshiel.entities.ArticleEntity;

import lombok.Data;

/**
 * お気に入り記事プライマリ
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
  private List<ArticleEntity> article;

}
