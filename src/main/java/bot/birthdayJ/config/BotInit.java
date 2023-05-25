package bot.birthdayJ.config;

import bot.birthdayJ.service.TelegramBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
@EnableScheduling
public class BotInit {
    @Autowired
    TelegramBot bot;

    @EventListener({ContextRefreshedEvent.class})
    @Scheduled(cron = "0 7 15 * * ?", zone = "Europe/Moscow")
    public void init() throws TelegramApiException{
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
                try{
                    telegramBotsApi.registerBot(bot);
                }catch (TelegramApiException e){

                }
    }

}
