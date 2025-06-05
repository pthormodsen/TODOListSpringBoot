package no.patreek.todolistspringboot.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name ="task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private boolean completed;
    private LocalDate dueDate;

    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Task() {}

    public Task(String description) {
        this.description = description;
        this.completed = false; // default verdi
    }

    public Task(String description, User user) {
        this.description = description;
        this.completed = false;
        this.user = user;
    }

    public Task(String description, LocalDate dueDate, User user) {
        this.description = description;
        this.dueDate = dueDate;
        this.completed = false;
        this.user = user;
    }

    public Task(String description, boolean completed) {
        this.description = description;
        this.completed = completed;
    }

    // Gettere og settere

    public String getTitle() {
        return description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void markDone() {
        this.completed = true;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        String status = completed ? "\u2713" : "\u25A1";  // ✓ eller □
        return String.format("%s  %s", status, description);
    }
}