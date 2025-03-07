package com.oshiel.repositories;

import com.oshiel.entities.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 会員リポジトリ
 */
@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {

    Optional<MemberEntity> findBySlackId(String slackId);

    /**
     * 会員情報取得
     * @param id oshiel会員ID
     * @return 会員情報
     */
    public MemberEntity findByOshielId(Integer id);

}
