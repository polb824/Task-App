package com.example.Task_App.service;

import com.example.Task_App.dao.Task;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.protobuf.Api;
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
            DocumentReference taskDoc = firestore.collection("tasks").document(id);
            Map<String, Object> updateTask = new HashMap<>();
            updateTask.put("title", updatedTask.getTitle());
            updateTask.put("description", updatedTask.getDescription());
            updateTask.put("color", updatedTask.getColor());

            ApiFuture<WriteResult> writeResult = taskDoc.update(updateTask);
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

}