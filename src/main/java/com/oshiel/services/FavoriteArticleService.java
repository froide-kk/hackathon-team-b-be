package com.oshiel.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.oshiel.beans.ArticleBean;
import com.oshiel.beans.FavoriteArticleBean;
import com.oshiel.beans.FavoriteArticleRequestBean;
import com.oshiel.entities.ArticleEntity;
import com.oshiel.entities.FavoriteArticleEntity;
import com.oshiel.entities.MemberEntity;
import com.oshiel.repositories.ArticleRepository;
import com.oshiel.repositories.FavoriteArticleRepository;
import com.oshiel.repositories.MemberRepository;

@Service
public class FavoriteArticleService {
    private final FavoriteArticleRepository favoriteArticleRepository;
    private final ArticleRepository articleRepository;
    private final MemberRepository member;

    @Autowired
    public FavoriteArticleService(FavoriteArticleRepository favoriteArticleRepository, ArticleRepository articleRepository, MemberRepository member) {
        this.favoriteArticleRepository = favoriteArticleRepository;
        this.articleRepository = articleRepository;
        this.member  = member;
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
                    articleBean.setArticleId(article.getArticleId());
                    articleBean.setTitle(article.getTitle());
                    articleBean.setUrl(article.getUrl());
                    articleBean.setDescription(article.getDescription());
                    articleBean.setUrlToImage(article.getUrlToImage());
                    articleBean.setPublishedAt(article.getPublishedAt());
                    return articleBean;
                })
                .collect(Collectors.toList());

        FavoriteArticleBean bean = new FavoriteArticleBean();
        bean.setArticles(filteredArticleList);
        bean.setOshielId(Integer.valueOf(oshielId));
        return bean;
    }

    @Transactional
    public void setFavoriteArticle(FavoriteArticleRequestBean bean) {
        FavoriteArticleEntity favoriteArticleEntity = new FavoriteArticleEntity();
        ArticleEntity articleEntity = new ArticleEntity();
        Optional<MemberEntity> member = this.member.findById(bean.getOshielId());

        articleEntity.setTitle(bean.getTitle());
        articleEntity.setUrl(bean.getUrl());
        articleEntity.setDescription(bean.getDescription());
        articleEntity.setUrlToImage(bean.getUrlToImage());
        articleEntity.setPublishedAt(bean.getPublishedAt());
        articleRepository.saveAndFlush(articleEntity);

        favoriteArticleEntity.setOshielId(bean.getOshielId());
        favoriteArticleEntity.setArticle(articleEntity);
        favoriteArticleEntity.setArticleId(articleEntity.getArticleId());
        favoriteArticleEntity.setMember(member.get());
        favoriteArticleRepository.saveAndFlush(favoriteArticleEntity);
    }
}