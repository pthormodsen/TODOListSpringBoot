package no.patreek.todolistspringboot.service;

import no.patreek.todolistspringboot.model.Task;
import no.patreek.todolistspringboot.model.User;
import no.patreek.todolistspringboot.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskReminderService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 0 12 * * ?") // hver dag kl 12:00
    public void sendDueDateReminders() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        List<Task> tasksDueTomorrow = taskRepository.findByDueDate(tomorrow);

        for (Task task : tasksDueTomorrow) {
            User user = task.getUser();
            if (user != null && user.getEmail() != null) {
                emailService.sendReminderEmail(
                        user.getEmail(),
                        task.getDescription(),
                        task.getDueDate()
                );
            }
        }
    }
}
