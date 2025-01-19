package com.taskmanagementsystem.task_management_app.enums;

public enum UserRoles {
    ADMIN("admin"),
    EMPLOYEE("employee");

    private final String roleName;

    UserRoles(String role) {
        this.roleName = role;
    }

    public String getRoleName(){
        return roleName;
    }
}
