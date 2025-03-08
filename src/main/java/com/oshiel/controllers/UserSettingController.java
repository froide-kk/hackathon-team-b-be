package com.oshiel.controllers;

import com.oshiel.beans.UserSettingRequestBean;
import com.oshiel.beans.UserSettingResponseBean;
import com.oshiel.services.UserSettingService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserSettingController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserSettingService userSettingService;

    /**
     * ユーザ設定サービスクラス
     */
    @Autowired
    public UserSettingController(UserSettingService userSettingService) {
        this.userSettingService = userSettingService;
    }

    /**
     * ユーザー設定取得API
     *
     * @return ユーザ設定情報
     */
    @GetMapping(value = "/user/setting/{id}")
    public ResponseEntity<UserSettingResponseBean> setting(@Valid @RequestBody UserSettingRequestBean bean) {

        HttpStatus status = HttpStatus.OK;
        UserSettingResponseBean response = this.userSettingService.get(bean);

        return new ResponseEntity<>(response, status);
    }

    /**
     * ユーザー設定登録API
     *
     * @return ユーザ設定情報
     */
    @PostMapping(path = "/user/setting")
    public ResponseEntity<String> set(@Valid @RequestBody UserSettingRequestBean bean) {
        HttpStatus status = HttpStatus.OK;
        this.userSettingService.set(bean);

        return new ResponseEntity<>(status);
    }
}
