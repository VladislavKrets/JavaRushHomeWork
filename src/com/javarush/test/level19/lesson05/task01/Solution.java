package com.javarush.test.level19.lesson05.task01;

/* Четные байты
Считать с консоли 2 имени файла.
Вывести во второй файл все байты с четным индексом.
Пример: второй байт, четвертый байт, шестой байт и т.д.
Закрыть потоки ввода-вывода.
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream fileInputStream = new FileInputStream(reader.readLine());
        FileOutputStream fileOutputStream = new FileOutputStream(reader.readLine());
        int count = 0;
        while (fileInputStream.available()>0) {
            count++;
            int data = fileInputStream.read();
            if (count % 2 == 0) {
                fileOutputStream.write((byte)data);
            }
        }
        reader.close();
        fileInputStream.close();
        fileOutputStream.close();
    }
}
