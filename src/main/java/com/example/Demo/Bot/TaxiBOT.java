package com.example.Demo.Bot;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class TaxiBOT extends TelegramLongPollingBot {

    private final UserRepository repository;

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        SendMessage returnMessage = new SendMessage();
        Message message = update.getMessage();


        if (message.hasText()) {
            if (!message.getChat().isUserChat()) {
                //Optional<User> optional = this.repository.findById(message.getChatId());
                Pattern regexPattern = Pattern.compile(BotQuery.pattern);
                Matcher matcher = regexPattern.matcher(message.getText());

                if (message.getText().equals("/start")){
                    deleteMessage(String.valueOf(message.getChatId()), message.getMessageId());
                    returnMessage.setChatId(String.valueOf(message.getChatId()));
                    returnMessage.setText("Iltimos zakazingizni qoldiring bizda \n Hurmatli taksistlar Sizlarga yangilik Ramazon oyi munosabati bilan VIP SKITKADA ‼\uFE0F 5\uFE0F⃣0\uFE0F⃣ MING SOM  ");
                    InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                    List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                    List<InlineKeyboardButton> rowInline = new ArrayList<>();
                    List<InlineKeyboardButton> rowInline2 = new ArrayList<>();
                    InlineKeyboardButton button = new InlineKeyboardButton();
                    button.setText("Bot orqali zakaz berish");
                    button.setUrl("https://t.me/Tashkent_bekabad_bot");
                    InlineKeyboardButton button2 = new InlineKeyboardButton();
                    button2.setText("VIP");
                    button2.setUrl("https://t.me/Vip_taxi_admin");
                    rowInline.add(button);
                    rowInline2.add(button2);
                    rowsInline.add(rowInline);
                    rowsInline.add(rowInline2);
                    markupInline.setKeyboard(rowsInline);
                    returnMessage.setReplyMarkup(markupInline);
                    execute(returnMessage);
                }

                if (message.getFrom().getUserName() != null) {

                    if (matcher.find()) {
                        returnMessage.setChatId(String.valueOf(message.getChatId()));
                        returnMessage.setText("Xurmatli \n" + "Klient \n" + "Sizning zakasingiz shafyorlar guruhiga tushdi\n" + "\n" + "Lichkangizda Ishonchlik shafyorlarimiz kutmoqda\n" + "\n" + "Qulaylik uchun bot orqali zakas bering\uD83D\uDC47");

                        KeyboardRow firstRow = new KeyboardRow();

                        KeyboardButton vipInfoButton = new KeyboardButton();
                        vipInfoButton.setText("Vip haqida ‼\uFE0F");

                        firstRow.add(vipInfoButton);

                        List<KeyboardRow> rowList = new ArrayList<>();
                        rowList.add(firstRow);

                        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                        replyKeyboardMarkup.setResizeKeyboard(true);
                        replyKeyboardMarkup.setKeyboard(rowList);
                        returnMessage.setReplyMarkup(replyKeyboardMarkup);

                        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                        List<InlineKeyboardButton> rowInline = new ArrayList<>();
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText("Bot orqali zakaz qilish");
                        button.setUrl("https://t.me/Tashkent_bekabad_bot");
                        rowInline.add(button);
                        rowsInline.add(rowInline);
                        markupInline.setKeyboard(rowsInline);
                        returnMessage.setReplyMarkup(markupInline);

                        execute(returnMessage);
                        deleteMessage(String.valueOf(message.getChatId()), message.getMessageId());



                        //todo : 2- gr chat Id bolishi kerak
                        returnMessage.setChatId("-1002026490224");
                        returnMessage.setParseMode(ParseMode.HTML);
                        returnMessage.setText("<b><a href='tg://user?id=" + message.getFrom().getId() + "'>" + message.getFrom().getFirstName() + "</a>dan zakaz keldi: </b>\n\n" + "\n zakazi:  " + message.getText());
                        InlineKeyboardMarkup markupInline3 = new InlineKeyboardMarkup();
                        List<List<InlineKeyboardButton>> rowsInline3 = new ArrayList<>();
                        List<InlineKeyboardButton> rowInline3 = new ArrayList<>();
                        InlineKeyboardButton button3 = new InlineKeyboardButton();
                        button3.setText("ZAKAZCHI");
                        button3.setUrl("tg://user?id=" + message.getFrom().getId());
                        rowInline3.add(button3);
                        rowsInline3.add(rowInline3);
                        markupInline3.setKeyboard(rowsInline3);
                        returnMessage.setReplyMarkup(markupInline3);
                        execute(returnMessage);

                        // todo : vip grup id bolad

                        returnMessage.setChatId("-1002075492453");
                        returnMessage.setParseMode(ParseMode.HTML);
                        returnMessage.setText("<b><a href='tg://user?id=" + message.getFrom().getId() + "'>" + message.getFrom().getFirstName() + "</a>dan zakaz keldi: </b>\n\n" + message.getText());

                        InlineKeyboardMarkup btr = new InlineKeyboardMarkup();
                        List<List<InlineKeyboardButton>> gtr = new ArrayList<>();
                        List<InlineKeyboardButton> ntr = new ArrayList<>();
                        InlineKeyboardButton butto3 = new InlineKeyboardButton();
                        butto3.setText("ZAKAZCHI");
                        butto3.setUrl("tg://user?id=" + message.getFrom().getId());
                        ntr.add(butto3);
                        gtr.add(ntr);
                        btr.setKeyboard(gtr);
                        returnMessage.setReplyMarkup(btr);
                        execute(returnMessage);




                        //todo : ADMIN chat id olcak
                        returnMessage.setChatId("6589039111");
                        returnMessage.setParseMode(ParseMode.HTML);
                        returnMessage.setText("<b><a href='tg://user?id=" + message.getFrom().getId() + "'>" + message.getFrom().getFirstName() + "</a>dan zakaz keldi: </b>\n\n" + message.getText());

                        InlineKeyboardMarkup markupInline2 = new InlineKeyboardMarkup();
                        List<List<InlineKeyboardButton>> rowsInline2 = new ArrayList<>();
                        List<InlineKeyboardButton> rowInline2 = new ArrayList<>();
                        InlineKeyboardButton button2 = new InlineKeyboardButton();
                        button2.setText("ZAKAZCHI");
                        button2.setUrl("tg://user?id=" + message.getFrom().getId());
                        rowInline2.add(button2);
                        rowsInline2.add(rowInline2);
                        markupInline2.setKeyboard(rowsInline2);
                        returnMessage.setReplyMarkup(markupInline2);
                        execute(returnMessage);


                    } else if (message.getText().equals("Vip haqida ‼\uFE0F") || message.getText().equals("Orqaga") || message.getText().equals("Haydovchi\uD83D\uDE95")) {
                        deleteMessage(String.valueOf(message.getChatId()), message.getMessageId());
                        returnMessage.setChatId(String.valueOf(message.getChatId()));
                        returnMessage.setText("\uD83E\uDD29☪\uFE0F Ramazon oyi munosabati bilan \n  BIZDA SKITKA ‼\uFE0F‼\uFE0F  ‼\uFE0F 5\uFE0F⃣0\uFE0F⃣ MING SOM  ");
                        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                        List<InlineKeyboardButton> rowInline = new ArrayList<>();
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText("VIP QOSHILISH");
                        button.setUrl("https://t.me/Vip_taxi_admin");
                        rowInline.add(button);
                        rowsInline.add(rowInline);
                        markupInline.setKeyboard(rowsInline);
                        returnMessage.setReplyMarkup(markupInline);
                        execute(returnMessage);
                    } else if (message.getText().equals("Yolovchi\uD83E\uDDCD\u200D♂\uFE0F")) {
                        deleteMessage(String.valueOf(message.getChatId()), message.getMessageId());

                        returnMessage.setChatId(String.valueOf(message.getChatId()));
                        returnMessage.setText("Zakazingizni yozib qoldiring \n Bot orqali zakaz bering   ");
                        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                        List<InlineKeyboardButton> rowInline = new ArrayList<>();
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText("Bot orqali zakaz berish");
                        button.setUrl("https://t.me/Tashkent_bekabad_bot");
                        rowInline.add(button);
                        rowsInline.add(rowInline);
                        markupInline.setKeyboard(rowsInline);
                        returnMessage.setReplyMarkup(markupInline);
                        execute(returnMessage);
                    }
                } else if (message.getFrom().getUserName() == null && matcher.find()) {
                    deleteMessage(String.valueOf(message.getChatId()), message.getMessageId());

                    returnMessage.setChatId(String.valueOf(message.getChatId()));
                    returnMessage.setText("Hurmatli: " + message.getFrom().getFirstName() + " \n Iltimos siz bot orqali zakaz bering");
                    InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                    List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                    List<InlineKeyboardButton> rowInline = new ArrayList<>();
                    InlineKeyboardButton button = new InlineKeyboardButton();
                    button.setText("Bot orqali zakaz qilish");
                    button.setUrl("https://t.me/Tashkent_bekabad_bot");
                    rowInline.add(button);
                    rowsInline.add(rowInline);
                    markupInline.setKeyboard(rowsInline);
                    returnMessage.setReplyMarkup(markupInline);
                    execute(returnMessage);
                    stageSong(message);
                }
            } else if (message.getChat().isUserChat()) {
                Optional<User> optional = this.repository.findById(message.getChatId());
                if (message.getText().equals("/start")) {
                    returnMessage = stageContact(message);
                    execute(returnMessage);
                    if (optional.isEmpty()) {
                        User user = new User();
                        user.setChatId(update.getMessage().getChatId());
                        user.setUsername(update.getMessage().getChat().getUserName());
                        user.setFirstName(message.getChat().getFirstName());
                        repository.save(user);
                    } else {
                        if (optional.isPresent()) {
                            User user = optional.get();
                            user.setStep(BotQuery.FIRST);
                            repository.save(user);
                        }
                    }
                } else if (message.getText().equals("Yolovchi\uD83E\uDDCD\u200D♂\uFE0F")) {
                    if (optional.isPresent()) {
                        User user = optional.get();
                        user.setStep(BotQuery.SECOND);
                        repository.save(user);
                    }
                    returnMessage.setChatId(String.valueOf(message.getChatId()));
                    returnMessage.setText("Ilttmos oz zakazingizni qoldiring \n Nomer ozingiznikini yuboring❌\n\n Пожалуйста, разместите заказ ");
                    execute(returnMessage);
                } else if (message.getText().equals("Haydovchi\uD83D\uDE95")) {
                    returnMessage = stageVip(message);

                    execute(returnMessage);

                } else if (optional.get().getStep().equals(BotQuery.SECOND) && !optional.get().getContact().isEmpty()) {
                    if (optional.isPresent()) {
                        User user = optional.get();
                        user.setStep(BotQuery.FIRST);
                    }
                    returnMessage.setChatId(message.getChatId());
                    returnMessage.setText("Sizning habaringiz qabul qilindi✅✅ \n Ваше сообщение было получено ✅✅");
                    execute(returnMessage);
                    if (update.getMessage().getChat().getUserName() != null) {
                        //todo : adminn chat Id bolishi kerak
                        returnMessage.setChatId("538902740");
                        returnMessage.setParseMode(ParseMode.HTML);
                        returnMessage.setText("<b><a href='tg://user?id=" + message.getFrom().getId() + "'>" + message.getFrom().getFirstName() + "</a>dan zakaz keldi: </b>\n\n" + "nomer : +" + optional.get().getContact() + "\nzakazi:  " + message.getText());
                        InlineKeyboardMarkup markupInline3 = new InlineKeyboardMarkup();
                        List<List<InlineKeyboardButton>> rowsInline3 = new ArrayList<>();
                        List<InlineKeyboardButton> rowInline3 = new ArrayList<>();
                        InlineKeyboardButton button3 = new InlineKeyboardButton();
                        button3.setText("ZAKAZCHI");
                        button3.setUrl("tg://user?id=" + message.getFrom().getId());
                        rowInline3.add(button3);
                        rowsInline3.add(rowInline3);
                        markupInline3.setKeyboard(rowsInline3);
                        returnMessage.setReplyMarkup(markupInline3);
                        execute(returnMessage);

                        //ADMIN chat id olcak
                        returnMessage.setChatId("6589039111");
                        returnMessage.setParseMode(ParseMode.HTML);
                        returnMessage.setText("<b><a href='tg://user?id=" + message.getFrom().getId() + "'>" + message.getFrom().getFirstName() + "</a>dan zakaz keldi: </b>\n\n" + message.getText());

                        InlineKeyboardMarkup markupInline2 = new InlineKeyboardMarkup();
                        List<List<InlineKeyboardButton>> rowsInline2 = new ArrayList<>();
                        List<InlineKeyboardButton> rowInline2 = new ArrayList<>();
                        InlineKeyboardButton button2 = new InlineKeyboardButton();
                        button2.setText("ZAKAZCHI");
                        button2.setUrl("tg://user?id=" + message.getFrom().getId());
                        rowInline2.add(button2);
                        rowsInline2.add(rowInline2);
                        markupInline2.setKeyboard(rowsInline2);
                        returnMessage.setReplyMarkup(markupInline2);
                        execute(returnMessage);

                    } else {
                        //todo : adminn chat Id bolishi kerak
                        SendMessage sendMessage = new SendMessage();
                        sendMessage.setChatId("538902740");
                        sendMessage.setText(message.getFrom().getFirstName() + "\n\n" + "nomeri :+" + optional.get().getContact() + "\n zakazi:  " + message.getText());
                        sendMessage.setParseMode(ParseMode.HTML);
                        execute(sendMessage);

                        sendMessage.setChatId("6589039111");
                        sendMessage.setText(message.getFrom().getFirstName() + "\n\n" + "nomeri :+" + optional.get().getContact() + "\n zakazi:  " + message.getText());
                        sendMessage.setParseMode(ParseMode.HTML);
                        execute(sendMessage);

                    }
                } else if (optional.get().getStep().equals(BotQuery.FIRST)) {
                    
                }
            }
        } else if (message.hasContact()) {
            Optional<User> optional1 = this.repository.findById(message.getChatId());

            if (optional1.isPresent()) {
                User user = optional1.get();
                user.setContact(String.valueOf(message.getContact().getPhoneNumber()));
                repository.save(user);
            }
            returnMessage = stageSecond(message);
            execute(returnMessage);
        }
    }

    private void stageSong(Message message) {
        SendMessage returnMessage = new SendMessage();
        Optional<User> optional = this.repository.findById(message.getChatId());

        if (optional.isEmpty()) {
            returnMessage.setChatId("6589039111");
            returnMessage.setParseMode(ParseMode.HTML);
            returnMessage.setText("<b><a href='tg://user?id=" + message.getFrom().getId() + "'>" + message.getFrom().getFirstName() + "</a>dan zakaz keldi: </b>\n\n" + "\n zakazi:  " + message.getText());
            InlineKeyboardMarkup markupInline3 = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> rowsInline3 = new ArrayList<>();
            List<InlineKeyboardButton> rowInline3 = new ArrayList<>();
            InlineKeyboardButton button3 = new InlineKeyboardButton();
            button3.setText("ZAKAZCHI");
            button3.setUrl("tg://user?id=" + message.getFrom().getId());
            rowInline3.add(button3);
            rowsInline3.add(rowInline3);
            markupInline3.setKeyboard(rowsInline3);
            returnMessage.setReplyMarkup(markupInline3);
            try {
                execute(returnMessage);
            } catch (TelegramApiException e) {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId("6589039111");
                sendMessage.setText("Bu odamni nastrokasi yoppilgan");
                try {
                    execute(sendMessage);
                } catch (TelegramApiException ex) {
                    throw new RuntimeException(ex);
                }
            }
        } else {
            //todo : adminn chat Id bolishi kerak
            returnMessage.setChatId("6589039111");
            returnMessage.setParseMode(ParseMode.HTML);
            returnMessage.setText("<b><a href='tg://user?id=" + message.getFrom().getId() + "'>" + message.getFrom().getFirstName() + "</a>dan zakaz keldi: </b>\n\n" + "nomer : +" + optional.get().getContact() + "\n zakazi:  " + message.getText());
            InlineKeyboardMarkup markupInline3 = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> rowsInline3 = new ArrayList<>();
            List<InlineKeyboardButton> rowInline3 = new ArrayList<>();
            InlineKeyboardButton button3 = new InlineKeyboardButton();
            button3.setText("ZAKAZCHI");
            button3.setUrl("tg://user?id=" + message.getFrom().getId());
            rowInline3.add(button3);
            rowsInline3.add(rowInline3);
            markupInline3.setKeyboard(rowsInline3);
            returnMessage.setReplyMarkup(markupInline3);
            try {
                execute(returnMessage);
            } catch (TelegramApiException e) {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId("6589039111");
                sendMessage.setText("Bu odamni nastrokasi yoppilgan");
                try {
                    execute(sendMessage);
                } catch (TelegramApiException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        //vip grup id bolad
        returnMessage.setChatId("-1002075492453");
        returnMessage.setParseMode(ParseMode.HTML);
        returnMessage.setText("<b><a href='tg://user?id=" + message.getFrom().getId() + "'>" + message.getFrom().getFirstName() + "</a>dan zakaz keldi: </b>\n\n" + message.getText());

        InlineKeyboardMarkup markupInline2 = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline2 = new ArrayList<>();
        List<InlineKeyboardButton> rowInline2 = new ArrayList<>();
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("ZAKAZCHI");
        button2.setUrl("tg://user?id=" + message.getFrom().getId());
        rowInline2.add(button2);
        rowsInline2.add(rowInline2);
        markupInline2.setKeyboard(rowsInline2);
        returnMessage.setReplyMarkup(markupInline2);
        try {
            execute(returnMessage);
        } catch (TelegramApiException e) {
            System.out.println("Buklenti nastrokasi shuning zakazni chiqarib bolmaydi");
        }

        //todo : 2- gr chat Id bolishi kerak
        returnMessage.setChatId("-1002026490224");
        returnMessage.setParseMode(ParseMode.HTML);
        returnMessage.setText("<b><a href='tg://user?id=" + message.getFrom().getId() + "'>" + message.getFrom().getFirstName() + "</a>dan zakaz keldi: </b>\n\n" + message.getText());

        InlineKeyboardMarkup markupInline3 = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline3 = new ArrayList<>();
        List<InlineKeyboardButton> rowInline3 = new ArrayList<>();
        InlineKeyboardButton button3 = new InlineKeyboardButton();
        button3.setText("ZAKAZCHI");
        button3.setUrl("tg://user?id=" + message.getFrom().getId());
        rowInline3.add(button3);
        rowsInline3.add(rowInline3);
        markupInline3.setKeyboard(rowsInline3);
        returnMessage.setReplyMarkup(markupInline3);

        try {
            execute(returnMessage);
        } catch (TelegramApiException e) {
            System.out.println("Buklenti nastrokasi shuning zakazni chiqarib bolmaydi");
        }


    }

    private SendMessage stageVip(Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("\uD83E\uDD29☪\uFE0F Ramazon oyi munosabati bilan \n  BIZDA SKITKA ‼\uFE0F‼\uFE0F  ‼\uFE0F 5\uFE0F⃣0\uFE0F⃣ MING SOM  ");
        sendMessage.setChatId(String.valueOf(message.getChatId()));

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        List<InlineKeyboardButton> buttons = new ArrayList<>();


        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("Vipga ulanish");
        button1.setUrl("https://t.me/Vip_taxi_admin");
        buttons.add(button1);

        rows.add(buttons);
        inlineKeyboardMarkup.setKeyboard(rows);

        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }

    private SendMessage stageSecond(Message message) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Bosh menu \n" + " \n" + "Haydovchimisiz yoki Yulovchi?");
        sendMessage.setChatId(String.valueOf(message.getChatId()));

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow first = new KeyboardRow();
        KeyboardButton firstButton = new KeyboardButton();
        firstButton.setText("Haydovchi\uD83D\uDE95");
        KeyboardButton secButton = new KeyboardButton();
        secButton.setText("Yolovchi\uD83E\uDDCD\u200D♂\uFE0F");
        first.add(firstButton);
        first.add(secButton);
        rowList.add(first);
        replyKeyboardMarkup.setKeyboard(rowList);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);


        return sendMessage;
    }

    private SendMessage stageContact(Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Telefon nomeringizni jonating \n Отправьте свой номер телефона \n⬇️⬇️⬇️⬇️ ");
        sendMessage.setChatId(String.valueOf(message.getChatId()));
        KeyboardRow first = new KeyboardRow();
        KeyboardButton contactButton = new KeyboardButton();
        contactButton.setText("Contact");
        contactButton.setRequestContact(true);
        first.add(contactButton);
        List<KeyboardRow> rowList = new ArrayList<>();
        rowList.add(first);
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(rowList);
        replyKeyboardMarkup.setResizeKeyboard(true);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }


    public void deleteMessage(String chatId, int messageId) throws TelegramApiException {
        DeleteMessage deleteMessage = new DeleteMessage(chatId, messageId);
        execute(deleteMessage);
    }

    @Override
    public String getBotUsername() {
        return "@Tashkent_bekabad_bot";
    }

    @Override
    public String getBotToken() {
        return "6960202456:AAG6pdKt2ABxzUVSzwfI3LJZb3qeWP9PhxY";
    }

}
