package com.oshiel.controllers;

import com.oshiel.beans.UserRequestBean;
import com.oshiel.beans.UserResponseBean;
import com.oshiel.services.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * ユーザコントローラークラス
 */
@RestController
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    /**
     * ユーザサービスクラス
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * ユーザー登録API
     *
     * @param bean ユーザ登録リクエスト
     * @return ユーザ登録レスポンス
     */
    @PostMapping("/user")
    public ResponseEntity<UserResponseBean> user(@Valid @RequestBody UserRequestBean bean) {

        HttpStatus status = HttpStatus.OK;
        UserResponseBean response = new UserResponseBean();
        try {

            // ユーザ登録処理
            response = this.userService.regist(bean);

        } catch (Exception e) {
            log.error("登録失敗", e);
        }

        return new ResponseEntity<>(response, status);
    }

    /**
     * ユーザ情報取得API
     *
     * @param id oshiel会員ID
     * @return ユーザ情報レスポンス
     */
    @GetMapping(path = "/user/{id}")
    public ResponseEntity<UserResponseBean> userInfo(@PathVariable String id) {

        HttpStatus status = HttpStatus.OK;
        UserResponseBean response = new UserResponseBean();

        try {
            // ユーザ情報取得
            response = this.userService.get(id);
        } catch (Exception e) {
            log.error("取得失敗", e);
        }

        return new ResponseEntity<>(response, status);
    }

}
