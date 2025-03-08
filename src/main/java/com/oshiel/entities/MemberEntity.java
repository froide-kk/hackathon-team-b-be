package com.oshiel.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.util.Date;

/**
 * 会員テーブルエンティティ
 */
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "member")
public class MemberEntity {

    /**
     * oshiel会員ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "oshiel_id")
    private Integer oshielId;

    /**
     * slackID
     */
    @Column(name = "slack_id")
    private String slackId ;

    /**
     * slack Token
     */
    @Column(name = "slack_token")
    private String slackToken;

    /**
     * slack expire
     */
    @Column(name = "slack_expire", columnDefinition = "DATETIME(0)")
    private Date slackExpire;

    /**
     * 通知フラグ
     */
    @Column(name = "notification_flag")
    private Integer notificationFlag;

    /**
     * 通知時間
     */
    @Column(name = "notification_time")
    private String notificationTime;

    /**
     * CREATE日時
     */
    @CreatedDate
    @Column(name = "createdate", updatable = false, columnDefinition = "DATETIME(0)")
    private Date createDate;

    /**
     * UPDATE日時
     */
    @LastModifiedDate
    @Column(name = "updatedate", columnDefinition = "DATETIME(0)")
    private Date updateDate;

    @PrePersist
    public void prePersist() {
        if (notificationFlag == null) {
            notificationFlag = 1;  // デフォルト値として1を設定
        }

        if (notificationTime == null) {
            notificationTime = "0900";
        }
    }

}
