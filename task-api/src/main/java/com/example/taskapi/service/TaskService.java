package com.example.taskapi.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.taskapi.domain.Task;
import com.example.taskapi.repo.TaskRepository;

@Service
public class TaskService {
    private final TaskRepository repo;

    public TaskService(TaskRepository repo) { this.repo = repo; }

    public Page<Task> search(String q, int page, int size) {
        if (q == null || q.isBlank()) return repo.findAll(PageRequest.of(page, size));
        return repo.findByTitleContainingIgnoreCase(q, PageRequest.of(page, size));
    }

    public Optional<Task> get(Integer id) { return repo.findById(id); }

    public Task updateStatus(Integer id, Task.Status status) {
        Task t = repo.findById(id).orElseThrow();
        t.setStatus(status);
        t.setUpdatedAt(LocalDateTime.now());
        return repo.save(t);
    }
}
