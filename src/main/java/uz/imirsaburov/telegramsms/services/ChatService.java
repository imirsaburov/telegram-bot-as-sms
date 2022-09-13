package uz.imirsaburov.telegramsms.services;

import uz.imirsaburov.telegramsms.entities.ChatEntity;
import uz.imirsaburov.telegramsms.repositories.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;

    public ChatEntity create(String chatId) {
        ChatEntity entity = chatRepository.findFirstByChatId(chatId).orElseGet(ChatEntity::new);
        entity.setChatId(chatId);
        return chatRepository.save(entity);
    }

    public void setPhone(String chatId, String phone) {
        ChatEntity entity = chatRepository.findFirstByChatId(chatId).orElseThrow();
        entity.setPhone(phone);
        chatRepository.save(entity);
    }

    public ChatEntity getByChatId(String chatId) {
        return chatRepository.findFirstByChatId(chatId).orElseThrow();
    }

    public Optional<ChatEntity> getByPhone(String phone) {
        return chatRepository.findFirstByPhone(phone);
    }
}
