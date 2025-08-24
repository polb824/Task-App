package com.example.Task_App.mapper;

import com.example.Task_App.dto.CheckListItemDto;
import com.example.Task_App.dto.TaskDto;
import com.example.Task_App.entity.CheckListItemEntity;
import com.example.Task_App.entity.TaskEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDto toDto(TaskEntity entity);
    TaskEntity toEntity(TaskDto dto);

    CheckListItemDto toDto(CheckListItemEntity entity);
    CheckListItemEntity toEntity(CheckListItemDto dto);
}

