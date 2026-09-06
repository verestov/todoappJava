package com.mickle.todoapp.controller;

import com.mickle.todoapp.dto.CreateTaskReq;
import com.mickle.todoapp.dto.CreateTaskResponse;
import com.mickle.todoapp.dto.GetAllTasksResponse;
import com.mickle.todoapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<List<GetAllTasksResponse>> getAllTasks(
            @RequestParam(required = false) Long userId
    ) {
        logger.info("called getAllTasks");

        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.getAllTasks(userId));
    }

    @PostMapping("/new")
    public ResponseEntity<CreateTaskResponse> createTask(
            @RequestParam(required = false) Long userId,
            @RequestBody CreateTaskReq request
    ) {
        logger.info("called createTask");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.createTask(request, userId));
    }
}
