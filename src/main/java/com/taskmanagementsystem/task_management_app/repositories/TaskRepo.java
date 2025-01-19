package com.taskmanagementsystem.task_management_app.repositories;

import com.taskmanagementsystem.task_management_app.dto.TaskDTO;
import com.taskmanagementsystem.task_management_app.entities.Task;
import io.micrometer.common.KeyValues;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Long> {
    List<Task> findAllByTitleContaining(String title);

    List<Task> findAllByUserId(Long userId);
}
