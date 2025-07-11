package com.example.TodoList.exception;

public class TaskException extends RuntimeException {
    public TaskException(String message) {
        super(message);
    }
}