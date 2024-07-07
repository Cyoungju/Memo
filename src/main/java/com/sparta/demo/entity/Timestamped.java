package com.sparta.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 추상클래스에 선언한 맴버 변수를 컬럼으로 인식함 - 엔티티 클래스의 상속
@EntityListeners(AuditingEntityListener.class) // 자동으로 시간 넣어주기
public abstract class Timestamped { // 추상클래스로 사용

    @CreatedDate
    @Column(updatable = false) // 업데이트 되지 않게
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @LastModifiedDate //
    @Column
    @Temporal(TemporalType.TIMESTAMP) //날짜 데이터 타입 저장할때 사용
    private LocalDateTime modifiedAt;


    // DATE : 2023-01-01
    // TIME : 20:21:14
    // TIMESTAMP : 2023-01-01 20:21:14.99999
}