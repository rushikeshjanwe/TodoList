package com.example.TodoList.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Explicitly uses MySQL auto-increment
    private Long id;
    private String title;
    private String priority; // e.g., Low, Medium, High
    private LocalDate dueDate;
    private boolean completed;
}