package com.example.Task_App.service;

import com.example.Task_App.dao.Task;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class TaskService {

    private final Firestore firestore;

    public TaskService(Firestore firestore) {
        this.firestore = firestore;
    }

    public Task getTask(String id) {
        try {
            ApiFuture<DocumentSnapshot> tasks = firestore.collection("tasks").document(id).get();
            return tasks.get().toObject(Task.class);
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public List<Task> getAllTasks() {
        List<Task> taskList = new ArrayList<>();
        try {
            ApiFuture<QuerySnapshot> future = firestore.collection("tasks").get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for (QueryDocumentSnapshot doc : documents) {
                Task task = doc.toObject(Task.class);
                task.setId(doc.getId());
                taskList.add(task);
            }
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return taskList;
    }

    public String createTask(Task task) {
        try {
            ApiFuture<DocumentReference> tasks = firestore.collection("tasks").add(task);
            return "Document saved: id is " + tasks.get().getId();
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String updateTask(String id, Task updatedTask) {
        try {
            Map<String, Object> updateData = buildUpdateData(updatedTask);
            log.info("Updating task {} with data: {}", id, updateData);

            if (updateData.isEmpty()) {
                throw new RuntimeException("No valid fields to update");
            }

            ApiFuture<WriteResult> writeResult = firestore.collection("tasks")
                    .document(id)
                    .update(updateData);

            return "Task updated at: " + writeResult.get().getUpdateTime();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Error updating task", e);
            throw new RuntimeException("Failed to update task", e);
        }
    }

    public String deleteTask(String id) {
        try {
            ApiFuture<WriteResult> tasks = firestore.collection("tasks").document(id).delete();
            return "Task deleted at : " + tasks.get().getUpdateTime();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Task> getImportantTasks() {
        List<Task> taskList = new ArrayList<>();
        try {
            ApiFuture<QuerySnapshot> future = firestore.collection("tasks")
                    .whereEqualTo("isImportant", true)
                    .get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for (QueryDocumentSnapshot doc : documents) {
                Task task = doc.toObject(Task.class);
                task.setId(doc.getId());
                taskList.add(task);
            }
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return taskList;
    }

    private Map<String, Object> buildUpdateData(Task updatedTask) {
        Map<String, Object> updateData = new HashMap<>();

        addIfNotEmpty(updateData, "title", updatedTask.getTitle());
        addIfNotEmpty(updateData, "description", updatedTask.getDescription());
        addIfNotNull(updateData, "color", updatedTask.getColor());
        addIfNotNull(updateData, "dueDate", updatedTask.getDueDate());

        if (updatedTask.getIsDone() != null) {
            updateData.put("isDone", updatedTask.getIsDone());
        }
        if (updatedTask.getIsImportant() != null) {
            updateData.put("isImportant", updatedTask.getIsImportant());
        }

        log.info("Built update data: {}", updateData);
        return updateData;
    }

    private void addIfNotEmpty(Map<String, Object> map, String key, String value) {
        if (value != null && !value.trim().isEmpty()) {
            map.put(key, value.trim());
        }
    }

    private void addIfNotNull(Map<String, Object> map, String key, Object value) {
        if (value != null) {
            map.put(key, value);
        }
    }
}