package uz.imirsaburov.telegramsms.properties;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties("bot")
@Configuration
public class TelegramBotProperties {
    private String username;
    private String token;

    @JsonProperty("proxy-enabled")
    private Boolean proxyEnabled;
}
