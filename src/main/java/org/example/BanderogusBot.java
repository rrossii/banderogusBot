package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.File;
import java.util.*;

public class BanderogusBot extends TelegramLongPollingBot {

    private Map<Long, Integer> levels = new HashMap<>();
    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(new BanderogusBot());
    }

    @Override
    public String getBotUsername() {
        return "first_username_bot";
    }

    @Override
    public String getBotToken() {
        return "5836382304:AAFK25h_8dHHA-SnOK66pht_Loms19O2pd8";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Long chatId = getChatId(update);

        if (update.hasMessage() && update.getMessage().getText().equals("/start")) {
            sendImage("level-1", chatId);

            SendMessage message = createMessage("Ґа-ґа-ґа!\n" +
                    "Вітаємо у боті біолабораторії «Батько наш Бандера».\n\n" +
                    "Ти отримуєш гусака №71\n\n" +
                    "Цей бот ми створили для того, щоб твій гусак прокачався з рівня звичайної свійської худоби до рівня біологічної зброї, здатної нищити ворога.\n" +
                    "Щоб звичайний гусак перетворився на бандерогусака, тобі необхідно:\n" +
                    "✔️виконувати завдання\n" +
                    "✔️переходити на наступні рівні\n" +
                    "✔️заробити достатню кількість монет, щоб придбати Джавеліну і зробити свєрхтра-та-та.\n\n" +
                    "*Гусак звичайний.* Стартовий рівень.\nБонус: 5 монет.\n" +
                    "Обери завдання, щоб перейти на наступний рівень"
            );

            List<String> buttons = Arrays.asList(
                    "Сплести маскувальну сітку (+15 монет)",
                    "Зібрати кошти патріотичними піснями (+15 монет)",
                    "Вступити в Міністерство Мемів України (+15 монет)",
                    "Запустити волонтерську акцію (+15 монет)",
                    "Вступити до лав тероборони (+15 монет)"
            );

            buttons = getRandomThreeTasks(buttons);

            attachButtons(message, Map.of(
                    buttons.get(0), "level_1_btn",
                    buttons.get(1), "level_1_btn",
                    buttons.get(2), "level_1_btn"
            ));


            message.setChatId(chatId);
            sendApiMethodAsync(message);
        }

        if (update.hasCallbackQuery()) {
            if (update.getCallbackQuery().getData().equals("level_1_btn") && getLevel(chatId) == 1) {
                setLevel(chatId, 2);

                sendImage("level-2", chatId);

                SendMessage message = createMessage("*Вітаємо на другому рівні! Твій гусак - біогусак.*\n" +
                        "Баланс: 20 монет. \n" +
                        "Обери завдання, щоб перейти на наступний рівень"
                );

                List<String> buttons = Arrays.asList(
                        "Зібрати комарів для нової біологічної зброї (+15 монет)",
                        "Пройти курс молодого бійця (+15 монет)",
                        "Задонатити на ЗСУ (+15 монет)",
                        "Збити дрона банкою огірків (+15 монет)",
                        "Зробити запаси коктейлів Молотова (+15 монет)"
                );

                buttons = getRandomThreeTasks(buttons);

                attachButtons(message, Map.of(
                        buttons.get(0), "level_2_btn",
                        buttons.get(1), "level_2_btn",
                        buttons.get(2), "level_2_btn"
                ));

                message.setChatId(chatId);
                sendApiMethodAsync(message);

            }
            if (update.getCallbackQuery().getData().equals("level_2_btn") && getLevel(chatId) == 2) {
                setLevel(chatId, 3);

                sendImage("level-3", chatId);

                SendMessage message = createMessage("\n" +
                        "*Вітаємо на третьому рівні! Твій гусак - бандеростажер.*\n" +
                        "Баланс: 35 монет. \n" +
                        "Обери завдання, щоб перейти на наступний рівень"
                );
                List<String> buttons = Arrays.asList(
                        "Злітати на тестовий рейд по чотирьох позиціях (+15 монет)",
                        "Відвезти гуманітарку на передок (+15 монет)",
                        "Знайти зрадника та здати в СБУ (+15 монет)",
                        "Навести арту на орків (+15 монет)",
                        "Притягнути танк трактором (+15 монет)"
                );
                buttons = getRandomThreeTasks(buttons);

                attachButtons(message, Map.of(
                        buttons.get(0), "level_3_btn",
                        buttons.get(1), "level_3_btn",
                        buttons.get(2), "level_3_btn"
                ));

                message.setChatId(chatId);
                sendApiMethodAsync(message);

            }
            if (update.getCallbackQuery().getData().equals("level_3_btn") && getLevel(chatId) == 3) {
                setLevel(chatId, 4);

                sendImage("level-4", chatId);

                SendMessage message = createMessage(
                        "*Вітаємо на останньому рівні! Твій гусак - готова біологічна зброя - бандерогусак.*\n" +
                        "Баланс: 50 монет. \n" +
                        "Тепер ти можеш придбати Джавелін і глушити чмонь\n"
                );

                attachButtons(message, Map.of(
                        "Купити Джавелін (50 монет)", "level_4_btn"
                ));

                message.setChatId(chatId);
                sendApiMethodAsync(message);
            }
            if (update.getCallbackQuery().getData().equals("level_4_btn") && getLevel(chatId) == 4) {

                SendMessage message = createMessage("*Джавелін твій. Повний вперед!*");

                sendImage("final", chatId);

                message.setChatId(chatId);
                sendApiMethodAsync(message);
            }
        }
    }

    public SendMessage createMessage(String text) {
        SendMessage msg = new SendMessage();
        msg.setText(text);
        msg.setParseMode("markdown");
        return msg;
    }

    public Long getChatId(Update update) {
        if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getFrom().getId();
        }
        if (update.hasMessage()) {
            return update.getMessage().getFrom().getId();
        }
        return null;
    }

    public void attachButtons(SendMessage message, Map<String, String> buttons) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        for (String buttonName : buttons.keySet()) {
            String buttonValue = buttons.get(buttonName);

            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(buttonName);
            button.setCallbackData(buttonValue);

            keyboard.add(Arrays.asList(button));
        }

        markup.setKeyboard(keyboard);
        message.setReplyMarkup(markup);
    }

    public void sendImage(String name, Long chatId) {
        SendAnimation animation = new SendAnimation();

        InputFile inputFile = new InputFile();
        inputFile.setMedia(new File("images/" + name + ".gif"));

        animation.setAnimation(inputFile);
        animation.setChatId(chatId);

        executeAsync(animation);
    }

    public int getLevel(Long chatId) {
        return levels.getOrDefault(chatId, 1);
    }

    public void setLevel(Long chatId, int level) {
        levels.put(chatId, level);
    }

    public List<String> getRandomThreeTasks(List<String> allTasks) {
        ArrayList<String> copy = new ArrayList<>(allTasks);
        Collections.shuffle(copy);

        return copy.subList(0, 3);
    }
}