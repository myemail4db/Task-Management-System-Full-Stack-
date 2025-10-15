package com.example.taskapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.taskapi.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {}
