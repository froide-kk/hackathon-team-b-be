package com.oshiel.controllers;

import com.oshiel.beans.TopicRequestBean;
import com.oshiel.services.TopicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/topic")
public class TopicController {

    private final TopicService topicService;

    /**
     * トピックサービスクラス
     */
    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    /**
     * トピック登録・更新API
     * @param bean
     * @return
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> setTopic(@Valid @RequestBody TopicRequestBean bean) {
        HttpStatus status = HttpStatus.OK;

        this.topicService.setTopic(bean);
        return new ResponseEntity<>(status);
    }

}
