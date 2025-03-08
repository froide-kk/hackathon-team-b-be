package com.oshiel.services;

import com.oshiel.beans.UserRequestBean;
import com.oshiel.beans.UserResponseBean;
import com.oshiel.entities.MemberEntity;
import com.oshiel.entities.TopicEntity;
import com.oshiel.repositories.MemberRepository;
import com.oshiel.repositories.TopicRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Service
public class UserService {

    private MemberRepository memberRepository;

    private TopicRepository topicRepository;

    @Autowired
    public UserService(MemberRepository memberRepository, TopicRepository topicRepository) {
        this.memberRepository = memberRepository;
        this.topicRepository = topicRepository;
    }

    /**
     * ユーザ登録
     * @return
     */
    @Transactional
    public UserResponseBean regist(UserRequestBean bean) throws Exception{

        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setSlackId(bean.getSlackId());
        memberEntity.setSlackToken(bean.getSlackToken());

        log.info(String.valueOf(memberEntity.getNotificationFlag()));

        long unixTimestamp = Long.parseLong(bean.getSlackExpire());
        Date date = new Date(unixTimestamp * 1000);

        memberEntity.setSlackExpire(date);
        this.memberRepository.saveAndFlush(memberEntity);

        UserResponseBean response = new UserResponseBean();
        response.setOshielId(memberEntity.getOshielId());
        return response;
    }


    /**
     * ユーザ情報情報取得
     * @param oshielId oshiel会員ID
     * @return ユーザ情報
     */
    public UserResponseBean get(String oshielId){
        UserResponseBean response = new UserResponseBean();
        // 会員情報取得
        MemberEntity memberEntity = this.memberRepository.findByOshielId(Integer.valueOf(oshielId));

        // 会員情報なし
        if (memberEntity == null) {
            return null;
        }
        response.setOshielId(memberEntity.getOshielId());

        // トピック取得
        TopicEntity topicEntity = this.topicRepository.findByOshielId(Integer.valueOf(oshielId));
        if (topicEntity != null) {
            response.setTopic(topicEntity.getTopicDetail());
        }

        // レスポンス作成
        return response;
    }

}
