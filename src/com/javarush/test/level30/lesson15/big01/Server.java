package com.javarush.test.level30.lesson15.big01;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lollipop on 09.07.2016.
 */
public class Server
{
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();
    public static void main(String[] args)
    {
        ConsoleHelper.writeMessage("Введите номер порта:");
        int port = ConsoleHelper.readInt();
        try (ServerSocket serverSocket=new ServerSocket(port)){
            ConsoleHelper.writeMessage("Сервер запущен");
            while (true) {
                try
                {
                    Socket socket = serverSocket.accept();
                    Handler handler = new Handler(socket);
                    handler.start();
                }
                catch (Exception e) {
                    ConsoleHelper.writeMessage("Ошибка сокета");
                    break;
                }
            }
        }
        catch (IOException e)
        {
            ConsoleHelper.writeMessage("Ошибка сокета");
        }

    }
    public static void sendBroadcastMessage(Message message) {
        Connection connection;
        for (Map.Entry<String, Connection> pair : connectionMap.entrySet()) {
            connection = pair.getValue();
            try
            {
                connection.send(message);
            }
            catch (IOException e)
            {
                ConsoleHelper.writeMessage("Не удалось отправить сообщение");
            }
        }
    }
    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket)
        {
            this.socket = socket;
        }

        @Override
        public void run()
        {
            ConsoleHelper.writeMessage("Установлено новое соединение с удаленным адресом: " + socket.getRemoteSocketAddress());
            String name = null;
            try (Connection connection = new Connection(socket))
            {
                name = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, name));
                sendListOfUsers(connection, name);
                serverMainLoop(connection, name);

            }
            catch (IOException | ClassNotFoundException e)
            {
                ConsoleHelper.writeMessage("Произошла ошибка при обмене данными с удаленным адресом");
            }
            if (name != null)
            {
                connectionMap.remove(name);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED, name));
            }
            ConsoleHelper.writeMessage("Cоединение с удаленным адресом закрыто");
        }
        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message reply = connection.receive();
                if (reply.getType() == MessageType.USER_NAME) {
                    if (reply.getData() != null && !reply.getData().isEmpty()) {
                        if (!connectionMap.containsKey(reply.getData())) {
                            connectionMap.put(reply.getData(), connection);
                            connection.send(new Message(MessageType.NAME_ACCEPTED));
                            return reply.getData();
                        }
                    }
                }
            }
        }
        private void sendListOfUsers(Connection connection, String userName) throws IOException {
            for (Map.Entry<String, Connection> pair : connectionMap.entrySet()) {
                if (!pair.getKey().equals(userName)) {
                    connection.send(new Message(MessageType.USER_ADDED, pair.getKey()));
                }
            }
        }
        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {

                while (true) {
                    Message message = connection.receive();
                    if (message.getType() == MessageType.TEXT) {
                        String text = userName + ": " + message.getData();
                        sendBroadcastMessage(new Message(MessageType.TEXT, text));
                    } else {
                        ConsoleHelper.writeMessage("Сообщение не является текстом");
                    }
                }

        }
    }
}
