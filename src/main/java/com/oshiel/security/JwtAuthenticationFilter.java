package com.oshiel.security;

import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.net.URL;
import java.util.Date;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.info("検証開始");

        /**
         *   「oshiel」トークンの検証
         */

        // ヘッダーにAuthorizationが存在し、Bearerで始まるか
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String idToken = authHeader.substring(7);
            try {
                // JWTをパース
                SignedJWT signedJWT = SignedJWT.parse(idToken);

                JWSHeader header = signedJWT.getHeader();
                String keyId = header.getKeyID();

                // 公開鍵を取得
                JWKSet jwkSet = JWKSet.load(new URL("https://slack.com/openid/connect/keys"));
                JWK jwk = jwkSet.getKeyByKeyId(keyId);
                if (jwk == null) {
                    log.error("署名検証用の鍵が見つかりません: " + keyId);
                    throw new Exception("署名検証用の鍵が見つかりません: " + keyId);
                }

                // RSA公開鍵を取得し、RSASSAVerifierを使ってJWTの署名検証
                RSAKey rsaKey = (RSAKey) jwk;
                JWSVerifier verifier = new RSASSAVerifier(rsaKey.toRSAPublicKey());

                // 署名の検証
                if (!signedJWT.verify(verifier)) {
                    log.error("JWTの署名検証に失敗しました");
                    throw new Exception("JWTの署名検証に失敗しました");
                }

                // クレームの取得
                JWTClaimsSet claims = signedJWT.getJWTClaimsSet();

                // 有効期限の検証
//                Date expirationTime = claims.getExpirationTime();
//                if (expirationTime == null) {
//                    throw new Exception("JWT does not have an expiration time (exp) claim");
//                }
//                if (new Date().after(expirationTime)) {
//                    throw new Exception("JWT has expired");
//                }

                // 発行者の検証
                String issuer = claims.getIssuer();
                if (issuer == null || !issuer.equals("https://slack.com")) {
                    log.error("Invalid JWT issuer: " + issuer);
                    throw new Exception("Invalid JWT issuer: " + issuer);
                }

//                // 検証成功で認証オブジェクトを保管
//                String username = claims.getSubject();
//
//                // ユーザー名とロール値を設定　（以後の処理で、authentication.isAuthenticated())で認証チェックが使える
//                UsernamePasswordAuthenticationToken authentication =  new UsernamePasswordAuthenticationToken(
//                        username, null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
//                );
//
//                /**
//                 *   SecurityContextHolderに認証情報を保存することで、検証したリクエストは認証済みとされ以降の処理で認証情報の参照が可能になる
//                 */
//                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        /*
         *   フィルターチェーン：すべてのフィルターが処理を終えると最終的にコントローラに渡される
         *   SecurityConfig内で、フィルターチェーンの順序を指定する
         * 　JwtAuthenticationFilterのようなフィルタークラスを作成したら、SecurityConfigに追加
         *
         *   下記のメソッドを呼ぶことで、次のフィルターまたはコントローラに渡す
         */
        filterChain.doFilter(request, response);
        log.info("検証終了");
    }
}
