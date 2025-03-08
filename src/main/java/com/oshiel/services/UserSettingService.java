package com.oshiel.services;

import com.oshiel.beans.UserSettingRequestBean;
import com.oshiel.beans.UserSettingResponseBean;
import com.oshiel.entities.MemberEntity;
import com.oshiel.repositories.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserSettingService {

    private MemberRepository memberRepository;

    @Autowired
    public UserSettingService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public UserSettingResponseBean get(Integer id){
        UserSettingResponseBean response = new UserSettingResponseBean();
        MemberEntity memberEntity = this.memberRepository.findByOshielId(id);

        response.setOshielId(memberEntity.getOshielId());
        response.setNotificationFlag(memberEntity.getNotificationFlag());
        response.setNotificationTime(memberEntity.getNotificationTime());
        return response;
    }

    @Transactional
    public void set(UserSettingRequestBean bean) {

        MemberEntity memberEntity = this.memberRepository.findByOshielId(bean.getOshielId());

        if (memberEntity == null) {
            log.error("会員情報なし");
            return;
        }

        memberEntity.setNotificationFlag(bean.getNotificationFlag());
        memberEntity.setNotificationTime(bean.getNotificationTime());
        this.memberRepository.saveAndFlush(memberEntity);
    }


}
