import org.json.JSONObject;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "";
    }

    @Override
    public String getBotToken() {
        return "";
    }



    @Override
    public void onUpdateReceived(Update update) {
        try {
            List<City> list = init();
            String message = update.getMessage().getText();

            City city = null;
            for (City c: list){
                if (c.getName().equals(message)){
                    city = c;
                    break;
                }
            }

            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            SendMessage sendMessage = new SendMessage();
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
            replyKeyboardMarkup.setSelective(true);
            replyKeyboardMarkup.setResizeKeyboard(true);
            replyKeyboardMarkup.setOneTimeKeyboard(false);
            List<KeyboardRow> keyboard = new ArrayList<>();
            KeyboardRow keyboardRow = new KeyboardRow();
            keyboardRow.add("Минск");
            keyboardRow.add("Гомель");
            keyboardRow.add("Гродно");
            KeyboardRow keyboardRow1 = new KeyboardRow();
            keyboardRow1.add("Витебск");
            keyboardRow1.add("Могилев");
            keyboardRow1.add("Брест");
            keyboard.add(keyboardRow);
            keyboard.add(keyboardRow1);
            replyKeyboardMarkup.setKeyboard(keyboard);

            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?lat="+city.getLat()+"&lon="+city.getLon()+"&appid=8214b5994813fe7379c043e827584008");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            String str = bufferedReader.readLine();
            JSONObject jsonObject = new JSONObject(str);
            JSONObject weather = (JSONObject) jsonObject.get("main");
            String temp = String.valueOf(weather.getBigDecimal("temp"));
            double t = Double.parseDouble(temp);
            t -= 273;
            t = Math.round(t);

            SendPhoto sendPhoto = new SendPhoto();

            switch (update.getMessage().getText()){
                case "Минск":
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    sendMessage.setText(Emoji.MINSK.get() + " Сейчас в "+ city.getName()+ "е "+ String.valueOf(t) +" градусов");

                    sendPhoto.setChatId(update.getMessage().getChatId().toString());
                    sendPhoto.setPhoto(new InputFile("https://cdn2.tu-tu.ru/image/pagetree_node_data/1/0275d27433408ef3ff3b6610f6db42b3/"));
                    execute(sendPhoto);
                    break;
                case "Гомель":
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    sendMessage.setText(Emoji.GOMEL.get() + " Сейчас в "+ city.getName().substring(0,5)+"е "+ String.valueOf(t) +" градусов");

                    sendPhoto.setChatId(update.getMessage().getChatId().toString());
                    sendPhoto.setPhoto(new InputFile("https://gp.by/upload/medialibrary/a47/a47583bc5906ce653798929b9736666c.jpg"));
                    execute(sendPhoto);
                    break;
                case "Брест":
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    sendMessage.setText(Emoji.BREST.get() + " Сейчас в "+ city.getName()+"е "+ String.valueOf(t) +" градусов");

                    sendPhoto.setChatId(update.getMessage().getChatId().toString());
                    sendPhoto.setPhoto(new InputFile("https://planetabelarus.by/upload/medialibrary/823/823e42e35d5ad2194380444c15a8be9e.jpg"));
                    execute(sendPhoto);
                    break;
                case "Витебск":
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    sendMessage.setText(Emoji.VITEBSK.get() + " Сейчас в "+ city.getName()+"е "+ String.valueOf(t) +" градусов");

                    sendPhoto.setChatId(update.getMessage().getChatId().toString());
                    sendPhoto.setPhoto(new InputFile("https://planetabelarus.by/upload/medialibrary/eb9/eb90f2aac9a5d6d35cecf100e20b9540.jpg"));
                    execute(sendPhoto);
                    break;
                case "Гродно":
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    sendMessage.setText(Emoji.GRODNO.get() + " Сейчас в "+ city.getName() + " " + String.valueOf(t) +" градусов");

                    sendPhoto.setChatId(update.getMessage().getChatId().toString());
                    sendPhoto.setPhoto(new InputFile("https://planetabelarus.by/upload/medialibrary/1fa/1fa2a7fc355fb9d451d0b54860ad180a.jpg"));
                    execute(sendPhoto);
                    break;
                case "Могилев":
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    sendMessage.setText(Emoji.MOGILEV.get() + " Сейчас в "+ city.getName()+"е "+ String.valueOf(t) +" градусов");

                    sendPhoto.setChatId(update.getMessage().getChatId().toString());
                    sendPhoto.setPhoto(new InputFile("https://masheka.by/uploads/posts/2016-08/1470768004_dubrovenka-leto-2016.jpg"));
                    execute(sendPhoto);
                    break;
                default:
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    sendMessage.setText("Такого города нет в базе данных");
            }
            execute(sendMessage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public List<City> init(){
        List<City> list = new ArrayList<>();
        City city = new City("Минск","53.893009", "27.567444");
        City city2 = new City("Витебск", "55.1904", "30.2049");
        City city3 = new City("Гродно", "53.6884", "23.8258");
        City city1 = new City("Могилев", "53.8981", "30.3325");
        City city4 = new City("Брест", "52.0975", "23.6877");
        City city5 = new City("Гомель", "52.4345", "30.9754");

        list.add(city);
        list.add(city1);
        list.add(city2);
        list.add(city3);
        list.add(city4);
        list.add(city5);

        return list;
    }
}
