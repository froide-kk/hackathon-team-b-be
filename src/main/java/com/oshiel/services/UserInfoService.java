package com.oshiel.services;

import com.nimbusds.jwt.JWTClaimsSet;
import com.oshiel.entities.MemberEntity;
import com.oshiel.repositories.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

@Service
public class UserInfoService {

    private static final Logger log = LoggerFactory.getLogger(UserInfoService.class);

    private MemberRepository memberRepository;

    public UserInfoService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberEntity saveUserInfo(JWTClaimsSet claims, String accessToken) throws ParseException {

        // JWTからユーザー情報を取得
        String userId = claims.getStringClaim("sub");
        Date expirationTime = claims.getExpirationTime();

        Date now = new Date();

        Optional<MemberEntity> member = memberRepository.findBySlackId(userId);

        MemberEntity user = new MemberEntity();

        // ユーザーが存在するかチェック
        if (!member.isPresent()) {
            user.setSlackId(userId);
            user.setSlackExpire(expirationTime);
            user.setSlackToken(accessToken);
            user.setNotificationFlag(0);

            user.setCreateDate(now);
            user.setUpdateDate(now);

            memberRepository.save(user);
        }

        log.info("ユーザー情報: userid={}", userId);

        return user;
    }
}
