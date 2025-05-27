package no.patreek.todolistspringboot.controller;

import no.patreek.todolistspringboot.model.Task;
import no.patreek.todolistspringboot.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Task> createTask(@RequestBody Task newTask) {
        Task savedTask = taskService.addTask(newTask.getDescription());
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
