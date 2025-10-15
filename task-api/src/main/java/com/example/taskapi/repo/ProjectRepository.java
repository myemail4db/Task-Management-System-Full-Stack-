package com.example.taskapi.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.taskapi.domain.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    Optional<Project> findByCode(String code);
}
