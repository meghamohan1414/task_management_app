package com.taskmanagementsystem.task_management_app.services.employee;

import com.taskmanagementsystem.task_management_app.dto.TaskDTO;
import com.taskmanagementsystem.task_management_app.entities.Task;
import com.taskmanagementsystem.task_management_app.repositories.TaskRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.taskmanagementsystem.task_management_app.utils.Utility.getTaskStatus;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    private final TaskRepo taskRepo;

    @Override
    public List<TaskDTO> getTasksByUserId(Long userId) {
        return taskRepo.findAllByUserId(userId)
                .stream()
                .map(Task::getTaskDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO updateTask(Long id, String status) {
        Optional<Task> taskDetails = taskRepo.findById(id);
        if(taskDetails.isPresent()){
            Task task = taskDetails.get();
            task.setTaskStatus(getTaskStatus(String.valueOf(status)));
            return taskRepo.save(task).getTaskDTO();
        }
        return null;
    }

    @Override
    public TaskDTO getTaskById(Long id) {
         return taskRepo.findById(id)
                 .map(Task::getTaskDTO)
                 .orElse(null);
    }
}
