package com.taskmanagementsystem.task_management_app.dto;


import com.taskmanagementsystem.task_management_app.enums.TaskStatus;
import lombok.Data;

import java.util.Date;

@Data
public class TaskDTO {
    private Long id;
    private String title;
    private Date dueDate;
    private String priority;
    private String description;
    private TaskStatus taskStatus;
    private Long employeeId;
    private String employeeName;
}
