package uz.imirsaburov.telegramsms.components;

import uz.imirsaburov.telegramsms.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduleComponent {

    private final MessageService messageService;

    @Scheduled(fixedDelay = 1000)
    public void sendMessages() {
        messageService.sendAllMessages();
    }
}
