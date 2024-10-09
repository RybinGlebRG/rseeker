package ru.rerumu.rseeker.infrastructure.telegram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.rerumu.rseeker.controller.telegram.TelegramDialogController;

@Service
public class TelegramBotService {

    @Autowired
    public TelegramBotService(
            @Value("${telegram.botToken}") String botToken,
            TelegramDialogController telegramDialogController
    ) throws TelegramApiException {
        TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication();
        botsApplication.registerBot(botToken, telegramDialogController);
    }
}
