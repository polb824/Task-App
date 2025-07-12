package com.example.Task_App.dto;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class TaskDto {

    private long id;
    private String title;
    private String description;
    private boolean isDone = false;
    private LocalDateTime dueDate;
}
