package com.oshiel.services;

import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.URL;

@Service
public class JwtValidatorService {
    private static final Logger log = LoggerFactory.getLogger(JwtValidatorService.class);

    public JWTClaimsSet validateIdToken(String idToken) throws Exception{

        // JWTをパース
        SignedJWT signedJWT = SignedJWT.parse(idToken);

        JWSHeader header = signedJWT.getHeader();
        String keyId = header.getKeyID();

        // 公開鍵を取得
        JWKSet jwkSet = JWKSet.load(new URL("https://slack.com/openid/connect/keys"));
        JWK jwk = jwkSet.getKeyByKeyId(keyId);
        if (jwk == null) {
            throw new Exception("署名検証用の鍵が見つかりません: " + keyId);
        }

        // RSA公開鍵を取得し、検証用のJWSVerifierを作成
        RSAKey rsaKey = (RSAKey) jwk;
        JWSVerifier verifier = new RSASSAVerifier(rsaKey.toRSAPublicKey());

        // 署名の検証
        if (!signedJWT.verify(verifier)) {
            throw new Exception("JWTの署名検証に失敗しました");
        }

        // クレームの取得
        JWTClaimsSet claims = signedJWT.getJWTClaimsSet();

        // 有効期限の検証
        // 発行者の検証

        log.info("JWTの検証に成功");
        return claims;
    }
}
