package no.patreek.todolistspringboot.controller;

import no.patreek.todolistspringboot.service.EmailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/test")
public class TestEmailController {

    private final EmailService emailService;

    public TestEmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/send-email")
    public String sendTestEmail() {
        emailService.sendReminderEmail(
                "patrik.thormodsen@gmail.com",
                "Test oppgave",
                LocalDate.now().plusDays(1)
        );
        return "Testmail sendt!";
    }
}
