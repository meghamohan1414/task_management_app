package com.taskmanagementsystem.task_management_app.dto;

import com.taskmanagementsystem.task_management_app.enums.UserRoles;

public class AuthResponse {

    private String jwt;
    private Long userId;
    private UserRoles userRoles;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UserRoles getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(UserRoles userRoles) {
        this.userRoles = userRoles;
    }
}
