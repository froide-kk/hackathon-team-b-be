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
    public static final String SLACK_REDIRECT_URI = "https://75a0-182-169-133-143.ngrok-free.app/login";
    public static final String SLACK_ACCESS_TOKEN_URL = "https://slack.com/api/openid.connect.token";

    private static final Logger log = LoggerFactory.getLogger(SlackOauthService.class);

    public OauthToken exchangeCodeForToken(String code){

        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();

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

        log.info("レスポンストークン : {}", tokenBody);

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
