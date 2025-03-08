package com.oshiel.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.oshiel.beans.ArticleBean;
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

    /**
     * お気に入り記事を取得
     *
     * @param oshielId oshiel会員ID
     * @return お気に入り記事一覧
     */
    public FavoriteArticleBean getByOshielId(String oshielId) {
        List<ArticleEntity> ArticleList = favoriteArticleRepository.findByOshielId(Integer.valueOf(oshielId)).stream()
                .map(FavoriteArticleEntity::getArticle)
                .collect(Collectors.toList());

        List<ArticleBean> filteredArticleList = ArticleList.stream()
                .map(article -> {
                    ArticleBean articleBean = new ArticleBean();
                    articleBean.setArticleId(article.getArticle_id());
                    articleBean.setTitle(article.getTitle());
                    articleBean.setUrl(article.getUrl());
                    articleBean.setDescription(article.getDescription());
                    articleBean.setUrlToImage(article.getUrlToImage());
                    articleBean.setPublishedAt(article.getPublishedAt());
                    return articleBean;
                })
                .collect(Collectors.toList());

        FavoriteArticleBean bean = new FavoriteArticleBean();
        bean.setArticle(filteredArticleList);
        bean.setOshielId(Integer.valueOf(oshielId));
        return bean;
    }
}