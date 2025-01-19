package com.taskmanagementsystem.task_management_app.repositories;

import com.taskmanagementsystem.task_management_app.dto.CommentDTO;
import com.taskmanagementsystem.task_management_app.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {
    List<Comment> findAllByTaskId(Long id);
}
