package no.patreek.todolistspringboot.service;

import no.patreek.todolistspringboot.model.Task;
import no.patreek.todolistspringboot.model.User;
import no.patreek.todolistspringboot.repository.TaskRepository;
import no.patreek.todolistspringboot.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Bruker ikke funnet: " + username));
    }

    public Task addTask(String description) {
        User currentUser = getCurrentUser();
        Task task = new Task(description, currentUser);
        return taskRepository.save(task);
    }

    public void markTaskDoneById(Long id) {
        User currentUser = getCurrentUser();
        Optional<Task> optionalTask = taskRepository.findByIdAndUser(id, currentUser);
        optionalTask.ifPresent(task -> {
            if (!task.isCompleted()) {
                task.markDone();
                taskRepository.save(task);
            }
        });
    }

    @Transactional
    public void removeTaskById(Long id) {
        User currentUser = getCurrentUser();
        taskRepository.deleteByIdAndUser(id, currentUser);
    }

    public List<Task> getTasks() {
        User currentUser = getCurrentUser();
        return taskRepository.findByUserOrderByCompletedAscDescriptionAsc(currentUser);
    }

    public void markTaskUndoneById(Long id) {
        User currentUser = getCurrentUser();
        Optional<Task> optionalTask = taskRepository.findByIdAndUser(id, currentUser);
        optionalTask.ifPresent(task -> {
            task.setCompleted(false);
            taskRepository.save(task);
        });
    }

    public void updateTaskDescription(Long id, String newDescription) {
        User currentUser = getCurrentUser();
        Optional<Task> optionalTask = taskRepository.findByIdAndUser(id, currentUser);
        optionalTask.ifPresent(task -> {
            task.setDescription(newDescription);
            taskRepository.save(task);
        });
    }
}