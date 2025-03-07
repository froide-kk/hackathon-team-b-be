package com.oshiel.services;

import com.nimbusds.jwt.JWTClaimsSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class UserInfoService {

    private static final Logger log = LoggerFactory.getLogger(UserInfoService.class);

    public void saveUserInfo(JWTClaimsSet claims) throws ParseException {

        // JWTからユーザー情報を取得
        String userId = claims.getStringClaim("sub");
        String email = claims.getClaimAsString("email");
        String name = claims.getStringClaim("name");

        log.info("ユーザー情報: userid={}, email={} name={}", userId, email, name);

    }
}
