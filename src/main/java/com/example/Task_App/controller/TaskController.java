package com.example.Task_App.controller;

import com.example.Task_App.dto.TaskDto;
import com.example.Task_App.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @PostMapping
    public TaskDto createTask(@RequestBody TaskDto dto){
        return taskService.saveTask(dto);
    }

    @GetMapping
    public List<TaskDto> getAllTasks(){
        return taskService.getAllTask();
    }

    @GetMapping("/{id}")
    public TaskDto getTask(@PathVariable Long id){
        return taskService.getTaskById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }
}
