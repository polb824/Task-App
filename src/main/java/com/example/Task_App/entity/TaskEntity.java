package com.example.Task_App.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class TaskEntity {

    @Id
    private Long id;
    private String title;
    private List<String> tags;

}
