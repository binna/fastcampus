package com.example.study.controller.api;

import com.example.study.controller.CrudController;
import com.example.study.model.entity.Item;
import com.example.study.model.network.request.ItemApiRequest;
import com.example.study.model.network.response.ItemApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
public class ItemApiController extends CrudController<ItemApiRequest, ItemApiResponse, Item> {

//    @Autowired
//    private ItemApiLogicService itemApiLogicService;
//
//    @PostConstruct
//    public void init() {
//        this.baseService = itemApiLogicService;
//    }

}