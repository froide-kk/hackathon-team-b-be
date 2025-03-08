package com.oshiel.controllers;

import com.oshiel.beans.NotificationArticleReponseBean;
import com.oshiel.services.NotificationArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification/list")
public class NotificationArticleController {

    private final NotificationArticleService notificationArticleService;

    @Autowired
    private NotificationArticleController(NotificationArticleService notificationArticleService) {
        this.notificationArticleService = notificationArticleService;
    }

    @GetMapping("/{oshielId}")
    public ResponseEntity<NotificationArticleReponseBean> getNotifications(@PathVariable Integer oshielId) {
        HttpStatus status = HttpStatus.OK;
        NotificationArticleReponseBean response = this.notificationArticleService.get(oshielId);

        return new ResponseEntity<>(response, status);

    }
}
