package com.mickle.todoapp.repository;

import com.mickle.todoapp.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksRepo extends JpaRepository<TaskEntity, Long> {

    List<TaskEntity> findAllByUserId(Long userId);
}
