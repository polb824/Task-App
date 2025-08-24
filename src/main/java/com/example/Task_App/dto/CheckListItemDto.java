package com.example.Task_App.dto;

import lombok.Data;

@Data
public class CheckListItemDto {
    private Long id;
    private String description;
    private boolean done;
}
