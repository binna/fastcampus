package com.example.study.service;

import com.example.study.model.entity.Item;
import com.example.study.model.entity.OrderGroup;
import com.example.study.model.entity.User;
import com.example.study.model.network.Header;
import com.example.study.model.network.Pagination;
import com.example.study.model.network.response.ItemApiResponse;
import com.example.study.model.network.response.OrderGroupApiResponse;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.model.network.response.UserOrderInfoApiResponse;
import com.example.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
//public class UserApiLogicService extends BaseService<UserApiRequest, UserApiResponse, User> {
public class UserApiLogicService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderGroupApiLogicService orderGroupApiLogicService;

    @Autowired
    private ItemApiLogicService itemApiLogicService;

//    @Override
//    public Header<UserApiResponse> create(Header<UserApiRequest> request) {
//        // 1. request data
//        UserApiRequest userApiRequest = request.getData();
//
//        // 2. User 생성
//        User user = User.builder()
//                .account(userApiRequest.getAccount())
//                .password(userApiRequest.getPassword())
//                .status(UserStatus.REGISTERED)
//                .phoneNumber(userApiRequest.getPhoneNumber())
//                .email(userApiRequest.getEmail())
//                .registeredAt(LocalDateTime.now())
//                .build();
//        User newUser = baseRepository.save(user);
//
//        // 3. 생성된 데이터 -> userApiResponse return
//        return Header.ok(response(newUser));
//    }

//    @Override
//    public Header<UserApiResponse> read(Long id) {
//        // 1. id -> repository getOne, getById
//        Optional<User> user = baseRepository.findById(id);
//
//        // 2. user -> userApiResponse return
//        return user
//                .map(targetUser -> response(targetUser))
////                .map(userApiResponse -> Header.ok(userApiResponse))
//                .map(Header::ok)
//                .orElseGet(() -> Header.error("데이터 없음"));
//    }

//    @Override
//    public Header<UserApiResponse> update(Long id, Header<UserApiRequest> request) {
//        // 1. data
//        UserApiRequest userApiRequest = request.getData();
//
//        // 2. id -> user 데이터를 찾고
//        Optional<User> user = baseRepository.findById(id);
//
//        // 3. data -> update
//        // 4. userApiResponse
//        return user
//                .map(targetUser -> {
//                    targetUser
//                            .setAccount(userApiRequest.getAccount())
//                            .setPassword(userApiRequest.getPassword())
//                            .setStatus(userApiRequest.getStatus())
//                            .setPhoneNumber(userApiRequest.getPhoneNumber())
//                            .setEmail(userApiRequest.getEmail())
//                            .setRegisteredAt(userApiRequest.getRegisteredAt())
//                            .setUnregisteredAt(userApiRequest.getUnregisteredAt())
//                    ;
//                    return targetUser;
//                })
//                .map(targetUser -> baseRepository.save(targetUser))     // update
//                .map(targetUser -> response(targetUser))                // userApiResponse
//                .map(Header::ok)
//                .orElseGet(() -> Header.error("데이터 없음"));
//    }

//    @Override
//    public Header delete(Long id) {
//        // 1. id -> repository -> user
//        Optional<User> user = baseRepository.findById(id);
//
//        // 2. repository -> delete
//        // 3. response return
//        return user
//                .map(targetUser -> {
//                    baseRepository.delete(targetUser);
//                    return Header.ok();
//                })
//                .orElseGet(() -> Header.error("데이터 없음"));
//    }

    public Header<List<UserApiResponse>> search(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);

        List<UserApiResponse> userApiResponseList = users.stream()
                .map(user -> response(user))
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(users.getTotalPages())
                .totalElements(users.getTotalElements())
                .currentPage(users.getNumber())
                .currentElements(users.getNumberOfElements())
                .build();

        return Header.ok(userApiResponseList, pagination);
    }

    public Header<UserOrderInfoApiResponse> orderInfo(Long id) {
        // 1. user
        User user = userRepository.getById(id);
        UserApiResponse userApiResponse = response(user);

        // 2. orderGroup
        List<OrderGroup> orderGroupList = user.getOrderGroupList();
        List<OrderGroupApiResponse> orderGroupApiResponseList = orderGroupList.stream()
                .map(orderGroup -> {
                    OrderGroupApiResponse orderGroupApiResponse = orderGroupApiLogicService.response(orderGroup).getData();

                    // 3. item
                    List<ItemApiResponse> itemApiResponseList = orderGroup.getOrderDetailList().stream()
                            .map(detail -> detail.getItem())
                            .map(item -> itemApiLogicService.response(item).getData())
                            .collect(Collectors.toList());
                    orderGroupApiResponse.setItemApiResponseList(itemApiResponseList);

                    return orderGroupApiResponse;
                })
                .collect(Collectors.toList());

        userApiResponse.setOrderGroupApiResponseList(orderGroupApiResponseList);

        return Header.ok(UserOrderInfoApiResponse.builder()
                .userApiResponse(userApiResponse)
                .build());
    }


    private UserApiResponse response(User user) {
        // user -> userApiResponse
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        // Header + data = return
        return userApiResponse;
    }

}
