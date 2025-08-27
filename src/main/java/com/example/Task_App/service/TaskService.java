package com.example.Task_App.service;

import com.example.Task_App.dao.Task;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.protobuf.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public String updateTaskTitle(Task task) {
        try {
            ApiFuture<WriteResult> tasks = firestore.collection("tasks")
                    .document(String.valueOf(task.getId()))
                    .update("title", task.getTitle());

            return "Title updated at: " + tasks.get().getUpdateTime();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
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