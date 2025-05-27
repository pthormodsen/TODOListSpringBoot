package no.patreek.todolistspringboot.service;

import no.patreek.todolistspringboot.model.Task;
import no.patreek.todolistspringboot.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task addTask(String description) {
        Task task = new Task(description);
        return taskRepository.save(task);
    }

    public void markTaskDoneById(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        optionalTask.ifPresent(task -> {
            if (!task.isCompleted()) {
                task.markDone();
                taskRepository.save(task);
            }
        });
    }

    public void removeTaskById(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> getTasks() {
        return taskRepository.findAllByOrderByCompletedAscDescriptionAsc();
    }

    public void markTaskUndoneById(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        optionalTask.ifPresent(task -> {
            task.setCompleted(false);
            taskRepository.save(task);
        });
    }

    public void updateTaskDescription(Long id, String newDescription) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        optionalTask.ifPresent(task -> {
            task.setDescription(newDescription);
            taskRepository.save(task);
        });
    }
}
