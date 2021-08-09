package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderGroupStatus {
    ORDERING(0, "주문 중", "주문하고 있는 상태"),
    COMPLETE(1, "주문 완료", "주문이 완료된 상태"),
    CONFIRM(2, "주문 확인", "판매자가 주문을 확인한 상태")
    ;

    private Integer id;
    private String title;
    private String description;
}
