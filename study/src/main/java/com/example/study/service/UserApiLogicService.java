package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.User;
import com.example.study.model.enumclass.UserStatus;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {
        // 1. request data
        UserApiRequest userApiRequest = request.getData();

        // 2. User 생성
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status(UserStatus.REGISTERED)
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();
        User newUser = userRepository.save(user);

        // 3. 생성된 데이터 -> userApiResponse return
        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        // 1. id -> repository getOne, getById
        Optional<User> user = userRepository.findById(id);

        // 2. user -> userApiResponse return
        return user
                .map(targetUser -> response(targetUser))
                .orElseGet(() -> Header.error("데이터 없음"));
    }

    @Override
    public Header<UserApiResponse> update(Long id, Header<UserApiRequest> request) {
        // 1. data
        UserApiRequest userApiRequest = request.getData();

        // 2. id -> user 데이터를 찾고
        Optional<User> user = userRepository.findById(id);

        // 3. data -> update
        // 4. userApiResponse
        return user
                .map(targetUser -> {
                    targetUser
                            .setAccount(userApiRequest.getAccount())
                            .setPassword(userApiRequest.getPassword())
                            .setStatus(userApiRequest.getStatus())
                            .setPhoneNumber(userApiRequest.getPhoneNumber())
                            .setEmail(userApiRequest.getEmail())
                            .setRegisteredAt(userApiRequest.getRegisteredAt())
                            .setUnregisteredAt(userApiRequest.getUnregisteredAt())
                    ;
                    return targetUser;
                })
                .map(targetUser -> userRepository.save(targetUser))     // update
                .map(targetUser -> response(targetUser))                // userApiResponse
                .orElseGet(() -> Header.error("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        // 1. id -> repository -> user
        Optional<User> user = userRepository.findById(id);

        // 2. repository -> delete
        // 3. response return
        return user
                .map(targetUser -> {
                    userRepository.delete(targetUser);
                    return Header.ok();
                })
                .orElseGet(() -> Header.error("데이터 없음"));
    }


    private Header<UserApiResponse> response(User user) {
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
        return Header.ok(userApiResponse);
    }

}
