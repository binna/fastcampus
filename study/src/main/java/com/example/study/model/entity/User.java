package com.example.study.model.entity;

import com.example.study.model.enumclass.UserStatus;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Accessors(chain = true)
@Builder
@ToString(exclude = {"orderGroupList"})
// [참고] JPA 에서 프록시 생성을 위해서 기본생성자가 필요,
// 이때 접근권한은 protected 충분,
// Why? 객체 생성시 안정성을 떨어뜨리기 때문에!
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
// @Table(name = "user")        // 테이블명과 클래스명이 일치한 경우 생략 가능
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    @Column(name = "account") // 컬럼명과 변수명이 일치한 경우 생략 가능
    private String account;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    private String email;
    private String phoneNumber;
    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;
    @CreatedDate
    private LocalDateTime createdAt;
    @CreatedBy
    private String createdBy;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @LastModifiedBy
    private String updatedBy;


    // FetchType.LAZY : 지연로딩, FetchType.EAGER : 즉시로딩(1 : 1)

    // 1 : N
    // User 입장에서 생각!
    // mappedBy의 user는 OrderDetail -> User user의 변수명과 일치해야함
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
//    private List<OrderDetail> orderDetailList;

    // User 1 : N OrderGroup
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<OrderGroup> orderGroupList;
}
