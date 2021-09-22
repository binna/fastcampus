package com.example.study.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // 모든 아규먼트를 가지는 생성자를 추가
public class SearchParam {
    private String account;
    private String email;
    private int page;
}
