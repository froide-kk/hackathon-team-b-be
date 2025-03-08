package com.oshiel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.oshiel.beans.NewsAPIResponseBean;
import com.oshiel.services.NewsAPIService;

@RestController
@RequestMapping(value = "/news/{topic}")
public class NewsController {
    private final NewsAPIService newsAPIService;

    /**
     * NewsAPIサービスクラス
     */
    @Autowired
    public NewsController(NewsAPIService newsAPIService) {
        this.newsAPIService = newsAPIService;
    }

    /**
     * NewsAPIから記事を取得
     *
     * @param bean
     * @return
     */
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<NewsAPIResponseBean>> getNews(@PathVariable String topic) {
        HttpStatus status = HttpStatus.OK;

        List<NewsAPIResponseBean> response = this.newsAPIService.getArticlesByNewsApi(topic);
        return new ResponseEntity<>(response, status);
    }
}
