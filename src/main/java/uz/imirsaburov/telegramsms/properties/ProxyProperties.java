package uz.imirsaburov.telegramsms.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties("proxy")
@Configuration
public class ProxyProperties {
    private String host;
    private Integer port;
}
