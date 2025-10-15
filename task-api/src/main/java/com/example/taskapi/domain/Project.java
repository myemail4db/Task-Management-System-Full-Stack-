package com.example.taskapi.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name="projects")
public class Project {
    @Id
    private Integer projectId;

    @Column(unique = true)
    private String code;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Integer ownerUserId;

    private LocalDateTime createdAt;
    private LocalDateTime targetDue;

    public enum Type { FEATURE, PLATFORM, RESEARCH, MAINTENANCE, SECURITY }
    public enum Status { PLANNING, ACTIVE, ON_HOLD, DONE, CANCELLED }

    // --- Getters and Setters ---
    public Integer getProjectId() { return projectId; }
    public void setProjectId(Integer projectId) { this.projectId = projectId; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Type getType() { return type; }
    public void setType(Type type) { this.type = type; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public Integer getOwnerUserId() { return ownerUserId; }
    public void setOwnerUserId(Integer ownerUserId) { this.ownerUserId = ownerUserId; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getTargetDue() { return targetDue; }
    public void setTargetDue(LocalDateTime targetDue) { this.targetDue = targetDue; }
}

