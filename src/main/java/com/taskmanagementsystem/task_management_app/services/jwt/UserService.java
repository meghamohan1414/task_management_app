package com.taskmanagementsystem.task_management_app.services.jwt;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    UserDetailsService userDetailsService();

}
