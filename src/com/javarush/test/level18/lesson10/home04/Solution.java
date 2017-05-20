package com.javarush.test.level18.lesson10.home04;

/* Объединение файлов
Считать с консоли 2 имени файла
В начало первого файла записать содержимое второго файла так, чтобы получилось объединение файлов
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        reader.close();
        FileInputStream inputStream1 = new FileInputStream(fileName1);
        byte[] buffer1 = new byte[inputStream1.available()];
        if (inputStream1.available() > 0) {
            inputStream1.read(buffer1);
        }
        inputStream1.close();
        FileInputStream inputStream2 = new FileInputStream(fileName2);
        byte[] buffer2 = new byte[inputStream2.available()];
        if (inputStream2.available() > 0) {
            inputStream2.read(buffer2);
        }
        inputStream2.close();
        FileOutputStream outputStream2 = new FileOutputStream(fileName1);
        outputStream2.write(buffer2);
        outputStream2.close();
        FileOutputStream outputStream1 = new FileOutputStream(fileName1, true);
        outputStream1.write(buffer1);
        outputStream1.close();

    }
}
