package com.oshiel.repositories;

import com.oshiel.entities.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 会員リポジトリ
 */
@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {

}
