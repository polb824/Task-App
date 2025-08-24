package com.example.Task_App.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "tasks")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    // one task can have many tags (simplify muna as comma-separated string)
    @ElementCollection
    private List<String> tags;

    // relationship to checklist items
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CheckListItemEntity> checkListItem;

}
