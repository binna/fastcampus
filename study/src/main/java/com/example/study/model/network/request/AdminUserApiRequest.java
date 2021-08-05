package com.example.study.model.network.request;

import com.example.study.model.enumclass.AdminUserStatus;
import com.example.study.model.enumclass.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminUserApiRequest {
    private String account;
    private String password;
    private AdminUserStatus status;
    private RoleType role;
    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;
}
