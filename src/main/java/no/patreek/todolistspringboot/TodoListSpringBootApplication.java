package no.patreek.todolistspringboot;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoListSpringBootApplication {

    public static void main(String[] args) {

        Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing() // <-- Don’t crash if .env is missing
            .load();

        setIfPresent("SPRING_DATASOURCE_URL", dotenv.get("SPRING_DATASOURCE_URL"));
        setIfPresent("SPRING_DATASOURCE_USERNAME", dotenv.get("SPRING_DATASOURCE_USERNAME"));
        setIfPresent("SPRING_DATASOURCE_PASSWORD", dotenv.get("SPRING_DATASOURCE_PASSWORD"));
        setIfPresent("SPRING_DATASOURCE_DRIVER_CLASS_NAME", dotenv.get("SPRING_DATASOURCE_DRIVER_CLASS_NAME"));
        setIfPresent("SPRING_MAIL_USERNAME", dotenv.get("SPRING_MAIL_USERNAME"));
        setIfPresent("SPRING_MAIL_PASSWORD", dotenv.get("SPRING_MAIL_PASSWORD"));

        SpringApplication.run(TodoListSpringBootApplication.class, args);
    }

    private static void setIfPresent(String key, String value) {
        if (value != null && !value.isBlank()) {
            System.setProperty(key, value);
        } else {
            System.out.println("⚠️ Warning: Missing value for " + key + " in .env");
        }
    }
}
