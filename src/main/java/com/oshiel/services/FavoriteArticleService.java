package com.oshiel.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oshiel.beans.FavoriteArticleBean;
import com.oshiel.entities.ArticleEntity;
import com.oshiel.entities.FavoriteArticleEntity;
import com.oshiel.repositories.FavoriteArticleRepository;

@Service
public class FavoriteArticleService {
  private final FavoriteArticleRepository favoriteArticleRepository;
    
  @Autowired
  public FavoriteArticleService(FavoriteArticleRepository favoriteArticleRepository) {
      this.favoriteArticleRepository = favoriteArticleRepository;
  }

  // ユーザーをIDで検索するメソッド
  public FavoriteArticleBean getByOshielId(String oshielId) {
    List<ArticleEntity> ArticleList = favoriteArticleRepository.findByOshielId(Integer.valueOf(oshielId)).stream()
    .map(FavoriteArticleEntity::getArticle)
    .collect(Collectors.toList());
    FavoriteArticleBean bean = new FavoriteArticleBean();
    bean.setArticle(ArticleList);
    bean.setOshielId( Integer.valueOf(oshielId) );
    return bean;
  }
}