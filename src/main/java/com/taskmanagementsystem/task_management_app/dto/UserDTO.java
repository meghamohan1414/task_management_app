package com.taskmanagementsystem.task_management_app.dto;

import com.taskmanagementsystem.task_management_app.enums.UserRoles;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private UserRoles userRoles;

}
