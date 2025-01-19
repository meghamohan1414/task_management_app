package com.taskmanagementsystem.task_management_app.services.auth;

import com.taskmanagementsystem.task_management_app.dto.SignUpReq;
import com.taskmanagementsystem.task_management_app.dto.UserDTO;

public interface AuthService {
    Boolean hasUserWithEmail(String email);

    UserDTO signUp(SignUpReq signUpReq);
}
