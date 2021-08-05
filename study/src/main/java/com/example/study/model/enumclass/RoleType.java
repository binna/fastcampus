package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoleType {
    MASTER(0, "마스터", "모든 권한을 갖고 있는 관리자"),
    ADMIN(1, "관리자", "소속된 부서의 권한을 갖고 있는 관리자"),
    PARTNER(2, "파트너", "우리와 거래를 맺고 물건을 판매하는 파트너사")
    ;

    private Integer id;
    private String title;
    private String description;
}
