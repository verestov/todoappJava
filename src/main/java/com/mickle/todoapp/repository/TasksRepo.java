package com.mickle.todoapp.repository;

import com.mickle.todoapp.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksRepo extends JpaRepository<TaskEntity, Long> {

    @Query("SELECT t FROM TaskEntity t WHERE t.user.id = :userId")
    List<TaskEntity> findTasksByUserId(@Param("userId") Long userId);
}
