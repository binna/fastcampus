package com.example.study.model.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

// java.lang.StackOverflowError
// 롬복을 쓰게 되면 toString을 자동으로 만들어 주는데
// 이게 현재 연관관계를 만들어 주면서
// 상호 참조하게 되고
// 상호 참조하게 되면 계속 계속 연관 연관타면서 StackOverflowError가 발생하기 때문에
// 계속 타지 못하도록 계속 타는 부분을 빼줘야 하는데
// 아래의 ToString 어노테이션 부분이 빼주는 것임
//@ToString(exclude = {"user", "item"})
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private LocalDateTime arrivalDate;
    private Integer quantity;
    private BigDecimal totalPrice;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

    // N : 1
    // OrderDetail 입장에서 생각!
//    @ManyToOne
//    private User user;

    // N : 1
//    @ManyToOne
//    private Item item;
}
