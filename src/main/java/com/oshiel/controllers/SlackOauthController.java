package com.oshiel.controllers;

import com.nimbusds.jwt.JWTClaimsSet;
import com.oshiel.entities.MemberEntity;
import com.oshiel.entities.OauthToken;
import com.oshiel.services.JwtValidatorService;
import com.oshiel.services.OshielOauthService;
import com.oshiel.services.SlackOauthService;
import com.oshiel.services.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SlackOauthController {

    private SlackOauthService slackOauthService;
    private JwtValidatorService jwtValidatorService;
    private UserInfoService userInfoService;
    private OshielOauthService oshielOauthService;

    @Autowired
    public SlackOauthController(SlackOauthService slackOauthService, JwtValidatorService jwtValidatorService, UserInfoService userInfoService, OshielOauthService oshielOauthService) {
        this.slackOauthService = slackOauthService;
        this.jwtValidatorService = jwtValidatorService;
        this.userInfoService = userInfoService;
        this.oshielOauthService = oshielOauthService;
    }

    // TODO セキュリティ上POSTの方が良い
    @GetMapping("/login")
    public ResponseEntity<Map<String, Object>> oauthCallback(@RequestParam("code") String code) throws Exception {

        // 1. 認可コードからアクセストークンを取得
        OauthToken token = slackOauthService.exchangeCodeForToken(code);

        // 2. IDトークンを検証
        JWTClaimsSet jwtClaims = jwtValidatorService.validateIdToken(token.getIdToken());

        // 3. IDトークンからユーザー情報を取得
        MemberEntity user = userInfoService.saveUserInfo(jwtClaims, token.getAccessToken());

        // 4. 「oshiel」用のトークンを作成
        String oshielToken = oshielOauthService.generateOshielJwt(user);

        return ResponseEntity.ok(Map.of(
                "id_token", oshielToken
        ));
    }
}
