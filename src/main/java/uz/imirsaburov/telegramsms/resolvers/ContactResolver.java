package uz.imirsaburov.telegramsms.resolvers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.*;
import uz.imirsaburov.telegramsms.services.ChatService;
import uz.imirsaburov.telegramsms.utils.CommonUtil;

@Service
@RequiredArgsConstructor
public class ContactResolver {

    private final ChatService chatService;

    public void resolve(Update update, SendMessage sendMessage) {

        Message message = update.getMessage();
        Chat chat = message.getChat();
        User from = message.getFrom();
        Contact contact = message.getContact();
        String chatId = Long.toString(chat.getId());

        if (!contact.getUserId().equals(from.getId())) {
            sendMessage.setText("Бу сизнинг рақамингиз эмас, илтимос ўзингизни рақамингизни жўнатинг!");
            return;
        }

        chatService.create(chatId);
        chatService.setPhone(chatId, CommonUtil.getOnlyNumbers(contact.getPhoneNumber()));

        sendMessage.setText("Телефон рақам қабул қилинди!");
    }
}
