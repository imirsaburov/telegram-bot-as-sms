package uz.imirsaburov.telegramsms;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TelegramBotApplication {

    @SneakyThrows
    public static void main(String[] args) {
        SpringApplication.run(TelegramBotApplication.class, args);
    }
}
