package com.example.Task_App.dto;

import com.example.Task_App.dao.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private List<Task> tasks;
}