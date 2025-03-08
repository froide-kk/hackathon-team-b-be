package com.oshiel.services;

import com.oshiel.beans.Article;
import com.oshiel.beans.FavoriteArticleBean;
import com.oshiel.beans.NotificationArticleReponseBean;
import com.oshiel.entities.ArticleEntity;
import com.oshiel.entities.FavoriteArticleEntity;
import com.oshiel.entities.NotificationArticleEntity;
import com.oshiel.repositories.FavoriteArticleRepository;
import com.oshiel.repositories.NotificationArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationArticleService {

    private final NotificationArticleRepository notificationArticleRepository;

    @Autowired
    public NotificationArticleService(NotificationArticleRepository notificationArticleRepository) {
        this.notificationArticleRepository = notificationArticleRepository;
    }

    public NotificationArticleReponseBean get(Integer oshielId) {

        List<ArticleEntity> ArticleEntityList = this.notificationArticleRepository.findByOshielId(oshielId).stream()
                .map(NotificationArticleEntity::getArticle)
                .toList();
        NotificationArticleReponseBean bean = new NotificationArticleReponseBean();
        List<Article> ArticleList = ArticleEntityList.stream().map(articleEntity -> {
            Article article = new Article();
            article.setArticleId(articleEntity.getArticleId());
            article.setTitle(articleEntity.getTitle());
            article.setDescription(articleEntity.getDescription());
            article.setUrl(articleEntity.getUrl());
            article.setUrlToImage(articleEntity.getUrlToImage());
            article.setPublishedAt(articleEntity.getPublishedAt());
            return article;
        }).collect(Collectors.toList());
        bean.setArticle(ArticleList);
        bean.setOshielId(oshielId);
        return bean;

    }
}
