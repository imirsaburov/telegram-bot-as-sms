package uz.imirsaburov.telegramsms.configurations;

import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import uz.imirsaburov.telegramsms.properties.ProxyProperties;
import uz.imirsaburov.telegramsms.properties.TelegramBotProperties;

import java.net.InetSocketAddress;
import java.net.Proxy;

@Configuration
@RequiredArgsConstructor
public class CustomBeans {

    private final TelegramBotProperties telegramBotProperties;
    private final ProxyProperties proxyProperties;

    @Bean("bot-options")
    public DefaultBotOptions telegramBotsOptions() {

        RequestConfig.Builder requestConfigBuilder = RequestConfig.custom();
        if (telegramBotProperties.getProxyEnabled())
            requestConfigBuilder.setProxy(new HttpHost(proxyProperties.getHost(), proxyProperties.getPort()));

        RequestConfig requestConfig = requestConfigBuilder.build();

        DefaultBotOptions botOptions = new DefaultBotOptions();
        botOptions.setRequestConfig(requestConfig);
        return botOptions;
    }

    @Bean("bot-okHttpClient")
    public OkHttpClient telegramBotOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if (telegramBotProperties.getProxyEnabled())
            builder.proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyProperties.getHost(), proxyProperties.getPort())));

        return builder.build();
    }
}
