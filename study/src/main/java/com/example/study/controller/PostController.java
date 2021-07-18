package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")     // 클래스 단위로 주소가 겹쳐도 스프링부트가 실행되는데 문제가 없다.
public class PostController {

    // json, xml, multiport-form, text-plain
    @PostMapping("postMethod")
    public SearchParam postMethod(@RequestBody SearchParam searchParam) {
        return searchParam;
    }

    @PutMapping
    public void put() {}

    @PatchMapping
    public void patch() {}
}
