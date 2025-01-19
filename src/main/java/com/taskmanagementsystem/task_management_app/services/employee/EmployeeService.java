package com.taskmanagementsystem.task_management_app.services.employee;

import com.taskmanagementsystem.task_management_app.dto.TaskDTO;

import java.util.List;

public interface EmployeeService {
    List<TaskDTO> getTasksByUserId(Long userId);

    TaskDTO updateTask(Long id, String status);

    TaskDTO getTaskById(Long id);
}
