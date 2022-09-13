package uz.imirsaburov.telegramsms.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import uz.imirsaburov.telegramsms.properties.TelegramBotProperties;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BotMessageSenderService {

    private final ObjectMapper objectMapper;
    private final TelegramBotProperties telegramBotProperties;
    private OkHttpClient okHttpClient;

    @Autowired
    public void setBeans(@Qualifier("bot-okHttpClient") OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }


    public void sendTextToChat(String chatId, String message) throws IOException {

        final String URL = "https://api.telegram.org"
                .concat("/bot")
                .concat(telegramBotProperties.getToken())
                .concat("/sendMessage");

        final String METHOD = HttpMethod.POST.name();

        final Map<String, String> requestBody = Map.of(
                "chat_id", chatId,
                "text", message
        );

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, objectMapper.writeValueAsString(requestBody));
        Request request = new Request.Builder()
                .url(URL)
                .method(METHOD, body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = okHttpClient.newCall(request).execute();

        if (response.code() != 200)
            throw new RuntimeException(response.body().string());
    }
}
