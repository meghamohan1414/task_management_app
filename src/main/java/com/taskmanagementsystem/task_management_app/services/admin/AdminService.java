package com.taskmanagementsystem.task_management_app.services.admin;

import com.taskmanagementsystem.task_management_app.dto.TaskDTO;
import com.taskmanagementsystem.task_management_app.dto.UserDTO;

import java.util.List;

public interface AdminService {

    List<UserDTO> getUsers();
    TaskDTO postTask(TaskDTO taskDTO);
    List<TaskDTO> getTasks();
    TaskDTO getTaskById(Long id);
    void deleteTask(Long id);
    List<TaskDTO> searchTaskByTitle(String title);
    TaskDTO updateTask(Long id, TaskDTO taskDTO);
}
