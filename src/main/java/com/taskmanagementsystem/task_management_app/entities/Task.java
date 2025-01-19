package com.taskmanagementsystem.task_management_app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.taskmanagementsystem.task_management_app.dto.TaskDTO;
import com.taskmanagementsystem.task_management_app.enums.TaskStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Date dueDate;
    private String priority;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskDTO getTaskDTO() {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(id);
        taskDTO.setTitle(title);
        taskDTO.setPriority(priority);
        taskDTO.setDueDate(dueDate);
        taskDTO.setEmployeeId(user.getId());
        taskDTO.setEmployeeName(user.getName());
        taskDTO.setDescription(description);
        taskDTO.setTaskStatus(taskStatus);
        return taskDTO;
    }
}
