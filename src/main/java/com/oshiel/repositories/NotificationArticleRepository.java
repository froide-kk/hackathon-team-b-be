package com.oshiel.repositories;

import com.oshiel.entities.NotificationArticleEntity;
import com.oshiel.entities.primaries.NotificationArticlePrimary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 通知記事リポジトリ
 */
@Repository
public interface NotificationArticleRepository extends JpaRepository<NotificationArticleEntity, NotificationArticlePrimary> {

}
