package com.example.Task_App.dto;

import lombok.Data;
import java.util.List;

@Data
public class TaskDto {
    private Long id;
    private String title;
    private List<String> tags;
    private List<CheckListItemDto> checkListItem;
}
