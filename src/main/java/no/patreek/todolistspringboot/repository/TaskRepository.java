package no.patreek.todolistspringboot.repository;

import no.patreek.todolistspringboot.model.Task;
import no.patreek.todolistspringboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserOrderByCompletedAscDescriptionAsc(User user);
    List<Task> findByDueDate(LocalDate dueDate);
    Optional<Task> findByIdAndUser(Long id, User user);
    void deleteByIdAndUser(Long id, User user);
}