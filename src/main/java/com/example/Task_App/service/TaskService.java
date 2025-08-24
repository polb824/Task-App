package com.example.Task_App.service;

import com.example.Task_App.dto.TaskDto;
import com.example.Task_App.entity.TaskEntity;
import com.example.Task_App.mapper.TaskMapper;

import com.example.Task_App.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskService(TaskMapper taskMapper, TaskRepository taskRepository){
        this.taskMapper = taskMapper;
        this.taskRepository = taskRepository;
    }

    public TaskDto saveTask (TaskDto dto){
        TaskEntity entity = taskMapper.toEntity(dto);
        TaskEntity saved = taskRepository.save(entity);
        return taskMapper.toDto(saved);
    }

    //get all
    public List <TaskDto> getAllTask (){
        return taskRepository.findAll()
        .stream()
        .map (taskMapper::toDto)
        .collect(Collectors.toList());
    }

    public TaskDto getTaskById(Long id) {
        return taskRepository.findById(id)
                .map(taskMapper::toDto)
                .orElse(null);
    }

    public void deleteTask (Long id){
        taskRepository.deleteById(id);
    }

}
