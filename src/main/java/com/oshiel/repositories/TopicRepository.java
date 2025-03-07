package com.oshiel.repositories;

import com.oshiel.entities.MemberEntity;
import com.oshiel.entities.TopicEntity;
import com.oshiel.entities.primaries.TopicPrimary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * トピックリポジトリ
 */
@Repository
public interface TopicRepository extends JpaRepository<TopicEntity, TopicPrimary> {

}
