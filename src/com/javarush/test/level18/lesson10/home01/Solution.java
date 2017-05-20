package com.javarush.test.level18.lesson10.home01;

/* Английские буквы
В метод main первым параметром приходит имя файла.
Посчитать количество букв английского алфавита, которое есть в этом файле.
Вывести на экран число (количество букв)
Закрыть потоки. Не использовать try-with-resources
*/


import java.io.FileInputStream;


public class Solution {
    public static void main(String[] args) throws Exception{

        FileInputStream inputStream = new FileInputStream(args[0]);
        byte[] buffer = new byte[inputStream.available()];
        while (inputStream.available() > 0) {
            int count = inputStream.read(buffer);
        }
        int count = 0;
        for (byte a : buffer) {
            if (a >= 65 && a <= 90 || a >= 97 && a <=122)
                count++;
        }
        System.out.println(count);
        inputStream.close();
    }
}
