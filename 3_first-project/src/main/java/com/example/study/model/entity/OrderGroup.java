package com.example.study.model.entity;

import com.example.study.model.enumclass.OrderGroupStatus;
import com.example.study.model.enumclass.OrderType;
import com.example.study.model.enumclass.PaymentType;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Accessors(chain = true)
@Builder
@ToString(exclude = {"user", "orderDetailList"})
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class OrderGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private OrderGroupStatus status;
    @Enumerated(EnumType.STRING)
    private OrderType orderType;
    private String revAddress;
    private String revName;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    private BigDecimal totalPrice;
    private Integer totalQuantity;
    private LocalDateTime orderAt;
    private LocalDate arrivalDate;
    @CreatedDate
    private LocalDateTime createdAt;
    @CreatedBy
    private String createdBy;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @LastModifiedBy
    private String updatedBy;

//    private Long userId;
    // OrderGroup N : 1 User
    @ManyToOne
    private User user;

    // OrderGroup 1 : N OrderDetail
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderGroup")
    private List<OrderDetail> orderDetailList;
}
