package no.patreek.todolistspringboot.controller;

import no.patreek.todolistspringboot.model.Task;
import no.patreek.todolistspringboot.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;


    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getTasks();
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Map<String, String> body) {
        String description = body.get("description");
        String dueDateStr = body.get("dueDate");  // new field from frontend
        LocalDate dueDate = null;

        if (dueDateStr != null && !dueDateStr.isEmpty()) {
            try {
                dueDate = LocalDate.parse(dueDateStr);
            } catch (DateTimeParseException e) {
                return ResponseEntity.badRequest().build();
            }
        }

        Task savedTask = taskService.addTask(description, dueDate);
        return ResponseEntity.ok(savedTask);
    }

    @PutMapping("/{id}/done")
    public ResponseEntity<Void> markDone(@PathVariable Long id) {
        taskService.markTaskDoneById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/undone")
    public ResponseEntity<Void> markUndone(@PathVariable Long id) {
        taskService.markTaskUndoneById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDescription(@PathVariable Long id, @RequestBody Task updatedTask) {
        taskService.updateTaskDescription(id, updatedTask.getDescription());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.removeTaskById(id);
        return ResponseEntity.ok().build();
    }
}
