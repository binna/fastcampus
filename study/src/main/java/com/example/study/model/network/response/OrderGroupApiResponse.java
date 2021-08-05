package com.example.study.model.network.response;

import com.example.study.model.enumclass.OrderType;
import com.example.study.model.enumclass.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderGroupApiResponse {
    private Long id;
    private String status;
    private OrderType orderType;
    private String revAddress;
    private String revName;
    private PaymentType paymentType;
    private BigDecimal totalPrice;
    private Integer totalQuantity;
    private LocalDateTime orderAt;
    private LocalDate arrivalDate;
    private Long userId;
}