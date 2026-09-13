package com.mickle.todoapp.service;

import com.mickle.todoapp.dto.*;
import com.mickle.todoapp.entity.TaskEntity;
import com.mickle.todoapp.entity.UserEntity;
import com.mickle.todoapp.enums.TaskStatus;
import com.mickle.todoapp.repository.TasksRepo;
import com.mickle.todoapp.repository.UsersRepo;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {
    private final TasksRepo tasksRepo;
    private final UsersRepo usersRepo;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(TasksRepo tasksRepo,  UsersRepo usersRepo) {
        this.tasksRepo = tasksRepo;
        this.usersRepo = usersRepo;
    }

    // Создание нового пользователя
    public CreateUserResponse createUser(
            CreateUserReq request
    ) {
        var newUser = new UserEntity(
                request.username()
        );

        usersRepo.save(newUser);

        return new CreateUserResponse(
                newUser.getId(),
                newUser.getUsername()
        );
    }

    // Получения списка всех задач пользователя
    public List<GetAllTasksResponse> getAllTasks(
            Long userId
    ) {
        logger.info("Searching tasks for userId = {}", userId);

        List<TaskEntity> list = tasksRepo.findTasksByUserId(userId);

        logger.info("Found {} tasks", list.size());

        return list.stream()
                .map(task -> new GetAllTasksResponse(
                        task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getStatus(),
                        task.getCreatedAt()
                ))
                .toList();
    }

    // Создание новой задачи
    public CreateTaskResponse createTask(
            CreateTaskReq request
    ) {
        if(request.user_id() == null) {
            throw new IllegalArgumentException("userId is required");
        }

        var user = usersRepo.findById(request.user_id())
                .orElseThrow(() -> new EntityNotFoundException("userId not found"));

        var newTask = new TaskEntity(
                request.title(),
                request.description()
        );
        newTask.setCreatedAt(LocalDate.now());
        newTask.setUser(user);
        newTask.setStatus(TaskStatus.NEW);

        tasksRepo.save(newTask);

        return new CreateTaskResponse(
                newTask.getTitle()
        );
    }

    // Обновление статуса задачи
    public UpdateStatusResponse updateStatus(
            UpdateStatusReq request
    ) {
        if(request == null) {
            throw new IllegalArgumentException("request is required");
        }

        var task = tasksRepo.findById(request.task_id())
                .orElseThrow(() -> new EntityNotFoundException("task not found"));

        task.setStatus(request.status());
        tasksRepo.save(task);

        return new UpdateStatusResponse(
                task.getId(),
                task.getTitle(),
                task.getStatus()
        );
    }

    // Изменение заголовка задачи
    public UpdateTitleResponse updateTitle(
            UpdateTitleReq request
    ) {
        if(request == null) {
            throw new IllegalArgumentException("request is required");
        }

        var task = tasksRepo.findById(request.task_id())
                .orElseThrow(() -> new EntityNotFoundException("task not found"));

        task.setTitle(request.title());
        tasksRepo.save(task);

        return new UpdateTitleResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription()
        );
    }

    // Изменение описания задачи
    public UpdateDescriptionResponse updateDescription(
            UpdateDescriptionReq request
    ) {
        if(request == null) {
            throw new IllegalArgumentException("request is required");
        }

        var task = tasksRepo.findById(request.task_id())
                .orElseThrow(() -> new EntityNotFoundException("task not found"));

        task.setDescription(request.description());
        tasksRepo.save(task);

        return new UpdateDescriptionResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription()
        );
    }

    // Удаление задачи
    public void deleteTask(
            DeleteTaskReq request
    ) {
        if(request == null) {
            throw new IllegalArgumentException("request is required");
        }

        var task = tasksRepo.findById(request.task_id())
                .orElseThrow(() -> new EntityNotFoundException("task not found"));

        tasksRepo.delete(task);
    }

}
