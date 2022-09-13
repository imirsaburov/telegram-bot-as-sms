package uz.imirsaburov.telegramsms.bot;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.imirsaburov.telegramsms.properties.TelegramBotProperties;
import uz.imirsaburov.telegramsms.services.BotUpdateService;

@Component
public class MessageHandler extends TelegramLongPollingBot {

    private final TelegramBotProperties telegramBotProperties;
    private final BotUpdateService botUpdateService;

    public MessageHandler(@Qualifier("bot-options") DefaultBotOptions options,
                          TelegramBotProperties telegramBotProperties,
                          BotUpdateService botUpdateService) {

        super(options);

        this.telegramBotProperties = telegramBotProperties;
        this.botUpdateService = botUpdateService;
    }

    @Override
    public String getBotUsername() {

        return telegramBotProperties.getUsername();
    }

    @Override
    public String getBotToken() {
        return telegramBotProperties.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());
        try {
            botUpdateService.resolve(update, sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
