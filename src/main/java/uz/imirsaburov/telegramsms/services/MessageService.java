package uz.imirsaburov.telegramsms.services;

import uz.imirsaburov.telegramsms.dtos.MessageCreateDTO;
import uz.imirsaburov.telegramsms.entities.ChatEntity;
import uz.imirsaburov.telegramsms.entities.MessageEntity;
import uz.imirsaburov.telegramsms.enums.MessageStatus;
import uz.imirsaburov.telegramsms.repositories.MessageRepository;
import uz.imirsaburov.telegramsms.utils.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final ChatService messageService;
    private final BotMessageSenderService botMessageSenderService;

    public void create(MessageCreateDTO dto) {
        String phone = CommonUtil.getOnlyNumbers(dto.getPhone());

        MessageEntity entity = new MessageEntity();
        entity.setPhone(phone);
        entity.setContent(dto.getContent());
        entity.setStatus(MessageStatus.PENDING);
        messageRepository.save(entity);

        Optional<ChatEntity> optionalChatEntity = messageService.getByPhone(phone);
        if (optionalChatEntity.isPresent()) {
            entity.setChat(optionalChatEntity.get());
            entity.setStatus(MessageStatus.CHAT_FOUND);
        } else {
            entity.setStatus(MessageStatus.CHAT_NOT_FOUND);
        }

        messageRepository.save(entity);
    }

    public void sendAllMessages() {
        for (MessageEntity entity : messageRepository.findAllByStatus(MessageStatus.CHAT_FOUND)) {

            try {
                ChatEntity chat = entity.getChat();

                botMessageSenderService.sendTextToChat(chat.getChatId(), entity.getContent());
                entity.setStatus(MessageStatus.SENT);
            } catch (Throwable e) {

                entity.setStatus(MessageStatus.CAN_NOT_SENT);

                e.printStackTrace();
            } finally {
                messageRepository.save(entity);

            }

        }
    }
}
