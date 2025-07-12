package com.example.Task_App.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "task")
@Getter
@Setter
public class TaskEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String description;
    private boolean isDone= false;
    private LocalDateTime dueDate;
}
