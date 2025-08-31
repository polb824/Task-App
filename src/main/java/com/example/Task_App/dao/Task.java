package com.example.Task_App.dao;
import com.google.cloud.firestore.annotation.DocumentId;
import lombok.Data;

@Data
public class Task {

    @DocumentId
    private String id;
    private String title;
    private String description;
    private boolean isDone = false;
    private String dueDate;
    private String color;
}