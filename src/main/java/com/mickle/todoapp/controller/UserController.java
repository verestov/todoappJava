package com.mickle.todoapp.controller;

import com.mickle.todoapp.dto.*;
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

    @PostMapping("/update/status")
    public ResponseEntity<UpdateStatusResponse> updateStatus(
            @RequestParam UpdateStatusReq request
    ) {
        logger.info("called updateStatus");
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.updateStatus(request));
    }

    @PostMapping("/update/title")
    public ResponseEntity<UpdateTitleResponse> updateTitle(
            @RequestParam UpdateTitleReq request
    ) {
        logger.info("called updateTitle");
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.updateTitle(request));
    }

    @PostMapping("/update/description")
    public ResponseEntity<UpdateDescriptionResponse> updateDescription(
            @RequestParam UpdateDescriptionReq request
    ) {
        logger.info("called updateDescription");

        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.updateDescription(request));
    }

    @DeleteMapping("/delete/task")
    public ResponseEntity<Void>  deleteTask(
            @RequestParam DeleteTaskReq request
    ) {
        logger.info("called deleteTask");
        userService.deleteTask(request);

        return ResponseEntity.noContent().build();
    }
}
