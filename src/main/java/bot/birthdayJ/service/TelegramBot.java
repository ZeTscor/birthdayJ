package bot.birthdayJ.service;

import bot.birthdayJ.config.BotConfig;
import bot.birthdayJ.model.Birthday;
import bot.birthdayJ.model.BirthdayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


@Component
@EnableScheduling
public class TelegramBot extends TelegramLongPollingBot {
    final BotConfig config;

    public TelegramBot(BotConfig config) {
        this.config = config;
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }
    public Long smeChatId;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            switch (messageText) {
                case "/start":
                    smeChatId = chatId;
                    startCommandReceived(chatId);
                    BirdayCommandReceived();
            }
        }

    }
    @Autowired
    private BirthdayRepository birthdayRepository;
    private void startCommandReceived(long chatId) {
            sendMessage(chatId, "");
            smeChatId = chatId;
            System.out.println(chatId);
        }

    @Scheduled(cron = "0 0 9 * * ?", zone = "Europe/Moscow")
    private void BirdayCommandReceived() {
        LocalDate today = LocalDate.now(ZoneId.of("Europe/Moscow"));
        List<Birthday> birthdays = birthdayRepository.findAllByDate(today);
        if (!birthdays.isEmpty()) {
            String messages = "Сегодня день рождения:";
            for (Birthday birthday : birthdays) {
                messages += "\n- " + birthday.getName();
            }
            messages += "\nПошлите им свои наилучшие пожелания! ! 🎉🎂🎁";
            sendMessage(smeChatId, messages);
            System.out.println(smeChatId);
        }
    }

    private void  sendMessage(long chatId, String textSend){
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textSend);
        try {
            execute(message);
        }catch (TelegramApiException e){

        }
    }
}



