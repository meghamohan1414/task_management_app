package com.taskmanagementsystem.task_management_app.dto;

import com.taskmanagementsystem.task_management_app.enums.UserRoles;
import lombok.Data;

@Data
public class AuthResponse {

    private String jwt;
    private Long userId;
    private UserRoles userRoles;
}
