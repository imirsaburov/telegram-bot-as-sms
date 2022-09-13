package uz.imirsaburov.telegramsms.services;

import uz.imirsaburov.telegramsms.resolvers.ContactResolver;
import uz.imirsaburov.telegramsms.resolvers.TextResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
public class BotUpdateService {

    private final ChatService chatService;
    private final ContactResolver contactResolver;
    private final TextResolver textResolver;

    public void resolve(Update update, SendMessage sendMessage) {
        if (update.getMessage().hasContact())
            contactResolver.resolve(update, sendMessage);

        if (update.getMessage().hasText())
            textResolver.resolve(update, sendMessage);
    }
}
