package com.oshiel.repositories;

import com.oshiel.entities.FavoriteArticleEntity;
import com.oshiel.entities.primaries.FavoriteArticlePrimary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * お気に入り記事リポジトリ
 */
@Repository
public interface FavoriteArticleRepository extends JpaRepository<FavoriteArticleEntity, FavoriteArticlePrimary> {

}
