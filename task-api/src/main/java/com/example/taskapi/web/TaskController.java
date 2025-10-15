package com.example.taskapi.web;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskapi.domain.Task;
import com.example.taskapi.service.TaskService;

import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service) {this.service = service; }

    @GetMapping
    public Page<Task> list(
            @RequestParam(required = false) String q,
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "20") @Min(1) int size) {
                
        return service.search(q, page, size);

    }

    @GetMapping("/{id}")
    public Task get(@PathVariable Integer id) {
        return service.get(id).orElseThrow();
    }

    // ✅ Single, proper partial update (PATCH)
    // PATCH /api/tasks/{id}/status?status=IN_PROGRESS
    @PatchMapping("/{id}/status")
    public Task updateStatus(@PathVariable Integer id,
                            @RequestParam("status") Task.Status newStatus) {
        return service.updateStatus(id, newStatus);
    }
}