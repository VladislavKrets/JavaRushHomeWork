package com.javarush.test.level18.lesson10.home02;

/* Пробелы
В метод main первым параметром приходит имя файла.
Вывести на экран соотношение количества пробелов к количеству всех символов. Например, 10.45
1. Посчитать количество всех символов.
2. Посчитать количество пробелов.
3. Вывести на экран п2/п1*100, округлив до 2 знаков после запятой
4. Закрыть потоки. Не использовать try-with-resources
*/

import java.io.FileInputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException{
        FileInputStream inputStream = new FileInputStream(args[0]);
        byte[] buffer = new byte[inputStream.available()];
        if (inputStream.available() > 0) {
            inputStream.read(buffer);
        }
        int count = 0;
        for (byte a : buffer) {
            if (a == 32) {
                count++;
            }
        }
        double a = ((double)count / (double) buffer.length) * 100;
        a = a * 100;
        int b = (int) a;
        a = (double) b / 100;

        System.out.println(a);
        inputStream.close();
    }
}
