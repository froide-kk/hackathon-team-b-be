package com.oshiel.services;

import com.oshiel.beans.TopicRequestBean;
import com.oshiel.entities.TopicEntity;
import com.oshiel.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TopicService {

    private final TopicRepository topicRepository;

    /**
     * トピックリポジトリ
     * @param topicRepository
     */
    @Autowired
    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    /**
     * トピック登録
     * @param bean
     */
    @Transactional
    public void setTopic(TopicRequestBean bean) {

        TopicEntity topicEntity = new TopicEntity();
        topicEntity.setOshielId(bean.getOshielId());
        topicEntity.setTopicId(bean.getTopicId() != null ? bean.getTopicId() : null);
        topicEntity.setTopicDetail(bean.getTopic());
        this.topicRepository.saveAndFlush(topicEntity);

    }

}
