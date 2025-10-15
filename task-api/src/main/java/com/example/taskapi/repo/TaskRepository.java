package com.example.taskapi.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.taskapi.domain.Task;
import com.example.taskapi.domain.Task.Status;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    Page<Task> findByProjectIdAndStatus(Integer projectId, Status status, Pageable pageable);
    Page<Task> findByTitleContainingIgnoreCase(String q, Pageable pageable);
}
