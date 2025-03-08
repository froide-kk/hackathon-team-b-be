package com.oshiel.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oshiel.entities.OauthToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class SlackOauthService {

    @Value("${slack.client.id}")
    private String clientId;
    @Value("${slack.client.secret}")
    private String clientSecret;
    // SLACK_REDIRECT_URIは、最初に送信された URI (送信された場合) と一致する必要がある。
    public static final String SLACK_REDIRECT_URI = "https://21e8-182-169-133-143.ngrok-free.app/redirect";
    public static final String SLACK_ACCESS_TOKEN_URL = "https://slack.com/api/openid.connect.token";

    private static final Logger log = LoggerFactory.getLogger(SlackOauthService.class);

    public OauthToken exchangeCodeForToken(String code){

        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();


        log.info("認可コード：{}", code);

        // リクエスト作成
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("code", code);
        params.add("redirect_uri", SLACK_REDIRECT_URI);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        // リクエスト送信（トークン要求）
        ResponseEntity<String> response = restTemplate.postForEntity(SLACK_ACCESS_TOKEN_URL, request, String.class);
        String tokenBody = response.getBody();

        log.info("レスポンストークン : {}", response.getBody());

        JsonNode json;
        try{
             json = mapper.readTree(tokenBody);
        } catch (Exception e){
            log.error("JSONのパースに失敗しました: {}", e.getMessage());
            throw new RuntimeException("JSON parse error", e);
        }

        String accessToken = json.path("access_token").asText(null);
        String idToken = json.path("id_token").asText(null);

        return new OauthToken(accessToken, idToken);
    }
}
