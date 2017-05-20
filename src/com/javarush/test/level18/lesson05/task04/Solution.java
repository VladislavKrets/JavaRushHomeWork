package com.javarush.test.level18.lesson05.task04;

/* Реверс файла
Считать с консоли 2 имени файла: файл1, файл2.
Записать в файл2 все байты из файл1, но в обратном порядке
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream inputStream = new FileInputStream(reader.readLine());
        FileOutputStream outputStream = new FileOutputStream(reader.readLine());
        byte[] buffer = new byte[inputStream.available()];
        byte[] b = new byte[inputStream.available()];
        if (inputStream.available() > 0) {
            int count = inputStream.read(buffer);
            for (int i = count - 1; i >= 0; i--) {
                b[count - 1 - i] = buffer[i];
            }
            outputStream.write(b);
        }
        reader.close();
        inputStream.close();
        outputStream.close();
    }
}
