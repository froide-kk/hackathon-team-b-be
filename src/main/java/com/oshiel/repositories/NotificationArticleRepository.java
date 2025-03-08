package com.oshiel.repositories;

import com.oshiel.entities.NotificationArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 通知記事リポジトリ
 */
@Repository
public interface NotificationArticleRepository extends JpaRepository<NotificationArticleEntity, Integer> {

    public List<NotificationArticleEntity> findByOshielId(Integer oshielId);
}
