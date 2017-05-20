package com.javarush.test.level30.lesson15.big01.client;

import com.javarush.test.level30.lesson15.big01.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by lollipop on 10.07.2016.
 */
public class BotClient extends Client
{
    public static void main(String[] args)
    {
        BotClient botClient = new BotClient();
        botClient.run();
    }
    private ArrayList<Integer> list = new ArrayList<>();
    @Override
    protected SocketThread getSocketThread()
    {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSentTextFromConsole()
    {
        return false;
    }

    @Override
    protected String getUserName()
    {
        int number;
        while (true)
        {
            number = (int) (Math.random() * 99);
            if (!list.contains(number))
            {
                list.add(number);
                break;
            }
        }
        return "date_bot_" + number;
    }

    public class BotSocketThread extends SocketThread {
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException
        {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message)
        {
            if (message.contains(": "))
            {
                ConsoleHelper.writeMessage(message);
                String[] splitMessage = message.split(": ");
                Calendar calendar = new GregorianCalendar();
                SimpleDateFormat dateFormat;
                if (message.contains(": "))
                {
                    switch (splitMessage[1])
                    {
                        case "дата":
                        {
                            dateFormat = new SimpleDateFormat("d.MM.YYYY");
                            String date = dateFormat.format(calendar.getTime());
                            sendTextMessage("Информация для " + splitMessage[0] + ": " + date);
                            break;
                        }
                        case "день":
                        {
                            dateFormat = new SimpleDateFormat("d");
                            String date = dateFormat.format(calendar.getTime());
                            sendTextMessage("Информация для " + splitMessage[0] + ": " + date);
                            break;
                        }
                        case "месяц":
                        {
                            dateFormat = new SimpleDateFormat("MMMM");
                            String date = dateFormat.format(calendar.getTime());
                            sendTextMessage("Информация для " + splitMessage[0] + ": " + date);
                            break;
                        }
                        case "год":
                        {
                            dateFormat = new SimpleDateFormat("YYYY");
                            String date = dateFormat.format(calendar.getTime());
                            sendTextMessage("Информация для " + splitMessage[0] + ": " + date);
                            break;
                        }
                        case "время":
                        {
                            dateFormat = new SimpleDateFormat("H:mm:ss");
                            String date = dateFormat.format(calendar.getTime());
                            sendTextMessage("Информация для " + splitMessage[0] + ": " + date);
                            break;
                        }
                        case "час":
                        {
                            dateFormat = new SimpleDateFormat("H");
                            String date = dateFormat.format(calendar.getTime());
                            sendTextMessage("Информация для " + splitMessage[0] + ": " + date);
                            break;
                        }
                        case "минуты":
                        {
                            dateFormat = new SimpleDateFormat("m");
                            String date = dateFormat.format(calendar.getTime());
                            sendTextMessage("Информация для " + splitMessage[0] + ": " + date);
                            break;
                        }
                        case "секунды":
                        {
                            dateFormat = new SimpleDateFormat("s");
                            String date = dateFormat.format(calendar.getTime());
                            sendTextMessage("Информация для " + splitMessage[0] + ": " + date);
                            break;
                        }
                    }
                }
            }
        }
    }
}
