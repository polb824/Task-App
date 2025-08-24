package com.example.Task_App.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "checklist_items")
@Data
public class CheckListItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private boolean done;
}
