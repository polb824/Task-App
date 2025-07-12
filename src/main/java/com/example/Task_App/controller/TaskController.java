package com.example.Task_App.controller;
import com.example.Task_App.dao.Task;
import com.example.Task_App.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    public TaskController( TaskService taskService) {
        this.taskService = taskService;
    }


    @PostMapping("/create-task")
    public ResponseEntity<String> createTask(@RequestBody Task task){
        System.out.println("🔥 createTask() method called");
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @GetMapping("get-task/{id}")
    public ResponseEntity<Task> getTask(@PathVariable("id") String id){
        return ResponseEntity.ok(taskService.getTask(id));
    }

    @PutMapping()
    public ResponseEntity<String> updateTaskTitle(@RequestBody Task task){
        return ResponseEntity.ok(taskService.updateTaskTitle(task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") String id){
        return ResponseEntity.ok(taskService.deleteTask(id));
    }

}
