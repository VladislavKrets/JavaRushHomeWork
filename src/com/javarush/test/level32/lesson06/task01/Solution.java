package com.javarush.test.level32.lesson06.task01;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

/* Генератор паролей
Реализуйте логику метода getPassword, который должен возвращать ByteArrayOutputStream, в котором будут байты пароля.
Требования к паролю:
1) 8 символов
2) только цифры и латинские буквы разного регистра
3) обязательно должны присутствовать цифры, и буквы разного регистра
Все сгенерированные пароли должны быть уникальные.
Пример правильного пароля:
wMh7SmNu
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String alphabet1 = "qwertyuiopasdfghjklzxcvbnm";
        String alphabet2 =  "QWERTYUIOPASDFGHJKLZXCVBNM";
        String alphabet3 = "1234567890";

        StringBuilder builder = new StringBuilder("7hI");
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int N = random.nextInt(3);
            switch (N)
            {
                case 0:  builder.append(alphabet1.charAt(random.nextInt(alphabet1.length())));
                    break;
                case 1: builder.append(alphabet2.charAt(random.nextInt(alphabet2.length())));
                    break;
                case 2: builder.append(alphabet3.charAt(random.nextInt(alphabet3.length())));
            }

        }
        try
        {
            outputStream.write(builder.toString().getBytes());
            return outputStream;
        }
        catch (IOException e)
        {

        }
        return outputStream;
    }
}
