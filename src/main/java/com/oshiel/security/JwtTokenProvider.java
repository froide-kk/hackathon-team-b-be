package com.oshiel.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    @Value("${slack.client.id}")
    private String clientId;
    @Value("${slack.client.secret}")
    private String SECRET_KEY;

    public String createToken(Authentication authentication) {
        // ユーザーの詳細情報を取得
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // ユーザーのロール情報を取得
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority) // GrantedAuthorityをStringに変換
                .collect(Collectors.toList());

        // JWT トークンを生成
        return Jwts.builder()
                .setSubject(userDetails.getUsername()) // ユーザー名を設定
                .claim("roles", roles) // ロール情報を "roles" というクレームに追加
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // シグネチャの生成
                .compact(); // トークンをコンパクトにして返す
    }

    public Authentication getAuthentication(String token) {
        // トークンをパースしてクレームを取得
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();

        // クレームからユーザー名を取得
        String username = claims.getSubject();

        // "roles" クレームからロール情報を取得
        List<String> roles = (List<String>) claims.get("roles"); // rolesクレームを取得

        // SimpleGrantedAuthority でロールを設定
        List<GrantedAuthority> authorities = roles.stream()
                .map(SimpleGrantedAuthority::new) // ロール文字列から SimpleGrantedAuthority を生成
                .collect(Collectors.toList());

        // ユーザー名とロール情報を使って UserDetails を作成
        UserDetails userDetails = new User(username, "", authorities);

        // UserDetails を基に Authentication を作成
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

}
