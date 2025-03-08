package com.oshiel.services;

import com.oshiel.entities.MemberEntity;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OshielOauthService {

    // 「oshiel」アプリ用のトークンを作成
    public String generateOshielJwt(MemberEntity member) {

        // Oshielアプリ用の秘密鍵が必要

        String oshielJwt = Jwts.builder().setSubject(String.valueOf(member.getOshielId()))
                .setIssuedAt(new Date())
                .compact();

        return oshielJwt;
    }
}
