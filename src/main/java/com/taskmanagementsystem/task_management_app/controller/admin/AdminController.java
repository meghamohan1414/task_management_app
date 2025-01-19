package com.taskmanagementsystem.task_management_app.controller.admin;

import com.taskmanagementsystem.task_management_app.dto.TaskDTO;
import com.taskmanagementsystem.task_management_app.services.admin.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(adminService.getUsers());
    }

    @PostMapping("/task")
    public ResponseEntity<?> postTask(@RequestBody TaskDTO taskDTO){
        TaskDTO createdTask = adminService.postTask(taskDTO);
        if(createdTask==null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @GetMapping("/tasks")
    public ResponseEntity<?> getTasks() {
        return ResponseEntity.ok(adminService.getTasks());
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.getTaskById(id));
    }
    @DeleteMapping("/task/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id){
        adminService.deleteTask(id);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/tasks/search/{title}")
    public ResponseEntity<?> getTasksByTitle(@PathVariable String title) {
        return ResponseEntity.ok(adminService.searchTaskByTitle(title));
    }

    @PostMapping("/task/update/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO){
        TaskDTO updatedTask = adminService.updateTask(id, taskDTO);
        if(updatedTask==null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedTask);
    }

}
