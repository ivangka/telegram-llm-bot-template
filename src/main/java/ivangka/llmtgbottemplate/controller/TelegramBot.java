package ivangka.llmtgbottemplate.controller;

import com.fasterxml.jackson.databind.JsonNode;
import ivangka.llmtgbottemplate.service.ApiService;
import ivangka.llmtgbottemplate.service.JsonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.io.IOException;

public class TelegramBot implements LongPollingSingleThreadUpdateConsumer {

    private final TelegramClient telegramClient;
    private final ApiService apiConfig;

    private static final Logger logger = LoggerFactory.getLogger(TelegramBot.class);

    public TelegramBot(String botToken) {
        this.telegramClient = new OkHttpTelegramClient(botToken);
        this.apiConfig = new ApiService();
    }

    @Override
    public void consume(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {

            if (update.getMessage().getText().equals("/start")) return;

            User user = update.getMessage().getFrom();
            String userInfo;
            if (user.getLastName() != null) {
                userInfo = user.getFirstName() + " " + user.getLastName() +
                        " (" + user.getUserName() + ", id:" + user.getId() + ")";
            } else {
                userInfo = user.getFirstName() +
                        " (" + user.getUserName() + ", id:" + user.getId() + ")";
            }

            String message_text = update.getMessage().getText();
            logger.debug("Received message from " + userInfo + ": "
                    + message_text.replaceAll("\\R", " "));
            long chat_id = update.getMessage().getChatId();
            String response = apiConfig.postRequest(message_text.replaceAll("\\R", " "));

            try {

                JsonNode node = JsonService.parseSubstring(response);
                response = node.get("generated_text").asText();
                SendMessage message = SendMessage
                        .builder()
                        .chatId(chat_id)
                        .text(response)
                        .build();
                telegramClient.execute(message);

                logger.debug("Generated response for " + userInfo + ": "
                        + response.replaceAll("\\R", " ") + "\n");

            } catch (IOException e) {
                logger.error("Failed to parse response from API: "
                        + e.getMessage().replaceAll("\\R", " "), e);
            } catch (TelegramApiException e) {
                logger.error("Failed to send message via Telegram API: "
                        + e.getMessage().replaceAll("\\R", " "), e);
            }

        }

    }

}
