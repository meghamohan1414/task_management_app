package com.taskmanagementsystem.task_management_app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.taskmanagementsystem.task_management_app.entities.Task;
import com.taskmanagementsystem.task_management_app.entities.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Data
public class CommentDTO {

    private Long id;

    private String content;

    private Date createdAt;

    private Long userId;

    private String username;

    private Long taskId;
}
