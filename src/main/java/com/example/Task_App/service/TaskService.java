package com.example.Task_App.service;

import com.example.Task_App.dto.TaskDto;
import com.example.Task_App.entity.TaskEntity;
import com.example.Task_App.mapper.TaskMapper;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TaskService {

    private final TaskMapper taskMapper;

    public TaskService(TaskMapper taskMapper){
        this.taskMapper = taskMapper;
    }

    public TaskDto processTask (TaskDto dto){
        TaskEntity entity = taskMapper.toEntity(dto);
        return taskMapper.toDto(entity);
    }

    public List<TaskDto> getAllTask(){
        return List.of();
    }

}
