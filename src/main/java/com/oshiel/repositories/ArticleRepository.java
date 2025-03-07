package com.oshiel.repositories;

import com.oshiel.entities.ArticleEntity;
import com.oshiel.entities.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 記事リポジトリ
 */
@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Integer> {
}
