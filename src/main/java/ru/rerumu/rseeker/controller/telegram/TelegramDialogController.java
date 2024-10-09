package ru.rerumu.rseeker.controller.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import ru.rerumu.rseeker.domain.SearchRequest;
import ru.rerumu.rseeker.domain.SearchResponse;
import ru.rerumu.rseeker.process.Finder;

@Component
@Slf4j
public class TelegramDialogController implements LongPollingSingleThreadUpdateConsumer {

    private final TelegramClient telegramClient;
    private final Finder finder;

    @Autowired
    public TelegramDialogController(@Value("${telegram.botToken}") String botToken, Finder finder) {
        telegramClient = new OkHttpTelegramClient(botToken);
        this.finder = finder;
    }

    @Override
    public void consume(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            log.info(update.getMessage().getText());
            SearchRequest searchRequest= new SearchRequest(update.getMessage().getText());

            SearchResponse searchResponse = finder.find(searchRequest);

            // Create your send message object
            SendMessage sendMessage = new SendMessage(update.getMessage().getChatId().toString(), searchResponse.getText());
            try {
                // Execute it
                telegramClient.execute(sendMessage);
            } catch (TelegramApiException e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}
