package com.oshiel.entities;

import lombok.Getter;

@Getter
public class OauthToken {
    private String accessToken;
    private String  idToken;

    public OauthToken(String accessToken, String idToken){
        this.accessToken = accessToken;
        this.idToken = idToken;
    }
}
