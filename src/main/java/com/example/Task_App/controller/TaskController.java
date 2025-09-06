package com.example.Task_App.controller;
import com.example.Task_App.dao.Task;
import com.example.Task_App.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    public TaskController( TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/create-task")
    public ResponseEntity<String> createTask(@RequestBody Task task){
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @GetMapping("/get-all-task")
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("get-task/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") String id){
        return ResponseEntity.ok(taskService.getTask(id));
    }

    @PutMapping("/update-task/{id}")
    public ResponseEntity<String> updateTask(@PathVariable String id, @RequestBody Task updatedTask) {
        try {
            String result = taskService.updateTask(id, updatedTask);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update task");
        }
    }

    @DeleteMapping("/delete-task/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") String id){
        return ResponseEntity.ok(taskService.deleteTask(id));
    }

    @GetMapping("/get-important-tasks")
    public ResponseEntity<List<Task>> getImportantTasks() {
        try {
            List<Task> tasks = taskService.getImportantTasks();
            return ResponseEntity.ok(tasks);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PatchMapping("/toggle-important/{id}")
    public ResponseEntity<String> toggleImportant(@PathVariable("id") String id) {
        try {
            Task task = taskService.getTask(id);
            if (task == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Task not found");
            }

            Boolean currentImportant = task.getIsImportant();
            task.setIsImportant(currentImportant == null || !currentImportant);


            String result = taskService.updateTask(id, task);
            String status = Boolean.TRUE.equals(task.getIsImportant()) ? "marked as important" : "unmarked as important";
            return ResponseEntity.ok("Task " + status + ". " + result);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to toggle task importance: " + e.getMessage());
        }
    }
}