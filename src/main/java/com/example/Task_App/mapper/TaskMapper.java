package com.example.Task_App.mapper;

import com.example.Task_App.dto.TaskDto;
import com.example.Task_App.entity.TaskEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel= "spring")
public interface TaskMapper {
    TaskDto toDto(TaskEntity entity); //dto -> entity
    TaskEntity toEntity(TaskDto dto); //entity -> dto
}
