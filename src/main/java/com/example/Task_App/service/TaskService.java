package com.example.Task_App.service;

import com.example.Task_App.dto.TaskDto;
import com.example.Task_App.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

//    public List<TaskDto> getAllTask(){
//        return taskRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
//    }

}
