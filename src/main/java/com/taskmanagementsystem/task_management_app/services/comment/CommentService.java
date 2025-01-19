package com.taskmanagementsystem.task_management_app.services.comment;

import com.taskmanagementsystem.task_management_app.dto.CommentDTO;

import java.util.List;

public interface CommentService {

    CommentDTO createComment(Long taskId, Long postedBy, String content);

    List<CommentDTO> getCommentsByTaskId(Long id);
}
