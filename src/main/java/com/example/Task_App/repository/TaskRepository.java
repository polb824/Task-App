package com.example.Task_App.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Task_App.entity.TaskEntity;

public interface TaskRepository extends JpaRepository<TaskEntity,Long> {
    
}
