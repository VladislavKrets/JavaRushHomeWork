package com.javarush.test.level30.lesson15.big01.client;

import com.javarush.test.level30.lesson15.big01.Connection;
import com.javarush.test.level30.lesson15.big01.ConsoleHelper;
import com.javarush.test.level30.lesson15.big01.Message;
import com.javarush.test.level30.lesson15.big01.MessageType;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by lollipop on 09.07.2016.
 */
public class Client
{
    protected Connection connection;
    private volatile boolean clientConnected = true;

    public static void main(String[] args)
    {
        Client client = new Client();
        client.run();
    }

    protected String getServerAddress()
    {
        ConsoleHelper.writeMessage("Введите ваш ip:");
        String ip = ConsoleHelper.readString();
        if (ip.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}") || ip.equals("localhost")) return ip;
        return null;
    }

    protected int getServerPort()
    {
        ConsoleHelper.writeMessage("Введите номер порта:");
        return ConsoleHelper.readInt();
    }

    protected String getUserName()
    {
        ConsoleHelper.writeMessage("Введите имя пользователя:");
        return ConsoleHelper.readString();
    }

    protected boolean shouldSentTextFromConsole()
    {
        return true;
    }

    protected SocketThread getSocketThread()
    {
        return new SocketThread();
    }

    protected void sendTextMessage(String text)
    {
        Message message = new Message(MessageType.TEXT, text);
        try
        {
            connection.send(message);
        }
        catch (IOException e)
        {
            ConsoleHelper.writeMessage("Ошибка во время отправки сообщения");
            clientConnected = false;

        }
    }
        public void run(){
        {
            SocketThread thread = getSocketThread();
            thread.setDaemon(true);
            thread.start();
            try
            {
                synchronized (this)
                {
                    this.wait();
                }
            }
            catch (InterruptedException e)
            {
                ConsoleHelper.writeMessage("Произошла ошибка во время выполнения программы");
                return;
            }

            if (clientConnected)
            {
                ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду 'exit'.");
            } else ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");

            String line;
            while (clientConnected)
            {
                line = ConsoleHelper.readString();
                if (line.equals("exit")) break;
                if (shouldSentTextFromConsole())
                {
                    sendTextMessage(line);
                }
            }
        }

    }

    public class SocketThread extends Thread
    {
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
        }
        protected void informAboutAddingNewUser(String userName) {
            ConsoleHelper.writeMessage(userName + " присоединился к чату");
        }
        protected void informAboutDeletingNewUser(String userName) {
            ConsoleHelper.writeMessage(userName + " покинул чат");
        }
        protected void notifyConnectionStatusChanged(boolean clientConnected) {
            Client.this.clientConnected = clientConnected;
            synchronized (Client.this) {
                Client.this.notify();
            }
        }
        protected void clientHandshake() throws IOException, ClassNotFoundException {
            Message message;
            while (true) {

                    message = connection.receive();

                    if (message.getType() == MessageType.NAME_REQUEST)
                    {
                        connection.send(new Message(MessageType.USER_NAME, getUserName()));
                    } else if (message.getType() == MessageType.NAME_ACCEPTED)
                    {
                        notifyConnectionStatusChanged(true);
                        return;
                    } else throw new IOException("Unexpected MessageType");

            }
        }
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            Message message;
            while (true) {
                message = connection.receive();
                if (message.getType() == MessageType.TEXT) {
                    processIncomingMessage(message.getData());
                }
                else if (message.getType() == MessageType.USER_ADDED) {
                    informAboutAddingNewUser(message.getData());
                }
                else if (message.getType() == MessageType.USER_REMOVED) {
                    informAboutDeletingNewUser(message.getData());
                }
                else throw new IOException("Unexpected MessageType");
            }
        }
        @Override
        public void run()
        {

            try(Socket socket = new Socket(getServerAddress(), getServerPort()))
            {
                connection = new Connection(socket);
                clientHandshake();
                clientMainLoop();
            }
            catch (IOException | ClassNotFoundException e)
            {
                notifyConnectionStatusChanged(false);
            }

        }
    }
}

