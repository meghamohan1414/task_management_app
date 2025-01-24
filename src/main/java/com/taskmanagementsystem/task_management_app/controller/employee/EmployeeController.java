package com.taskmanagementsystem.task_management_app.controller.employee;

import com.taskmanagementsystem.task_management_app.dto.TaskDTO;
import com.taskmanagementsystem.task_management_app.services.employee.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/tasks/{id}")
    public ResponseEntity<?> getTasksByUserId(@PathVariable Long id){
        return ResponseEntity.ok(employeeService.getTasksByUserId(id));
    }

    @PutMapping("/task/{id}/{status}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @PathVariable String status){
        TaskDTO updatedTask = employeeService.updateTask(id, status);
        if(updatedTask==null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedTask);
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<?> getTasksById(@PathVariable Long id){
        return ResponseEntity.ok(employeeService.getTaskById(id));
    }
}
