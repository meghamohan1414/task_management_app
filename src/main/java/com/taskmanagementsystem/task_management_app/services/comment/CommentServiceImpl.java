package com.taskmanagementsystem.task_management_app.services.comment;

import com.taskmanagementsystem.task_management_app.dto.CommentDTO;
import com.taskmanagementsystem.task_management_app.entities.Comment;
import com.taskmanagementsystem.task_management_app.entities.Task;
import com.taskmanagementsystem.task_management_app.entities.User;
import com.taskmanagementsystem.task_management_app.repositories.CommentRepo;
import com.taskmanagementsystem.task_management_app.repositories.TaskRepo;
import com.taskmanagementsystem.task_management_app.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepo commentRepo;
    private final TaskRepo taskRepo;
    private final UserRepo userRepo;

    @Override
    public CommentDTO createComment(Long taskId, Long postedBy, String content) {
        Optional<Task> taskDetails = taskRepo.findById(taskId);
        Optional<User> userDetails = userRepo.findById(postedBy);
        if(taskDetails.isPresent() && userDetails.isPresent()){
            Comment comment = new Comment();
            comment.setContent(content);
            comment.setCreatedAt(new Date());
            comment.setTask(taskDetails.get());
            comment.setUser(userDetails.get());
            return commentRepo.save(comment).getCommentDTO();
        }
        return null;
    }

    @Override
    public List<CommentDTO> getCommentsByTaskId(Long id) {
        return commentRepo.findAllByTaskId(id)
                .stream()
                .map(Comment::getCommentDTO)
                .collect(Collectors.toList());
    }
}
