package com.taskmanagementsystem.task_management_app.services.admin;

import com.taskmanagementsystem.task_management_app.dto.TaskDTO;
import com.taskmanagementsystem.task_management_app.dto.UserDTO;
import com.taskmanagementsystem.task_management_app.entities.Task;
import com.taskmanagementsystem.task_management_app.entities.User;
import com.taskmanagementsystem.task_management_app.enums.TaskStatus;
import com.taskmanagementsystem.task_management_app.enums.UserRoles;
import com.taskmanagementsystem.task_management_app.repositories.TaskRepo;
import com.taskmanagementsystem.task_management_app.repositories.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.taskmanagementsystem.task_management_app.utils.Utility.getTaskStatus;

@Service
public class AdminServiceImpl implements AdminService{

    private final UserRepo userRepo;
    private final TaskRepo taskRepo;

    public AdminServiceImpl(UserRepo userRepo, TaskRepo taskRepo) {
        this.userRepo = userRepo;
        this.taskRepo = taskRepo;
    }

    @Override
    public List<UserDTO> getUsers() {
        return userRepo.findAll()
                .stream()
                .filter(user -> user.getUserRoles().equals(UserRoles.EMPLOYEE))
                        .map(user -> user.getUserDTO()).collect(Collectors.toList());
    }

    @Override
    public TaskDTO postTask(TaskDTO taskDTO) {
        Optional<User> userDetails = userRepo.findById(taskDTO.getEmployeeId());
        if(userDetails.isPresent()){
            Task task = new Task();
            task.setTitle(taskDTO.getTitle());
            task.setDescription(taskDTO.getDescription());
            task.setPriority(taskDTO.getPriority());
            task.setDueDate(taskDTO.getDueDate());
            task.setUser(userDetails.get());
            task.setTaskStatus(TaskStatus.PENDING);
            return taskRepo.save(task).getTaskDTO();
        }
        return null;
    }

    @Override
    public List<TaskDTO> getTasks() {
        return taskRepo.findAll()
                .stream()
                .map(Task::getTaskDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO getTaskById(Long id) {
        Optional<Task> taskDetails = taskRepo.findById(id);
        return taskDetails.map(Task::getTaskDTO).orElse(null);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepo.deleteById(id);
    }

    @Override
    public List<TaskDTO> searchTaskByTitle(String title) {
        return taskRepo.findAllByTitleContaining(title)
                .stream()
                .map(Task::getTaskDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO updateTask(Long id, TaskDTO taskDTO) {
        Optional<Task> taskDetails = taskRepo.findById(id);
        Optional<User> userDetails = userRepo.findById(taskDTO.getEmployeeId());
        if(taskDetails.isPresent()&& userDetails.isPresent()){
            Task task = taskDetails.get();
            task.setTitle(taskDTO.getTitle());
            task.setDescription(taskDTO.getDescription());
            task.setPriority(taskDTO.getPriority());
            task.setDueDate(taskDTO.getDueDate());
            task.setUser(userDetails.get());
            task.setTaskStatus(getTaskStatus(String.valueOf(taskDTO.getTaskStatus())));
            return taskRepo.save(task).getTaskDTO();
        }
        return null;
    }


}
