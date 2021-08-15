package com.example.study.controller.api;

import com.example.study.controller.CrudController;
import com.example.study.model.entity.User;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.model.network.response.UserOrderInfoApiResponse;
import com.example.study.service.UserApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserApiController extends CrudController<UserApiRequest, UserApiResponse, User> {

    @Autowired
    private UserApiLogicService userApiLoginService;

    @GetMapping("/{id}/orderInfo")
    public Header<UserOrderInfoApiResponse> test(@PathVariable Long id) {
        return userApiLoginService.orderInfo(id);
    }

//    @PostConstruct
//    public void init() {
//        this.baseService = userApiLoginService;
//    }

    @GetMapping("")
    public Header<List<UserApiResponse>> search(
            // import org.springframework.data.domain.Pageable;
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 15) Pageable pageable) {
        log.info("{}", pageable);
        return userApiLoginService.search(pageable);
    }

}
