package no.patreek.todolistspringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendReminderEmail(String to, String taskDescription, LocalDate dueDate) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Påminnelse: Oppgave forfaller i morgen");
        message.setText("Oppgaven \"" + taskDescription + "\" forfaller i morgen (" + dueDate + ").");
        mailSender.send(message);
    }
}
