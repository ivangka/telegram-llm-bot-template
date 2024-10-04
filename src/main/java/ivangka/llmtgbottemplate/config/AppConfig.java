package ivangka.llmtgbottemplate.config;

import org.springframework.stereotype.Component;

@Component
public class AppConfig {

    private static final String BOT_TOKEN = "your_telegram_bot_token";
    private static final String API_TOKEN = "your_api_token";
    private static final String URI = "api_uri";

    public static String getApiToken() {
        return API_TOKEN;
    }

    public static String getUri() {
        return URI;
    }

    public static String getBotToken() {
        return BOT_TOKEN;
    }

}
