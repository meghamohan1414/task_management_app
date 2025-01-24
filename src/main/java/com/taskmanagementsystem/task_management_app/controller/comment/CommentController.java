package com.taskmanagementsystem.task_management_app.controller.comment;

import com.taskmanagementsystem.task_management_app.dto.CommentDTO;
import com.taskmanagementsystem.task_management_app.dto.TaskDTO;
import com.taskmanagementsystem.task_management_app.services.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/task/createComment")
    public ResponseEntity<?> createComment(@RequestParam Long taskId, @RequestParam Long userId, @RequestBody String content){
        CommentDTO createdComment = commentService.createComment(taskId, userId, content);
        if(createdComment==null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }

    @GetMapping("task/getComments/{id}")
    public ResponseEntity<?> getCommentsForTask(@PathVariable Long id){
        List<CommentDTO> comments = commentService.getCommentsByTaskId(id);
        if(comments.size()==0)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(comments);
    }
}
