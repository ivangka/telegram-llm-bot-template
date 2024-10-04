package ivangka.llmtgbottemplate.runner;

import ivangka.llmtgbottemplate.config.AppConfig;
import ivangka.llmtgbottemplate.controller.TelegramBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;

@Component
public class TelegramBotRunner implements CommandLineRunner {

    private final String botToken = AppConfig.getBotToken();

    private static final Logger logger = LoggerFactory.getLogger(TelegramBotRunner.class);


    @Override
    public void run(String... args) {
        try {
            TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication();
            botsApplication.registerBot(botToken, new TelegramBot(botToken));
            Thread.currentThread().join();
        } catch (Exception e) {
            logger.error("Error occurred while running the Telegram bot: " + e.getMessage() + "\n", e);
        }
    }

}