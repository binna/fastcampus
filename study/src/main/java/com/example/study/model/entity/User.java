package com.example.study.model.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

// [참고] JPA 에서 프록시 생성을 위해서 기본생성자가 필요,
// 이때 접근권한은 protected 충분,
// Why? 객체 생성시 안정성을 떨어뜨리기 때문에!
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
// @Table(name = "user")        // 테이블명과 클래스명이 일치한 경우 생략 가능
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //    @Column(name = "account") // 컬럼명과 변수명이 일치한 경우 생략 가능
    private String account;
    private String email;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}
