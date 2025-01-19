package com.taskmanagementsystem.task_management_app.utils;

import com.taskmanagementsystem.task_management_app.enums.TaskStatus;

public class Utility {

    public static TaskStatus getTaskStatus(String taskStatus) {

        return switch (taskStatus){
            case "PENDING" -> TaskStatus.PENDING;
            case "INPROGRESS" -> TaskStatus.INPROGRESS;
            case "COMPLETED" -> TaskStatus.COMPLETED;
            case "DEFERRED" -> TaskStatus.DEFERRED;
            default -> TaskStatus.CANCELLED;
        };
    }
}
