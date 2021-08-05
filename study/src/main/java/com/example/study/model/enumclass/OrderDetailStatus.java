package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderDetailStatus {
    REGISTERED(0, "등록", "상품 상세 등록 상태"),
    UNREGISTERED(1, "해지", "상품 상세 해지 상태"),
    WAITING(2, "검수(대기)", "상품 상세 검수 상태")
    ;

    private Integer id;
    private String title;
    private String description;
}