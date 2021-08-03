package com.example.study.controller.api;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.service.UserApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserApiController implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserApiLogicService userApiLoginService;

    @Override
    @PostMapping("")
    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {
        log.info("{}", request);
        return userApiLoginService.create(request);
    }

    @Override
    @GetMapping("/{id}")
    public Header<UserApiResponse> read(@PathVariable Long id) {
        log.info("read id : {}", id);
        return userApiLoginService.read(id);
    }

    @Override
    @PutMapping("/{id}")
    public Header<UserApiResponse> update(@PathVariable Long id,
                                          @RequestBody Header<UserApiRequest> request) {
        return userApiLoginService.update(id, request);
    }

    @Override
    @DeleteMapping("/{id}")
    public Header delete(@PathVariable Long id) {
        log.info("delete id : {}", id);
        return userApiLoginService.delete(id);
    }

}
