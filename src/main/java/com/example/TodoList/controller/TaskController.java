package com.example.TodoList.controller;

import com.example.TodoList.model.Task;
import com.example.TodoList.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    public String getTasks(Model model, @RequestParam(value = "filter", required = false) String filter) {
        List<Task> tasks;
        if ("completed".equals(filter)) {
            tasks = taskService.getAllTasks().stream().filter(Task::isCompleted).toList();
        } else if ("active".equals(filter)) {
            tasks = taskService.getAllTasks().stream().filter(task -> !task.isCompleted()).toList();
        } else {
            tasks = taskService.getAllTasks();
        }
        model.addAttribute("tasks", tasks);
        model.addAttribute("newTask", new Task());
        return "tasks";
    }

    @PostMapping
    public String createTask(@ModelAttribute("newTask") Task task) {
        if (task.getTitle() != null && !task.getTitle().isEmpty()) {
            taskService.createTask(task);
        }
        return "redirect:/tasks";
    }

    @PostMapping("/toggle/{id}")
    public String toggleTask(@PathVariable Long id) {
        Task task = taskService.getTaskById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setCompleted(!task.isCompleted());
        taskService.updateTask(id, task);
        return "redirect:/tasks";
    }

    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }
}